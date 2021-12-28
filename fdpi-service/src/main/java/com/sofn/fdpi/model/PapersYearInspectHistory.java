package com.sofn.fdpi.model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @author wenxin
 * @create 2021/1/20
 * 证书年审历史记录
 */
@Data
@TableName("PAPERS_YEAR_INSPECT_HISTORY")
public class PapersYearInspectHistory extends BaseModel<PapersYearInspectHistory> {

    @TableId(value="ID",type = IdType.UUID)
    private String id;

    private String speId;

    private String compId;

    private String yearInspectId;

    private String code;

    private String status;

    private Date createTime;

    private String createUserId;

    private String delFlag;

    private String signboardType;

}
