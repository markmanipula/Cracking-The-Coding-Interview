package com.prep.interview.queues;

import java.util.NoSuchElementException;

public class QueueWIthStackImpl {

    java.util.Stack<Integer> stack1;
    java.util.Stack<Integer> stack2;
    int size;

    public void push(int value) {
        stack1.push(value);
        size++;
    }

    //remove item in front of the queue
    public int pop() {
        if (empty()) {
            throw new NoSuchElementException();
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        int value = stack2.pop();
        size--;

        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

        return value;
    }

    //return front of the queue
    public int peek() {
        if (empty()) {
            throw new NoSuchElementException();
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        int value = stack2.peek();

        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

        return value;
    }


    public boolean empty() {
        return size == 0;
    }


}
