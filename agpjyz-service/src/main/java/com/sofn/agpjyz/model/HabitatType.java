package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 生境类型类
 *
 * @Author yumao
 * @Date 2020/3/9 17:13
 **/
@Data
@TableName("HABITAT_TYPE")
public class HabitatType {

    /**
     * 主键
     */
    private String id;
    /**
     * 资源调查ID
     */
    private String sourceId;
    /**
     * 生境类型ID
     */
    private String habitatId;
    /**
     * 生境类型名称
     */
    private String habitatValue;
}
