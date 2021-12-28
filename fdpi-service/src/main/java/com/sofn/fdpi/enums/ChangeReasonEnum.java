package com.sofn.fdpi.enums;

/**
 * @Description 物种变更原因枚举类
 * @Author wg
 * @Date 2021/4/9 11:15
 **/
//1：进口；2：出口；3：自繁；4：死亡；5：救护；6：放生;7，购买，8：销售；9：借入；10:借出；11：捐赠
public enum ChangeReasonEnum {
    //其中1,3,5,7,9属于入库类型;2,4,6,8,10,11属于出库类型
    PORT("1","进口"),
    EXPORT("2","出口"),
    SELFBREED("3","自繁"),
    DEAD("4","死亡"),
    RESCUE("5","救护"),
    FREE("6","放生"),
    BUY("7","购买"),
    SELL("8","销售"),
    BORROW("9","借入"),
    LOAN("10","借出"),
    DONATE("11","捐赠");

    private String code;
    private String desc;

    ChangeReasonEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    //根据code获得desc
    public static String getVal(String code) {
        ChangeReasonEnum[] arr = values();
        for (ChangeReasonEnum obj : arr) {
            if(obj.code.equals(code)){
                return obj.desc;
            }
        }
        return null;
    }
}
