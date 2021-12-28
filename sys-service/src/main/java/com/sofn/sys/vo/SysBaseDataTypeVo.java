package com.sofn.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import com.sofn.sys.model.SysBaseDataType;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@ApiModel(value="基础数据类型管理")
@ExcelSheetInfo(title = "基础数据类型",sheetName = "基础数据类型")
public class SysBaseDataTypeVo {
    @TableId(type= IdType.UUID)
    private String id;
    private String typename;
    private String typevalue;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String delFlag;
    private String remark;
    private String enable;

    public SysBaseDataTypeVo(){

    }

    public SysBaseDataTypeVo(String id,String typename,String typevalue){
        this.id = id;
        this.typename = typename;
        this.typevalue = typevalue;
    }

    /**
     * 将PO转换成VO
     * @param sysBaseDataType
     * @return
     */
    public static SysBaseDataTypeVo getSysBaseDataTypeVo(SysBaseDataType sysBaseDataType){
        SysBaseDataTypeVo sysBaseDataTypeVo = new SysBaseDataTypeVo();
        BeanUtils.copyProperties(sysBaseDataType,sysBaseDataTypeVo);
        return sysBaseDataTypeVo;
    }

    /**
     * 将VO转换成PO
     * @param sysBaseDataTypeVo
     * @return
     */
    public static SysBaseDataType getSysTypeBaseData(SysBaseDataTypeVo sysBaseDataTypeVo){
        SysBaseDataType sysBaseDataType = new SysBaseDataType();
        BeanUtils.copyProperties(sysBaseDataTypeVo,sysBaseDataType);
        return sysBaseDataType;
    }
}
