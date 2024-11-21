package com.prep.interview.stacks;

import java.util.EmptyStackException;

public class ThreeInOne {

    int numberOfStacks = 3;
    int stackCapacity;
    int[] values;
    int [] sizes;

    public ThreeInOne(int stackCapacity) {
        this.stackCapacity = stackCapacity;
        values = new int[stackCapacity * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    public void pushToStack(int stackIndex, int value) throws Exception {

        if (isStackFull(stackIndex)) {
            throw new Exception("Stack is full");
        }

        sizes[stackIndex]++;

        int indexForStack = getTopIndexForStack(stackIndex);
        values[indexForStack] = value;
    }

    public int peekToStack(int stackIndex) {
        if (isStackEmpty(stackIndex)) {
            throw new EmptyStackException();
        }
        return values[getTopIndexForStack(stackIndex)];
    }

    public int popToStack(int stackIndex) {
        if (isStackEmpty(stackIndex)) {
            throw new EmptyStackException();
        }

        int topIndex = getTopIndexForStack(stackIndex);
        int poppedValue = values[topIndex];
        values[topIndex] = 0;
        sizes[stackIndex]--;
        return poppedValue;
    }

    public boolean isStackEmpty(int stackIndex) {
        return sizes[stackIndex] == 0;
    }

    public boolean isStackFull(int stackIndex) {
        return sizes[stackIndex] == stackCapacity;
    }

    public int getTopIndexForStack(int stackIndex) {
        int offset = stackIndex * stackCapacity;
        int size = sizes[stackIndex];
        return offset + size - 1;
    }
}
