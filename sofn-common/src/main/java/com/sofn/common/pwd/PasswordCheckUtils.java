/*
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 *
 *
 */
package com.sofn.common.pwd;

import java.util.Arrays;
import java.util.List;

/**
 * 密码安全性检查工具类
 *
 * @author John
 * @version 1.0
 */
public class PasswordCheckUtils {
    public static void main(String[] args) {
        String pwd = "Abc815@st";
        System.out.println(evalPassword(pwd));
    }

    /**
     * 检测密码中字符长度
     *
     * @param password 密码字符串
     * @return 符合长度要求 返回true
     */
    public static boolean checkPasswordLength(String password) {
        boolean flag = false;

        if ("".equals(PasswordCheckConfig.MAX_LENGTH)) {
            if (password.length() >= Integer.parseInt(PasswordCheckConfig.MIN_LENGTH)) {
                flag = true;
            }
        } else {
            if (password.length() >= Integer.parseInt(PasswordCheckConfig.MIN_LENGTH) &&
                    password.length() <= Integer.parseInt(PasswordCheckConfig.MAX_LENGTH)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 检测密码中是否包含数字
     *
     * @param password 密码字符串
     * @return 包含数字 返回true
     */
    public static boolean checkContainDigit(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int num_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isDigit(chPass[i])) {
                num_count++;
            }
        }

        if (num_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 检测密码中是否包含字母（不区分大小写）
     *
     * @param password 密码字符串
     * @return 包含字母 返回true
     */
    public static boolean checkContainCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLetter(chPass[i])) {
                char_count++;
            }
        }

        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 检测密码中是否包含小写字母
     *
     * @param password 密码字符串
     * @return 包含小写字母 返回true
     */
    public static boolean checkContainLowerCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLowerCase(chPass[i])) {
                char_count++;
            }
        }

        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 检测密码中是否包含大写字母
     *
     * @param password 密码字符串
     * @return 包含大写字母 返回true
     */
    public static boolean checkContainUpperCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isUpperCase(chPass[i])) {
                char_count++;
            }
        }

        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 检测密码中是否包含特殊符号
     *
     * @param password 密码字符串
     * @return 包含特殊符号 返回true
     */
    public static boolean checkContainSpecialChar(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int special_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (PasswordCheckConfig.SPECIAL_CHAR.indexOf(chPass[i]) != -1) {
                special_count++;
            }
        }

        if (special_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 键盘规则匹配器 横向连续检测
     *
     * @param password 密码字符串
     * @return 含有横向连续字符串 返回true
     */
    public static boolean checkLateralKeyboardSite(String password) {
        String t_password = password;
        //将所有输入字符转为小写
        t_password = t_password.toLowerCase();
        int n = t_password.length();
        /**
         * 键盘横向规则检测
         */
        boolean flag = false;
        int arrLen = PasswordCheckConfig.KEYBOARD_HORIZONTAL_ARR.length;
        int limit_num = Integer.parseInt(PasswordCheckConfig.LIMIT_HORIZONTAL_NUM_KEY);

        for (int i = 0; i + limit_num <= n; i++) {
            String str = t_password.substring(i, i + limit_num);
            String distinguishStr = password.substring(i, i + limit_num);

            for (int j = 0; j < arrLen; j++) {
                String configStr = PasswordCheckConfig.KEYBOARD_HORIZONTAL_ARR[j];
                String revOrderStr = new StringBuffer(PasswordCheckConfig.KEYBOARD_HORIZONTAL_ARR[j]).reverse().toString();

                //检测包含字母(区分大小写)
                if ("enable".equals(PasswordCheckConfig.CHECK_DISTINGGUISH_CASE)) {
                    //考虑 大写键盘匹配的情况
                    String UpperStr = PasswordCheckConfig.KEYBOARD_HORIZONTAL_ARR[j].toUpperCase();
                    if ((configStr.indexOf(distinguishStr) != -1) || (UpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                    if ((revOrderStr.indexOf(distinguishStr) != -1) || (revUpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                } else {
                    if (configStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    if (revOrderStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 键盘规则匹配器 斜向规则检测
     *
     * @param password 密码字符串
     * @return 含有斜向连续字符串 返回true
     */
    public static boolean checkKeyboardSlantSite(String password) {
        String t_password = password;
        t_password = t_password.toLowerCase();
        int n = t_password.length();
        /*
         * 键盘斜线方向规则检测
         */
        boolean flag = false;
        int arrLen = PasswordCheckConfig.KEYBOARD_SLOPE_ARR.length;
        int limit_num = Integer.parseInt(PasswordCheckConfig.LIMIT_SLOPE_NUM_KEY);

        for (int i = 0; i + limit_num <= n; i++) {
            String str = t_password.substring(i, i + limit_num);
            String distinguishStr = password.substring(i, i + limit_num);
            for (int j = 0; j < arrLen; j++) {
                String configStr = PasswordCheckConfig.KEYBOARD_SLOPE_ARR[j];
                String revOrderStr = new StringBuffer(PasswordCheckConfig.KEYBOARD_SLOPE_ARR[j]).reverse().toString();
                //检测包含字母(区分大小写)
                if ("enable".equals(PasswordCheckConfig.CHECK_DISTINGGUISH_CASE)) {

                    //考虑 大写键盘匹配的情况
                    String UpperStr = PasswordCheckConfig.KEYBOARD_SLOPE_ARR[j].toUpperCase();
                    if ((configStr.indexOf(distinguishStr) != -1) || (UpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                    if ((revOrderStr.indexOf(distinguishStr) != -1) || (revUpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                } else {
                    if (configStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    if (revOrderStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 评估a-z,z-a这样的连续字符
     *
     * @param password 密码字符串
     * @return 含有a-z,z-a连续字符串 返回true
     */
    public static boolean checkSequentialChars(String password) {
        String t_password = password;
        boolean flag = false;
        int limit_num = Integer.parseInt(PasswordCheckConfig.LIMIT_LOGIC_NUM_CHAR);
        int normal_count = 0;
        int reversed_count = 0;

        //检测包含字母(区分大小写)
        if ("enable".equals(PasswordCheckConfig.CHECK_DISTINGGUISH_CASE)) {

        } else {
            t_password = t_password.toLowerCase();
        }
        int n = t_password.length();
        char[] pwdCharArr = t_password.toCharArray();

        for (int i = 0; i + limit_num <= n; i++) {
            normal_count = 0;
            reversed_count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if (pwdCharArr[i + j + 1] - pwdCharArr[i + j] == 1) {
                    normal_count++;
                    if (normal_count == limit_num - 1) {
                        return true;
                    }
                }

                if (pwdCharArr[i + j] - pwdCharArr[i + j + 1] == 1) {
                    reversed_count++;
                    if (reversed_count == limit_num - 1) {
                        return true;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 评估aaaa,1111这样的相同连续字符
     *
     * @param password 密码字符串
     * @return 含有aaaa, 1111等连续字符串 返回true
     */
    public static boolean checkSequentialSameChars(String password) {
        String t_password = password;
        int n = t_password.length();
        char[] pwdCharArr = t_password.toCharArray();
        boolean flag = false;
        int limit_num = Integer.parseInt(PasswordCheckConfig.LIMIT_NUM_SAME_CHAR);
        int count = 0;
        for (int i = 0; i + limit_num <= n; i++) {
            count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if (pwdCharArr[i + j] == pwdCharArr[i + j + 1]) {
                    count++;
                    if (count == limit_num - 1) {
                        return true;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 检测常用词库
     *
     * @param password 密码字符串
     * @return 含有常见词库 返回true
     */
    public static boolean checkSimpleWord(String password) {
        List<String> simpleWords = Arrays.asList(PasswordCheckConfig.SIMPLE_WORDS);
        return simpleWords.contains(password.toLowerCase());
    }

    /**
     * 评估密码中包含的字符类型是否符合要求
     *
     * @param password 密码字符串
     * @return 符合要求 返回true
     */
    public static boolean evalPassword(String password) {
        if (password == null || "".equals(password)) {
            return false;
        }
        boolean flag = false;

        /*
         * 检测长度
         */
        if ("enable".equals(PasswordCheckConfig.CHECK_PASSWORD_LENGTH)) {
            flag = checkPasswordLength(password);
            if (!flag) {
                return false;
            }
        }

        /*
         * 检测包含数字
         */
        if ("enable".equals(PasswordCheckConfig.CHECK_CONTAIN_DIGIT)) {
            flag = checkContainDigit(password);
            if (!flag) {
                return false;
            }
        }

        /*
         *  检测包含字母
         */
        if ("enable".equals(PasswordCheckConfig.CHECK_CONTAIN_CASE)) {
            flag = checkContainCase(password);
            if (!flag) {
                return false;
            }
        }

        /*
         * 检测字母区分大小写
         */
        if ("enable".equals(PasswordCheckConfig.CHECK_DISTINGGUISH_CASE)) {
            //检测包含小写字母
            if ("enable".equals(PasswordCheckConfig.CHECK_LOWER_CASE)) {
                flag = checkContainLowerCase(password);
                if (!flag) {
                    return false;
                }
            }

            //检测包含大写字母
            if ("enable".equals(PasswordCheckConfig.CHECK_UPPER_CASE)) {
                flag = checkContainUpperCase(password);
                if (!flag) {
                    return false;
                }
            }
        }

        /*
         * 检测包含特殊符号
         */
        if ("enable".equals(PasswordCheckConfig.CHECK_CONTAIN_SPECIAL_CHAR)) {
            flag = checkContainSpecialChar(password);
            if (!flag) {
                return false;
            }
        }

        /*
         * 检测键盘横向连续
         */
        if ("enable".equals(PasswordCheckConfig.CHECK_HORIZONTAL_KEY_SEQUENTIAL)) {
            flag = checkLateralKeyboardSite(password);
            if (flag) {
                return false;
            }
        }

        /*
         * 检测键盘斜向连续
         */
        if ("enable".equals(PasswordCheckConfig.CHECK_SLOPE_KEY_SEQUENTIAL)) {
            flag = checkKeyboardSlantSite(password);
            if (flag) {
                return false;
            }
        }

        /*
         * 检测逻辑位置连续
         */
        if ("enable".equals(PasswordCheckConfig.CHECK_LOGIC_SEQUENTIAL)) {
            flag = checkSequentialChars(password);
            if (flag) {
                return false;
            }
        }

        /*
         * 检测相邻字符是否相同
         */
        if ("enable".equals(PasswordCheckConfig.CHECK_SEQUENTIAL_CHAR_SAME)) {
            flag = checkSequentialSameChars(password);
            if (flag) {
                return false;
            }
        }

        /*
         * 检测常用词库
         */
        if ("enable".equals(PasswordCheckConfig.CHECK_SIMPLE_WORD)) {
            flag = checkSimpleWord(password);
            return !flag;
        }
        return true;
    }
}
