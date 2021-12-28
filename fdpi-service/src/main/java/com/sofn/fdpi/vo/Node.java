package com.sofn.fdpi.vo;

import lombok.Data;

import java.util.List;

@Data
public class Node {
    private String id;
    private List<Node> node;
}
