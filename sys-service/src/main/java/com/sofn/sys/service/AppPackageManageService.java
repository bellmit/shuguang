package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.AppPackageManage;
import com.sofn.sys.vo.AppPackageManageForm;
import com.sofn.sys.vo.AppPackageManageVo;

/**
 * @author heyongjie
 * @date 2019/11/18 11:13
 */
public interface AppPackageManageService  extends IService<AppPackageManage> {

    /**
     * 添加App包
     * @param appPackageManageForm   基本信息
     */
    void insert(AppPackageManageForm appPackageManageForm);

    /**
     * 删除App管理数据
     * @param id   ID
     */
    void delete(String id);

    /**
     * 分页获取数据
     * @param pageNo  从哪开始查询
     * @param pageSize  每页显示多少条
     * @param productName   产品名称
     * @param platform  平台
     * @param state  状态
     * @return  分页后的数据
     */
    PageUtils getList(Integer pageNo, Integer pageSize, String productName, String platform, String state);

    /**
     * 根据ID获取数据
     * @param id   ID
     * @return   AppPackageManageVo
     */
    AppPackageManageVo getOne(String id);

    /**
     * 获取最新的版本信息
     * @param productName  产品名称
     * @param platform   平台
     * @return  最新的版本信息
     */
    AppPackageManageVo getTheLatestVersion(String productName,String  platform);

    /**
     * 更新产品信息，更新版本不能替换文件
     * @param appPackageManageForm  更新后的信息
     */
    void update(AppPackageManageForm appPackageManageForm);

}
