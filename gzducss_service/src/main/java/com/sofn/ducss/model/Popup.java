package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("popup")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class Popup extends Model<Popup> {
    private String id;

    @ApiModelProperty(name = "areaId", value = "县ID")
    private String areaId;

    @ApiModelProperty(name = "year", value = "所属年度")
    private String year;

    @ApiModelProperty(name = "proStillPopup", value = "产生量与直接还田量弹窗（Y：弹出提示框，N：不弹出提示框）")
    private String proStillPopup;

    @ApiModelProperty(name = "disperseUtilizePopup", value = "农户分散利用量弹窗（Y：弹出提示框，N：不弹出提示框）")
    private String disperseUtilizePopup;

    @ApiModelProperty(name = "strawUtilizePopup", value = "规模化秸秆利用量弹窗（Y：弹出提示框，N：不弹出提示框）")
    private String strawUtilizePopup;

    public Popup() {
        this.proStillPopup = "Y";
        this.disperseUtilizePopup = "Y";
        this.strawUtilizePopup = "Y";
    }

}