package com.sofn.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import com.sofn.sys.model.SysBaseData;
import com.sofn.sys.model.SysBaseDataType;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@ApiModel(value="基础数据管理")
@ExcelSheetInfo(title = "基础数据",sheetName = "基础数据")
public class SysBaseDataVo {

    @TableId(type= IdType.UUID)
    @ExcelField(title = "ID")
    private String id;

    @ExcelField(title = "基础数据类型")
    private SysBaseDataTypeVo sysBaseDataTypeVo;

    @ExcelField(title = "基础数据名")
    private String BaseDataname;

    @ExcelField(title = "基础数据码")
    private String BaseDatacode;

    @ExcelField(title = "是否启用")
    private String enable;

    @ExcelField(title = "备注")
    private String remark;

    @ExcelField(title = "创建者")
    private String createBy;

    @ExcelField(title = "创建日期",dataFormat = "yyyy-MM")
    private Date createTime;

    @ExcelField(title = "修改者")
    private String updateBy;

    @ExcelField(title = "修改日期",dataFormat = "yyyy-MM")
    private Date updateTime;

    /**
     * 将PO转换成VO
     * @param sysBaseData
     * @return
     */
    public static SysBaseDataVo getSysBaseDataVo(SysBaseData sysBaseData){
        SysBaseDataVo sysBaseDataVo = new SysBaseDataVo();
        if(sysBaseData != null){
            BeanUtils.copyProperties(sysBaseData,sysBaseDataVo);
            SysBaseDataType sysBaseDataType = sysBaseData.getSysBaseDataType();
            if(sysBaseDataType != null){
                SysBaseDataTypeVo sysBaseDataTypeVo = new SysBaseDataTypeVo();
                BeanUtils.copyProperties(sysBaseDataType,sysBaseDataTypeVo);
                sysBaseDataVo.setSysBaseDataTypeVo(sysBaseDataTypeVo);
            }
        }
        return sysBaseDataVo;
    }

    /**
     * 将VO转换成PO
     * @param sysBaseDataVo
     * @return
     */
    public static SysBaseData getSysBaseData(SysBaseDataVo sysBaseDataVo){
        SysBaseData sysBaseData = new SysBaseData();
        if(sysBaseDataVo != null){
            BeanUtils.copyProperties(sysBaseDataVo,sysBaseData);
            SysBaseDataTypeVo sysBaseDataTypeVo = sysBaseDataVo.getSysBaseDataTypeVo();
            if(sysBaseDataTypeVo != null){
                SysBaseDataType sysBaseDataType = new SysBaseDataType();
                BeanUtils.copyProperties(sysBaseDataTypeVo,sysBaseDataType);
                sysBaseData.setSysBaseDataType(sysBaseDataType);
                sysBaseData.setBasedatatypeid(sysBaseDataTypeVo.getId());
            }
        }
        return sysBaseData;
    }
}
