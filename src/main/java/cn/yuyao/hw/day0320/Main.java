package cn.yuyao.hw.day0320;

import java.util.*;

public class Main {

    private static int minLength = 0;

    private static Map<Integer, List<Integer>> routeA = new HashMap<>();;

    private static Map<Integer, List<Integer>> routeB = new HashMap<>();;

    public static Map<Integer, Integer> cus = new HashMap<>();

    public static List<int[]> lengthMap = new ArrayList<>();

    public static Set<Integer> hasRe = new HashSet<>();

    public static ArrayList<Integer> pointList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();

        for (int i = 0; i < m; i++) {
            int index = in.nextInt();
            int length = in.nextInt();
            cus.put(index, length);
        }
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int length = in.nextInt();
            List<Integer> orDefaultA = routeA.getOrDefault(a, new ArrayList<Integer>());
            orDefaultA.add(b);
            List<Integer> orDefaultB = routeB.getOrDefault(b, new ArrayList<Integer>());
            orDefaultB.add(a);
            lengthMap.add(new int[]{a, b, length});
        }

        Set<Integer> integers = cus.keySet();

        pointList = new ArrayList<>(cus.keySet());
        int curLength = 0;
        for (Integer point : integers) {
            dfs(point, curLength);
        }
        System.out.println(minLength);
    }

    private static void dfs(int index, int curLength) {
        if (hasRe.contains(index)) {
            dfs(index+1, curLength);
        }
        if (!routeA.containsKey(index) && !routeB.containsKey(index)) {
            curLength += cus.get(index) * 2;
        }
    }
}

