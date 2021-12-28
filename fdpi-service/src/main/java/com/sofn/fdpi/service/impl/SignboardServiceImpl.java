package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.enums.CommonEnum;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.*;
import com.sofn.fdpi.mapper.SignboardMapper;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysFileManageForm;
import com.sofn.fdpi.util.CodeUtil;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 标识服务类
 *
 * @Author yumao
 * @Date 2019/12/27 17:30
 **/
@Service(value = "signboardService")
@Slf4j
public class SignboardServiceImpl extends BaseService<SignboardMapper, Signboard> implements SignboardService {

    @Resource
    private SignboardMapper signboardMapper;

    @Resource
    @Lazy
    private SignboardApplyService signboardApplyService;

    @Resource
    private FileManageService fileManageService;

    @Resource
    private SysRegionApi sysRegionApi;

    @Resource
    private CompSpeStockService cpsService;

    @Resource
    private SpeService speService;

    @Resource
    private SqlSessionFactory sessionFactory;

    @Resource
    private PapersYearInspectService papersYearInspectService;

    @Resource
    private SignboardPrintService signboardPrintService;

    @Resource
    @Lazy
    private TbCompService tbCompService;

    @Resource
    private SignboardSaveBatchService signboardSaveBatchService;

    /**
     * 1 生成只带ID, CODE, CREATE_TIME三个字段的标识数据
     * 2 根据创建时间补全标识其它字段
     * 3 把每条配发数据记录到变更记录表
     * 4 把每条酸发数据记录到申请记录表
     */
    @Override
    @Transactional
    public void insertSignboardBatch(SignboardApply signboardApply, String createUserId) {
        int applyNum = signboardApply.getApplyNum();
        Date now = new Date();
        Long crtTime = System.currentTimeMillis();
        List<Signboard> signboardList = Lists.newArrayListWithCapacity(applyNum);
        String year = DateUtils.format(now, "yyyy").substring(2);
        //物种信息
        Spe spe = speService.getSpeBySpeId(signboardApply.getSpeId());
        String speCode = spe.getSpeCode();
        //企业信息
        TbCompVo tbCompVo = tbCompService.getCombById(signboardApply.getCompId());
        String compCode = tbCompVo.getCompCode();
        String provinceCode = CodeUtil.getProvinceCode(tbCompVo.getRegionInCh().split("-")[0]);
        // 获取制品/活体 还是鲟鱼子酱代码
        String speType = this.getSpeType(speCode, signboardApply.getType(), signboardApply.getCitesCode());
        String printId = IdUtil.getUUId();
        // 海洋馆不加损耗，养殖加式加10%损耗
        int printNum = speType.equals("002") ? applyNum :
                new BigDecimal(applyNum).multiply(new BigDecimal(1.1)).intValue();
        String codeStart = this.getCodeStart(speCode, provinceCode, year, compCode, speType);
        List<String> codes = this.generateCodes(this.getOldCodes(codeStart), printNum, codeStart);
        for (int i = 0; i < printNum; i++) {
            signboardList.add(this.generateSignboard(createUserId, printId, codeStart, codes.get(i),
                    signboardApply, now, crtTime));
        }
        signboardSaveBatchService.saveBatch(signboardList, printNum > 10000 ? 1000 : 500);
        this.handleLast(signboardApply.getId(), signboardApply.getCompId(), signboardApply.getType(), applyNum, crtTime,
                printId, speCode, compCode, provinceCode, year, createUserId);
    }

    private Set<String> getOldCodes(String codeStart) {
        //先查redis是否存在数据，没有再从数据库取
        Object obj = RedisUserUtil.hget(Constants.FDPI_INSERT_SIGNBOARD, codeStart);
        return Objects.isNull(obj) ? this.getExistsCodes(codeStart).stream().collect(Collectors.toSet()) :
                JsonUtils.json2List(obj.toString(), String.class).stream().collect(Collectors.toSet());
    }

    private String getCodeStart(String speCode, String provinceCode, String year, String compCode, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append(speCode).append(provinceCode);
        if (StringUtils.hasText(year)) {
            sb.append(year);
        } else {
            sb.append(DateUtils.format(new Date(), "yyyyMMdd").substring(2));
        }
        sb.append(compCode).append(type);
        return sb.toString();
    }

    private List<String> generateCodes(Set<String> oldCodes, Integer printNum, String codeStart) {
        Integer allSize = oldCodes.size() + printNum;
        Set<String> allCodes = new HashSet<>(oldCodes);
        while (allCodes.size() < allSize) {
            allCodes.add(IdUtil.getNumCode(8));
        }
        //保存到redis,方便下次查询
        Integer time = 60 * 60 * 24; //保存时间
        RedisUserUtil.hset(Constants.FDPI_INSERT_SIGNBOARD, codeStart, JsonUtils.obj2json(allCodes), Long.valueOf(time));
        allCodes.removeAll(oldCodes);
        return allCodes.stream().collect(Collectors.toList());
    }

    private List<String> getExistsCodes(String codeStart) {
        QueryWrapper<Signboard> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("code", codeStart);
        queryWrapper.select("substring(code,16) as code");
        return signboardMapper.selectList(queryWrapper).stream().map(Signboard::getCode).collect(Collectors.toList());
    }

    /**
     * 生成标识后 记录变更记录，申请列表 并 新增至打印企业
     */
    private void handleLast(
            String applyId, String compId, String type, Integer applyNum, Long crtTime, String printId,
            String speCode, String compCode, String provinceCode, String year, String createUserId) {
        //变更记录
        signboardMapper.recordAllotment(crtTime);
        //申请列表
        signboardMapper.recordApplyList(applyId, crtTime);
        SignboardPrintForm signboardPrintForm = new SignboardPrintForm();
        signboardPrintForm.setId(printId);
        signboardPrintForm.setType(type);
        signboardPrintForm.setCompId(compId);
        signboardPrintForm.setNum(applyNum);
        signboardPrintForm.setSpeCode(speCode);
        signboardPrintForm.setProvinceCode(provinceCode);
        signboardPrintForm.setCompCode(compCode);
        signboardPrintForm.setApplyId(applyId);
//        type = StringUtils.hasText(type) ? type.substring(0, 2) : "00";
        signboardPrintForm.setCodeStart(speCode + provinceCode + year + compCode + type);
        signboardPrintService.insertSignboardPrint(signboardPrintForm, createUserId);
    }

    //验证编码是否重复
    private void validCode(String code) {
        QueryWrapper<Signboard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).eq("code", code);
        if (signboardMapper.selectCount(queryWrapper) > 0) {
            throw new SofnException("编码（" + code + "）已存在，请重新输入");
        }
    }

    private String getSpeType(String speCode, String type, String citesCode) {
        if ("0000".equals(speCode)) {
            switch (citesCode) {
                case "STE":
                    type = "01";
                    break;
                case "HUS":
                    type = "02";
                    break;
                case "DAU":
                    type = "03";
                    break;
                case "GUE":
                    type = "04";
                    break;
                case "SCH":
                    type = "05";
                    break;
                case "BAE":
                    type = "06";
                    break;
                case "GUE*BAE":
                    type = "07";
                    break;
                case "SCH*DAU":
                    type = "08";
                    break;
            }
        }
        return type;
    }

    private Signboard generateSignboard(String createUserId, String printId, String codeStart, String codeEnd,
                                        SignboardApply signboardApply, Date generatDate, Long crtTime) {
        Signboard signboard = new Signboard();
        signboard.setId(IdUtil.getUUId());
        signboard.setSpeId(signboardApply.getSpeId());
        signboard.setApplyId(signboardApply.getId());
        signboard.setCompId(signboardApply.getCompId());
        signboard.setSpeSource(signboardApply.getSpeSource());
        signboard.setSpeUtilizeType(signboardApply.getSpeUtilizeType());
        signboard.setSaleProvince(signboardApply.getSaleProvince());
        signboard.setAndriasContent(signboardApply.getAndriasContent());
        signboard.setSignboardType(signboardApply.getType());
        signboard.setApplyNum(signboardApply.getApplyNum());
        signboard.setIntroduce(signboardApply.getIntroduction());
        signboard.setCode(codeStart + codeEnd);
        signboard.setStatus(SignboardStatusEnum.UN_ACTIVATE.getKey());
        signboard.setPrintId(printId);
        signboard.setCreateTime(generatDate);
        signboard.setCreateUserId(createUserId);
        signboard.setCrtTime(crtTime);
        signboard.setIsPedigree(IsEnum.IS_N.getKey());
        signboard.setPrintStatus(PrintStatusEnum.NOT_PRINTED.getKey());
        signboard.setDelFlag(BoolUtils.Y);
        return signboard;
    }

    /**
     * 换发标识
     */
    @Override
    public Signboard changeSignboard(SignboardApplyListVo vo) {
        Signboard entity = signboardMapper.selectById(vo.getSignboardId());
        entity.preUpdate();
        String signboardApplyId = vo.getApplyId();
        SignboardApplyVo signboardApplyVo = signboardApplyService.getSignboardApply(signboardApplyId);
        //物种信息
        Spe spe = speService.getSpeBySpeId(entity.getSpeId());
        String speCode = spe.getSpeCode();
        //企业信息
        TbCompVo tbCompVo = tbCompService.getCombById(entity.getCompId());
        String provinceCode = CodeUtil.getProvinceCode(tbCompVo.getRegionInCh().split("-")[0]);
        String speType = this.getSpeType(speCode, signboardApplyVo.getType(), signboardApplyVo.getCitesCode());
        String year = DateUtils.format(new Date(), "yyyy").substring(2);
        String codeStart = this.getCodeStart(speCode, provinceCode, year, tbCompVo.getCompCode(), speType);
        entity.setCode(codeStart + this.generateCodes(this.getOldCodes(codeStart), 1, codeStart).get(0));
        entity.setPrintStatus(PrintStatusEnum.NOT_PRINTED.getKey());
        signboardMapper.updateById(entity);
        return entity;
    }

    /**
     * 补发标识
     */
    @Override
    public Signboard reIssue(String signboardId) {
        Signboard entity = signboardMapper.selectById(signboardId);
        entity.preUpdate();
        entity.setPrintStatus(PrintStatusEnum.NOT_PRINTED.getKey());
        signboardMapper.updateById(entity);
        return entity;
    }

    @Override
    public SignboardVo getSignboard(String id) {
        Signboard signboard = signboardMapper.getSignboard(id);
        if (Objects.isNull(signboard)) {
            return null;
        } else {
            if (CommonEnum.DEL_FLAG_Y.getCode().equals(signboard.getDelFlag())) {
                return null;
            }
            SignboardVo vo = SignboardVo.Signboard2Vo(signboard);
            vo.setFiles(fileManageService.listBySourceId(id));
            return vo;
        }
    }

    @Override
    public SignboardVo getSignboardByCode(String code, String type) {
        SignboardVo signboardVo = new SignboardVo();
        List<Signboard> signboards = signboardMapper.listSignboardByCode(code);
        if (!CollectionUtils.isEmpty(signboards)) {
            Signboard signboard = signboards.get(0);
            type = StringUtils.hasText(type) ? type : "1";
            String delFlag = signboard.getDelFlag();
            if ("1".equals(type) && BoolUtils.Y.equals(delFlag)) {
                String compId = signboard.getCompId();
                TbCompVo tcv = tbCompService.getCombById(compId);
                signboardVo = SignboardVo.Signboard2Vo(signboard);
                signboardVo.setCompType(tcv.getCompType());
                signboardVo.setFiles(fileManageService.listBySourceId(signboard.getId()));
            } else {
                String status = signboard.getStatus();
                if (SignboardStatusEnum.FEED.getKey().equals(status) ||
                        SignboardStatusEnum.SALE.getKey().equals(status) ||
                        SignboardStatusEnum.DEPOSIT.getKey().equals(status)) {
                    String compId = signboard.getCompId();
                    TbCompVo tcv = tbCompService.getCombById(compId);
                    signboardVo = SignboardVo.Signboard2Vo(signboard);
                    signboardVo.setCompType(tcv.getCompType());
                    signboardVo.setFiles(fileManageService.listBySourceId(signboard.getId()));
                }
            }
        }
        if ("2".equals(signboardVo.getCompType()) && "1".equals(type)) {
            //如果是加工企业的并且在扫码页面，重量kg需要换算成g
            signboardVo.setWeight(signboardVo.getWeight() * 1000);
        }
        return signboardVo;
    }

    /**
     * 更新标识
     */
    @Override
    @Transactional
    public Signboard updateSignboard(SignboardForm form) {
        Signboard entity = signboardMapper.selectById(form.getId());
        if (Objects.isNull(entity)) {
            throw new SofnException("待修改数据不存在!");
        }
        BeanUtils.copyProperties(form, entity, "code", "speId", "compId", "speSource");
        entity.preUpdate();
        entity.setIsPedigree(IsEnum.IS_Y.getKey());
        signboardMapper.updateById(entity);
        //更新文件信息
        this.updateFiles(form);
        return entity;
    }

    /**
     * 更新文件信息
     */
    private void updateFiles(SignboardForm form) {
        String id = form.getId();
        //先根据源ID查出所有文件,并获取文件ID列表
        List<FileManageVo> fmvList = fileManageService.listBySourceId(id);
        List<String> oldFileIds = null;
        if (!CollectionUtils.isEmpty(fmvList)) {
            oldFileIds = new ArrayList<>(fmvList.size());
            for (FileManageVo vo : fmvList) {
                oldFileIds.add(vo.getId());
            }
        }

        List<FileManageForm> files = form.getFiles();
        if (!CollectionUtils.isEmpty(files)) {
            StringBuilder ids = new StringBuilder();
            for (FileManageForm fileManageForm : files) {
                String fileId = fileManageForm.getId();
                if (CollectionUtils.isEmpty(oldFileIds)) {
                    ids.append("," + fileId);
                    fileManageService.insertFileMange(fileManageForm, "", id);
                } else {
                    if (oldFileIds.contains(fileId)) {
                        oldFileIds.remove(fileId);
                    } else {
                        ids.append("," + fileId);
                        fileManageService.insertFileMange(fileManageForm, "", id);
                    }
                }
            }
            //激活系统文件
            if (ids.length() > 0) {
                SysFileManageForm sfmf = new SysFileManageForm();
                sfmf.setIds(ids.substring(1));
                sfmf.setInterfaceNum("hidden");
                sfmf.setSystemId(Constants.SYSTEM_ID);
                try {
                    Result<List<SysFileVo>> result = sysRegionApi.activationFile(sfmf);
                    if (!Result.CODE.equals(result.getCode())) {
                        throw new SofnException("系统激活文件失败!");
                    }
                } catch (Exception e) {
                    throw new SofnException("支撑平台连接失败!");
                }
            }
        }

        if (!CollectionUtils.isEmpty(oldFileIds)) {
            // 本系统文件删除
            fileManageService.deleteBatchIds(oldFileIds);
            //支撑平台文件删除
            StringBuilder sysDelIds = new StringBuilder();
            for (String sysDelId : oldFileIds) {
                sysDelIds.append("," + sysDelId);
            }
            try {
                Result<String> result = sysRegionApi.batchDeleteFile(sysDelIds.substring(1));
                if (!Result.CODE.equals(result.getCode())) {
                    throw new SofnException("支撑平台文件删除文件失败!");
                }
            } catch (Exception e) {
                throw new SofnException("支撑平台连接失败!");
            }
        }
    }

    @Override
    public PageUtils<SignboardVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        this.perfectParams(params);
        Long total = signboardMapper.countTotal(params);
        if (total.equals(0)) {
            PageInfo<SignboardVo> nullPage = new PageInfo<>(Collections.EMPTY_LIST);
            nullPage.setPageSize(pageSize);
            nullPage.setPageNum(0);
            return PageUtils.getPageUtils(nullPage);
        } else {
            params.put("pageNo", pageNo);
            params.put("pageSize", pageNo + pageSize);

            List<Signboard> list = signboardMapper.listPage(params);
            List<SignboardVo> listVo = new ArrayList<>(list.size());
            for (Signboard o : list) {
                listVo.add(SignboardVo.Signboard2Vo(o));
            }
            PageInfo<SignboardVo> svPageInfo = new PageInfo<>(listVo);
            svPageInfo.setTotal(total);
            svPageInfo.setPageSize(pageSize);
            svPageInfo.setPageNum(pageNo / pageSize + 1);
            return PageUtils.getPageUtils(svPageInfo);
        }
    }

    @Override
    public Long listPageTotalCount(Map<String, Object> params) {
        this.perfectParams(params);
        return signboardMapper.countTotal(params);
    }

    @Override
    public List<SignboardVo> listPageData(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        this.perfectParams(params);
        params.put("pageSize", pageNo + pageSize);
        params.put("pageNo", pageNo);
        List<Signboard> list = signboardMapper.listPage(params);
        List<SignboardVo> listVo = new ArrayList<>(list.size());
        for (Signboard o : list) {
            listVo.add(SignboardVo.Signboard2Vo(o));
        }
        return listVo;
    }

    /**
     * 完善权限查询参数
     */
    private void perfectParams(Map<String, Object> params) {
        String orgInfoJosn = UserUtil.getLoginUserOrganizationInfo();
        if (StringUtils.isEmpty(orgInfoJosn)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        OrganizationInfo orgInfo = JsonUtils.json2obj(orgInfoJosn, OrganizationInfo.class);
        //验证用户机构配置是否满足要求
        RedisUserUtil.validLoginUser(orgInfo);
        String regionLastCode = orgInfo.getRegionLastCode();
        Object code = params.get("code");
        String completeCode = null;
        if (!Objects.isNull(code)) {
            String codeStr = String.valueOf(code);
            if (codeStr.length() == 23) {
                completeCode = codeStr;
            }
        }
        if (StringUtils.hasText(completeCode)) {
            params.clear();
            params.put("delFlag", BoolUtils.N);
            params.put("completeCode", completeCode);
        } else {
            //第三方机构
            if (BoolUtils.N.equals(orgInfo.getThirdOrg())) {
                List<String> roles = UserUtil.getLoginUserRoleCodeList();
                if (!CollectionUtils.isEmpty(roles) && !roles.contains(Constants.PRINT_USER_ROLE_CODE)) {
                    params.put("compId", orgInfo.getId());
                }
            }
            //非第三机构(行政机构)
            else {
                int length = regionLastCode.length();
                //执法机构
                if (Constants.ORG_ZHIFA_FUNC_CODE.equals(orgInfo.getOrgFunction())) {
                    if (length >= 2) {
                        params.put("province", regionLastCode.substring(0, 2) + "0000");
                    }
                }
                //非执法机构
                else {
                    String organizationLevel = orgInfo.getOrganizationLevel();
                    params.put("organizationLevel", organizationLevel);

                    //当前机构用户级别是区县,查询参数需要增加省 市 区
                    if (Constants.REGION_TYPE_COUNTY.equals(organizationLevel)) {
                        if (length >= 6) {
                            params.put("province", regionLastCode.substring(0, 2) + "0000");
                            params.put("city", regionLastCode.substring(0, 4) + "00");
                            params.put("district", regionLastCode.substring(0, 6));
                        }
                    }
                    //当前机构用户级别是市,查询参数需要增加省 市
                    else if (Constants.REGION_TYPE_CITY.equals(organizationLevel)) {
                        if (length >= 4) {
                            params.put("province", regionLastCode.substring(0, 2) + "0000");
                            params.put("city", regionLastCode.substring(0, 4) + "00");
                        }
                    }
                    //机构用户级别是省,查询参数需要增加省
                    else if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)) {
                        if (length >= 2) {
                            params.put("province", regionLastCode.substring(0, 2) + "0000");
                        }
                    }
                    //机构用户级别是部,不需要自动增加区域参数
                    else if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel)) {
                    }
                }
            }
        }
    }

    @Override
    public Signboard updateStatus(String id, String status) {
        Signboard s = signboardMapper.selectById(id);
        s.preUpdate();
        s.setStatus(status);
        signboardMapper.updateById(s);
        return s;
    }

    @Override
    public Signboard updatePrintStatus(String id, String printStatus) {
        Signboard s = signboardMapper.selectById(id);
        s.preUpdate();
        s.setPrintStatus(printStatus);
        signboardMapper.updateById(s);
        return s;
    }

    @Override
    public Integer updatePrintStatusBatch(List<String> ids, String printStatus) {
        UpdateWrapper<Signboard> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("ID", ids);
        Signboard signboard = new Signboard();
        signboard.setPrintStatus(printStatus);
        signboard.preUpdate();
        return signboardMapper.update(signboard, updateWrapper);
    }

    @Override
    public void activate(String id, String status) {
        if (!SignboardStatusEnum.FEED.getKey().equals(status) && !SignboardStatusEnum.SALE.getKey().equals(status)) {
            throw new SofnException("激活标识只能是在养或者销售");
        }
        Signboard entity = signboardMapper.selectById(id);
        if (Objects.isNull(entity)) {
            throw new SofnException("待激活标识不存在,请核对标识ID");
        }
        //销售状态不能改变成在养状态
        if (SignboardStatusEnum.FEED.getKey().equals(status) &&
                SignboardStatusEnum.SALE.getKey().equals(entity.getStatus())) {
            throw new SofnException("标识编码(" + entity.getCode() + ")为销售状态，不能激活为在养");
        }
        //物种转移中的标识，不能操作
        if (BoolUtils.Y.equals(entity.getTransferStatus())) {
            throw new SofnException("标识编码(" + entity.getCode() + ")正在转移操作,不能激活");
        }
        //已注销标识,不能激活
        if (SignboardStatusEnum.CANCELLATION.getKey().equals(entity.getStatus())) {
            throw new SofnException("标识编码(" + entity.getCode() + ")已注销,不能激活");
        }
        //谱系信息是否完善
        String isPedigree = entity.getIsPedigree();
        //是否进行谱系
        String pedigree = IsEnum.IS_N.getKey();
        Spe spe = speService.getSpeBySpeId(entity.getSpeId());
        if (Objects.nonNull(spe)) {
            pedigree = spe.getPedigree();
        }
        if (IsEnum.IS_Y.getKey().equals(pedigree) && IsEnum.IS_N.getKey().equals(isPedigree)) {
            throw new SofnException("标识编码(" + entity.getCode() + ")谱系信息未完善,不能激活");
        }
        entity.setStatus(status);
        signboardMapper.updateById(entity);

    }

    @Override
    @Transactional
    public void activateBatch(SignActivForm signActivForm) {
        String compType = tbCompService.getCombById(UserUtil.getLoginUserOrganizationId()).getCompType();
        List<String> idList = signActivForm.getIds();
        // 企业类型 1海洋馆 2养殖加工场 3无证书备案
        if ("1".equals(compType)) {
            SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
            for (String id : idList) {
                this.activate(id, SignboardStatusEnum.FEED.getKey());
            }
            session.commit();
            session.close();
        } else if ("2".equals(compType) || "3".equals(compType)) {
            int idListSize = idList.size();
            List<String> oldCodes =
                    this.listOldCodes(idListSize, signActivForm.getOldCodeS(), signActivForm.getOldCodeE());
            Boolean hadOldCodes = !CollectionUtils.isEmpty(oldCodes);
            for (int i = 0; i < idListSize; i++) {
                SignboardForm sf = signActivForm.getSignboardForm();
                UpdateWrapper<Signboard> uw = new UpdateWrapper<>();
                //养殖加工企业默认激活为销售
                uw.set("status", SignboardStatusEnum.SALE.getKey());
                uw.set("iden_person", sf.getIdenPerson());
                uw.set("iden_time", sf.getIdenTime());
                uw.set(Objects.nonNull(sf.getWeight()), "weight", sf.getWeight());
                uw.set(Objects.nonNull(sf.getSignProduct()), "sign_product", sf.getSignProduct());
                uw.set(Objects.nonNull(sf.getProduct()), "product", sf.getProduct());
                uw.set(Objects.nonNull(sf.getSpeSource()), "spe_source", sf.getSpeSource());
                uw.set(Objects.nonNull(sf.getAndriasContent()), "andrias_content", sf.getAndriasContent());
                uw.set(Objects.nonNull(sf.getApplyNum()), "apply_num", sf.getApplyNum());
                uw.set(Objects.nonNull(sf.getSaleProvince()), "sale_province", sf.getSaleProvince());
                uw.set(Objects.nonNull(sf.getBatchNumber()), "batch_number", sf.getBatchNumber());
                uw.set(Objects.nonNull(sf.getEffective()), "effective", sf.getEffective());
//                uw.set(Objects.nonNull(sf.getIntroduce()), "introduce", sf.getIntroduce());
                if (hadOldCodes) {
                    uw.set("old_code", oldCodes.get(i));
                }
                uw.eq("id", idList.get(i));
                signboardMapper.update(null, uw);
            }


        }
    }

    private List<String> listOldCodes(Integer idListSize, String oldCodeS, String oldCodeE) {
        if (StringUtils.isEmpty(oldCodeE) && StringUtils.isEmpty(oldCodeS)) {
            return Collections.EMPTY_LIST;
        }
        if (StringUtils.isEmpty(oldCodeS) || StringUtils.isEmpty(oldCodeE)) {
            throw new SofnException("旧标识编码起止号码必须同时存在！");
        }
        if (oldCodeS.length() != oldCodeE.length()) {
            throw new SofnException("旧标识编码起止号码长度必须相同！");
        }
        if (oldCodeS.length() < 10) {
            throw new SofnException("旧标识编码太短！");
        }
        String codeStart = oldCodeS.substring(0, oldCodeS.length() - 6);
        if (!codeStart.equals(oldCodeE.substring(0, oldCodeS.length() - 6))) {
            throw new SofnException("旧标识编码起止号码必须满足相同规则！");
        }
        Integer startSerial = Integer.parseInt(oldCodeS.substring(oldCodeS.length() - 6));
        Integer size = Integer.parseInt(oldCodeE.substring(oldCodeS.length() - 6)) - startSerial + 1;
        if (!idListSize.equals(size)) {
            throw new SofnException("所选标识数量(" + idListSize + ")和旧标识数量(" + size + ")不相等，请检查！");
        }
        List<String> codes = Lists.newArrayListWithCapacity(size);
        for (int i = 0; i < size; i++) {
            codes.add(codeStart + String.format("%06d", startSerial++));
        }
        return codes;
    }

    @Override
    public List<SignboardApplyListVo> getListInfo(String compId, String speId, String queryType) {
        Map<String, Object> map = new HashMap<>(4);
        if (!StringUtils.hasText(compId)) {
            compId = UserUtil.getLoginUserOrganizationId();
        } else {
//            在转移中
            map.put("withInTransfer", BoolUtils.Y);
        }
        map.put("compId", compId);
        map.put("delFlag", BoolUtils.N);
        map.put("withoutSignboardStatus", BoolUtils.Y);
        if (StringUtils.hasText(speId)) {
            map.put("speId", speId);
        }
        if (queryType != null && !queryType.trim().equals("")) {
            map.put("transferStatus", BoolUtils.Y);
        }
        List<Signboard> list = signboardMapper.listByParams(map);
        if (!CollectionUtils.isEmpty(list)) {
            List<SignboardApplyListVo> res = new ArrayList<>(list.size());
            for (Signboard signboard : list) {
                SignboardApplyListVo vo = new SignboardApplyListVo();
                String status = signboard.getStatus();
                vo.setId(signboard.getId());
                vo.setStatusName(SignboardStatusEnum.getVal(status));
                vo.setStatus(status);
                vo.setSignboardId(signboard.getId());
                vo.setCode(signboard.getCode());
                vo.setSpeName(signboard.getSpeName());
                vo.setCompName(signboard.getCompName());
                vo.setSpeId(signboard.getSpeId());
                vo.setTransferStatus(signboard.getTransferStatus());
                res.add(vo);
            }
            return res;
        }
        return null;
    }

    @Override
    public PageUtils<SignboardApplyListVo> getListInfoPage(String compId, String speId, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String, Object> params = new HashMap<>(4);
        if (!StringUtils.hasText(compId)) {
            compId = UserUtil.getLoginUserOrganizationId();
        } else {
            params.put("withInTransfer", BoolUtils.Y);
        }
        params.put("delFlag", BoolUtils.N);
        params.put("compId", compId);
        params.put("withoutSignboardStatus", BoolUtils.Y);
        if (StringUtils.hasText(speId)) {
            params.put("speId", speId);
        }
        List<Signboard> list = signboardMapper.listByParams(params);
        PageInfo<Signboard> sPageInfo = new PageInfo<>(list);

        List<SignboardApplyListVo> listVo = new ArrayList<>(list.size());
        if (!CollectionUtils.isEmpty(list)) {
            for (Signboard o : list) {
                SignboardApplyListVo vo = new SignboardApplyListVo();
                String status = o.getStatus();
                vo.setId(o.getId());
                vo.setStatusName(SignboardStatusEnum.getVal(status));
                vo.setSignboardId(o.getId());
                vo.setCode(o.getCode());
                vo.setSpeName(o.getSpeName());
                vo.setCompName(o.getCompName());
                vo.setStatus(status);
                vo.setSpeId(o.getSpeId());
                listVo.add(vo);
            }
            PageInfo<SignboardApplyListVo> svPageInfo = new PageInfo<>(listVo);
            svPageInfo.setTotal(sPageInfo.getTotal());
            svPageInfo.setPageSize(pageSize);
            svPageInfo.setPageNum(sPageInfo.getPageNum());
            return PageUtils.getPageUtils(svPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    @Override
    public Map<String, List<SelectVo>> getSpeSourceAndUtilizeType(String speId, String compId) {
        Map<String, List<SelectVo>> res = new HashMap<>(2);
        List<String> speSourceList = signboardMapper.listSpeSourceDistinct(speId, compId);
        if (!CollectionUtils.isEmpty(speSourceList)) {
            List<SelectVo> list = new ArrayList<>(speSourceList.size());
            speSourceList.forEach(key -> {
                SelectVo vo = new SelectVo();
                vo.setKey(key);
                vo.setVal(SpeciesSourceEnum.getVal(key));
                list.add(vo);
            });
            res.put("speciesSource", list);
        }

        List<String> utilizeTypeList = signboardMapper.listUtilizeTypeDistinct(speId, compId);
        if (!CollectionUtils.isEmpty(utilizeTypeList)) {
            List<SelectVo> list = new ArrayList<>(utilizeTypeList.size());
            utilizeTypeList.forEach(key -> {
                SelectVo vo = new SelectVo();
                vo.setKey(key);
                vo.setVal(SpeciesUtilizeTypeEnum.getVal(key));
                list.add(vo);
            });
            res.put("speciesUtilizeType", list);
        }
        return res;
    }

    @Override
    public PageUtils<SignboardVoForInspect> listPageForSignboard(String compId, String inspectId, String speciesId, String signboardCode, String signboardType, Integer pageNo, Integer pageSize) {
        Date dateNow = new Date();
        Map<String, Object> signMap = Maps.newHashMap();
        List<SignboardVoForInspect> signList;
        if (org.apache.commons.lang.StringUtils.isBlank(inspectId)) {
            //新增页面获取数据
            //获取查询标识条件Map
            signMap.put("compId", StringUtils.isEmpty(compId) ? UserUtil.getLoginUserOrganizationId() : compId);
            signMap.put("speciesId", speciesId);
            signMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateNow));
            signMap.put("withOutStatus", "3");
            signMap.put("signboardCode", signboardCode);
            signMap.put("signboardType", signboardType);
            PageHelper.offsetPage(pageNo, pageSize);
            signList = signboardMapper.listForSignboard(signMap);
        } else {
            //编辑or详情
            QueryWrapper<PapersYearInspect> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ID", inspectId);
            queryWrapper.eq("DEL_FLAG", "N");
            PapersYearInspect PapersYearInspect = papersYearInspectService.getOne(queryWrapper);
            //获取查询条件Map
            signMap.put("compId", PapersYearInspect.getTbCompId());
            signMap.put("speciesId", speciesId);
            signMap.put("yearInspectId", inspectId);
            signMap.put("signboardCode", signboardCode);
            signMap.put("signboardType", signboardType);
            PageHelper.offsetPage(pageNo, pageSize);
            signList = signboardMapper.listForSignboardInspectHistory(signMap);
        }
        PageInfo<SignboardVoForInspect> pageInfo = new PageInfo<>(signList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public Map<String, Integer> getStockAndAllotted(String speId, String compId, String signboardType) {
        if (StringUtils.isEmpty(compId)) {
            compId = UserUtil.getLoginUserOrganizationId();
        }
        Map<String, Integer> res = new HashMap<>(2);
        CompSpeStock css = cpsService.getBySpeIdAndCompId(speId, compId);
        res.put("stock", css == null ? 0 : css.getSpeNum());

        QueryWrapper<Signboard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DEL_FLAG", "N").eq("SPE_ID", speId)
                .eq("COMP_ID", compId).eq("signboard_type", signboardType);
        res.put("allotted", signboardMapper.selectCount(queryWrapper));

        res.put("allotting", signboardApplyService.getApplyNum(compId, speId, signboardType));
        return res;
    }

    @Override
    public List<String> listSignboardCode(String printId) {
        QueryWrapper<Signboard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("print_id", printId);
        queryWrapper.select("code");
        List<Signboard> list = signboardMapper.selectList(queryWrapper);
        return list.stream().map(Signboard::getCode).collect(Collectors.toList());
    }

    @Override
    public void updateChip(String id, String chipAbroad, String chipDomestic) {
        if (StringUtils.isEmpty(chipAbroad) && StringUtils.isEmpty(chipDomestic)) {
            throw new SofnException("至少填写一样国内/外芯片编码");
        }
        UpdateWrapper<Signboard> uw = new UpdateWrapper<>();
        uw.eq("id", id);
        uw.set(StringUtils.hasText(chipAbroad), "chip_abroad", chipAbroad);
        uw.set(StringUtils.hasText(chipDomestic), "chip_domestic", chipDomestic);
        signboardMapper.update(null, uw);
    }

    @Override
    public List<Signboard> listByPrintId(String printId) {
        QueryWrapper<Signboard> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("code").eq("PRINT_ID", printId);
        return signboardMapper.selectList(queryWrapper);
    }

    @Override
    public List<String> delByCodes(List<String> codes) {
        if (!CollectionUtils.isEmpty(codes)) {
            QueryWrapper<Signboard> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("code", codes).select("id");
            List<Signboard> signboards = signboardMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(signboards)) {
                List<String> ids = signboards.stream().map(Signboard::getId).collect(Collectors.toList());
                signboardMapper.deleteBatchIds(ids);
                return ids;
            }
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void updateDelFlagByPringId(String pringId, String delFlag) {
        UpdateWrapper<Signboard> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("del_flag", delFlag).eq("print_id", pringId);
        signboardMapper.update(null, updateWrapper);
    }

    @Override
    public void updatePrintStatusByPringId(String pringId, String printStatus) {
        UpdateWrapper<Signboard> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("print_status", printStatus).eq("print_id", pringId);
        signboardMapper.update(null, updateWrapper);
    }

    @Override
    public int delByCompId(String compId) {
        QueryWrapper<Signboard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return signboardMapper.delete(queryWrapper);
    }


}
