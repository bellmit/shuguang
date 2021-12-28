package com.sofn.core.util;

import java.util.UUID;

/**
 * Created by: dong4j.
 * Date: 2016-10-27.
 * Time: 15:13.
 * Description:
 */

public final class UUIDUtil {
    public UUIDUtil() {
    }

    public static String getUUID() {
        UUID   uuid1 = UUID.randomUUID();
        String str1   = uuid1.toString();
        UUID   uuid2 = UUID.randomUUID();
        String str2  = uuid2.toString();
        // 去掉"-"符号
        String temp1 = str1.replace("-", "");
        String temp2 = str2.replace("-", "");
        return temp1 + temp2;
    }

    //获得指定数量的UUID
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

    public static void main(String[] args) {
        String[] ss = getUUID(10);
        System.out.println(ss[0].length());
        if (ss != null) {
            for (int i = 0; i < ss.length; i++) {
                System.out.println("ss[" + i + "]=====" + ss[i]);
            }
        }
    }
}
