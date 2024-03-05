package cn.yuyao.leetcode.day0301;

import java.util.function.Predicate;

public class Day0301Demo {

    public static void main(String[] args) {
        Day0301Demo demo = new Day0301Demo();
        int i = demo.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(i);
    }

    /*
    LeetCode第115题 困难
    *   给定一个字符串s和一个字符串t，计算在s的子序列中t出现的个数。
     */
    public int test1(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        int[][] dp = new int[sLength + 1] [tLength + 1];

        for (int j = 0; j <= tLength; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= sLength; i++) {
            for (int j = 1; j <= tLength; j++) {
                if (t.charAt( i- 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[sLength][tLength];
    }

    /**
     * 2369. 检查数组是否存在有效划分
     * 给你一个下标从 0 开始的整数数组 nums ，你必须将数组划分为一个或多个 连续 子数组。
     *
     * 如果获得的这些子数组中每个都能满足下述条件 之一 ，则可以称其为数组的一种 有效 划分：
     *
     * 子数组 恰 由 2 个相等元素组成，例如，子数组 [2,2] 。
     * 子数组 恰 由 3 个相等元素组成，例如，子数组 [4,4,4] 。
     * 子数组 恰 由 3 个连续递增元素组成，并且相邻元素之间的差值为 1 。例如，子数组 [3,4,5] ，但是子数组 [1,3,5] 不符合要求。
     * 如果数组 至少 存在一种有效划分，返回 true ，否则，返回 false 。
     *
     * 输入：nums = [4,4,4,5,6]
     * 输出：true
     * 解释：数组可以划分成子数组 [4,4] 和 [4,5,6] 。
     * 这是一种有效划分，所以返回 true 。
     *
     * 示例 2：
     * 输入：nums = [1,1,1,2]
     * 输出：false
     * 解释：该数组不存在有效划分。
     */
    public boolean validPartition(int[] nums) {
        if (nums.length <= 1) return false;
        boolean[] dp = new boolean[nums.length];
        dp[0] = false;
        dp[1] = nums[0] == nums[1];
        for (int i = 2; i < nums.length; i++) {

        }
        return dp[nums.length - 1];
    }

    /**
     *  判断子序列
     *  给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     *
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     */
    public boolean isSubsequence(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        int i = 0, j = 0;
        while (i < sLength && j < tLength) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == sLength;
    }

    /**
     * LCS 01. 下载插件
     * 小扣打算给自己的 VS code 安装使用插件，初始状态下带宽每分钟可以完成 1 个插件的下载。假定每分钟选择以下两种策略之一:
     *
     * 使用当前带宽下载插件
     * 将带宽加倍（下载插件数量随之加倍）
     * 请返回小扣完成下载 n 个插件最少需要多少分钟。
     *
     * 注意：实际的下载的插件数量可以超过 n 个
     * 输入：n = 4
     * 输出：3
     * 解释： 最少需要 3 分钟可完成 4 个插件的下载，以下是其中一种方案: 第一分钟带宽加倍，带宽可每分钟下载 2 个插件; 第二分钟下载 2 个插件; 第三分钟下载 2 个插件。
     */
    public int leastMinutes(int n) {
        if ( n <= 2) return n;
        int count = n / 2;
        int t = n % 2;
        return t == 0 ? count + 1 : count + 2;
    }


    /**
     * 面试题 05.03. 翻转数位
     * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
     */
    public int reverseBits(int num) {
        int cur = 0;
        int insert = 0;
        int max = 0;
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                cur++;
                insert++;
            } else {
                insert = cur + 1;
                cur = 0;
            }
            max = Math.max(insert, max);
        }
        return max;
    }

    /**
     * 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i ++) {
            dp[i][i] = true;
        }

        for (int l = 2; l <= len; l++) {
            for (int i = 0; i < len; i++) {
                int j = l+ i - 1;
                if (j >= len) {
                    break;
                }
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);

    }

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * 输入：m = 3, n = 7
     * 输出：28
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     *
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2
     * 解释：3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
            } else {
                if (i == 0) {
                    dp[0][i] = 1;
                } else {
                    dp[0][i] = dp[0][i-1] == 1 ? 1 : 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                if ( i == 0) {
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = dp[i-1][0] == 1 ? 1 : 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 72. 编辑距离
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     */
    public int minDistance(String word1, String word2) {
        int minCount = 0;
        if (word1.equals("") || word1.equals(" ")) return word2.length();


        return minCount;
    }

    /**
     *  解码方法
     *  一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
     *
     * 'A' -> "1"
     * 'B' -> "2"
     * ...
     * 'Z' -> "26"
     * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
     *
     * "AAJF" ，将消息分组为 (1 1 10 6)
     * "KJF" ，将消息分组为 (11 10 6)
     * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
     * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
     * 题目数据保证答案肯定是一个 32 位 的整数。
     */
    public int numDecodings(String s) {
        int n = s.length();
        if (n < 1) return 0;
        int[] dp = new int[n];
        dp[0] = Integer.valueOf(s.charAt(0)).equals(0) ? 0 : 1;
        if (n == 1) {
            return dp[0];
        }

        for (int i = 1; i < n; i++) {
            int now = Integer.valueOf(s.charAt(i));
            if (now == 0) {

            } else {
                dp[i] = dp[i -1] + 1;
            }
        }
        return 0;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int[] dp = new int[prices.length];
        dp[0] = 0;
        dp[1] = prices[1] > prices[0] ? prices[1] - prices[0] : 0;
        int min = prices[1] > prices[0] ? prices[0] : prices[1];
        for (int i = 2; i < prices.length; i++) {
            dp[i] = Math.max(dp[i-1], prices[i] - min);
            if (prices[i] < min) min = prices[i];
        }
        return dp[prices.length - 1];
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     */
    public int maxProfit2(int[] prices) {
        int[][] dp = new int[prices.length][prices.length];
        if (prices.length <= 1) return 0;
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], prices[i] + dp[i-1][1]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

}
