package cn.yuyao.leetcode.day0312;

import cn.yuyao.leetcode.day0303.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法
 */
public class BacktrackDemo {

    public static void main(String[] args) {
        BacktrackDemo demo = new BacktrackDemo();
        List<List<Integer>> lists = demo.test1(new int[]{1, 2, 3});
        System.out.println(lists);
    }


    /**
     * 给定一个不含重复数字的数组nums，返回其所有可能的全排列。你可以按任意顺序返回答案。
     */
    public List<List<Integer>> test1(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        bk(result, new ArrayList<Integer>(),  nums);
        return result;
    }

    private void bk(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            ArrayList<Integer> list = new ArrayList<>(tempList);
            result.add(list);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i])) continue;
            tempList.add(nums[i]);
            bk(result, tempList, nums);
            tempList.remove(tempList.size() - 1);
        }
    }


    /**
     * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m *
     * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如
     * 果该单元格是空的，那么就是 0。
     */
    public int test2(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                res = Math.max(res, dfs(grid, i, j));
            }
        }
        return res;
    }

    public int dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
            return 0;
        }
        int temp = grid[x][y];
        grid[x][y] = 0;
        int up = dfs(grid, x - 1, y);
        int down = dfs(grid, x +1, y);
        int left = dfs(grid, x, y - 1);
        int right = dfs(grid, x, y + 1);
        int res = Math.max(up, Math.max(down, Math.max(left, right)));
        grid[x][y] = temp;
        return res + temp;
    }

    /**
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树
     * 的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     */
    public List<List<Integer>> test3(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), root, target, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> tempList, TreeNode root, int target, int sum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        tempList.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == target) {
                ArrayList<Integer> list = new ArrayList<>(tempList);
                result.add(list);
            }
        }
        dfs(result, tempList, root.left, target, sum);
        tempList.remove(tempList.size() - 1);
        dfs(result, tempList, root.right, target, sum);
        tempList.remove(tempList.size() - 1);
    }
}
