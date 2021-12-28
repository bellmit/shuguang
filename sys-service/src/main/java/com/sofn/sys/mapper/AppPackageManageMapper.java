package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.AppPackageManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 包管理Mapper
 * @author heyongjie
 * @date 2019/11/18 11:15
 */
public interface AppPackageManageMapper extends BaseMapper<AppPackageManage> {

    /**
     * 按条件查询数据并根据时间倒序排序
     * @param productName   产品名称 模糊查询
     * @param platform   平台
     * @param state   状态
     * @return 符合条件的数据  List<AppPackageManage>
     */
    List<AppPackageManage> getListByCondition(@Param("productName") String productName,
                                              @Param("platform") String platform,
                                              @Param("state") String state);

    /**
     * 根据产品名称获取数据库中安卓产品最大的版本号
     * @param productName  产品名称
     * @return  版本号
     */
    Integer getAndroidMaxVersion(@Param("productName") String productName);

    /**
     * 根据产品名称版本号和平台查询数量
     * @param productName   产品名称
     * @param version  版本号
     * @param platform   平台
     * @param id 传入之后就不包括当前数据
     * @return  符合条件的数量
     */
    Integer getNumberByNameAndPlatform(@Param("productName")String productName,@Param("version")String version,@Param("platform")String platform,@Param("id")String id);

    /**
     * 根据产品和平台获取数据
     * @param productName  产品名称
     * @param platform  平台
     * @return    List<AppPackageManage>
     */
    List<AppPackageManage> getListByProductNameAntPlatform(@Param("productName")String productName,@Param("platform")String platform);

}
