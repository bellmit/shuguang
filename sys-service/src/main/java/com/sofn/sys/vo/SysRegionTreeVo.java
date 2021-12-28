package com.sofn.sys.vo;

import com.sofn.sys.model.SysRegion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 *
 * 行政区划代码树结构
 * @author heyongjie
 */
@Data
@ApiModel
public class SysRegionTreeVo {

    @ApiModelProperty(value = "行政区划ID")
    private String id;

    @ApiModelProperty(value = "行政区划名称")
    private String regionName;

    @ApiModelProperty(value = "行政区划代码")
    private String regionCode;

    @ApiModelProperty(value = "行政区划排序")
    private Integer sort ;

    @ApiModelProperty(value = "父行政区划ID")
    private String parentId;

    @ApiModelProperty(value = "备注")
    private String remark = "";

    @ApiModelProperty(value = "区域ID")
    private String areaId;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String latitude;

    /**
     * 版本年份
     */
    @ApiModelProperty(value = "版本年份")
    private Integer versionYear;

    /**
     * 版本代码
     */
    @ApiModelProperty(value = "版本代码")
    private Long versionCode;

    @ApiModelProperty(value = "行政区划下的行政区划")
    private List<SysRegionTreeVo> children;

    /**
     * 将持久对象转化为VO
     * @param sysRegion
     * @return
     */
    public static SysRegionTreeVo getSysRegionTreeVo(SysRegion sysRegion){
        SysRegionTreeVo sysRegionTreeVo = new SysRegionTreeVo();
        BeanUtils.copyProperties(sysRegion,sysRegionTreeVo);
        if(StringUtils.isBlank(sysRegionTreeVo.getRemark())){
            sysRegionTreeVo.setRemark("");
        }
        if(StringUtils.isBlank(sysRegionTreeVo.getAreaId())){
            sysRegionTreeVo.setAreaId("");
        }
        sysRegionTreeVo.setSort(sysRegion.getSortid());
        return sysRegionTreeVo;
    }

}
