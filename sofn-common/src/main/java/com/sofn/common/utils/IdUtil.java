package com.sofn.common.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 主要作用获取ID  方便以后ID生成规则变化后更改
 * Created by heyongjie on 2019/6/5 16:12
 */
public class IdUtil {

    /**
     * 获取UUID  已经去掉了- 如 3c64f0924be84c76924e106a72a5136b
     * @return  String  去掉了-之后的ID
     */
    public static String getUUId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 随机生成6位数字密码
     * @return  String  去掉了-之后的ID
     */
    public static String getSixNumCode(){
        Random random = new Random();
        String[] chars = new String[]{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            buffer.append(chars[random.nextInt(chars.length)]);
        }
        return buffer.toString();
    }

    /**
     * 随机生成数字编码
     * @param：size 随机数的长度
     * @return  String
     */
    public static String getNumCode(Integer size){
        Random random = new Random();
        String[] chars = new String[]{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < size; i++) {
            buffer.append(chars[random.nextInt(chars.length)]);
        }
        return buffer.toString();
    }


    /**
     * 按照某个分隔符获取List集合
     * @param strIds  ID,ID,ID,ID
     * @param splitStr  分隔符 如,
     * @return List<String>
     */
    public static List<String> getIdsByStr(String strIds,String splitStr){
        List<String> ids = new ArrayList<>();
        if(StringUtils.isEmpty(strIds)) return ids;
        ids = Splitter.on(splitStr).trimResults().omitEmptyStrings().splitToList(strIds);
        return ids;
    }


    /**
     * 根据,将字符串分割为List<String>
     * @param strIds  ID,ID,ID,ID
     * @return List<String>
     */
    public static List<String> getIdsByStr(String strIds){
        return getIdsByStr(strIds,",");
    }

    /**
     * 根据List<String> 获取String 格式ID,ID,ID,
     * @param ids   List<String>
     * @return  String
     */
    public static String getStrIdsByList(List<String> ids){
        return Joiner.on(",").join(ids);
    }




}
