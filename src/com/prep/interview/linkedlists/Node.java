package com.prep.interview.linkedlists;

public class Node {

    Node next;
    int val;

    public Node (Node next, int val) {
        this.next = next;
        this.val = val;
    }

    public Node() {};

    public Node (Node next) {
        this.next = next;
    }

    public Node (int val) {
        this.val = val;
    }
}
