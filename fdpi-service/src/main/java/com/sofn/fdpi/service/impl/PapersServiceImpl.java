package com.sofn.fdpi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.email.EmailService;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.*;
import com.sofn.fdpi.mapper.AuditProcessMapper;
import com.sofn.fdpi.mapper.CompSpeStockFlowMapper;
import com.sofn.fdpi.mapper.CompSpeStockMapper;
import com.sofn.fdpi.mapper.PapersMapper;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.*;
import com.sofn.fdpi.sysapi.bean.SysThirdOrgForm;
import com.sofn.fdpi.util.*;
import com.sofn.fdpi.vo.*;
import com.sofn.fdpi.vo.SysRegionInfoVo;
import com.sofn.fdpi.vo.SysUserForm;
import com.sofn.fdpi.vo.exportBean.ExporrtPapers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("papersService")
public class PapersServiceImpl extends ServiceImpl<PapersMapper, Papers> implements PapersService {

    @Resource
    private PapersMapper papersMapper;

    @Resource
    private SpeService speService;

    @Resource
    private SysRegionApi sysRegionApi;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Resource
    private AuditProcessService auditProcessService;

    @Resource
    private AuditProcessMapper auditProcessMapper;

    @Resource
    private TbDepartmentService tbDepartmentService;


    @Resource
    @Lazy
    private TbCompService tbCompService;

    @Resource
    private TbUsersService tbUsersService;
    @Resource
    FileManageService fileManageService;

    @Resource
    CompSpeStockMapper compSpeStockMapper;

    @Resource
    CompSpeStockFlowMapper compSpeStockFlowMapper;

    @Resource
    PapersSpecService papersSpecService;

    @Resource
    RegionService regionService;

    @Resource
    private com.sofn.fdpi.service.CaptureService CaptureService;

    @Resource
    private RedisHelper redisHelper;

    @Resource
    private WorkService workService;

    @Autowired(required = false)
    private EmailService emailService;

    //流程id
    private final static String defId = "fdpi_paper_binding:fdpi_paper_binding";

    //业务数据名称
    private final static String idAttrName = "dataId";

    private final Integer maxAmount = 100000000;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveArt(ArtPaperFrom artPaperFrom) {
        RedisUserUtil.validReSubmit("fdpi_art_save");
        // 有效期至时间 大于发证日期
        // 有效日期
        artPaperFrom.setPapersNumber(this.getPapersNumber(artPaperFrom.getPapersNumber()));
        Date dataClos = artPaperFrom.getDataClos();
        // 发证日期
        Date issueDate = artPaperFrom.getIssueDate();
        int res = dataClos.compareTo(issueDate);
        // 相等则返回bai0,date1大返回1,否则返回-1;
        if (res != 1) {
            throw new SofnException("有效日期必须大于发证日期");
        }
        int i = 0;
        // 确定证书编号不存在
        Papers paperByPapersId = papersMapper.getPaperByPapersNumber(artPaperFrom.getPapersNumber());
        if (paperByPapersId != null) {
            throw new SofnException("证书编号已存在");
        }
        Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
        SysRegionInfoVo sriv = sysRegionInfoByOrgId.getData();
        Papers papers = new Papers();
        artPaperFrom.setId(IdUtil.getUUId());
        BeanUtils.copyProperties(artPaperFrom, papers);
        papers.setDelFlag("N");
        papers.setParStatus("0");
        papers.setProvincialId(sriv.getProvince());
        papers.setCityId(sriv.getCity());
        papers.setAreaId(sriv.getArea());
        papers.setCreateUserId(UserUtil.getLoginUserId());
        papers.setCreateTime(new Date());
        papers.setCompName(artPaperFrom.getCompName().trim());
        papers.setFilingLevel(SysOwnOrgUtil.getOrganizationLevel());
        //插入证书基础信息
        i = papersMapper.insert(papers);
        List<PapersSpecForm> papersSpecs = artPaperFrom.getPapersSpecs();
        //插入证书物种信息
        papersSpecService.save(papersSpecs, papers.getId());
        this.setPaperCache();
        return i;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateArt(ArtPaperFrom artPaperFrom) {
        RedisUserUtil.validReSubmit("fdpi_art_update");
        //有效期至时间大于发证日期
        //有效日期
        Date dataClos = artPaperFrom.getDataClos();
        //发证日期
        Date issueDate = artPaperFrom.getIssueDate();
        artPaperFrom.setPapersNumber(this.getPapersNumber(artPaperFrom.getPapersNumber()));
        int res = dataClos.compareTo(issueDate);
        //相等则返回bai0,date1大返回1,否则返回-1;
        if (res != 1) {
            throw new SofnException("有效日期必须大于发证日期");
        }
        int i = 0;

        Papers papers1 = papersMapper.selectById(artPaperFrom.getId());
        //判断证书是否存在
        if (papers1 == null) {
            throw new SofnException("证书不存在");
        }
        //能够修改的证书为：省级新建证书parStatus：0，或者是来自于注册绑定时的退回证书parStatus：3
        Boolean caseOne = "0".equals(papers1.getParStatus()) || "3".equals(papers1.getParStatus());
        if (caseOne) {
            Map map = new HashMap();
            map.put("id", artPaperFrom.getId());
            map.put("papersNumber", artPaperFrom.getPapersNumber());
            //  查询当前证书编号是否重复
            Papers paperByNumber = papersMapper.getPaperByNumber(map);
            if (paperByNumber != null) {
                throw new SofnException("证书编号已存在");
            }
            Papers papers = new Papers();
            BeanUtils.copyProperties(artPaperFrom, papers);
            Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
            SysRegionInfoVo sriv = sysRegionInfoByOrgId.getData();
            papers.setDelFlag("N");
            papers.setParStatus("0");
            papers.setProvincialId(sriv.getProvince());
            papers.setCityId(sriv.getCity());
            papers.setAreaId(sriv.getArea());
            papers.preUpdate();
            papers.setIsPrint(papers1.getIsPrint());
            papers.setIsPrint(papers1.getIsCopyPrint());
            papers.setCompName(artPaperFrom.getCompName().trim());
            //修改证书的基础信息
            i = papersMapper.updateById(papers);
            //修改证书的物种信息
            papersSpecService.update(artPaperFrom.getPapersSpecs(), papers.getId());

        } else {
            throw new SofnException("证书已开始绑定不可修改");
        }
        this.setPaperCache();

        return i;

    }

    /**
     * 已修改
     * 加经营利用许可证/人工繁育许可证
     *
     * @param
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveMan(PapersForm papersForm) {
        RedisUserUtil.validReSubmit("fdpi_man_save");
        //发证日期小于有效期至时间
        //有效日期
        Date dataClos = papersForm.getDataClos();
        //发证日期
        Date issueDate = papersForm.getIssueDate();
        papersForm.setPapersNumber(this.getPapersNumber(papersForm.getPapersNumber()));
        int res = dataClos.compareTo(issueDate);
        //相等则返回bai0,date1大返回1,否则返回-1;
        if (res != 1) {
            throw new SofnException("有效日期必须大于发证日期");
        }
        int i = 0;
        //经营许可证：证书编号是否存在
        Papers paperByPapersId = papersMapper.getPaperByPapersNumber(papersForm.getPapersNumber());
        if (paperByPapersId != null) {
            throw new SofnException("证书编号已存在");
        }
        //获取当前登录用户的所在行政区划
        Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
        SysRegionInfoVo sriv = sysRegionInfoByOrgId.getData();
        //确定企业没有一个当前物种的证书
        Papers papers = new Papers();
        papersForm.setId(IdUtil.getUUId());
        BeanUtils.copyProperties(papersForm, papers);
        //设置基础信息
        papers.setDelFlag("N");
        papers.setParStatus("0");
        papers.setProvincialId(sriv.getProvince());
        papers.setCityId(sriv.getCity());
        papers.setAreaId(sriv.getArea());
        papers.setCreateUserId(UserUtil.getLoginUserId());
        papers.setCreateTime(new Date());
        papers.setCompName(papersForm.getCompName().trim());
        papers.setFilingLevel(SysOwnOrgUtil.getOrganizationLevel());
        //保存基础信息
        i = papersMapper.insert(papers);
        //插入证书物种信息
        List<PapersSpecForm> papersSpecs = papersForm.getPapersSpecs();
        papersSpecService.save(papersSpecs, papers.getId());
        this.setPaperCache();
        return i;
    }

    /**
     * @param papersForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateMan(PapersForm papersForm) {
        RedisUserUtil.validReSubmit("fdpi_man_update");
        //有效期至时间大于发证日期
        //有效日期
        Date dataClos = papersForm.getDataClos();
        // 发证日期
        Date issueDate = papersForm.getIssueDate();
        papersForm.setPapersNumber(this.getPapersNumber(papersForm.getPapersNumber()));
        int res = dataClos.compareTo(issueDate);
        // 相等则返回bai0,date1大返回1,否则返回-1;
        if (res != 1) {
            throw new SofnException("有效日期必须大于发证日期");
        }
        // 判断证书是否存在
        Papers papers1 = papersMapper.selectById(papersForm.getId());

        if (papers1 == null) {
            throw new SofnException("证书不存在");
        }
        //  能够修改的证书为：省级新建证书parStatus：0，或者是来自于注册绑定时的退回证书parStatus：3
        Boolean caseOne = "0".equals(papers1.getParStatus()) || "3".equals(papers1.getParStatus());
//        if ("0".equals(papers1.getParStatus())||("3".equals(papers1.getParStatus())&& "1".equals(papers1.getSource()))) {
//            throw new SofnException("证书已开始绑定不可修改");
//    }
        int i = 0;
        if (caseOne) {
            Map map = new HashMap();
            map.put("id", papersForm.getId());
            map.put("papersNumber", papersForm.getPapersNumber());
            //查询当前要修改的证书编号是否已存在
            Papers paperByNumber = papersMapper.getPaperByNumber1(map);
            if (paperByNumber != null) {
                throw new SofnException("证书编号已存在");
            }
            Papers papers = new Papers();
            BeanUtils.copyProperties(papersForm, papers);
            Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
            SysRegionInfoVo sriv = sysRegionInfoByOrgId.getData();
            papers.setDelFlag("N");
            papers.setParStatus("0");
            papers.setProvincialId(sriv.getProvince());
            papers.setCityId(sriv.getCity());
            papers.setAreaId(sriv.getArea());
            papers.preUpdate();
            papers.setIsPrint(papers1.getIsPrint());
            papers.setCompName(papersForm.getCompName().trim());
            //修改证书的基础信息
            i = papersMapper.updateById(papers);
            //修改证书的物种信息
            papersSpecService.update(papersForm.getPapersSpecs(), papers.getId());
        } else {
            throw new SofnException("证书已开始绑定不可修改");
        }

        this.setPaperCache();
        return i;
    }

    /**
     * 已修改
     * 通过证书编号查看证书详细信息
     *
     * @param papersNumber
     * @return
     */
    @Override
    public Papers selectPaperInfoById(String papersNumber) {
        // 根据证书编号查询当前证书是否存在
        Papers paper = papersMapper.getPaperByPapersNumber(papersNumber);
        if (paper == null) {
            return null;
        }
        Papers papers = papersMapper.selectById(paper.getId());
        //查出改证书所拥有的物种
        List<PapersSpecVo> bypapersId = papersSpecService.listForCondition(paper.getId());
        if ("4".equals(papers.getParStatus())) {
            List<FileManage> list = fileManageService.listforPaper(paper.getId(), "1");
            if (!CollectionUtils.isEmpty(list)) {
                papers.setFileList(list);
            }
        }
        if (!CollectionUtils.isEmpty(bypapersId)) {
            this.initUnitMap();
            for (PapersSpecVo psv : bypapersId) {
                String speType = psv.getSpeType();
                speType = StringUtils.isEmpty(speType) ? "1" : speType;
                String unit = psv.getUnit();
                if (StringUtils.isNotBlank(unit)) {
                    psv.setUnitName(this.getUnitName(speType, unit));
                }
            }
            //设置改证书所拥有的物种
            papers.setSpeciesList(bypapersId);
        }
        return papers;
    }

    private void initUnitMap() {
        List<String> units = SpeCategoryEnum.getSelect().
                stream().map(SelectVo::getKey).collect(Collectors.toList());
        unitMap = new HashMap<>();
        for (String key : units) {
            String className = key.replaceAll("fdpi_", "").replaceAll("_unit", "") + "_Enum";
            className = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, className);
            try {
                Class clazz = Class.forName("com.sofn.fdpi.enums.speUnit." + className);
                Method method = clazz.getMethod("getSelect");
                List<SelectVo> list = (List<SelectVo>) method.invoke(clazz);
                unitMap.put(key, list.stream().collect(Collectors.toMap(SelectVo::getKey, SelectVo::getVal)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    Map<String, Map<String, String>> unitMap;


    /**
     * 移除证书
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delPapers(String id) {
        Papers papers1 = papersMapper.selectById(id);
        String parStatus = papers1.getParStatus();
        // 获取证书状态 只能删除升级新建的证书, 审核退回的证书和撤回的证书
        if (!"0".equals(parStatus) && !"3".equals(parStatus) && !"7".equals(parStatus)) {
            throw new SofnException("证书已开始绑定不可删除");
        }
        //    逻辑删除证书基础信息
        int i = papersMapper.deleteById(id);
        //逻辑删除证书物种信息
        papersSpecService.del(id);
        auditProcessService.delByPapersId(id);
        this.setPaperCache();
        return i;
    }


    @Override
    public PageUtils<PapersListVo> getArtificialPaperListPage(Map<String, Object> map, int pageNo,
                                                              int pageSize) {
        Result<SysOrgAndRegionVo> sysOrgInfoForApproveAndQuery = null;
        try {
            sysOrgInfoForApproveAndQuery = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        } catch (Exception e) {
            throw new SofnException("获取当前用户的级别失败");
        }
        String sysOrgLevelId = sysOrgInfoForApproveAndQuery.getData().getSysOrgLevelId();

        if (OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            Result<SysOrgAndRegionVo> sys = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
            Map<String, Object> sysOrgRegionMap = sys.getData().getSysOrgRegionMap();
            String province = (String) sysOrgRegionMap.get("sysOrgProvince");
            map.put("province", province);
        } else if (OrganizationLevelEnum.CITY_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_CITY_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            Result<SysOrgAndRegionVo> sys = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
            Map<String, Object> sysOrgRegionMap = sys.getData().getSysOrgRegionMap();
            String city = (String) sysOrgRegionMap.get("sysOrgCity");
            map.put("city", city);
        } else if (OrganizationLevelEnum.DISTRICT_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            Result<SysOrgAndRegionVo> sys = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
            Map<String, Object> sysOrgRegionMap = sys.getData().getSysOrgRegionMap();
            String district = (String) sysOrgRegionMap.get("sysOrgDistrict");
            map.put("area", district);
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<PapersListVo> art = papersMapper.getArtificialPaperList(map);
//        Boolean canPrint = tbDepartmentService.isAuth(DepartmentTypeEnum.PAPER_PRINT.getKey());
        Set<String> printAuths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.PAPER_PRINT.getKey());
        Set<String> recordAuths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.PAPER_RECORD.getKey());
        for (PapersListVo lsv : art) {
            lsv.setCanPrint(this.getCanPrint(printAuths, lsv));
            lsv.setCanHandle(this.getCanHandle(recordAuths, lsv));
        }
        PageInfo<PapersListVo> pageInfo = new PageInfo<>(art);
        return PageUtils.getPageUtils(pageInfo);
    }

    private Boolean getCanHandle(Set<String> auths, PapersListVo lsv) {
        String organizationLevel = SysOwnOrgUtil.getOrganizationLevel();
        String parStatus = lsv.getParStatus();
        if (!"0".equals(parStatus) && !"3".equals(parStatus) && !"7".equals(parStatus)) {
            return false;
        } else if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)) {
            return true;
        } else if (CollectionUtils.isEmpty(auths)) {
            return false;
        } else if (Constants.REGION_TYPE_CITY.equals(organizationLevel) && auths.contains(lsv.getCityId())) {
            return true;
        } else if (Constants.REGION_TYPE_COUNTY.equals(organizationLevel) && auths.contains(lsv.getAreaId())) {
            return true;
        }
        return false;
    }

    private Boolean getCanPrint(Set<String> auths, PapersListVo lsv) {
        String organizationLevel = SysOwnOrgUtil.getOrganizationLevel();
        if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)) {
            return true;
        } else if (CollectionUtils.isEmpty(auths)) {
            return false;
        } else if (Constants.REGION_TYPE_CITY.equals(organizationLevel) && auths.contains(lsv.getCityId())) {
            return true;
        } else if (Constants.REGION_TYPE_COUNTY.equals(organizationLevel) && auths.contains(lsv.getAreaId())) {
            return true;
        }
        return false;
    }


    @Override
    public PageUtils<PapersListVo> getManagementPaperListPage(Map<String, Object> map, int pageNo,
                                                              int pageSize) {
        Result<SysOrgAndRegionVo> sysOrgInfoForApproveAndQuery = null;
        try {
            sysOrgInfoForApproveAndQuery = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        } catch (Exception e) {
            throw new SofnException("获取当前用户的级别失败");
        }
        String sysOrgLevelId = sysOrgInfoForApproveAndQuery.getData().getSysOrgLevelId();

        if (OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            Result<SysOrgAndRegionVo> sys = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
            Map<String, Object> sysOrgRegionMap = sys.getData().getSysOrgRegionMap();
            String province = (String) sysOrgRegionMap.get("sysOrgProvince");
            map.put("province", province);
        } else if (OrganizationLevelEnum.CITY_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_CITY_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            Result<SysOrgAndRegionVo> sys = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
            Map<String, Object> sysOrgRegionMap = sys.getData().getSysOrgRegionMap();
            String city = (String) sysOrgRegionMap.get("sysOrgCity");
            map.put("city", city);
        } else if (OrganizationLevelEnum.DISTRICT_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            Result<SysOrgAndRegionVo> sys = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
            Map<String, Object> sysOrgRegionMap = sys.getData().getSysOrgRegionMap();
            String district = (String) sysOrgRegionMap.get("sysOrgDistrict");
            map.put("area", district);
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<PapersListVo> artificialPaperList = papersMapper.getManagementPaperList(map);
//        Boolean canPrint = tbDepartmentService.isAuth(DepartmentTypeEnum.PAPER_PRINT.getKey());
        Set<String> printAuths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.PAPER_PRINT.getKey());
        Set<String> recordAuths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.PAPER_RECORD.getKey());
        for (PapersListVo lsv : artificialPaperList) {
            lsv.setCanPrint(this.getCanPrint(printAuths, lsv));
            if (PapersType2Enum.NO_PAPERS.getKey().equals(lsv.getPapersType())) {
                lsv.setCanPrint(false);
            }
            lsv.setCanHandle(this.getCanHandle(recordAuths, lsv));
        }
        PageInfo<PapersListVo> pageInfo = new PageInfo<>(artificialPaperList);
        return PageUtils.getPageUtils(pageInfo);
    }


    /**
     * 获取证书下拉列表
     * wuXY
     * 2019-12-30 16:42:44
     *
     * @param map 证书类型：1：人工繁育；2：驯养繁殖；3：经营利用
     * @return List<SelectVo>
     */
    @Override
    public List<SelectVo> listPapersForSelect(Map<String, Object> map) {
        return papersMapper.listPapersForSelect(map);
    }

    /**
     * 获取证书绑定申请列表
     * wuXY
     * 2019-12-31 11:32:21
     *
     * @param map 查询阐述
     * @return 返回分页数据
     */
    @Override
    public PageUtils<PapersVo> listForBinding(Map<String, Object> map, Integer pageNo, Integer
            pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<PapersVo> papersList = papersMapper.listForBinding(map);
        //新增校验，当且仅当证书状态为已上报的时候才显示撤回按钮
        papersList.stream().forEach(vo -> {
            if (DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(vo.getParStatus())) {
                vo.setIsShowCancel(true);
            }
            vo.setPapersTypeName(PapersType2Enum.getVal(vo.getPapersType()));
            vo.setParStatusName(PapersBindingProcessEnum.getVal(vo.getParStatus()));
        });
        PageInfo<PapersVo> pageInfo = new PageInfo<>(papersList);

        return PageUtils.getPageUtils(pageInfo);
    }

    /**
     * 新增证书绑定申请
     * wuXY
     * 2019-12-31 11:32:21
     *
     * @param papersBindingForm 表单
     * @param status:1:未上报；2：上报
     * @return 返回1：成功；其他：异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveForBinding(PapersBindingForm papersBindingForm, String status) {
        //校验重复提交
        RedisUserUtil.validReSubmit("fdpi_papersBinding_saveForBinding", papersBindingForm.getPapersId());
        //验证上传的文件，必填
        if (CollectionUtils.isEmpty(papersBindingForm.getFileList())) {
            return "证书图片必须上传！";
        }
        //获取组织机构名称
        SysUserForm sysUserForm = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysUserForm.class);
        Date dateNow = new Date();
        //3.修改证书中的信息
        Papers papers = convertToPapers(papersBindingForm, dateNow, status);
        //从数据库中获取当前证书文件列表
        List<FileManageVo> fileListInDb = fileManageService.listBySourceId(papers.getId());
        //保存使用
        List<FileManage> fileManagesList = assembleFileManage(papers.getId(), papersBindingForm.getFileList());
        try {
            //修改证书信息
            this.updateById(papers);
            if ("2".equals(status)) {
                //上报
                AuditProcess auditProcess = convertAuditProcess(
                        papers.getId(), DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), papers.getUpdateTime(),
                        sysUserForm.getOrganizationName(), "0", "", papers.getApplyNum());
                auditProcessMapper.insert(auditProcess);
                //流程组件
                if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                    SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(auditProcess.getPapersId(), auditProcess.getStatus(), auditProcess.getAdvice(), auditProcess.getPersonName());
                    Result<String> result = workService.startChainProcess(submitInstanceVo);
                    if (!Result.CODE.equals(result.getCode())) {
                        throw new SofnException("加入流程失败");
                    }
                }
            }

            //处理文件，先删除在保存
            if (!CollectionUtils.isEmpty(fileListInDb)) {
                List<String> idsList = fileListInDb.stream().map(FileManageVo::getId).collect(Collectors.toList());
                fileManageService.deleteBatchIds(idsList);
            }
            fileManageService.batchInsert(fileManagesList);
            //激活证书文件
            SysFileManageUtil.dealFileListForUpdate(papersBindingForm.getFileList(), fileListInDb);
            return "1";
        } catch (Exception ex) {
            log.error("证书绑定异常：" + ex.getMessage());
            throw new SofnException("失败！");
        }
    }

    /**
     * 新增证书绑定申请保存中的《上报》
     * wuXY
     * 2019-12-31 11:32:21
     *
     * @param papersId 表单
     * @return 返回1：成功；其他：异常
     */
    @Override
    @Transactional
    public String reportForBinding(String papersId, String status) {
        //校验重复提交
        RedisUserUtil.validReSubmit("fdpi_papersBinding_reportForBinding");
        Date dateNow = new Date();
        Map<String, Object> map = Maps.newHashMap();
        map.put("parStatus", status);
        map.put("updateTime", dateNow);
        map.put("updateUserId", UserUtil.getLoginUserId());
        map.put("papersId", papersId);
        String applyNum = this.getPapersInfo(papersId).getApplyNum();
        if (StringUtils.isBlank(applyNum)) {
            String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
            map.put("applyNum", CodeUtil.getApplyCode(provinceCode,
                    CodeTypeEnum.PAPER_CHANGE.getKey(), this.getSequenceNum(provinceCode)));
        }
        try {
            papersMapper.updateStatusById(map);

            //获取组织机构名称
            SysUserForm sysUserForm = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysUserForm.class);
            //上报操作流水
            AuditProcess auditProcess = convertAuditProcess(
                    papersId, DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), dateNow, sysUserForm.getOrganizationName(),
                    "0", "", StringUtils.isBlank(applyNum) ? map.get("applyNum").toString() : applyNum);
            auditProcessMapper.insert(auditProcess);
            //流程组件
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(papersId, auditProcess.getStatus(), auditProcess.getAdvice(), auditProcess.getPersonName());
                Result<String> result = workService.startChainProcess(submitInstanceVo);
                if (!Result.CODE.equals(result.getCode())) {
                    throw new SofnException("加入流程失败");
                }
            }
            return "1";
        } catch (Exception ex) {
            log.error("证书绑定上报失败：" + ex.getMessage());
            throw new SofnException("上报失败！");
        }
    }

    private String getSequenceNum(String provinceCode) {
        String maxApplyNum = this.getTodayMaxApplyNum(
                DateUtils.format(new Date(), "yyyyMMdd") + "0" + provinceCode);
        return StringUtils.isNotBlank(maxApplyNum) ?
                String.format("%06d", (Integer.valueOf(maxApplyNum.substring(13)) + 1)) : null;
    }

    /**
     * 根据id获取证书信息（编辑获取数据）
     * wuXY
     * 2019-12-31 11:32:21
     *
     * @param papersId 证书编号
     * @return EnterpriseForm
     */
    @Override
    public PapersBindingVo getPapersInfo(String papersId) {
        PapersBindingVo papersBinding = papersMapper.getPapersInfo(papersId);
        if (papersBinding != null) {
            //获取证书物种列表
            List<PapersSpecVo> speciesList = papersSpecService.listForCondition(papersId);
            if (!CollectionUtils.isEmpty(speciesList)) {
                this.initUnitMap();
                for (PapersSpecVo psv : speciesList) {
                    String speType = psv.getSpeType();
                    speType = StringUtils.isEmpty(speType) ? "1" : speType;
                    String unit = psv.getUnit();
                    if (StringUtils.isNotBlank(unit)) {
                        psv.setUnitName(this.getUnitName(speType, unit));
                    }
                }
            }
            //获取证书文件列表
            List<FileManageVo> fileList = fileManageService.listBySourceIdAndFileStatusOne(papersId, "1");
            papersBinding.setSpeciesList(speciesList);
            papersBinding.setFileList(fileList);
        }
        return papersBinding;
    }

    /**
     * 根据id或者证书信息（编辑或者详情使用），返回列表是可能有多证书（注册多证书）
     * wuXY
     * 2020-11-4 16:32:35
     *
     * @param papersId 证书编号
     * @return 返回证书信息列表
     */
    @Override
    public List<PapersBindingVo> papersListForView(String papersId) {
        List<PapersBindingVo> papersList = Lists.newArrayList();
        String papersIdOne = null;
        String papersIdTwo = null;
        if (papersId.contains(",")) {
            String[] papersIdSplit = papersId.split(",");
            papersIdOne = papersIdSplit[0];
            papersIdTwo = papersIdSplit[1];
        } else {
            papersIdOne = papersId;
        }
        PapersBindingVo papersOneInfo = this.getPapersInfo(papersIdOne);
        if (ObjectUtils.isEmpty(papersOneInfo)) {
            return papersList;
        }
        papersList.add(papersOneInfo);
        if (!StringUtils.isBlank(papersIdTwo)) {
            //注册绑定证书，需要去找是否两个证书绑定注册。
            PapersBindingVo papersTwoInfo = this.getPapersInfo(papersIdTwo);
            papersList.add(papersTwoInfo);
        }
        return papersList;
    }

    private void perfectParams(Map<String, Object> params) {
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        //验证用户机构配置是否满足要求
        RedisUserUtil.validLoginUser(orgInfo);
        String id = orgInfo.getId();
        String thirdOrg = orgInfo.getThirdOrg();
        String regionLastCode = orgInfo.getRegionLastCode();
        // 第三方机构
        if (BoolUtils.N.equals(thirdOrg)) {
            params.put("compId", id);
        } else {
            String organizationLevel = orgInfo.getOrganizationLevel();
            params.put("organizationLevel", organizationLevel);
            params.put("regionLastCode", regionLastCode);
        }

    }

    /**
     * 获取证书绑定审核列表
     * wuXY
     * 2020-1-2 10:08:57
     *
     * @param map 查询参数
     * @return list
     */
    @Override
    public PageUtils<PapersVo> listForBindingApprove(Map<String, Object> map) {
        //获取当前用户的数据条件
        this.perfectParams(map);
        PageHelper.offsetPage(Integer.parseInt(map.get("pageNo").toString()), Integer.parseInt(map.get("pageSize").toString()));
        List<PapersVo> papersVoList = papersMapper.listForBindingApprove(map);
        Map<String, String> paperTypeMap = sysRegionApi.getDictListByType("fdpi_PaperType").getData().
                stream().collect(Collectors.toMap(SysDict::getDictcode, SysDict::getDictname));
//        Boolean canAudit = tbDepartmentService.isAuth(DepartmentTypeEnum.PAPER_BINDING.getKey());
        Set<String> auths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.PAPER_BINDING.getKey());
        for (PapersVo papersVo : papersVoList) {
            String[] papersTypes = papersVo.getPapersType().split(",");
            papersVo.setCanAudit(this.getCanAudit(auths, papersVo));
            papersVo.setPapersTypeName(
                    papersTypes.length == 2 ? paperTypeMap.get(papersTypes[0]) + "," + paperTypeMap.get(papersTypes[1])
                            : paperTypeMap.get(papersTypes[0]));
            papersVo.setParStatusName(PapersBindingProcessEnum.getVal(papersVo.getParStatus()));
        }
        PageInfo<PapersVo> pageInfo = new PageInfo<>(papersVoList);
        return PageUtils.getPageUtils(pageInfo);
    }

    private Boolean getCanAudit(Set<String> auths, PapersVo papersVo) {
        String parStatus = papersVo.getParStatus();
        //只有状态在上报情况才能审核
        if (!PapersBindingProcessEnum.REPORT.getKey().equals(parStatus)) {
            return false;
        }
        String organizationLevel = SysOwnOrgUtil.getOrganizationLevel();
        String compDistrict = papersVo.getCompDistrict();
        String compCity = papersVo.getCompCity();

        if (CollectionUtils.isEmpty(auths)) {
            return false;
        } else if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)) {
            if (auths.contains(compDistrict) || auths.contains(compCity)) {
                return false;
            }
        } else if (Constants.REGION_TYPE_CITY.equals(organizationLevel)) {
            if (auths.contains(compDistrict)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 证书审核
     * wuXY
     * 2020-1-2 10:51:06
     * 直属-初审；省级-复审
     *
     * @param processForm 审核表单
     * @param isApprove   1:审核，0：退回
     * @return 1：成功；其它：提示
     */
    @Override
    public String approveOrBack(ProcessForm processForm, String isApprove) {
        //校验重复提交
        RedisUserUtil.validReSubmit("fdpi_papersBinding_approveOrBack", processForm.getId());
        //processForm.getId() 可能有多个值，已, 隔开(注册时有两个证书的问题)
        String papersIdOne = null;
        String papersIdTwo = null;
        TbCompVo compModel = null;
        if (processForm.getId().contains(",")) {
            String[] papersIdSplit = processForm.getId().split(",");
            papersIdOne = papersIdSplit[0];
            papersIdTwo = papersIdSplit[1];
        } else {
            papersIdOne = processForm.getId();
        }
        Papers papers = papersMapper.getPapersById(papersIdOne);
        if (papers == null) {
            return "证书不存在！";
        }
        //获取当前审核机构
        Result<SysOrgAndRegionVo> result = SysOwnOrgUtil.getSysApproveLevelForApprove("1");

        String loginUserId = UserUtil.getLoginUserId();
        //获取员工的id和password
        TbUsers userModel = tbUsersService.getUserIdAndPassword(papers.getCompId());
        String userName = userModel.getAccount();

        if (Result.CODE.equals(result.getCode())) {
            //获取当前组织机构级别成功
            SysOrgAndRegionVo data = result.getData();
            //绑定两个证书的审核流程列表
            List<AuditProcess> auditProcessList = Lists.newArrayList();
            AuditProcess auditProcess = null;
            String isFinalApprove = "0";
            Date dateNow = new Date();
            //判断审核级别
            if (ApproveLevelEnum.APPROVE_FIRST_LEVEL.getLevel().equals(data.getApproveLevel())) {
                //审核
                auditProcess = convertAuditProcess(papersIdOne, processForm.getAdvice(), dateNow, data.getOrgName(),
                        "1", isApprove, this.getPapersById(papersIdOne).getApplyNum());
                auditProcessList.add(auditProcess);
                if (!StringUtils.isBlank(papersIdTwo)) {
                    //判断是否有第二个证书
                    auditProcess = convertAuditProcess(papersIdTwo, processForm.getAdvice(), dateNow, data.getOrgName(),
                            "1", isApprove, this.getPapersById(papersIdTwo).getApplyNum());
                    auditProcessList.add(auditProcess);
                }
                isFinalApprove = "1";
            } else {
                return "该部门用户不能审核/退回";
            }
            //

            String returnSysOrgId = "";
            String returnSysUserId = "";
            //修改企业信息的参数对象map
            Map<String, Object> compMap = Maps.newHashMap();
            //修改用户信息的参数对象map
            Map<String, Object> userMap = Maps.newHashMap();
            //修改操作流水中上报的用户id
            Map<String, Object> updateAuditProMap = Maps.newHashMap();

            if ("1".equals(isFinalApprove) && "0".equals(papers.getSource()) && "1".equals(isApprove)) {
                //终极审核,并且是来源于注册，则需要调支撑平台新增组织机构
                //1、获取当前企业信息
                compModel = tbCompService.getCombById(papers.getCompId());

                SysThirdOrgForm sysThirdOrgForm = assembleSysThirdOrgForm(compModel);
                //新增第三方机构，调支持平台接口
                Result<String> thirdOrgResult = sysRegionApi.createThirdOrganization(sysThirdOrgForm);
                if (Result.CODE.equals(thirdOrgResult.getCode())) {
                    //创建成功
                    returnSysOrgId = thirdOrgResult.getData();
                } else {
                    return thirdOrgResult.getMsg();
                }

                //新增支撑平台中的员工

                SysUserRoleCodeForm sysUserRoleCodeForm = assembleSysUserRoleCodeForm(userModel.getAccount(), userModel.getPassword(), returnSysOrgId, compModel);
                //调用支撑平台创建用户接口创建用户
                Result<String> userResult = sysRegionApi.createByRoleCode(sysUserRoleCodeForm);
                if (Result.CODE.equals(userResult.getCode())) {
                    returnSysUserId = userResult.getData();
                } else {
                    // return userResult.getMsg();
                }

                //获取直属机构的机构级别
                Result<SysOrganizationForm> sysOrgInfoResult = sysRegionApi.getOrgInfoById(compModel.getDireclyId());
                String directOrgLevel = "";
                if (Result.CODE.equals(sysOrgInfoResult.getCode())) {
                    SysOrganizationForm sysOrgInfo = sysOrgInfoResult.getData();
                    directOrgLevel = sysOrgInfo.getOrganizationLevel();
                } else {
                    return sysOrgInfoResult.getMsg();
                }

                //组装修改企业表中的信息
                compMap = assembleCompMapForUpdate(true, "1", dateNow, returnSysOrgId, compModel.getId(), directOrgLevel);

                //组装修改员工表信息
                assembleUserMap(userMap, dateNow, returnSysOrgId, returnSysUserId, userModel.getId());

                //修改操作记录中的人员id,主要是注册上报
                updateAuditProMap.put("newPersonId", returnSysUserId);
                updateAuditProMap.put("oldPersonId", userModel.getId());
            }
            //如果是企业注册，并且退回操作，则需要改企业表中的状态，改成2：退回
            if ("0".equals(papers.getSource()) && !"1".equals(isApprove)) {
                //1、获取当前企业信息
                compModel = tbCompService.getCombById(papers.getCompId());
                //组装修改企业表中的信息
                compMap = assembleCompMapForUpdate(false, "2", dateNow, "", compModel.getId(), "");
            }
            //如果是复审则需要在库存表和库存流水表中增加记录
            List<CompSpeStock> stockList = Lists.newArrayList();
            List<CompSpeStockFlow> stockFlowLis = Lists.newArrayList();
            if ("1".equals(isFinalApprove) && "1".equals(isApprove)) {
                //获取证书绑定申请中物种列表
                List<PapersSpecVo> speList = null;
                if (StringUtils.isBlank(papersIdTwo)) {
                    speList = papersSpecService.listForCondition(papers.getId());
                } else {
                    List<String> papersIdList = Lists.newArrayList();
                    papersIdList.add(papersIdOne);
                    papersIdList.add(papersIdTwo);
                    speList = papersSpecService.listByPapersIds(papersIdList);
                }

                if (!CollectionUtils.isEmpty(speList)) {
                    speList = this.mergeSpeList(speList);
                    String finalReturnSysOrgId = returnSysOrgId;
                    String finalPapersIdOne = papersIdOne;
                    speList.forEach(PapersSpecVo -> {
                        QueryWrapper<CompSpeStock> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("SPECIES_ID", PapersSpecVo.getSpecId())
                                .eq("COMP_ID", StringUtils.isBlank(finalReturnSysOrgId) ? papers.getCompId() : finalReturnSysOrgId);
                        CompSpeStock stockObj = compSpeStockMapper.selectOne(queryWrapper);
                        if (stockObj == null) {

                            CompSpeStock stock = new CompSpeStock();
                            CompSpeStockFlow stockFlow = new CompSpeStockFlow();
                            stock.setId(IdUtil.getUUId());
                            stock.setCompId(StringUtils.isBlank(finalReturnSysOrgId) ? papers.getCompId() : finalReturnSysOrgId);
                            stock.setLastChangeTime(dateNow);
                            stock.setLastChangeUserId(loginUserId);
                            stock.setSpeciesId(PapersSpecVo.getSpecId());
                            if (papers.getSource().equals(PapersSpecSource.CHANGE)) {
                                stock.setSpeNum(0);
                                stockFlow.setBillType(ComeStockFlowEnum.CHANGE.getCode());
                            } else {
                                Integer amount = PapersSpecVo.getAmount();
                                stock.setSpeNum(Objects.isNull(amount) ? 0 : amount);
                                stockFlow.setBillType(ComeStockFlowEnum.REGISTER.getCode());
                            }
                            stockList.add(stock);
                            stockFlow.setId(IdUtil.getUUId());
                            stockFlow.setCompId(stock.getCompId());
                            stockFlow.setSpeciesId(stock.getSpeciesId());
                            stockFlow.setSpeNum(finalPapersIdOne);
                            stockFlow.setImportMark("入库");
                            stockFlow.setBeforeNum(0);
                            stockFlow.setChNum(stock.getSpeNum());
                            stockFlow.setAfterNum(stock.getSpeNum());
                            stockFlow.setLastChangeUserId(loginUserId);
                            stockFlow.setOtherComName("");
                            stockFlow.setChTime(dateNow);
                            stockFlowLis.add(stockFlow);
                        }
                    });
                }
            }
            //事务
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
            Result<String> workResult = new Result<>();
            try {
                if ("1".equals(isFinalApprove) && "0".equals(papers.getSource()) && "1".equals(isApprove)) {
                    //终极审核,并且是来源于注册
                    //修改企业信息

                    //增加企业编号
                    String maxCompCode = tbCompService.getMaxCompCode();
                    compMap.put("compCode", String.format("%04d",
                            Integer.valueOf(StringUtils.isBlank(maxCompCode) ? "0" : maxCompCode) + 1));
                    tbCompService.updateStatusById(compMap);
                    //修改员工信息
                    tbUsersService.updateStatusById(userMap);
                    //修改操作记录中的人员id
                    if (Constants.WORKFLOW.equals(BoolUtils.N)) {
                        auditProcessMapper.updatePersonId(updateAuditProMap);
                    }
                }

                //修改当前证书之前，需要修改之前证书类型的，isEnable字段为'0';
                if ("1".equals(papers.getSource()) && "1".equals(isApprove)) {
                    papersMapper.updateIsEnableByPapersType(papers.getPapersType(), papers.getCompId());
                }

                //修改证书审核状态
                Map<String, Object> map = Maps.newHashMap();
                if (StringUtils.isBlank(papersIdTwo)) {
                    map.put("papersId", auditProcess.getPapersId());
                } else {
                    List<String> idsList = Lists.newArrayList();
                    idsList.add(papersIdOne);
                    idsList.add(papersIdTwo);
                    map.put("papersIdList", idsList);
                }
                map.put("parStatus", auditProcess.getStatus());
                map.put("updateTime", dateNow);
                map.put("updateUserId", auditProcess.getPerson());
                if ("1".equals(isApprove)) {
                    map.put("isEnable", "1");
                }
                if (StringUtils.isNotBlank(returnSysOrgId)) {
                    //修改企业id的值
                    map.put("compId", returnSysOrgId);
                }

                if (StringUtils.isBlank(papersIdTwo)) {
                    papersMapper.updateStatusById(map);
                    //新增审核/退回操作记录；
                    auditProcessMapper.insert(auditProcess);
                    //流程组件
                    if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                        if (isApprove.equals("1")) {
                            //1 审核
                            SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(auditProcess.getPapersId(), auditProcess.getStatus(), auditProcess.getAdvice(), auditProcess.getPersonName());
                            workResult = workService.completeWorkItem(submitInstanceVo);
                        } else {
                            //0 退回
                            BackWorkItemForm backWorkItemForm = backWorkItemFormProcess(auditProcess.getPapersId(), auditProcess.getStatus(), auditProcess.getAdvice(), auditProcess.getPersonName());
                            workResult = workService.backWorkItem(backWorkItemForm);
                        }
                        if (!Result.CODE.equals(workResult.getCode())) {
                            throw new SofnException("加入流程失败");
                        }
                    }
                } else {
                    //注册时的两个证书
                    papersMapper.updateStatusByIds(map);
                    auditProcessMapper.insertBatch(auditProcessList);
                    //流程组件
                    if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                        if (isApprove.equals("1")) {
                            //1 审核
                            for (AuditProcess aProcess : auditProcessList) {
                                SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(aProcess.getPapersId(), aProcess.getStatus(), aProcess.getAdvice(), aProcess.getPersonName());
                                workResult = workService.completeWorkItem(submitInstanceVo);
                            }
                        } else {
                            //0 退回
                            for (AuditProcess aProcess : auditProcessList) {
                                BackWorkItemForm backWorkItemForm = backWorkItemFormProcess(aProcess.getPapersId(), aProcess.getStatus(), aProcess.getAdvice(), aProcess.getPersonName());
                                workResult = workService.backWorkItem(backWorkItemForm);
                            }
                        }
                        if (!Result.CODE.equals(workResult.getCode())) {
                            throw new SofnException("加入流程失败");
                        }

                    }
                }
                if (!CollectionUtils.isEmpty(stockList)) {
                    stockList.forEach(speStock -> {
                        compSpeStockMapper.insert(speStock);
                    });
                    stockFlowLis.forEach(speStockFlow -> {
                        compSpeStockFlowMapper.insert(speStockFlow);
                    });
                }

                //证书年审退回操作。
                if ("0".equals(papers.getSource()) && !"1".equals(isApprove)) {

                    //修改企业信息
                    tbCompService.updateStatusById(compMap);
                    //修改文件管理表中的状态值：0（删除）
                    fileManageService.updateFileStatusForDelete(papers.getId());
                    //企业注册，证书绑定审核退回，则可以重新注册，删除企业注册企业缓存
//                    RedisCompUtil.deleteCompInCacheForHash(papers.getCompName());
//                    RedisCompUtil.deleteUserNameInCacheForHash(userName);
                }

                //提交事务
                platformTransactionManager.commit(transactionStatus);
                return "1";
            } catch (Exception ex) {
                log.error("证书审核/退回失败：" + ex.getMessage());
                result.setMsg(ex.getMessage());
                //事务回滚
                platformTransactionManager.rollback(transactionStatus);
            }

            TbCompVo finalCompModel = compModel;
            new Thread(() -> {
                //发送邮件
                if ("0".equals(papers.getSource())) {
                    if (isApprove.equals("1")) {
                        sendEmail(finalCompModel.getEmail(), "您在农业农村部渔业渔政管理局门户平台中注册的账号 " + userModel.getAccount() + " 已通过，欢迎使用国家重点保护水生野生动物标识及信息管理子系统!");
                    } else {
                        sendEmail(finalCompModel.getEmail(), "您在农业农村部渔业渔政管理局门户平台中注册的账号 " + userModel.getAccount() + " 已被上级驳回，请联系上级确认!");
                    }
                }
            }).start();

        }
        return result.getMsg();
    }

    /**
     * 合并相同物种的数量
     */
    private List<PapersSpecVo> mergeSpeList(List<PapersSpecVo> list) {
        List<PapersSpecVo> res = Lists.newArrayListWithCapacity(list.size());
        list.parallelStream().collect(Collectors.groupingBy(PapersSpecVo::getSpecId, Collectors.toList()))
                .forEach((id, transfer) -> {
                    transfer.stream().reduce((a, b) -> new PapersSpecVo(a.getPapersId(), a.getSpecId(), a.getSpecName(),
                            a.getSource(), Integer.valueOf(a.getAmount()) + Integer.valueOf(b.getAmount()),
                            a.getProLevel(), a.getCites(), a.getMode())).ifPresent(res::add);
                });
        return res;
    }


    /**
     * 获取证书审核/退回意见
     * wuXY
     * 2020-1-2 10:51:06
     *
     * @param papersId 证书编号
     * @param status   状态
     * @return AuditProcess
     */
    @Override
    public AuditProcess getAuditProcessByPapersId(String papersId, String status) {
        if (papersId.contains(",")) {
            papersId = papersId.split(",")[0];
        }
        return auditProcessMapper.getObj(papersId, status);
    }

    @Override
    public AuditProcess getAuditProcessByPapersIdInfo(String papersId, String status) {
        if (papersId.contains(",")) {
            papersId = papersId.split(",")[0];
        }
        List<Map<String, Object>> changeRecordId = WorkUtil.getProcesslist(defId, idAttrName, papersId);
        AuditProcess auditProcess = new AuditProcess();
        changeRecordId.stream().anyMatch(o -> {
            String resultStatus = String.valueOf(o.get("status"));
            if (resultStatus.equals(status)) {
                auditProcess.setId(IdUtil.getUUId());
                auditProcess.setStatus(status);
                auditProcess.setAdvice(String.valueOf(o.get("advice")));
                auditProcess.setConTime((Date) o.get("createTime"));
                auditProcess.setPersonName(String.valueOf(o.get("personName")));
                return true;
            }
            return false;
        });

        return auditProcess;
    }

    //    /**
//     * 证书信息-》修改证书文件
//     *
//     * @param papersFileForm 证书文件表单对象
//     * @return 1：成功；其他：提示异常
//     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updatePapersFile(PapersFileForm papersFileForm) {
        Papers papers = this.getById(papersFileForm.getPapersId());
        if (papers == null) {
            return "证书id不存在！";
        }

        //先删除数据库文件，然后再新增
        try {
            List<FileManage> fileManageList = assembleFileManage(papers.getId(), papersFileForm.getFileList());
            fileManageService.delBySourceId(papers.getId());
            fileManageService.batchInsert(fileManageList);
            //激活或者删除文件，调用支撑平台接口
            //获取数据库中的证书文件列表
            List<FileManageVo> fileListInDB = fileManageService.listBySourceId(papers.getId());
            SysFileManageUtil.dealFileListForUpdate(papersFileForm.getFileList(), fileListInDB);
        } catch (Exception ex) {
            throw new SofnException("保存失败！");
        }
        this.setPaperCache();
        return "1";
    }

    //证书绑定申请中删除功能
    @Override
    public String deleteBindingByPapersId(String papersId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("status", "0");
        map.put("updateTime", new Date());
        map.put("updateUserId", UserUtil.getLoginUserId());
        map.put("papersId", papersId);
        int i = papersMapper.deleteBindingByPapersId(map);
        if (i > 0) {
            return "1";
        }
        return "删除失败！";
    }

    @Transactional()
    @Override
    public String importArt(List<PaperExcel> importList) {
        if (importList.size() >= 3001) {
            throw new SofnException("一次导入的数据不超过3000");
        }
        Map<String, Object> data = this.handleData(importList);
        papersMapper.getCache();
        Map<String, String> papersMap = this.getOldPapers().stream().
                collect(Collectors.toMap(Papers::getPapersNumber, Papers::getId));
        List<Papers> papersList = (List<Papers>) data.get("papers");
        for (Papers p : papersList) {
            if (StringUtils.isNotBlank(papersMap.get(p.getPapersNumber())))
                throw new SofnException("已存在证书编号--" + p.getPapersNumber() + ",不可重复添加");
        }
        List<PapersSpec> specList = (List<PapersSpec>) data.get("papersSpec");
        this.saveBatch(papersList);
        papersSpecService.saveBatch(specList);
        return "";
    }

    private List<Papers> getOldPapers() {
        QueryWrapper<Papers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N);
        queryWrapper.select("id", "papers_number");
        return papersMapper.selectList(queryWrapper);
    }

    private Map<String, Object> handleData(List<PaperExcel> paperExcels) {
        Object redisObj = redisHelper.get(Constants.REDIS_KEY_ALL_SPECIES);
        if (redisObj == null) {
            throw new SofnException("物种缓存为空，请确认系统是否存在物种，或修改物种信息已达到重设缓存的效果");
        }
        List<SpeNameLevelVo> speNameLevelVos = JsonUtils.json2List(redisObj.toString(), SpeNameLevelVo.class);
        Map<String, String> speMap = Maps.newHashMap();
        if (speNameLevelVos != null) {
            speNameLevelVos.forEach(o -> {
                //物种名和id的map
                speMap.put(o.getSpeName(), o.getId());
            });
        }
        Map<String, Object> result = new HashMap<>(2);
        Map<String, Papers> paperMap = new HashMap<>();
        List<PapersSpec> specList = new ArrayList<>(paperExcels.size());
        Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
        SysRegionInfoVo sriv = sysRegionInfoByOrgId.getData();
        this.initUnitMap();
        for (PaperExcel pe : paperExcels) {
            String index = pe.getId();
            String papersNumber = pe.getPapersNumber();
            if (StringUtils.isBlank(papersNumber)) {
                throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
            }
            papersNumber = this.getPapersNumber(papersNumber);
            Papers p = paperMap.get(papersNumber);
            if (Objects.isNull(p)) {
                p = new Papers();
                p.preInsert();
                BeanUtils.copyProperties(pe, p);
                p.setId(IdUtil.getUUId());
                p.setParStatus("0");
                p.setProvincialId(sriv.getProvince());
                p.setCityId(sriv.getCity());
                p.setAreaId(sriv.getArea());
                p.setDataClos(pe.getTerm());
                p.setCompName(p.getCompName().trim());
                p.setPapersNumber(papersNumber);
                p.setFilingLevel(SysOwnOrgUtil.getOrganizationLevel());
                String papersType = p.getPapersType();
                if (PapersType2Enum.ARTIFICIAL_BREEDING.getVal().equals(papersType)) {
                    papersType = PapersType2Enum.ARTIFICIAL_BREEDING.getKey();
                } else if (PapersType2Enum.MANAGEMENT_UTILIZATION.getVal().equals(papersType)) {
                    papersType = PapersType2Enum.MANAGEMENT_UTILIZATION.getKey();
                } else if (PapersType2Enum.NO_PAPERS.getVal().equals(papersType)) {
                    papersType = PapersType2Enum.NO_PAPERS.getKey();
                }
                p.setPapersType(papersType);
                paperMap.put(papersNumber, p);
            }
            String speName = pe.getSpecName().trim();
            if (!speMap.containsKey(speName)) {
                throw new SofnException("系统不存在物种名（" + speName + "），请联系部级管理员添加后再试！");
            }
            PapersSpec ps = new PapersSpec();
            ps.preInsert();
            BeanUtils.copyProperties(pe, ps);
            ps.setId(IdUtil.getUUId());
            ps.setSource(pe.getOrigin());
            ps.setSpecId(speMap.get(speName));
            ps.setPapersId(p.getId());
            ps.setUnit(this.getUnit(speName, ps.getUnit(), index));
            specList.add(ps);

        }
        result.put("papers", paperMap.values().stream().collect(Collectors.toList()));
        result.put("papersSpec", specList);
        return result;
    }

    //通过物种中文名及中文单位获取单位值
    private String getUnit(String speName, String unitName, String index) {

        String speType = speService.getSpeTypeByName(speName);
        String unit = "";
        if (StringUtils.isNotBlank(speType)) {
            if (StringUtils.isEmpty(unitName)) {
                throw new SofnException("第" + index + "行数据，导入失败，未设置单位，请检查后提交");
            }
            Map<String, String> map = unitMap.get(speType);
            for (String key : map.keySet()) {
                if (unitName.trim().equals(map.get(key))) {
                    unit = key;
                    break;
                }
            }
        } else {
            throw new SofnException("第" + index + "行数据，导入失败，物种(" + speName + ")未设置物种类别，请检查后提交");
        }
        if (StringUtils.isBlank(unit)) {
            throw new SofnException("第" + index + "行数据，导入失败，数据字典未找到单位（" + unitName + "）的对应值，请在对应数据字典添加");
        }
        return unit;
    }

    private String getPapersNumber(String papersNumber) {
        papersNumber = papersNumber.replaceAll(" ", "");
        papersNumber = papersNumber.replaceAll("（", "(").replaceAll("【", "(");
        papersNumber = papersNumber.replaceAll("）", ")").replaceAll("】", ")");
        return papersNumber;
    }


    @Transactional()
    @Override
    public String importMan(List<PaperExcel> importList) {
        if (importList.size() >= 3001) {
            throw new SofnException("一次导入的数据不超过3000");
        }
        Map<String, Object> data = this.handleData(importList);
        papersMapper.getCache();
        Map<String, String> papersMap = this.getOldPapers().stream().
                collect(Collectors.toMap(Papers::getPapersNumber, Papers::getId));
        List<Papers> papersList = (List<Papers>) data.get("papers");
        for (Papers p : papersList) {
            if (StringUtils.isNotBlank(papersMap.get(p.getPapersNumber())))
                throw new SofnException("已存在证书编号--" + p.getPapersNumber() + ",不可重复添加");
        }
        List<PapersSpec> specList = (List<PapersSpec>) data.get("papersSpec");

        this.saveBatch(papersList);
        papersSpecService.saveBatch(specList);
        return "";
    }

    /**
     * 通过证书id，获取证书信息和物种信息
     *
     * @param papersId 证书id
     * @return papers
     */
    @Override
    public Papers getPapersById(String papersId) {
        Papers papers = papersMapper.getPapersById(papersId);
        if (papers != null) {
            //获取证书下面的物种列表
            List<PapersSpecVo> specList = papersSpecService.listForCondition(papers.getId());
            this.initUnitMap();
            for (PapersSpecVo vo : specList) {
                vo.setUnitName(unitMap.get(vo.getSpeType()).get(vo.getUnit()));
            }
            papers.setSpeciesList(specList);
        }
        return papers;
    }


    @Override
    public PageUtils<PaperPrintVo> listPrint(Map<String, Object> map, int pageNo, int pageSize) {
        Result<SysOrgAndRegionVo> sysOrgInfoForApproveAndQuery = null;
        try {
            sysOrgInfoForApproveAndQuery = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        } catch (Exception e) {
            throw new SofnException("获取当前用户的级别失败");
        }
        String sysOrgLevelId = sysOrgInfoForApproveAndQuery.getData().getSysOrgLevelId();

        if (OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            Result<SysOrgAndRegionVo> sys = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
            Map<String, Object> sysOrgRegionMap = sys.getData().getSysOrgRegionMap();
            String province = (String) sysOrgRegionMap.get("sysOrgProvince");
            map.put("province", province);
        }


        PageHelper.offsetPage(pageNo, pageSize);
        List<PaperPrintVo> printList = papersMapper.getPrintList(map);
        if (!CollectionUtils.isEmpty(printList)) {
            for (PaperPrintVo p :
                    printList) {
                Papers papers = papersMapper.selectById(p.getId());
                if (papers.getCompId() != null) {
                    TbCompVo combById = tbCompService.getCombById(papers.getCompId());
                    p.setCompAdd(combById.getRegionInCh() + combById.getContactAddress());
                }
            }
        }
        PageInfo<PaperPrintVo> pageInfo = new PageInfo<>(printList);
        return PageUtils.getPageUtils(pageInfo);
    }


    @Override
    public Papers print(String id) {
        //  papersMapper.printPaper(id);
        Papers papers1 = papersMapper.selectById(id);
        // 查出改证书所拥有的物种
        List<PapersSpecVo> bypapersId = papersSpecService.listForCondition(id);
        //  设置改证书所拥有的物种
        papers1.setSpeciesList(bypapersId);
        return papers1;
    }

    /**
     * @param map
     * @param pageNo
     * @param pageSize
     */
    @Override
    public PageUtils<LicenceVo> licence(Map<String, Object> map, int pageNo, int pageSize) {
        Result<SysOrgAndRegionVo> sysOrgInfoForApproveAndQuery = null;
        try {
            sysOrgInfoForApproveAndQuery = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        } catch (Exception e) {
            throw new SofnException("获取当前用户的级别失败");
        }
        String sysOrgLevelId = sysOrgInfoForApproveAndQuery.getData().getSysOrgLevelId();

        if (OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            Result<SysOrgAndRegionVo> sys = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
            Map<String, Object> sysOrgRegionMap = sys.getData().getSysOrgRegionMap();
            String province = (String) sysOrgRegionMap.get("sysOrgProvince");
            map.put("province", province);
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<LicenceVo> licence = papersMapper.getLicence(map);
        PageInfo<LicenceVo> pageInfo = new PageInfo<>(licence);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delLicence(String id, String papersType) {
        String proLevel = "";
        int i = 0;
        if ("4".equals(papersType)) {
            i = CaptureService.delCapture(id, proLevel);
        } else {
            i = delPapers(id);
        }

        return i;
    }

    /**
     * 通过企业名称获取最近一次证书中企业的信息
     * wuXy
     * 2020-11-4 10:56:59
     *
     * @param compName 企业名称
     * @return 企业对象
     */
    @Override
    public CompInRegisterVo getCompByCompName(String compName) {
        return papersMapper.getCompByCompName(compName);
    }

    @Override
    public void setPaperCache() {
        //TODO 新增修改删除都比较慢，暂时删除证书缓存
//        redisHelper.del(Constants.REDIS_KEY_ALL_PAPERS);
//        List<PapersCacheVo> cache = papersMapper.getCache();
//        //  如果证书编号为空
//        if (!CollectionUtils.isEmpty(cache)) {
//            redisHelper.set(Constants.REDIS_KEY_ALL_PAPERS, JsonUtils.obj2json(cache));
//        }

    }


    @Override
    public String getTodayMaxApplyNum(String todayStr) {
        return papersMapper.getTodayMaxApplyNum(todayStr);
    }

    @Override
    public String promptingExpire() {
        Date now = new Date();
        Date nextMoth = DateUtils.addDateMonths(now, 3);
        String[] keys = {"compId", "papersType", "nextMoth"};
        Object[] vals = {UserUtil.getLoginUserOrganizationId(), "1", nextMoth};
        PapersBindingVo papersBindingVo = papersMapper.lastMonthPrompting(MapUtil.getParams(keys, vals));
        if (Objects.nonNull(papersBindingVo)) {
            return papersBindingVo.getPapersNumber();
        }
        vals[1] = "3";
        PapersBindingVo papersBindingVo2 = papersMapper.lastMonthPrompting(MapUtil.getParams(keys, vals));
        if (Objects.nonNull(papersBindingVo2)) {
            return papersBindingVo2.getPapersNumber();
        }
        return null;
    }

    @Override
    public List<Papers> listEnablePapers() {
        QueryWrapper<Papers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DEL_FLAG", BoolUtils.N).eq("is_enable", "1").
                eq("comp_id", UserUtil.getLoginUserOrganizationId());
        return papersMapper.selectList(queryWrapper);
    }

    @Override
    public PapersVo paperInfo(String id) {
        Papers p = papersMapper.selectById(id);
        if (Objects.isNull(p))
            return null;
        String parStatus = p.getParStatus();
        if (!"4".equals(parStatus))
            return null;
        PapersVo pv = PapersVo.entity2Vo(papersMapper.selectById(id));
        TbCompVo tcv = tbCompService.getCombById(p.getCompId());
        if (Objects.nonNull(tcv)) {
            //替换企业类型字典
            Map<String, String> compTypeMap = sysRegionApi.getDictListByType("fdpi_comp_type").getData().
                    stream().collect(Collectors.toMap(SysDict::getDictcode, SysDict::getDictname));
            pv.setCompType(compTypeMap.get(tcv.getCompType()));
        }
        List<FileManage> list = fileManageService.listforPaper(id, "1");
        if (!CollectionUtils.isEmpty(list)) {
            pv.setFileList(list);
        }
        //设置改证书所拥有的物种
        pv.setSpeciesList(papersSpecService.listForCondition(id));
        String purpose = pv.getPurpose();
        pv.setPurposeName("1".equals(purpose) ? "物种保护" : "2".equals(purpose) ? "经营利用" : "3".equals(purpose)
                ? "人工繁育" : "4".equals(purpose) ? "科学研究" : "5".equals(purpose) ? "其他" : "");
        pv.setPapersTypeName(PapersType2Enum.getVal(pv.getPapersType()));
        return pv;
    }

    @Override
    public void downArtPaperTemplate(HttpServletResponse response) {
        String fileName = "人工繁育许可证导入模板.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("人工繁育许可证导入模板");
        XSSFRow row0 = sheet.createRow(0);
        row0.setHeight((short) (15 * 200));
        Cell cell0 = row0.createCell(0);
        cell0.setCellValue("备注（导入限制）若导入失败检查导入序号为N的数据是否满足以下条件：\n" +
                "1.有效日期要大于发证日期\n" +
                "2.导入物种必须在系统中存在\n" +
                "3.同一证书不能导入两个相同的物种\n" +
                "4.除（来源、数量外）其余字段均不为空 \n" +
                "5.一个证书导入多个物种时，新加一行数据（序号、物种学名与之前导入的证书物种不同即可，详情请看示例）\n" +
                "6.当为证书导入多物种时，导入的证书编号如果再本系统中已经用于证书绑定或进入绑定审核，那么导入时不能为此证书添加新物种\n" +
                "7.导入数据时，红字行不可删除");
        //合并需要的单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));
        XSSFCellStyle cellStyle0 = workbook.createCellStyle();
        //启动单元格内换行
        cellStyle0.setWrapText(true);
        Font font0 = workbook.createFont();
        font0.setColor(Font.COLOR_RED);
        cellStyle0.setFont(font0);
        //设置此单元格换行
        cell0.setCellStyle(cellStyle0);


        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("序号");
        row1.createCell(1).setCellValue("证书类型");
        row1.createCell(2).setCellValue("证书编号");
        row1.createCell(3).setCellValue("企业名称");
        row1.createCell(4).setCellValue("企业地址");
        row1.createCell(5).setCellValue("法人代表");
        row1.createCell(6).setCellValue("技术负责人");
        row1.createCell(7).setCellValue("人工繁育目的");
        row1.createCell(8).setCellValue("有效日期");
        row1.createCell(9).setCellValue("发证机关");
        row1.createCell(10).setCellValue("发证日期");
        row1.createCell(11).setCellValue("物种学名");
        row1.createCell(12).setCellValue("来源地");
        row1.createCell(13).setCellValue("方式");
        row1.createCell(14).setCellValue("数量");
        row1.createCell(15).setCellValue("单位");

        XSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font1 = workbook.createFont();
        font1.setColor((short) 9);
        cellStyle1.setFont(font1);
        for (int i = 0; i < 16; i++) {
            row1.getCell(i).setCellStyle(cellStyle1);
        }

        for (int i = 2; i <= 3; i++) {
            XSSFRow rowI = sheet.createRow(i);
            rowI.createCell(0).setCellValue(i - 1);
            rowI.createCell(1).setCellValue("人工繁育许可证");
            rowI.createCell(2).setCellValue("（国渔）水野驯繁字（2018）024号");
            rowI.createCell(3).setCellValue("腾讯");
            rowI.createCell(4).setCellValue("中国广东省深圳市南山区深南大道10000号腾讯大厦");
            rowI.createCell(5).setCellValue("张三");
            rowI.createCell(6).setCellValue("李四");
            rowI.createCell(7).setCellValue("人工繁育");
            rowI.createCell(8).setCellValue("2000/12/7");
            rowI.createCell(9).setCellValue("省渔业渔政主管部门");
            rowI.createCell(10).setCellValue("1980/12/7");
            rowI.createCell(11).setCellValue(i == 2 ? "海龟" : "海豹");
            rowI.createCell(12).setCellValue("中国");
            rowI.createCell(13).setCellValue("采购");
            rowI.createCell(14).setCellValue(10);
            rowI.createCell(15).setCellValue("只");
        }

        DataValidationHelper helper1 = sheet.getDataValidationHelper();
        String[] paperType = new String[]{"人工繁育许可证"};
        DataValidationConstraint constraint1 = helper1.createExplicitListConstraint(paperType);
        CellRangeAddressList addressList1 = null;
        DataValidation dataValidation1 = null;
        for (int i = 2; i < 3000; i++) {
            addressList1 = new CellRangeAddressList(i, i, 1, 1);
            dataValidation1 = helper1.createValidation(constraint1, addressList1);
            sheet.addValidationData(dataValidation1);
        }

        DataValidationHelper helper2 = sheet.getDataValidationHelper();
        String[] purposeType = new String[]{"人工繁育", "经营利用", "物种保护", "科学研究", "其他"};
        DataValidationConstraint constraint2 = helper2.createExplicitListConstraint(purposeType);
        CellRangeAddressList addressList2 = null;
        DataValidation dataValidation2 = null;
        for (int i = 2; i < 3000; i++) {
            addressList2 = new CellRangeAddressList(i, i, 7, 7);
            dataValidation2 = helper2.createValidation(constraint2, addressList2);
            sheet.addValidationData(dataValidation2);
        }

        for (int i = 0; i < 15; i++) {
            // 调整每一列宽度
            sheet.autoSizeColumn(i);
            // 解决自动设置列宽中文失效的问题
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }

        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''"
                    + URLEncoder.encode(fileName, "utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downManPaperTemplate(HttpServletResponse response, String paperType) {
        String fileName = paperType.equals(PapersType2Enum.MANAGEMENT_UTILIZATION.getKey())
                ? "经营许可证导入模板.xlsx" : "无证书备案导入模板.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(paperType.equals(PapersType2Enum.MANAGEMENT_UTILIZATION.getKey())
                ? "经营利用许可证导入模板" : "无证书备案导入模板");
        XSSFRow row0 = sheet.createRow(0);
        row0.setHeight(paperType.equals(PapersType2Enum.MANAGEMENT_UTILIZATION.getKey()) ? (short) (12 * 200) : (short) (15 * 200));
        Cell cell0 = row0.createCell(0);
        StringBuilder noted = new StringBuilder("备注（导入限制）若导入失败检查导入序号为N的数据是否满足以下条件：\n" +
                "1.有效日期要大于发证日期\n" +
                "2.导入物种必须在系统中存在\n" +
                "3.同一证书不能导入两个相同的物种\n" +
                "4.除（来源、数量外）其余字段均不为空 \n" +
                "5.一个证书导入多个物种时，新加一行数据（序号、物种学名与之前导入的证书物种不同即可，详情请看示例）\n" +
                "6.当为证书导入多物种时，导入的证书编号如果再本系统中已经用于证书绑定或进入绑定审核，那么导入时不能为此证书添加新物种\n");
        noted.append(paperType.equals(PapersType2Enum.MANAGEMENT_UTILIZATION.getKey())
                ? "7.导入数据时，红字行不可删除"
                : "7.编码规则：年份（2位）+省份（2位）+顺序码（6位），例如：2021 01 000001 。  省份代码： 01北京、02天津、03河北、04山西、05内蒙古、06辽宁、07吉林、08黑龙江、09上海、10江苏、11浙江、\n     12安徽、13福建、14江西、15山东、16河南、17湖北、18湖南、19广东、20广西、21海南、22重庆、23四川、24贵州、25云南、26西藏、27、陕西、28甘肃、29青海、30宁夏、31新疆\n8.导入数据时，红字行不可删除");
        cell0.setCellValue(noted.toString());
        //合并需要的单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));
        XSSFCellStyle cellStyle0 = workbook.createCellStyle();
        //启动单元格内换行
        cellStyle0.setWrapText(true);
        Font font0 = workbook.createFont();
        font0.setColor(Font.COLOR_RED);
        cellStyle0.setFont(font0);
        //设置此单元格换行
        cell0.setCellStyle(cellStyle0);


        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("序号");
        row1.createCell(1).setCellValue("证书类型");
        row1.createCell(2).setCellValue("证书编号");
        row1.createCell(3).setCellValue("企业名称");
        row1.createCell(4).setCellValue("企业地址");
        row1.createCell(5).setCellValue("法人代表");
        row1.createCell(6).setCellValue("经营方式");
        row1.createCell(7).setCellValue("销售去向");
        row1.createCell(8).setCellValue("有效日期");
        row1.createCell(9).setCellValue("发证日期");
        row1.createCell(10).setCellValue("发证机关");
        row1.createCell(11).setCellValue("物种学名");
        row1.createCell(12).setCellValue("来源地");
        row1.createCell(13).setCellValue("方式");
        row1.createCell(14).setCellValue("数量");
        row1.createCell(15).setCellValue("单位");

        XSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font1 = workbook.createFont();
        font1.setColor((short) 9);
        cellStyle1.setFont(font1);
        for (int i = 0; i < 16; i++) {
            row1.getCell(i).setCellStyle(cellStyle1);
        }

        for (int i = 2; i <= 3; i++) {
            XSSFRow rowI = sheet.createRow(i);
            rowI.createCell(0).setCellValue(i - 1);
            rowI.createCell(1).setCellValue(
                    paperType.equals(PapersType2Enum.MANAGEMENT_UTILIZATION.getKey()) ? "经营利用许可证" : "无证书备案");
            rowI.createCell(2).setCellValue(paperType.equals(
                    PapersType2Enum.MANAGEMENT_UTILIZATION.getKey()) ? "（国渔）水野驯经字（2011）001号" : "202101000001");
            rowI.createCell(3).setCellValue("腾讯");
            rowI.createCell(4).setCellValue("中国广东省深圳市南山区深南大道10000号腾讯大厦");
            rowI.createCell(5).setCellValue("张三");
            rowI.createCell(6).setCellValue("销售");
            rowI.createCell(7).setCellValue("北京");
            rowI.createCell(8).setCellValue("2000/12/7");
            rowI.createCell(9).setCellValue("1980/12/7");
            rowI.createCell(10).setCellValue("省渔业渔政主管部门");
            rowI.createCell(11).setCellValue(i == 2 ? "海狮" : "海豹");
            rowI.createCell(12).setCellValue("中国");
            rowI.createCell(13).setCellValue("采购");
            rowI.createCell(14).setCellValue(10);
            rowI.createCell(15).setCellValue("只");
        }

        DataValidationHelper helper1 = sheet.getDataValidationHelper();
        String[] paperTypes = paperType.equals(PapersType2Enum.MANAGEMENT_UTILIZATION.getKey()) ?
                new String[]{"经营利用许可证"} : new String[]{"无证书备案"};
        DataValidationConstraint constraint1 = helper1.createExplicitListConstraint(paperTypes);
        CellRangeAddressList addressList1 = null;
        DataValidation dataValidation1 = null;
        for (int i = 2; i < 3000; i++) {
            addressList1 = new CellRangeAddressList(i, i, 1, 1);
            dataValidation1 = helper1.createValidation(constraint1, addressList1);
            sheet.addValidationData(dataValidation1);
        }

        for (int i = 0; i < 15; i++) {
            // 调整每一列宽度
            sheet.autoSizeColumn(i);
            // 解决自动设置列宽中文失效的问题
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }

        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''"
                    + URLEncoder.encode(fileName, "utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPapersNumber() {
        String province = SysOwnOrgUtil.getOrgInfo().getRegionLastCode().substring(0, 2) + "0000";
        Result<String> result = sysRegionApi.getSysRegionName(province);
        if (StringUtils.isBlank(result.getData())) {
            throw new SofnException("未获取到当前用户省份信息");
        } else {
            String papersNumber = DateUtils.format(new Date(), "yyyy")
                    + CodeUtil.getProvinceCode(result.getData());
            String maxNumber = papersMapper.getMaxPaperNumber(papersNumber);
            if (StringUtils.isBlank(maxNumber)) {
                return papersNumber + "000001";
            }
            return new BigDecimal(maxNumber).add(new BigDecimal(1)).toString();
        }
    }

    @Override
    public List<PapersVo> getCurrentPapers(String compId) {
        QueryWrapper<Papers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("par_status", PapersBindingProcessEnum.PASS_DIRECTLY.getKey()).
                eq("del_flag", BoolUtils.N).eq("comp_id", compId).orderByDesc("update_time");
        List<Papers> papers = papersMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(papers)) {
            List<PapersVo> result = Lists.newArrayListWithCapacity(1);
            result.add(PapersVo.entity2Vo(papers.get(0)));
            return result;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @param id
     * @return void
     * @author wg
     * @description 证书变更撤回
     * @date 2021/4/8 16:22
     */
    @Override
    public void cancel(String id) {
        //拿着证书主键去数据库中查询详情
        QueryWrapper<Papers> wrapper = new QueryWrapper<>();
        wrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        Papers papers = papersMapper.selectOne(wrapper);
        //判断当前证书的状态,只有是已上报状态才能撤回
        if (!DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(papers.getParStatus())) {
            throw new SofnException("已上报状态才可以撤回");
        }
        //设置更新人与更新时间
        papers.preUpdate();
        //设置更新后的状态
        papers.setParStatus(DefaultAdviceEnum.BINDING_CANCEL.getCode());
        //执行更新
        int i = papersMapper.updateById(papers);
        if (i != 1) {
            throw new SofnException("撤回失败");
        }
        //获取当前组织机构
        Result<SysOrgAndRegionVo> result = SysOwnOrgUtil.getSysApproveLevelForPapersYearApprove();
        if (Result.CODE.equals(result.getCode())) {
            //获取当前组织机构级别成功
            SysOrgAndRegionVo data = result.getData();
            //上报
            AuditProcess auditProcess = convertAuditProcess(
                    papers.getId(), DefaultAdviceEnum.BINDING_CANCEL.getMsg(), papers.getUpdateTime(),
                    data.getOrgName(), "0", "cancel", papers.getApplyNum());
            auditProcessMapper.insert(auditProcess);
        }

        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            //操作工作流
            String[] keys = {"status", "person"};
            Object[] vals = {DefaultAdviceEnum.BINDING_CANCEL.getCode(), SysOwnOrgUtil.getOrganizationName()};
            workService.backWorkItem(BackWorkItemForm.getInstanceForm(
                    defId, idAttrName, id, "fdpi_paper_binding_report", MapUtil.getParams(keys, vals)));
        }

    }

    private Result<List<SysFileVo>> activationFile(String fileId) {
        SysFileManageForm sfmf = new SysFileManageForm();
        sfmf.setIds(fileId);
        sfmf.setSystemId(Constants.SYSTEM_ID);
        sfmf.setInterfaceNum("hidden");
        return sysRegionApi.activationFile(sfmf);
    }

    /**
     * 证书绑定申请中对象转化
     *
     * @param papersBindingForm 证书绑定申请表单对象
     * @param dateNow           物种id
     * @param status            状态：1：未上报；2：上报
     * @return Papers 对象
     */
    private Papers convertToPapers(PapersBindingForm papersBindingForm, Date
            dateNow, String status) {
        Papers papers = new Papers();
        //修改证书中的信息
        papers.setId(papersBindingForm.getPapersId());
        //0：省级新建；1：绑定未上报；2：上报；3：初审通过；4;初审退回；5：复审通过；6：复审退回
        papers.setParStatus(status);
        papers.setCompId(papersBindingForm.getCompId());
        //0：注册绑定；1：证书绑定
        papers.setSource("1");
        papers.setUpdateTime(dateNow);
        papers.setUpdateUserId(UserUtil.getLoginUserId());
        papers.setDelFlag("N");
        papers.setIsEnable("0");
        return papers;
    }

    /**
     * 组装证书审核流程操作记录
     *
     * @param papersId     证书编号
     * @param advice       意见
     * @param dateNow      时间
     * @param approveLevel 0:上报； 1:初审，2：复审
     * @param isApprove    1:审核，0：退回
     * @return AuditProcess
     */
    private AuditProcess convertAuditProcess(String papersId, String advice, Date dateNow, String personOrOrgName,
                                             String approveLevel, String isApprove, String applyNum) {
        AuditProcess auditProcess = new AuditProcess();
        //直属-初审
        auditProcess.setId(IdUtil.getUUId());
        auditProcess.setPapersId(papersId);
        auditProcess.setApplyNum(applyNum);
        if ("1".equals(approveLevel)) {
            //初审
            if ("1".equals(isApprove)) {
                //审核
                auditProcess.setAdvice(StringUtils.isBlank(advice) ? DefaultAdviceEnum.APPROVE_FIRST_DEFAULT_ADVICE.getMsg() : advice);
                auditProcess.setStatus(DefaultAdviceEnum.APPROVE_FIRST_DEFAULT_ADVICE.getCode());
            } else {
                //退回
                auditProcess.setAdvice(StringUtils.isBlank(advice) ? DefaultAdviceEnum.RETURN_FIRST_DEFAULT_ADVICE.getMsg() : advice);
                auditProcess.setStatus(DefaultAdviceEnum.RETURN_FIRST_DEFAULT_ADVICE.getCode());
            }

        } else if ("2".equals(approveLevel)) {
            //复审
            if ("1".equals(isApprove)) {
                //审核
                auditProcess.setAdvice(StringUtils.isBlank(advice) ? DefaultAdviceEnum.APPROVE_SECOND_DEFAULT_ADVICE.getMsg() : advice);
                auditProcess.setStatus(DefaultAdviceEnum.APPROVE_SECOND_DEFAULT_ADVICE.getCode());
            } else {
                //退回
                auditProcess.setAdvice(StringUtils.isBlank(advice) ? DefaultAdviceEnum.RETURN_SECOND_DEFAULT_ADVICE.getMsg() : advice);
                auditProcess.setStatus(DefaultAdviceEnum.RETURN_SECOND_DEFAULT_ADVICE.getCode());
            }

        } else {
            if ("cancel".equals(isApprove)) {
                auditProcess.setAdvice(StringUtils.isBlank(advice) ? DefaultAdviceEnum.BINDING_CANCEL.getMsg() : advice);
                auditProcess.setStatus(DefaultAdviceEnum.BINDING_CANCEL.getCode());
            } else {
                //上报
                auditProcess.setAdvice(StringUtils.isBlank(advice) ? DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg() : advice);
                auditProcess.setStatus(DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode());
            }
        }
        auditProcess.setPerson(UserUtil.getLoginUserId());
        auditProcess.setPersonName(personOrOrgName);
        auditProcess.setConTime(dateNow);
        return auditProcess;
    }

    /**
     * 组装文件列表，证书文件列表
     * wuXY
     *
     * @param papersId   证书编号
     * @param fileVoList 上传的文件列表
     * @return List<FileManage>
     */
    private List<FileManage> assembleFileManage(String papersId, List<FileManageVo> fileVoList) {
        List<FileManage> fileList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(fileVoList)) {
            return fileList;
        } else {
            fileVoList.forEach(fileManageVo -> {
                FileManage fileManage = fileManageVo.getFileManage(fileManageVo);
                fileManage.setFileSource("PAPERS");
                fileManage.setFileSourceId(papersId);
                fileManage.setFileStatus("1");
                fileList.add(fileManage);
            });
            return fileList;
        }
    }

    /**
     * 组装调用支撑平台
     *
     * @param compModel 企业机构信息
     * @return SysThirdOrgForm
     */
    private SysThirdOrgForm assembleSysThirdOrgForm(TbCompVo compModel) {
        SysThirdOrgForm sysThirdOrgForm = new SysThirdOrgForm();
        sysThirdOrgForm.setAppId(Constants.SYSTEM_ID);
        sysThirdOrgForm.setOrganizationName(compModel.getCompName());
        sysThirdOrgForm.setPrincipal(compModel.getLinkMan());
        sysThirdOrgForm.setPhone(compModel.getPhone());
        String compDistrict = compModel.getCompDistrict();
        if (StringUtils.isBlank(compDistrict)) {
            throw new SofnException("未获取到该区域信息");
        }
        if (compDistrict.length() > 6) {
            Region region = regionService.getById(compDistrict);
            if (Objects.isNull(region)) {
                throw new SofnException("支撑平台未获取到该区域信息");
            } else {
                sysThirdOrgForm.setAddress(region.getRegionCode());//组织机构[省，市，区]
            }
        } else {
            sysThirdOrgForm.setAddress(compDistrict);
        }
        return sysThirdOrgForm;
    }

    /**
     * 组装调用支撑平台创建用户接口用户对象
     *
     * @param account   账号
     * @param password  密码
     * @param SysOrgId  支撑凭他机构id
     * @param compModel 企业对象
     * @return SysUserRoleCodeForm
     */
    private SysUserRoleCodeForm assembleSysUserRoleCodeForm(String account, String
            password, String SysOrgId, TbCompVo compModel) {
        SysUserRoleCodeForm sysUserRoleCodeForm = new SysUserRoleCodeForm();
        sysUserRoleCodeForm.setAppId("fdpi");
        sysUserRoleCodeForm.setUsername(account);
        sysUserRoleCodeForm.setNickname(compModel.getLinkMan());
        sysUserRoleCodeForm.setMobile(compModel.getPhone());
        sysUserRoleCodeForm.setInitPassword(password);
        sysUserRoleCodeForm.setEmail(compModel.getEmail());
        sysUserRoleCodeForm.setOrganizationId(SysOrgId);
        sysUserRoleCodeForm.setOrganizationName(compModel.getCompName());
        sysUserRoleCodeForm.setRoleCodes(Constants.COMP_USER_ROLE_CODE);
        sysUserRoleCodeForm.setSex(3);
        sysUserRoleCodeForm.setStatus("Y");
        sysUserRoleCodeForm.setRemark("企业用户");
        return sysUserRoleCodeForm;
    }

    //组装修改用户的Map信息
    private Map<String, Object> assembleUserMap(Map<String, Object> userMap, Date dateNow, String returnSysOrgId, String returnSysUserId, String userIdInDb) {
        userMap.put("userStatus", "1");
        userMap.put("updateTime", dateNow);
        userMap.put("updateUserId", UserUtil.getLoginUserId());
        userMap.put("sysCompId", returnSysOrgId);
        userMap.put("sysUserId", returnSysUserId);
        userMap.put("delFlag", "N");
        userMap.put("id", userIdInDb);
        return userMap;
    }

    /**
     * 组装企业Map信息，绑定审核时进行修改
     *
     * @param isUpdateDirectOrgLevel 是否需要修改审核级别
     * @param status                 状态
     * @param dateNow                当前时间
     * @param returnSysOrgId         注册的企业机构id
     * @param compIdInDb             当前企业id
     * @param directOrgLevel         直属机构级别
     * @return map
     */
    private Map<String, Object> assembleCompMapForUpdate(boolean isUpdateDirectOrgLevel, String status, Date dateNow, String returnSysOrgId, String compIdInDb, String directOrgLevel) {
        Map<String, Object> compMap = Maps.newHashMap();
        compMap.put("compStatus", status);
        compMap.put("updateTime", dateNow);
        compMap.put("updateUserId", UserUtil.getLoginUserId());
        compMap.put("id", compIdInDb);
        //compMap.put("directOrgId",deptLevelModel.getSysDeptId());
        if (isUpdateDirectOrgLevel) {
            compMap.put("directOrgLevel", directOrgLevel);
            compMap.put("compId", returnSysOrgId);
        } else {
            compMap.put("delFlag", "Y");
        }
        //查询所有通过的企业的的数量
        String num = tbCompService.getCompCount("1") + 1 + "";
        String compCode = num;
        for (int i = num.length(); i < 4; i++) {
            compCode = "0" + compCode;
        }
        compMap.put("compCode", compCode);
        return compMap;
    }

    /**
     * 上报 或 通过
     *
     * @param papersId   证书id
     * @param status     状态
     * @param advice     审核意见
     * @param personName 操作人员
     * @return
     */
    private SubmitInstanceVo submitInstanceVoProcess(String papersId, String status, String advice, String personName) {

        SubmitInstanceVo submitInstanceVo = new SubmitInstanceVo();
        submitInstanceVo.setDefId(defId);
        submitInstanceVo.setIdAttrName(idAttrName);
        submitInstanceVo.setIdAttrValue(papersId);
        String[] keys = {"status", "advice", "personName"};
        String[] values = {status, advice, personName};
        Map<String, Object> params = MapUtil.getParams(keys, values);
        submitInstanceVo.setParams(params);
        return submitInstanceVo;
    }

    /**
     * 驳回
     *
     * @param papersId
     * @param status
     * @param advice
     * @param personName
     * @return
     */
    private BackWorkItemForm backWorkItemFormProcess(String papersId, String status, String advice, String personName) {
        String[] keys = {"status", "advice", "personName"};
        String[] values = {status, advice, personName};
        Map<String, Object> params = MapUtil.getParams(keys, values);

        return BackWorkItemForm.getInstanceForm(defId, idAttrName, papersId, "fdpi_paper_binding_report", params);
    }

    /**
     * 邮件发送
     *
     * @param email
     * @param content
     */
    public void sendEmail(String email, String content) {

        try {
            final String subject = "国家重点保护水生野生动物标识及信息管理子系统-账号审核";
            emailService.sendHtmlMail(email, subject, content);
        } catch (MessagingException e) {
            throw new SofnException("邮件发送失败");
        }

    }

    /**
     * @param speid 物种主键
     * @return java.lang.Boolean true证书没过期可用,false证书过期了不可用
     * @author wg
     * @description 根据物种主键判断所存在该物种的证书是否过期
     * @date 2021/4/13 14:40
     */
    public Boolean assertOverdueBySpeId(String speid) {
        //得到当前企业的id
        String copmId = UserUtil.getLoginUserOrganizationId();
        //通过企业id拿到全部有效的证书编号
        QueryWrapper<Papers> wrapper = new QueryWrapper<>();
        wrapper.eq("comp_id", copmId);
        wrapper.eq("is_enable", "1");
        wrapper.gt("data_clos", new Date());
        wrapper.select("id");
        List<Papers> papers = papersMapper.selectList(wrapper);
        //如果证书都过期了
        if (papers == null || papers.size() == 0) return false;
        //遍历有效证书
        for (Papers paper : papers) {
            //通过证书id，获取物种列表
            List<PapersSpecVo> vos = papersSpecService.listForCondition(paper.getId());
            //如果这个证书一个有效物种都没有直接跳出当次循环
            if (vos == null || vos.size() == 0) continue;
            //遍历判断
            for (PapersSpecVo vo : vos) {
                if (Objects.equals(vo.getSpecId(), speid)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void exportArt(Map<String, Object> params, HttpServletResponse response) {
        String fileName = "人工繁育许可证信息.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("人工繁育许可证信息");
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("序号");
        row0.createCell(1).setCellValue("证书类型");
        row0.createCell(2).setCellValue("证书编号");
        row0.createCell(3).setCellValue("企业名称");
        row0.createCell(4).setCellValue("企业地址");
        row0.createCell(5).setCellValue("法人代表");
        row0.createCell(6).setCellValue("技术负责人");
        row0.createCell(7).setCellValue("人工繁育目的");
        row0.createCell(8).setCellValue("有效日期");
        row0.createCell(9).setCellValue("发证机关");
        row0.createCell(10).setCellValue("发证日期");
        row0.createCell(11).setCellValue("物种学名");
        row0.createCell(12).setCellValue("来源地");
        row0.createCell(13).setCellValue("方式");
        row0.createCell(14).setCellValue("数量");
        row0.createCell(15).setCellValue("单位");

        XSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font1 = workbook.createFont();
        font1.setColor((short) 9);
        cellStyle1.setFont(font1);
        for (int i = 0; i < 16; i++) {
            row0.getCell(i).setCellStyle(cellStyle1);
        }
        //获取数据
        this.perfectRegionParam(params);
        params.put("papersType", PapersType2Enum.ARTIFICIAL_BREEDING.getKey());
        List<ExporrtPapers> list = papersMapper.exportPapers(params);
        int size = list.size();
        for (int i = 1; i <= size; i++) {
            ExporrtPapers ep = list.get(i - 1);
            XSSFRow rowI = sheet.createRow(i);
            rowI.createCell(0).setCellValue(i);
            rowI.createCell(1).setCellValue(PapersType2Enum.getVal(ep.getPapersType()));
            rowI.createCell(2).setCellValue(ep.getPapersNumber());
            rowI.createCell(3).setCellValue(ep.getCompName());
            rowI.createCell(4).setCellValue(ep.getCompAddress());
            rowI.createCell(5).setCellValue(ep.getLegal());
            rowI.createCell(6).setCellValue(ep.getTechnicalDirector());
            rowI.createCell(7).setCellValue(ep.getPurpose());
            rowI.createCell(8).setCellValue(DateUtils.format(ep.getDataClos(), "yyyy/MM/dd"));
            rowI.createCell(9).setCellValue(ep.getIssueUnit());
            rowI.createCell(10).setCellValue(DateUtils.format(ep.getIssueDate(), "yyyy/MM/dd"));
            rowI.createCell(11).setCellValue(ep.getSpeName());
            rowI.createCell(12).setCellValue(ep.getSource());
            rowI.createCell(13).setCellValue(ep.getMode());
            rowI.createCell(14).setCellValue(Objects.isNull(ep.getAmount()) ? 0 : ep.getAmount());
            this.initUnitMap();
            String speType = ep.getSpeType();
            String unit = ep.getUnit();
            if (StringUtils.isNotBlank(unit) && StringUtils.isNotBlank(speType)) {
                rowI.createCell(15).setCellValue(this.getUnitName(speType, unit));
            }

        }
        for (int i = 0; i < 15; i++) {
            // 调整每一列宽度
            sheet.autoSizeColumn(i);
            // 解决自动设置列宽中文失效的问题
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''"
                    + URLEncoder.encode(fileName, "utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUnitName(String speType, String unit) {
        Map<String, String> map = unitMap.get(speType);
        if (!CollectionUtils.isEmpty(map)) {
            return unitMap.get(speType).get(unit);
        }
        return "";
    }

    @Override
    public void exportMan(Map<String, Object> params, HttpServletResponse response) {
        String fileName = "经营许可证信息.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("经营许可证信息");
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("序号");
        row0.createCell(1).setCellValue("证书类型");
        row0.createCell(2).setCellValue("证书编号");
        row0.createCell(3).setCellValue("企业名称");
        row0.createCell(4).setCellValue("企业地址");
        row0.createCell(5).setCellValue("法人代表");
        row0.createCell(6).setCellValue("经营方式");
        row0.createCell(7).setCellValue("销售去向");
        row0.createCell(8).setCellValue("有效日期");
        row0.createCell(9).setCellValue("发证日期");
        row0.createCell(10).setCellValue("发证机关");
        row0.createCell(11).setCellValue("物种学名");
        row0.createCell(12).setCellValue("来源地");
        row0.createCell(13).setCellValue("方式");
        row0.createCell(14).setCellValue("数量");
        row0.createCell(15).setCellValue("单位");

        XSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font1 = workbook.createFont();
        font1.setColor((short) 9);
        cellStyle1.setFont(font1);
        for (int i = 0; i < 16; i++) {
            row0.getCell(i).setCellStyle(cellStyle1);
        }
        //获取数据
        this.perfectRegionParam(params);
        List<ExporrtPapers> list = papersMapper.exportPapers(params);
        int size = list.size();
        for (int i = 1; i <= size; i++) {
            ExporrtPapers ep = list.get(i - 1);
            XSSFRow rowI = sheet.createRow(i);
            rowI.createCell(0).setCellValue(i);
            rowI.createCell(1).setCellValue(PapersType2Enum.getVal(ep.getPapersType()));
            rowI.createCell(2).setCellValue(ep.getPapersNumber());
            rowI.createCell(3).setCellValue(ep.getCompName());
            rowI.createCell(4).setCellValue(ep.getCompAddress());
            rowI.createCell(5).setCellValue(ep.getLegal());
            rowI.createCell(6).setCellValue(ep.getModeOperation());
            rowI.createCell(7).setCellValue(ep.getSalesDestination());
            rowI.createCell(8).setCellValue(DateUtils.format(ep.getDataClos(), "yyyy/MM/dd"));
            rowI.createCell(9).setCellValue(DateUtils.format(ep.getIssueDate(), "yyyy/MM/dd"));
            rowI.createCell(10).setCellValue(ep.getIssueUnit());
            rowI.createCell(11).setCellValue(ep.getSpeName());
            rowI.createCell(12).setCellValue(ep.getSource());
            rowI.createCell(13).setCellValue(ep.getMode());
            rowI.createCell(14).setCellValue(Objects.isNull(ep.getAmount()) ? 0 : ep.getAmount());
            this.initUnitMap();
            String speType = ep.getSpeType();
            String unit = ep.getUnit();
            if (StringUtils.isNotBlank(unit) && StringUtils.isNotBlank(speType)) {
                rowI.createCell(15).setCellValue(this.getUnitName(speType, unit));
            }
        }
        for (int i = 0; i < 14; i++) {
            // 调整每一列宽度
            sheet.autoSizeColumn(i);
            // 解决自动设置列宽中文失效的问题
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''"
                    + URLEncoder.encode(fileName, "utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Papers> listPaperByCompId(String compId) {
        QueryWrapper<Papers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return papersMapper.selectList(queryWrapper);
    }

    @Override
    public int delByCompId(String compId) {
        QueryWrapper<Papers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return papersMapper.delete(queryWrapper);
    }

    @Override
    public List<SelectVo> getUnitsByType(String speType) {
        String className = speType.replaceAll("fdpi_", "").replaceAll("_unit", "") + "_Enum";
        className = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, className);
        try {
            Class clazz = Class.forName("com.sofn.fdpi.enums.speUnit." + className);
            Method method = clazz.getMethod("getSelect");
            return (List<SelectVo>) method.invoke(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    private void perfectRegionParam(Map<String, Object> params) {
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        String organizationLevel = orgInfo.getOrganizationLevel();
        if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)) {
            params.put("province", orgInfo.getRegionLastCode());
        } else if (Constants.REGION_TYPE_CITY.equals(organizationLevel)) {
            params.put("city", orgInfo.getRegionLastCode());
        }
        if (Constants.REGION_TYPE_COUNTY.equals(organizationLevel)) {
            params.put("country", orgInfo.getRegionLastCode());
        }
    }
}
