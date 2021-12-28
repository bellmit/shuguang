package com.sofn.fdpi.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 进口企业溯源图数据格式对象
 * @Author wg
 * @Date 2021/5/24 11:09
 **/
@Data
public class ImportTraceToSourceVo implements Serializable {
    private List<Source> sources;
    private List<Target> targets;
}
