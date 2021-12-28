package com.sofn.common.treeutil.basic;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.treeutil.enums.TreeEnums;
import com.sofn.common.treeutil.service.TreeService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 树生成器
 *
 * @author heyongjie
 * @date 2019/11/28 16:16
 */
@Data
@Slf4j
public class TreeGenerate<T extends BasicTreeBean> {

    /**
     * IDS之间的间隔
     */
    private String idsSplitStr = TreeEnums.PARENT_IDS_SPLIT_STR.getCode();

    /**
     * 跟节点
     */
    private String rootLevel = TreeEnums.DEFAULT_ROOT_NODE.getCode();

    /**
     * 排序
     */
    private String sort = TreeEnums.SORT_ASC.getCode();

    public TreeGenerate() {
    }

    /**
     * 构造器
     *
     * @param idsSplitStr 多个ID之间的间隔符
     * @param rootLevel   根节点
     */
    public TreeGenerate(String idsSplitStr, String rootLevel) {
        if (!StringUtils.isBlank(idsSplitStr)) {
            this.idsSplitStr = idsSplitStr;
        }
        if (!StringUtils.isBlank(rootLevel)) {
            this.rootLevel = rootLevel;
        }
    }

    /**
     * 指定排序方式
     *
     * @param idsSplitStr 多个ID之间的间隔符
     * @param rootLevel   根节点
     * @param sort        排序方式
     */
    public TreeGenerate(String idsSplitStr, String rootLevel, String sort) {
        this(idsSplitStr, rootLevel);
        if (TreeEnums.SORT_ASC.getCode().equals(sort) || TreeEnums.SORT_DESC.getCode().equals(sort)) {
            this.sort = sort;
        } else {
            log.warn("传入的排序方式【{}】不合法，使用默认的排序方式", sort);
            this.sort = TreeEnums.SORT_ASC.getCode();
        }
    }


    /**
     * 设置树属性
     *
     * @param t 需要给那个对象设置树属性
     * @return 设置好的树属性
     */
    public void setTreeInfo(T t, TreeService treeService) {
        if (t == null) {
            throw new SofnException("无法设置树属性，因为该对象为空");
        }
        if (treeService == null) {
            throw new SofnException("查询树信息的服务不能为空");
        }

        if (StringUtils.isBlank(t.getParentId())) {
            // 如果父ID为空那么就默认为一级节点
            t.setParentId(this.rootLevel);
            t.setParentIds(this.rootLevel + this.idsSplitStr);
        } else {
            // 如果不为空且不是一级节点则要判断该节点是否存在
            if (!this.rootLevel.equals(t.getParentId())) {
                List<T> infoByIds = treeService.getInfoByIds(Lists.newArrayList(t.getParentId()));
                if (CollectionUtils.isEmpty(infoByIds)) {
                    throw new SofnException("父节点不存在");
                }
                T parentInfo = infoByIds.get(0);
                t.setParentIds(parentInfo.getParentIds() + parentInfo.getId() + this.idsSplitStr);
            } else {
                t.setParentIds(this.rootLevel + this.idsSplitStr);
            }
        }
    }


    /**
     * 根据父Id 获取所有的子节点  包含传入的parentId
     *
     * @param parentId    父ID
     * @param treeService treeService
     * @return
     */
    public List<String> getAllNodeByParentId(String parentId, TreeService treeService) {
        T t = checkDeleteParams(parentId, treeService);
        String parentIds = t.getParentIds();
        if (StringUtils.isBlank(parentIds) && !this.rootLevel.equals(t.getId())) {
            log.error("节点ID{}", parentId);
            throw new SofnException("节点：" + parentId + "的ParentIds字段为空，可以使用树工具的updateDatabaseParentIdsColumn方法统一添加");
        }
        // 根节点的parentIds可以为空   如果为空那么就查询rootLevel/ 开头的节点
        parentIds = StringUtils.isBlank(parentIds) ?
                this.rootLevel + this.idsSplitStr : parentIds + t.getId() + this.idsSplitStr;
        List<T> sonNode = (List<T>) treeService.getInfoByLikeParentIds(parentIds);
        List<String> allIds = null;
        if (!CollectionUtils.isEmpty(sonNode)) {
            allIds = sonNode.stream().map(T::getId).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(allIds)) {
            allIds = Lists.newArrayList();
        }
        allIds.add(parentId);
        return allIds;
    }

    /**
     * 删除没有子节点的树，如果有子节点直接报错
     *
     * @param id          ID
     * @param treeService 查询接口
     */
    public void deleteNotChildrenTree(String id, TreeService treeService) {
        List<String> allIds = getAllNodeByParentId(id, treeService);
        if (!CollectionUtils.isEmpty(allIds)) {
            throw new SofnException("当前节点有子节点不能删除");
        }
        treeService.deleteByIds(allIds);
    }

    /**
     * 删除有子节点的树
     *
     * @param id          要删除的数据
     * @param treeService 树接口
     */
    public void deleteHaveChildrenTree(String id, TreeService treeService) {
        List<String> allIds = getAllNodeByParentId(id, treeService);
        treeService.deleteByIds(allIds);
    }


    public T toTreeByCondition(Map<String, Object> params, TreeService treeService, String rootLevel) {
        return toTreeByCondition(params, treeService, rootLevel, "N");
    }

    /**
     * 根据条件获取树结构  从根节点开始显示
     *
     * @param params      参数
     * @param treeService tree查询服务
     * @param rootLevel   根节点代码   可以从中间开始生成  如果为空就为构造TreeGenerate时的跟节点代码
     * @param isChildren  是否携带子节点   Y  携带子节点，N不携带
     * @return 从根节点开始的树
     */
    public T toTreeByCondition(Map<String, Object> params, TreeService treeService, String rootLevel, String isChildren) {
        if (treeService == null) {
            throw new SofnException("treeService不能为空");
        }
        List<T> infoByCondition = treeService.getInfoByCondition(params);
        if (!CollectionUtils.isEmpty(infoByCondition)) {
            // 需要查询的ID
            Set<String> collect = infoByCondition.stream().map(T::getId).collect(Collectors.toSet());
            if (CollectionUtils.isEmpty(collect)) {
                collect = Sets.newHashSet();
            }
            // 将所有的ParenId 查找出来
            // JAVA 8 FOREACH 循环里面的变量默认是final  collect上面改变过指向！ 所以单独接收
            Set<String> finalCollect = collect;
            infoByCondition.forEach((info) -> {
                if (info != null) {
                    // 找父ID
                    String parentIds = info.getParentIds();
                    if (StringUtils.isBlank(parentIds)) {
                        if (this.rootLevel.equals(info.getId())) {
                            parentIds = this.rootLevel + this.idsSplitStr;
                        } else {
                            log.error("节点ID{}", info.getId());
                            throw new SofnException("节点：" + info.getId() + "的ParentIds字段为空，可以使用树工具的updateDatabaseParentIdsColumn方法统一添加");
                        }
                    }
                    if (!StringUtils.isBlank(parentIds)) {
                        List<String> idsByStr = IdUtil.getIdsByStr(parentIds, this.idsSplitStr);
                        finalCollect.addAll(idsByStr);
                    }
                    // 找子节点
                    if (BoolUtils.Y.equals(isChildren)) {
                        String parentIds1 = "";
                        // 如果当前节点是跟节点那么parentIds1 就是root/ 否则是root/Ids/
                        if (!this.rootLevel.equals(info.getId())) {
                            parentIds1 = parentIds + info.getId() + this.idsSplitStr;
                        }
                        List<T> infoByLikeParentIds = treeService.getInfoByLikeParentIds(parentIds1);
                        if (!CollectionUtils.isEmpty(infoByLikeParentIds)) {
                            List<String> collect1 = infoByLikeParentIds.stream().map(T::getId).collect(Collectors.toList());
                            finalCollect.addAll(collect1);
                        }
                    }
                }
            });
            // 根据ID查询出信息
            List<T> infoByIds = treeService.getInfoByIds(Lists.newArrayList(finalCollect));

            // 这里多级的时候 还需要在继续循环找上一级免得漏掉一层
            Set<String> pParentIds = Sets.newHashSet();
            boolean flag = true;
            int num = 10;
            while (flag) {
                num--;
                flag = false;
                if (!CollectionUtils.isEmpty(infoByIds)) {
                    Set<String> collect1 = infoByIds.stream().map(T::getId).collect(Collectors.toSet());
                    for (T infoById : infoByIds) {
                        if (infoById != null && !StringUtils.isBlank(infoById.getParentId())) {
                            if (!StringUtils.isBlank(infoById.getParentId()) && !collect1.contains(infoById.getParentId()) && !"''".equals(infoById.getParentId())) {
                                log.info("死循环检测：需要重新获取Id为：{}的ParentId为：{}", infoById.getId(), infoById.getParentId());
                                pParentIds.add(infoById.getParentId());
                                flag = true;
                            }
                        }
                    }
                }
                if (!CollectionUtils.isEmpty(pParentIds)) {
                    List<T> infoByIds1 = treeService.getInfoByIds(Lists.newArrayList(pParentIds));
                    if (!CollectionUtils.isEmpty(infoByIds1)) {
                        infoByIds.addAll(infoByIds1);
                        pParentIds.clear();
                    }
                }

                // 最多只找10次， 超过10次  就不找了
                if (num < 0) {
                    flag = false;
                }
            }
            return toTree(infoByIds, rootLevel);
        }
        return null;
    }

    /**
     * 更新数据表中的parentIds
     * 注意： 数据库中所有的未删除数据都会更改
     * 因方法是维护性数据表方法，所以可以考虑在单元测试中使用
     * 因为有可能之前的表并没有考虑parentId字段，可以添加这个字段后执行这个方法自动将值给添加上
     *
     * @param treeService 各个功能实现的TreeService
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateDatabaseParentIdsColumn(TreeService treeService) {
        if (treeService != null) {
            // 查询根节点下的所有  然后一级一级往下查
            List<T> infoByParentId = treeService.getInfoByParentId(Lists.newArrayList(this.rootLevel));
            List<T> newInfoByParentId = infoByParentId.stream().filter(item -> !item.getId().equals(this.rootLevel)).collect(Collectors.toList());
            String parentIds = this.rootLevel + this.idsSplitStr;
            recursionUpdateNode(newInfoByParentId, parentIds, treeService);
        }
    }


    /**
     * 将数据转为树结构
     *
     * @param basicTreeBeans 继承BasicTreeBean的数据
     * @param rootLevelId    需要的根节点ID
     * @return 根节点
     */
    public T toTree(List<T> basicTreeBeans, String rootLevelId) {
        // 1. 判断根节点是否存在
        if (StringUtils.isBlank(rootLevelId)) {
            rootLevelId = this.rootLevel;
        }
        if (!CollectionUtils.isEmpty(basicTreeBeans)) {
            // 1. 准备基础数据
            // 根节点
            T basicTreeBean = null;
            // 一级节点
            List<T> basicTreeSets = Lists.newArrayList();
            // 其他节点
            Multimap<String, T> multimap = ArrayListMultimap.create();
            for (T tree : basicTreeBeans) {
                if (tree != null) {
                    if (rootLevelId.equals(tree.getId())) {
                        // 跟节点
                        basicTreeBean = tree;
                    } else if (rootLevelId.equals(tree.getParentId())) {
                        // 一级节点
                        basicTreeSets.add(tree);
                    } else {
                        // 其他节点
                        multimap.put(tree.getParentId(), tree);
                    }
                }
            }
            if (basicTreeBean == null) {
                throw new SofnException("根节点信息不能为空");
            }
            if (CollectionUtils.isEmpty(basicTreeSets)) {
                basicTreeSets = Lists.newArrayList();
            }
            basicTreeSets.sort(comparator);
            // 2. 递归生成树
            toTree(basicTreeSets, multimap);
            basicTreeBean.setChildren(basicTreeSets);
            return basicTreeBean;
        }

        return null;
    }

    // =============================分割线 下面的方法未暴露==============================================

    /**
     * 递归更新剩下的节点
     *
     * @param infoByParentId 上级节点
     * @param parentIds      上级节点的ParentIds
     * @param treeService    各个功能实现的TreeService
     */
    private void recursionUpdateNode(List<T> infoByParentId, String parentIds, TreeService treeService) {
        if (!CollectionUtils.isEmpty(infoByParentId)) {
            // 将这些数据的ParentIds字段统一更新
            List<String> updateIds = infoByParentId.stream().map(T::getId).collect(Collectors.toList());
            treeService.updateParentId(parentIds, updateIds);
            // 查找下一级  继续更新
            infoByParentId.forEach((treeInfo) -> {
                String parentId = treeInfo.getId();
                String sonParentIds = parentIds + parentId + this.idsSplitStr;
                List<T> infoByParentId1 = treeService.getInfoByParentId(Lists.newArrayList(parentId));
                recursionUpdateNode(infoByParentId1, sonParentIds, treeService);
            });
        }
    }

    /**
     * 树结构排序
     */
    private Comparator comparator = (Comparator<T>) (o1, o2) -> {
        if (o1 == null || o2 == null) {
            return 0;
        }
        if (o1.getSort() == null || o2.getSort() == null) {
            return 0;
        }
        if (TreeEnums.SORT_ASC.getCode().equals(this.sort)) {
            return o1.getSort() - o2.getSort();
        }
        return o2.getSort() - o1.getSort();
    };

    /**
     * 递归生成tree
     *
     * @param basicTreeSets 上级节点
     * @param multimap      所有的子节点
     * @return List<BasicTreeBean>
     */
    private <T extends BasicTreeBean> List<T> toTree(List<T> basicTreeSets, Multimap<String, T> multimap) {
        if (!CollectionUtils.isEmpty(basicTreeSets)) {
            for (T basicTreeSet : basicTreeSets) {
                String id = basicTreeSet.getId();
                List<T> basicTreeBeans = (List<T>) multimap.get(id);
                basicTreeBeans.sort(comparator);
                toTree(basicTreeBeans, multimap);
                basicTreeSet.setChildren(basicTreeBeans);
            }
        }
        return basicTreeSets;
    }

    /**
     * 校验删除的参数
     *
     * @param id          待删除ID
     * @param treeService 查询接口
     * @return 查询到的信息
     */
    private T checkDeleteParams(String id, TreeService treeService) {
        if (treeService == null) {
            throw new SofnException("查询树信息的服务不能为空");
        }
        if (StringUtils.isBlank(id)) {
            throw new SofnException("ID必传");
        }
        List<T> infoByIds = treeService.getInfoByIds(Lists.newArrayList(id));
        if (CollectionUtils.isEmpty(infoByIds)) {
            throw new SofnException("未查询到该节点信息");
        }
        return infoByIds.get(0);
    }

}
