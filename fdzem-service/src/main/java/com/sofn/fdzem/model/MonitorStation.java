package com.sofn.fdzem.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author gaosheng
 * @since 2020-5-27
 */
@Data
@ApiModel("监测站信息")
@TableName("tb_monitor_station")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonitorStation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 監測站維護信息id
     */
    @TableId
    @ApiModelProperty("ID")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 监测站名称
     */
    //@TableField(exist = false)
    @ApiModelProperty("监测站名称")
    private String name;

    /**
     * 监测站地址
     */
    //@TableField(exist = false)
    @ApiModelProperty("监测站地址")
    private String address;

    /**
     * 經度
     */
    @ApiModelProperty("經度")
    private String longitude;

    /**
     * 緯度
     */
    @ApiModelProperty("緯度")
    private String latitude;

    /**
     * 水域类型
     */
    @ApiModelProperty("水域类型")
    private String watersType;

    /**
     * 所属海区或流域
     */
    @ApiModelProperty("所属海区或流域")
    private String seaArea;

    /**
     * 水域名称
     */
    @ApiModelProperty("水域名称")
    private String watersName;

    /**
     * 省
     */
    @ApiModelProperty("省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty("市")
    private String provinceCity;

    /**
     * 縣
     */
    @ApiModelProperty("县")
    private String countyTown;

    /**
     * 是否已经被分到到区域管辖中
     */
    @ApiModelProperty("是否已经被分到到区域管辖中,Y  or  N")
    private String isDistribute;
    /**
     * 监测中心外键id
     */
    @ApiModelProperty("监测中心外键id")
    private String distributeId;
    /**
     * 用户所在机构id
     */
    @ApiModelProperty("用户所在机构的id,这个是必传项")
    private String organizationId;


}
