package com.sdzyc.arithmetic.interview;

/** 两个大整数的和
 * TwoBigNumberSum class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/21 16:46
 */
public class TwoBigNumberSum {

    public static String sum(String bigNum1, String bigNum2) {
        int maxLength = bigNum1.length() > bigNum2.length()? bigNum1.length() + 1: bigNum2.length() + 1;

        int[] arrA = new int[maxLength];
        int[] arrB = new int[maxLength];
        for (int i = 0,j = bigNum1.length() - 1; i < bigNum1.length(); i++,j--) {
            arrA[i] = bigNum1.charAt(j) - '0';
        }
        for (int i = 0, j= bigNum2.length() -1; i < bigNum2.length(); i++, j--) {
            arrB[i] = bigNum2.charAt(j) - '0';
        }
        // 进行计算
        int[] result = new int[maxLength];
        for (int i = 0; i < maxLength - 1; i++) {
            int temp = result[i];
            temp += arrA[i];
            temp += arrB[i];
            if(temp >= 10) {
                temp = temp - 10;
                result[i + 1] = 1;
            }
            result[i] = temp;
        }
        // 转换成String
        StringBuilder sb = new StringBuilder();
        // 是否找到大整数的最高有效位
        boolean findFirst = false;
        for (int i = maxLength - 1; i >= 0; i--) {
            if(!findFirst) {
                if(result[i] == 0) {
                    continue;
                }
                findFirst = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(sum("426709752318347","95481253129"));
    }
}
