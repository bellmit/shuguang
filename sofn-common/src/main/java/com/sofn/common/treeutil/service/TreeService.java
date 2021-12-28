package com.sofn.common.treeutil.service;

import com.sofn.common.treeutil.basic.BasicTreeBean;

import java.util.List;
import java.util.Map;

/**
 * Tree查询服务
 * @author heyongjie
 * @date 2019/11/28 16:12
 */
public interface TreeService<T extends BasicTreeBean> {

    /**
     * 根据ID查询信息
     * @param ids  需要查询的IDS
     * @return    List<T>
     */
    List<T> getInfoByIds(List<String> ids);

    /**
     * 按条件查询树信息  这个方法用于生成树时的条件查询
     * 因为这个方法可能会涉及到翻译值的操作，使用的时候可能因Sql原因执行的较慢
     * 其他方法尽量不要直接使用这个方法进行条件查询
     * @param params  参数，这个参数由使用者定义
     * @return   List<T>
     */
    List<T> getInfoByCondition(Map<String,Object> params);

    /**
     * 根据ID集合删除树信息
     * @param ids  ID集合
     */
    void deleteByIds(List<String> ids);

    /**
     * 查询所有父ID为parentIds的列表 ，如根据所有一级节点查询所有的二级节点
     * @param parentIds  父ID
     * @return    List<T>
     */
    List<T> getInfoByParentId(List<String> parentIds);

    /**
     * 根据ParentIds 模糊查询节点信息
     * @param parentIds  parentIds 0/1/1/   请使用:parentIds% 这种模糊查询
     * @return    List<T>
     */
    List<T> getInfoByLikeParentIds(String parentIds);


    /**
     * 修改ParentIds字段的值   如果不需要可不实现这个方法
     * @param parentIds   修改后的值
     * @param ids   哪些数据的ParentIds需要更改
     */
    void updateParentId(String parentIds,List<String> ids);


}
