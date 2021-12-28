package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.OmExportModel;
import com.sofn.fdpi.vo.OmExportFrom;
import com.sofn.fdpi.vo.OmHistogram;
import com.sofn.fdpi.vo.OmRePortListTableVo;
import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.OmExportVo;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/21 17:27
 **/
public interface OmExportService extends IService<OmExportModel> {
    //添加一条再出口数据
    void addReExport(OmExportFrom omExportFrom);

    //导出再出口数据
    void reportExport(String sourceProc, String credential, Date dealDate, String field, String order, HttpServletResponse response, String exportType);

    //删除一条再出口数据
    void delReExport(String id);

    //修改一条再出口数据
    void updateReExport(OmExportFrom omExportFrom);

    //得到再出口列表数据
    PageUtils<OmRePortListTableVo> getReExportList(String sourceProc, String credential, Date dealDate, int pageNo, int pageSize, String field, String order);

    //再出口时得到有效的养殖企业信息
    List<SelectVo> getBreedSelectVo();

    //再出口时根据养殖企业的名字得到有效的允许进出口说明书号
    List<SelectVo> getCredList(String breedComp);

    List<OmHistogram> getReexportHistogram(String breedComp, String credential, Date startDate, Date endDate);

    OmExportVo searchById(String id);
}
