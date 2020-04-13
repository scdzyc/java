package com.sdzyc.arithmetic;

import java.util.Arrays;

/** 冒泡排序
 * BubbleSort class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic
 * @blame yanchuan
 * @since 20/04/13 12:43
 */
public class BubbleSort {

    /**
     * 冒泡排序
     * @param array
     */
    public static void sort(int[] array){
        if(null == array || array.length < 2) {
            return;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            int temp = array[0];
            for (int j = 1; j < length - i; j++) {
                //boolean compa = array[i] < array[j];
                boolean compa = temp > array[j];
                if(compa) {
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
                temp = array[j];
            }
        }
    }

    /**
     * 优化，如果已经是有序状态就退出
     * @param array
     */
    public static void optimizeSort(int[] array) {
        if(null == array || array.length < 2) {
            return;
        }
        int length = array.length;
        // 再优化版
        //int lastExchangeIndex = 0;
        //int sortBorder = length - 1;
        for (int i = 0; i < length - 1; i++) {
            // 标记是否有效，每一轮初始值都是true
            boolean isSorted = true;
            int temp = array[0];
            for (int j = 1; j < length - i/*sortBorder*/; j++) {
                //boolean compa = array[i] < array[j];
                boolean compa = temp > array[j];
                if(compa) {
                    array[j - 1] = array[j];
                    array[j] = temp;
                    // 如果有交换，就说明不是有序状态
                    isSorted = false;
                    //lastExchangeIndex = j - 1;
                }
                temp = array[j];
            }
            //sortBorder = lastExchangeIndex;
            if(isSorted) {
                break;
            }
        }
    }

    /**
     * 再优化版，如果已经是有序状态就退出，每一轮排序过程中处于sortBorder之后的元素就不在进行比较了
     * @param array
     */
    public static void optimizerSort(int[] array) {
        if(null == array || array.length < 2) {
            return;
        }
        int length = array.length;
        // 记录最后一次交换的位置
        int lastExchangeIndex = 0;
        // 无序数列的边界，每次比较只需要比较到这里
        int sortBorder = length;
        for (int i = 0; i < length - 1; i++) {
            // 标记是否有效，每一轮初始值都是true
            boolean isSorted = true;
            int temp = array[0];
            for (int j = 1; j < sortBorder; j++) {
                //boolean compa = array[i] < array[j];
                boolean compa = temp > array[j];
                if(compa) {
                    array[j - 1] = array[j];
                    array[j] = temp;
                    // 如果有交换，就说明不是有序状态
                    isSorted = false;
                    lastExchangeIndex = j;
                }
                temp = array[j];
            }
            sortBorder = lastExchangeIndex;
            if(isSorted) {
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序，基于冒泡排序的实现，
     *
     * @param array
     */
    public static void cocktailSort(int[] array) {
        if(null == array || array.length < 2) {
            return;
        }
        int tmp = 0;
        for (int i = 0; i < array.length/2; i++) {
            // 有序标记，么偶一轮初始值为true
            boolean isSorted = true;
            // 奇数轮从左往右比较
            for (int j = i; j < array.length - i - 1; j++) {
                if(array[j] > array[j + 1]){
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    // 有元素交换，所以不是有序的，
                    isSorted = false;
                }
            }
            if(isSorted) {
                break;
            }
            // 偶数轮(从左往右),将isSorted 重新标记为true
            isSorted = true;
            for (int j = array.length -i - 1; j > i; j--) {
                if(array[j] < array[j - 1]) {
                    tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    // 有元素交换
                    isSorted = false;
                }
            }
            if(isSorted) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{5, 8, 6, 3, 9, 2, 1, 4, 7};
        int[] arr2 = new int[]{5, 8, 6, 3, 9, 2, 1, 4, 7};
        int[] arr3 = new int[]{5, 8, 6, 3, 9, 2, 1, 4, 7};
        int[] arr4 = new int[]{5, 8, 6, 3, 9, 2, 1, 4, 7};
        sort(arr1);
        optimizeSort(arr2);
        optimizerSort(arr3);
        cocktailSort(arr4);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr4));
    }
}
