package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

/**
 * 证书年审主表
 */
@Data
@TableName("PAPERS_YEAR_INSPECT")
public class PapersYearInspect extends BaseModel<PapersYearInspect> {
    //主键
    @TableId(value="ID",type = IdType.UUID)
    private String id;
    //年度
    private Integer year;
    //企业编号
    private String tbCompId;
    //状态：1：未上报；2：上报；3：初审通过；4;初审退回；5：复审通过；6：复审退回;7:撤回
    private String status;
    //申请编号
    private String applyNum;
}
