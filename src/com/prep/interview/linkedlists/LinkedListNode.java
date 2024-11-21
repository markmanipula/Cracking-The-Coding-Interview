package com.prep.interview.linkedlists;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LinkedListNode {

    LinkedListNode next;
    int val;

    public LinkedListNode(LinkedListNode next, int val) {
        this.next = next;
        this.val = val;
    }

    public LinkedListNode() {};

    public LinkedListNode(LinkedListNode next) {
        this.next = next;
    }

    public LinkedListNode(int val) {
        this.val = val;
    }
}
