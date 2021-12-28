package com.sofn.sys.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@TableName("SYS_WORKFLOW_URL")
@Data
@ApiModel("工作流url基础类")
public class WorkFlowUrlRegulator implements Serializable {
  @ApiModelProperty("id")
  @TableId(type= IdType.UUID)
  private String id;

  @ApiModelProperty("路径名称（key）")
  @NotBlank(message = "路径名称不能为空")
  private String  urlKey ;

  @ApiModelProperty("路径（value）")
  private String  urlValue;
}
