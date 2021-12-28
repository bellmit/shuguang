package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.AppPackageManageEnum;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.AppPackageManageMapper;
import com.sofn.sys.mapper.SysFileManageMapper;
import com.sofn.sys.model.AppPackageManage;
import com.sofn.sys.model.SysFileManage;
import com.sofn.sys.service.AppPackageManageService;
import com.sofn.sys.service.SysFileManageService;
import com.sofn.sys.vo.AppPackageManageForm;
import com.sofn.sys.vo.AppPackageManageVo;
import com.sofn.sys.vo.SysFileManageForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * App包管理服务实现
 *
 * @author heyongjie
 * @date 2019/11/18 14:24
 */
@Slf4j
@Service
public class AppPackageManageServiceImpl extends ServiceImpl<AppPackageManageMapper, AppPackageManage> implements AppPackageManageService {

    @Autowired
    private AppPackageManageMapper appPackageManageMapper;

    @Autowired
    private SysFileManageService sysFileManageService;

    @Autowired
    private SysFileManageMapper sysFileManageMapper;

    /**
     * App包管理接口！ 这个接口下的文件不会做权限拦截就可下载
     */
    private final static String APP_PACKAGE_INTERFACE = "hidden_appPackageManage";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(AppPackageManageForm appPackageManageForm) {
        if (StringUtils.isBlank(appPackageManageForm.getPlatform())) {
            throw new SofnException("添加操作，请填写platform参数");
        }
        // 1. 一个产品在同一个平台上不能出现重复的版本号
        Integer numberByNameAndPlatform = appPackageManageMapper.getNumberByNameAndPlatform(appPackageManageForm.getProductName(), appPackageManageForm.getVersion(), appPackageManageForm.getPlatform(), null);
        if (numberByNameAndPlatform > 0) {
            throw new SofnException(String.format("%s已经在平台%s上有相同的版本了", appPackageManageForm.getProductName(), appPackageManageForm.getPlatform()));
        }

        // 2. 判断文件是否存在
        SysFileManage one = sysFileManageMapper.selectById(appPackageManageForm.getSysFileManageId());
        if (one == null) {
            throw new SofnException("App包对应的文件无效");
        }
        if(SysManageEnum.DEL_FLAG_Y.getCode().equals(one.getDelFlag())){
            throw new SofnException("App包对应的文件无效");
        }
        // 3. 准备必要参数
        AppPackageManage appPackageManage = AppPackageManageForm.getAppPackageManage(appPackageManageForm);
        appPackageManage.preInsert();
        appPackageManage.setId(IdUtil.getUUId());
        // 如果是android平台需要自动获取整形版本号，默认从1开始
        if (AppPackageManageEnum.PLATFORM_ANDROID.getCode().equals(appPackageManageForm.getPlatform())) {
            Integer androidMaxVersion = appPackageManageMapper.getAndroidMaxVersion(appPackageManageForm.getProductName());
            if (androidMaxVersion == null) {
                androidMaxVersion = 0;
            }
            appPackageManage.setIntVersion(++androidMaxVersion);
        }
        // 4. 保存数据
        appPackageManageMapper.insert(appPackageManage);
        // 5. 激活App文件
        sysFileManageService.activationFile(new SysFileManageForm("",
                SysManageEnum.SYS_SYSTEM_ID.getCode(), "App包管理",
                APP_PACKAGE_INTERFACE, appPackageManageForm.getSysFileManageId()),
                UserUtil.getLoginToken()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        AppPackageManage appPackageManage = checkDataIsExists(id);
        // 逻辑删除数据
        appPackageManage.preUpdate();
        appPackageManage.setDelFlag(SysManageEnum.DEL_FLAG_Y.getCode());
        appPackageManageMapper.updateById(appPackageManage);
        // 删除文件
        sysFileManageService.deleteFile(appPackageManage.getSysFileManageId(), UserUtil.getLoginToken());
    }

    /**
     * 检查数据是否存在，如果不存在，直接抛出异常
     *
     * @param id 数据ID
     * @return 存在的数据
     */
    private AppPackageManage checkDataIsExists(String id) {
        AppPackageManage appPackageManage = appPackageManageMapper.selectById(id);
        if (appPackageManage == null) {
            throw new SofnException("记录不存在");
        }
        if(SysManageEnum.DEL_FLAG_Y.getCode().equals(appPackageManage.getDelFlag())){
            throw new SofnException("数据已删除");
        }
        return appPackageManage;
    }


    @Override
    public PageUtils getList(Integer pageNo, Integer pageSize, String productName, String platform, String state) {
        PageHelper.startPage(pageNo, pageSize);
        List<AppPackageManage> listByCondition = appPackageManageMapper.getListByCondition(productName, platform, state);
        PageInfo<AppPackageManage> appPackageManagePageInfo = new PageInfo<>(listByCondition);
        PageInfo<AppPackageManageVo> appPackageManageVoPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(appPackageManagePageInfo, appPackageManageVoPageInfo);
        List<AppPackageManage> list = appPackageManagePageInfo.getList();
        List<AppPackageManageVo> appPackageManageVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach((appPackageManage) -> appPackageManageVos.add(AppPackageManageVo.getAppPackageManageVo(appPackageManage)));
        }
        appPackageManageVoPageInfo.setList(appPackageManageVos);
        return PageUtils.getPageUtils(appPackageManageVoPageInfo);
    }

    @Override
    public AppPackageManageVo getOne(String id) {
        AppPackageManage appPackageManage = checkDataIsExists(id);
        return AppPackageManageVo.getAppPackageManageVo(appPackageManage);
    }

    @Override
    public AppPackageManageVo getTheLatestVersion(String productName, String platform) {
        // 根据时间获取最新版本信息
        // 1. 将当前产品在当前平台的所有数据取出来
        List<AppPackageManage> listByProductNameAntPlatform = appPackageManageMapper.getListByProductNameAntPlatform(productName, platform);
        //去除文件被删除的版本
        for(AppPackageManage appPackageManage:listByProductNameAntPlatform){
            SysFileManage fileManage=sysFileManageMapper.getOne(appPackageManage.getSysFileManageId());
            if(fileManage==null){
                listByProductNameAntPlatform.remove(appPackageManage);
            }
        }
        if (CollectionUtils.isEmpty(listByProductNameAntPlatform)) {
            return null;
        }
        // 2. 将日期转为Long型用于比较  当修改日期为空时使用创建日期
        List<Long> dates = Lists.newArrayList();
        listByProductNameAntPlatform.forEach((e) -> {
            Date date =  e.getCreateTime();
            dates.add(date.getTime());
        });
        // 3. 找出最大的日期
        Optional<Long> max = dates.stream().max(Long::compare);
        if (max.isPresent()) {
            Long maxDate = max.get();
            // 4. 根据最大的日期查找数据
            Optional<AppPackageManage> first = listByProductNameAntPlatform.stream().filter((e) -> {
                Date date =  e.getCreateTime();
                return date.getTime() == maxDate;
            }).findFirst();
            if (first.isPresent()) {
                AppPackageManage appPackageManage = first.get();
                // 5. 将找到的数据转为Vo
                return AppPackageManageVo.getAppPackageManageVo(appPackageManage);
            }
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AppPackageManageForm appPackageManageForm) {
        AppPackageManage appPackageManage = checkDataIsExists(appPackageManageForm.getId());
        // 只能更新产品描述和状态
        appPackageManage.setUpdateDescription(appPackageManageForm.getUpdateDescription());
        appPackageManage.setState(appPackageManageForm.getState());
        appPackageManageMapper.updateById(appPackageManage);
    }
}
