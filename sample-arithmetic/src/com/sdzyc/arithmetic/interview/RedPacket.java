package com.sdzyc.arithmetic.interview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** 红包算法
 * RedPacket class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/28 13:16
 */
public class RedPacket {


    /**
     *  二倍均值法 ， 最后可能是负数。。。。
     * @param totalAmount
     * @param totalPeopleNum
     * @return
     */
    public static List<Integer> divideRedPacket(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            // 随机范围：[1, 剩余人均金额的2倍 - 1]分
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }


    private static void print(List<Integer> list) {
        int count = 0;
        for(Integer amount : list) {
            count += amount;
            System.out.println("抢到的金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }
        System.out.println("总计： " + count + " 分");
    }

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPacket(1000, 5);

        print(amountList);
    }
}
