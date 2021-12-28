package com.sofn.fyem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "测试数据")
@TableName("TEST")
@Data
public class Test extends BaseModel<Test> {
    @TableId(type = IdType.UUID )
    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(value = "名字")
    private String name;

    public Test() {
    }
}
