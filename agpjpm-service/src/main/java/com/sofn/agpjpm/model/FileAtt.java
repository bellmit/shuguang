package com.sofn.agpjpm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.agpjpm.vo.AttFrom;
import com.sofn.agpjpm.vo.PicFrom;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-16 17:30
 */
@Data
@TableName("file_att")
public class FileAtt {
    private String id;
    private String picId;
    private String picName;
    private String picUrl;
    private String attId;
    private String attName;
    private String attUrl;
    private String sourceId;
    @TableField(exist = false)
    private AttFrom att;
    @TableField(exist = false)
    private PicFrom pic;
}
