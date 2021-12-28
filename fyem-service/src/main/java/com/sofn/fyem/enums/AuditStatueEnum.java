package com.sofn.fyem.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Description: 审核状态枚举
 * @Author: DJH
 * @Date: 2020/4/27 15:18
 */
@Getter
public enum AuditStatueEnum {
    STATUS_NOTREPORT("0", "未上报"),

    STATUS_REPORT_CITY("1", "市级待审核"),
    STATUS_RETURN_CITY("2", "市级已退回"),
    STATUS_APPROVE_CITY("3", "市级已通过"),

    STATUS_REPORT_PROV("4", "省级待审核"),
    STATUS_RETURN_PROV("5", "省级已退回"),
    STATUS_APPROVE_PROV("6", "省级已通过"),

    STATUS_REPORT_MASTER("7", "部级待审核"),
    STATUS_RETURN_MASTER("8", "部级已退回"),
    STATUS_APPROVE_MASTER("9", "部级已通过");

    private String status;
    private String describe;

    AuditStatueEnum(String status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public static AuditStatueEnum getEnum(String status){
        for (AuditStatueEnum auditStatueEnum : AuditStatueEnum.values()){
            if (auditStatueEnum.getStatus().equals(status)){
                return auditStatueEnum;
            }
        }
        return null;
    }

    public static Optional<AuditStatueEnum> resolve(final String status) {
        return Stream.of(values()).filter(en -> StringUtils.equalsIgnoreCase(status, en.getStatus()))
                .findFirst();
    }
}
