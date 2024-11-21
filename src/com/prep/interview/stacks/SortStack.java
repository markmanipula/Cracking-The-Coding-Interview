package com.prep.interview.stacks;

import java.util.EmptyStackException;

public class SortStack {

    StackNode top;
    int size;

    public void push(int key, int value) {

        StackNode newStackNode = new StackNode(key, value);

        if (isEmpty() || top.value >= newStackNode.value) {
            newStackNode.next = top;
            top = newStackNode;
            size++;
            return;
        }

        StackNode current = top;
        while (current.next != null && current.next.value < newStackNode.value) {
            current = current.next;
        }
        newStackNode.next = current.next;
        current.next = newStackNode;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size--;
        int value = top.value;
        top = top.next;
        return value;

    }

    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printSortStack() {
        StackNode current = top;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}
