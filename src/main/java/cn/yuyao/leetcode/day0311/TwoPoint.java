package cn.yuyao.leetcode.day0311;

import cn.yuyao.leetcode.day0305.ListNode;

public class TwoPoint {

    public static void main(String[] args) {
        TwoPoint demo = new TwoPoint();
//        String s = demo.demo1("ADOBECODEBANC", "ABC");
//        System.out.println(s);
//        int i = demo.test2(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 4, 5, 5});
//        System.out.println(i);
        demo.hanoi(10, 'A', 'B', 'C');
    }


    // 给你一个字符串s、一个字符串t。返回s中涵盖t所有字符的最小子串。如果s中不存在涵
    //盖t所有字符的子串，则返回空字符串""。
    public String demo1(String s, String t) {
        int[] map = new int[128];
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }

        int miniLength = Integer.MAX_VALUE;
        int miniLeft = 0;
        int count = t.length();

        int left = 0, right = 0;
        while (right < s.length()) {
            if (map[s.charAt(right++)]-- > 0) {
                count--;
            }
            while (count == 0) {
                int length = right - left;
                if (length < miniLength) {
                    miniLength = length;
                    miniLeft = left;
                }
               if (map[s.charAt(left++)]++ >= 0) {
                   count++;
               }
            }
        }
       return s.substring(miniLeft, miniLeft + miniLength);
    }

    /**
     * 给你一个有序数组nums ，请你原地删除重复出现的元素，使每个元素只出现一次 ，返
     * 回删除后数组的新长度。
     */
    public int test2(int[] nums) {
        int length = nums.length;
        if (length <= 1) return 1;
        int left = 0;
        int right = 1;
        int count = 0;
        while (right < length) {
            if (nums[right++] == nums[left]) {
                count++;
            } else {
                left = right - 1;
            }
        }
        return length - count;
    }

    /**
     * 输入一个正整数target，输出所有和为target的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     */


    /**
     * 给定一个链表，判断链表中是否有环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引
     * 从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
     */
    public boolean test4(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        int length = 1;
        while (fast.next != null) {
            length++;
            fast = fast.next;
        }
        fast.next = head;

        int size = length - k % length;
        while (size-- > 1) {
            slow = slow.next;
        }
        ListNode target = slow.next;
        slow.next = null;
        return target;
    }

    /**
     * 汉诺塔问题
     */
    public void hanoi(int n, char A, char B, char C) {
        if (n == 1) {
            System.out.println("从" + A + "移动到" + C);
            return;
        } else {
            hanoi(n-1, A, C, B);
            System.out.println("从" + A + "移动到" + C);
            hanoi(n-1, B, A, C);
        }
    }
}
