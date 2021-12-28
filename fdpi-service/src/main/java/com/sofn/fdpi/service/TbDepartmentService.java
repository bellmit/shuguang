package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.TbDepartment;
import com.sofn.fdpi.vo.DepartmentLevelVo;
import com.sofn.fdpi.vo.TbDepartmentForm;
import com.sofn.fdpi.vo.TbDepartmentVo;

import java.util.Map;
import java.util.Set;

public interface TbDepartmentService extends IService<TbDepartment> {
    PageUtils<TbDepartmentVo> listForPage(Map<String,Object> map,Integer pageNo,Integer pageSize);
    String deleteDepartment(String deptId);
    void addDepartment(TbDepartmentForm tbDepartmentForm,String type);
    String updateDepartment(TbDepartmentForm tbDepartmentForm,String type);
    TbDepartmentVo getOneById(String deptId,String type);
    String convertDataToRedis();
    /**
     * 注册是根据企业的省市区来匹配直属机构
     * @param compProvinceCode 省级编号
     * @param compCityCode 市级编号
     * @param compDistrictCode 区级编号
     * @return 直属机构编号和级别
     */
    DepartmentLevelVo getRedirectTempId(String compProvinceCode, String compCityCode, String compDistrictCode);

    Boolean isDirectlyDept();

    Boolean isAuth(String type);

    Set<String> listAllAuth(String type);


}
