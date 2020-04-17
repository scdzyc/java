package com.sdzyc.arithmetic.interview;

/** 判断一个是否2的整数次幂
 * PowerOf2 class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/17 14:14
 */
public class PowerOf2 {


    /**
     *  优化版
     * @return
     */
    public static boolean optimizeIsPowerOf2(int num) {
        return (num & (num - 1)) == 0;
    }

    /**
     * 改进版，使用位运算
     * @param num
     * @return
     */
    public static boolean isPowerOf2ByBit(int num) {
        int temp = 1;
        while(temp <= num) {
            if(temp == num) {
                return true;
            }
            temp = temp<<1;
        }
        return false;
    }

    /**
     * 最原始的方式
     * @param num
     * @return
     */
    public static boolean isPowerOf2(int num) {
        int temp = 1;
        while(temp <= num) {
            if(temp == num) {
                return true;
            }
            temp = temp * 2;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOf2(32));
        System.out.println(isPowerOf2ByBit(32));
        System.out.println(optimizeIsPowerOf2(32));
    }
}
