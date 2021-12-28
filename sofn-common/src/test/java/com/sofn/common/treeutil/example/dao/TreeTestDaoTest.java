//package com.sofn.common.treeutil.example.dao;
//
//import com.google.common.collect.Maps;
//import com.sofn.common.treeutil.basic.TreeGenerate;
//import com.sofn.common.treeutil.enums.TreeEnums;
//import com.sofn.common.treeutil.example.vo.SysTreeTestVo;
//import com.sofn.common.treeutil.service.TreeService;
//import com.sofn.common.utils.IdUtil;
//import org.junit.Test;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @author heyongjie
// * @date 2019/11/29 16:13
// */
//public class TreeTestDaoTest {
//
//    private TreeService<SysTreeTestVo> treeTestVoTreeService = new TreeTestDao();
//
//    /**
//     * 排序工具
//     * 默认的根节点ID 和 ID之间的分隔符为root、/  如果和这些值不一样，请使用有参构造器更改
//     */
//    private  TreeGenerate<SysTreeTestVo> treeTestVoTreeGenerate =
//            new TreeGenerate<>(TreeEnums.PARENT_IDS_SPLIT_STR.getCode(),"0",TreeEnums.SORT_DESC.getCode());
//
//    @Test
//    public void toTreeTest(){
//        List<SysTreeTestVo> infoByCondition = treeTestVoTreeService.getInfoByCondition(null);
//
//
//        SysTreeTestVo sysTreeTestVo = treeTestVoTreeGenerate.toTree(infoByCondition);
//        System.out.println(sysTreeTestVo);
//    }
//
//    @Test
//    public void toTreeByConditonTest(){
//        Map<String,Object> params = Maps.newHashMap();
//        params.put("name","四级节点");
//        SysTreeTestVo sysTreeTestVo = treeTestVoTreeGenerate.toTreeByCondition(params,treeTestVoTreeService);
//        System.out.println(sysTreeTestVo);
//    }
//
//    @Test
//    public void testSave(){
//        // == 前端传递的参数
//        SysTreeTestVo sysTreeTestVo = new SysTreeTestVo();
//        sysTreeTestVo.setName("四级节点1-1-1-1");
//        sysTreeTestVo.setParentId("4");
//        sysTreeTestVo.setRemark("测试添加节点");
//        sysTreeTestVo.setId(IdUtil.getUUId());
//        sysTreeTestVo.setSort(0);
//        // == 树工具主要是生成parentIds和判断父节点是否存在
//        treeTestVoTreeGenerate.setTreeInfo(sysTreeTestVo,treeTestVoTreeService);
//        System.out.println(sysTreeTestVo);
//        // 后面执行添加各自的添加操作
//
//
//    }
//
//    @Test
//    public void testDelete(){
//        // 有子节点不能删除
////        treeTestVoTreeGenerate.deleteNotChildrenTree("4",treeTestVoTreeService);
//
//
//        treeTestVoTreeGenerate.deleteHaveChildrenTree("1",treeTestVoTreeService);
//    }
//
//
//
//
//}