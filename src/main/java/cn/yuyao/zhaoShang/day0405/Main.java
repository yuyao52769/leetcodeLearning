package cn.yuyao.zhaoShang.day0405;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        ThreadTest();
    }

    public static void ThreadTest()  throws Exception {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardPolicy());

        Future<String> submit1 = pool.submit(() -> {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(date);
            System.out.println(format + "====" + Thread.currentThread().getName() + "开始啦!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Date date1 = new Date();
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format1 = simpleDateFormat.format(date1);
            System.out.println(format1 + "===" + Thread.currentThread().getName() + "结束啦!");
            return "123";
        });
        Thread.sleep(100);
        Future<String> submit2 = pool.submit(() -> {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(date);
            System.out.println(format + "====" + Thread.currentThread().getName() + "开始啦!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Date date1 = new Date();
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format1 = simpleDateFormat.format(date1);
            System.out.println(format1 + "===" + Thread.currentThread().getName() + "结束啦!");
            return "456";
        });
        Thread.sleep(100);
        Future<String> submit3 = pool.submit(() -> {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(date);
            System.out.println(format + "====" + Thread.currentThread().getName() + "开始啦!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Date date1 = new Date();
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format1 = simpleDateFormat.format(date1);
            System.out.println(format1 + "===" + Thread.currentThread().getName() + "结束啦!");
            return "789";
        });

        System.out.println("=====================================================");
        try {
            String s1 = submit1.get();
            String s2 = submit2.get();
            String s3 = submit3.get();
            System.out.println(s3);
            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("全部结束啦!");


    }
}
