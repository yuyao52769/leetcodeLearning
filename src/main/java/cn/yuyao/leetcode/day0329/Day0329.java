package cn.yuyao.leetcode.day0329;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Day0329 {

    public volatile Integer age = 0;

    private static long ageOffset;

    private static Unsafe UNSAFE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
        }
    }



    public static void main(String[] args) throws Exception {

        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future<Integer> future = pool.submit(() -> {
            Thread.sleep(10000);
            return 20;
        });
        try {
            Integer integer = future.get();
            System.out.println(integer);
        } catch (Exception e) {
            System.out.println(e);
        }
//
//        Day0329 day = new Day0329();
//        Day0329 o =  (Day0329)UNSAFE.allocateInstance(Day0329.class);
//        ageOffset = UNSAFE.objectFieldOffset(o.getClass().getDeclaredField("age"));
//        boolean b = UNSAFE.compareAndSwapInt(o, ageOffset, 0, 1);
//        System.out.println(b);
    }
}
