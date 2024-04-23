package cn.yuyao.hw.day0325;

public class Main {

    public static void main(String[] args) {
        String s1 = "9119";
        String s2 = "2222";
        String sum = sum(s1, s2);
        System.out.println(sum);
    }


    /**
     * 两个大数相加。
     * 1、是整数；
     * 2、两个数无限大，long都装不下；
     * 3、不能用BigInteger；
     * 4、不能用任何包装类提供的运算方法；
     * 5、两个数都是以字符串的方式提供。
     */
    public static String sum(String s1, String s2) {
        int pre = 0;
        String res = "";
        if (s1.length() > s2.length()) {
            for (int index = 1; index <= s1.length() - s2.length(); index++) {
                s2 = "0" + s2;
            }

        }

        if (s1.length() < s2.length()) {
            for (int index = 1; index <= s2.length() - s1.length(); index++) {
                s1 = "0" + s1;
            }
        }

        int i = s1.length() - 1;
        int j = s2.length() - 1;
        for (; i >=0 && j >= 0; i--, j--) {
            Integer a = Integer.valueOf(s1.charAt(i) - '0');
            Integer b = Integer.valueOf(s2.charAt(j) - '0');
            int c  = a + b + pre;
            pre = c / 10;
            res = c % 10 + res;
        }
        if (pre > 0) {
            res = "1" + res;
        }
        return res;
     }

}
