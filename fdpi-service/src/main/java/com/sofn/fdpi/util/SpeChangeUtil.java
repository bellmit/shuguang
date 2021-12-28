package com.sofn.fdpi.util;

import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.fdpi.enums.ComeStockFlowEnum;
import com.sofn.fdpi.service.WorkService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysDict;
import com.sofn.fdpi.vo.ChangeRecVoInPapersYearVo;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wenxin
 * @create 2021/2/26
 */
public class SpeChangeUtil {

    private static SysRegionApi sysRegionApi = SpringContextHolder.getBean(SysRegionApi.class);

    /**
     * 翻译物种转移类型
     * @param changeRecList
     */
    public static void replaceSpeChangeType(List<ChangeRecVoInPapersYearVo> changeRecList) {
        Map<String, String> map = mergeSpeChangeType();
        if (CollectionUtils.isEmpty(changeRecList)) return;

        changeRecList.forEach(changeRecVoInPapersYearVo -> {
            changeRecVoInPapersYearVo.setChangeReason(map.get(changeRecVoInPapersYearVo.getChangeReason()));
        });
    }

    /**
     * 获取物种转移类型
     * @return
     */
    private static Map<String, String> mergeSpeChangeType(){
        //从支撑平台获取物种转移枚举类型
        final String type = "fdpi_changeType";
        Result<List<SysDict>> dictListByType = sysRegionApi.getDictListByType(type);
        if (dictListByType.getData() == null){
            throw new SofnException("支撐平台获取字典错误");
        }
        Map<String, String> collect = dictListByType.getData().stream().
                collect(Collectors.toMap(SysDict::getDictcode, SysDict::getDictname));

        //获取枚举类型
        ComeStockFlowEnum[] values = ComeStockFlowEnum.values();

        Arrays.stream(values).forEach(comeStockFlowEnum -> {
            collect.put(comeStockFlowEnum.getCode(),comeStockFlowEnum.getMsg());
        });

        return collect;
    }
}
