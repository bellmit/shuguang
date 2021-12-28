package com.sofn.fyrpa.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("文件信息对象")
@Data
@TableName(value = "fileinfovo")
public class FileInfo {

    @ApiModelProperty(name = "id",value ="主键id" )
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(name = "fileIds",value ="需要激活的文件id" )
    private String fileIds;

    @ApiModelProperty(name = "systemId",value ="系统id" )

    private String systemId;


}