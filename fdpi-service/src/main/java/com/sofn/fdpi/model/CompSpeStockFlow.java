package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("COMP_SPE_STOCKFLOW")
public class CompSpeStockFlow {
    private String id;
    private String compId;
    private String speciesId;
    private String speNum;
    private String billType;
    private String importMark;
    private Integer beforeNum;
    private Integer chNum;
    private Integer afterNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date chTime;
    private String lastChangeUserId;
    private String otherComName;

}
