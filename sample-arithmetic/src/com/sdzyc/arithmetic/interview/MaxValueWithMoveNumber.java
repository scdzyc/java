package com.sdzyc.arithmetic.interview;

/**
 *  一个整数去掉k个数字后的最小值
 * MaxValueWithMoveNumber class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/21 16:09
 */
public class MaxValueWithMoveNumber {


    /**
     *  优化版
     * @param num
     * @param k
     * @return
     */
    public static String optimizer(String num, int k) {
        // 新整数的最终长度 = 原数长度 - k
        int newLength = num.length() - k;
        // 创建一个栈，用于接受所有的数字
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            // 遍历当前数字
            char c = num.charAt(i);
            // 当栈顶数字大于比遍历到的当前数字时，栈顶数字出栈(想当于删除数字)
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            // 遍历到的当前数字入栈
            stack[top++] = c;
        }
        // 找到栈中第一个非0数字的位置，以此构建新的整数字符串
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++;
        }
        return offset == newLength? "0" : new String(stack,offset,newLength - offset);
    }
    /**
     *  时间复杂度是O(kn)
     * @param num
     * @param k
     * @return
     */
    public static String removeKDigits(String num, int k) {
        for (int i = 0; i < k; i++) {
            boolean hasCut = false;
            // 从左向右比遍历，找到比自己大的右侧数字数字并删除
            for (int j = 0; j < num.length() - 1; j++) {
                if(num.charAt(j) > num.charAt(j + 1)) {
                    num = num.substring(0,j) + num.substring(j + 1, num.length());
                    hasCut = true;
                    // 移除后跳出内循环
                    break;
                }
            }
            // 如果没有找到要删除的数字，则删除最后一个数字
            if(!hasCut) {
                num = num.substring(0, num.length() - 1);
            }
        }
        // 清楚整数左侧的 0
        int start = 0;
        for (int i = 0; i < num.length() - 1; i++) {
            if(num.charAt(i) != '0') {
                break;
            }
            start ++;
        }
        num = num.substring(start,num.length());
        // 如果整数的数字都被删除，直接返回 0
        if(num.length() == 0) {
            return "0";
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(removeKDigits("1593212",2));
        System.out.println(optimizer("1593212",2));
        System.out.println();
        System.out.println(removeKDigits("30200",1));
        System.out.println(optimizer("30200",1));
        System.out.println();
        System.out.println(removeKDigits("10",2));
        System.out.println(optimizer("10",2));
        System.out.println();
        System.out.println(removeKDigits("541270936",3));
        System.out.println(optimizer("541270936",3));
    }
}
