package com.sdzyc.arithmetic.interview;

import java.util.Arrays;

/** 无序数组排序后最大相邻差
 * MaxDiffValue class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/17 14:44
 */
public class MaxDiffValue {



    /**
     * 利用桶排序的思想
     * @param arr
     * @return
     */
    public static Integer optimizer(int[] arr) {
        // 确定最大、最小值
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
            if(arr[i] < min) {
                min = arr[i];
            }
        }

        int d = max - min;
        // 如果max和min相等，说明数组所有元素都相等，返回0
        if(d == 0) {
            return 0;
        }

        // 初始化桶
        int bucketNum = arr.length;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new Bucket();
        }

        // 遍历原始数组，确定每个桶的最大最小值
        for (int i = 0; i < arr.length; i++) {
            // 确定数组元素归属桶的下标
            int index = ((arr[i] - min) * (bucketNum -1)/d);
            if(buckets[index].min == null || buckets[index].min > arr[index]) {
                buckets[index].min = arr[i];
            }
            if(buckets[index].max == null || buckets[index].max < arr[index]) {
                buckets[index].max = arr[i];
            }
        }

        // 遍历桶找到最大差值
        int leftMax = buckets[0].max;
        int maxDistance = 0;
        for (int i = 0; i < buckets.length; i++) {
            if(buckets[i].min == null) {
                continue;
            }
            if(buckets[i].min - leftMax > maxDistance) {
                maxDistance = buckets[i].min - leftMax;
            }
            leftMax = buckets[i].max;
        }
        return maxDistance;
    }

    private static class Bucket {
        Integer min;
        Integer max;
    }
    /**
     * 类似计数排序的方式
     * @param arr
     * @return
     */
    public static Integer optimize(int[] arr) {
        // 确定最大、最小值
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
            if(arr[i] < min) {
                min = arr[i];
            }
        }
        // 创建数组
        int[] buck = new int[max - min + 1];
        // 对号入座
        for (int i = 0; i < arr.length; i++) {
            buck[arr[i]-min]++;
        }
        // 判断连续0值的
        int count = 0;
        int temp = 0;
        for (int i = 0; i < buck.length; i++) {
            if(buck[i] == 0) {
                count++;
            }else {
                temp = (count > temp)? count : temp;
                count = 0;
            }
        }
        return temp+1;
    }
    /**
     * 原始方法，
     * @param arr
     * @return
     */
    public static Integer getMaxDiffBySort(int[] arr) {
        Arrays.sort(arr);
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
           temp = (arr[i+1]-arr[i]) > temp? (arr[i+1]-arr[i]) : temp;
        }
        return temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2,6,3,4,5,10,9};
        System.out.println(getMaxDiffBySort(arr));
        System.out.println(optimize(arr));
        System.out.println(optimizer(arr));
    }
}
