package cn.yuyao.leetcode.day0303;

import java.nio.file.attribute.UserPrincipal;

/**
 * 埃氏筛选法
 */
public class Solution572 {

    static final int MAX_N = 1005;
    static final int MOD = 1000000007;
    boolean[] vis = new boolean[MAX_N];
    int[] p = new int[MAX_N];

    int tot;

    public static void main(String[] args) {
        Solution572 demo = new Solution572();
        demo.getPrime3();
        System.out.println("111");
    }

    public void getPrime() {
        // true代表合数
        vis[0] = vis[1] = true;
        tot = 0;
        for (int i = 2; i < MAX_N; ++i) {
            if (!vis[i]) {
                p[++tot] = i;
            }
            for (int j = 1; j <= tot && i * p[j] < MAX_N; ++j) {
                vis[i * p[j]] = true;
                if (i % p[j] == 0) {
                    break;
                }
            }
        }
    }

    /**
     * 计算100以内的素数
     */
    public void getPrime2() {
        boolean[] isPrime = new boolean[101];
        isPrime[0] = true;
        isPrime[1] = true;
        int[] Prime = new int[101];
        int t = 0;
        for (int i = 2; i * i < isPrime.length; i++) {
            if (isPrime[i]) continue;
            Prime[t++] = i;
            for (int j = 2; i * j < isPrime.length; j++) {
                if (!isPrime[i]) {
                    isPrime[i * j] = true;
                }
            }
        }

        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println(Prime);
    }


    private void getPrime3() {
        boolean[] isPrime = new boolean[101];
        int point = 0;
        int[] result = new int[101];
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i <= 100; i++) {
            if (!isPrime[i]) {
                result[++point] = i;
            }
            for (int j = 1; j <= point && result[j] * i <= 100; j++) {
                isPrime[i * result[j]] = true;
                if (i % result[j] == 0) break;
            }
        }
        System.out.println("123");
    }



































    private void getPrime4() {
        boolean[] isPrime = new boolean[101];
        isPrime[0] = isPrime[1] = true;
        int[] result = new int[101];
        int point = 0;
        for (int i = 2; i < 101; i++) {
            if (!isPrime[i]) {
                result[++point] = i;
            }
            for (int j = 1; j <= point && result[j] * i < 101; j++) {
                isPrime[i * result[j]] = true;
                if ( i % result[j] == 0) break;
            }
        }
    }
}
