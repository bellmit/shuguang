package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 国内鲟鱼子酱标识补打列表
 */
@Data
@TableName("STURGEON_REPRINT_LIST")
public class SturgeonReprintList {

    /**
     * id
     */
    private String id;
    /**
     * 补打id
     */
    private String reprintId;
    /**
     * 标识id
     */
    private String signboardId;


}
