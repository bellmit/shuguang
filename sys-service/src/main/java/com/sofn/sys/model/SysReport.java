package com.sofn.sys.model;

import com.sofn.common.model.BaseModel;
import lombok.Data;

/**
 * 系统报表
 * @author heyongjie
 * @date 2020/5/26 16:26
 */
@Data
public class SysReport extends BaseModel {

    /**
     * 报表名称
     */
    private String reportName;

    /**
     * 系统ID
     */
    private String appId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 报表类型  1  普通报表 2 决策报表
     */
    private String reportType;

}
