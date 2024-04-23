package cn.yuyao.zhaoShang.day0405;

class Solution {

    //private static final Solution instance = new Solution();

    public static Solution getInstance() {
        return InnerClass.instance;
    }

    public static class InnerClass {
        private static final Solution instance = new Solution();
    }
}
