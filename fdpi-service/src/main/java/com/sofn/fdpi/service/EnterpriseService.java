package com.sofn.fdpi.service;

import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.vo.CompInRegisterVo;
import com.sofn.fdpi.vo.EnterpriseForm;
import com.sofn.fdpi.vo.SelectVo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 企业注册
 * @Author: wuXY
 * @Date: 2019-12-30 13:57:48
 */
public interface EnterpriseService {
    /**
     * 企业注册
     * @param  enterpriseForm 注册对象
     * @return 1：返回成功；其它：异常提示
     */
    String registerCompany(EnterpriseForm enterpriseForm);

    /**
     * 获取证书下拉列表
     * @param map 证书类型
     * @return List<SelectVo>对象
     */
    List<SelectVo> listPapersForSelect(Map<String,Object> map);

    /**
     * 根据证书id，获取证书信息
     * @param id 证书表主键
     * @return Papers
     */
    Papers getPapersById(String id);

    /**
     * 通过企业名称获取最近一次证书中企业的信息
     * @param compName 企业名称
     * @return 企业对象
     */
    CompInRegisterVo getCompByCompName(String compName);

}
