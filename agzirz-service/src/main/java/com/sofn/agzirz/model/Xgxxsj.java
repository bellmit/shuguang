package com.sofn.agzirz.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@TableName("XGXXSJ")
public class Xgxxsj extends Model<Xgxxsj> {

    @ExcelField(title = "主键")
    @ApiModelProperty(name = "xgxxsjNo", value = "主键")
    @TableId("XGXXSJ_NO")
    private String xgxxsjNo;

    @ApiModelProperty(value = "省级ID")
    @TableField("PROVINCE_ID")
    private String provinceId;

    @ApiModelProperty(value = "市级ID")
    @TableField("CITY_ID")
    private String cityId;

    @ApiModelProperty(value = "县级ID")
    @TableField("COUNTY_ID")
    private String countyId;

    @ExcelField(title = "中文名")
    @Size(max= 100,message = "中文名字数不能超过100")
    @ApiModelProperty(name = "cnName", value = "中文名")
    private String cnName;

    @ExcelField(title = "拉丁学名")
    @Size(max= 100,message = "拉丁学名字数不能超过100")
    @ApiModelProperty(name = "latinName", value = "拉丁学名")
    private String latinName;

    @ExcelField(title = "科名")
    @Size(max= 100,message = "科名字数不能超过100")
    @ApiModelProperty(name = "schoName", value = "科名")
    private String schoName;

    @ExcelField(title = "形态特征")
    @Size(max= 300,message = "形态特征字数不能超过300")
    @ApiModelProperty(name = "appearChara", value = "形态特征")
    private String appearChara;

    @ExcelField(title = "生物学特征")
    @Size(max= 300,message = "生物学特征字数不能超过300")
    @ApiModelProperty(name = "biologChara", value = "生物学特征")
    private String biologChara;

    @ExcelField(title = "危害特征")
    @Size(max= 300,message = "危害特征字数不能超过300")
    @ApiModelProperty(name = "hazardChara", value = "危害特征")
    private String hazardChara;

    @ExcelField(title = "专门法律")
    @Size(max= 300,message = "专门法律字数不能超过300")
    @ApiModelProperty(name = "specialLaw", value = "专门法律")
    private String specialLaw;

    @ExcelField(title = "相关法律")
    @Size(max= 300,message = "相关法律字数不能超过300")
    @ApiModelProperty(name = "relatedLaw", value = "相关法律")
    private String relatedLaw;

    @ExcelField(title = "是否有防控措施")
    @ApiModelProperty(name = "haveMeasure", value = "是否有防控措施(1:是  0:否)")
    private String haveMeasure;

    @ExcelField(title = "防控措施描述")
    @Size(max= 300,message = "防控措施描述字数不能超过300")
    @ApiModelProperty(name = "descriptionOfMeasure", value = "防控措施描述")
    private String descriptionOfMeasure;

    @ExcelField(title = "入侵事件")
    @Size(max= 300,message = "入侵事件字数不能超过300")
    @ApiModelProperty(name = "intrusionEvent", value = "入侵事件")
    private String intrusionEvent;

    @ExcelField(title = "防控案例")
    @Size(max= 300,message = "防控案例字数不能超过300")
    @ApiModelProperty(name = "preconCase", value = "防控案例")
    private String preconCase;

    @ExcelField(title = "参考文献")
    @Size(max= 300,message = "参考文献字数不能超过300")
    @ApiModelProperty(name = "refeFile", value = "参考文献")
    private String refeFile;

    @ExcelField(title = "相关文件")
    @ApiModelProperty(name = "relevantFiles", value = "相关文件")
    private String relevantFiles;

    @ExcelField(title = "上报人")
    @ApiModelProperty(name = "reportUser", value = "上报人")
    private String reportUser;

    @ExcelField(title = "上报时间")
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "reportTime", value = "上报时间")
    private Date reportTime;

    @ApiModelProperty(value = "有效状态，用于逻辑删除")
    @ExcelField(title="有效状态，用于逻辑删除",isShow = false)
    private String enableStatus;

}
