package cn.yuyao.leetcode.day0229;

import java.util.*;

public class Day0229Demo {

    public static void main(String[] args) {
        Day0229Demo demo = new Day0229Demo();
        int[] decrypt = demo.decrypt(new int[]{2,4,2,4,10,3,10,7,10,5}, 1);
        System.out.println(decrypt);
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
    }
}
