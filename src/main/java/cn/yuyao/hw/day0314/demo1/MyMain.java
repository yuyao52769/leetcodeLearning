package cn.yuyao.hw.day0314.demo1;

public class MyMain {

    private int m;

    private int n;

    private int maxDiff = 3;

    private int res = 0;

    public int test1(int m, int n) {
        if (m == 1) return 1;
        this.m = m;
        this.n = n;
        dfs(0, 1, n / m, n);
        return res;
    }

    public void dfs(int index, int min, int max, int remain) {
        if (index == m - 1) {
            if (remain - min <= maxDiff) {
                res++;
                return;
            }
        }
        index++;
       for (int i = min; i <= max; i++) {
           remain -= i;
           dfs(index, i, Math.min(i + maxDiff, (remain - i) / (n - index - 1)), remain);
           remain += i;
       }
    }
}
