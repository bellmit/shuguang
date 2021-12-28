package com.sofn.flow.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("wr_model")
@Data
public class Def {
    private long id;
    private String defId;
    private Integer version;
    private Integer state;
    private String importTime;
    private String lastChangeTime;
}
