package com.sofn.sys.dto;

/**
 *
 * @author cjbi
 */
public class TreeDto {

    private String id;
    private String pId;
    private String name;
    private boolean parent;
    private Object obj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public TreeDto() {

    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public boolean getIsParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public TreeDto(String id, String pId, String name, boolean parent, Object obj) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.parent = parent;
        this.obj = obj;
    }

}
