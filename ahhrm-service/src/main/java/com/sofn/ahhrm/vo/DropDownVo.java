package com.sofn.ahhrm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 下拉列表
 * @Author: WXY
 * @Date: 2020-2-25 19:40:26
 */
@Data
@ApiModel
public class DropDownVo {
    //id
    @ApiModelProperty("id")
    private String id;

    //名称
    @ApiModelProperty("值")
    private String name;

    public static  List<DropDownVo> string2DropDownVo(List<String> list){
        List<DropDownVo> res = null;
        if (CollectionUtils.isEmpty(list)) {
            res = new ArrayList<>(1);
            return res;
        }
        res = new ArrayList<>(list.size());
        for (String str : list) {
            DropDownVo ddv = new DropDownVo();
            ddv.setId(str);
            ddv.setName(str);
            res.add(ddv);
        }
        return res;
    }
}
