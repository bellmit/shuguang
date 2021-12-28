package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.OmSize;
import com.sofn.fdpi.mapper.OmBreedMapper;
import com.sofn.fdpi.mapper.OmEelImportMapper;
import com.sofn.fdpi.model.OmBreed;
import com.sofn.fdpi.model.OmComp;
import com.sofn.fdpi.model.OmEelImportFrom;
import com.sofn.fdpi.model.SourceInfoModel;
import com.sofn.fdpi.service.OmCompService;
import com.sofn.fdpi.service.OmEelImportService;
import com.sofn.fdpi.service.OmFileService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysDict;
import com.sofn.fdpi.util.ExportUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/12 11:44
 **/
@Service
public class OmEelImportServiceImpl extends BaseService<OmEelImportMapper, OmEelImportFrom> implements OmEelImportService {
    @Resource
    private OmEelImportMapper omEelImportMapper;
    @Resource
    private OmCompService omCompService;
    @Resource
    private OmFileService omFileService;
    @Resource
    private OmBreedMapper omBreedMapper;
    @Resource
    private SysRegionApi sysRegionApi;
    @Resource
    private Redisson redisson;

    //扣减库存
    @Override
    @Transactional
    public void deductionRepo(String name, String cred, Double num) {
        //开始扣减库存，加锁防止超卖发生
        //定义key
        String key = "ImportDeductionRepo";
        RLock lock = redisson.getLock(key);
        try {
            lock.lock();
            //首先拿到详情
            OmImportInfo importFrom = getInfoByNameAndCred(name, cred);
            if (importFrom == null) {
                throw new SofnException("进口方未找到该条数据");
            }
            if (importFrom.getRemainingQty() < num) {
                throw new SofnException("扣减超出");
            }
            Double remainingQty = importFrom.getRemainingQty();
            //扣完以后的库存
            double nowQty = remainingQty - num;
            //设置当前库存
            UpdateWrapper<OmEelImportFrom> wrapper = new UpdateWrapper<>();
            wrapper.eq("import_man", name)
                    .eq("credential", cred)
                    .eq("del_flag", BoolUtils.N)
                    .set("remaining_qty", nowQty);
            //更新
            int i = omEelImportMapper.update(null, wrapper);
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
    public OmImportInfo getInfoByNameAndCred(String compName, String cred) {
        QueryWrapper<OmEelImportFrom> wrapper = new QueryWrapper<>();
        wrapper.eq("import_man", compName)
                .eq("credential", cred)
                .eq("del_flag", BoolUtils.N);
        OmEelImportFrom omEelImportFrom = omEelImportMapper.selectOne(wrapper);
        if (omEelImportFrom == null) {
            throw new SofnException("进口方未找到该条记录");
        }
        OmImportInfo omImportInfo = new OmImportInfo();
        BeanUtils.copyProperties(omEelImportFrom, omImportInfo);
        return omImportInfo;
    }

    //新增进口欧鳗
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(OmEelImportVo omEelImportVo) {
        //如果新增的数量为0，那么当次请求无效
        if (omEelImportVo.getQuantity() == 0) {
            throw new SofnException("新增吨数为0，当次请求无效");
        }
        //判断有效期和进口日期是否在审批时间之后
        if (omEelImportVo.getApproveTime().after(omEelImportVo.getPeriodOfValidity())) {
            throw new SofnException("有效期需要在审批时间之后");
        }
        //判断有效期和进口日期是否在审批时间之后
        if (omEelImportVo.getApproveTime().after(omEelImportVo.getImportDate())) {
            throw new SofnException("进口日期需要在审批时间之后");
        }
        //先判断该企业是否已经存在同进口证明书的数据再说
        QueryWrapper<OmEelImportFrom> wrapper0 = new QueryWrapper<>();
        wrapper0.eq("del_flag", BoolUtils.N)
                .eq("credential", omEelImportVo.getCredential())
                .eq("import_man", omEelImportVo.getImportMan())
                .eq("create_user_id", UserUtil.getLoginUserId());
        if (omEelImportMapper.selectOne(wrapper0) != null) {
            //如果该企业已经存在该证明书直接抛出异常
            throw new SofnException("该企业已经存在该允许进出口证明书号");
        }
        //判断新增的欧鳗数量是否已经超过准许驯养繁殖数量
        OmCompVO omComp = omCompService.getOmComp();
        //得到该企业准许驯养繁殖情况鳗苗(吨)
        Double tameAllowTon = omComp.getTameAllowTon();
        if (tameAllowTon == 0 || tameAllowTon == null) {
            throw new SofnException("当前企业准许驯养繁殖鳗苗吨数为0");
        }
        //判断当前进口的鳗鱼吨数是否超出了准许驯养繁殖吨数
        if (omEelImportVo.getQuantity() > tameAllowTon) {
            throw new SofnException("新增失败,当次进口鳗鱼吨数" + omEelImportVo.getQuantity() + "吨,准许驯养繁殖吨数" + tameAllowTon + "吨");
        }
        //得到该企业已经进口的鳗鱼总数
        QueryWrapper<OmEelImportFrom> wrapper = new QueryWrapper<>();
        String loginUserId = UserUtil.getLoginUserId();
        wrapper.eq("province", omComp.getProvinceCode())
                .eq("create_user_id", loginUserId)
                .eq("del_flag", "N");
        List<OmEelImportFrom> omEelImportFroms = omEelImportMapper.selectList(wrapper);
        if (omEelImportFroms.size() != 0) {
            //得到剩余的总吨数
            Double reduce = omEelImportFroms.stream().map(a -> a.getRemainingQty()).reduce(0.0, (a, b) -> a + b);
            //判断已进口剩余的总吨数加上当前的进口吨数是否已经超出了准许
            if ((reduce + omEelImportVo.getQuantity()) > tameAllowTon) {
                throw new SofnException("当前已经超过了准许驯养繁殖情况鳗苗。准许驯养:"
                        + tameAllowTon + "吨。当前已进口欧鳗剩余总吨数:"
                        + reduce + "吨;当次进口"
                        + omEelImportVo.getQuantity() + "吨;超出准许驯养繁殖吨数:"
                        + (reduce + omEelImportVo.getQuantity() - tameAllowTon) + "吨。");
            }
        }
        //创建实体
        OmEelImportFrom omEelImportFrom = new OmEelImportFrom();
        //vo转model
        BeanUtils.copyProperties(omEelImportVo, omEelImportFrom);
        //插入前
        omEelImportFrom.preInsert();
        //设置省区域编码
        omEelImportFrom.setProvince(omComp.getProvinceCode());
        //设置操作人
        omEelImportFrom.setOperator(omComp.getUsername());
        //新增时剩余欧鳗等于新增的欧鳗
        omEelImportFrom.setRemainingQty(omEelImportFrom.getQuantity());
        int insert = omEelImportMapper.insert(omEelImportFrom);
        if (insert == 1) {
            //上传文件信息
            omFileService.add(omEelImportFrom.getId(), omEelImportVo.getFiles());
            return true;
        }
        throw new SofnException("添加失败");
    }

    //查询详情
    @Override
    public OmImportVo searchByid(String id) {
        QueryWrapper<OmEelImportFrom> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("del_flag", "N");
        OmEelImportFrom omEelImportFrom = omEelImportMapper.selectOne(wrapper);
        OmImportVo omImportVo = new OmImportVo();
        BeanUtils.copyProperties(omEelImportFrom, omImportVo);
        omImportVo.setFiles(omFileService.listBySourceId(id));
        return omImportVo;
    }

    @Override
    public List<SelectVo> getImportList() {
        List<SelectVo> importList = omEelImportMapper.getImportList();
        return importList;
    }

    //根据进口企业的主键得到溯源图数据
    @Override
    public ImportTraceToSourceVo getTraceSourceById(String id) {
        //先通过主键找到这条数据的在进口表的详情
        QueryWrapper<OmEelImportFrom> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", BoolUtils.N)
                .eq("id", id);
        OmEelImportFrom omEelImportFrom = omEelImportMapper.selectOne(wrapper);
        ImportTraceToSourceVo sourceVo = new ImportTraceToSourceVo();
        //处理source
        Source importSource = new Source();
        //此处是进口企业的信息
        importSource.setId(0);//进口企业的标识固定为0
        //id转name
        QueryWrapper<OmComp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N);
        List<OmComp> list = omCompService.list(queryWrapper);
        for (OmComp omComp : list) {
            if (omEelImportFrom.getImportMan().equals(omComp.getId())) {
                importSource.setCompName(omComp.getCompName() + "(总" + omEelImportFrom.getQuantity() + "吨)(剩余" + omEelImportFrom.getRemainingQty() + "吨)");
                break;
            }
        }
        //通过进口企业名称和允许进出口说明书号去养殖企业数据库查交易企业名称和交易量
        List<SourceInfoModel> sourceInfo = omBreedMapper.getSourceInfo(omEelImportFrom.getImportMan(), omEelImportFrom.getCredential());
        //设置source
        ArrayList<Source> sources = new ArrayList<>(sourceInfo.size() + 1);
        //准备targets
        ArrayList<Target> targets = new ArrayList<>(sourceInfo.size());
        sources.add(importSource);//此处设置进口企业的信息
        for (int i = 1; i < sourceInfo.size() + 1; i++) {
            //此处设置分散到养殖企业的数据
            Source source = new Source();
            source.setId(i);
            for (OmComp omComp : list) {
                if (omComp.getId().equals(sourceInfo.get(i - 1).getTransferComp())) {
                    source.setCompName(omComp.getCompName() + "(总" + sourceInfo.get(i - 1).getSum() + "吨)(剩余" + sourceInfo.get(i - 1).getRemainingQty() + "吨)");
                    break;
                }
            }
            //添加进集合
            sources.add(source);
            //设置target
            Target target = new Target();
            target.setSourceId(0);//进口企业固定为0
            target.setTargetId(i);
            target.setDataType("A");//进口到养殖类型为A
            target.setSubLabel(sourceInfo.get(i - 1).getDealDate());
            target.setDealNum(sourceInfo.get(i - 1).getSum());
            targets.add(target);
        }
        sourceVo.setSources(sources);//完成关于公司的设置
        sourceVo.setTargets(targets);//完成关于关系链的设置
        return sourceVo;
    }

    //进口企业图表分析数据list
    @Override
    public List<OmHistogram> getOmHistogram(String importMan, String credential, Date startDate, Date endDate) {
        if (!SysOwnOrgUtil.getOrgInfo().getThirdOrg().equals("N")) {
            //如果不是第三方企业则需要判断是否携带企业信息
            if (StringUtils.isNotBlank(credential) && StringUtils.isBlank(importMan)) {
                throw new SofnException("需要先选择企业才能按照允许进出口说明书号进行查询");
            }
            //如果不是第三方企业则需要判断是否携带企业信息
            if (StringUtils.isBlank(importMan) && (Objects.nonNull(startDate) || Objects.nonNull(endDate))) {
                throw new SofnException("需要先选择企业才能按照日期进行查询");
            }
        }
        List<OmHistogram> omHistogram = omEelImportMapper.getOmHistogram(StringUtils.isBlank(importMan) ? UserUtil.getLoginUserId() : importMan, credential, startDate, endDate);
        return omHistogram;
    }

    //进口比例折算导出
    @Override
    public void export(Map<String, Object> map, HttpServletResponse response) {
        //首先根据当前用户类型和查询条件拿到总的列表数据
        PageUtils<OmImportFromVo> list = getListImport(map, -1, -1);
        List<OmImportFromVo> omImportFromVos = list.getList();
        //由于要将主键转换成对应的名字，为了降低访问数据库的次数，直接找到全部的企业名称
        QueryWrapper<OmComp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N);
        List<OmComp> omComps = omCompService.list(queryWrapper);
        Result<List<SysDict>> import_country = sysRegionApi.getDictListByType("import_country");
        List<SysDict> data = import_country.getData();
        //判断导出类型
        if (map.get("exportType").equals("1")) {
            //进口比例折算的导出
            ArrayList<OmImportExportBean> omImportExportBeans = new ArrayList<>(omImportFromVos.size());
            //将列表数据转换为导出数据
            for (OmImportFromVo omImportFromVo : omImportFromVos) {
                OmImportExportBean bean = new OmImportExportBean();
                BeanUtils.copyProperties(omImportFromVo, bean);
                //设置规格
                if (Objects.equals(omImportFromVo.getSize(), OmSize.WHITEEEL.getCode())) {
                    //白仔鳗
                    bean.setSize(OmSize.WHITEEEL.getVal());
                } else if (Objects.equals(omImportFromVo.getSize(), OmSize.BLACKEEL.getCode())) {
                    //黑仔鳗
                    bean.setSize(OmSize.BLACKEEL.getVal());
                }
                //主键转换成企业名称
                for (int i = 0; i < omComps.size(); i++) {
                    if (Objects.equals(bean.getImportMan(), omComps.get(i).getId())) {
                        bean.setImportMan(omComps.get(i).getCompName());
                        break;
                    }
                }
                //进口国code转name
                for (SysDict datum : data) {
                    if (Objects.equals(bean.getImportCountry(), datum.getDictcode())) {
                        bean.setImportCountry(datum.getDictname());
                        break;
                    }
                }
                omImportExportBeans.add(bean);
            }
            //根据列表数据开始导出
            ExportUtil.createExcel(OmImportExportBean.class, omImportExportBeans, response, "进口企业折算报表.xlsx");
        } else if (map.get("exportType").equals("2")) {
            //此处是进口企业汇总分析导出
            ArrayList<OmImportAllExportBean> omImportAllExportBeans = new ArrayList<>(omImportFromVos.size());
            for (OmImportFromVo omImportFromVo : omImportFromVos) {
                OmImportAllExportBean bean = new OmImportAllExportBean();
                BeanUtils.copyProperties(omImportFromVo, bean);
                //设置规格
                if (Objects.equals(omImportFromVo.getSize(), OmSize.WHITEEEL.getCode())) {
                    //白仔鳗
                    bean.setSize(OmSize.WHITEEEL.getVal());
                } else if (Objects.equals(omImportFromVo.getSize(), OmSize.BLACKEEL.getCode())) {
                    //黑仔鳗
                    bean.setSize(OmSize.BLACKEEL.getVal());
                }
                //主键转换成企业名称
                for (int i = 0; i < omComps.size(); i++) {
                    if (Objects.equals(bean.getImportMan(), omComps.get(i).getId())) {
                        bean.setImportMan(omComps.get(i).getCompName());
                        break;
                    }
                }
                //进口国code转name
                for (SysDict datum : data) {
                    if (Objects.equals(bean.getImportCountry(), datum.getDictcode())) {
                        bean.setImportCountry(datum.getDictname());
                        break;
                    }
                }
                omImportAllExportBeans.add(bean);
            }
            ExportUtil.createExcel(OmImportAllExportBean.class, omImportAllExportBeans, response, "进口企业汇总分析报表.xlsx");
        }
    }

    //列表展示
    @Override
    public PageUtils<OmImportFromVo> getListImport(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        //因为导出数据时需要用到这个方法，全额导出，排除偏移量
        if (pageNo != -1 && pageSize != -1) {
            //设置偏移量
            PageHelper.offsetPage(pageNo, pageSize);
        }
        //根据当前登录的主体对象不同来完善map
        this.perfect(params);
        List<OmImportFromVo> omImportFromVos = omEelImportMapper.getOmEelImportFromByMap(params);
        //遍历设值
        for (OmImportFromVo omImportFromVo : omImportFromVos) {
            if (Objects.equals(omImportFromVo.getSize(), OmSize.BLACKEEL.getCode())) {
                //如果是黑籽鳗折算比率*600
                omImportFromVo.setObversion(omImportFromVo.getQuantity() * OmSize.BLACKEEL.getCon());
                omImportFromVo.setRemainingQtyConvert(omImportFromVo.getRemainingQty() * OmSize.BLACKEEL.getCon());
            } else if (Objects.equals(omImportFromVo.getSize(), OmSize.WHITEEEL.getCode())) {
                //如果是白籽鳗折算比率*900
                omImportFromVo.setObversion(omImportFromVo.getQuantity() * OmSize.WHITEEEL.getCon());
                omImportFromVo.setRemainingQtyConvert(omImportFromVo.getRemainingQty() * OmSize.WHITEEEL.getCon());
            }
        }
        PageInfo<OmImportFromVo> pageInfo = new PageInfo<>(omImportFromVos);
        return PageUtils.getPageUtils(pageInfo);
//        ArrayList<OmImportFromVo> omImportVos = new ArrayList<>(omEelImportFroms.size());
//        for (OmEelImportFrom omEelImportFrom : omEelImportFroms) {
//            OmImportFromVo omImportVo = new OmImportFromVo();
//            BeanUtils.copyProperties(omEelImportFrom, omImportVo);
//            //设置折算比例与剩余吨数的折算比率
//            if (Objects.equals(omEelImportFrom.getSize(), OmSize.BLACKEEL.getCode())) {
//                //如果是黑籽鳗折算比率*600
//                omImportVo.setConvert(omImportVo.getQuantity() * OmSize.BLACKEEL.getCon());
//                omImportVo.setRemainingQtyConvert(omImportVo.getRemainingQty() * OmSize.BLACKEEL.getCon());
//            } else if (Objects.equals(omEelImportFrom.getSize(), OmSize.WHITEEEL.getCode())) {
//                //如果是白籽鳗折算比率*900
//                omImportVo.setConvert(omImportVo.getQuantity() * OmSize.WHITEEEL.getCon());
//                omImportVo.setRemainingQtyConvert(omImportVo.getRemainingQty() * OmSize.WHITEEEL.getCon());
//            }
//            omImportVos.add(omImportVo);
//        }
//        PageInfo<OmEelImportFrom> pi = new PageInfo<>(omEelImportFroms);
//        PageInfo<OmImportFromVo> pageInfo = new PageInfo<>(omImportVos);
//        pageInfo.setTotal(pi.getTotal());
//        pageInfo.setPageNum(pi.getPageNum());
//        pageInfo.setPageSize(pageSize);
//        return null;
//        return PageUtils.getPageUtils(pageInfo);
    }

    //完善map
    private void perfect(Map<String, Object> params) {
        //判断当前是第三方企业还是省级、部级用户
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        if (BoolUtils.N.equals(orgInfo.getThirdOrg())) {
            //第三方进口企业
            params.put("level", "T");
            String loginUserId = UserUtil.getLoginUserId();
            //只能看到自己企业的数据
            params.put("param", loginUserId);
        } else if (orgInfo.getOrganizationLevel().equals("ministry")) {
            //部级用户
            params.put("level", "M");
        } else if (orgInfo.getOrganizationLevel().equals("province")) {
            //省级用户
            params.put("level", "P");
            //可以看到本省的数据
            String provinceCode = omCompService.getOmComp().getProvinceCode();
            params.put("param", provinceCode);
        }
    }

    //进口欧鳗删除
    @Override
    public Integer delEelImport(String id) {
        //先拿到详情
        QueryWrapper<OmEelImportFrom> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", BoolUtils.N)
                .eq("id", id);
        OmEelImportFrom omEelImportFrom = omEelImportMapper.selectOne(wrapper);
        //判断该条数据是否已经流向养殖企业
        QueryWrapper<OmBreed> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("cell_comp", omEelImportFrom.getImportMan())
                .eq("credential", omEelImportFrom.getCredential());
        List<OmBreed> breeds = omBreedMapper.selectList(queryWrapper);
        if (breeds.size() != 0) {
            //如果在养殖企业的db中已经存在了该进口企业的数据则直接抛异常
            throw new SofnException("数据已经流向养殖企业,不可删除");
        }
        //逻辑删除
        UpdateWrapper<OmEelImportFrom> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id)
                .set("del_flag", BoolUtils.Y)
                .set("update_time", new Date())
                .set("update_user_id", UserUtil.getLoginUserId());
        return omEelImportMapper.update(null, updateWrapper);
    }

    //欧鳗进口数据更新
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateImport(OmEelImportVo omEelImportVo) {
        //判断该条数据是否已经流向养殖企业
        QueryWrapper<OmBreed> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("cell_comp", omEelImportVo.getImportMan())
                .eq("credential", omEelImportVo.getCredential());
        List<OmBreed> breeds = omBreedMapper.selectList(queryWrapper);
        if (breeds.size() != 0) {
            //如果在养殖企业的db中已经存在了该进口企业的数据则直接抛异常
            throw new SofnException("数据已经流向养殖企业,不可更改");
        }
        //判断是否携带主键
        if (StringUtils.isBlank(omEelImportVo.getId())) {
            throw new SofnException("没有携带主键");
        }
        //判断更新的进口吨数是否已经超出了允许的范围;
        OmCompVO omComp = omCompService.getOmComp();
        //得到该企业准许驯养繁殖情况鳗苗(吨）
        Double tameAllowTon = omComp.getTameAllowTon();
        //得到该企业已经进口的鳗鱼总数
        QueryWrapper<OmEelImportFrom> wrapper = new QueryWrapper<>();
        String loginUserId = UserUtil.getLoginUserId();
        wrapper.eq("province", omComp.getProvinceCode())
                .eq("create_user_id", loginUserId)
                .eq("del_flag", "N");
        List<OmEelImportFrom> omEelImportFroms = omEelImportMapper.selectList(wrapper);
        //得到该条数据之前的详情
        QueryWrapper<OmEelImportFrom> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("id", omEelImportVo.getId()).eq("del_flag", BoolUtils.N);
        OmEelImportFrom eelImportFrom = omEelImportMapper.selectOne(wrapper2);
        if (omEelImportFroms.size() != 0) {
            //得到已经进口的剩余总吨数
            Double reduce = omEelImportFroms.stream().map(a -> a.getRemainingQty()).reduce(0.0, (a, b) -> a + b);
            //判断是否超出
            if ((reduce - eelImportFrom.getRemainingQty() + omEelImportVo.getQuantity()) > tameAllowTon) {
                throw new SofnException("更新失败!超过了准许驯养繁殖鳗苗吨数。准许驯养:"
                        + tameAllowTon + "吨。当次更新超出准许驯养繁殖吨数:"
                        + (reduce - eelImportFrom.getRemainingQty() + omEelImportVo.getQuantity() - tameAllowTon) + "吨。");
            }
        }
        //创建实体
        OmEelImportFrom model = new OmEelImportFrom();
        //复制属性
        BeanUtils.copyProperties(omEelImportVo, model);
        //如果更新不会超出，那么剩余数量需要在原来的基础上增加
        model.setRemainingQty(eelImportFrom.getRemainingQty() + (omEelImportVo.getQuantity() - eelImportFrom.getQuantity()));
        //更新前的操作
        model.preUpdate();
        //执行更新
        int i = omEelImportMapper.updateById(model);
        if (i != 1) {
            throw new SofnException("更新失败");
        }
        //更新文件
        omFileService.update(omEelImportVo.getId(), omEelImportVo.getFiles());
    }
}
