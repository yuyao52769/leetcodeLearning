package cn.yuyao.hw.day0319;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String trim = in.nextLine().trim();
        int m = Integer.valueOf(trim);
        String quezhen = "";
        quezhen = in.nextLine().trim();
        String[] split = quezhen.split(",");
        int[] quezhenInt = new int[split.length];
        boolean zero = false;
        for (int i = 0; i < split.length; i++) {
            if (split[i] == null || split[i].equals("") || split[i].equals(" ")) {
                zero = true;
                break;
            }
            quezhenInt[i] = Integer.valueOf(split[i]);
        }
        int[][] nums = new int[m][m];
        for (int i = 0; i < m; i++) {
            nums[i] = convert(in.nextLine().trim());
        }
        if (zero) {
            System.out.println(0);
        }
        List<int[]> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = i+1; j < m; j++) {
                if (nums[i][j] == 1) {
                    res.add(new int[]{i, j});
                }
            }
        }

        Set<Integer> chanbo = new HashSet<>();

        for (int i = 0; i < res.size(); i++) {
            int[] ints = res.get(i);
            if (contains(quezhenInt, ints[0])) {
               chanbo.add(ints[1]);
            } else {
                if (contains(quezhenInt, ints[1])){
                    chanbo.add(ints[0]);
                } else {
                    continue;
                }
            }
        }

        for (int i : quezhenInt) {
            chanbo.remove(i);
        }

        for (int i = 0; i < res.size(); i++) {
            int[] ints = res.get(i);
            if (chanbo.contains(ints[0])) {
                chanbo.add(ints[1]);
            } else if (chanbo.contains(ints[1])) {
                chanbo.add(ints[0]);
            }
        }
        for (int i : quezhenInt) {
            chanbo.remove(i);
        }

        for (int i = 0; i < res.size(); i++) {
            int[] ints = res.get(i);
            if (chanbo.contains(ints[0])) {
                chanbo.add(ints[1]);
            } else if (chanbo.contains(ints[1])) {
                chanbo.add(ints[0]);
            }
        }
        for (int i : quezhenInt) {
            chanbo.remove(i);
        }

        System.out.println(chanbo.size());
    }

    private static int[] convert(String str) {
        String[] strArr = str.split(",");
        int[] res = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            res[i] = Integer.valueOf(strArr[i]);
        }
        return res;
    }

    private static boolean contains(int[] con, int k) {
        for (int i : con) {
            if (i == k) return true;
        }
        return false;
    }
}
