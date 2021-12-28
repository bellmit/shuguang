package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 下拉列表
 * @Author: WXY
 * @Date: 2020-2-25 19:40:26
 */
@Data
@ApiModel
public class ImExportCacheVo {

    private String id;

    private String impAuform;

    private String  speName;
}
