package com.sofn.agzirdd;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Description
 * @Author wg
 * @Date 2021/8/17 10:09
 **/
public class Test {

    @org.junit.Test
    public void test1() throws Exception {
//        Class<Son> sonClass = Son.class;
//        Son son = sonClass.newInstance();
//        Field ff =null;
//        try {
//            ff = sonClass.getField("ff");
//        }catch (Exception e){
//            Class<? super Son> superclass = sonClass.getSuperclass();
////            while (superclass!=Object.class){
////                superclass = sonClass.getSuperclass();
////            }
//            ff = superclass.getDeclaredField("ff");
//        }
//        ff.setAccessible(true);
//        ff.set(son,"asd");
//        System.out.println(son);
//        Class<Son> sonClass = Son.class;
//        Field[] declaredFields = sonClass.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName());
//        }


//        Field yy=null;
//        while (yy==null){
//            Field[] fields = sonClass.getDeclaredFields();
//            Arrays.asList(fields).contains("yy");
//        }
//        System.out.println(Object.class.getSuperclass());
        Class clasz = Son.class;
        Field yy=null;
        while (yy==null){
            Field[] fields = clasz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals("yy")){
                    yy=field;
                    break;
                }
            }
            clasz = clasz.getSuperclass();
        }
        System.out.println(yy);
    }

    public void orm(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clasz = obj.getClass();
        //一获取cityid;
        Field cityId = clasz.getDeclaredField("cityId");
        cityId.setAccessible(true);
        //获取cityIdVal的值
        String cityIdVal = (String)cityId.get(obj);
        //根据cityIdVal的值去支撑平台查询Name
        String cityNameval="city的值";
        Field cityName = clasz.getDeclaredField("cityName");
        cityName.setAccessible(true);
        cityName.set(obj,cityNameval);
        //二、处理countryId
    }
}

@Data
class OOO{
    private String cityId;
    private String cityName;
    private String countryId;
    private String countryName;
}

@Data
class TTT{
    private String cityId;
    private String cityName;
    private String countryId;
    private String countryName;
}

class YY{
    private String yy;
}

class Fu extends YY{
    private String ff;
}

class Son extends Fu{
    private String name;
    private String age;
}