package cn.yuyao.hw.day0304;

import java.util.*;
import java.util.stream.Collectors;

public class Day0304 {

    public static void main(String[] args) {

    }

    /**
     * 找座位
     * 在一个大型体育场内举办了一场大型活动，由于疫情防控的需要，要求每位观众的必须间隔至少一个空位才允许落座。现在给出一排观众座位
     * ，座位中存在已落座的观众，请计算出，在不移动现有观众座位的情况下，最多还能坐下多少名观众。
     * 一个数组，用来标识某一排座位中，每个座位是否已经坐人。0 表示该座位没有坐人，1 表示该座位已经坐人。
     * 在不移动现有观众座位的情况下，最多还能坐下多少名观众。
     */
    public int test1(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                i += 2;
            } else {
                if (i == str.length() - 1 || str.charAt(i + 1) == '0') {
                    i += 2;
                    result++;
                } else {
                    i += 3;
                }
            }
        }
        return result;
    }

    /**
     * 密码输入检测
     */
    public void test3(String password) {
        password = password.replaceAll("<", "");
        boolean checkLength = password.length() >= 8;
        boolean upperCheck = false;
        boolean lowerCheck = false;
        boolean numberCheck = false;
        boolean specialCheck = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upperCheck = true;
            }
            if (Character.isLowerCase(c)) {
                lowerCheck = true;
            }
            if (Character.isDigit(c)) {
                numberCheck = true;
            }
            if (Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                specialCheck = true;
            }
        }
        System.out.println(checkLength && upperCheck && lowerCheck && numberCheck && specialCheck);
    }

    /**
     * 最长连续子串
     */
    public int test4(String str) {
        int length = str.length();
        int left = 0;
        int right = 0;
        int max = 0;
        int point = 0;
        boolean hasLetter = false;
        while (right < length) {
            if (isLetter(str.charAt(right))) {
                if (!hasLetter) {
                    point = right;
                    hasLetter = !hasLetter;
                } else {
                    left = point + 1;
                    point = right;
                }
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

    public boolean isLetter(Character ch) {
        return ch - 'a' >= 0 && 'z' - ch >= 0;
    }

    public void test6(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (str == null || str.length() <= 1) {
            System.out.println("-1");
            return;
        }

        for (char c : str.toCharArray()) {
            if (!((c >= '0' && c <= '9') || (c - 'a' >= 0 && 'z' - c >= 0) || (c - 'A' >= 0 && 'Z' - c >= 0))) {
                System.out.println("-1");
                return;
            }
        }
        int max = -1;
        int left = 0;
        int right = 1;
        while (right < str.length() && left < right) {
            right++;
            String childStr = str.substring(left, right);
            String replaced = childStr.replaceAll("[0-9]]", "");
            if (replaced.length() != 1) {
                System.out.println("-1");
                return;
            }
            max = Math.max(max, childStr.length());
        }
        System.out.println(max);
    }

    /**
     * 最长的指定瑕疵度的元音子串
     */
    public int test7(int k, String str) {
        int result = 0;
        int n = str.length();
        int left = 0;
        int right = 0;
        int maxCount = 0;
        char[] yuanYin = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        HashSet<Character> set = new HashSet<>();
        for (char c : yuanYin) {
            set.add(c);
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            while (!set.contains(ch) && left < str.length()) {
                left++;
            }
        }
        right = left + 1;
        if (left == n || (left == n - 1 && k != 0)) {
            return result;
        }


        while (right < n && result < k) {
            char ch = str.charAt(right);
           if (!set.contains(ch)) {
               result++;
           }
           right++;

        }
        return result;
    }

    public int test8(int k, String str) {
        String yuan = "aeiouAEIOU";
        int len = 0;
        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String sub = str.substring(i, j);
                int d = isYuan(sub, yuan);
                if (d == k) {
                    len = Math.max(len, sub.length());
                }
            }
        }
        return len;
    }

    public int isYuan(String sub, String yuan) {
        int n = 0;
        if (yuan.indexOf(sub.charAt(0)) != -1 && yuan.indexOf(sub.charAt(sub.length()- 1)) != -1) {
            for (int i = 0; i <sub.length(); i++) {
                if (yuan.indexOf(sub.charAt(i)) == -1) {
                    n++;
                }
            }
        } else {
            n = -1;
        }
        return n;
    }


    /**
     * 众数和中位数
     */
    public void test9(int[] nums) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer orDefault = map.getOrDefault(num, 0);
            orDefault++;
            map.put(num, orDefault);
        }

        Integer maxCount = map.values().stream().max(Integer::compareTo).get();
        List<Integer> newList = map.entrySet().stream().filter(entry -> entry.getValue().equals(maxCount))
                .map(Map.Entry::getKey).sorted(Integer::compareTo).collect(Collectors.toList());

        Collections.sort(newList);
        int size = newList.size();
        if ((size & 1) == 0) {
            // 偶数
            int mid = size >> 1;
            result =(newList.get(mid) + newList.get(mid - 1)) / 2;
        } else {
            // 奇数
            result = newList.get((size - 1) >> 1);
        }
        System.out.println(result);
    }

    /**
     * 出租车计费
     */
    public int test10(int input) {
        int result = 0;
        int add = 0;
        // 余数
        int a = 1;
        int b = 1;
        int count = 0;
        while (b != 0) {
            a = input % 10;
            b = input / 10;
            if (a > 4) {
                add += Math.pow(10, count);
            }
            count++;
        }
        return input - count;
    }

    /**
     * 找朋友
     */
    public int[] test11(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int max = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > max) {
                    result[i] = j;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 最长合法表达式
     */
    public int test12(String str) {
        Stack<Integer> numberStack = new Stack<>();
        Stack<Character> operateStack = new Stack<>();
        int maxLent = 0;
        for (int i = 0; i < str.length(); i++) {

        }
        return 0;
    }

    /**
     * 小明能到达的最大坐标值
     */
    public int test13(int k, int[] arr) {
        int maxLent =  0;
        int curLent = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (num == k) {
                if (num < 0) {
                    curLent = curLent + num - 1;
                } else if (num > 0){
                    curLent += num + 1;
                }
            } else {
                curLent += num;
            }
            maxLent = Math.max(maxLent, curLent);
        }
        return maxLent;
    }
}
