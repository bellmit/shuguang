package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.OmComp;
import com.sofn.fdpi.vo.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface OmCompService extends IService<OmComp> {
    /**
     * 企业本身查企业信息
     */
    OmCompVO getOmComp();

    /**
     * 机构通过id查企业信息
     */
    OmCompVO getOmCompById(String id);

    /**
     * 申请提交
     */
    void apply(OmCompForm omCompForm);

    /**
     * 审核
     */
    void audit(String id, String status);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 分页查询
     */
    PageUtils<OmCompVO> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 导出企业查询统计
     */
    void exportCount(Map<String, Object> params, HttpServletResponse response);

    /**
     * 列表查询
     */
    List<OmComp> list(Map<String, Object> params);

    /**
     * 企业名称下拉
     */
    List<SelectVo> listComp(ArrayList<Integer> compTypes);

    /**
     * 得到记录的饼状图数据
     */
    //PieChartData getPieChartData(String compNameId, String dataId,String dataType);

    /**
     * @param params
     * @return com.sofn.common.utils.PageUtils<com.sofn.fdpi.vo.QuotaListVo>
     * @author wg
     * @description 得到欧鳗企业配额分析的列表数据
     * @date 2021/6/7 17:22
     */
    PageUtils<QuotaListVo> getQuotaList(Map<String, Object> params);

    /**
     * @param params
     * @return com.sofn.common.utils.PageUtils<com.sofn.fdpi.vo.OmBreedProcTableVo>
     * @author wg
     * @description 省级部级用户的双表交易比例折算
     * @date 2021/6/8 18:51
     */
    PageUtils<OmBreedProcTableVo> getConvertList(Map<String, Object> params);


    void exportAll(Map<String, Object> params, HttpServletResponse httpServletResponse);
}
