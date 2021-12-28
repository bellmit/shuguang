package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;


/**
 * 植物利用用途类
 **/
@Data
@TableName("PLANT_UTILIZATION_PURPOSE")
public class PlantUtilizationPurpose {

    /**
     * 主键
     */
    private String id;
    /**
     * 利用ID
     */
    private String utilizationId;
    /**
     * 用途ID
     */
    private String purposeId;
    /**
     * 用途VALUE
     */
    private String purposeValue;

}
