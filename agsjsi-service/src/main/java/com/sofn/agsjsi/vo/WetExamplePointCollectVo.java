package com.sofn.agsjsi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("农业湿地示范点收集")
public class WetExamplePointCollectVo {
    @ApiModelProperty("主键")
    private String id;
    //湿地区名称
    @ApiModelProperty("湿地区名称")
    private String wetName;
    //湿地区编码
    @ApiModelProperty("湿地区编码")
    private String wetCode;
    @ApiModelProperty(value = "湿地总面积（hm2）",example = "100.23")
    private String wetTotalArea;
    //湿地斑块数量（个）
    @ApiModelProperty(value = "湿地斑块数量（个）",example = "100")
    private String wetPlaqueCount;
    //湿地类1
    @ApiModelProperty(value = "湿地类1",example = "湿地类1")
    private String wetType1;
    //类面积1（hm2）
    @ApiModelProperty(value = "类面积1（hm2）",example = "20")
    private String wetTypeArea1;
    //主要湿地型1
    @ApiModelProperty(value = "主要湿地型1",example = "主要湿地型1")
    private String wetModel1;
    //行面积1（hm2）
    @ApiModelProperty(value = "行面积1（hm2）",example = "10")
    private String wetModelArea1;
    //湿地类2
    @ApiModelProperty(value = "湿地类2",example = "湿地类2")
    private String wetType2;
    //类面积2（hm2）
    @ApiModelProperty(value = "类面积2（hm2）",example = "30")
    private String wetTypeArea2;
    //主要湿地型2
    @ApiModelProperty(value = "主要湿地型2",example = "主要湿地型2")
    private String wetModel2;
    //行面积2（hm2）
    @ApiModelProperty(value = "行面积2（hm2）",example = "10")
    private String wetModelArea2;

    //分布-省
    @ApiModelProperty(value = "分布-省",example = "440000")
    private String provinceCode;
    //分布-市
    @ApiModelProperty(value = "分布-市",example = "440300")
    private String cityCode;
    //分布-区
    @ApiModelProperty(value = "分布-区",example = "440303")
    private String areaCode;
    //分布-区域中文
    @ApiModelProperty(value="分布-区域中文",example = "广东省-深圳市-罗湖区")
    private String regionInCh;
    //北纬
    @ApiModelProperty(value = "北纬",example = "101.254568")
    private String northLatitude;
    //东经
    @ApiModelProperty(value = "东经",example = "131.254518")
    private String eastLongitude;
    //所属二级流域
    @ApiModelProperty(value = "所属二级流域",example = "二级流域1")
    private String secondBasin;
    //河流级别
    @ApiModelProperty(value = "河流级别",example = "级别1")
    private String riverLevel;
    //平均海拔
    @ApiModelProperty(value = "平均海拔",example = "500")
    private String avgAltitude;
    //水源补给情况
    @ApiModelProperty(value = "水源补给情况",example = "地表径流,大气降水,综合补给")
    private String waterSupply;

    //近海域海岸湿地
    @ApiModelProperty(value = "近海域海岸湿地",example = "半日潮")
    private String wetNearSea;
    //盐度%
    @ApiModelProperty(value = "盐度%",example = "20")
    private String salinity;
    //水温（℃）
    @ApiModelProperty(value = "水温（℃）",example = "45.2")
    private String waterTemp;
    //土地所有权
    @ApiModelProperty(value = "土地所有权",example = "国有")
    private String landOwnership;
    //植被类型
    @ApiModelProperty(value = "植被类型",example = "植被类型1")
    private String plantType;
    //植被面积
    @ApiModelProperty(value = "植被面积",example = "650")
    private String plantArea;
    //中文学名
    @ApiModelProperty(value = "中文学名",example = "植物1")
    private String nameInCh;
    //拉丁学名
    @ApiModelProperty(value = "拉丁学名",example = "latin植物1")
    private String latinName;
    //科名
    @ApiModelProperty(value = "科名",example = "family植物1")
    private String familyName;
    //状态0：保存；1：撤回；2：上报；3：市级退回；4：市级审核；5：省级退回；6：省级通过；7：总站退回；8：总站通过；9：专家批复
    @ApiModelProperty("状态0：保存；1：撤回；2：上报；3：市级退回；4：市级审核；5：省级退回；6：省级通过；7：总站退回；8：总站通过；9：专家批复")
    private String status;
    //状态中文值
    @ApiModelProperty("状态0：保存；1：撤回；2：上报；3：市级退回；4：市级审核；5：省级退回；6：省级通过；7：总站退回；8：总站通过；9：专家批复")
    private String statusName;
    @ApiModelProperty("调查时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
    @ApiModelProperty("调查人")
    private String createUserName;

    private List<ProcessVo> processList;
}
