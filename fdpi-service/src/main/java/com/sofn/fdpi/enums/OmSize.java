package com.sofn.fdpi.enums;

/**
 * @Description 欧鳗种类 ，目前已知白子鳗和黑子鳗
 * @Author wg
 * @Date 2021/5/14 13:34
 **/
public enum OmSize {

    WHITEEEL(0, "白仔鳗", 900),
    BLACKEEL(1, "黑仔鳗", 600);

    private Integer code;
    private String val; //物种的名字
    private Integer con;    //对应的折算比率

    OmSize(int code, String val, int con) {
        this.code = code;
        this.val = val;
        this.con = con;
    }

    public Integer getCode() {
        return code;
    }

    public String getVal() {
        return val;
    }

    public Integer getCon() {
        return con;
    }
}
