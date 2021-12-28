package com.sofn.fdpi.util;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.fdpi.service.WorkService;
import com.sofn.fdpi.sysapi.bean.*;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class WorkUtil {

    private static WorkService workService = SpringContextHolder.getBean(WorkService.class);

    /**
     * 获取单个业务流程
     */
    public static List<Map<String, Object>> getProcesslist(String defId, String idAttrName, String idAttrValue) {
        List<Map<String, Object>> list = Collections.EMPTY_LIST;
        try {
            Result<HisProcInstVo> result =
                    workService.getProcessInstHisByIdAttr(new UpdateInstVo(defId, idAttrName, idAttrValue));
            List<HisActInstVo> hisActInstVos = result.getData().getActInstList();
            if (!CollectionUtils.isEmpty(hisActInstVos)) {
                list = Lists.newArrayListWithCapacity(hisActInstVos.size());
                for (int i = hisActInstVos.size() - 1; i >= 0; i--) {
                    HisActInstVo hisActInstVo = hisActInstVos.get(i);
                    Map<String, Object> params = Collections.EMPTY_MAP;
                    String actDefId = hisActInstVo.getActDefId();
                    Date actInstCompleteTime = hisActInstVo.getActInstCompleteTime();
                    if (!"START".equals(actDefId) && !"END".equals(actDefId) && Objects.nonNull(actInstCompleteTime)) {
                        List<HisWorkItemVo> hisWorkItemVos = hisActInstVo.getWorkItemList();
                        if (!CollectionUtils.isEmpty(hisWorkItemVos)) {
                            params = hisWorkItemVos.get(0).getWorkItemParams();
                            params.put("createTime", actInstCompleteTime);
                        }
                        list.add(params);
                    }
                }
            }
        } catch (Exception e) {
            throw new SofnException("未获取到流程工作实例");
        }
        Collections.sort(list, new MapComparatorDesc());
        return list;
    }

    static class MapComparatorDesc implements Comparator<Map<String, Object>> {
        @Override
        public int compare(Map<String, Object> m1, Map<String, Object> m2) {
           String v1 = DateUtils.format((Date) m1.get("createTime"), DateUtils.DATE_TIME_PATTERN);
            String v2 =  DateUtils.format((Date) m2.get("createTime"), DateUtils.DATE_TIME_PATTERN);
            if (v2 != null) {
                return v2.compareTo(v1);
            }
            return 0;
        }
    }

    /**
     * 根据条件查询流程（分页）
     */
    public static PageUtils<ActivityDataVo> getPageUtilsByParams(ActivityDataParamsVo activityDataParamsVo) {
        if (CollectionUtils.isEmpty(activityDataParamsVo.getIdAttrValues())) {
            return PageUtils.getPageUtils(new PageInfo(Collections.EMPTY_LIST));
        }
        try {
            Result<PageUtils<ActivityDataVo>> result = workService.getActivityAllDataByName(activityDataParamsVo);
            return result.getData();
        } catch (Exception e) {
            throw new SofnException("未获取到流程工作实例");
        }
    }
}
