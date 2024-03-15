package cn.yuyao.hw.day0314;

import cn.yuyao.leetcode.day0303.TreeNode;

import java.util.*;

public class Demo1 {

    private int sum = 0;

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();



    }


    /**
     * HJ16 购物单
     * 王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
     */


    /**
     * HJ17 坐标移动
     * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
     * 输入：
     * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
     * 坐标之间以;分隔。
     * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
     * 下面是一个简单的例子 如：
     * A10;S20;W10;D30;X;A1A;B10A11;;A10;
     */
    public int[] test17(String str) {
        int x = 0;
        int y = 0;
        List<Character> chars = Arrays.asList('A', 'S', 'W', 'D');
        String[] split = str.split(";");
        for (String childStr : split) {
            if (!valid(childStr, chars)) continue;
            char operate = childStr.charAt(0);
            Integer length = Integer.valueOf(childStr.substring(1));
            switch (operate){
                case 'A':
                    x -= length;
                    break;
                case 'D':
                    x += length;
                    break;
                case 'W':
                    y += length;
                    break;
                case 'S':
                    y -= length;
                    break;
            }
        }
        return new int[]{x, y};
    }

    private boolean valid(String str, List<Character> chars) {
        if (str == null || str.length() <= 1) return false;
        char c = str.charAt(0);
        if (!chars.contains(c))return false;
        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 华为OD机试 - 最富裕的小家庭
     */
    public void test17() {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] weight = new int[length + 1];
        int[] family = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            weight[i] = in.nextInt();
            family[i] = weight[i];
        }

        for (int i = 1; i <= length - 1; i++){
            int father = in.nextInt();
            int child = in.nextInt();
            family[father] += weight[child];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= length; i++) {
            max = Math.max(max, family[i]);
        }
        System.out.println(max);
    }

    /**
     * 最长子字符串的长度（一）
     */
    public void test18(String str) {
        int n = str.length();
        String realStr = str + str;
        int left = 0;
        int right = 0;
        int maxLength = 0;
        int oCount = 0;
        while (right < realStr.length()) {
            if (realStr.charAt(right++) == 'o') {
                oCount++;
            }
            if (oCount > 0 && ((oCount & 1) == 0)) {
                maxLength = Math.max(maxLength, (right - left) % n);
            }
        }

        while (left < realStr.length()) {
            if (realStr.charAt(left++) == 'o') {
                oCount--;
            }
            if (oCount > 0 && ((oCount & 1) == 0)) {
                maxLength = Math.max(maxLength, (right - left) % n);
            }
        }
        System.out.println(maxLength);
    }

    /**
     * 找座位
     */
    public void test19(String str) {
        int count = 0;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == '1') {
                i += 2;
            } else {
                if (i < length - 1) {
                    if (str.charAt(i + 1) == '0') {
                        count++;
                        i += 2;
                    } else {
                        i += 3;
                    }
                }
            }
        }
    }

    /**
     * 猴子吃西瓜
     */
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        int k = high;
        while (low < high) {
            int speed = (high - low) / 2 + low;
            int time = getTime(piles, speed);
            if (time <= h) {
                k = speed;
                high = speed;
            } else {
                low = speed + 1;
            }

        }
        return k;
    }

    private int getTime(int[] piles, int speed) {
        int time = 0;
        for (int pile : piles) {
            time += Math.ceil(pile * 1.0 / speed);
        }
        return time;
    }

    /**
     * 装满石头的背包的最大数量
     */
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        for (int i = 0; i < capacity.length; i++) {
            capacity[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(capacity);
        int count = 0;
        for (int i = 0; i < capacity.length && additionalRocks > 0; i++) {
            if (capacity[i] == 0) {
                count++;
                continue;
            }
            additionalRocks -= capacity[i];
            if (additionalRocks < 0) {
                break;
            }
            count++;
        }
        return count;
    }

    /**
     * 二叉树计算
     */
    public void test5(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            bfs(node);
            node.val = sum;
            this.sum = 0;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    private void bfs(TreeNode node) {
        if (node == null) return;
        if (node.left != null) {
            sum += node.left.val;
        }
        if (node.right != null) {
            sum += node.right.val;
        }
        bfs(node.left);
        bfs(node.right);
    }
}
