package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.OmSize;
import com.sofn.fdpi.mapper.*;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.util.ExportUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description
 * @Author wg
 * @Date 2021/5/20 10:03
 **/
@Service
public class OmProcServiceImpl extends BaseService<OmProcMapper, OmProc> implements OmProcService {
    @Resource
    private OmBreedService omBreedService;
    @Resource
    private OmBreedMapper omBreedMapper;
    @Resource
    private OmCompService omCompService;
    @Resource
    private OmProcMapper omProcMapper;
    @Resource
    private OmFileService omFileService;
    @Resource
    private OmExportMapper omExportMapper;
    @Resource
    private OmEelImportMapper omEelImportMapper;
    @Resource
    private Redisson redisson;

    //得到加工企业的交易数量和交易频次的柱状图数据
    @Override
    public List<OmHistogram> getOmHistogram(String omProcComp, String credential, Date startDate, Date endDate) {
        if (!SysOwnOrgUtil.getOrgInfo().getThirdOrg().equals("N")) {
            //如果不是第三方企业则需要判断是否携带企业信息
            if (StringUtils.isNotBlank(credential) && StringUtils.isBlank(omProcComp)) {
                throw new SofnException("需要先选择企业才能按照允许进出口说明书号进行查询");
            }
            //如果不是第三方企业则需要判断是否携带企业信息
            if (StringUtils.isBlank(omProcComp) && (Objects.nonNull(startDate) || Objects.nonNull(endDate))) {
                throw new SofnException("需要先选择企业才能按照日期进行查询");
            }
        }
        List<OmHistogram> omHistogram = omProcMapper.getOmHistogram(StringUtils.isBlank(omProcComp) ? UserUtil.getLoginUserId() : omProcComp, credential, startDate, endDate);
        return omHistogram;
    }

    //加工企业扣减剩余欧鳗库存
    @Override
    public void deductionRepo(String procName, String credential, Double num) {
        String key = "procDeductionRepoKey";
        RLock lock = redisson.getLock(key);
        try {
            lock.lock();
            //首先拿到详情
            QueryWrapper<OmProc> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("transfer_comp", procName)
                    .eq("credential", credential)
                    .eq("del_flag", BoolUtils.N);
            OmProc omProc = omProcMapper.selectOne(queryWrapper);
            if (omProc == null) {
                throw new SofnException("加工企业未找到该条数据");
            }
            if (omProc.getRemainingQty() < num) {
                throw new SofnException("扣减超出");
            }
            Double remainingQty = omProc.getRemainingQty();
            //扣完以后的库存
            double nowQty = remainingQty - num;
            //设置扣减后的剩余折算
            double dueCovert = omProc.getImportSize().equals(OmSize.WHITEEEL.getCode()) ? nowQty * (OmSize.WHITEEEL.getCon()) : nowQty * (OmSize.BLACKEEL.getCon());
            //设置当前库存
            UpdateWrapper<OmProc> wrapper = new UpdateWrapper<>();
            wrapper.eq("transfer_comp", procName)
                    .eq("credential", credential)
                    .eq("del_flag", BoolUtils.N)
                    .set("remaining_qty_convert", dueCovert)
                    .set("remaining_qty", nowQty);
            //更新
            int i = omProcMapper.update(null, wrapper);
            if (i != 1) {
                throw new SofnException("扣减失败");
            }
        } catch (SofnException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //向养殖企业进口一条数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(OmProcVo omProc) {
        //先判断该企业是否已经存在同进口证明书的数据再说
        QueryWrapper<OmProc> wrapper0 = new QueryWrapper<>();
        wrapper0.eq("del_flag", BoolUtils.N)
                .eq("credential", omProc.getCredential())
                .eq("transfer_comp", omProc.getTransferComp());
        if (omProcMapper.selectOne(wrapper0) != null) {
            //如果该企业已经存在该证明书直接抛出异常
            throw new SofnException("该加工企业已经存在该允许进出口证明书号数据记录");
        }
        if ("1".equals(omProc.getDealType())) {
            //如果是本系统内交易才扣减养殖企业的剩余库存
            omBreedService.deductionRepo(omProc.getCellComp(), omProc.getCredential(), omProc.getDealNum());
        }
        //其次新增加工企业方的数据
        OmProc proc = new OmProc();
        BeanUtils.copyProperties(omProc, proc);
        //插入前
        proc.preInsert();
        //添加时剩余数量等于新增数量
        proc.setRemainingQty(proc.getDealNum());
        //设置折算比例和剩余折算
        if (proc.getImportSize().equals(OmSize.WHITEEEL.getCode())) {
            //白子鳗的情况
            proc.setObversion(proc.getDealNum() * OmSize.WHITEEEL.getCon());
            proc.setRemainingQtyConvert(proc.getDealNum() * OmSize.WHITEEEL.getCon());
        } else if (proc.getImportSize().equals(OmSize.BLACKEEL.getCode())) {
            //黑子鳗的情况
            proc.setObversion(proc.getDealNum() * OmSize.BLACKEEL.getCon());
            proc.setRemainingQtyConvert(proc.getDealNum() * OmSize.BLACKEEL.getCon());
        }
        //设置省区域编码
        OmCompVO omComp = omCompService.getOmComp();
        String provinceCode = omComp.getProvinceCode();
        proc.setProvince(provinceCode);
        //设置操作人
        proc.setOperator(omComp.getUsername());
        //新增
        int i = omProcMapper.insert(proc);
        if (i != 1) {
            throw new SofnException("新增失败!");
        }
        //文件上传
        omFileService.add(proc.getId(), omProc.getFiles());

//        //判断该企业在库存剩余表里面是否已经有数据了
//        QueryWrapper<ProcResidue> wrapper = new QueryWrapper<>();
//        wrapper.eq("del_flag", BoolUtils.N)
//                .eq("fk_proc_comp", proc.getCreateUserId());
//        ProcResidue procResidue = residueMapper.selectOne(wrapper);
//        if (procResidue == null) {
//            //如果在库存表里面没有找到数据那么insert
//            omProcResidueService.insertSum(proc.getDealNum());
//        } else {
//            //如果说在库存表里面有数据了那么add
//            omProcResidueService.addSum(proc.getDealNum());
//        }
    }

    //根据允许进出口说明书和欧鳗养殖企业名字得到规格和鳗鱼量
    @Override
    public ReExportSomeInfoVo getByCC(String breedCompName, String credential) {
        QueryWrapper<OmProc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("cell_comp", breedCompName)
                .eq("transfer_comp", omCompService.getOmComp().getId())
                .eq("credential", credential);
        OmProc omProc = omProcMapper.selectOne(queryWrapper);
        if (Objects.isNull(omProc)) {
            throw new SofnException("加工企业未找到该条数据");
        }
        ReExportSomeInfoVo reExportSomeInfoVo = new ReExportSomeInfoVo();
        reExportSomeInfoVo.setSize(omProc.getImportSize());
        reExportSomeInfoVo.setSum(omProc.getRemainingQty());
        if (OmSize.WHITEEEL.getCode().equals(omProc.getImportSize())) {
            reExportSomeInfoVo.setObversion(omProc.getRemainingQty() * OmSize.WHITEEEL.getCon());
        } else if (OmSize.BLACKEEL.getCode().equals(omProc.getImportSize())) {
            reExportSomeInfoVo.setObversion(omProc.getRemainingQty() * OmSize.BLACKEEL.getCon());
        }
        return reExportSomeInfoVo;
    }

    //得到详情
    @Override
    public OmBreedReVo getById(String id) {
        QueryWrapper<OmProc> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("del_flag", "N");
        OmProc omProc = omProcMapper.selectOne(wrapper);
        OmBreedReVo omBreedReVo = new OmBreedReVo();
        BeanUtils.copyProperties(omProc, omBreedReVo);
        omBreedReVo.setFiles(omFileService.listBySourceId(id));
        return omBreedReVo;
    }

    //得到列表
    @Override
    public PageUtils<OmBreedProcTableVo> getList(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        //判断当前是第三方企业还是省级、部级用户
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        String provinceCode = omCompService.getOmComp().getProvinceCode();
        //由于要将主键转换成对应的名字，为了降低访问数据库的次数，直接找到全部的企业名称
        QueryWrapper<OmComp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N);
        List<OmComp> omComps = omCompService.list(queryWrapper);
        //如果是导出为全额导出不需要设置偏移量
        if (pageNo != -1 && pageSize != -1) {
            //设置偏移量
            PageHelper.offsetPage(pageNo, pageSize);
        }
        //条件构造器
        QueryWrapper<OmProc> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", BoolUtils.N);
        //判断查询的类型
        if ("1".equals(params.get("searchType"))) {
            wrapper.eq("deal_type", "1");
        } else if ("2".equals(params.get("searchType"))) {
            wrapper.eq("deal_type", "2");
        }
        //排序方式
        String orderBy = params.get("orderBy").toString();
        String sortord = params.get("sortord").toString();
        if (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(sortord)) {
            //判断sortord的类型是升序还是降序
            if (sortord.equals("asc")) {
                wrapper.orderByAsc(orderBy);
            } else if (sortord.equals("desc")) {
                wrapper.orderByDesc(orderBy);
            }
        }
        //动态判断是否加入欧洲鳗鲡出让企业、欧洲鳗鲡受让企业、欧洲鳗鲡交易日期作为条件
        wrapper.like(Objects.nonNull(params.get("cellComp")), "cell_comp", params.get("cellComp"));
        wrapper.eq(Objects.nonNull(params.get("transferComp")), "transfer_comp", params.get("transferComp"));
        wrapper.eq(Objects.nonNull(params.get("dealDate")), "deal_date", params.get("dealDate"));
        wrapper.eq(Objects.nonNull(params.get("dealType")), "deal_type", params.get("dealType"));
        List<OmProc> OmProcs = null;
        //多角色查询
        if (BoolUtils.N.equals(orgInfo.getThirdOrg())) {
            //此处是养殖企业角色的列表情况
            //得到当前企业的信息，企业的话只能看到自己的数据
            String loginUserId = UserUtil.getLoginUserId();
            wrapper.eq("province", provinceCode)
                    .eq("create_user_id", loginUserId);
            OmProcs = omProcMapper.selectList(wrapper);
        } else {
            if (orgInfo.getOrganizationLevel().equals("ministry")) {
                //如果是部级可以看到全国的进口数据
                OmProcs = omProcMapper.selectList(wrapper);
            } else if (orgInfo.getOrganizationLevel().equals("province")) {
                //如果是省级只能看到本省的进口数据
                wrapper.eq("province", provinceCode);
                OmProcs = omProcMapper.selectList(wrapper);
            }
        }
        PageInfo<OmProc> procPageInfo = new PageInfo<>(OmProcs);
        //model转form
        ArrayList<OmBreedProcTableVo> omBreedExportBeans = new ArrayList<>(OmProcs.size());
        for (OmProc omBreed : OmProcs) {
            OmBreedProcTableVo bean = new OmBreedProcTableVo();
            BeanUtils.copyProperties(omBreed, bean);
            //主键转换成企业名称
            for (int i = 0; i < omComps.size(); i++) {
                if (Objects.equals(bean.getCellComp(), omComps.get(i).getId())) {
                    bean.setCellComp(omComps.get(i).getCompName());
                }
                if (Objects.equals(bean.getTransferComp(), omComps.get(i).getId())) {
                    bean.setTransferComp(omComps.get(i).getCompName());
                }
            }
            omBreedExportBeans.add(bean);
        }
        PageInfo<OmBreedProcTableVo> pageInfo = new PageInfo<>(omBreedExportBeans);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(procPageInfo.getPageNum());
        pageInfo.setTotal(procPageInfo.getTotal());
        return PageUtils.getPageUtils(pageInfo);
    }

    //加工企业修改数据
    @Override
    public void updateOmProc(OmProcVo omProcVo) {
        //判断是否携带主键
        if (StringUtils.isBlank(omProcVo.getId())) {
            throw new SofnException("没有携带主键");
        }
        //判断数据是否已经流向了再出口表
        QueryWrapper<OmExportModel> omExportModelQueryWrapper = new QueryWrapper<>();
        omExportModelQueryWrapper.eq("del_flag", BoolUtils.N)
                .eq("source_proc", omProcVo.getTransferComp())
                .eq("credential", omProcVo.getCredential());
        if (omExportMapper.selectOne(omExportModelQueryWrapper) != null) {
            throw new SofnException("数据已经出口不可更改");
        }
        //拿到该条数据在数据库的旧信息
        QueryWrapper<OmProc> wrapper = new QueryWrapper<>();
        wrapper.eq("id", omProcVo.getId())
                .eq("del_flag", "N");
        OmProc oldProc = omProcMapper.selectOne(wrapper);
        //判断当前更新的数据是不是补录的数据
        if (omProcVo.getDealType().equals("1")) {
            //如果是本系统内交易才需要进行扣减进口方的数量
            //判断进口方更新的吨数是否大于出口方的剩余数量
            //首先得到这条数据在养殖企业的详情
            QueryWrapper<OmBreed> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("del_flag", BoolUtils.N)
                    .eq("transfer_comp", omProcVo.getCellComp())
                    .eq("credential", omProcVo.getCredential());
            OmBreed omBreed = omBreedService.getOne(queryWrapper);
            if (omProcVo.getDealNum() - oldProc.getDealNum() > omBreed.getRemainingQty()) {
                throw new SofnException("加工企业进口吨数超过养殖企业剩余吨数" + (omProcVo.getDealNum() - oldProc.getDealNum() - omBreed.getRemainingQty()) + "吨");
            }
            //判断进口方减少的吨数最多减到0
            if (omProcVo.getDealNum() < 0) {
                throw new SofnException("进口方减少的吨数最多减到0");
            }
            //用现在进口的吨数减去之前的吨数就是出口方改变的吨数
            double diff = omProcVo.getDealNum() - oldProc.getDealNum();
            //出口方改变剩余吨数
            omBreedService.deductionRepo(omProcVo.getCellComp(), omProcVo.getCredential(), diff);
        }
        //进口方更新数据
        //vo转model
        OmProc omProc = new OmProc();
        BeanUtils.copyProperties(omProcVo, omProc);
        //设置剩余与折算,因为数据还没有流向再出口表，所以剩余数量就是修改的数量
        omProc.setRemainingQty(omProcVo.getDealNum());
        omProc.setRemainingQtyConvert(omProc.getImportSize() == 0 ? omProc.getDealNum() * OmSize.WHITEEEL.getCon() : omProc.getDealNum() * OmSize.BLACKEEL.getCon());
        omProc.setObversion(omProc.getRemainingQtyConvert());
        //更新前
        omProc.preUpdate();
        //更新
        int i = omProcMapper.updateById(omProc);
        if (i != 1) {
            throw new SofnException("更新失败");
        }
        //更新后更新图片
        omFileService.update(omProcVo.getId(), omProcVo.getFiles());
    }

    @Override
    public OmImportInfo getImportInfoByCnameAndCred(String breedCompName, String credential) {
        //加工企业的出让企业是养殖企业
        QueryWrapper<OmBreed> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("transfer_comp", breedCompName)
                .eq("credential", credential);
        OmBreed omBreed = omBreedMapper.selectOne(queryWrapper);
        if (omBreed == null) {
            throw new SofnException("养殖企业没有找到该条记录");
        }
        //拷贝属性
        OmImportInfo omImportInfo = new OmImportInfo();
        BeanUtils.copyProperties(omBreed, omImportInfo);
        omImportInfo.setSize(omBreed.getImportSize());
        return omImportInfo;
    }

    //根据养殖企业的名称得到有效的允许进出口说明书号
    @Override
    public List<CredKV> getBreedCredList(String breedComp) {
        QueryWrapper<OmBreed> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("transfer_comp", breedComp)
                .ne("remaining_qty", 0);
        List<OmBreed> omBreeds = omBreedMapper.selectList(queryWrapper);
        List<String> credentials = omBreeds.stream().map(a -> a.getCredential()).collect(Collectors.toList());
        ArrayList<CredKV> credKVS = new ArrayList<>(credentials.size());
        for (String credential : credentials) {
            CredKV credKV = new CredKV();
            credKV.setKey(credential);
            credKV.setVal(credential);
            credKVS.add(credKV);
        }
        return credKVS;
    }

    //得到有效的养殖场名称列表
    @Override
    public List<SelectVo> getBreedList() {
        List<SelectVo> list = omBreedMapper.getBreedList();
        return list;
    }

    //根据主键得到加工企业的一条溯源数据
    @Override
    public ImportTraceToSourceVo getBreedTraceSourceById(String id) {
        //首先去加工企业表拿到这条数据
        QueryWrapper<OmProc> procWrapper = new QueryWrapper<>();
        procWrapper.eq("del_flag", BoolUtils.N)
                .eq("id", id);
        OmProc omProc = omProcMapper.selectOne(procWrapper);
        //再拿到在养殖表中的数据
        QueryWrapper<OmBreed> breedWrapper = new QueryWrapper<>();
        breedWrapper.eq("del_flag", BoolUtils.N)
                .eq("transfer_comp", omProc.getCellComp())
                .eq("credential", omProc.getCredential());
        OmBreed omBreed = omBreedService.getOne(breedWrapper);
        //id转name
        QueryWrapper<OmComp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N);
        List<OmComp> list = omCompService.list(queryWrapper);
        //开始设置vo
        ImportTraceToSourceVo sourceVo = new ImportTraceToSourceVo();
        ArrayList<Source> sources = new ArrayList<>();
        Source isource = new Source();
        isource.setId(0);//进口企业的标识
        if ("2".equals(omBreed.getDealType())) {
            //如果是数据补录的话在进口表里面是找不到数据的
            isource.setCompName(omBreed.getCellComp() + "(补录企业(进口))");
        } else if ("1".equals(omBreed.getDealType())) {
            //此处是进口企业属于本系统的情况
            //再拿到进口表中的数据
            QueryWrapper<OmEelImportFrom> importWrapper = new QueryWrapper<>();
            importWrapper.eq("del_flag", BoolUtils.N)
                    .eq("import_man", omBreed.getCellComp())
                    .eq("credential", omBreed.getCredential());
            OmEelImportFrom eelImportFrom = omEelImportMapper.selectOne(importWrapper);
            isource = new Source();
            for (OmComp omComp : list) {
                if (omComp.getId().equals(eelImportFrom.getImportMan())) {
                    isource.setCompName(omComp.getCompName() + "(总" + eelImportFrom.getQuantity() + "吨)(剩余" + eelImportFrom.getRemainingQty() + "吨)");
                    break;
                }
            }
        }
        sources.add(isource);
        Source bsource = new Source();
        bsource.setId(1);//养殖企业的标识
        for (OmComp omComp : list) {
            if (omComp.getId().equals(omBreed.getTransferComp())) {
                bsource.setCompName(omComp.getCompName() + "(总" + omBreed.getDealNum() + "吨)(剩余" + omBreed.getRemainingQty() + "吨)");
                break;
            }
        }
        sources.add(bsource);
        Source psource = new Source();
        psource.setId(2);//加工企业的标识
        for (OmComp omComp : list) {
            if (omComp.getId().equals(omProc.getTransferComp())) {
                psource.setCompName(omComp.getCompName() + "(总" + omProc.getDealNum() + "吨)(剩余" + omProc.getRemainingQty() + "吨)");
                break;
            }
        }
        sources.add(psource);
        sourceVo.setSources(sources);//公司信息设置完毕
        //开始关联
        ArrayList<Target> targets = new ArrayList<>();
        Target target1 = new Target();
        target1.setSourceId(0);
        target1.setTargetId(1);
        target1.setSubLabel(omBreed.getDealDate());
        target1.setDataType("A");
        target1.setDealNum(omBreed.getDealNum());//关联进口和养殖
        targets.add(target1);
        Target target2 = new Target();
        target2.setSourceId(1);
        target2.setTargetId(2);
        target2.setDataType("B");
        target2.setSubLabel(omProc.getDealDate());
        target2.setDealNum(omProc.getDealNum());//关联养殖和加工
        targets.add(target2);
        sourceVo.setSources(sources);
        sourceVo.setTargets(targets);
        return sourceVo;
    }

    //导出
    @Override
    public void exportProcList(Map<String, Object> params, HttpServletResponse response) {
        //先根据条件得到列表数据
        PageUtils<OmBreedProcTableVo> beans = getList(params, -1, -1);
        List<OmBreedProcTableVo> omBreedProcTableVos = beans.getList();
        //判断导出类型
        if ("1".equals(params.get("exportType"))) {
            //此处为比例折算的导出
            ArrayList<OmBreedProcExportBean> omBreedProcExportBeans = new ArrayList<>(omBreedProcTableVos.size());
            for (OmBreedProcTableVo bean : omBreedProcTableVos) {
                OmBreedProcExportBean exportBean = new OmBreedProcExportBean();
                BeanUtils.copyProperties(bean, exportBean);
                //转换规格
                if (Objects.equals(bean.getImportSize(), OmSize.WHITEEEL.getCode())) {
                    exportBean.setImportSize(OmSize.WHITEEEL.getVal());
                } else if (Objects.equals(bean.getImportSize(), OmSize.BLACKEEL.getCode())) {
                    exportBean.setImportSize(OmSize.BLACKEEL.getVal());
                }
                omBreedProcExportBeans.add(exportBean);
            }
            ExportUtil.createExcel(OmBreedProcExportBean.class, omBreedProcExportBeans, response, "加工企业比例折算报表.xlsx");
        } else if ("2".equals(params.get("exportType"))) {
            //此处为汇总分析的导出
            ArrayList<OmBreedAllExportBean> arrayList = new ArrayList<>(omBreedProcTableVos.size());
            for (OmBreedProcTableVo bean : omBreedProcTableVos) {
                OmBreedAllExportBean exportBean = new OmBreedAllExportBean();
                BeanUtils.copyProperties(bean, exportBean);
                //转换规格
                if (Objects.equals(bean.getImportSize(), OmSize.WHITEEEL.getCode())) {
                    exportBean.setImportSize(OmSize.WHITEEEL.getVal());
                } else if (Objects.equals(bean.getImportSize(), OmSize.BLACKEEL.getCode())) {
                    exportBean.setImportSize(OmSize.BLACKEEL.getVal());
                }
                arrayList.add(exportBean);
            }
            ExportUtil.createExcel(OmBreedAllExportBean.class, arrayList, response, "加工企业汇总报表.xlsx");
        }
    }

    //逻辑删除
    @Override
    public void delByid(String id) {
        if (StringUtils.isBlank(id)) {
            throw new SofnException("未携带主键");
        }
        //判断数据是否已经出口
        QueryWrapper<OmProc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("id", id);
        OmProc omProc = omProcMapper.selectOne(queryWrapper);
        QueryWrapper<OmExportModel> exportModelQueryWrapper = new QueryWrapper<>();
        exportModelQueryWrapper.eq("del_flag", BoolUtils.N)
                .eq("source_proc", omProc.getTransferComp())
                .eq("credential", omProc.getCredential());
        OmExportModel omExportModel = omExportMapper.selectOne(exportModelQueryWrapper);
        if (omExportModel != null) {
            throw new SofnException("数据已经出口,不可删除");
        }
        UpdateWrapper<OmProc> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id)
                .set("del_flag", BoolUtils.Y)
                .set("update_time", new Date())
                .set("update_user_id", UserUtil.getLoginUserId());
        int i = omProcMapper.update(null, wrapper);
        if (i != 1) {
            throw new SofnException("删除失败!");
        }
        //如果是非补录数据，出口方需要回退数据
        if (omProc.getDealType().equals("1")) {
            omBreedService.deductionRepo(omProc.getCellComp(), omProc.getCredential(), omProc.getDealNum() * (-1));
        }
//        //如果删除成功那么库存剩余表也要扣减相应的量,先查出来这条数据
//        QueryWrapper<OmProc> queryWrapper = new QueryWrapper<>();
//        //先查出来这条数据
//        queryWrapper.eq("id", id);
//        OmProc omProc = omProcMapper.selectOne(queryWrapper);
//        double v = omProc.getDealNum() * (-1);
//        //扣减加工企业总表的库存
//        omProcResidueService.addSum(v);
    }
}
