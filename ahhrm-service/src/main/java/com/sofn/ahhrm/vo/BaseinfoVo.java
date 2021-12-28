package com.sofn.ahhrm.vo;

import com.sofn.ahhrm.enums.ProcessEnum;
import com.sofn.ahhrm.model.Baseinfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@ApiModel(value = "基础信息Vo对象")
public class BaseinfoVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "监测点名称")
    private String pointName;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "省名")
    private String provinceName;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "市名")
    private String cityName;
    @ApiModelProperty(value = "县")
    private String county;
    @ApiModelProperty(value = "县名")
    private String countyName;
    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;
    @ApiModelProperty(value = "所属类型")
    private String type;
    @ApiModelProperty(value = "固定电话/手机")
    private String tel;
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "状态名称")
    private String statusName;
    @ApiModelProperty(value = "监测人")
    private String monitor;
    @ApiModelProperty(value = "监测时间")
    private Date monitoringTime;
    @ApiModelProperty(value = "基础信息子表Vo对象")
    private List<BaseinfoSubVo> baseinfoSubVos;
    @ApiModelProperty(value = "审核进程记录VO对象")
    private List<AuditProcessVo> auditProcessVos;


    @ApiModelProperty(value = "能否审核")
    private Boolean canAudit;
    @ApiModelProperty(value = "能否撤回")
    private Boolean canCancel;
    @ApiModelProperty(value = "能否修改")
    private Boolean canEdit;

    /**
     * 实体类转VO类
     */
    public static BaseinfoVo entity2Vo(Baseinfo entity) {
        BaseinfoVo vo = null;
        if (!Objects.isNull(entity)) {
            vo = new BaseinfoVo();
            BeanUtils.copyProperties(entity, vo);
            vo.setStatusName(ProcessEnum.getVal(entity.getStatus()));
        }
        return vo;
    }
}
