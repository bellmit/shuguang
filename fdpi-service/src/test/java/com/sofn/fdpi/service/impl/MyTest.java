package com.sofn.fdpi.service.impl;

import antlr.ASdebug.ASDebugStream;
import com.google.common.base.Splitter;
import com.sofn.common.exception.SofnException;
import com.sofn.fdpi.enums.ChangeReasonEnum;
import com.sofn.fdpi.enums.OmCompType;
import com.sofn.fdpi.enums.OmSize;
import com.sofn.fdpi.service.impl.bean.Stu;
import com.sofn.fdpi.service.impl.build.Computer;
import com.sofn.fdpi.vo.OmEelImportVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.redisson.misc.Hash;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Member;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MyTest {
    @Test
    public void get() throws InterruptedException {
        Double num=12.21;
//        System.out.println(num * 600);
        long round = Math.round(2.4);
        Double aDouble = new Double(round);


        System.out.println(Math.round(2.4));
    }



    public static List<String> getIdsByStr(String strIds, String splitStr,int i) {

        List<String> ids = new ArrayList<>();
        if (StringUtils.isEmpty(strIds)) return ids;
        ids = Splitter.on(splitStr).trimResults().omitEmptyStrings().splitToList(strIds);
        return ids;
    }
    

    private static void A(String rex, String str) {
        Boolean match = match(rex, str);
    }


    private static Boolean match(String rex, String str) {
        // TODO Auto-generated method stub
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}

@Data
class Stu2{
    private int age;
    private String name;

    public Stu2(int age, String name) {
        this.age = age;
        this.name = name;
    }
}