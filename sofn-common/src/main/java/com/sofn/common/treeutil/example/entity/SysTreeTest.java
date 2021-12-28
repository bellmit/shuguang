package com.sofn.common.treeutil.example.entity;

import lombok.Data;

/**
 * SYS_TREE_TEST
 * @author heyongjie
 * @date 2019/11/29 11:21
 */
@Data
public class SysTreeTest {

    /**
     * ID
     */
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 父节点
     */
    private String parentId;

    /**
     * 父节点IDS
     */
    private String parentIds;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    // ==============其他字段
}
