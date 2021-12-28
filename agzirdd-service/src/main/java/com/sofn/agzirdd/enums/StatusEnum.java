package com.sofn.agzirdd.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 状态枚举
 * @Author: Administrator
 */
@Getter
public enum StatusEnum {


    /**
     * 0-已保存,1-已提交,2-已撤回,3-市级通过,4-市级退回,
     * 5-省级通过,6-省级退回,7-总站通过,8-总站退回,9-专家填报
     */
    STATUS_0("0","已保存"),
    STATUS_1("1","已提交"),
    STATUS_2("2","已撤回"),
    STATUS_3("3","市级通过"),
    STATUS_4("4","市级退回"),
    STATUS_5("5","省级通过"),
    STATUS_6("6","省级退回"),
    STATUS_7("7","总站通过"),
    STATUS_8("8","总站退回"),
    STATUS_9("9","专家填报"),
    STATUS_10("10","专家提交");


    private String code;
    private String description;

    private StatusEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(StatusEnum statusEnum:StatusEnum.values()){
            if(code.equals(statusEnum.getCode())){
                return statusEnum.getDescription();
            }
        }
        return  null;
    }

    /**
     * 判断状态码，是否是可以删除的
     * 所有状态为「填报」「保存」「撤回」「退回」的都可以删除
     * @param code
     * @return
     */
    public static boolean canRemove(String code){
        boolean flag = false;
        if(StatusEnum.STATUS_0.code.equals(code) ||
            StatusEnum.STATUS_2.code.equals(code) ||
            StatusEnum.STATUS_4.code.equals(code) ||
            StatusEnum.STATUS_6.code.equals(code) ||
            StatusEnum.STATUS_8.code.equals(code) ||
            StatusEnum.STATUS_9.code.equals(code)
        )
            flag = true;
        return flag;
    }

    /**
     * 该行政级别是否有权限审核数据
     * 只有部级，省级，市级用户拥有审核权限
     * @param oragLevel
     * @param status
     * @return
     */
    public static boolean hasAuditRight(String oragLevel,String status){
        boolean flag = false;
        if(RoleCodeEnum.MINISTRY.getCode().equals(oragLevel)){ //部级
            if(StatusEnum.STATUS_5.getCode().equals(status) || StatusEnum.STATUS_7.getCode().equals(status)
            || StatusEnum.STATUS_10.getCode().equals(status))
                flag = true;
        }else if(RoleCodeEnum.PROVINCE.getCode().equals(oragLevel)){ //省级
            if(StatusEnum.STATUS_3.getCode().equals(status))
                flag = true;
        }else if(RoleCodeEnum.CITY.getCode().equals(oragLevel)){ //市级
            if(StatusEnum.STATUS_1.getCode().equals(status))
                flag = true;
        }

        return flag;
    }

    /**
     * 根据权限码，获得可查询状态的数据
     * @param roleCode
     * @return
     */
    public static String getShowStatusByRole(String roleCode){
        List<String> statusList = new ArrayList<>();
        if (RoleCodeEnum.MINISTRY.getCode().equals(roleCode)) {//总站
            statusList.add(StatusEnum.STATUS_5.code);
            statusList.add(StatusEnum.STATUS_7.code);
            statusList.add(StatusEnum.STATUS_10.code);
        }else if(RoleCodeEnum.PROVINCE.getCode().equals(roleCode)) {//省级
            statusList.add(StatusEnum.STATUS_3.code);
            statusList.add(StatusEnum.STATUS_5.code);
            statusList.add(StatusEnum.STATUS_7.code);
        }else if(RoleCodeEnum.EXPERT.getCode().equals(roleCode)) {//专家
            statusList.add(StatusEnum.STATUS_2.code);
            statusList.add(StatusEnum.STATUS_7.code);
            statusList.add(StatusEnum.STATUS_8.code);
            statusList.add(StatusEnum.STATUS_9.code);
            statusList.add(StatusEnum.STATUS_10.code);
        }else if(RoleCodeEnum.CITY.getCode().equals(roleCode)) {//市级
            statusList.add(StatusEnum.STATUS_1.code);
            statusList.add(StatusEnum.STATUS_3.code);
            statusList.add(StatusEnum.STATUS_5.code);
            statusList.add(StatusEnum.STATUS_7.code);
        }else if(RoleCodeEnum.COUNTY.getCode().equals(roleCode)) {//县级
            statusList.add(StatusEnum.STATUS_0.code);
            statusList.add(StatusEnum.STATUS_1.code);
            statusList.add(StatusEnum.STATUS_2.code);
            statusList.add(StatusEnum.STATUS_3.code);
            statusList.add(StatusEnum.STATUS_4.code);
            statusList.add(StatusEnum.STATUS_5.code);
            statusList.add(StatusEnum.STATUS_6.code);
            statusList.add(StatusEnum.STATUS_7.code);
            statusList.add(StatusEnum.STATUS_8.code);
        }

        return statusList.stream().map(s -> s = "'"+s+"'").collect(Collectors.joining(","));
    }
}
