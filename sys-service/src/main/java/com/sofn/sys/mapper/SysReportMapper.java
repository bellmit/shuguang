package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysReport;
import com.sofn.sys.vo.SysReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/5/26 16:41
 */
public interface SysReportMapper extends BaseMapper<SysReport> {

    /**
     * 根据报表ID逻辑删除报表
     *
     * @param id           报表ID
     * @param updateUserId 修改人
     * @param updateDate   修改时间
     */
    void deleteSysReportById(@Param("id") String id, @Param("updateUserId") String updateUserId, @Param("updateDate") Date updateDate);

    /**
     * 根据报表名称或者系统ID查询报表数据量
     *
     * @param reportName 报表名称
     * @param appId      系统ID
     * @param id         传入就不包含该ID对应的数据
     * @return 数据量
     */
    int getReportCountByNameAndAppId(@Param("reportName") String reportName, @Param("appId") String appId, @Param("id") String id);

    /**
     * 按条件查询报表信息
     * @param params  条件
     * @return   List<SysReportVo>
     */
    List<SysReportVo> getSysReportByCondition(Map<String,String> params);

}
