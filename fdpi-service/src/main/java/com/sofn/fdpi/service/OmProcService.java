package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.OmProc;
import com.sofn.fdpi.vo.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/20 9:55
 **/
public interface OmProcService extends IService<OmProc> {
    //添加一条数据
    void add(OmProcVo omProc);

    //得到加工企业的柱状图数据
    List<OmHistogram> getOmHistogram(String omProcComp, String credential, Date startDate, Date endDate);

    //得到详情
    OmBreedReVo getById(String id);

    //根据养殖企业和允许进出口说明书得到成鳗量
    ReExportSomeInfoVo getByCC(String breedCompName, String credential);

    //得到列表数据
    PageUtils<OmBreedProcTableVo> getList(Map<String, Object> params, Integer pageNo, Integer pageSize);

    //数据更新
    void updateOmProc(OmProcVo OmProcVo);

    //数据删除
    void delByid(String id);

    //导出
    void exportProcList(Map<String, Object> map, HttpServletResponse response);

    //根据主键得到养殖企业的溯源图
    ImportTraceToSourceVo getBreedTraceSourceById(String id);

    //得到有效的养殖企业名称
    List<SelectVo> getBreedList();

    //根据选择的养殖企业名称得到有效的允许进出口说明书号
    List<CredKV> getBreedCredList(String breedComp);

    //加工企业扣减剩余欧鳗量
    void deductionRepo(String procName, String credential, Double num);

    //根据养殖企业名称和允许进出口说明书的得到该数据进口时的详情
    OmImportInfo getImportInfoByCnameAndCred(String breedCompName, String credential);
}
