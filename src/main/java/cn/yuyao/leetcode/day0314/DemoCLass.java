package cn.yuyao.leetcode.day0314;

/**
 *
 */
public class DemoCLass {

    public static void main(String[] args) {
        DemoCLass demo = new DemoCLass();
        int i = demo.test1(new int[]{1, 3, 4, 2, 2});
        System.out.println(i);
    }
    /**
     * 给定一个包含n+1个整数的数组nums，其数字都在1到n之间（包括1和n），可知至
     * 少存在一个重复的整数。假设nums只有一个重复的整数，找出这个重复的数。
     * 你设计的解决方案必须不修改数组nums且只用常量级O(1)的额外空间。
     */
    public int test1(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
            if (res == 0) return nums[i];
        }
        return -1;
    }
}
