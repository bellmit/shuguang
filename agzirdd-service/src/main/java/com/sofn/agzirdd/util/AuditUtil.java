package com.sofn.agzirdd.util;

import com.sofn.agzirdd.enums.RoleCodeEnum;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.UserUtil;

import java.util.List;

/**
 * 审核工具类
 */
public class AuditUtil {
    /**
     * 检查是否有审核权限
     * @param currentStatus
     */
    public static void checkAuditRight(String currentStatus) {
        boolean flag = StatusEnum.hasAuditRight(RoleCodeUtil.getLoginUserAgzirddRoleCode(), currentStatus);
        if(!flag)
            SofnExceptionUtil.throwError("您还不能审核该数据");
    }

    /**
     * 检查新状态是否正确
     * @param newStatus
     */
    public static void checkAuditNewStatus(String newStatus){
        String oragLevel = RoleCodeUtil.getLoginUserAgzirddRoleCode();
        boolean flag =false;
        if(RoleCodeEnum.MINISTRY.getCode().equals(oragLevel)){ //部级
            if(StatusEnum.STATUS_8.getCode().equals(newStatus) || StatusEnum.STATUS_7.getCode().equals(newStatus))
                flag = true;
        }else if(RoleCodeEnum.PROVINCE.getCode().equals(oragLevel)){ //省级
            if(StatusEnum.STATUS_5.getCode().equals(newStatus) || StatusEnum.STATUS_6.getCode().equals(newStatus))
                flag = true;
        }else if(RoleCodeEnum.CITY.getCode().equals(oragLevel)){ //市级
            if(StatusEnum.STATUS_3.getCode().equals(newStatus) || StatusEnum.STATUS_4.getCode().equals(newStatus))
                flag = true;
        }

        if(!flag)
            SofnExceptionUtil.throwError("审核结果状态错误");
    }

    /**
     * 获取审核拒绝新状态码
     * @param currStatus
     * @return
     */
    public static String getRejectNewStatus(String currStatus) {
        String newStatus = "";
        if (StatusEnum.STATUS_1.getCode().equals(currStatus)) {
            newStatus = StatusEnum.STATUS_4.getCode();
        } else if (StatusEnum.STATUS_3.getCode().equals(currStatus)) {
            newStatus = StatusEnum.STATUS_6.getCode();
        } else if (StatusEnum.STATUS_5.getCode().equals(currStatus)) {
            newStatus = StatusEnum.STATUS_8.getCode();
        } else if (StatusEnum.STATUS_7.getCode().equals(currStatus)) {
            newStatus = StatusEnum.STATUS_8.getCode();
        } else if (StatusEnum.STATUS_10.getCode().equals(currStatus)) {
            newStatus = StatusEnum.STATUS_8.getCode();
        }else
            SofnExceptionUtil.throwError("当前状态不可审核");

        return newStatus;
    }

    /**
     * 获取审核通过新状态码
     * @param currStatus
     * @return
     */
    public static String getAcceptNewStatus(String currStatus) {
        String newStatus = "";
        if (StatusEnum.STATUS_1.getCode().equals(currStatus)) {
            newStatus = StatusEnum.STATUS_3.getCode();
        } else if (StatusEnum.STATUS_3.getCode().equals(currStatus)) {
            newStatus = StatusEnum.STATUS_5.getCode();
        } else if (StatusEnum.STATUS_5.getCode().equals(currStatus)) {
            newStatus = StatusEnum.STATUS_7.getCode();
        } else if (StatusEnum.STATUS_7.getCode().equals(currStatus)) {
            newStatus = StatusEnum.STATUS_7.getCode();
        } else if (StatusEnum.STATUS_10.getCode().equals(currStatus)) {
            newStatus = StatusEnum.STATUS_7.getCode();
        }else
            SofnExceptionUtil.throwError("当前状态不可审核");

        return newStatus;
    }
}
