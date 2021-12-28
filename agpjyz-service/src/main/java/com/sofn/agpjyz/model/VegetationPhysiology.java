package com.sofn.agpjyz.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-02 16:31
 */

@TableName("VEGETATION_PHYSIOLOGY")
@Data
public class VegetationPhysiology {
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    //    保护点ID
    private String protectId;
    //    保护点value
    private String protectValue;
    //   植被覆盖度
    private String coverDegree;
    //    冠层
    private String canopy;
    //    叶面积
    private String leafArea;
    // 操作人
    private String inputer ;
    // 操作时间
    @ApiModelProperty(value = "操作时间")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputerTime;

}
