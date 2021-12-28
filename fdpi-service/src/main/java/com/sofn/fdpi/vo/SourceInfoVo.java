package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @Description 溯源图对应字段名的数据格式
 * @Author wg
 * @Date 2021/6/4 10:32
 **/
@Data
@ApiModel("溯源图对应字段名的数据格式")
public class SourceInfoVo {
    private List<Nodes> nodes;
    private List<Edges> edges;
}
