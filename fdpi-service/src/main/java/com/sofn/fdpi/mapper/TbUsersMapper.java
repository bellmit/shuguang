package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.TbUsers;
import com.sofn.fdpi.vo.CompanyUserInfo;
import com.sofn.fdpi.vo.UserCompanyInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 企业员工
 * wuXY
 * 2019-12-30 11:50:58
 */
public interface TbUsersMapper extends BaseMapper<TbUsers> {
    /**
     * 修改企业员工密码
     * @param map对象
     * @return int
     */
    int updatePasswordById(Map<String, Object> map);

    /**
     * 修改企业员工状态
     * @param map 修改参数map对象
     * @return int
     */
    int updateStatusById(Map<String, Object> map);

    TbUsers getUserIdAndPassword(@Param("compId") String compId);
    List<CompanyUserInfo> getCompUserInfo(Map map);
    UserCompanyInfoVo getCompanyUserInfo(@Param("id") String id);




}
