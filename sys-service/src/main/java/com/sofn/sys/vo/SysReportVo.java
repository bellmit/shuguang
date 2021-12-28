package com.sofn.sys.vo;

import com.sofn.common.exception.SofnException;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.model.SysReport;
import com.sofn.sys.util.FineReportUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * 系统报表VO
 *
 * @author heyongjie
 * @date 2020/5/26 16:29
 */
@Data
@ApiModel
public class SysReportVo {

    private String id;

    @ApiModelProperty(value = "报表名称", required = true)
    @NotBlank(message = "报表名称不能为空")
    @Length(max = 100, message = "报表名称最多100个字符")
    private String reportName;

    @ApiModelProperty(value = "所属系统", required = true)
    @NotBlank(message = "所属系统不能为空")
    private String appId;

    @ApiModelProperty(value = "子系统名称，用于显示，添加时不填")
    private String subSystemName;

    @ApiModelProperty(value = "报表备注")
    @Length(max = 100, message = "备注最多100个字符")
    private String remark;

    @ApiModelProperty(value = "报表类型，1：普通报表 2：决策报表", required = true)
    @NotBlank(message = "报表类型不能为空")
    private String reportType;

    @ApiModelProperty(value = "报表URL，用于访问报表，添加时不用传入", required = true)
    private String url;

    /**
     * 根据VO对象获取持久对象
     *
     * @param sysReportVo VO对象
     * @return 持久对象
     */
    public static SysReport getSysReport(SysReportVo sysReportVo) {
        if (sysReportVo != null) {
            SysReport sysReport = new SysReport();
            if (!(SysManageEnum.SYS_REPORT_TYPE_ORDINARA.getCode().equals(sysReportVo.getReportType()) ||
                    SysManageEnum.SYS_REPORT_TYPE_POLICY_DECISION.getCode().equals(sysReportVo.getReportType()))) {
                throw new SofnException("报表类型异常");
            }
            BeanUtils.copyProperties(sysReportVo, sysReport);
            return sysReport;
        }
        throw new SofnException("未传入任何数据");
    }

    /**
     * 根据持久对象获取VO对象
     * @param sysReport  持久对象
     * @return  SysReportVo
     */
    public static SysReportVo getSysReportVo(SysReport sysReport) {
        if (sysReport != null) {
            SysReportVo sysReportVo = new SysReportVo();
            BeanUtils.copyProperties(sysReport, sysReportVo);
            // 设置路径
            sysReportVo.setUrl(FineReportUtil.getReportUrl(sysReport.getReportName(), sysReport.getReportType()));
            return sysReportVo;
        }
        return null;
    }
}
