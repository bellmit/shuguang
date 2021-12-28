package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.AuditProcess;
import com.sofn.fdpi.vo.AuditProcessVo;
import com.sofn.fdpi.vo.PapersProcessVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AuditProcessMapper extends BaseMapper<AuditProcess> {
    /**
     * 证书绑定进度查询
     * @param map 查询参数
     * @return  List<PapersProcessVo>
     */
    List<PapersProcessVo> listForCondition(Map<String,Object> map);
    //修改注册是上报人员的用户id
    int updatePersonId(Map<String,Object> map);
    AuditProcess getObj(@Param("papersId") String papersId,@Param("status")String status);

    /**
     * 批量插入
     * @param list 记录列表
     * @return 执行条数
     */
    int insertBatch(@Param("list") List<AuditProcess> list);

    List<AuditProcessVo> listForAuditProcessByPapersId(@Param("papersId") String papersId);

    /**
     * 流程组件
     * @param map
     * @return
     */
    List<PapersProcessVo> listForConditionByInfo(Map<String, Object> map);
}
