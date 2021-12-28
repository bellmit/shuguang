package com.sofn.ducss.enums;

/**
 * @PackageName com.sofn.mainbody.common.enums
 * @ClassName FormManagerEnum
 * @Author 李晨峰
 */
public enum FormManagerEnum {
    BSWK("文档标识为空",0),
    ZZBJ("正在编辑文档",1),
    ZBBC("文档已准备保存",2),
    BCCW("文档保存错误",3),
    GBWXG("文档关闭，文档没有修改",4),
    BJBC("正在编辑文档，但保存文档",6),
    QZBCCW("强制保存文档时发生错误",7);
    private String name;
    private Integer index;

    FormManagerEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static String getName(int index) {
        for (FormManagerEnum s : FormManagerEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return null;
    }
    public static Integer getIndex(String name) {
        for (FormManagerEnum s : FormManagerEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return null;
    }
}
