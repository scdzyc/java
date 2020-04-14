package com.sdzyc.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/** 桶排序
 * BucketSort class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic
 * @blame yanchuan
 * @since 20/04/14 14:55
 */
public class BucketSort {

    public static double[] sort(double[] arr) {
        // 1.得到数列的最大值和最小值，并算出差值d
        double max = arr[0];
        double min = arr[0];
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
            if(arr[i] < min) {
                min = arr[i];
            }
        }
        double d = max -min;

        // 2. 初始化桶，与数列元素数相同
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            bucketList.add(new LinkedList<Double>());
        }

        // 3. 遍历原始数列，将每个元素放入桶中
        for (int i = 0; i < length; i++) {
            int num = (int)((arr[i] - min) * (length - 1) / d);
            bucketList.get(num).add(arr[i]);
        }

        // 4. 对每个桶内部进行排序
        for (int i = 0; i < bucketList.size(); i++) {
            // JDK 底层采用了归并排序和归并的优化版本
            Collections.sort(bucketList.get(i));
        }

        // 5. 输出全部的元素
        double[] sortedArr = new double[length];
        int index = 0;
        for (LinkedList<Double> list : bucketList) {
            for(double ele : list) {
                sortedArr[index] = ele;
                index++;
            }
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        double[] arr = new double[]{4.12, 6.421, 0.0023, 3.0, 2.123, 8.122, 4.12, 10.09};
        double[] sorted = sort(arr);
        System.out.println(Arrays.toString(sorted));
    }
}
