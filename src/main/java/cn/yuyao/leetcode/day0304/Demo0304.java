package cn.yuyao.leetcode.day0304;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Demo0304 {

    public static void main(String[] args) {
        Demo0304 demo = new Demo0304();
        boolean b = demo.test8(new int[]{1, 5, 11, 5});
        System.out.println(b);
    }

    /**
     * LCR 061. 查找和最小的 K 对数字
     * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
     * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
     *
     * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 输出: [1,2],[1,4],[1,6]
     * 解释: 返回序列中的前 3 对数：
     *     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2)->{
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });

        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i,0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }
        return ans;
    }
























    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });

        for (int i = 0; i < Math.min(n, k); i++) {
            pq.offer(new int[]{i, 0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] polled = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[polled[0]]);
            list.add(nums2[polled[1]]);
            result.add(list);
            if (polled[1] + 1 < m) {
                pq.offer(new int[]{polled[0], polled[1] + 1});
            }
        }
        return result;
    }


    /**
     * 复习，求字符串的最大回文字符串
     */
    public String test7(String str) {
        if (str == null || str.length() <= 1) return str;
        int n = str.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int maxLen = 0;
        String subStr = "";
        for (int len = 2; len < n; len++) {
            for (int i = 0; i < n; i++) {
                int right = i + len - 1;
                if (right > n) break;
                if (str.charAt(i) == str.charAt(right)) {
                    if (right - i < 3) {
                        dp[i][right] = true;
                    } else {
                        dp[i][right] = dp[i + 1][right - 1];
                    }
                } else {
                    dp[i][right] = false;
                }
                if (dp[i][right] && (right - i + 1 > maxLen)) {
                    maxLen = right - i + 1;
                    subStr = str.substring(i, right + 1);
                }
            }
        }
        return subStr;
    }

    /**
     * 给你一个只包含正整数的非空数组nums。请你判断是否可以将这个数组分割成两
     * 个子集，使得两个子集的元素和相等
     */
    public boolean test8(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) return false;
        int target = sum >> 1;
        int length = nums.length;
        int[][] dp = new int[length + 1][target + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i-1]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i-1]] + nums[i-1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }

            }
        }
        return dp[length][target] == target;
    }

    /**
     * 动态规划解最大正方形
     * 在一个由'0'和'1'组成的二维矩阵内，找到只包含'1'的最大正方形，并返回其面积
     */
    public int test9(char[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] dp = new int[height + 1][ width + 1];
        int maxSize = 0;
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }
        return maxSize;
    }

}
