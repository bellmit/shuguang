package com.sofn.dhhrp.vo;

import com.sofn.dhhrp.enums.ProcessEnum;
import com.sofn.dhhrp.model.Baseinfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

@Data
@ApiModel(value = "基础信息Vo对象")
public class BaseinfoLastVo {

    @ApiModelProperty(value = "监测点名称")
    private String pointName;
    @ApiModelProperty(value = "监测年份")
    private String year;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "省名")
    private String provinceName;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "市名")
    private String cityName;
    @ApiModelProperty(value = "县")
    private String county;
    @ApiModelProperty(value = "县名")
    private String countyName;
    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;
    @ApiModelProperty(value = "温度（℃）")
    private Double temperature;
    @ApiModelProperty(value = "湿度（克/立方米")
    private Double humidity;
    @ApiModelProperty(value = "光照（Lux）")
    private Double illumination;
    @ApiModelProperty(value = "年降雨量（mm）")
    private Double rainfall;
    @ApiModelProperty(value = "物种名称Id")
    private String variety;
    @ApiModelProperty(value = "物种名称")
    private String varietyName;
    @ApiModelProperty(value = "群体数量(个)")
    private Integer amount;
    @ApiModelProperty(value = "公母比例")
    private String proportion;
    @ApiModelProperty(value = "养殖户数量(个)")
    private Integer breeder;
    @ApiModelProperty(value = "空气质量")
    private String air;
    @ApiModelProperty(value = "饲草料种植面积（m2）")
    private Double plant;
    @ApiModelProperty(value = "饲草料产量（t）")
    private Double yield;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "状态名称")
    private String statusName;
    @ApiModelProperty(value = "监测人")
    private String monitor;
    @ApiModelProperty(value = "监测时间")
    private Date monitoringTime;

    /**
     * 实体类转VO类
     */
    public static BaseinfoLastVo entity2Vo(Baseinfo entity) {
        BaseinfoLastVo vo = null;
        if (!Objects.isNull(entity)) {
            vo = new BaseinfoLastVo();
            BeanUtils.copyProperties(entity, vo);
            vo.setStatusName(ProcessEnum.getVal(entity.getStatus()));
        }
        return vo;
    }
}
