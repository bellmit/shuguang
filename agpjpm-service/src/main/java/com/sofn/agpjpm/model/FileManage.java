package com.sofn.agpjpm.model;

import com.baomidou.mybatisplus.annotation.*;
import com.sofn.agpjpm.vo.FileAttForm;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文件管理
 * </p>
 *
 * @author jiangtao
 * @since 2020-06-10
 */
@Data
@TableName("file_manage")
public class FileManage  {
    private String id;
    private String fileName;
    private Date createTime;
    private String createUserId;
    private String createUser;
    @TableField(exist = false)
    private List<FileAtt> fileAttList;
}
