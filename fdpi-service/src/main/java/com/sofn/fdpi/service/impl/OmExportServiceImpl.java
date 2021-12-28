package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.OmSize;
import com.sofn.fdpi.mapper.OmExportMapper;
import com.sofn.fdpi.mapper.OmProcMapper;
import com.sofn.fdpi.model.OmComp;
import com.sofn.fdpi.model.OmExportModel;
import com.sofn.fdpi.model.OmProc;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysDict;
import com.sofn.fdpi.util.ExportUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import com.sofn.fdpi.vo.OmExportVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/21 17:28
 **/
@Service
public class OmExportServiceImpl extends BaseService<OmExportMapper, OmExportModel> implements OmExportService {
    @Resource
    private OmExportMapper omExportMapper;
    @Resource
    private OmProcMapper omProcMapper;
    @Resource
    private OmProcService omProcService;
    @Resource
    private OmFileService omFileService;
    @Resource
    private OmCompService omCompService;
    @Resource
    private SysRegionApi sysRegionApi;

    @Override
    public List<SelectVo> getCredList(String breedComp) {
        QueryWrapper<OmProc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("cell_comp", breedComp)
                .eq("transfer_comp", omCompService.getOmComp().getId())
                .ne("remaining_qty", 0)
                .select("credential");
        List<OmProc> omProcs = omProcMapper.selectList(queryWrapper);
        //由于下拉的养殖企业一定是有效的所以省去非空判断
        ArrayList<SelectVo> selectVos = new ArrayList<>(omProcs.size());
        for (OmProc omProc : omProcs) {
            selectVos.add(new SelectVo(omProc.getCredential(), omProc.getCredential()));
        }
        return selectVos;
    }

    @Override
    public List<SelectVo> getBreedSelectVo() {
        //得到当前企业的登录信息
        String compId = omCompService.getOmComp().getId();
        List<SelectVo> list = omProcMapper.getBreedSelectVoList(compId);
        list.stream().forEach(a -> {
            if (a.getVal() == null) {
                a.setVal(a.getKey());
            }
        });
        return list;
    }

    //通过主键去查询详情
    @Override
    public OmExportVo searchById(String id) {
        QueryWrapper<OmExportModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("del_flag", BoolUtils.N);
        OmExportModel omExportModel = omExportMapper.selectOne(queryWrapper);
        //model转vo
        OmExportVo omExportVo = new OmExportVo();
        BeanUtils.copyProperties(omExportModel, omExportVo);
        omExportVo.setFiles(omFileService.listBySourceId(omExportModel.getId()));
        return omExportVo;
    }

    @Override
    public List<OmHistogram> getReexportHistogram(String procComp, String credential, Date startDate, Date endDate) {
        if (!SysOwnOrgUtil.getOrgInfo().getThirdOrg().equals("N")) {
            //如果不是第三方企业则需要判断是否携带企业信息
            if (StringUtils.isNotBlank(credential) && StringUtils.isBlank(procComp)) {
                throw new SofnException("需要先选择企业才能按照允许进出口说明书号进行查询");
            }
            //如果不是第三方企业则需要判断是否携带企业信息
            if (StringUtils.isBlank(procComp) && (Objects.nonNull(startDate) || Objects.nonNull(endDate))) {
                throw new SofnException("需要先选择企业才能按照日期进行查询");
            }
        }
        //根据条件分组查询
        List<OmHistogram> omHistogram = omExportMapper.getOmHistogram(StringUtils.isBlank(procComp) ? UserUtil.getLoginUserId() : procComp, credential, startDate, endDate);
        return omHistogram;
    }

    //得到再出口列表数据
    @Override
    public PageUtils<OmRePortListTableVo> getReExportList(String sourceProc, String credential, Date dealDate, int pageNo, int pageSize, String field, String order) {
        //判断当前是第三方企业还是省级、部级用户
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        //得到当前企业的省编号
        String provinceCode = omCompService.getOmComp().getProvinceCode();
        //由于要将主键转换成对应的名字，为了降低访问数据库的次数，直接找到全部的企业名称
        QueryWrapper<OmComp> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("del_flag", BoolUtils.N);
        List<OmComp> omComps = omCompService.list(queryWrapper1);
        //如果是导出为全额导出不需要设置偏移量
        if (pageNo != -1 && pageSize != -1) {
            //设置偏移量
            PageHelper.offsetPage(pageNo, pageSize);
        }
        //必要条件
        QueryWrapper<OmExportModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq(Objects.nonNull(sourceProc), "source_proc", sourceProc)
                .eq(Objects.nonNull(dealDate), "out_of_date", dealDate)
                .like(Objects.nonNull(credential), "credential", credential);
        //排序方式
        if (StringUtils.isNotBlank(field) && StringUtils.isNotBlank(order)) {
            //判断sortord的类型是升序还是降序
            if (order.equals("asc")) {
                queryWrapper.orderByAsc(field);
            } else if (order.equals("desc")) {
                queryWrapper.orderByDesc(field);
            }
        }
        List<OmExportModel> omExportModels = null;
        //多角色查询
        if (BoolUtils.N.equals(orgInfo.getThirdOrg())) {
            //此处是加工企业角色的列表情况
            //得到当前企业的信息，企业的话只能看到自己的数据
            queryWrapper.eq("province", provinceCode).eq("create_user_id", UserUtil.getLoginUserId());
            omExportModels = omExportMapper.selectList(queryWrapper);
        } else {
            if (orgInfo.getOrganizationLevel().equals("ministry")) {
                //如果是部级可以看到全国的加工数据
                omExportModels = omExportMapper.selectList(queryWrapper);
            } else if (orgInfo.getOrganizationLevel().equals("province")) {
                //如果是省级只能看到本省的进口数据
                queryWrapper.eq("province", provinceCode);
                omExportModels = omExportMapper.selectList(queryWrapper);
            }
        }
        PageInfo<OmExportModel> pageInfo = new PageInfo<>(omExportModels);
        //model转vo
        ArrayList<OmRePortListTableVo> omRePortListTableVos = new ArrayList<>(omExportModels.size());
        for (OmExportModel omExportModel : omExportModels) {
            OmRePortListTableVo vo = new OmRePortListTableVo();
            BeanUtils.copyProperties(omExportModel, vo);
            //主键转换成企业名称
            for (int i = 0; i < omComps.size(); i++) {
                if (Objects.equals(vo.getSourceProc(), omComps.get(i).getId())) {
                    vo.setSourceProc(omComps.get(i).getCompName());
                    continue;
                }
            }
            //设置折算比例
            if (OmSize.WHITEEEL.getCode().equals(vo.getImportSize())) {
                vo.setObversion(vo.getExportVolume() * OmSize.WHITEEEL.getCon());
            } else if (OmSize.BLACKEEL.getCode().equals(vo.getImportSize())) {
                vo.setObversion(vo.getExportVolume() * OmSize.BLACKEEL.getCon());
            }
            omRePortListTableVos.add(vo);
        }
        PageInfo<OmRePortListTableVo> tableVoPageInfo = new PageInfo<>(omRePortListTableVos);
        tableVoPageInfo.setTotal(pageInfo.getTotal());
        tableVoPageInfo.setPageNum(pageInfo.getPageNum());
        tableVoPageInfo.setPageSize(pageSize);
        return PageUtils.getPageUtils(tableVoPageInfo);
    }

    //更新再出口数据
    @Override
    public void updateReExport(OmExportFrom omExportVo) {
        //判断是否携带主键
        if (omExportVo.getId() == null) {
            throw new SofnException("没有携带主键");
        }
        //拿到旧数据
        QueryWrapper<OmExportModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("id", omExportVo.getId());
        OmExportModel exportModel = omExportMapper.selectOne(queryWrapper);
        //拿到之前的吨数
        Double oldNum = exportModel.getNum();
        //此处需求不明。
        //vo转model
        OmExportModel omExportModel = new OmExportModel();
        BeanUtils.copyProperties(omExportVo, omExportModel);
        //更新前
        omExportModel.preUpdate();
        //更新
        int i = omExportMapper.updateById(omExportModel);
        if (i != 1) {
            throw new SofnException("更新失败");
        }
        //更新后更新图片信息
        omFileService.update(omExportVo.getId(), omExportVo.getFiles());
    }

    //删除一条再出口数据
    @Override
    public void delReExport(String id) {
        if (StringUtils.isBlank(id)) {
            throw new SofnException("未携带主键");
        }
        //查出详情
        QueryWrapper<OmExportModel> exportModelQueryWrapper = new QueryWrapper<>();
        exportModelQueryWrapper.eq("del_flag", BoolUtils.N)
                .eq("id", id);
        OmExportModel omExportModel = omExportMapper.selectOne(exportModelQueryWrapper);
        //先逻辑删除
        UpdateWrapper<OmExportModel> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id)
                .set("del_flag", BoolUtils.Y)
                .set("update_time", new Date())
                .set("update_user_id", UserUtil.getLoginUserId());
        int i = omExportMapper.update(null, wrapper);
        if (i != 1) {
            throw new SofnException("删除失败!");
        }
        //加工表回退数据
        omProcService.deductionRepo(omExportModel.getSourceProc(), omExportModel.getCredential(), omExportModel.getExportVolume() * (-1));
    }

    //导出再出口数据
    @Override
    public void reportExport(String sourceProc, String credential, Date dealDate, String field, String order, HttpServletResponse response, String exportType) {
        PageUtils<OmRePortListTableVo> reExportList = getReExportList(sourceProc, credential, dealDate, -1, -1, field, order);
        List<OmRePortListTableVo> list = reExportList.getList();
        //根据导出类型分类导出
        if ("1".equals(exportType)) {
            //此处是出口比例折算导出
            ArrayList<OmReportExcel> omReportExcels = new ArrayList<>(list.size());
            //复制属性
            for (OmRePortListTableVo omRePortListTableVo : list) {
                OmReportExcel omReportExcel = new OmReportExcel();
                BeanUtils.copyProperties(omRePortListTableVo, omReportExcel);
                //转换属性
                if (Objects.equals(omRePortListTableVo.getImportSize(), OmSize.WHITEEEL.getCode())) {
                    omReportExcel.setImportSize(OmSize.WHITEEEL.getVal());
                } else if (Objects.equals(omRePortListTableVo.getImportSize(), OmSize.BLACKEEL.getCode())) {
                    omReportExcel.setImportSize(OmSize.BLACKEEL.getVal());
                }
                omReportExcels.add(omReportExcel);
            }
            //导出
            ExportUtil.createExcel(OmReportExcel.class, omReportExcels, response, "出口企业折算报表.xlsx");
        } else if ("2".equals(exportType)) {
            //此处是出口汇总导出
            ArrayList<OmAllReportExcel> omReportExcels = new ArrayList<>(list.size());
            //货物类型
            Result<List<SysDict>> goodType = sysRegionApi.getDictListByType("good_type");
            List<SysDict> goodTypeData = goodType.getData();
            //发货口岸
            Result<List<SysDict>> importPort = sysRegionApi.getDictListByType("import_port");
            List<SysDict> importPorts = importPort.getData();
            //复制属性
            for (OmRePortListTableVo omRePortListTableVo : list) {
                OmAllReportExcel omAllReportExcel = new OmAllReportExcel();
                BeanUtils.copyProperties(omRePortListTableVo, omAllReportExcel);
                //替换发货口岸
                for (SysDict port : importPorts) {
                    if (omRePortListTableVo.getPortOfDispatch().equals(port.getDictcode())) {
                        omAllReportExcel.setPortOfDispatch(port.getDictname());
                        break;
                    }
                }
                //替换货物类型
                for (SysDict goodTypeDatum : goodTypeData) {
                    if (goodTypeDatum.getDictcode().equals(omRePortListTableVo.getGoodsType())) {
                        omAllReportExcel.setGoodsType(goodTypeDatum.getDictname());
                        break;
                    }

                }
                omReportExcels.add(omAllReportExcel);
            }
            //导出
            ExportUtil.createExcel(OmAllReportExcel.class, omReportExcels, response, "出口企业汇总分析.xlsx");
        }
    }

    //添加一条再出口数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addReExport(OmExportFrom omExportFrom) {
        //添加再出口的话先扣减该进口企业的剩余总量,目前的需求是直接扣完
//        Double num = omExportFrom.getNum();
//        omProcResidueService.reduceProcSum(num);
        OmCompVO omComp = omCompService.getOmComp();
        UpdateWrapper<OmProc> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cell_comp", omExportFrom.getSourceBreed())
                .eq("transfer_comp", omComp.getId())
                .eq("credential", omExportFrom.getCredential())
                .eq("del_flag", BoolUtils.N)
                .set("remaining_qty", 0);
        int update = omProcMapper.update(null, updateWrapper);
        if (update != 1) {
            //如果更新失败，说明没有加工企业没有这条数据，直接抛异常
            throw new SofnException("加工企业没有这条数据");
        }
        //扣减后再添加一条出口数据
        OmExportModel exportModel = new OmExportModel();
        BeanUtils.copyProperties(omExportFrom, exportModel);
        exportModel.preInsert();
        //设置来源加工厂就是当前的用户主体对象
        exportModel.setSourceProc(omComp.getId());
        //设置操作
        exportModel.setOperator(omComp.getUsername());
        exportModel.setProvince(omComp.getProvinceCode());
        //插入数据
        omExportMapper.insert(exportModel);
        //上传文件
        omFileService.add(exportModel.getId(), omExportFrom.getFiles());
    }
}
