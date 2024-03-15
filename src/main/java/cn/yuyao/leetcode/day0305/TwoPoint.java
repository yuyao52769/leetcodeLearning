package cn.yuyao.leetcode.day0305;

import java.util.*;

public class TwoPoint {

    public static void main(String[] args) {
        String hello = "1234";
        System.out.println(hello.substring(0, 4));
        System.out.println(hello.substring(0, hello.length()- 1));
    }

    /**
     *给定一个包含红色、白色和蓝色，一共n个元素的数组，原地对它们进行排序，使得相
     * 同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数0、1和2分别表示红色、白色和蓝色
     */
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int index = 0;
        while (index <= right) {
            int num = nums[index];
            if (num == 0) {
                index++;
                left++;
            } else if (num == 1) {
                index++;
            } else {
                swap(nums, left, right--);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，
     * 并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表。
     */
    public ListNode addTwoNumbers(ListNode listNode1, ListNode listNode2) {
        //创建一个哑节点，他的指针指向新链表的头节点
        ListNode dummyNode = new ListNode(0);
        //preNode表示当前节点的前一个节点
        ListNode preNode = dummyNode;
        //表示两个节点相加进位的值，加法最多只进一位，所以carry要么是1要么是0
        int carry = 0;

        while (listNode1 != null || listNode2 != null || carry != 0) {
            //当前节点的累加值，需要加上前面进位的值
            int sum = carry;
            //如果第一个链表的当前节点不为空，加上第一个链表当前节点的值
            if (listNode1 != null) {
                sum += listNode1.val;
                listNode1 = listNode1.next;
            }
            //第二个链表，同上
            if (listNode2 != null) {
                sum += listNode2.val;
                listNode2 = listNode2.next;
            }
            //创建新的节点，preNode的next指针指向新的节点，因为链表节点
            //只能存储一位数字，所以这里要对sum求余，取个位数。
            preNode.next = new ListNode(sum % 10);
            //如果sum大于等于10，说明有进位，carry为1，
            //否则没有，carry为0
            carry = sum / 10;
            //更新preNode
            preNode = preNode.next;
        }
        return dummyNode.next;
    }

    /**
     * 给你一个包含n个整数的数组nums，判断nums中是否存在三个元素a，b，c，使得
     * a+b+c=0？请你找出所有和为0且不重复的三元组。
     */
    public List<List<Integer>> threeSum(int[] num) {
        //先对数组进行排序
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < num.length - 2; i++) {
            //过滤掉重复的
            if (i > 0 && num[i] == num[i - 1]) continue;
            //因为是排序的，如果第一个数字大于0，那么后面的也都
            //大于0，他们三个数字的和不可能等于0
            if (num[i] > 0)
                break;
            int left = i + 1;//左指针
            int right = num.length - 1;//右指针
            int target = -num[i];
            while (left < right) {
                //左右指针的和
                int sum = num[left] + num[right];
                if (sum == target) {
                    res.add(Arrays.asList(num[i], num[left], num[right]));
                    while (left < right && num[left] == num[left + 1])
                        left++;
                    while (left < right && num[right] == num[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }

            }
        }

        return res;
    }

    /**
     * 给 定 一 个 整 数 数 组 和 一 个 整 数 k ， 判 断 数 组 中 是 否 存 在 两 个 不 同 的 索 引 i 和 j ， 使 得
     * nums[i]=nums[ j]，并且i和j的差的绝对值至多为k。
     */
    public boolean test1(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }


    /**
     * 给定一个非空字符串s，最多删除一个字符。判断是否能成为回文字符串
     */
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return huiwen(s, left + 1, right) || huiwen(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean huiwen(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 滑动窗口解可获得的最大点数
     * 几张卡牌排成一行，每张卡牌都有一个对应的点数。点数由整数数组cardPoints给出。
     * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿k张卡牌。
     * 你的点数就是你拿到手中的所有卡牌的点数之和。
     * 给你一个整数数组cardPoints和整数k，请你返回可以获得的最大点数。
     */
    public int maxScore(int[] cardPoints, int k) {
        int maxWindow = 0, length = cardPoints.length;
        for (int i = 0; i < k; i++)
         maxWindow += cardPoints[i];
        //然后窗口移动，更新当前窗口的值
        int curWindow = maxWindow;
        for (int i = length - 1; i >= length - k; i--) {
            //窗口移动的时候一个元素会出窗口，一个元素会进入窗口。
            //cardPoints[k - (length - i)]是移除窗口的元素
            curWindow -= cardPoints[k - (length - i)];
             //cardPoints[i]是进入窗口的元素
            curWindow += cardPoints[i];
            //记录窗口的最大值
            maxWindow = Math.max(maxWindow, curWindow);
        }
        return maxWindow;
    }




    public int maxScore2(int[] cardPoints, int k) {
        int maxWindow = 0, length = cardPoints.length;
        for (int i = 0; i < k; i++)
            maxWindow += cardPoints[i];
        //然后窗口移动，更新当前窗口的值
        int curWindow = maxWindow;
        int j = length - 1;
        for (int i = k - 1; i >= 0; i--) {
            curWindow -= cardPoints[i];
            curWindow += cardPoints[j];
            j--;
            maxWindow = Math.max(maxWindow, curWindow);
        }
        return maxWindow;
    }


}
