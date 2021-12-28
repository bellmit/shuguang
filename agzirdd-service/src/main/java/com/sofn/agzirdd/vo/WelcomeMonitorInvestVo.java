package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class WelcomeMonitorInvestVo {

    @ApiModelProperty(value = "数量")
    private Integer num;

    @ApiModelProperty(value="物种链表")
    private Map<String,WelcomeSpeciesVo> map;

    public WelcomeMonitorInvestVo(){
        this.num = 0;
        this.map = new HashMap();
        map.put("1", new WelcomeSpeciesVo("1"));
        map.put("2", new WelcomeSpeciesVo("2"));
        map.put("3", new WelcomeSpeciesVo("3"));
    }

    public void setData(WelcomeMapVo wmv,boolean isMonitor) {
        if(!isMonitor){
            if("植物".equals(wmv.getSpeciesType()))
                wmv.setSpeciesType("1");
            else if("动物".equals(wmv.getSpeciesType()))
                wmv.setSpeciesType("2");
            else if("微生物".equals(wmv.getSpeciesType()))
                wmv.setSpeciesType("3");
        }
        WelcomeSpeciesVo wsv = map.get(wmv.getSpeciesType());
        if(wsv==null)   //未知生物类型，不组装数据
            return;

        wsv.getSpeciesNames().add(wmv.getSpeciesName());

        map.put(wsv.getSpeciesType(), wsv);

        this.num++;
    }

    public List<WelcomeSpeciesVo> getList() {
        return map.values().stream().collect(Collectors.toList());
    }

    private Map<String, WelcomeSpeciesVo> getMap() {
        return null;
    }
}
