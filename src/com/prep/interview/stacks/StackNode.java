package com.prep.interview.stacks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class StackNode {

    int key;
    int value;
    StackNode next;

    public StackNode (int value) {
        this.value = value;
    }

    public StackNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

}
