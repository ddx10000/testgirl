package com.example.demo.offer;

import java.util.Stack;

public class Test2 {
    private Stack<Integer> stack = new Stack<>();
    private int min = Integer.MAX_VALUE;

    public void push(int node) {
        stack.push(node);
        min = node < min ? node : min;
    }

    public void pop() {
        int pop = stack.pop();
        if (pop == min) {
            min = stack.get(0);
            for (int value : stack) {
                min = value < min ? value : min;
            }

        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }
}
