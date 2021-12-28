package com.sofn.common.treeutil.enums;

import lombok.Getter;

/**
 * @author heyongjie
 * @date 2019/11/28 16:05
 */
@Getter
public enum TreeEnums {

    /**
     * 默认的根节点代码
     */
    DEFAULT_ROOT_NODE("root","默认的根节点"),

    /**
     * 父节点ID之间的间隔字符串
     */
    PARENT_IDS_SPLIT_STR("/","父节点ID之间的间隔字符串"),

    /**
     * 正序排序
     */
    SORT_ASC("asc","正序排序"),

    /**
     * 倒序排序
     */
    SORT_DESC("desc","倒序排序"),

    ;

    private String code;

    private String des;

    TreeEnums(String code,String des){
        this.code = code;
        this.des = des;
    }


}
