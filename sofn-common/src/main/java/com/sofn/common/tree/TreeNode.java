package com.sofn.common.tree;

import java.util.List;

/**
 *@Description TODO
 *@Author quzhijie
 *@Date 2020/3/23 11:10
 *@Version 1.0
 **/
public interface TreeNode<T> {
  String getParentId();
  String getId();
  String getName();
  void setChildren(List<T> children);
}
