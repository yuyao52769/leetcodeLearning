package cn.yuyao.leetcode.day0313;

import java.util.Stack;

/**
 * 栈
 */
public class StackDemo {

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹
     * 出顺序。假设压入栈的所有数字均不相等。
     * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一
     * 个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     */
    public boolean test1(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i : pushed) {
            stack.push(i);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                index++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
