package com.sdzyc.arithmetic;

import java.util.Arrays;

/**
 * HeapSort class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic
 * @blame yanchuan
 * @since 20/04/14 13:26
 */
public class HeapSort {

    /**
     *  "下沉" 调整
     * @param arr
     * @param parentIndex
     * @param length
     */
    private static void downAdjust(int[] arr, int parentIndex, int length) {
        // temp 保存父节点，用于最后的赋值
        int temp = arr[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while(childIndex < length) {
            // 如果有右孩子，且比左孩子的值大，则定位到右孩子
            if(childIndex + 1 < length && arr[childIndex + 1] > arr[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于任何一个孩子节点的值，则直接跳出
            if(temp >= arr[childIndex]) {
                break;
            }
//            // 最小堆实现-------------------------------
//            if(childIndex + 1 < length && arr[childIndex + 1] < arr[childIndex]) {
//                childIndex++;
//            }
//            if(temp <= arr[childIndex]) {
//                break;
//            }//-----------------------------------------------
            // 无须真正交换，单向赋值即可
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        arr[parentIndex] = temp;
    }

    /**
     * 堆排序(升序)
     * @param arr
     */
    public static void sort(int[] arr) {
        // 1. 把无序数列构建成最大堆
        for (int i = (arr.length -2) / 2; i >= 0 ; i--) {
            downAdjust(arr,i,arr.length);
        }
        System.out.println(Arrays.toString(arr));
        // 2. 循环删除堆顶元素，移动到集合尾部，调整堆产生新的堆顶
        for (int i = arr.length - 1; i > 0 ; i--) {
            // 最后一个元素和第一个元素进行交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            // 调整
            downAdjust(arr, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2,6,5,7,8,9,10,0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
