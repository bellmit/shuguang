package com.sofn.agzirz.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.agzirz.model.Xgxxsj;
import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class XgxxsjVo extends Model<XgxxsjVo> {

    @ApiModelProperty(name = "xgxxsjNo", value = "主键")
    @TableId("XGXXSJ_NO")
    private String xgxxsjNo;

    @ApiModelProperty(name = "cnName", value = "中文名")
    private String cnName;

    @ApiModelProperty(name = "latinName", value = "拉丁学名")
    private String latinName;

    @ApiModelProperty(name = "schoName", value = "科名")
    private String schoName;

    @ApiModelProperty(name = "appearChara", value = "形态特征")
    private String appearChara;

    @ApiModelProperty(name = "biologChara", value = "生物学特征")
    private String biologChara;

    @ApiModelProperty(name = "hazardChara", value = "危害特征")
    private String hazardChara;

    @ApiModelProperty(name = "specialLaw", value = "专门法律")
    private String specialLaw;

    @ApiModelProperty(name = "relatedLaw", value = "相关法律")
    private String relatedLaw;

    @ApiModelProperty(name = "haveMeasure", value = "是否有防控措施(1:是  0:否)")
    private String haveMeasure;

    @ApiModelProperty(name = "descriptionOfMeasure", value = "防控措施描述")
    private String descriptionOfMeasure;

    @ApiModelProperty(name = "intrusionEvent", value = "入侵事件")
    private String intrusionEvent;

    @ApiModelProperty(name = "preconCase", value = "防控案例")
    private String preconCase;

    @ApiModelProperty(name = "refeFile", value = "参考文献")
    private String refeFile;

    @ApiModelProperty(name = "relevantFiles", value = "相关文件")
    private String relevantFiles;

    @ApiModelProperty(name = "reportUser", value = "上报人")
    private String reportUser;

    @ApiModelProperty(name = "reportUserName", value = "上报人姓名")
    private String reportUserName;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "reportTime", value = "上报时间")
    private Date reportTime;

    @ApiModelProperty(value = "有效状态，用于逻辑删除")
    private String enableStatus;

    @ApiModelProperty(name = "relevantFilesUrl", value = "文件地址list")
    private List<Map<String, Object>> relevantFilesUrl;

    public XgxxsjVo(){}

    /**
     * 将vo转换为po对象
     */
    public static Xgxxsj getXgxxsj(XgxxsjVo xgxxsjVo){
        Xgxxsj xgxxsj = new Xgxxsj();
        BeanUtils.copyProperties(xgxxsjVo,xgxxsj);
        return xgxxsj;
    }


    /**
     * 将po转换为vo对象
     */
    public static XgxxsjVo getXgxxsjVo(Xgxxsj xgxxsj){
        XgxxsjVo xgxxsjVo = new XgxxsjVo();
        BeanUtils.copyProperties(xgxxsj,xgxxsjVo);
        return xgxxsjVo;

    }

}
