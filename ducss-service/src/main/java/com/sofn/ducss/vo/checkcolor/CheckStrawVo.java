package com.sofn.ducss.vo.checkcolor;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 检查基本VO
 */
@Data
public class CheckStrawVo {

    /**
     * 秸秆类型
     */
    @ApiModelProperty(value = "秸秆类型")
    private  String strawType;

    /**
     * 秸秆类型名字
     */
    @ApiModelProperty(value = "秸秆类型名字")
    private String strawTypeName;

//    public CheckStrawVo(){
//        try{
//            // 将所有的Color都初始化成0表示颜色未改变
//            Field[] fields = this.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                String columnName = field.getName();
//                if(columnName.contains("ColorState")){
//                    field.setAccessible(true);
//                    String colorStateValue = (String)field.get(this);
//                    if(StringUtils.isBlank(colorStateValue)){
//                        field.set(this, CheckColorEnum.COLOR_UNCHANGE.getCode());
//                    }
//                    field.setAccessible(false);
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

}
