package cn.yuyao.leetcode.day0305;

import cn.yuyao.leetcode.day0303.TreeNode;

import java.util.*;

/**
 * DFS算法
 */
public class DFSDemo {

    public int res = 0;

    public int maxPath = 0;

    public int count = 0;

    public static void main(String[] args) {
        DFSDemo demo = new DFSDemo();
//        List<String> strings = demo.letterCombinations("23");
//        System.out.println(strings);
        //demo.test2()
       // TreeNode root = demo.buildTree();
        //Integer integer = demo.test2(root);
//        List<Integer> path = new ArrayList<>();
//        int i = demo.test3(root);
//        System.out.println(i);
      //  int i = demo.test4(root);
       // System.out.println(i);
        int a = 1;
        if (--a > 0) {
            System.out.println(a);
        } else {
            System.out.println(a);
        }
    }

    public TreeNode build(Integer[] nums) {
        TreeNode root = null;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != null) {
                TreeNode node = new TreeNode(nums[i]);
                if (i == 0) {
                    root = node;
                } else {

                }
            } else {

            }
        }
        return root;
    }

    public TreeNode buildTree() {
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(0);
        TreeNode right1 = new TreeNode(1);
        TreeNode left11 = new TreeNode(0);
        TreeNode left12 = new TreeNode(1);

        TreeNode right11 = new TreeNode(0);
        TreeNode right12 = new TreeNode(1);

        root.left = left1;
        root.right = right1;
        left1.left = left11;
        left1.right = left12;

        right1.left = right11;
        right1.right = right12;
        return root;
    }

    /**
     * 630，Leetcode原题电话号码的字母组合的两种解法
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按任意顺
     * 序返回。给出数字到字母的映射如下（与电话按键相同）。注意1不对应任何字母
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() <= 0) return res;
        char[][] tab = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
                {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        dfs(res, 0, digits, tab, "");
        return res;
    }

    /**
     * @param res
     * @param index 表示访问到第几个数字了，也可以认为访问到树的第几层了
     * @param digits
     * @param tab
     * @param path 从根节点到叶子结点的路径
     */
    private void dfs(List<String> res, Integer index, String digits, char[][] tab,  String path) {
        if (digits.length() == path.length()) {
            res.add(path);
            return;
        }
        char[] chars = tab[digits.charAt(index) - '2'];
        for (int i = 0; i < chars.length; i++) {
            dfs(res, index+1, digits, tab, path + chars[i]);
        }
    }



























    public void dfs01(List<String> res, Integer index, String dig, char[][] tab, String path) {
        if (index == path.length()) {
            res.add(path);
            return;
        }

        char[] chars = tab[dig.charAt(index) - '2'];
        for (int i = 0; i < chars.length; i++) {
            dfs01(res, index++, dig, tab, path + chars[i]);
        }
    }


    /**
     * LeetCode第1022题
     * 给出一棵二叉树，其上每个结点的值都是0或1。每一条从根到叶的路径都代表一个从最
     * 高有效位开始的二进制数。例如，如果路径为0->1->1->0->1，那么它表示二进制数
     * 01101，也就是13。对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的
     * 数字。
     * 返回这些数字之和。题目数据保证答案是一个32位整数。
     */
    public Integer test2(TreeNode root) {
        dfs02(root, 0);
        return res;
    }

    public void dfs02(TreeNode root, int parentPathSum) {
        //如果节点为空，直接返回
        if (root == null)
            return;
        //父节点的值*2，在加上当前节点的值就是从根节点到
        //当前节点这条路径表示的数字
        int sum = parentPathSum * 2 + root.val;
        //如果到叶子节点，说明找到了一个从根节点到叶子
        //节点的完整路径，把这条路径的值加到res中
        if (root.left == null && root.right == null) {
            res += sum;
            return;
        }
        //如果没到叶子节点就继续遍历当前节点的左子节点和右子节点
        dfs02(root.left, sum);
        dfs02(root.right, sum);
    }

    public int test3(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        dfs03(root, path);
        return res;
    }

    public void dfs03(TreeNode root, List<Integer> path) {
        if (root == null) return;
        // 到达叶子节点了
        path.add(root.val);
        if (root.left == null && root.right == null) {
            res += cal(path);
            return;
        }
        dfs03(root.left, path);
        path.remove(path.size() - 1);
        dfs03(root.right, path);
        path.remove(path.size() - 1);
    }

    private int cal(List<Integer> path) {
        int sum = 0;
        int size = path.size();
        for (int i = 0; i < size; i++) {
            sum += path.get(i) * Math.pow(2, size - i - 1);
        }
        return sum;
    }

    /**
     * LeetCode第1302题
     * 给你一棵二叉树的根节点root，请你返回层数最深的叶子节点的和
     */
    public int test4(TreeNode root) {
        deep(root);
        deepSum(root, 0);
        return res;
    }

    public int deep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = deep(root.left);
        int right = deep(root.right);
        int deep = Math.max(left, right) + 1;
        if (deep >= maxPath) {
            maxPath = deep;
        }
        return deep;
    }

    public int deepSum(TreeNode root, int deep) {
        if (root == null) {
            return deep;
        }
        deep++;
        if (root.left == null && root.right == null && deep == maxPath) {
            res += root.val;
            return deep;
        }
        deepSum(root.left, deep);
        deepSum(root.right, deep);
       return deep;
    }

    public void dfs5(TreeNode root, int deep) {
        if (root == null) return;
        deep++;
        maxPath = Math.max(deep, maxPath);

        dfs5(root.left, deep);
        dfs5(root.right, deep);
    }

    /**
     * 给你一个整数数组nums和一个整数target。
     * 向数组中的每个整数前添加'+'或'-'，然后串联起所有整数，可以构造一个表达式 ：
     * 例如，nums=[2,1]，可以在2之前添加'+'，在1之前添加'-'，然后串联起来得到表
     * 达式"+2-1"。
     * 返回可以通过上述方法构造的、运算结果等于target的不同表达式的数目。
     */

    public int test6(int[] nums, int target) {
        dsf6(nums, target, 0, 0);
        return count;
    }
    public void dsf6(int[] nums, int target, int sum, int index) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
            return;
        }
        dsf6(nums, target, sum - nums[index + 1], index++);
        dsf6(nums, target, sum + nums[index + 1], index++);
    }

    public void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 做一些事情
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            level++;
        }
    }

    /**
     * BFS和动态规划解完全平方数
     * 给定正整数n，找到若干个完全平方数（比如1，4，9，16,...）使得它们的和等于n。你
     * 需要让组成和的完全平方数的个数最少。
     * 给你一个整数n，返回和为n的完全平方数的最少数量 。
     * 完全平方数是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘
     * 的积。例如，1、4、9和16都是完全平方数，而3和11不是。
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i; //最坏的情况都是由1的平方组成
            for (int j = 1; j * j <= i; j++) {
                //动态规划公式
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
