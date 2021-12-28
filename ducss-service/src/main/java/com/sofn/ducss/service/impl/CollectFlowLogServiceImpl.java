package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.mapper.CollectFlowLogMapper;
import com.sofn.ducss.model.CollectFlowLog;
import com.sofn.ducss.service.CollectFlowLogService;
import com.sofn.ducss.sysapi.SysFileApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 获取年度任务
 */
@Service
@Slf4j
public class CollectFlowLogServiceImpl extends ServiceImpl<CollectFlowLogMapper, CollectFlowLog> implements CollectFlowLogService {

    @Autowired
    private CollectFlowLogMapper collectFlowLogMapper;

    @Autowired
    private SysFileApi sysFileApi;

    @Override
    public PageUtils<CollectFlowLog> getCollectFlowLogByPage(Integer pageNo, Integer pageSize, String year, List<String> areaIds) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("areaIds", areaIds);
        List<CollectFlowLog> logList = collectFlowLogMapper.getCollectFlowLogList(params);
        if (CollectionUtils.isNotEmpty(logList)) {
            // 查询fileIds
            List<String> fileIds = Lists.newArrayList();
            for (CollectFlowLog log : logList) {
                String userId = log.getCreateUserId();
                String userName = UserUtil.getUsernameById(userId);
                log.setCreateUserName(userName);
                fileIds.addAll(IdUtil.getIdsByStr(log.getFiles()));
            }
            // 批量查询对应文件信息
            if (CollectionUtils.isNotEmpty(fileIds)) {
                Result<List<SysFileManageVo>> fileResult = sysFileApi.batchGetFileInfo(IdUtil.getStrIdsByList(fileIds));
                if (fileResult != null && CollectionUtils.isNotEmpty(fileResult.getData())) {
                    Map<String, SysFileManageVo> fileMap = fileResult.getData().stream().collect(Collectors.toMap(SysFileManageVo::getId, Function.identity(), (key1, key2) -> key2));
                    for (CollectFlowLog log : logList) {
                        List<SysFileManageVo> fileManageVos = Lists.newArrayList();
                        for (String fileId : IdUtil.getIdsByStr(log.getFiles())) {
                            SysFileManageVo file = fileMap.get(fileId);
                            if (file != null) {
                                fileManageVos.add(file);
                            }
                        }
                        log.setFileManageVos(fileManageVos);
                        List<String> fileNameList = fileManageVos.stream().map(SysFileManageVo::getFileName).collect(Collectors.toList());
                        log.setFileNames(StringUtils.join(fileNameList, ","));
                    }
                }
            }
        }
        return PageUtils.getPageUtils(new PageInfo(logList));
    }

    @Override
    public CollectFlowLog lastCollectFlowLog(String year, String areaId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("areaId", areaId);
        params.put("status", Constants.ExamineState.RETURNED.toString());
        CollectFlowLog log = collectFlowLogMapper.lastCollectFlowLog(params);
        // 查询文件信息
        if (log != null && StringUtils.isNotEmpty(log.getFiles())) {
            // 查询对应文件信息
            Result<List<SysFileManageVo>> fileResult = sysFileApi.batchGetFileInfo(log.getFiles());
            if (fileResult != null && CollectionUtils.isNotEmpty(fileResult.getData())) {
                log.setFileManageVos(fileResult.getData());
            }
        }
        return log;
    }

}
