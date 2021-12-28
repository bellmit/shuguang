package com.sofn.common.treeutil.example.vo;

import com.sofn.common.treeutil.basic.BasicTreeBean;
import com.sofn.common.treeutil.example.entity.SysTreeTest;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 树对象
 * @author heyongjie
 * @date 2019/11/29 11:24
 */
@Data
public class SysTreeTestVo extends BasicTreeBean<SysTreeTestVo> {

    /**
     * 节点名字
     */
    private String name;

    /**
     * 节点备注
     */
    private String remark;

    public SysTreeTestVo(){}
    /**
     * 构造器
     * @param id  ID
     * @param parentId   父节点
     * @param parentIds   父节点IDS
     * @param sort   排序
     */
    public SysTreeTestVo(String id, String parentId, String parentIds, Integer sort,String rootLevel) {
        super(id,parentId,parentIds,sort,rootLevel);
    }

    /**
     * 将DTO转为VO
     * @param sysTreeTest  DTO
     * @param rootLevel  根节点代码
     * @return   VO
     */
    public static SysTreeTestVo getSysTreeTestVo(SysTreeTest sysTreeTest,String rootLevel){
        SysTreeTestVo sysTreeTestVo = new SysTreeTestVo(
                sysTreeTest.getId(),
                sysTreeTest.getParentId(),
                sysTreeTest.getParentIds(),
                sysTreeTest.getSort(),
                rootLevel
        );
        BeanUtils.copyProperties(sysTreeTest,sysTreeTestVo);
        return sysTreeTestVo;
    }


}
