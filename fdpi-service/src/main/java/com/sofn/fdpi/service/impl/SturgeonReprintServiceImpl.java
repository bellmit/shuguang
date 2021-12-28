package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.CitesEnum;
import com.sofn.fdpi.enums.CodeTypeEnum;
import com.sofn.fdpi.enums.SturgeonPaperEnum;
import com.sofn.fdpi.enums.SturgeonStatusEnum;
import com.sofn.fdpi.mapper.SturgeonReprintMapper;
import com.sofn.fdpi.model.SturgeonProcess;
import com.sofn.fdpi.model.SturgeonReprint;
import com.sofn.fdpi.model.SturgeonSignboardDomestic;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.bean.BackWorkItemForm;
import com.sofn.fdpi.sysapi.bean.SubmitInstanceVo;
import com.sofn.fdpi.util.*;
import com.sofn.fdpi.vo.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

@Service(value = "sturgeonReprintService")
public class SturgeonReprintServiceImpl extends BaseService<SturgeonReprintMapper, SturgeonReprint> implements SturgeonReprintService {

    private final String DEF_ID = "fdpi:cites_reprint";

    private final String ID_ATTR_NAME = "dataId";

    @Resource
    private SturgeonReprintMapper sturgeonReprintMapper;
    @Resource
    @Lazy
    private TbCompService tbCompService;
    @Resource
    private SturgeonSignboardService sturgeonSignboardService;
    @Resource
    private SturgeonReprintListService sturgeonReprintListService;
    @Resource
    @Lazy
    private SignboardPrintListService signboardPrintListService;
    @Resource
    private SturgeonSignboardDomesticService ssdService;
    @Resource
    private SturgeonProcessService sturgeonProcessService;
    @Resource
    private FileUtil fileUtil;
    @Resource
    private WorkService workService;

    @Override
    @Transactional
    public SturgeonReprintVo save(SturgeonReprintForm form) {
        RedisUserUtil.validReSubmit("fdpi_save");
        String status = form.getStatus();
        if (!SturgeonStatusEnum.KEEP.getKey().equals(status) && !SturgeonStatusEnum.REPORT.getKey().equals(status)) {
            throw new SofnException("新增状态只能为1保存2上报,请检查");
        }
        List<String> signboardIds = form.getSignboardIds();
        SturgeonReprint entity = new SturgeonReprint();
        BeanUtils.copyProperties(form, entity);
        entity.preInsert();
        entity.setCompId(UserUtil.getLoginUserOrganizationId());
        String applyType = Objects.isNull(form.getApplyType()) ? "1" : form.getApplyType();
        entity.setApplyType(applyType);
        if (SturgeonStatusEnum.REPORT.getKey().equals(status)) {
            entity.setApplyTime(entity.getCreateTime());
            String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(
                    UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
            String codeType = "2".equals(applyType) ?
                    CodeTypeEnum.CITES_REPRINT.getKey() : CodeTypeEnum.STURGEON_REPRINT.getKey();
            entity.setApplyCode(CodeUtil.getApplyCode(provinceCode, codeType, this.getSequenceNum(provinceCode)));
            if (Constants.WORKFLOW.equals(BoolUtils.N)) {
                SturgeonProcess sturgeonProcess = new SturgeonProcess();
                sturgeonProcess.setApplyId(entity.getId());
                sturgeonProcess.setStatus(status);
                sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
            } else {
                String[] keys = {"status", "person"};
                Object[] vals = {status, SysOwnOrgUtil.getOrganizationName()};
                workService.startChainProcess(
                        new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, entity.getId(), MapUtil.getParams(keys, vals)));
            }
        }

        if (!CollectionUtils.isEmpty(signboardIds)) {
            sturgeonReprintListService.saveOrUpdate(entity.getId(), signboardIds);
        }
        SturgeonReprintVo sturgeonReprintVo = SturgeonReprintVo.entity2Vo(entity);
        //激活文件
        this.activationFile("", "", form.getFileId(), form.getImgIds());
        sturgeonReprintMapper.insert(entity);
        return sturgeonReprintVo;
    }

    private String getSequenceNum(String provinceCode) {
        String maxApplyNum = sturgeonReprintMapper.getTodayMaxApplyNum(
                DateUtils.format(new Date(), "yyyyMMdd") + "0" + provinceCode);
        return StringUtils.hasText(maxApplyNum) ?
                String.format("%06d", (Integer.valueOf(maxApplyNum.substring(13)) + 1)) : null;

    }

    /**
     * 激活文件
     */
    private void activationFile(String oldFileId, String oldImgIds, String newFileId, String newImgIds) {
        List<String> oldIds = this.getIds(oldFileId, oldImgIds);
        List<String> newIds = this.getIds(newFileId, newImgIds);
        oldIds.removeAll(newIds);
        fileUtil.activationFile(String.join(",", newIds));
        if (!CollectionUtils.isEmpty(oldIds)) {
            fileUtil.batchDeleteFile(String.join(",", oldIds));
        }
    }

    //拼接id
    private List<String> getIds(String fileId, String imgIds) {
        List<String> ids = Lists.newArrayList();
        if (StringUtils.hasText(fileId)) {
            ids.add(fileId);
        }
        if (StringUtils.hasText(imgIds)) {
            ids.addAll(Arrays.asList(imgIds.split(",")));
        }
        return ids;
    }

    @Override
    @Transactional
    public void delete(String id) {
        QueryWrapper<SturgeonReprint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonReprint entity = sturgeonReprintMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待删除的数据不存在");
        }
        entity.preUpdate();
        entity.setDelFlag(BoolUtils.Y);
        sturgeonReprintMapper.updateById(entity);
    }

    @Override
    @Transactional
    public void update(SturgeonReprintForm form) {
        String status = form.getStatus();
        if (!SturgeonStatusEnum.KEEP.getKey().equals(status) && !SturgeonStatusEnum.REPORT.getKey().equals(status)) {
            throw new SofnException("修改状态只能为1保存2上报,请检查");
        }
        String id = form.getId();
        QueryWrapper<SturgeonReprint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonReprint entity = sturgeonReprintMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待修改数据不存在!");
        }
        List<String> signboardIds = form.getSignboardIds();
        if (!CollectionUtils.isEmpty(signboardIds)) {
            sturgeonReprintListService.saveOrUpdate(entity.getId(), signboardIds);
        }
        //激活文件
        String oldFileId = entity.getFileId();
        String oldImgIds = entity.getImgIds();
        this.activationFile(StringUtils.hasText(oldFileId) ? oldFileId : "",
                StringUtils.hasText(oldImgIds) ? oldImgIds : "", form.getFileId(), form.getImgIds());
        BeanUtils.copyProperties(form, entity);
        entity.preUpdate();
        if (SturgeonStatusEnum.REPORT.getKey().equals(status)) {
            entity.setApplyTime(entity.getCreateTime());
            if (!StringUtils.hasText(entity.getApplyCode())) {
                String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(
                        UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
                String codeType = "1".equals(entity.getApplyType()) ?
                        CodeTypeEnum.STURGEON_REPRINT.getKey() : CodeTypeEnum.CITES_REPRINT.getKey();
                entity.setApplyCode(CodeUtil.getApplyCode(provinceCode, codeType, this.getSequenceNum(provinceCode)));
            }
            if (Constants.WORKFLOW.equals(BoolUtils.N)) {
                SturgeonProcess sturgeonProcess = new SturgeonProcess();
                sturgeonProcess.setApplyId(entity.getId());
                sturgeonProcess.setStatus(status);
                sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
            } else {
                String[] keys = {"status", "person"};
                Object[] vals = {status, SysOwnOrgUtil.getOrganizationName()};
                workService.startChainProcess(
                        new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, entity.getId(), MapUtil.getParams(keys, vals)));
            }
        }
        sturgeonReprintMapper.updateById(entity);
    }

    @Override
    public SturgeonReprintVo get(String id) {
        QueryWrapper<SturgeonReprint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonReprintVo sturgeonReprintVo = SturgeonReprintVo.entity2Vo(sturgeonReprintMapper.selectOne(queryWrapper));
        if (Objects.nonNull(sturgeonReprintVo)) {
            sturgeonReprintVo.setCompName(tbCompService.getCombById(sturgeonReprintVo.getCompId()).getCompName());
            if ("2".equals(sturgeonReprintVo.getApplyType())) {
                sturgeonReprintVo.setSignboardIds(sturgeonReprintListService.listByReprintId(id));
            }
        }
        return sturgeonReprintVo;
    }

    @Override
    @Transactional
    public void auditPass(String id, String thirdPrint) {
        this.audit(id, SturgeonStatusEnum.PASS.getKey(), thirdPrint, null);
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            String[] keys = {"status", "person"};
            Object[] vals = {SturgeonStatusEnum.PASS.getKey(), SysOwnOrgUtil.getOrganizationName()};
            workService.completeWorkItem(new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, id, MapUtil.getParams(keys, vals)));
        }
    }

    @Override
    @Transactional
    public void auditReturn(String id, String opinion) {
        this.audit(id, SturgeonStatusEnum.RETURN.getKey(), null, opinion);
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            String[] keys = {"status", "opinion", "person"};
            Object[] vals = {SturgeonStatusEnum.RETURN.getKey(), opinion, SysOwnOrgUtil.getOrganizationName()};
            workService.backWorkItem(BackWorkItemForm.getInstanceForm(
                    DEF_ID, ID_ATTR_NAME, id, "report", MapUtil.getParams(keys, vals)));
        }
    }


    @Override
    public PageUtils<SturgeonReprintVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        this.perfectParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<SturgeonReprint> sturgeonReprints = sturgeonReprintMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(sturgeonReprints)) {
            PageInfo<SturgeonReprint> sturgeonReprintPageInfo = new PageInfo<>(sturgeonReprints);
            List<SturgeonReprintVo> sturgeonVos = Lists.newArrayListWithCapacity(sturgeonReprints.size());
            for (SturgeonReprint sturgeonReprint : sturgeonReprints) {
                SturgeonReprintVo vo = SturgeonReprintVo.entity2Vo(sturgeonReprint);
                //判断是否显示撤回按钮
                if (params.containsKey("compId") && SturgeonStatusEnum.REPORT.getKey().equals(sturgeonReprint.getStatus())) {
                    vo.setIsShowCancel(true);
                }
                sturgeonVos.add(vo);
            }
            PageInfo<SturgeonReprintVo> sturgeonReprintVoPageInfo = new PageInfo<>(sturgeonVos);
            sturgeonReprintVoPageInfo.setPageSize(pageSize);
            sturgeonReprintVoPageInfo.setTotal(sturgeonReprintPageInfo.getTotal());
            sturgeonReprintVoPageInfo.setPageNum(sturgeonReprintPageInfo.getPageNum());
            return PageUtils.getPageUtils(sturgeonReprintVoPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(sturgeonReprints));
    }

    public static void perfectParams(Map<String, Object> params) {
        List<String> roles = UserUtil.getLoginUserRoleCodeList();
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        String organizationLevel = orgInfo.getOrganizationLevel();
        if (CollectionUtils.isEmpty(roles)) {
            throw new SofnException("未获取到该用户角色");
        }
        if (Objects.isNull(params.get("applyType"))) {
            params.put("applyType", "1");
        }
        if (roles.contains(Constants.PRINT_USER_ROLE_CODE)) {
            params.put("isPrint", BoolUtils.Y);
        } else if (roles.contains(Constants.COMP_USER_ROLE_CODE)) {
            params.put("compId", UserUtil.getLoginUserOrganizationId());
        } else if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel)) {
            params.put("isMinistry" + params.get("applyType"), BoolUtils.Y);
        } else {
            params.put("direclyId", UserUtil.getLoginUserOrganizationId());
        }

    }

    @Override
    @Transactional
    public void report(String id) {
        UpdateWrapper<SturgeonReprint> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonReprint entity = new SturgeonReprint();
        entity.setApplyTime(new Date());
        if (Objects.isNull(entity.getApplyCode())) {
            String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
            String codeType = "2".equals(entity.getApplyType()) ?
                    CodeTypeEnum.CITES_REPRINT.getKey() : CodeTypeEnum.STURGEON_REPRINT.getKey();
            entity.setApplyCode(CodeUtil.getApplyCode(provinceCode, codeType, this.getSequenceNum(provinceCode)));
        }
        entity.setStatus(SturgeonStatusEnum.REPORT.getKey());
        sturgeonReprintMapper.update(entity, updateWrapper);
        if (Constants.WORKFLOW.equals(BoolUtils.N)) {
            //增加上报流程
            SturgeonProcess sturgeonProcess = new SturgeonProcess();
            sturgeonProcess.setApplyId(id);
            sturgeonProcess.setStatus(SturgeonStatusEnum.REPORT.getKey());
            sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
        } else {
            String[] keys = {"status", "person"};
            Object[] vals = {SturgeonStatusEnum.REPORT.getKey(), SysOwnOrgUtil.getOrganizationName()};
            workService.startChainProcess(
                    new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, id, MapUtil.getParams(keys, vals)));
        }
    }

    @Override
    public List<SturgeonProcessVo> listSturgeonProcess(String applyId) {
        SturgeonReprintVo srv = this.get(applyId);
        if (Objects.isNull(srv.getApplyTime())) {
            return Collections.EMPTY_LIST;
        }
        List<Map<String, Object>> list = WorkUtil.getProcesslist(DEF_ID, ID_ATTR_NAME, applyId);
        List<SturgeonProcessVo> sturgeonProcessVos = Lists.newArrayListWithCapacity(list.size());
        for (Map map : list) {
            SturgeonProcessVo vo = SturgeonProcessVo.map2Vo(map);
            vo.setAdvice(Objects.isNull(map.get("opinion")) ? null : map.get("opinion").toString());
            sturgeonProcessVos.add(vo);
        }
        return sturgeonProcessVos;
    }

    /**
     * @param id 主键
     * @return void
     * @description 标识补打申请撤回
     * @date 2021/4/1 18:31
     */
    @Override
    public void cancel(String id) {
        QueryWrapper<SturgeonReprint> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        //查询当前标识补打信息
        SturgeonReprint sturgeonReprint = sturgeonReprintMapper.selectOne(wrapper);
        //非空校验
        if (sturgeonReprint == null) {
            throw new SofnException("没有找到相关数据");
        }
        //如果当前是已上报才可以撤回
        if (sturgeonReprint.getStatus() != null && !sturgeonReprint.getStatus().equals(SturgeonStatusEnum.REPORT.getKey())) {
            throw new SofnException("只有已上报状态才可以撤回");
        }
        //设置当前操作人、操作时间
        sturgeonReprint.preUpdate();
        //设置当前标签纸的状态
        sturgeonReprint.setStatus(SturgeonStatusEnum.CANCEL.getKey());
        //执行更新
        int i = sturgeonReprintMapper.updateById(sturgeonReprint);
        if (i != 1) {
            throw new SofnException("撤回失败");
        }
        //操作工作流
        String[] keys = {"status", "person"};
        Object[] vals = {SturgeonStatusEnum.CANCEL.getKey(), SysOwnOrgUtil.getOrganizationName()};
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            workService.backWorkItem(BackWorkItemForm.getInstanceForm(
                    DEF_ID, ID_ATTR_NAME, id, "report", MapUtil.getParams(keys, vals)));
        }
    }

    @Override
    public List<SturgeonSignboardVo> listRepring(String compId, List<String> signboardIds) {
        List<SturgeonSignboardDomestic> ssds = ssdService.listRepring(compId, signboardIds, BoolUtils.N);
        if (!CollectionUtils.isEmpty(ssds)) {
            List<SturgeonSignboardVo> result = Lists.newArrayListWithCapacity(ssds.size());
            for (SturgeonSignboardDomestic ssd : ssds) {
                result.add(SturgeonSignboardVo.entity2Vo(ssd));
            }
            return result;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    @Transactional
    public void print(String id) {
        List<String> signboardIds = sturgeonReprintListService.listByReprintId(id);
        ssdService.updatePirngStatusBySturgeonIds(signboardIds, null, BoolUtils.Y);
        QueryWrapper<SturgeonReprint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        SturgeonReprint entity = sturgeonReprintMapper.selectOne(queryWrapper);
        entity.preInsert();
        entity.setStatus(SturgeonPaperEnum.PRINT.getKey());
        sturgeonReprintMapper.updateById(entity);
        //添加流程数据
        SturgeonProcess sturgeonProcess = new SturgeonProcess();
        sturgeonProcess.setApplyId(entity.getId());
        sturgeonProcess.setStatus(SturgeonPaperEnum.PRINT.getKey());
        sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);

    }


    @Override
    public void export(String id, HttpServletResponse response) {
        QueryWrapper<SturgeonReprint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonReprint entity = sturgeonReprintMapper.selectOne(queryWrapper);
        String compId = entity.getCompId();
        TbCompVo tbCompVo = tbCompService.getCombById(compId);
        String contractNum = entity.getContractNum();
        Date makeTime = entity.getMakeTime();
        //当前日期字符串
        Date now = new Date();
        String typeCode = "Zp";
        if (Objects.isNull(makeTime)) {
            contractNum = "Ht-" + DateUtils.format(now, DateUtils.DATE_PATTERN).substring(2, 4) + "-";
            String sequenceNum = this.getContractSequenceNum(contractNum);
            String sequenceNum2 = signboardPrintListService.getSequenceNum(contractNum);
            sequenceNum = Integer.parseInt(sequenceNum2) > Integer.parseInt(sequenceNum) ? sequenceNum2 : sequenceNum;
            contractNum += sequenceNum;
            makeTime = now;
            this.updateMakeTimeAndContractNum(id, makeTime, contractNum);
        }
        //信息单号
        String[] contractNumArr = contractNum.split("-");
        String infoNum = "20" + contractNumArr[1] + "-" + typeCode + "-" + contractNumArr[2];
        String zipName = DateUtils.format(now, DateUtils.DATE_PATTERN) + "(" + infoNum + ")";
        String fileName = zipName + ".zip";
        OutputStream os = null;
        List<String> signboardIds = sturgeonReprintListService.listByReprintId(id);
        List<SturgeonSignboardDomestic> ssds = ssdService.listSignboardBySignboardIds(signboardIds);
        List<String> signboards = ssds.stream().map(SturgeonSignboardDomestic::getSignboard).collect(Collectors.toList());
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''"
                    + URLEncoder.encode(fileName, "utf-8"));
            os = response.getOutputStream();
            ZipOutputStream zos = new ZipOutputStream(os);


            List<String> a = Lists.newArrayList();
            List<String> b = Lists.newArrayList();
            List<String> c = Lists.newArrayList();
            for (SturgeonSignboardDomestic s : ssds) {
                String key = s.getSignboard();
                String val = s.getLabel();
                if ("A".equals(val)) {
                    a.add(key);
                } else if ("B".equals(val)) {
                    b.add(key);
                } else if ("S".equals(val)) {
                    c.add(key);
                }
            }
            if (!CollectionUtils.isEmpty(b)) {
                ZipUtil.createZip(zos, "code-b.txt", getTxtCodes(b));
            }
            if (!CollectionUtils.isEmpty(c)) {
                ZipUtil.createZip(zos, "code-c.txt", getTxtCodes(c));
            }
            if (!CollectionUtils.isEmpty(a)) {
                ZipUtil.createZip(zos, "code-a.txt", getTxtCodes(a));
            }
            ZipUtil.createZip(zos, zipName + ".xlsx", getExcel(contractNum, infoNum, makeTime, signboards,
                    entity.getApplyCode().substring(9, 11), tbCompVo));
            zos.closeEntry();
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] getSpeCodesAndNames(List<String> signboards) {
        String[] res = new String[3];
        if (!CollectionUtils.isEmpty(signboards)) {
            Set<String> set = new HashSet<>();
            for (String signboard : signboards) {
                set.add(signboard.substring(12, 14));
            }
            if (!CollectionUtils.isEmpty(set)) {
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                StringBuilder sb3 = new StringBuilder();
                for (String s : set) {
                    sb1.append("、" + s);
                    sb2.append("、" + CitesEnum.getVal(s));
                    sb3.append("、" + CitesEnum.getVal(s) + "制品");
                }
                res[0] = sb1.substring(1);
                res[1] = sb2.substring(1);
                res[2] = sb3.substring(1);
            }
        }
        return res;
    }

    public byte[] getTxtCodes(List<String> codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codes.size(); i++) {
            sb.append(codes.get(i)).append("\t\n");
        }
        return sb.toString().getBytes();
    }

    private void updateMakeTimeAndContractNum(String id, Date makeTime, String contractNum) {
        UpdateWrapper<SturgeonReprint> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("make_time", makeTime).set("contract_num", contractNum);
        sturgeonReprintMapper.update(null, updateWrapper);
    }


    private byte[] getExcel(String contractNum, String infoNum, Date makeTime, List<String> signboards,
                            String provinceCode, TbCompVo tbCompVo) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(1).setCellValue("专用标识制作信息单（制品类）");
        row0.createCell(2).setCellValue("合同编号：" + contractNum);
        row0.setHeight((short) 480);

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("供货方");
        row1.createCell(1).setCellValue("北京翰龙翔天防伪科技有限公司");
        row1.createCell(2).setCellValue("信息单号：" + infoNum);

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("信息核验");
        row2.createCell(1).setCellValue("中国野生动物保护协会水生野生动物保护分会");

        XSSFRow row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("制单日期");
        row3.createCell(1).setCellValue(DateUtils.format(makeTime, DateUtils.DATE_PATTERN));
        row3.createCell(2).setCellValue("打印内容:");

        String[] speCodesAndNames = this.getSpeCodesAndNames(signboards);
        XSSFRow row4 = sheet.createRow(4);
        row4.createCell(0).setCellValue("物种代码");
        row4.createCell(1).setCellValue(speCodesAndNames[0]);
        row4.createCell(2).setCellValue("原料名称：" + speCodesAndNames[1]);

        XSSFRow row5 = sheet.createRow(5);
        row5.createCell(0).setCellValue("省份代码");
        row5.createCell(1).setCellValue(provinceCode);
        row5.createCell(2).setCellValue("制品名称：" + speCodesAndNames[2]);

        XSSFRow row6 = sheet.createRow(6);
        row6.createCell(0).setCellValue("企业代码");
        row6.createCell(1).setCellValue(tbCompVo.getCompCode());
        row6.createCell(2).setCellValue("标识代码：" + this.getCodes(signboards));

        XSSFRow row7 = sheet.createRow(7);
        row7.createCell(0).setCellValue("企业名称");
        row7.createCell(1).setCellValue(tbCompVo.getCompName());
//        row7.createCell(2).setCellValue(this.getCodes(signboards));

        XSSFRow row8 = sheet.createRow(8);
        row8.createCell(0).setCellValue("制作数量");
        row8.createCell(1).setCellValue(signboards.size() + "枚");


        XSSFRow row9 = sheet.createRow(9);
        row9.createCell(0).setCellValue("制单人： 王振伟  ");
        row9.createCell(1).setCellValue("核验人： 李晓菲");
        row9.createCell(2).setCellValue("下单日期： " + DateUtils.format(makeTime, "yyyy年MM月dd日"));

        //合并需要的单元格
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 2));


        sheet.setColumnWidth(0, 5500);
        sheet.setColumnWidth(1, 8500);
        sheet.setColumnWidth(2, 8000);


        XSSFCellStyle titleStyle1 = workbook.createCellStyle();
        titleStyle1.setAlignment(HorizontalAlignment.CENTER);
        titleStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        sheet.getRow(0).getCell(1).setCellStyle(titleStyle1);
        XSSFCellStyle titleStyle2 = workbook.createCellStyle();
        titleStyle2.setAlignment(HorizontalAlignment.RIGHT);
        titleStyle2.setVerticalAlignment(VerticalAlignment.BOTTOM);
        sheet.getRow(0).getCell(2).setCellStyle(titleStyle2);

        XSSFCellStyle dataStyle1 = workbook.createCellStyle();
        dataStyle1.setBorderTop(BorderStyle.THIN);
        dataStyle1.setBorderBottom(BorderStyle.THIN);
        dataStyle1.setBorderLeft(BorderStyle.THIN);
        dataStyle1.setBorderRight(BorderStyle.THIN);
        dataStyle1.setWrapText(true);
        for (int i = 1; i < 9; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            for (int j = 0; j < 3; j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                cell.setCellStyle(dataStyle1);
            }
        }

        XSSFCellStyle bottomStyle1 = workbook.createCellStyle();
        bottomStyle1.setAlignment(HorizontalAlignment.CENTER);
        sheet.getRow(9).getCell(1).setCellStyle(bottomStyle1);
        XSSFCellStyle bottomStyle2 = workbook.createCellStyle();
        bottomStyle2.setAlignment(HorizontalAlignment.RIGHT);
        sheet.getRow(9).getCell(2).setCellStyle(bottomStyle2);


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bos.toByteArray();
    }

    public static String getCodes(List<String> signboards) {
        if (!CollectionUtils.isEmpty(signboards)) {
            Set<String> set = new HashSet();
            StringBuilder sb = new StringBuilder();
            for (String signboard : signboards) {
                set.add(signboard.substring(0, 14) + "********");
            }
            for (String signboard : set) {
                sb.append(" " + signboard);
            }
            return sb.toString();
        }
        return "";
    }


    @Override
    public String getContractSequenceNum(String contractNum) {
        String maxSequenceNum = sturgeonReprintMapper.getYearMaxSequenceNum(contractNum);
        return StringUtils.hasText(maxSequenceNum) ?
                String.format("%06d", (Integer.valueOf(maxSequenceNum.substring(6)) + 1)) : "000001";
//        return "000001";
    }

    @Override
    public String getApplyType(String id) {
        QueryWrapper<SturgeonReprint> queryWrapper = new QueryWrapper();
        queryWrapper.select("apply_type").eq("id", id);
        return sturgeonReprintMapper.selectOne(queryWrapper).getApplyType();
    }

    /**
     * 审核
     */
    private void audit(String id, String status, String thirdPrint, String opinion) {
        QueryWrapper<SturgeonReprint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonReprint sturgeon = sturgeonReprintMapper.selectOne(queryWrapper);
        if (Objects.isNull(sturgeon)) {
            throw new SofnException("待审核的数据不存在");
        }
        sturgeon.preUpdate();
        if (StringUtils.hasText(thirdPrint)) {
            sturgeon.setThirdPrint(thirdPrint);
        }
        sturgeon.setStatus(status);
        SturgeonProcess sturgeonProcess = new SturgeonProcess();
        sturgeonProcess.setApplyId(id);
        sturgeonProcess.setStatus(status);
        if (SturgeonStatusEnum.PASS.getKey().equals(status)) {
            if ("2".equals(sturgeon.getApplyType())) {
                ssdService.updatePirngStatusBySturgeonIds(
                        sturgeonReprintListService.listByReprintId(
                                sturgeon.getId()), StringUtils.hasText(thirdPrint) ? thirdPrint : BoolUtils.N, BoolUtils.N);
            } else {
                sturgeonSignboardService.updatePirngStatusBySturgeonId(sturgeon.getSturgeonId(), BoolUtils.N);
            }
        } else {
            sturgeon.setOpinion(opinion);
            sturgeonProcess.setAdvice(opinion);
        }
        sturgeonReprintMapper.updateById(sturgeon);
        if (Constants.WORKFLOW.equals(BoolUtils.N)) {
            sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
        }
    }


}
