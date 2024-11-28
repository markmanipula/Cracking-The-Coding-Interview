package com.prep.interview.stacks;

import java.util.Deque;
import java.util.EmptyStackException;
import java.util.LinkedList;

public class Stack {

    StackNode top;
    int min = Integer.MAX_VALUE;
    java.util.Stack<Integer> minStack = new java.util.Stack<>();

    public int evalRPN(String[] tokens) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        for (String token : tokens) {
            if (!token.equals("+") && !token.equals("-") &&
                 !token.equals("*") && !token.equals("/")) {
                stack.push(Integer.parseInt(token));
            } else {
                int value2 = stack.pop();
                int value1 = stack.pop();
                int output = switch (token) {
                    case "+" -> value1 + value2;
                    case "-" -> value1 - value2;
                    case "/" -> value1 / value2;
                    case "*" -> value1 * value2;
                    default -> 0;
                };
                stack.push(output);
            }
        }
        return stack.peek();
    }

    public boolean backspaceCompare(String s, String t) {

        Deque<Character> stackS = new LinkedList<>();
        Deque<Character> stackT = new LinkedList<>();

        pushToStack(s, stackS);
        pushToStack(t, stackT);

        if (stackS.size() != stackT.size()) return false;

        while (!stackS.isEmpty()) {
            if (stackS.pop() != stackT.pop()) return false;
        }

        return true;
    }

    private void pushToStack(String s, Deque<Character> stack) {

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (stack.isEmpty() && current == '#') {
                //do nothing
                continue;
            }

            if (current == '#') {
                stack.pop();
            } else {
                stack.push(current);
            }
        }
    }

    public void sort(java.util.Stack<Integer> stack) {
        java.util.Stack<Integer> sortedStack = new java.util.Stack<>();

        while (!stack.isEmpty()) {
            int temp = stack.pop();

            while (!sortedStack.isEmpty() && temp < sortedStack.peek()) {
                stack.push(sortedStack.pop());
            }

            sortedStack.push(temp);
        }
        while (!sortedStack.isEmpty()) {
            stack.push(sortedStack.pop());
        }

    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int topValue = top.value;
        top = top.next;
        if (topValue == min) {
            minStack.pop();
        }
        return topValue;
    }

    public int getMin() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return minStack.peek();
    }

    public void push(int value) {
        StackNode newNode = new StackNode(value);
        newNode.next = top;
        top = newNode;
        if (value < min) {
            min = value;
            minStack.push(value);
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void printStack() {
        StackNode current = top;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}
