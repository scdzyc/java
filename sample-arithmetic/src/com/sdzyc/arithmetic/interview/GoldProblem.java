package com.sdzyc.arithmetic.interview;

/** 金矿问题
 * GoldProblem class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/22 15:49
 */
public class GoldProblem {

    /**
     * 效率提升 空间复杂度减低到O(n)
     * @param w 工人数
     * @param n 金矿数
     * @param p 金矿需要工人集合
     * @param g 金矿储量集合
     * @return
     */
    public static int getBestGoldOptimizer(int w, int n, int[] p, int[] g) {
        // 创建结果数组
        int[] results = new int[w + 1];
        // 填充数组
        for (int i = 1; i <= g.length ; i++) {
            for (int j = w; j >= 1; j--) { // i=4 j = 9
                if(j >= p[i - 1]) {
                    results[j] = Math.max(results[j], results[j-p[i - 1]] + g[i -1]);// 500 + 300
                }
            }
        }
        // 返回最后一个数
        return results[w];
    }

    /**
     * 效率低下 时间复杂度是O（2^n）
     * @param w 工人数
     * @param n 金矿数
     * @param p 金矿需要工人集合
     * @param g 金矿储量集合
     * @return
     */
    public static int getBestGoldOptimize(int w, int n, int[] p, int[] g) {
        // 创建表格
        int[][] resultTable = new int[g.length + 1][w + 1];
        // 填充表格
        for (int i = 1; i <= g.length; i++) {
            for (int j = 1; j <= w; j++) {
                // 工人数小于 需要的工人数
                if(j < p[i - 1]) {
                    resultTable[i][j] = resultTable[i - 1][j];
                }else {
                    // 否则
                    resultTable[i][j] = Math.max(resultTable[i-1][j],
                            resultTable[i-1][j-p[i-1]] + g[i-1]);
                }
            }
        }
        // 返回最后一格的值
        return resultTable[g.length][w];
    }
    /**
     * 效率低下 时间复杂度是O（2^n）
     * @param w 工人数
     * @param n 金矿数
     * @param p 金矿需要工人集合
     * @param g 金矿储量集合
     * @return
     */
    public static int getBestGold(int w, int n, int[] p, int[] g) {
        if(w == 0 || n == 0) {
            return 0;
        }
        // 分成两种最优选择
        // 最后一个矿需要的人数比当前拥有工人多，只有一种选择
        if(w < p[n - 1]) {
            return getBestGold(w, n - 1, p, g);
        }
        return Math.max(getBestGold(w, n -1, p, g),
                getBestGold(w - p[n - 1],n - 1, p, g) + g[n - 1]);
    }

    public static void main(String[] args) {
        int[] p = new int[]{5,5,3,4,3};
        int[] g = new int[]{400,500,200,300,350};
        System.out.println(getBestGold(10, g.length, p, g));
        System.out.println(getBestGoldOptimize(10, g.length, p, g));
        System.out.println(getBestGoldOptimizer(10, g.length, p, g));
    }
}
