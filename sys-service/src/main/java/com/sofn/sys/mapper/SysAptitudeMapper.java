package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysAptitude;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资质证书管理Dto
 * @author heyongjie
 * @date 2019/11/22 15:17
 */
public interface SysAptitudeMapper  extends BaseMapper<SysAptitude> {

    /**
     * 批量添加资质证书
     * @param sysAptitudes     资质证书信息集合
     */
    void batchInsert(@Param("sysAptitudes") List<SysAptitude> sysAptitudes);

    /**
     * 根据机构删除凭证信息
     * @param orgId  机构ID
     * @param deleteUserId 删除用户
     */
    void deleteByOrgId(@Param("orgId") String orgId,@Param("deleteUserId")String deleteUserId);



}
