package com.sdzyc.arithmetic.interview;

/** 两个正整数的最大公约数
 * MaxCommonDivisor class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/16 16:27
 */
public class MaxCommonDivisor {


    /**
     * 使用位运算，综合【推荐】
     * @return
     */
    public static Integer optimizerGCD(int a, int b) {
        if(a == b) {
            return a;
        }
        // 保证 a > b
        if(a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        // 双偶数
        if((a&1)==0 && (b&1) == 0) {
            return optimizerGCD(a>>1,b>>1)<<1;
        }
        //  a偶数，b奇数
        if((a&1) == 0 /* && (b&1) != 0*/) {
            return optimizerGCD(a>>1,b);
        }
        // a奇数，b偶数
        if((b&1) == 0/* && (a&1) != 0*/) {
            return optimizerGCD(a,b>>1);
        }
        //双奇数
        return optimizerGCD(b,a - b);
    }

    /**
     * 更相减损术，两个整数类似10000，1，这样性能也是问题
     * @param a
     * @param b
     * @return
     */
    public static  Integer commonDivisor1(int a, int b) {

        if(a == 1 || b == 1) {
            return 1;
        }
        if(a == b) {
            return a;
        }
        if(a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        // 逻辑开始
        a = a - b;
        return commonDivisor1(b,a);
    }

    /**
     *  辗转相除法（当两个整数较大时，取模运算性能较差）
     * @param a
     * @param b
     * @return
     */
    public static Integer commonDivisor(int a, int b) {
        if(a == b) {
            return a;
        }
        if(a == 1 || b == 1) {
            return 1;
        }
        if(a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        // 逻辑开始
        a = a % b;
        if(a ==0) {
            return b;
        }
        return commonDivisor(b, a);
    }

    /**
     * 暴力枚举法
     * @param a
     * @param b
     * @return
     */
    public static Integer commonDivisor2(int a, int b) {
        if(a == b) {
            return a;
        }
        if(a == 1 || b == 1) {
            return 1;
        }
        int big = a > b? a : b;
        int small = a < b? a : b;
        for (int i = small/2; i > 1 ; i--) {
            if(small % i == 0 && big % i == 0) {
                return i;
            }
        }
        return 1;
    }

    public static void main(String[] args) {

        System.out.println(commonDivisor(32,36));
        System.out.println(commonDivisor1(32,36));
        System.out.println(optimizerGCD(32,36));
        System.out.println(commonDivisor2(32,36));
    }
}
