package cn.yuyao.hw.day0318;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String str = in.nextLine().trim();
        if (str == null || str.length() <= 1) {
            System.out.println(0);
            return;
        }
        int count = 0;
        int[] map = new int[2];
        for (int i = 0; i < str.length(); i++) {
            int index = index(str.charAt(i));
            map[index]++;
            if (isEq(map)) {
                count++;
                clear(map);
            }
        }
        System.out.println(count);
    }

    private static int index(char charAt) {
        if (charAt == 'X') {
            return 0;
        }
        return 1;
    }

    private static boolean isEq(int[] map) {
        return map[0] == map[1] && map[0] != 0;
    }

    private static void clear(int[] map) {
        for (int i = 0; i < map.length; i++) {
            map[i] = 0;
        }
    }
}
