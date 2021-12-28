package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

/**
 * 证书年审中证书列表信息
 */
@Data
@TableName("PAPERS_YEAR_INSPECT_P_RETAIL")
public class PapersYearInspectPRetail extends BaseModel<PapersYearInspectPRetail> {
    @TableId(value="ID",type = IdType.UUID)
    private String id;
    //证书年审表id
    private String papersYearInspectId;
    //证书表id
    private String papersId;
}
