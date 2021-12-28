package com.sofn.agsjsi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 农业湿地示范点收集审核流程记录表
 */
@Data
@TableName("wet_example_point_collect_proc")
public class WetExamplePointCollectProc extends BaseModel<WetExamplePointCollectProc> {
    private String id;
    //农业湿地示范点收集id
    private String collectId;
    //操作状态
    private String status;
    //建议
    private String advice;
    //操作人ID
    private String personId;
    //操作人
    private String personName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
