package cn.yuyao.leetcode.day0228;

import java.util.*;

public class TestClass {

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        int i = testClass.minimumDifference(new int[]{9, 4, 1, 7}, 2);
        System.out.println(i);
    }

    /**
     283. 移动零
     给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     输入: nums = [0,1,0,3,12]  [1, 0, 1, 12, 0]
     输出: [1,3,12,0,0]
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length,  pre = 0,  last = 0;
        while (last < n) {
            if (nums[last] != 0) {
                swap(nums, pre, last);
                pre++;
            }
            last++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    /**
     * 11. 盛最多水的容器
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     *
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 返回容器可以储存的最大水量。
     *
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     */
    public int maxArea1(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return max;
    }

    public int maxArea2(int[] height) {
        int max = 0;
        int n = height.length;
        int i = 0;
        int j = n - 1;
        while (i < j) {
            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            if (height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    /**
     * 15. 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int i = 0;
        int j = n - 1;
        Map<Integer, Integer> hashMap = new HashMap<>();
       while (i < j) {
           int target = 0 - (nums[i] + nums[j]);
           Integer list = hashMap.getOrDefault(target, -1);
           if (list == -1 ) {
               //hashMap.put(target, )

           } else {
               ArrayList<Integer> arrayList = new ArrayList<>();
               arrayList.add(i);
               arrayList.add(j);
               arrayList.add(list);
               result.add(arrayList);
           }
       }

        return result;
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     */
    public int trap(int[] height) {
        int sum = 0;
        if (height.length <= 1) return sum;
        for (int mid = 1; mid < height.length - 1; mid++) {
            int maxLeft = 0;
            for (int i = mid - 1; i >= 0; i--) {
                maxLeft = Math.max(maxLeft, height[i]);
            }

            int maxRight = 0;
            for (int j = mid + 1; j < height.length; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }

            int h = Math.min(maxLeft, maxRight);
            if (h > height[mid]) {
                sum += h - height[mid];
            }
        }
        return sum;
    }

    public int trap2(int[] num) {
        int sum = 0;
        if (num.length <= 1) return sum;

        int[] maxLeft = new int[num.length];
        int[] maxRight = new int[num.length];

        for (int i = 1; i < num.length - 1; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], num[i - 1]);
        }

        for (int j = num.length - 2;  j >= 0; j--) {
            maxRight[j] = Math.max(maxRight[j + 1], num[j + 1]);
        }

        for (int i = 1; i < num.length - 1; i++) {
            int h = Math.min(maxLeft[i], maxRight[i]);
            if (h > num[i]) {
                sum += (h - num[i]);
            }
        }

        return sum;
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        int left = 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - left + 1);
        }
        return res;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     *
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     */
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
            pCount[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }

        for (int i = 0; i < s.length() - p.length(); i++) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];
        }
        return null;
    }

    /**
     * 594. 最长和谐子序列
     * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
     * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
     * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
     *
     * 输入：nums = [1,3,2,2,5,2,3,7]
     * 输出：5
     * 解释：最长的和谐子序列是 [3,2,2,2,3]
     */
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int max = 0;
        for (Integer integer : map.keySet()) {
            if (map.containsKey(integer + 1)) {
                max = Math.max(max, map.get(integer) + map.get(integer + 1));
            }

            if (map.containsKey(integer - 1)) {
                max = Math.max(max, map.get(integer) + map.get(integer - 1));
            }

        }
        return max;
    }

    /**
     * 643. 子数组最大平均数 I
     * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
     * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
     * 任何误差小于 10-5 的答案都将被视为正确答案。
     *
     * 输入：nums = [1,12,-5,-6,50,3], k = 4
     * 输出：12.75
     * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int l = 0;
        int r = k - 1;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        while (r < nums.length - 1) {
            sum -= nums[l++];
            sum += nums[++r];
            maxSum = Math.max(maxSum, sum);
        }
        return  maxSum / (double) k;
    }

    public double findMaxAverageCopy(int[] nums, int k) {
        int maxSum = 0;
        int sum = 0;
        int l = 0;
        int r = 0;
        while (r < nums.length) {
            if (r - l + 1 <= k) {
                sum += nums[r++];
            } else {
                sum -= nums[l++];
            }
            maxSum = Math.max(sum, maxSum);
        }
        return  maxSum / (double) k;
    }

    /**
     * 1763. 最长的美好子字符串
     * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
     * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
     */


    /**
     * 1984. 学生分数的最小差值
     * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
     * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
     * 返回可能的 最小差值 。
     */
    public int minimumDifference(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - k + 1; i++) {
            min = Math.min(min, nums[i + k - 1] - nums[i]);
        }
        return min;
    }
}
