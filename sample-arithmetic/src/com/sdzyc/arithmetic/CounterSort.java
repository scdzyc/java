package com.sdzyc.arithmetic;

import java.util.Arrays;

/** 计数排序
 * CounterSort class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic
 * @blame yanchuan
 * @since 20/04/14 14:05
 */
public class CounterSort {

    /**
     * 计数排序
     * @param arr
     * @return
     */
    public static int[] sort(int[] arr) {
        // 1. 得到数列的最大值
        int max = arr[0];
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        // 2. 根据取到的最大值确定统计数组的长度
        int[] countArr = new int[max+1];
        // 3. 遍历数列，填充统计数组
        for (int i = 0; i < length; i++) {
            countArr[arr[i]]++;
        }
        // 4. 遍历统计数组，输出结果
        int index = 0;
        int[] sortedArr = new int[arr.length];
        for (int i = 0,len = countArr.length; i < len; i++) {
            for (int j = 0; j < countArr[i]; j++) {
                sortedArr[index++] = i;
            }
        }
        return sortedArr;
    }

    /**
     *  优化版
     * @param arr
     * @return
     */
    public static int[] optimizeSort(int[] arr) {
        // 1. 得到数列的最大值、最小值
        int max = arr[0];
        int min = arr[0];
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
            if(arr[i] < min) {
                min = arr[i];
            }
        }
        // 统计数组长度
        int countLen = max - min + 1;
        // 2. 根据取到的最大值确定统计数组的长度
        int[] countArr = new int[countLen];
        // 3. 遍历数列，填充统计数组
        for (int i = 0; i < length; i++) {
            countArr[arr[i]- min]++;
        }
        // 4. 统计数组变形，后面元素的值等于前面的元素之和
        for (int i = 1; i < countLen; i++) {
            countArr[i] += countArr[i - 1];
        }
        // 5. 倒序遍历原始数组，从统计数组找到正确的位置，输出到结果
        int[] sortedArr = new int[arr.length];
        for (int i = length - 1; i >= 0 ; i--) {
            sortedArr[countArr[arr[i] - min] - 1] = arr[i];
            countArr[arr[i] - min]--;
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{4,4,6,5,3,2,8,1,7,5,6,0,10};
        int[] arr2 = new int[]{95,94,91,98,99,90,99,92,91,92};
        int[] sorted1 = sort(arr1);
        int[] sorted2 = optimizeSort(arr2);
        System.out.println(Arrays.toString(sorted1));
        System.out.println(Arrays.toString(sorted2));

    }
}
