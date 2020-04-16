package com.sdzyc.arithmetic.interview;

import java.util.Stack;

/** 最小栈 pop，push，getMin
 * MinStack class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/16 15:52
 */
public class MinStack {

    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int element) {
        mainStack.push(element);
        // 如果辅助栈为空，或者新元素小于或等于辅助栈栈顶，将新元素压入辅助栈中
        if(minStack.empty() || element <= minStack.peek()) {
            minStack.push(element);
        }
    }

    /**
     * 出栈操作
     * @return
     */
    public Integer pop() {
        // 如果出栈元素与辅助栈栈顶元素相等，辅助栈出栈
        if(mainStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        return mainStack.pop();
    }

    /**
     * 获取最小元素
     * @return
     * @throws Exception
     */
    public int getMin() throws Exception {
        if(mainStack.empty()) {
            throw new Exception("stack is empty");
        }
        // 获取栈顶元素
        return minStack.peek();
    }

    public static void main(String[] args) throws Exception {
        MinStack stack = new MinStack();
        stack.push(4);
        stack.push(9);
        stack.push(7);
        stack.push(3);
        stack.push(8);
        stack.push(5);

        System.out.println(stack.getMin());

        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
    }
}
