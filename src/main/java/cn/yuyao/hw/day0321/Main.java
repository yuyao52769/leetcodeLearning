package cn.yuyao.hw.day0321;

import java.util.*;

public class Main {

    private static int minLength = 0;

    private static Map<Integer, Integer> routeA = new HashMap<>();;

    private static Map<Integer, Integer> routeB = new HashMap<>();;

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
            routeA.put(a, b);
            routeB.put(b, a);
//            List<Integer> orDefaultA = routeA.getOrDefault(a, new ArrayList<Integer>());
//            orDefaultA.add(b);
//            List<Integer> orDefaultB = routeB.getOrDefault(b, new ArrayList<Integer>());
//            orDefaultB.add(a);
            lengthMap.add(new int[]{a, b, length});
        }

        Set<Integer> integers = cus.keySet();
        Set<Integer> special = new HashSet<>();
        pointList = new ArrayList<>(cus.keySet());
        int curLength = 0;
        for (Integer point : integers) {
           if (hasRe.contains(point)) continue;
           if (special.contains(point)) {
               if (!routeA.containsKey(point) && !routeB.containsKey(point)) {
                   curLength += cus.get(point);
                   continue;
               } else {
                   int next = 0;
                   if (routeA.containsKey(point)) {
                       next = routeA.get(point);
                   } else {
                       next = routeB.get(point);
                   }

                   int route = getRoute(lengthMap, point, next);
                   curLength = curLength + (route);
                   special.add(next);
               }
           }
           else if (!routeA.containsKey(point) && !routeB.containsKey(point)) {
                curLength += cus.get(point) * 2;
               hasRe.add(point);
           } else {
               int next = 0;
               if (routeA.containsKey(point)) {
                   next = routeA.get(point);
               } else {
                   next = routeB.get(point);
               }
               routeA.remove(point);
               routeA.remove(next);
               routeB.remove(point);
               routeB.remove(next);
               int route = getRoute(lengthMap, point, next);
               curLength = curLength + (route + cus.get(point));
               hasRe.add(point);
               special.add(next);
           }
        }
        System.out.println(curLength);
    }

    public static int getRoute(List<int[]> lengthMap, int x, int y) {
        for (int[] ints : lengthMap) {
            if (ints[0] == x && ints[1] == y) {
                return ints[2];
            } else if (ints[0] == y && ints[1] == x) {
                return ints[2];
            }
        }
        return 0;
    }
}

