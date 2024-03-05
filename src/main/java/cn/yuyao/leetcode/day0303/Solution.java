package cn.yuyao.leetcode.day0303;

import sun.reflect.generics.tree.VoidDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中的众数
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 * 假定 BST 满足如下定义：
 *
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 */
public class Solution {

    List<Integer> numList = new ArrayList<>();
    Integer base = 0;
    Integer maxCount = 0;

    Integer curCount = 0;

    public int[] findMode(TreeNode root) {
        dfs(root);
        return numList.stream().mapToInt(k -> k).toArray();
    }

    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        update(root.val);
        dfs(root.right);
    }

    public void update(Integer value) {
       if (base == value) {
            curCount++;
        }else{
            base = value;
            curCount = 1;
        }
        if (maxCount == curCount) {
            numList.add(base);
        }

        if (curCount > maxCount) {
            maxCount = curCount;
            numList.clear();
            numList.add(base);
        }
    }
}
