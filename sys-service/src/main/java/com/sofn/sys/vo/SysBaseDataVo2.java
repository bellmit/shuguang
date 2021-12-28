package com.sofn.sys.vo;

import com.sofn.sys.model.SysBaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页返回BaseDataVo
 * @author heyongjie
 * @date 2020/4/7 13:57
 */
@Data
@ApiModel(value="基础数据VO")
public class SysBaseDataVo2 {

    private String id;

    @ApiModelProperty("基础数据名称")
    private String baseDataName;

    @ApiModelProperty("基础数据代码")
    private String baseDataCode;

    @ApiModelProperty("基础数据类型")
    private String baseTypeId;

    @ApiModelProperty("是否启用")
    private String enable;

    @ApiModelProperty("备注")
    private String remark;

    /**
     * 将持久层转为VO
     * @param sysBaseData  SysBaseData
     * @return  SysBaseDataVo2
     */
    public static SysBaseDataVo2 getSysBaseDataVo2(SysBaseData sysBaseData){
        if(sysBaseData != null){
            SysBaseDataVo2 sysBaseDataVo2 = new SysBaseDataVo2();
            sysBaseDataVo2.setId(sysBaseData.getId());
            sysBaseDataVo2.setBaseDataCode(sysBaseData.getBaseDatacode());
            sysBaseDataVo2.setBaseDataName(sysBaseData.getBaseDataname());
            sysBaseDataVo2.setEnable(sysBaseData.getEnable());
            sysBaseDataVo2.setRemark(sysBaseData.getRemark());
            sysBaseDataVo2.setBaseTypeId(sysBaseData.getBasedatatypeid());
            return sysBaseDataVo2;
        }
        return null;
    }

}
