package com.sofn.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import com.sofn.sys.model.SysDict;
import com.sofn.sys.model.SysDictType;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@ApiModel(value="字典管理")
@ExcelSheetInfo(title = "字典",sheetName = "字典")
public class SysDictVo {

    @TableId(type= IdType.UUID)
    @ExcelField(title = "ID")
    private String id;

    @ExcelField(title = "字典类型")
    private SysDictTypeVo sysDictTypeVo;

    @ExcelField(title = "字典名")
    private String dictname;

    @ExcelField(title = "字典码")
    private String dictcode;

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
     * @param sysDict
     * @return
     */
    public static SysDictVo getSysDictVo(SysDict sysDict){
        SysDictVo sysDictVo = new SysDictVo();
        if(sysDict != null){
            BeanUtils.copyProperties(sysDict,sysDictVo);
            SysDictType sysDictType = sysDict.getSysDictType();
            if(sysDictType != null){
                SysDictTypeVo sysDictTypeVo = new SysDictTypeVo();
                BeanUtils.copyProperties(sysDictType,sysDictTypeVo);
                sysDictVo.setSysDictTypeVo(sysDictTypeVo);
            }
        }
        return sysDictVo;
    }

    /**
     * 将VO转换成PO
     * @param sysDictVo
     * @return
     */
    public static SysDict getSysDict(SysDictVo sysDictVo){
        SysDict sysDict = new SysDict();
        if(sysDictVo != null){
            BeanUtils.copyProperties(sysDictVo,sysDict);
            SysDictTypeVo sysDictTypeVo = sysDictVo.getSysDictTypeVo();
            if(sysDictTypeVo != null){
                SysDictType sysDictType = new SysDictType();
                BeanUtils.copyProperties(sysDictTypeVo,sysDictType);
                sysDict.setSysDictType(sysDictType);
            }
        }
        return sysDict;
    }
}
