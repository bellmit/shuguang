package com.sofn.agsjsi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agsjsi.model.WetExamplePointCollect;
import com.sofn.agsjsi.vo.DropDownVo;
import com.sofn.agsjsi.vo.WetExamplePointCollectForm;
import com.sofn.agsjsi.vo.WetExamplePointCollectVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface WetExamplePointCollectService extends IService<WetExamplePointCollect> {
    /**
     * 分页列表
     * WuXY
     * 2020-4-10 11:26:47
     * @param wetName 湿地区名称
     * @param wetCode 湿地区编码
     * @param secondBasin 所属二级流域
     * @param status  状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageNo 分页页数
     * @param pageSize 分页条数
     * @return 分页列表对象
     */
    PageUtils<WetExamplePointCollectVo> listPage(String wetName,String wetCode,String secondBasin, String status,String startTime,String endTime, int pageNo, int pageSize,String isApprove);

    /**
     * 通过id获取对象信息
     * WuXY
     * 2020-4-10 11:28:44
     * @param id 主键
     * @param isView true：查看详情；false：编辑中获取
     * @return 返回对象
     */
    WetExamplePointCollectVo getPointCollect(String id,boolean isView);

    /**
     * 新增中保存
     * WuXY
     * 2020-4-10 11:28:44
     * @param form 表单
     * @return '1':成功；其它提示
     */
    String saveAndReport(WetExamplePointCollectForm form,boolean isReport);
    /**
     * 修改中保存
     * WuXY
     * 2020-4-10 11:28:44
     * @param form 表单
     * @return '1':成功；其它提示
     */
    String updateAndReport(WetExamplePointCollectForm form,boolean isReport);
    /**
     * 上报/撤销
     * WXY
     * 2020-2-29 10:02:44
     * @param id 主键
     * @param type 1；上报；2：撤销
     * @return "1":成功;其它：提示
     */
    String updateStatus(String id,String type);

    /**
     * 删除
     * WXY
     * 2020-2-29 10:02:44
     * @param id 主键
     * @return "1":成功;其它：提示
     */
    String delObj(String id);
    /**
     * 导出excel
     * WuXY
     * 2020-4-10 11:26:47
     * @param wetName 湿地区名称
     * @param wetCode 湿地区编码
     * @param secondBasin 所属二级流域
     * @param status  状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    void export(String wetName,String wetCode,String secondBasin, String status,String startTime,String endTime,String isApprove, HttpServletResponse response);

    /**
     * 审核或者退回
     * @param id 主键
     * @param isApprove true：审核；false：退回
     * @return 审核结果
     */
    String approveOrReturn(String id, String advice, boolean isApprove);

    List<DropDownVo> listForSelect(String lastRegionCode);
}
