package cn.yuyao.hw.day0314.demo1;

import java.util.Scanner;

public class Main {
    static int m;
    static int n;
    static int maxDiff = 3;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();

        if (m == 1) {
            // 如果只有一个员工分月饼，那么就只有一种方案
            System.out.println(1);
        } else {
            // 如果有多个员工分月饼，为了保证分月饼的方案不重复，我们这里保证 员工i的月饼数量 <= 员工i+1的月饼数量
            // 因此对于第0个员工，至少分得1个月饼，至多分得n/m个月饼（均分数量）
            recursive(0, 1, n / m, n);
            System.out.println(ans);
        }
    }

    /**
     * @param level 第几个员工
     * @param min 当前员工至少分得几个月饼
     * @param max 当前员工至多分得几个月饼
     * @param remain 分月饼给当前员工前，月饼的剩余数量
     */
    public static void recursive(int level, int min, int max, int remain) {
        if (level == m - 1) {
            // 分到最后一个员工时，我们应该将剩余月饼都给他
            // 因此最后一个员工的月饼数量就是remain，而倒数第二个员工的月饼数量是min（本轮递归的min参数，即上一轮员工分得的月饼数量）
            // 如果二者差距不超过maxDiff，则分月饼方案可行
            if (remain - min <= maxDiff) {
                ans++;
            }
            return;
        }

        // i 是当前员工可以分得的月饼数量
        for (int i = min; i <= max; i++) {
            // 下一个员工至少分得 i 个月饼（当前员工分得的月饼数量），至多分得 i + maxDiff
            // 同时下一个员工分得的月饼数量不能超过：均分月饼数量（即剩余月饼总数 / 剩余员工总数），否则破坏去重策略（为了保证分月饼的方案不重复，我们这里保证后面的员工分得月饼数不小于前面员工）
            remain -= i;
            recursive(level + 1, i, Math.min(i + maxDiff, remain / (m - level - 1)), remain);
            remain += i;
        }
    }
}
