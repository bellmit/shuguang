package com.sofn.common.treeutil.basic;

import com.sofn.common.exception.SofnException;
import com.sofn.common.treeutil.enums.TreeEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * TreeBean
 * @author heyongjie
 * @date 2019/11/28 15:59
 */
@Data
public class BasicTreeBean<T extends BasicTreeBean> {

    /**
     * TreeId
     */
    @ApiModelProperty(value = "ID")
    private String id;

    /**
     * 父ID
     */
    @ApiModelProperty(value = "父编号")
    private String parentId;

    /**
     * 父IDS    用/分隔开
     */
    @ApiModelProperty(value = "父编号列表")
    private String parentIds;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 子节点
     */
    @ApiModelProperty(value = "下级子节点")
    private List<T> children;

    public BasicTreeBean(){}

    /**
     * 构造器, 会校验参数是否合法
     * @param id  ID
     * @param parentId   父节点
     * @param parentIds   父节点IDS
     * @param sort   排序
     * @param rootLevel  跟节点代码
     */
    public BasicTreeBean(String id, String parentId, String parentIds, Integer sort,String rootLevel) {
        this.id = id;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.sort = sort;
        check(rootLevel);
    }

    /**
     * 校验参数
     * @param rootLevel    校验参数
     */
    public void check(String rootLevel){
        if(StringUtils.isBlank(id)){
            throw new SofnException("转化树失败，ID为空");
        }
        if(StringUtils.isBlank(rootLevel)){
            rootLevel = TreeEnums.DEFAULT_ROOT_NODE.getCode();
        }
        if(!this.id.equals(rootLevel)){
            if(StringUtils.isBlank(parentId)){
                throw new SofnException("转化树失败，parentId为空");
            }
            if(StringUtils.isBlank(parentIds)){
                throw new SofnException("转化树失败，parentIds为空");
            }
        }
    }


}
