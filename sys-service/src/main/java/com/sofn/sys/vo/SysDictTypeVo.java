package com.sofn.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sofn.sys.model.SysDict;
import com.sofn.sys.model.SysDictType;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@ApiModel(value="字典类型管理")
public class SysDictTypeVo {

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

    public SysDictTypeVo(){

    }

    public SysDictTypeVo(String id,String typename,String typevalue){
        this.id = id;
        this.typename = typename;
        this.typevalue = typevalue;
    }

    /**
     * 将PO转换成VO
     * @param sysDictType
     * @return
     */
    public static SysDictTypeVo getSysDictTypeVo(SysDictType sysDictType){
        SysDictTypeVo sysDictTypeVo = new SysDictTypeVo();
        BeanUtils.copyProperties(sysDictType,sysDictTypeVo);
        return sysDictTypeVo;
    }

    /**
     * 将VO转换成PO
     * @param sysDictTypeVo
     * @return
     */
    public static SysDictType getSysTypeDict(SysDictTypeVo sysDictTypeVo){
        SysDictType sysDictType = new SysDictType();
        BeanUtils.copyProperties(sysDictTypeVo,sysDictType);
        return sysDictType;
    }
}
