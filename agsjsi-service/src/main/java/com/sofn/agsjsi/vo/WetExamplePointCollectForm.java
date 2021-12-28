package com.sofn.agsjsi.vo;

import com.sofn.agsjsi.model.WetExamplePointCollect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@ApiModel("农业湿地示范点收集表单")
public class WetExamplePointCollectForm {
    @ApiModelProperty("主键，新增：不用上传；修改：需上传")
    private String id;
    //湿地区名称
    @NotBlank(message = "湿地区名称必填！")
    @Length(max=100,message = "湿地区名称长度不能超过100！")
    @ApiModelProperty(value = "湿地区名称",example = "湿地区名称1")
    private String wetName;
    //湿地区编码
    @NotBlank(message = "湿地区编码必填！")
    @Length(max=20,message = "湿地区编码长度不能超过20！")
    @ApiModelProperty(value = "湿地区编码",example = "10102215")
    private String wetCode;
    @ApiModelProperty(value = "湿地总面积（hm2）",example = "100.23")
    private BigDecimal wetTotalArea;
    //湿地斑块数量（个）
    @ApiModelProperty(value = "湿地斑块数量（个）",example = "100")
    private Integer wetPlaqueCount;
    //湿地类1
    @ApiModelProperty(value = "湿地类1",example = "湿地类1")
    private String wetType1;
    //类面积1（hm2）
    @ApiModelProperty(value = "类面积1（hm2）",example = "20")
    private BigDecimal wetTypeArea1;
    //主要湿地型1
    @ApiModelProperty(value = "主要湿地型1",example = "主要湿地型1")
    private String wetModel1;
    //行面积1（hm2）
    @ApiModelProperty(value = "行面积1（hm2）",example = "10")
    private BigDecimal wetModelArea1;
    //湿地类2
    @ApiModelProperty(value = "湿地类2",example = "湿地类2")
    private String wetType2;
    //类面积2（hm2）
    @ApiModelProperty(value = "类面积2（hm2）",example = "30")
    private BigDecimal wetTypeArea2;
    //主要湿地型2
    @ApiModelProperty(value = "主要湿地型2",example = "主要湿地型2")
    private String wetModel2;
    //行面积2（hm2）
    @ApiModelProperty(value = "行面积2（hm2）",example = "10")
    private BigDecimal wetModelArea2;

    //湿地区分布-省
    @Length(max = 32,message = "长度不能超过32！")
    @ApiModelProperty(value = "湿地区分布-省",example = "440000")
    private String provinceCode;
    //湿地区分布-市
    @Length(max = 32,message = "长度不能超过32！")
    @ApiModelProperty(value = "湿地区分布-市",example = "440300")
    private String cityCode;
    //湿地区分布-区
    @Length(max = 32,message = "长度不能超过32！")
    @ApiModelProperty(value = "湿地区分布-区",example = "440303")
    private String areaCode;
    //湿地区分布-区域中文
    @NotBlank(message = "湿地区分布必填！")
    @Length(max = 100,message = "长度不能超过100！")
    @ApiModelProperty(value="湿地区分布-区域中文",example = "广东省-深圳市-罗湖区")
    private String regionInCh;
    //北纬
    @ApiModelProperty(value = "北纬",example = "101.254568")
    private BigDecimal northLatitude;
    //东经
    @ApiModelProperty(value = "东经",example = "131.254518")
    private BigDecimal eastLongitude;
    //所属二级流域
//    @NotBlank(message = "所属二级流域必填！")
    @Length(max=50,message = "所属二级流域长度不能超过50！")
    @ApiModelProperty(value = "所属二级流域",example = "二级流域1")
    private String secondBasin;
    //河流级别
    @ApiModelProperty(value = "河流级别",example = "级别1")
    private String riverLevel;
    //平均海拔
    @ApiModelProperty(value = "平均海拔",example = "500")
    private BigDecimal avgAltitude;
    //水源补给情况
    @ApiModelProperty(value = "水源补给情况",example = "地表径流,大气降水,综合补给")
    private String waterSupply;

    //近海域海岸湿地
    @ApiModelProperty(value = "近海域海岸湿地",example = "半日潮")
    private String wetNearSea;
    //盐度%
    @ApiModelProperty(value = "盐度%",example = "20")
    private BigDecimal salinity;
    //水温（℃）
    @ApiModelProperty(value = "水温（℃）",example = "45.2")
    private BigDecimal waterTemp;
    //土地所有权
    @ApiModelProperty(value = "土地所有权",example = "国有")
    private String landOwnership;
    //植被类型
    @ApiModelProperty(value = "植被类型",example = "植被类型1")
    private String plantType;
    //植被面积
    @ApiModelProperty(value = "植被面积",example = "650")
    private BigDecimal plantArea;
    //中文学名
    @ApiModelProperty(value = "中文学名",example = "植物1")
    private String nameInCh;
    //拉丁学名
    @ApiModelProperty(value = "拉丁学名",example = "latin植物1")
    private String latinName;
    //科名
    @ApiModelProperty(value = "科名",example = "family植物1")
    private String familyName;

    public WetExamplePointCollect getWetExamplePointCollect(WetExamplePointCollectForm form){
        WetExamplePointCollect collect=new WetExamplePointCollect();
        BeanUtils.copyProperties(form,collect);
        return collect;
    }
}
