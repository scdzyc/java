package com.sdzyc.arithmetic.interview;

/** 寻找整数
 * FindLostInteger class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/23 15:55
 */
public class FindLostInteger {

    /**
     * 在一个无序数组里有若干个正整数，范围为1~100，其中98个整数都出现了偶数次，只有 2 个整数出现了奇数次，如何找出这 2 个出现奇数次的整数？
     */
    public static int[] findLostNum(int[] arr) {
        // 创建结果数组
        int[] result = new int[2];

        // 进行第一次整体异或运算
        int xorResult = 0;
        for (int i = 0; i < arr.length; i++) {
            xorResult ^= arr[i];
        }
        // 如果进行异或运算的结果为0，则说明输入的数组不符合题目要求
        if(xorResult == 0) {
            return null;
        }
        // 确定2个整数的不同位，以此来做分组
        int separator = 1;
        while (0 == (xorResult & separator)) {
            separator <<= 1;// 左移以为
        }
        for (int i = 0; i < arr.length; i++) {
            // 分组，将数据放入对应数组位置
            if(0 == (arr[i] & separator)) {
                result[0] ^= arr[i];
            }else {
                result[1] ^=arr[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4,1,2,2,5,1,4,3};
        int[] result = findLostNum(arr);
        System.out.println(result[0] + " ============= " + result[1]);
    }
}
