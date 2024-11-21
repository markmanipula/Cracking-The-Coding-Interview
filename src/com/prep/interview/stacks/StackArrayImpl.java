package com.prep.interview.stacks;

import java.util.EmptyStackException;

public class StackArrayImpl {

    int capacity = 100;
    int[] array = new int[capacity];
    int index = 0;

    public void push(int value) {

        if (isFull()) {
            int[] newArray = new int[capacity * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            capacity *= 2;
        }
        array[index] = value;
        index++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        int value = array[index - 1];
        array[index - 1] = 0;
        index--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[index - 1];
    }

    public boolean isFull() {
        return index == array.length;
    }

    public boolean isEmpty() {
        return index == 0;
    }
}
