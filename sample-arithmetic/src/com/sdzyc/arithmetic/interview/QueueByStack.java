package com.sdzyc.arithmetic.interview;

import java.util.Stack;

/**
 *  用栈实现队列， 要求两个基本操作入队，出队
 * QueueByStack class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/20 14:21
 */
public class QueueByStack {

    private static Stack<Integer> inStack = new Stack<>();
    private static Stack<Integer> outStack = new Stack<>();

    public static synchronized void push(Integer ele) {
        inStack.push(ele);
    }

    public static Integer pop() {
        if (outStack.empty()){
            move();
        }

        return outStack.pop();
    }

    private static synchronized void move() {
        if(outStack.empty()) {
            while(!inStack.empty()){
                outStack.push(inStack.pop());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                push(i);
                try{
                    Thread.sleep(20);
                }catch(Exception e) {}
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(pop());
                    Thread.sleep(100);
                }catch(Exception e) {}


            }

        }).start();

        System.in.read();
    }
}
