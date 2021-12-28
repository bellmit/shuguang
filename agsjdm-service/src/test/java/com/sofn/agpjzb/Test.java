package com.sofn.agpjzb;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author wg
 * @Date 2021/7/14 10:59
 **/
public class Test {
    public static void main(String[] args) throws ParseException, ClassNotFoundException, NoSuchMethodException {
//        Zi zi = new Zi();
//        zi.change();
//        Son son = new Son();
//        BeanUtils.copyProperties(zi,son);
//        System.out.println(son);
//        String str="12.jpg、2.jpg、13.jpg、12.jpg、11.jpg、2.jpg、3.jpg、4.jpg";
//        String[] split = str.split("、");
//        List<String> strings = Stream.of(split).sorted((a, b) -> {
//            return Integer.valueOf(a.split("\\.")[0]) - Integer.valueOf(b.split("\\.")[0]);
//        }).collect(Collectors.toList());
////        System.out.println(strings);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date parse = simpleDateFormat.parse("2021-07-10");
//        System.out.println(parse);
//        ClassLoader classLoader = Test.class.getClassLoader();
//        URL resource = classLoader.getResource("com/sofn/agpjzb");
//        System.out.println(resource);
//        File file = new File(resource.getFile());
//        Class<?> com = classLoader.loadClass("com.sofn.agsjdm.AgsjdmServiceApplication");
//        System.out.println();
//        Fu fu = new Fu();
//        Object fu1 = fu;
//        if (fu1 instanceof JK){
//            JK fu11 = (JK) fu1;
//            fu11.getJk("asd");
//        }
//        System.out.println(JK.class.isAssignableFrom(Son.class));
//        Method asd = Son.class.getMethod("asd", String.class);


    }

    @org.junit.Test
    public void get() {
//        Double 花呗=6527.95;
//        Double 借呗=1394.50+11372.00+7195.00+93.00+6090.00;
//        Double 我妹=18000.0+2000;
//        Double 房租=3915.75;
//        Double 总计=花呗+借呗+我妹+房租;
//        System.out.println(总计);

        System.out.println(1394.50+11372.00+7195.00+6090.00+93.00);
        System.out.println(17068.00+8039.00);
    }
}

class Fu {
    private String qwe;
    private String createName;

    public void change() {
        this.qwe = "1";
        this.createName = "2";
    }

    public String getqwe() {
        return qwe;
    }

    public void setqwe(String qwe) {
        this.qwe = qwe;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}

class Zi extends Fu {
    private String ziId;
    private String ziName;

    public String getZiId() {
        return ziId;
    }

    public void setZiId(String ziId) {
        this.ziId = ziId;
    }

    public String getZiName() {
        return ziName;
    }

    public void setZiName(String ziName) {
        this.ziName = ziName;
    }
}

@Data
class Son extends Fu implements JK {
    private String ziId;
    private String ziName;
    private String qwe;
    private String createName;

    @Override
    public void getJk(String name) {

    }
}

interface JK {
    void getJk(String name);
}

@Data
class A {
    private String name = "asd";
    private String age;
}

@Data
class B extends A {
    private String name = "qwe";
}