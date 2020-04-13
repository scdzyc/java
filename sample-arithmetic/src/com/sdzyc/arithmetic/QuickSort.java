package com.sdzyc.arithmetic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/** 快速排序
 * QuickSort class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic
 * @blame yanchuan
 * @since 20/04/13 13:26
 */
public class QuickSort {

    public static void sort(int[] arr){
        if(null == arr) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }
    public static void sort(int[] arr, int startIndex, int endIndex){
        if(null == arr || startIndex < 0 || endIndex > arr.length || startIndex > endIndex) {
            return;
        }
        // 递归方式
//        quickSort(arr, startIndex,endIndex);
        // 使用栈的方式
        quickSortByStack(arr, startIndex, endIndex);
    }
    private static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件
        if(startIndex >= endIndex) {
            return;
        }
        // 得到基准元素
//        int pivotIndex = partition(arr, startIndex, endIndex);
        int pivotIndex = partitionforOne(arr, startIndex, endIndex);
        // 根据基准元素，分成两部分
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr,pivotIndex + 1, endIndex);
    }

    private static void quickSortByStack(int[] arr, int startIndex, int endIndex) {
        // 用一个集合栈来代替递归的函数栈
        Stack<Map<String, Integer>> quickSortStack = new Stack<>();
        // 将整个数列的起止下标以hash的形式入栈
        Map<String, Integer> rootParam = new HashMap<>();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex",endIndex);
        quickSortStack.push(rootParam);
        // 循环结束条件-栈为空时
        while(!quickSortStack.isEmpty()) {
            // 栈顶元素出栈，得到起止下标
            Map<String, Integer> param = quickSortStack.pop();
            // 得到基准元素的位置
            int pivotIndex = partitionforOne(arr,param.get("startIndex"), param.get("endIndex"));
            // 根据基准元素，分成两个部分，把每一个部分元素入栈
            if(param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex",param.get("startIndex"));
                leftParam.put("endIndex",pivotIndex - 1);
                quickSortStack.push(leftParam);
            }
            if(pivotIndex + 1 < param.get("endIndex")) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }
    }

    /**
     * 分治 （双边循环法）
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return
     */
    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置（也可以选择随机位置）的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            // 控制right指针比较并左移
            while (left < right && arr[right] > pivot) {
                right--;
            }
            // 控制left指针比较并右移
            while(left < right && arr[left] <= pivot) {
                left++;
            }
            // 交换left和right指针所指向的元素
            if(left < right) {
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;

            }
        }
        //pivot和指针重合点元素交换
        arr[startIndex] = arr[left];
        arr[left] = pivot;
        return left;
    }

    /**
     * 分治（单边循环法）
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return
     */
    private static int partitionforOne(int[] arr, int startIndex, int endIndex) {
        // 取顶一个位置元素，作为基准元素
        int pivot = arr[startIndex];
        int mark = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if(arr[i] < pivot) {
                mark++;
                int p = arr[mark];
                arr[mark] = arr[i];
                arr[i] = p;
            }
        }

        arr[startIndex] = arr[mark];
        arr[mark] =pivot;
        return mark;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,4,6,5,3,2,8,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
