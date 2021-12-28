package com.sofn.sys.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import com.sofn.sys.enums.SysManageEnum;
import lombok.Data;

/**
 * Created by sofn
 */
@TableName("SYS_SUBSYSTEM")
@Data
public class SysSubsystem extends BaseModel<SysSubsystem> {
    private String subsystemName;
    private String delFlag;
    private String parentId;
    private String description;
    private String appId;
    private String parentIds;
    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }
    public boolean isRootNode() {
        return parentId.equals(SysManageEnum.RESOURCE_ROOT.getCode());
    }

    /**
     * 前端访问路径
     */
    public String viewUrl;

    /**
     * 排序
     */
    public Integer sort;

    /**
     * 菜单类型   1. 平台 2 分系统  3 子系统
     */
    public String systemType;

}
