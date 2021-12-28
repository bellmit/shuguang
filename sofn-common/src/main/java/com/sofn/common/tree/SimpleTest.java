package com.sofn.common.tree;

import com.sofn.common.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *@Description TODO
 *@Author quzhijie
 *@Date 2020/3/23 13:36
 *@Version 1.0
 **/
public class SimpleTest {
  public static void main(String[] args){
    long startTime = Calendar.getInstance().getTimeInMillis();
    TreeUtil<NodeTest> treeUtil = new TreeUtil<>();
    List<NodeTest> nodeList = treeUtil.generate(getTestData1());
    System.out.println(JsonUtils.obj2json(nodeList));
    long endTime = Calendar.getInstance().getTimeInMillis();
    System.out.println("cast time:"+(endTime-startTime));
  }

  private static List<NodeTest> getTestData1(){
    List<NodeTest> list = new ArrayList<>();
    list.add(new NodeTest("1","0","一级节点 1"));
    list.add(new NodeTest("2","0","一级节点 2"));
    list.add(new NodeTest("3","0","一级节点 3"));
    list.add(new NodeTest("4","0","一级节点 4"));
    list.add(new NodeTest("5","0","一级节点 5"));

    list.add(new NodeTest("1-1","1","一级节点 1-1"));
    list.add(new NodeTest("1-2","1","一级节点 1-2"));
    list.add(new NodeTest("1-2-1","1-2","一级节点 1-2-1"));
    list.add(new NodeTest("1-2-2","1-2-1","一级节点 1-2-2"));
    list.add(new NodeTest("1-2-2-1","1-2-2","一级节点 1-2-2-1"));
    list.add(new NodeTest("1-3","1","一级节点 1-3"));
    list.add(new NodeTest("1-4","1","一级节点 1-4"));
    list.add(new NodeTest("1-5","1","一级节点 1-5"));

    list.add(new NodeTest("2-1","2","一级节点 2-1"));
    list.add(new NodeTest("2-2","2","一级节点 2-2"));
    list.add(new NodeTest("2-3","2","一级节点 2-3"));
    list.add(new NodeTest("2-4","2","一级节点 2-4"));

    return list;
  }

  public static class NodeTest implements TreeNode<NodeTest> {
    private String id;
    private String pid;
    private String text;
    private List<NodeTest> children;
    private String desc;

    public NodeTest(String id, String pid, String text) {
      this.id = id;
      this.pid = pid;
      this.text = text;
    }

    @Override
    public String getParentId() {
      return pid;
    }

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getName() {
      return text;
    }

    @Override
    public void setChildren(List<NodeTest> children) {
      this.children = children;
    }

    public List<NodeTest> getChildren() {
      return children;
    }
  }
}
