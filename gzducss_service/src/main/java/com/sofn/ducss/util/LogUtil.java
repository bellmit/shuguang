package com.sofn.ducss.util;

import com.sofn.ducss.service.DucssOperateLogService;
import com.sofn.ducss.vo.AreaRegionCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * 日志相关工具类
 */
@Slf4j
public class LogUtil {

    /**
     * 保存操作日志
     * @param operateType   操作类型  LogEnum中添加了枚举，请使用枚举
     * @param operateDetail   操作详情  查看原型中的消息格式
     */
    public static void addLog(String operateType, String operateDetail){
        DucssOperateLogService ducssOperateLogService = SpringContextHolder.getBean(DucssOperateLogService.class);
        ducssOperateLogService.save(operateType, operateDetail);
    }

    /**
     * 将完整的区划查询出来
     * @param areaId   末级区划ID
     * @return   最上层到本层的名字
     */
    public static String getAreaName(String areaId){
        try{
            if(!StringUtils.isEmpty(areaId)){
                AreaRegionCode regionCodeByLastCode = SysRegionUtil.getRegionCodeByLastCode(areaId);
                String provinceName = regionCodeByLastCode.getProvinceName() == null ? "" : regionCodeByLastCode.getProvinceName();
                String cityName = regionCodeByLastCode.getCityName() == null ? "" : regionCodeByLastCode.getCityName();
                String countyName = regionCodeByLastCode.getCountyName() == null ? "" : regionCodeByLastCode.getCountyName();
                return provinceName + cityName + countyName;
            }
        }catch (Exception e){
            log.error("日志查询完整的区划名称出现错误,areaId={}", areaId);
            e.printStackTrace();
        }
        return "";
    }

}
