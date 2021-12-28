package com.sofn.model.dgap;

/**
 * author tianjie
 *
 * @date 2016/11/28
 */
public class ZTreeNode {

    private String id;
    private String pId;
    private String name;
    private String checked;
    private String open;

    public ZTreeNode(String id, String pId, String name, String checked, String open) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.checked = checked;
        this.open = open;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
