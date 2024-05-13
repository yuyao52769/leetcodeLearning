package cn.yuyao.leetcode.day0303;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Day0303Demo {

    public static void main(String[] args) {
        Day0303Demo demo = new Day0303Demo();
        int i = demo.numDecodings("06");
        System.out.println(i);
    }


    /**
     * 94. 二叉树的中序遍历
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        doInorderTraversal(root, res);
        return res;
    }

    public void doInorderTraversal(TreeNode root, List res) {
        if (root == null) return;
        doInorderTraversal(root.left, res);
        res.add(root.val);
        doInorderTraversal(root.right, res);
    }
    /**
     * 100. 相同的树
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     *
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    /**
     *
     101. 对称二叉树
     给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }
    public boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null ||right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        } else {
            return dfs(left.left, right.right) && dfs(left.right, right.left);
        }
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树 root ，返回其最大深度。
     * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     */
    public int maxDepth(TreeNode root) {
        Count max = new Count();
        int curDept = 0;
        doGetMaxDept(root, curDept, max);
        return max.maxCount;
    }

    public void doGetMaxDept(TreeNode root, int curDept, Count max) {
        if (root == null) {
            int maxCount  = Math.max(max.maxCount, curDept);
            max.setMaxCount(maxCount);
            return;
        } else {
            curDept++;
            doGetMaxDept(root.left, curDept, max);
            doGetMaxDept(root.right, curDept, max);
        }
    }

    public static class Count{
        public Integer maxCount = 0;

        public Integer getMaxCount() {
            return this.maxCount;
        }

        public void setMaxCount(Integer max) {
            this.maxCount = max;
        }
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int maxLeftHeight = maxDepth2(root.left);
        int maxRightHeight = maxDepth2(root.right);
        return Math.max(maxLeftHeight, maxRightHeight) + 1;
    }


    /**
     * 将有序数组转换为二叉搜索树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }


    /**
     * 1382. 将二叉搜索树变平衡
     * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。如果有多种构造方法，请你返回任意一种。
     *
     * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
     */
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        getNodeList(root, list);
        return doBuild(0, list.size() - 1, list);
    }

    public void getNodeList(TreeNode root, List<Integer> list) {
        if (root.left != null) {
            getNodeList(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            getNodeList(root.right, list);
        }
    }

    public TreeNode doBuild(int left, int right, List<Integer> list) {
        int mid = (left + right) >> 1;
        TreeNode node = new TreeNode(list.get(mid));
        if (left <= mid - 1) {
            node.left = doBuild(left, mid - 1, list);
        }
        if (mid + 1 <= right) {
            node.right = doBuild(mid + 1, right, list);
        }
        return node;
    }

    /*
    110. 平衡二叉树
    给定一个二叉树，判断它是否是高度平衡的二叉树。
    本题中，一棵高度平衡二叉树定义为：
    一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(getTreeHeight(root.left) - getTreeHeight(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }


    public int getTreeHeight(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (root.left == null || root.right == null) return left + right + 1;
        return Math.min(left, right) + 1;
    }

    /**
     * 112. 路径总和
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
     * 这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        int sum = 0;
        return doHasPathSUm(root, targetSum, sum);
    }

    public boolean doHasPathSUm(TreeNode root, int targetSum, int realSum) {
        if (root == null) return realSum == targetSum;
        realSum += root.val;
        if (root.left == null && root.right == null) return realSum == targetSum;
        if (root.left == null && root.right != null) {
            return doHasPathSUm(root.right, targetSum, realSum);
        }
        if (root.left != null && root.right == null) {
            return doHasPathSUm(root.left, targetSum, realSum);
        }
        return doHasPathSUm(root.left, targetSum, realSum) || doHasPathSUm(root.right, targetSum, realSum);
    }

    /**
     * 144. 二叉树的前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        doPreorderTraversal(root, list);
        return list;
    }

    public void doPreorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        doPreorderTraversal(root.left, list);
        doPreorderTraversal(root.right, list);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        doPostorderTraversal(root, list);
        return list;
    }

    public void doPostorderTraversal(TreeNode root, List<Integer> list){
        if (root == null) return;
        doPostorderTraversal(root.left, list);
        doPostorderTraversal(root.right, list);
        list.add(root.val);
    }

    /**
     * 222. 完全二叉树的节点个数
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     *
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
     * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 226. 翻转二叉树
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     */
    public TreeNode invertTree(TreeNode root) {
        //递归函数的终止条件，节点为空时返回
        if(root==null) {
            return null;
        }
        //下面三句是将当前节点的左右子树交换
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        //递归交换当前节点的 左子树
        invertTree(root.left);
        //递归交换当前节点的 右子树
        invertTree(root.right);
        //函数返回时就表示当前这个节点，以及它的左右子树
        //都已经交换完了
        return root;
    }

    /**
     * 404. 左叶子之和
     * 给定二叉树的根节点 root ，返回所有左叶子之和。
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root == null) return sum;
        if (root.left == null);
        return sum;
    }

    public int doSumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root == null) return sum;
        if (root.left == null) return sum;
        sum += root.val;
        return sum;
    }

    /**
     * 91. 解码方法
     * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
     *
     * 'A' -> "1"
     * 'B' -> "2"
     * ...
     * 'Z' -> "26"
     * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
     *
     * "AAJF" ，将消息分组为 (1 1 10 6)
     * "KJF" ，将消息分组为 (11 10 6)
     * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
     *
     * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
     *
     * 题目数据保证答案肯定是一个 32 位 的整数。
     */
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 0;
            int cur = s.charAt(i) == '0' ? 0 : 1;
            if (i == 0) {
                dp[i] = cur == 0 ? 0 : 1;
            } else {
                if (cur != 0) {
                    dp[i] += dp[i-1];
                }
                if (i >= 1 && (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' )) {
                    int val = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
                    if (val <= 26) {
                        if (i == 1) {
                            dp[i] ++;
                        } else {
                            dp[i] += dp[i - 2];
                        }
                    }
                }
            }
        }
        return dp[len - 1];
    }

    public int numDecodingsCo(String s) {
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i] = 0;
            boolean zero = s.charAt(i) == '0';
            if (i == 0) {
                dp[i] = zero ? 0 : 1;
            } else {
                if (!zero) {
                    dp[i] += dp[i-1];
                }

                if (i >= 1 && (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') && s.charAt(i) <= '6') {
                    if (i == 1) {
                        dp[i]++;
                    } else {
                        dp[i] += dp[i-2];
                    }

                }
            }
        }
        return dp[s.length() - 1];
    }

    /**
     * 1043. 分隔数组以得到最大和
     * 给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
     *
     * 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
     * 输入：arr = [1,15,7,9,2,5,10], k = 3
     * 输出：84
     * 解释：数组变为 [15,15,15,9,10,10,10]
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        // 设 d[i] 为以 i 结尾时（即 arr[0...i]）分割后的最大和，最后只需要返回 dp[n-1]
        int[] dp = new int[n];
        // 递推，i 是结束位置
        for (int i = 0; i < n; ++i) {
            // 倒序枚举最后一个子数组的起点，[j...i] 长度不能k
            // 在倒序枚举j的过程中 更新 max，得到 arr[j...i]内的最大值
            int max = arr[i];
            // 1 <= len([j...i] = i-j+1) <= k
            for (int j = i; j >= Math.max(i - k + 1, 0); --j) {
                // 当前 arr[j...i] 内最大值
                if (arr[j] >= max) {
                    max = arr[j];
                    dp[i] = Math.max(dp[i], (j > 0 ? dp[j - 1] : 0) + (i - j + 1) * max);
                }
                // 为以 i 结尾时（即 arr[0...i]）分割后的最大和 = 最后一段子数组[j...i]的最大和 + 前面[0...j-1]的最大分割和
                // 注意不要越界

            }
        }
        // 返回
        return dp[n - 1];
    }

}


