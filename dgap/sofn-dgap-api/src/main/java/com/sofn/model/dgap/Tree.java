package com.sofn.model.dgap;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成资源管理中的资源目录json
 *
 * @date 2016/11/1
 */
public class Tree {
    private String id;
    private String text;
    private List<Tree> children = new ArrayList<Tree>();

    public Tree(){

    }

    public Tree(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public void addChild(Tree tree){
        children.add(tree);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
