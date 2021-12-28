package com.sofn.agsjsi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("wet_example_point_collect")
public class WetExamplePointCollect extends BaseModel<WetExamplePointCollect> {
    private String id;
    //湿地区名称
    private String wetName;
    //湿地区编码
    private String wetCode;
    //湿地总面积（hm2）
    private BigDecimal wetTotalArea;
    //湿地斑块数量（个）
    private Integer wetPlaqueCount;
    //湿地类1
    private String wetType1;
    //类面积1（hm2）
    private BigDecimal wetTypeArea1;
    //主要湿地型1
    private String wetModel1;
    //行面积1（hm2）
    private BigDecimal wetModelArea1;
    //湿地类2
    private String wetType2;
    //类面积2（hm2）
    private BigDecimal wetTypeArea2;
    //主要湿地型2
    private String wetModel2;
    //行面积2（hm2）
    private BigDecimal wetModelArea2;
    //分布-省
    private String provinceCode;
    //分布-市
    private String cityCode;
    //分布-区
    private String areaCode;
    //分布-区域中文
    private String regionInCh;
    //北纬
    private BigDecimal northLatitude;
    //东经
    private BigDecimal eastLongitude;
    //所属二级流域
    private String secondBasin;
    //河流级别
    private String riverLevel;
    //平均海拔
    private BigDecimal avgAltitude;
    //水源补给情况
    private String waterSupply;
    //近海域海岸湿地
    private String wetNearSea;
    //盐度%
    private BigDecimal salinity;
    //水温（℃）
    private BigDecimal waterTemp;
    //土地所有权
    private String landOwnership;
    //植被类型
    private String plantType;
    //植被面积
    private BigDecimal plantArea;
    //中文学名
    private String nameInCh;
    //拉丁学名
    private String latinName;
    //科名
    private String familyName;
    //状态0：保存；1：撤回；2：上报；3：市级退回；4：市级审核；5：省级退回；6：省级通过；7：总站退回；8：总站通过；9：专家批复
    private String status;
    private String createUserName;
    private String updateUserName;
    //当前用户机构的行政区划-省
    private String orgProvinceCode;
    //当前用户机构的行政区划-市
    private String orgCityCode;
    //当前用户机构的行政区划-区
    private String orgAreaCode;
}
