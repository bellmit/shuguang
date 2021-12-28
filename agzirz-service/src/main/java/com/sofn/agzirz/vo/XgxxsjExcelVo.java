package com.sofn.agzirz.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.agzirz.model.FKYJZH;
import com.sofn.agzirz.model.Xgxxsj;
import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.Date;


@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class XgxxsjExcelVo {

    @ExcelField(title = "中文名")
    @ApiModelProperty(name = "cnName", value = "中文名")
    private String cnName;

    @ExcelField(title = "拉丁学名")
    @ApiModelProperty(name = "latinName", value = "拉丁学名")
    private String latinName;

    @ExcelField(title = "科名")
    @ApiModelProperty(name = "schoName", value = "科名")
    private String schoName;

    @ExcelField(title = "形态特征")
    @ApiModelProperty(name = "appearChara", value = "形态特征")
    private String appearChara;

    @ExcelField(title = "生物学特征")
    @ApiModelProperty(name = "biologChara", value = "生物学特征")
    private String biologChara;

    @ExcelField(title = "危害特征")
    @ApiModelProperty(name = "hazardChara", value = "危害特征")
    private String hazardChara;

    @ExcelField(title = "专门法律")
    @ApiModelProperty(name = "specialLaw", value = "专门法律")
    private String specialLaw;

    @ExcelField(title = "相关法律")
    @ApiModelProperty(name = "relatedLaw", value = "相关法律")
    private String relatedLaw;

    @ExcelField(title = "是否有防控措施")
    @ApiModelProperty(name = "haveMeasure", value = "是否有防控措施(1:是  0:否)")
    private String haveMeasure;

    @ExcelField(title = "防控措施描述")
    @ApiModelProperty(name = "descriptionOfMeasure", value = "防控措施描述")
    private String descriptionOfMeasure;

    @ExcelField(title = "入侵事件")
    @ApiModelProperty(name = "intrusionEvent", value = "入侵事件")
    private String intrusionEvent;

    @ExcelField(title = "防控案例")
    @ApiModelProperty(name = "preconCase", value = "防控案例")
    private String preconCase;

    @ExcelField(title = "参考文献")
    @ApiModelProperty(name = "refeFile", value = "参考文献")
    private String refeFile;

    @ExcelField(title = "上报人")
    @ApiModelProperty(name = "reportUser", value = "上报人")
    private String reportUser;

    @ExcelField(title = "上报时间")
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "reportTime", value = "上报时间")
    private Date reportTime;

    public XgxxsjExcelVo(){}

    public static XgxxsjExcelVo getXgxxsjVo(Xgxxsj xgxxsj){
        XgxxsjExcelVo xgxxsjExcelVo = new XgxxsjExcelVo();
        BeanUtils.copyProperties(xgxxsj,xgxxsjExcelVo);
        return xgxxsjExcelVo;

    }

}
