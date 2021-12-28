package com.sofn.sys.service;

import com.sofn.sys.vo.SysFileManageVo;

import java.util.List;
import java.util.Map;

/**
 * 资质证书服务
 * @author heyongjie
 * @date 2019/11/22 15:51
 */
public interface SysAptitudeService {

    /**
     * 批量添加资质证书
     * @param infos   证书信息集合  key 为 证书类型 value证书文件列表
     * @param orgId  机构ID
     */
    void batchSave(Map<String, List<String>> infos,String orgId);

    /**
     * 根据机构ID 获取机构的资质信息
     * @param orgId  机构ID
     * @return   Key 为资质ID ， Value为文件的绝对路径
     */
    Map<String,List<SysFileManageVo>> getInfo(String orgId);

    /**
     * 根据机构ID删除凭证信息
     * @param orgId  机构ID
     */
    void deleteByOrgId(String orgId);

    /**
     * 更新数据
     * @param infos   更新后的数据
     * @param orgId  机构ID
     */
    void update(Map<String, List<String>> infos,String orgId);

}
