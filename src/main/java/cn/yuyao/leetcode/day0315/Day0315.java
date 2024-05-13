package cn.yuyao.leetcode.day0315;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Day0315 {

    public static void main(String[] args) {
        Day0315 day = new Day0315();

    }


    /**
     * 在一个由'0'和'1'组成的二维矩阵内，找到只包含'1'的最大正方形，并返回其面积。
     */
    public int test1(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] dp = new int[height][width];
        int max = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    if ( i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

    /**
     * 背包问题是动态规划中最经典的一道算法题。背包问题的种类比较多，我们先来看一个最简单的背包问题-基础背包。他是这样描述的。
     * 有N件物品和一个容量为V的包，第i件物品的重量是w[i]，价值是v[i]，求将哪些物品装入背包可使这些
     * 物品的重量总和不能超过背包容量，且价值总和最大。我们先来举个例子分析一下
     */
    public int test2(int size, int[] weight, int[] value) {
        int length = weight.length;
        int[][] dp = new int[length + 1][size + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= size; j++) {
                if (j < weight[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i-1]] + value[i-1]);
                }
            }
        }
        return dp[length][size];
    }

    public int test2Copy(int size, int[] weight, int[] value) {
        int[][] dp = new int[weight.length + 1][size + 1];

        for (int i = 1; i <= weight.length; i++) {
            for (int j = 1; j <= size; j++) {
                if (j >= weight[i-1]) {
                    dp[i][j] = Math.max(dp[i-1][j], value[i-1] + dp[i-1][j-weight[i-1]]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[weight.length][size];
    }

    /**
     * 数组去重和排序
     */
    public String test3(int[] nums) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        Map<Integer, Integer> first = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++){
            Integer count = map.getOrDefault(nums[i], 0);
            map.put(nums[i], count + 1);
            first.put(nums[i], i);
        }

        StringJoiner joiner = new StringJoiner(",");
        first.keySet().stream().sorted((a, b) -> {
            Integer aCount = map.get(a);
            Integer bCount = map.get(b);
            if (aCount != bCount) {
                return bCount - aCount;
            } else {
                Integer aIndex = first.get(a);
                Integer bIndex = first.get(b);
                return aIndex - bIndex;
            }
        }).forEach(a -> joiner.add(String.valueOf(a)));

        return joiner.toString();

    }
}
