package cn.yuyao.leetcode.day0303;

public class Solution543 {

    private Integer maxDeep = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        getMaxDeep(root);
        return maxDeep;
    }

    public int getMaxDeep(TreeNode root) {
        if (root == null) return 0;
        int left = getMaxDeep(root.left);
        int right = getMaxDeep(root.right);
        int curDeep =  left + right + 1;
        maxDeep = Math.max(curDeep, maxDeep);
        return Math.max(left, right) + 1;
    }

}
