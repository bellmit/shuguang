package com.sofn.sys.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.model.SysResource;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * 批量导入菜单
 * @author heyongjie
 * @date 2020/5/21 16:32
 */
@Data
@ExcelSheetInfo(title = "批量导入菜单",sheetName = "批量导入菜单")
public class SysResourceBatchSaveVo {

    /**
     * 系统代码
     */
    @ExcelField(title = "系统代码")
    @ExcelImportCheck(isNull = false,errMsg = "系统代码不能为空")
    private String subSystemCode;

    /**
     * 菜单名称
     */
    @ExcelField(title = "菜单名称")
    @ExcelImportCheck(isNull = false,max = 50,min = 2,errMsg = "菜单名称不能为空，2-50个字符")
    private String name;

    /**
     * 父节点名称
     */
    @ExcelField(title = "父菜单名称")
    @ExcelImportCheck(isNull = false,max = 50,min = 2,errMsg = "父菜单名称不能为空，2-50个字符")
    private String parentName;

    /**
     * 菜单链接
     */
    @ExcelField(title = "菜单链接")
    @ExcelImportCheck(isNull = false,max = 100,min = 2,errMsg = "菜单链接不能为空，2-100个字符")
    private String url;

    /**
     * 菜单类型
     */
    @ExcelField(title = "菜单类型",customDictValue = {"菜单","按钮"})
    @ExcelImportCheck(isNull = false)
    private String type;

    /**
     * 权限标识
     */
    @ExcelField(title = "权限代码")
    @ExcelImportCheck(isNull = false,max = 100,min=2,errMsg = "权限代码不能为空，2-100个字符")
    private String permission;


    /**
     * 获取持久对象，因为ID需要构造parentIds等信息，所以传入
     * @param sysResourceBatchSaveVo   导入的数据
     * @return  SysResource
     */
    public static SysResource getSysResource(SysResourceBatchSaveVo sysResourceBatchSaveVo,SysResource parentResource){
        if(parentResource == null){
            throw new SofnException(String.format("当前节点%s父节点菜单为空",sysResourceBatchSaveVo.getName()));
        }
        SysResource sysResource = new SysResource();
        // 设置基本信息
        sysResource.setId(IdUtil.getUUId());
        sysResource.setResourceName(sysResourceBatchSaveVo.getName());
        sysResource.setType(SysManageEnum.getEnumCode("RESOURCE", sysResourceBatchSaveVo.getType()));
        sysResource.setResourceUrl(sysResourceBatchSaveVo.getUrl());
        sysResource.setPermission(sysResourceBatchSaveVo.getPermission());
        sysResource.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());
        sysResource.setCreateUserId(UserUtil.getLoginUserId());
        sysResource.setCreateTime(new Date());
        sysResource.setAppId(sysResourceBatchSaveVo.getSubSystemCode());
        // 根据父节点设置上下级信息
        String parentIds  = "";
        if(StringUtils.isBlank(parentResource.getParentIds())){
            // 父菜单的parentIds为空就只有根节点才会出现这种情况
            if(!SysManageEnum.RESOURCE_ROOT.getCode().equals(parentResource.getId())){
                throw new SofnException(String.format("节点%s的parentIds为空，但该节点不是根节点，请检查数据！",sysResourceBatchSaveVo.getName()));
            }
        }else{
            parentIds  = parentResource.getParentIds();
        }
        sysResource.setParentIds(parentIds + parentResource.getId() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode());
        sysResource.setParentId(parentResource.getId());
        return sysResource;
    }

}
