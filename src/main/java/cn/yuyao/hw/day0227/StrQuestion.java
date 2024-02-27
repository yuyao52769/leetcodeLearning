package cn.yuyao.hw.day0227;

import java.util.*;

public class StrQuestion {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        test14(in);
    }

    /*
    HJ1 字符串最后一个单词的长度
    描述
        计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
        输入描述：
        输入一行，代表要计算的字符串，非空，长度小于5000。
        输出描述：
        输出一个整数，表示输入字符串最后一个单词的长度。
        输入：hello nowcoder
        输出：8
        说明：最后一个单词为nowcoder，长度为8
     */
    public static int test1(String str) {
        String[] split = str.split(" ");
        String s = split[split.length - 1];
        return s.length();
    }

    /**
     * HJ2 计算某字符出现次数
     * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
     * 输入描述：
     * 第一行输入一个由字母、数字和空格组成的字符串，第二行输入一个字符（保证该字符不为空格）。
     * 输出描述：
     * 输出输入字符串中含有该字符的个数。（不区分大小写字母）
     * 输入：ABCabc
     * A
     * 输出：2
     */
    public static int test2(String str, String target) {
        int res = 0;
        char targetChar = target.toLowerCase().charAt(0);
        char[] strCharArr = str.toLowerCase().toCharArray();
        for (char c : strCharArr) {
            if (c == targetChar) {
                res++;
            }
        }
        return res;
    }

    /**
     * 明明生成了
     * �
     * N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
     * 第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。
     */
    public static void test3() {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        Set<Integer> pool = new HashSet<>();
        for (int i = 0; i < size; i++) {
            pool.add(in.nextInt());
        }
        Arrays.stream(pool.toArray()).sorted().forEach(System.out::println);
    }

    /**
     * HJ4 字符串分隔
     * 输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
     * 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     * 连续输入字符串(每个字符串长度小于等于100)
     * 依次输出所有分割后的长度为8的新字符串
     * 输入：abc
     * 输出：abc00000
     */
    public static void test4(String str) {
        int length = str.length();
        int size = length / 8;
        int last = length % 8;
        for (int i = 0; i <= size; i++) {
            if (i != size) {
                System.out.println(str.substring(i * 8, i * 8 + 8));
            } else {
                if (last == 0) break;
                String lastStr = str.substring(size * 8);
                for (int j = 1; j <= (8-last); j++) {
                    lastStr += "0";
                }
                System.out.println(lastStr);
            }

        }
    }

    /**
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
     * 输入一个十六进制的数值字符串。
     * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
     * 输入：0xAA
     * 输出：170
     * @return
     */
    public static String test5(String str) {
         str = str.replaceFirst("0x", "");
         int num = 0;
         for (int i = (str.length() - 1); i >= 0; i--) {
             int value = Character.getNumericValue(str.charAt(i));
             num += value * (Math.pow(16,str.length() - i - 1));
         }
         return String.valueOf(num);
    }

    /**
     * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
     * 输入：180
     * 输出：2 2 3 3 5
     */
    public static void test6(long num) {
       long max = (long) Math.sqrt(num);
       for (int i = 2; i <= max; i++) {
           while (num % i == 0) {
               System.out.print(i + " ");
               num /= i;
           }
       }
        System.out.println(num == 1 ? "": num+" ");
    }

    /**
     * HJ7 取近似值
     * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于 0.5 ,向上取整；小于 0.5 ，则向下取整。
     * 数据范围：保证输入的数字在 32 位浮点数范围内
     * 输入一个正浮点数值
     * 输出该数值的近似整数值
     *
     * 输入：5.5
     * 输出：6
     * 说明：0.5>=0.5，所以5.5需要向上取整为6
     *
     * 输入：2.499
     * 输出：2
     * 说明：0.499<0.5，2.499向下取整为2
     */
    public static void test7(String num) {
        String[] split = num.split("\\.");
        String first = split[0];
        Integer res = Integer.valueOf(first);
        String last = split[1];
        int value = Character.getNumericValue(last.charAt(0));
        if (value >= 5) res++;
        System.out.println(res);
    }

    /**
     * HJ8 合并表记录
     * 数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
     * 先输入键值对的个数n（1 <= n <= 500）
     * 接下来n行每行输入成对的index和value值，以空格隔开
     * 输出合并后的键值对（多行）
     * 4
     * 0 1
     * 0 2
     * 1 2
     * 3 4
     */
    public static void test8(Scanner scanner) {
        int size = scanner.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int index = scanner.nextInt();
            int value = scanner.nextInt();
            if (map.containsKey(index)) {
                map.put(index, value + map.get(index));
            } else {
                map.put(index, value);
            }
        }
        map.entrySet().stream().sorted((t1, t2) -> t1.getKey() - t2.getKey()).forEach(entry -> {
            System.out.print(entry.getKey() + " ");
            System.out.println(entry.getValue());
        });
    }

    /**
     * HJ9 提取不重复的整数
     * 输入一个 int 型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
     * 保证输入的整数最后一位不是 0 。
     *
     * 输入描述：
     * 输入一个int型整数
     *
     * 输出描述：
     * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
     *
     */
    public static String test9(int source) {
        String str = String.valueOf(source);
        String res = "";
        Set<Integer> pool = new HashSet<>();
        for (int i = str.length() - 1; i >= 0; i--) {
            int value = Character.getNumericValue(str.charAt(i)) ;
            if (!pool.contains(value)) {
                res += value;
                pool.add(value);
            }
        }
        return res;
    }

    /**
     * HJ10 字符个数统计
     * 编写一个函数，计算字符串中含有的不同字符的个数。字符在 ASCII 码范围内( 0~127 ，包括 0 和 127 )，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
     * 例如，对于字符串 abaca 而言，有 a、b、c 三种不同的字符，因此输出 3 。
     * 输入描述：
     * 输入一行没有空格的字符串。
     *
     * 输出描述：
     * 输出 输入字符串 中范围在(0~127，包括0和127)字符的种数。
     */
    public static void test10(String str) {
        Set<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (!set.contains(c)) set.add(c);
        }
        System.out.println(set.size());
    }

    /**
     * HJ11 数字颠倒
     * 输入一个整数，将这个整数以字符串的形式逆序输出
     * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
     * 输入描述：
     * 输入一个int整数
     *
     * 输出描述：
     * 将这个整数以字符串的形式逆序输出
     */
    public static void test11(int num) {
        String s = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        System.out.println(sb.toString());
    }

    /**
     * HJ12 字符串反转
     * 接受一个只包含小写字母的字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
     * 输入一行，为一个只包含小写字母的字符串。
     *
     * 输出描述：
     * 输出该字符串反转后的字符串。
     */
    public static void test12(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        System.out.println(sb.toString());
    }

    /**
     * HJ13 句子逆序
     * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
     * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
     *
     * 输入描述：
     * 输入一个英文语句，每个单词用空格隔开。保证输入只包含空格和字母。
     *
     * 输出描述：
     * 得到逆序的句子
     */
    public static void test13(String s) {
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            sb.append(split[i] + " ");
        }
        System.out.println(sb.toString());
    }

    /**
     * HJ14 字符串排序
     * 给定 n 个字符串，请对 n 个字符串按照字典序排列。
     * 输入描述：
     * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
     * 输出描述：
     * 数据输出n行，输出结果为按照字典序排列的字符串。
     * 输入9
     * cap
     * to
     * cat
     * card
     * two
     * too
     * up
     *输出 boat
     * boot
     * boat
     * boot
     * cap
     * card
     * cat
     * to
     * too
     * two
     * up
     */
    public static void test14(Scanner in) {
        int size =Integer.valueOf(in.nextLine());
        List<String> list = new ArrayList<>();
        int i = 0;
        while (i < size) {
            String str = in.nextLine();
            list.add(str);
            i++;
        }
        list.stream().sorted().forEach(System.out::println);
    }

    /**
     * HJ15 求int型正整数在内存中存储时1的个数
     * 输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。
     * 数据范围：保证在 32 位整型数字范围内
     * 输入描述：
     *  输入一个整数（int类型）
     *
     * 输出描述：
     *  这个数转换成2进制后，输出1的个数
     */
    public static void test15(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        System.out.println(count);
    }
}
