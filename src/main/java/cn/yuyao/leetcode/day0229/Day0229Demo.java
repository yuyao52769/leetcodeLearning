package cn.yuyao.leetcode.day0229;

import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.*;

public class Day0229Demo {

    public static void main(String[] args) {
        Day0229Demo demo = new Day0229Demo();
        int b = demo.longestAlternatingSubarray2(new int[]{3, 2, 5, 4}, 5);
        System.out.println(b);
//        int a = 4;
//        a = (++a) % 5;
//        System.out.println(a);
    }

    /**
     * 219. 存在重复元素 II
     * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
     * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
     *
     * 输入：nums = [1,2,3,1], k = 3
     * 输出：true
     *
     * 输入：nums = [1,0,1,1], k = 1
     * 输出：true
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> orDefault = map.getOrDefault(nums[i], new ArrayList<Integer>());
            if (orDefault.size() == 0) {
                map.put(nums[i], orDefault);
            }
            orDefault.add(i);
        }

        return map.entrySet().stream().filter(o -> {
            List<Integer> value = o.getValue();
            return  value.size() > 1 && cal(value, k);
                }).findFirst().isPresent();
    }

    private boolean cal(List<Integer> list, int k) {
        int i = 0, j = 1;
        Collections.sort(list);
        while (j < list.size()) {
            int dev = 0;
            while ((dev = Math.abs(list.get(j) - list.get(i))) > k) {
                i++;
                if (i >= j) j++;
                if (j >= list.size())break;
            }
            if (dev <= k) return true;
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums.length <= 1) return false;
        int left = 0, right = 0;
        Set<Integer> set = new HashSet<>();
        while (right < nums.length) {
            if (set.contains(nums[right])) return true;
            set.add(nums[right]);
            if (right - left < k) {
                right++;
            } else {
                set.remove(nums[left]);
                left++;
                right++;
            }
        }
        return false;
    }

    /**
     * 1652. 拆炸弹
     * 你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 n 的 循环 数组 code 以及一个密钥 k 。
     *
     * 为了获得正确的密码，你需要替换掉每一个数字。所有数字会 同时 被替换。
     *
     * 如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。
     * 如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。
     * 如果 k == 0 ，将第 i 个数字用 0 替换。
     * 由于 code 是循环的， code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。
     *
     * 给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
     * 输入：code = [5,7,1,4], k = 3
     * 输出：[12,10,16,13]
     * 解释：每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5, 4+5+7, 5+7+1]。注意到数组是循环连接的。
     *
     * 输入：code = [1,2,3,4], k = 0
     * 输出：[0,0,0,0]
     * 解释：当 k 为 0 时，所有数字都被 0 替换。
     *
     * 输入：code = [2,4,9,3], k = -2
     * 输出：[12,5,6,13]
     * 解释：解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果 k 是负数，那么和为 之前 的数字。
     */
    public int[] decrypt(int[] code, int k) {
        int[] result = new int[code.length];
        if ( k == 0) return result;
        int sum = 0;
        int left = k > 0 ? 1 : code.length - 1;
        int right = left;
        sum += code[left];
            for (int i = 0; i < code.length; i++) {
                int size = right >= left ? right - left + 1 : right - left + code.length + 1;
                if (size == Math.abs(k)) {
                    sum -= code[left];
                    left = (++left) % code.length;
                    right = (++right) % code.length;
                    sum += code[right];
                    result[i] = sum;
                    continue;
                }
                while ((size = right >= left ? right - left + 1 : right - left + code.length) < Math.abs(k)) {
                    if (k > 0) {
                        right = (++right) % code.length;
                        sum += code[right];
                    } else {
                        left = (--left) % code.length;
                        sum += code[left];
                    }

                }
                result[i] = sum;

            }
            return result;
    }

    public int[] decrypt2(int[] code, int k) {
        int n = code.length;
        if (k == 0) {
            return new int[n];
        }
        int[] res = new int[n];
        int[] newCode = new int[n * 2];
        System.arraycopy(code, 0, newCode, 0, n);
        System.arraycopy(code, 0, newCode, n, n);
        code = newCode;
        int l = k > 0 ? 1 : n + k;
        int r = k > 0 ? k : n - 1;
        int w = 0;
        for (int i = l; i <= r; i++) {
            w += code[i];
        }
        for (int i = 0; i < n; i++) {
            res[i] = w;
            w -= code[l];
            w += code[r + 1];
            l++;
            r++;
        }
        return res;
    }

    public int[] decrypt3(int[] code, int k) {
        int[] result = new int[code.length];
        if ( k == 0) return result;
        int n = code.length;
        int sum = 0;
        int l = 1;
        int r = 1;
        if (k > 0) {
            l = 1;
            for (int index = 1; index <= k; index++) {
                sum += code[r];
                r = (++r) % 5;
            }
        }
        return result;
    }

    /**
     *
     2269. 找到一个数字的 K 美丽值
     一个整数 num 的 k 美丽值定义为 num 中符合以下条件的 子字符串 数目：

     子字符串长度为 k 。
     子字符串能整除 num 。
     给你整数 num 和 k ，请你返回 num 的 k 美丽值。
     允许有 前缀 0 。
     0 不能整除任何值。
     一个 子字符串 是一个字符串里的连续一段字符序列。

     输入：num = 240, k = 2
     输出：2
     解释：以下是 num 里长度为 k 的子字符串：
     - "240" 中的 "24" ：24 能整除 240 。
     - "240" 中的 "40" ：40 能整除 240 。
     所以，k 美丽值为 2 。
     */
    public int divisorSubstrings(int num, int k) {
        int count = 0;
        if (k <= 0) return count;
        String numStr = String.valueOf(num);
        int n = numStr.length();
        if (n < k) return count;
        int left = 0;
        int right = k - 1;
        Set<Integer> set = new HashSet<>();
        while (right < n) {
            Integer childNum = Integer.valueOf(numStr.substring(left, right + 1));
            if (set.contains(childNum)) {
                count++;
            }  else if (!childNum.equals(0) && num % childNum == 0){
                set.add(childNum);
                count++;
            }
            left++;
            right++;
        }
        return count;
    }

    /**
     * 2379. 得到 K 个黑块的最少涂色次数
     * 给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。字符 'W' 和 'B' 分别表示白色和黑色。
     *
     * 给你一个整数 k ，表示想要 连续 黑色块的数目。
     *
     * 每一次操作中，你可以选择一个白色块将它 涂成 黑色块。
     *
     * 请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。
     *
     * 输入：blocks = "WBBWWBBWBW", k = 7
     * 输出：3
     * 解释：
     * 一种得到 7 个连续黑色块的方法是把第 0 ，3 和 4 个块涂成黑色。
     * 得到 blocks = "BBBBBBBWBW" 。
     * 可以证明无法用少于 3 次操作得到 7 个连续的黑块。
     * 所以我们返回 3 。
     */
    public int minimumRecolors(String blocks, int k) {
        int minCount = 0;
        int curCunt = 0;
        if (blocks.length() < k) return minCount;
        Set<Integer> set = new HashSet<>();
        int left = 0;
        int right = k - 1;
        for (int i = left; i <= right; i++) {
            char charAt = blocks.charAt(i);
            if (charAt == 'W') {
                curCunt++;
                set.add(i);
            }
        }
        minCount = curCunt;
        while (right < blocks.length() - 1) {
            if (set.contains(left)) curCunt--;
            left++;
            if (blocks.charAt(++right) == 'W') {
                set.add(right);
                curCunt++;
            }
            minCount = Math.min(minCount, curCunt);
        }

        return minCount;
    }

    /**
     * 2760. 最长奇偶子数组
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 threshold 。
     * 请你从 nums 的子数组中找出以下标 l 开头、下标 r 结尾 (0 <= l <= r < nums.length) 且满足以下条件的 最长子数组 ：
     *
     * nums[l] % 2 == 0
     * 对于范围 [l, r - 1] 内的所有下标 i ，nums[i] % 2 != nums[i + 1] % 2
     * 对于范围 [l, r] 内的所有下标 i ，nums[i] <= threshold
     * 以整数形式返回满足题目要求的最长子数组的长度。
     *
     * 注意：子数组 是数组中的一个连续非空元素序列。
     *
     * 输入：nums = [3,2,5,4], threshold = 5
     * 输出：3
     * 解释：在这个示例中，我们选择从 l = 1 开始、到 r = 3 结束的子数组 => [2,5,4] ，满足上述条件。
     * 因此，答案就是这个子数组的长度 3 。可以证明 3 是满足题目要求的最大长度。
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int maxCount = 0;
        int left = 0;
        int right = 0;
        int curCount = 0;
        // 偶数的索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if ( nums[i] % 2 == 0) {
                map.put(i, 1) ;
            } else {
                map.put(i, 0);

            }
        }
        while (right < nums.length) {
            if (nums[right] > threshold){
                left = ++right;
                if (right >= nums.length) break;
                curCount = 0;
            }
            if (right == left ) continue;
            if ((map.get(right) ^ map.get(right - 1)) == 1) {
                curCount++;
                right++;
                maxCount = Math.max(maxCount, curCount);
            } else {
                curCount = 0;
                left = right;
                right++;
            }

        }
        return maxCount;
    }

    public int longestAlternatingSubarray2(int[] nums, int threshold) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0] <= threshold ? 1 : 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] <= threshold && ((nums[i] & 1) != (nums[i - 1] & 1)) ? dp[i - 1] + 1 : dp[i - 1];
        }
        return dp[nums.length - 1];
    }
}
