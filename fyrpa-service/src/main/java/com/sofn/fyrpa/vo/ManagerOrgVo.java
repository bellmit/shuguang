package com.sofn.fyrpa.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("管理机构信息vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerOrgVo {

    @ApiModelProperty(name = "managerOrgName",value ="保护区管理机构名称" )
    private String managerOrgName;

    @ApiModelProperty(name = "orgFormationDept",value ="机构编制部门" )
    private String orgFormationDept;

    @ApiModelProperty(name = "deptApproveNumber",value ="机构编制部门批准文号" )
    private String deptApproveNumber;

    @ApiModelProperty(name = "managerOrgLevel",value ="保护区管理机构级别" )
    private String managerOrgLevel;

    @ApiModelProperty(name = "managerOrgQuality",value ="保护区管理机构性质" )
    private String managerOrgQuality;

    @ApiModelProperty(name = "jurisdictionRelation",value ="隶属关系" )
    private String jurisdictionRelation;

    @ApiModelProperty(name = "managerOrgStaffFormation",value ="保护区管理机构人员编制" )
    private String managerOrgStaffFormation;

    @ApiModelProperty(name = "managerStaffAmout",value ="管理人员数量" )
    private String managerStaffAmout;

    @ApiModelProperty(name = "technologyStaffAmout",value ="技术人员数量" )
    private String technologyStaffAmout;

    @ApiModelProperty(name = "isFunds",value ="是否有专项管护经费" )
    private String isFunds;

    @ApiModelProperty(name = "fundsSource",value ="经费来源" )
    private String fundsSource;

    @ApiModelProperty(name = "fixedFunds",value ="固定经费（万元/年）" )
    private String fixedFunds;

    @ApiModelProperty(name = "managerOrgAddr",value ="保护区管理机构所在地地址" )
    private String managerOrgAddr;

    @ApiModelProperty(name = "managerOrgPostCode",value ="保护区管理机构所在地邮编" )
    private String managerOrgPostCode;

    @ApiModelProperty(name = "managerOrgPhone",value ="保护区管理机构电话" )
    private String managerOrgPhone;

    @ApiModelProperty(name = "managerOrgPortraiture",value ="保护区管理机构传真" )
    private String managerOrgPortraiture;

    @ApiModelProperty(name = "email",value ="电子邮件" )
    private String email;

}