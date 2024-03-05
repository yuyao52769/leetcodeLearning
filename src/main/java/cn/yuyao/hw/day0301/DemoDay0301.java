package cn.yuyao.hw.day0301;

public class DemoDay0301 {

    public static void main(String[] args) {

    }


    /**
     * 输入两个字符串 S 和 L，都只包含英文小写字母。S 长度<=100，L 长度<=500,000。
     * S 中的每个字符在 L 中都能找到（可以不连续），且 S 在Ｌ中字符的前后顺序与 S 中顺序要保持一致。
     * <p>（例如，S=”ace”是 L=”abcde”的一个子序列且有效字符是 a、c、e，而”aec”不是有效子序列，且有效字符只有 a、e）</p>
     */
    public boolean test1(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        int i = 0;
        int j = 0;
        while (i < sLength && j < tLength) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == sLength;
    }
}
