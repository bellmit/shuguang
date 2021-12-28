package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.fdpi.enums.DepartmentTypeEnum;
import com.sofn.fdpi.model.TbDepartment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

@Data
@ApiModel("部门信息对象")
public class TbDepartmentVo {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("机构名称")
    private String deptName;
    //省
    @ApiModelProperty("省编码")
    private String deptPro;
    //市
    @ApiModelProperty("市编码")
    private String deptCity;
    //区
    @ApiModelProperty("区编码")
    private String deptArea;

    //行政区划中文：省-市-区
    @ApiModelProperty("行政区划中文，格式：省-市-区，例：四川省-成都市-高新区")
    private String regionInChina;

    //支撑平台中机构id
    @ApiModelProperty("支撑平台中机构id")
    private String orgId;

    //创建时间
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("删除标识")
    private String delFlag;
    @ApiModelProperty("机构类型：1：直属机构；2：执法机构")
    private String type;
    @ApiModelProperty("机构类型：1：直属机构；2：执法机构")
    private String typeName;

    /**
     * 实体类转VO类
     */
    public static TbDepartmentVo entity2Vo(TbDepartment entity) {
        TbDepartmentVo vo = new TbDepartmentVo();
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
            vo.setTypeName(DepartmentTypeEnum.getVal(vo.getType()));
        }
        return vo;
    }
}
