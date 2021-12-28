package com.sofn.common.tree;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Data
public class Node implements TreeNode {
  /**
   * 当前节点id
   */
  private String id;

  /**
   * 父节点id
   */
  private String parentId;

  /**
   * 名称
   */
  private String name;

  private List<Node> children = new ArrayList<>();

  public Node(String id, String parentId, String name) {
    this.id = id;
    this.parentId = parentId;
    this.name = name;
  }

  @Override
  public void setChildren(List children) {

  }
}
