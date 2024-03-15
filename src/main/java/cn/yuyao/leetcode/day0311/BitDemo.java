package cn.yuyao.leetcode.day0311;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 位运算
 */
public class BitDemo {

    public static void main(String[] args) {
        BitDemo demo = new BitDemo();
        int i = demo.test4(11);
        System.out.println(i);
    }

    /**
     * 给你两个整数left和right，表示区间[left,right]，返回此区间内所有数字按位与的结
     * 果（包含left、right端点）。
     */
    public int test1(int left, int right) {
        int bit = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            bit++;
        }
        return left << bit;
    }

    /**
     * 所 有 DNA 都 由 一 系 列 缩 写 为 'A' ， 'C' ， 'G' 和 'T' 的 核 苷 酸 组 成 ， 例
     * 如："ACGAATTCCG"。在研究DNA时，识别DNA中的重复序列有时会对研究非常有
     * 帮助。
     * 编写一个函数来找出所有目标子串，目标子串的长度为10，且在DNA字符串s中出现
     * 次数超过一次。
     */
    public List<String> test2(String str) {
        Set<String> res = new HashSet<>();
        Set<Integer> subStr = new HashSet<>();
        char[] map = new char[128];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;
        for (int i = 0; i < str.length() - 9; i++) {
            //截取10位数字，计算他们的值，这个是每两位存储
            //一个字母对应的值
            int bitmap = 0;
            for (int j = i; j < i + 10; j++) {
                //计算一次就往左移动两位
                bitmap <<= 2;
                bitmap |= map[str.charAt(j)];
            }
            if (!subStr.add(bitmap)) {
                res.add(str.substring(i, i + 10));
            }
        }
        return new ArrayList<>(res);
    }

    /**
     * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。
     *
     * 例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
     */
    public int test3(int num) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if(((num >>> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    public int test4(int num) {
        int count = 0;
        while (num != 0) {
            count += (num & 1);
            num >>= 1;
        }
        return count;
    }

    public int test5(int num) {
        int count = 0;
        while (num != 0) {
            num &= (num - 1);
            count++;
        }
        return count;
    }

    /**
     * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     * 给出两个整数 x 和 y，计算它们之间的汉明距离。
     */
    public int test6(int x, int y) {
        int n = x ^ y;
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>= 1;
        }
        return count;
    }
}
