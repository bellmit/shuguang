package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.OmBreed;
import com.sofn.fdpi.model.OmEelImportFrom;
import com.sofn.fdpi.vo.*;
import com.sofn.fdpi.vo.OmBreedProcExportBean;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 欧鳗养殖企业业务层接口
 * @Author wg
 * @Date 2021/5/15 19:14
 **/
public interface OmBreedService extends IService<OmBreed> {
    //养殖企业扣减库存
    void deductionRepo(String compName, String cred, Double num);

    //添加一条数据
    void add(OmBreedVo omBreedvo);

    //得到允许进出口说明书号的列表
    List<CredKV> getCredentialList(String cellComp);

    //得到列表数据
    PageUtils<OmBreedProcTableVo> getList(Map<String, Object> params, Integer pageNo, Integer pageSize);

    //数据更新
    void updateOmBreed(OmBreedVo omBreedVo);

    //数据删除
    void delByid(String id);

    //根据主键查询一条数据
    OmBreedReVo searchByid(String id);

    //导出
    void export(Map<String, Object> map, HttpServletResponse response);

    //养殖企业的柱状图数据
    List<OmHistogram> getBreedHistogram(String importMan, String credential, Date startDate, Date endDate);

    //根据主键得到养殖企业的溯源图
    ImportTraceToSourceVo getBreedTraceSourceById(String id);


}
