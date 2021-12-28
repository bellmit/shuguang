package com.sofn.common.tree;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class TreeUtil<T extends TreeNode<T>> {

  public List<T> generate(List<T> list) {
    if (CollectionUtils.isEmpty(list)) {
      return  new ArrayList<>();
    }

    List<T> returnList = new ArrayList<>();
    List<T> rootNodeList = findRootNode(list);
    for (T res : rootNodeList) {
      if (res == null) {
        continue;
      }

      recursionFn(list, res);
      returnList.add(res);
    }
    return returnList;
  }

  private List<T> findRootNode(List<T> list) {
    List<T> results = new ArrayList<T>();
    for (T node : list) {
      boolean isRoot = true;
      for (T comparedOne : list) {
        if (StringUtils.equals(node.getParentId(), comparedOne.getId())) {
          isRoot = false;
          break;
        }
      }

      if (isRoot) {
        results.add(node);
      }
    }
    return results;
  }

  /**
   * 递归寻找子节点
   * @Title: recursionFn
   * @Description: 递归寻找子节点
   * @param beans 所有节点
   * @param current 当前节点
   */
  private void recursionFn(List<T> beans, T current) {
    List<T> childsList = getChildList(beans, current);
    current.setChildren(childsList);
    for (T nextChild : childsList) {
      recursionFn(beans, nextChild);
    }
  }

  /**
   * 获得指定节点下的所有子节点
   * @Title: getChildList
   * @Description: 获得指定节点下的所有子节点
   * @param beans 所有节点集合
   * @param current 当前节点
   * @return
   */
  private List<T> getChildList(List<T> beans, T current) {
    List<T> childsList = new ArrayList<>();
    for (T child : beans) {
      if (current.getId().equals(child.getParentId())) {
        childsList.add(child);
      }
    }
    return childsList;
  }

}
