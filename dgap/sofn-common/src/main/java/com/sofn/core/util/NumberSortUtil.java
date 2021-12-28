package com.sofn.core.util;

/**
 * Created by Liw on 2017/3/29.
 * 排序工具类
 */
public class NumberSortUtil {
    /**
     * 冒泡排序
     * @param numbers
     * @return
     */
    public static int[] bubbleSort(int[] numbers) {
        int temp; // 记录临时中间值
        int size = numbers.length; // 数组大小
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (numbers[i] < numbers[j]) { // 交换两数的位置
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        return numbers ;
    }
}
