package com.sofn.sys.vo;

import com.sofn.sys.model.SysBaseDataType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 基础数据分页返回类
 *
 * @author heyongjie
 * @date 2020/4/7 13:50
 */
@Data
@ApiModel("基础数据类型")
public class SysBaseTypeAndDataVo {

    private String id;

    @ApiModelProperty("基础数据类型名称")
    private String typeName;

    @ApiModelProperty("基础数据类型代码")
    private String typeValue;

    @ApiModelProperty("基础数据")
    private List<SysBaseDataVo2> sysBaseDataVo2List;

    /**
     * 将持久类转为VO对象
     * @param sysBaseDataType SysBaseDataType
     * @return  SysBaseTypeAndDataVo
     */
    public static SysBaseTypeAndDataVo getSysBaseTypeAndDataVo(SysBaseDataType sysBaseDataType){
        if(sysBaseDataType != null){
            SysBaseTypeAndDataVo sysBaseTypeAndDataVo = new SysBaseTypeAndDataVo();
            sysBaseTypeAndDataVo.setId(sysBaseDataType.getId());
            sysBaseTypeAndDataVo.setTypeName(sysBaseDataType.getTypename());
            sysBaseTypeAndDataVo.setTypeValue(sysBaseDataType.getTypevalue());
            return sysBaseTypeAndDataVo;
        }
        return null;
    }

}
