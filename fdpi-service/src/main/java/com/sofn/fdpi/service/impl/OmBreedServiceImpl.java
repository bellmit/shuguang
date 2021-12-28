package com.sofn.fdpi.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.OmSize;
import com.sofn.fdpi.mapper.OmBreedMapper;
import com.sofn.fdpi.mapper.OmEelImportMapper;
import com.sofn.fdpi.mapper.OmProcMapper;
import com.sofn.fdpi.model.OmBreed;
import com.sofn.fdpi.model.OmComp;
import com.sofn.fdpi.model.OmEelImportFrom;
import com.sofn.fdpi.model.OmProc;
import com.sofn.fdpi.service.OmBreedService;
import com.sofn.fdpi.service.OmCompService;
import com.sofn.fdpi.service.OmEelImportService;
import com.sofn.fdpi.service.OmFileService;
import com.sofn.fdpi.util.ExportUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.A;
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
 * @Description 欧鳗养殖企业业务层实现类
 * @Author wg
 * @Date 2021/5/15 19:16
 **/
@Service
@Transactional
public class OmBreedServiceImpl extends BaseService<OmBreedMapper, OmBreed> implements OmBreedService {
    @Resource
    private OmBreedMapper omBreedMapper;
    @Resource
    private OmEelImportMapper omEelImportMapper;
    @Resource
    private OmProcMapper omProcMapper;
    @Resource
    private OmFileService omFileService;
    @Resource
    private OmEelImportService omEelImportService;
    @Resource
    private OmCompService omCompService;
    @Resource
    private Redisson redisson;

    @Override
    public OmBreedReVo searchByid(String id) {
        QueryWrapper<OmBreed> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id)
                .eq("del_flag", BoolUtils.N);
        OmBreed omBreed = omBreedMapper.selectOne(wrapper);
        if (omBreed == null) {
            throw new SofnException("该数据不存在或已被删除");
        }
        OmBreedReVo omBreedReVo = new OmBreedReVo();
        BeanUtils.copyProperties(omBreed, omBreedReVo);
        omBreedReVo.setFiles(omFileService.listBySourceId(omBreed.getId()));
        return omBreedReVo;
    }

    //导出
    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        //先拿到数据
        PageUtils<OmBreedProcTableVo> list = getList(params, -1, -1);
        List<OmBreedProcTableVo> beans = list.getList();
        //判断导出类型
        if ("1".equals(params.get("exportType"))) {
            //此处为比例折算的导出
            ArrayList<OmBreedProcExportBean> omBreedProcExportBeans = new ArrayList<>(beans.size());
            for (OmBreedProcTableVo bean : beans) {
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
            ExportUtil.createExcel(OmBreedProcExportBean.class, omBreedProcExportBeans, response, "养殖企业比例折算报表.xlsx");
        } else if ("2".equals(params.get("exportType"))) {
            //此处为汇总分析的导出
            ArrayList<OmBreedAllExportBean> arrayList = new ArrayList<>(beans.size());
            for (OmBreedProcTableVo bean : beans) {
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
            ExportUtil.createExcel(OmBreedAllExportBean.class, arrayList, response, "养殖企业汇总分析.xlsx");
        }
    }

    //根据主键删除一条数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delByid(String id) {
        //判断数据是否已经流向了加工企业
        //先拿到详情
        QueryWrapper<OmBreed> breedQueryWrapper = new QueryWrapper<>();
        breedQueryWrapper.eq("del_flag", BoolUtils.N)
                .eq("id", id);
        OmBreed omBreed = omBreedMapper.selectOne(breedQueryWrapper);
        QueryWrapper<OmProc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("cell_comp", omBreed.getTransferComp())
                .eq("credential", omBreed.getCredential());
        List<OmProc> omProcs = omProcMapper.selectList(queryWrapper);
        if (omProcs.size() != 0) {
            throw new SofnException("该条数据已经开始流向加工企业,不能删除");
        }
        //逻辑删除
        UpdateWrapper<OmBreed> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("del_flag", BoolUtils.Y).
                set("update_time", new Date()).set("update_user_id", UserUtil.getLoginUserId());
        int i = omBreedMapper.update(null, updateWrapper);
        if (i != 1) {
            throw new SofnException("删除失败");
        }
        //如果该数据不是补录数据，那么还需要回退数据
        if (omBreed.getDealType().equals("1")) {
            //删除后回退进口企业的数据
            omEelImportService.deductionRepo(omBreed.getCellComp(), omBreed.getCredential(), omBreed.getDealNum() * (-1));
        }
    }

    //根据得到养殖企业的溯源图
    @Override
    public ImportTraceToSourceVo getBreedTraceSourceById(String id) {
        //首先通过去养殖企业数据库中得到详情
        QueryWrapper<OmBreed> breedWrapper = new QueryWrapper<>();
        breedWrapper.eq("del_flag", BoolUtils.N)
                .eq("id", id);
        OmBreed omBreed = omBreedMapper.selectOne(breedWrapper);
        //id转name
        QueryWrapper<OmComp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N);
        List<OmComp> list = omCompService.list(queryWrapper);
        Source isource = new Source();
        isource.setId(0);
        ImportTraceToSourceVo traceToSourceVo = new ImportTraceToSourceVo();
        ArrayList<Source> sources = new ArrayList<>();
        //判断这条数据是不是补录数据，如果是补录数据的话在进口表示找不到信息的
        if (omBreed.getDealType().equals("1")) {
            //如果不是补录数据，才需要去进口表里面找
            //通过允许进出口说明书号和进口企业名称拿到进口数据
            QueryWrapper<OmEelImportFrom> importWrapper = new QueryWrapper<>();
            importWrapper.eq("del_flag", BoolUtils.N)
                    .eq("credential", omBreed.getCredential())
                    .eq("import_man", omBreed.getCellComp());
            OmEelImportFrom importFrom = omEelImportMapper.selectOne(importWrapper);
            //设置进口企业的信息
            for (OmComp omComp : list) {
                if (omComp.getId().equals(importFrom.getImportMan())) {
                    isource.setCompName(omComp.getCompName() + "(总" + importFrom.getQuantity() + "吨)(剩余" + importFrom.getRemainingQty() + "吨)");
                    break;
                }
            }
        } else {
            //如果是补录数据的话不需要去进口表查
            isource.setCompName(omBreed.getCellComp() + "(补录企业(进口))");
        }
        sources.add(isource);
        //设置养殖企业的信息
        Source bsource = new Source();
        bsource.setId(1);
        for (OmComp omComp : list) {
            if (omComp.getId().equals(omBreed.getTransferComp())) {
                bsource.setCompName(omComp.getCompName() + "(总" + omBreed.getDealNum() + "吨)(剩余" + omBreed.getRemainingQty() + "吨)");
                break;
            }
        }
        sources.add(bsource);
        ArrayList<Target> targets = new ArrayList<>();
        Target target = new Target();//连接养殖企业和进口企业
        target.setTargetId(1);
        target.setSourceId(0);
        target.setDataType("A");//进口到养殖固定为A
        target.setSubLabel(omBreed.getDealDate());
        target.setDealNum(omBreed.getDealNum());
        targets.add(target);
        //去加工企业找到全部数据
        QueryWrapper<OmProc> procWrapper = new QueryWrapper<>();
        procWrapper.eq("del_flag", BoolUtils.N)
                .eq("cell_comp", omBreed.getTransferComp())
                .eq("credential", omBreed.getCredential());
        List<OmProc> omProcs = omProcMapper.selectList(procWrapper);
        for (int i = 0; i < omProcs.size(); i++) {
            //设置加工企业的单位信息
            Source source = new Source();
            source.setId(i + 2);
            for (OmComp omComp : list) {
                if (omComp.getId().equals(omProcs.get(i).getTransferComp())) {
                    source.setCompName(omComp.getCompName() + "(总" + omProcs.get(i).getDealNum() + "吨)(剩余" + omProcs.get(i).getRemainingQty() + "吨)");
                    break;
                }
            }
            sources.add(source);//source信息设置完毕
            //设置养殖企业和各个加工企业的关联信息
            Target target1 = new Target();
            target1.setSourceId(1);//养殖企业标识固定为1
            target1.setTargetId(i + 2);//加工企业的标识从2开始
            target1.setDataType("B");//养殖到加工数据类型为B
            target1.setSubLabel(omProcs.get(0).getDealDate());//设置交易日期
            target1.setDealNum(omProcs.get(0).getDealNum());
            targets.add(target1);
        }
        traceToSourceVo.setSources(sources);
        traceToSourceVo.setTargets(targets);
        return traceToSourceVo;
    }

    //得到养殖企业的柱状图数据
    @Override
    public List<OmHistogram> getBreedHistogram(String breedCompId, String credential, Date startDate, Date endDate) {
        if (!SysOwnOrgUtil.getOrgInfo().getThirdOrg().equals("N")) {
            //如果不是第三方企业则需要判断是否携带企业信息
            if (StringUtils.isNotBlank(credential) && StringUtils.isBlank(breedCompId)) {
                throw new SofnException("需要先选择企业才能按照允许进出口说明书号进行查询");
            }
            //如果不是第三方企业则需要判断是否携带企业信息
            if (StringUtils.isBlank(breedCompId) && (Objects.nonNull(startDate) || Objects.nonNull(endDate))) {
                throw new SofnException("需要先选择企业才能按照日期进行查询");
            }
        }
        List<OmHistogram> omHistogram = omBreedMapper.getOmHistogram(StringUtils.isBlank(breedCompId) ? omCompService.getOmComp().getId() : breedCompId, credential, startDate, endDate);
        return omHistogram;
    }

    //欧鳗养殖企业扣减库存
    @Override
    public void deductionRepo(String compName, String cred, Double num) {
        String key = "breedDeductionRepoKey";
        RLock lock = redisson.getLock(key);
        try {
            lock.lock();
            //首先拿到详情
            QueryWrapper<OmBreed> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("transfer_comp", compName)
                    .eq("credential", cred)
                    .eq("del_flag", BoolUtils.N);
            OmBreed importFrom = omBreedMapper.selectOne(queryWrapper);
            if (importFrom == null) {
                throw new SofnException("进口方未找到该条数据");
            }
            if (importFrom.getRemainingQty() < num) {
                throw new SofnException("扣减超出");
            }
            Double remainingQty = importFrom.getRemainingQty();
            //扣完以后的库存
            double nowQty = remainingQty - num;
            //设置扣减后的剩余折算
            double dueCovert = importFrom.getImportSize().equals(OmSize.WHITEEEL.getCode()) ? nowQty * (OmSize.WHITEEEL.getCon()) : nowQty * (OmSize.BLACKEEL.getCon());
            //设置当前库存
            UpdateWrapper<OmBreed> wrapper = new UpdateWrapper<>();
            wrapper.eq("transfer_comp", compName)
                    .eq("credential", cred)
                    .eq("del_flag", BoolUtils.N)
                    .set("remaining_qty_convert", dueCovert)
                    .set("remaining_qty", nowQty);
            //更新
            int i = omBreedMapper.update(null, wrapper);
            if (i != 1) {
                throw new SofnException("扣减失败");
            }
        } catch (SofnException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public List<CredKV> getCredentialList(String cellComp) {
        //拿到当前企业的信息
        QueryWrapper<OmEelImportFrom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("import_man", cellComp)
                .ne("remaining_qty", 0)
                .select("credential");
        List<OmEelImportFrom> omEelImportFroms = omEelImportMapper.selectList(queryWrapper);
        List<String> collect = omEelImportFroms.stream().map(a -> a.getCredential()).collect(Collectors.toList());
        ArrayList<CredKV> credKVS = new ArrayList<>(collect.size());
        for (String s : collect) {
            CredKV credKV = new CredKV();
            credKV.setKey(s);
            credKV.setVal(s);
            credKVS.add(credKV);
        }
        return credKVS;
    }

    //欧鳗养殖企业从进口企业进口数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(OmBreedVo omBreedVo) {
        //先判断该企业是否已经存在同进口证明书的数据再说
        QueryWrapper<OmBreed> wrapper0 = new QueryWrapper<>();
        wrapper0.eq("del_flag", BoolUtils.N)
                .eq("credential", omBreedVo.getCredential())
                .eq("transfer_comp", omBreedVo.getTransferComp());
        if (omBreedMapper.selectOne(wrapper0) != null) {
            //如果该企业已经存在该证明书直接抛出异常
            throw new SofnException("该养殖企业已经存在该允许进出口证明书号数据记录");
        }
        if (omBreedVo.getDealType().equals("1")) {
            //如果交易类型是本系统企业扣减进口企业方的库存
            omEelImportService.deductionRepo(omBreedVo.getCellComp(), omBreedVo.getCredential(), omBreedVo.getDealNum());
        }
        //其次新增养殖企业方的数据
        OmBreed breed = new OmBreed();
        BeanUtils.copyProperties(omBreedVo, breed);
        //插入前
        breed.preInsert();
        //添加时剩余数量等于新增数量
        breed.setRemainingQty(breed.getDealNum());
        //设置折算比例和剩余折算
        if (breed.getImportSize().equals(OmSize.WHITEEEL.getCode())) {
            //白子鳗的情况
            breed.setObversion(breed.getDealNum() * OmSize.WHITEEEL.getCon());
            breed.setRemainingQtyConvert(breed.getDealNum() * OmSize.WHITEEEL.getCon());
        } else if (breed.getImportSize().equals(OmSize.BLACKEEL.getCode())) {
            //黑子鳗的情况
            breed.setObversion(breed.getDealNum() * OmSize.BLACKEEL.getCon());
            breed.setRemainingQtyConvert(breed.getDealNum() * OmSize.BLACKEEL.getCon());
        }
        //设置省区域编码
        OmCompVO omComp = omCompService.getOmComp();
        String provinceCode = omComp.getProvinceCode();
        breed.setProvince(provinceCode);
        //设置操作人
        breed.setOperator(omComp.getUsername());
        //新增
        int i = omBreedMapper.insert(breed);
        if (i != 1) {
            throw new SofnException("新增失败!");
        }
        //文件上传
        omFileService.add(breed.getId(), omBreedVo.getFiles());
    }

    //更新数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOmBreed(OmBreedVo omBreedVo) {
        //判断是否携带主键
        if (StringUtils.isBlank(omBreedVo.getId())) {
            throw new SofnException("没有携带主键");
        }
        //更新前先判断这条数据是否已经流向加工企业
        QueryWrapper<OmProc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("cell_comp", omBreedVo.getTransferComp())
                .eq("credential", omBreedVo.getCredential());
        List<OmProc> omProcs = omProcMapper.selectList(queryWrapper);
        if (omProcs.size() != 0) {
            throw new SofnException("该条数据已经流向加工企业,不能修改");
        }
        //拿到该条数据在数据库的旧信息
        QueryWrapper<OmBreed> wrapper = new QueryWrapper<>();
        wrapper.eq("id", omBreedVo.getId())
                .eq("del_flag", BoolUtils.N);
        OmBreed oldBreed = omBreedMapper.selectOne(wrapper);
        //判断当前数据是补录数据还是本系统内修改的数据
        if (oldBreed.getDealType().equals("1")) {
            //如果当前不是补录数据才需要操作出口方数据库
            //判断进口方更新的吨数是否大于出口方的剩余数量
            OmImportInfo importFrom = omEelImportService.getInfoByNameAndCred(omBreedVo.getCellComp(), omBreedVo.getCredential());
            if (omBreedVo.getDealNum() - oldBreed.getDealNum() > importFrom.getRemainingQty()) {
                throw new SofnException("养殖企业进口吨数超过进口企业的剩余吨数,进口方剩余" + importFrom.getRemainingQty()
                        + "吨;当次进口在原来的基础上新增" + (omBreedVo.getDealNum() - oldBreed.getDealNum()) + "吨"
                        + "超出进口方吨数" + (omBreedVo.getDealNum() - oldBreed.getDealNum() - importFrom.getRemainingQty() + "吨"));
            }
            //用现在的吨数减去之前的吨数就是出口方改变的吨数
            double diff = omBreedVo.getDealNum() - oldBreed.getDealNum();
            //出口方改变剩余吨数
            omEelImportService.deductionRepo(omBreedVo.getCellComp(), omBreedVo.getCredential(), diff);
        }
        //进口方更新数据
        //vo转model
        BeanUtils.copyProperties(omBreedVo, oldBreed);
        oldBreed.setObversion(oldBreed.getImportSize() == 0 ? oldBreed.getDealNum() * OmSize.WHITEEEL.getCon() : oldBreed.getDealNum() * OmSize.BLACKEEL.getCon());
        //因为数据没有流向加工企业才能更新,所以养殖企业的剩余数量其实就是目前的进口吨数
        oldBreed.setRemainingQty(omBreedVo.getDealNum());
        //设置剩余折算
        oldBreed.setRemainingQtyConvert(omBreedVo.getImportSize() == 0 ? omBreedVo.getDealNum() * OmSize.WHITEEEL.getCon() : omBreedVo.getDealNum() * OmSize.BLACKEEL.getCon());
        //更新前
        oldBreed.preUpdate();
        //更新
        int i = omBreedMapper.updateById(oldBreed);
        if (i != 1) {
            throw new SofnException("更新失败");
        }
        //更新图片信息
        omFileService.update(oldBreed.getId(), omBreedVo.getFiles());
    }

    @Override
    public PageUtils<OmBreedProcTableVo> getList(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        //判断当前是第三方企业还是省级、部级用户
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        String provinceCode = omCompService.getOmComp().getProvinceCode();
        //由于要将主键转换成对应的名字，为了降低访问数据库的次数，直接找到全部的企业名称
        QueryWrapper<OmComp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N);
        List<OmComp> omComps = omCompService.list(queryWrapper);
        if (pageNo != -1 && pageSize != -1) {
            //设置偏移量
            PageHelper.offsetPage(pageNo, pageSize);
        }
        //条件构造器
        QueryWrapper<OmBreed> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", BoolUtils.N);
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
        wrapper.eq(Objects.nonNull(params.get("searchType")), "deal_type", params.get("searchType"));
        wrapper.eq(Objects.nonNull(params.get("cellComp")), "cell_comp", params.get("cellComp"));
        wrapper.eq(Objects.nonNull(params.get("transferComp")), "transfer_comp", params.get("transferComp"));
        wrapper.eq(Objects.nonNull(params.get("dealDate")), "deal_date", params.get("dealDate"));
        wrapper.like(Objects.nonNull(params.get("credential")), "credential", params.get("credential"));
        List<OmBreed> omBreeds = null;
        //多角色查询
        if (BoolUtils.N.equals(orgInfo.getThirdOrg())) {
            //此处是养殖企业角色的列表情况
            //得到当前企业的信息，企业的话只能看到自己的数据
            String loginUserId = UserUtil.getLoginUserId();
            wrapper.eq("province", provinceCode)
                    .eq("create_user_id", loginUserId);
            omBreeds = omBreedMapper.selectList(wrapper);
        } else {
            if (orgInfo.getOrganizationLevel().equals("ministry")) {
                //如果是部级可以看到全国的进口数据
                omBreeds = omBreedMapper.selectList(wrapper);
            } else if (orgInfo.getOrganizationLevel().equals("province")) {
                //如果是省级只能看到本省的进口数据
                wrapper.eq("province", provinceCode);
                omBreeds = omBreedMapper.selectList(wrapper);
            }
        }
        PageInfo<OmBreed> omBreedPageInfo = new PageInfo<>(omBreeds);
        //model转form
        ArrayList<OmBreedProcTableVo> omBreedExportBeans = new ArrayList<>(omBreeds.size());
        for (OmBreed omBreed : omBreeds) {
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
        pageInfo.setTotal(omBreedPageInfo.getTotal());
        pageInfo.setPageNum(omBreedPageInfo.getPageNum());
        return PageUtils.getPageUtils(pageInfo);
    }
}
