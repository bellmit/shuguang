package com.sofn.sys.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.sys.vo.SysReportVo;

import java.util.Map;

/**
 *
 * @author heyongjie
 * @date 2020/5/26 17:09
 */
public interface SysReportService {

    /**
     * 按条件分页查询信息
     * @param params    分页信息
     * @param pageNo    第几页
     * @param pageSize  每页显示条数
     * @return    PageUtils<SysReportVo>
     */
    PageUtils<SysReportVo> getSysReportByCondition(Map<String,String> params, Integer pageNo, Integer pageSize);

    /**
     * 更新报表信息
     * @param sysReport  更改后的信息
     */
    void updateSysReport(SysReportVo sysReport);

    /**
     * 保存报表信息
     * @param sysReportVo   待保存报表信息
     */
    void insertSysReport(SysReportVo sysReportVo);

    /**
     * 删除报表信息
     * @param id   报表ID
     */
    void delSysReport(String id);

    /**
     * 获取报表路径
     * @param reportName  报表名称
     * @param type   报表类型   决策报表还是普通报表
     * @return   报表路径
     */
    String getUrl(String reportName,String type);

    /**
     * 根据报表ID获取报表信息
     * @param id  报表的ID
     * @return  报表信息
     */
    SysReportVo getOne(String id);

}
