package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @Description:  证书年审明细表
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@TableName("PAPERS_YEAR_INSPECT_RETAIL")
public class PapersYearInspectRetail extends BaseModel<PapersYearInspectRetail> {
    @TableId(value="ID",type = IdType.UUID)
    private String id;
    //证书年审表id
    private String papersYearInspectId;
    //证书表id
    private String papersId;
    //物种编号
    private String speciesId;
    //物种数量
    private Integer issueNum;
    //标识数量
    private Integer signboardNum;
}
