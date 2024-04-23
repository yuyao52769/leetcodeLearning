package cn.yuyao.leetcode.day0316;

import java.util.*;

public class Day0316 {

    public static void main(String[] args) {
        Day0316 day = new Day0316();
        System.out.println(day.test25());

    }

    /**
     * 给你一个整数数组nums，判断这个数组中是否存在长度为3的递增子序列。
     * 如 果 存 在 这 样 的 三 元 组 下 标 (i,j,k) 且 满 足 i<j<k ， 使 得 nums[i]<nums[ j]
     * <nums[k]，返回true；否则，返回false。
     */
    public boolean test1(int[] nums) {
        if (nums.length < 3) return false;
        int left = 0;
        int right = 1;
        int count = 1;
        while (right < nums.length) {
            if (nums[right++] > nums[left]) {
                count++;
            } else {
                left = right;
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给定一个mxn的矩阵，如果一个元素为0，则将其所在行和列的所有元素都设为0。请
     * 使用原地算法。
     */
    public void test3(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;

        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i  = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i  = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    nums[i][j] = 0;
                }
            }
        }
    }

    /**
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的
     * 排列（即，组合出下一个更大的整数）。
     */
    public void test4(int[] nums) {
        int right = nums.length-1;
        int left = nums.length - 2;
        while (left >= 0) {
            if (nums[left] >= nums[left+1]) {
                left--;
            }
        }

        if (left >= 0) {
            while (left < right) {
                if (nums[right] <= nums[left]) {
                    right--;
                } else {
                    swap(nums, left, right);
                    break;
                }
            }
            left = left + 1;
            right = nums.length - 1;
            while (left < right) {
                swap(nums, left++, right--);
            }
        } else {
            left = 0;
            while (left < right) {
                swap(nums, left++, right--);
            }
        }

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 给你一个正整数组成的数组nums，返回nums中一个升序子数组的最大可能元素
     * 和。
     */
    public int test5(int[] nums){
        int maxSum = 0;
        int curSum = nums[0];
        int right = 1;
        while (right < nums.length) {
            if (nums[right] > nums[right - 1]) {
                curSum += nums[right];
            } else {
                curSum = nums[right];
            }
            maxSum = Math.max(maxSum, curSum);
            right++;
        }
        return maxSum;
    }

    /**
     * 给定一个整数数组和一个整数k，你需要找到该数组中和为k的连续的子数组的个
     * 数。
     */
    public int test6(int[] nums, int k) {
        int[] per = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if ( i == 0) {
                per[i] =  nums[i];
            } else {
                per[i] = per[i - 1] + nums[i];
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < per.length; i++) {
            int target = k + per[i];
            Integer integer = map.get(target);
            if (integer == null) {
                map.put(per[i], 1);
            } else {
                count += integer;
            }
        }
        return count;
    }

    /**
     * 摩尔投票算法
     */
    public int test7(int[] nums){
        if (nums.length == 1) return nums[0];
        int major = nums[0];
        int count = 1;
        int index = 1;
        while (index < nums.length) {
            if (count == 0) {
                major = nums[index];
                count++;
            } else {
                if (nums[index] == major) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return major;
    }

    /**
     * 欧式筛选质数
     */
    public int test8(int n){
        boolean[] isPrime = new boolean[n+1];
        int[] res = new int[n+1];

        isPrime[0] = isPrime[1] = true;
        int index = 0;
        for (int i = 2; i<= n; i++) {
            if (!isPrime[i]) {
                res[++index] = i;
            }

            for (int j = 1; j <= index && i * res[j] <= n; j++) {
                isPrime[i * res[j]] = true;
                if (i % res[j] == 0) break;
            }

        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (res[i] != 0) count++;
        }
        return count;
    }

    /**
     * 符合下列属性的数组arr称为山脉数组 ：
     * 给 你 由 整 数 组 成 的 山 脉 数 组 arr ， 返 回 任 何 满 足 arr[0]<arr[1]<...arr[i-1]
     * <arr[i]>arr[i+1]>...>arr[arr.length-1]的下标i。
     */
    public int test9(int[] nums) {
        if (nums.length == 1) return 0;
        int maxNum = nums[0];
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
                maxIndex = i;
            }
        }

        boolean flag = true;
        for (int i = 1; i <= maxIndex; i++) {
            if (nums[i] <= nums[i - 1]) {
                flag = false;
                break;
            }
        }

        for (int i = maxIndex; i < nums.length-1; i++) {
            if (nums[i] <= nums[i+1]) {
                flag = false;
                break;
            }
        }
        if (flag) return maxIndex;
        return -1;
    }

    /**
     * 给定一个整数，写一个函数来判断它是否是4的幂次方。如果是，返回true；否则，返回
     * false。
     */
    public boolean test10(int k) {
        int count = 0;
        while (k > 1) {
            if ((k & 1) == 0) {
                count++;
                k = k >> 1;
            }  else {
                return false;
            }
        }
        return count != 0 && (count & 1) == 0;
    }

    /**
     * 有一堆石头，每块石头的重量都是正整数。
     * 每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为x和
     * y，且x<=y。那么粉碎的可能结果如下：
     * 如果x==y，那么两块石头都会被完全粉碎；
     * 如果x!=y，那么重量为x的石头将会完全粉碎，而重量为y的石头新重量为y-x。
     * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回0。
     */
    public int test11(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>( (a, b) -> b - a);
        for (int num : nums) {
            queue.add(num);
        }

        while (queue.size() > 1) {
            Integer x = queue.poll();
            Integer y = queue.poll();
            if (x > y) {
                queue.add(x - y);
            }
        }
        if (queue.isEmpty()) return 0;
        return queue.poll();
    }

    /**
     * 图像顺时针旋转90°
     */
    public void rotate(int[][] matrix) {
         int length = matrix.length;
         //先上下交换
         for (int i = 0; i < length / 2; i++) {
             int temp[] = matrix[i];
             matrix[i] = matrix[length - i - 1];
             matrix[length - i - 1] = temp;
             }
         //在按照对角线交换
         for (int i = 0; i < length; ++i) {
             for (int j = i + 1; j < length; ++j) {
                 int temp = matrix[i][j];
                 matrix[i][j] = matrix[j][i];
                 matrix[j][i] = temp;
                 }
             }
         }

    /**
     * 给你一个mxn的整数网格accounts，其中accounts[i][j]是第i位客户在第j家银行托管
     * 的资产数量。返回最富有客户所拥有的资产总量 。
     */
    public int test11(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;
        int maxSum = 0;
        for (int i = 0; i < m; i++) {
            int curSum = 0;
            for (int j = 0; j < n; j++) {
                curSum += nums[i][j];
            }
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    /**
     * 今 天 ， 书 店 老 板 有 一 家 店 打 算 试 营 业 customers.length 分 钟 。 每 分 钟 都 有 一 些 顾 客
     * （customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
     * 在某些时候，书店老板会生气。如果书店老板在第i分钟生气，那么 grumpy[i]=1，否
     * 则grumpy[i]=0。当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满
     * 意的。
     * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续X分钟不生气，但却只
     * 能使用一次。
     * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int satisfied = 0;
        int index = 0;
        int[] noSatisfied = new int[customers.length];
        for (int i = 0; i < grumpy.length; i++) {
            if (grumpy[i] == 0) {
                satisfied += customers[i];
            } else if (customers[i] != 0){
                noSatisfied[index++] = customers[i];
            }
        }

        int maxOtherSum = 0;
        int otherSum = 0;
        int left = 0;
        int right = 0;
        while (right < index - 1) {
            otherSum += noSatisfied[right];
            if (right - left + 1 > X) {
                otherSum -= noSatisfied[left++];
            }
            maxOtherSum = Math.max(maxOtherSum, otherSum);
            right++;
        }
        return satisfied + maxOtherSum;
    }

    /**
     * 给定一个由若干0和1组成的数组A，我们最多可以将K个值从0变成1。
     * 返回仅包含1的最长（连续）子数组的长度。
     */
    public int test12(int[] num, int k) {
        int maxWindow = 0;
        int zeroCount = 0;
        int left = 0;
        int right = 0;
        for (; right < num.length; right++) {
          if (num[right] == 0) {
              zeroCount++;
          }

          while (zeroCount > k) {
              if (num[left++] == 0 ) {
                  zeroCount--;
              }
          }
          maxWindow = Math.max(maxWindow, right - left + 1);
        }
        return maxWindow;
    }

    /**
     * 给你一个mxn的矩阵matrix。如果这个矩阵是托普利茨矩阵，返回true；否则，返回
     * false 。
     * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是托普利茨矩
     * 阵。
     */
    public boolean test13(int[][] nums) {
        int m = nums.length - 1;
        int n = nums[0].length - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (nums[i][j] != nums[i+1][j+1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 给你一个整数数组arr，请你帮忙统计数组中每个数的出现次数。
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     * @return
     */
    public boolean test14(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer orDefault = map.getOrDefault(num, 0);
            map.put(num, orDefault + 1);
        }

//        List<Integer> collect = map.values().stream().sorted((a, b) -> a - b).collect(Collectors.toList());
//        for (int i = 0; i < collect.size() - 1; i++) {
//            if (collect.get(i) == collect.get(i+1)) return false;
//        }
//        return true;
        Set<Integer> set = new HashSet<>();
        for (Integer value : map.values()) {
            if (!set.add(value)) return false;
        }
        return true;
    }

    /**
     * 在未排序的数组中找到第k个最大的元素。请注意，你需要找的是数组排序后的第k个最
     * 大的元素，而不是第k个不同的元素。
     */
    public int test15(int[] nums, int k){
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int test16(int[] nums, int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    /**
     * 给定一个数组，将数组中的元素向右移动k个位置，其中k是非负数。
     */
    public void test17(int[] nums, int k) {
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = temp[i];
        }
    }

    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返
     * 回 -1。
     */
    public int test18(String str) {
        Map<Character, Integer> countMap = new HashMap<>();
        Map<Character, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            indexMap.putIfAbsent(c, i);
        }

        for (Character character : indexMap.keySet()) {
            if (countMap.get(character) > 1) continue;
            return indexMap.get(character);
        }
        return -1;
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下，一夜之内能够偷窃到的最高金额。
     */
    public int test19(int[] nums){
        int[] dp = new int[nums.length];
        if (nums.length <= 1) return nums[0];
        dp[0] = nums[0];
        dp[1] = nums[1] > nums[0] ? nums[1] : nums[0];
        if (nums.length == 2) {
            return dp[1];
        }
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[nums.length - 1];
    }

    /** 打家劫舍2
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所
     * 有的房屋都围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
     * 房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自
     * 动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下 ，
     * 能够偷窃到的最高金额。
     */
    public int test20(int[] nums){
        int[] dp = new int[nums.length];
        if (nums.length <= 1) return nums[0];
        dp[0] = nums[0];
        dp[1] = nums[1] > nums[0] ? nums[1] : nums[0];
        if (nums.length == 2) {
            return dp[1];
        }
        for (int i = 2; i < nums.length; i++) {
            if (i == nums.length - 1) {
                dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i] - dp[0]);
            } else {
                dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
            }

        }
        return dp[nums.length - 1];
    }

    /**
     * 最大公约数
     */
    public int test21(int x, int y){
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }
        while (x % y != 0) {
          int emp = y;
           y =  x % y;
           x = emp;
        }
        return y;
    }

    /**
     * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰
     * 好 z升 的水？
     * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
     * 你允许：
     * 装满任意一个水壶
     * 清空任意一个水壶
     * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
     */
    public boolean test22(int x, int y, int z) {
        return z == 0 || (x + y >= z) && (z % (test21(x, y)) == 0);
    }


    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     */
    public int test23(int[] height) {
        int maxSize = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            maxSize = Math.max(maxSize, (j - i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxSize;
    }


    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */
    public int test24(int[] height) {
        int maxSize = 0;
        int[] dpLeft = new int[height.length];
        int[] dpRight = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            if (i == 0) {
                dpLeft[i] = 0;
            } else {
                dpLeft[i] = Math.max(dpLeft[i-1], height[i-1]);
            }
        }

        for (int j = height.length - 1; j >= 0; j--) {
            if (j == height.length - 1) {
                dpRight[j] = 0;
            } else {
                dpRight[j] = Math.max(dpRight[j+1], height[j+1]);
            }
        }

        for (int i = 0; i < height.length; i++) {
            if (i == 0 || i == height.length - 1){
                continue;
            }
            if (dpLeft[i] > height[i] && dpRight[i] > height[i]) {
                maxSize += Math.min(dpRight[i], dpLeft[i]) - height[i];
            }
        }


      return maxSize;
    }


    public int test25() {
        try {
            System.out.println("try");
            return 1;
        } catch (Exception e) {

        } finally {
            System.out.println("finally");
            return 2;
        }
    }
}
