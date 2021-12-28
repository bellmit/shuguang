package com.sofn.agpjpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjpm.constants.Constants;
import com.sofn.agpjpm.mapper.FileManageMapper;
import com.sofn.agpjpm.model.FileAtt;
import com.sofn.agpjpm.model.FileManage;
import com.sofn.agpjpm.model.HabitatType;
import com.sofn.agpjpm.service.FileAttService;
import com.sofn.agpjpm.service.FileManageService;
import com.sofn.agpjpm.util.FileUtil;
import com.sofn.agpjpm.util.RedisUserUtil;
import com.sofn.agpjpm.vo.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-16 17:29
 */
@Service("fileManageService")
public class FileManageServiceImpl implements FileManageService {
    @Autowired
    private FileManageMapper fileManageMapper;
    @Resource
    private FileUtil fileUtil;
    @Autowired
    private FileAttService fileAttService;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(FileForm fileForm) {
        FileManage fo=new FileManage();
        BeanUtils.copyProperties(fileForm,fo);
        fo.setId(IdUtil.getUUId());

        String inputer = "操作人";
        User user = UserUtil.getLoginUser();
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        fo.setCreateUser(inputer);
        fo.setCreateTime(new Date());
        fo.setCreateUserId(UserUtil.getLoginUserId());
        fileManageMapper.insert(fo);
        List<FileAttForm> fileAttFormList = fileForm.getFileAttFormList();
        StringBuilder ids = new StringBuilder();
        for (FileAttForm f:
             fileAttFormList) {
            if (f.getAtt()==null){
                throw new SofnException("附件参数为空");
            }
            if (f.getPic()==null){
                throw new SofnException("文件参数为空");
            }
            ids.append(","+f.getAtt().getAttId());
            ids.append(","+f.getPic().getPicId());
            fileAttService.insert(f,fo.getId());
        }
        if (StringUtils.hasText(ids.toString())) {
            fileUtil.activationFile(ids.toString().substring(1));
        }

    }

    @Override
    public FileManage get(String Id) {
        List<FileAtt> bySouceId = fileAttService.getBySouceId(Id);
        for (FileAtt ft:
             bySouceId) {
            PicFrom pic = new PicFrom();
            BeanUtils.copyProperties(ft,pic);
            ft.setPic(pic);
            AttFrom att=new AttFrom();
            ft.setAtt(att);
            BeanUtils.copyProperties(ft,att);
        }
        FileManage fileManage = fileManageMapper.selectById(Id);
        fileManage.setFileAttList(bySouceId);
        return fileManage;
    }
    @Transactional
    @Override
    public void update(FileForm fileForm) {
        FileManage fo=new FileManage();
        BeanUtils.copyProperties(fileForm,fo);
        String inputer = "操作人";
        User user = UserUtil.getLoginUser();
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        fo.setCreateUser(inputer);
        fo.setCreateTime(new Date());
        fileManageMapper.updateById(fo);
        fileAttService.updateList(fileForm.getFileAttFormList(),fileForm.getId());
    }
    @Transactional
    @Override
    public void del(String id) {
        fileManageMapper.deleteById(id);
        fileAttService.delBySourceId(id);
    }

    @Override
    public PageUtils<FileManage> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        RedisUserUtil.perfectParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<FileManage> fileManages = fileManageMapper.listByParams(params);
        return PageUtils.getPageUtils(new PageInfo(fileManages));
    }


}
