package com.sdzyc.arithmetic.interview;

import java.util.Arrays;

/** 全排列的下一个数
 * 如： 12345
 * 下一个就是只大于12345 -> 12354
 * MaxNextNumByInteger class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/20 15:15
 */
public class MaxNextNumByInteger {

    public static int[] findNearestNumber(Integer number) {
        String numStr = number.toString();
        int[] nums = new int[numStr.length()];
        for (int i = 0; i < numStr.length(); i++) {
            Character ch = numStr.charAt(i);
            nums[i] = Integer.parseInt(ch.toString());
        }
        return findNearestNumber(nums);
    }

    public static int[] findNearestNumber(int[] numbers) {
        // 1. 查找逆序边界
        int index = findTransferPoint(numbers);
        // 如果到0了，说明整个数组逆序，无法组合更大的值了
        if(index == 0) {
            return null;
        }
        // 2. 把逆序区域的前一位和逆序区域中刚刚大于它的数交换
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        exchangeHead(numbersCopy, index);
        // 将原逆序转为顺序
        reverse(numbersCopy,index);
        return numbersCopy;
    }

    /**
     * 查找逆序区边界点(索引)
     * @param numbers
     * @return
     */
    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if(numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 交换：逆序区前一位x与逆序区中大于x的最小值
     * @param numbers
     * @param index
     * @return
     */
    private static int[] exchangeHead(int[] numbers, int index) {
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if(head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    /**
     *  转换逆序
     * @param num
     * @param index
     * @return
     */
    private static int[] reverse(int[] num, int index) {
        for (int i = index, j = num.length - 1; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
         }
        return num;
    }

    private static void outPutNumbers(int[] numbers) {
        String print = "";
        for (int i : numbers){
            print += i;
        }
        System.out.println(print);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        outPutNumbers(nums);
        System.out.println("------------------------------");
        for (int i = 0; i < 10; i++) {
            nums = findNearestNumber(nums);
            outPutNumbers(nums);
        }

    }
}
