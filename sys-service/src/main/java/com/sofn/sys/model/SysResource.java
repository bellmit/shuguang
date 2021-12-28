package com.sofn.sys.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import com.sofn.sys.enums.SysManageEnum;
import lombok.Data;

/**
 * Created by sofn
 */
@TableName("SYS_RESOURCE")
@Data
public class SysResource extends BaseModel<SysResource> {

    /**
     * 资源名称
     */
    private String resourceName;
  /*  private String resourceNo;*/
    /**
     * 资源类型
     */

    private String type;
    /**
     * 资源路径
     */
    private String resourceUrl;
    /**
     * 权限字符串
     */
    private String permission;
    /**
     * 父编号
     */
    private String parentId;

    private String appId;

    /**
     * 父编号列表
     */
    private String parentIds;
   /* private List<SysResource> parentList;*/
    private String delFlag;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Long priority;
    /**
     * 叶子节点
     */
    private Boolean leaf;

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }

    public boolean isRootNode() {
        return parentId.equals(SysManageEnum.RESOURCE_ROOT.getCode());
    }


    public SysResource(String resourceName) {
        this.resourceName = resourceName;

    }
    public SysResource() {
    }

}
