package cn.yuyao.leetcode.day0227;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class SumOfTwoNumber {

    public static void main(String[] args) throws Exception{

        SumOfTwoNumber sum = new SumOfTwoNumber();
        String[] str = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> lists = sum.calGroupAnagrams(str);
        System.out.println(lists);
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     */
    private int[] calSumOfTwoNumber(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(target - nums[i])) {
                map.put(nums[i], i);
            } else {
                res[0] = i;
                res[1] = map.get(target - nums[i]);
                break;
            }
        }
        return res;
    }

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * 输入: strs = [""]
     * 输出: [[""]]
     *
     * 输入: strs = ["a"]
     * 输出: [["a"]]
     * @param strs
     * @return
     */
    private List<List<String>> calGroupAnagrams(String[] strs) throws Exception {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            if (list.isEmpty()) {
                map.put(key, list);
            }
            list.add(str);

        }
        return map.values().stream().collect(Collectors.toList());
    }


    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     *
     * 示例 1：
     *
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 示例 2：
     *
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {

        for (int num : nums) {

        }
        return 0;
    }
}
