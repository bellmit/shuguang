package com.sofn.service;

import com.sofn.model.dgap.Constants;

import java.util.Random;

/**
 * Created by helong on 18-3-7.
 */
public class DataGenerator {

    public static String sampleString(int maxlengh){
        Random random = new Random();
        String k = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // 添加不同的随机字符串
        StringBuffer str = new StringBuffer();
        int stringLength = random.nextInt(maxlengh);
        for (int j = 0; j <= stringLength; j++) {
            int number = random.nextInt(k.length());
            str.append(k.charAt(number));
        }

        return str.toString();
    };

    public static int sampleNumber(){
        return 0;
    }

    public static int sampleTimestamp(){
        return 0;
    }

    public static int sampleTime(){
        return 0;
    }

    public static int sampleDecimal(){
        return 0;
    }

}
