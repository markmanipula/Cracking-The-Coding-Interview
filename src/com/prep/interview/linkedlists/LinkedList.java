package com.prep.interview.linkedlists;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {

    private Node front;

    public LinkedList() {}
    public LinkedList (Node front) {
        this.front = front;
    }

    public void deleteMiddleNode(Node node) {

        if (node == null || node.next == null) {
            return;
        }

        node.val = node.next.val;
        node.next = node.next.next;

    }

    public Node findNode(int val) {
        Node current = front;

        while (current != null) {
            if (current.val == val) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public int kthToTheLastElement(int n) {

        if (front == null) {
            return -1;
        }

        Node runner1 = front;
        for (int i = 0; i < n; i++) {
            if (runner1 == null) {
                return -1;
            }
            runner1 = runner1.next;
        }

        Node runner2 = front;
        while (runner1 != null) {
            runner1 = runner1.next;
            runner2 = runner2.next;
        }

        return runner2.val;

    }

    public Node removeDuplicatesInUnsortedLinedList_3() {

        if (front == null) {
            return null;
        }

        Set<Integer> set = new HashSet<>();
        Node current = front;
        Node runner = null;

        while (current != null) {
            if (set.contains(current.val)) {
                runner.next = current.next;
            } else {
                set.add(current.val);
                runner = current;
            }
            current = current.next;
        }

        return front;
    }

    public Node removeDuplicatesInUnsortedLinedList_2() {
        if (front == null) {
            return null;
        }

        Node pointer_1 = front;
        Node pointer_2 = front;

        while (pointer_1 != null) {
            while (pointer_2.next != null) {
                if (pointer_1.val != pointer_2.next.val) {
                    pointer_2 = pointer_2.next;
                } else {
                    pointer_2.next = pointer_2.next.next;
                }
            }
            pointer_1 = pointer_1.next;
            pointer_2 = pointer_1;
        }
        return front;
    }

    public Node removeDuplicatesInUnsortedLinedList() {
        if (front == null) {
            return null;
        }

        Node current = front;
        Set<Integer> set = new HashSet<>();
        while (current != null) {
            set.add(current.val);
            current = current.next;
        }

        Node output = null;
        Node outputTail = null;

        for (Integer val : set) {
            if (output == null) {
                output = new Node(val);
                outputTail = output;
            } else {
                outputTail.next = new Node(val);
                outputTail = outputTail.next;
            }
        }
        return output;
    }

    public Node removeDuplicatesInSortedLinkedList() {
        if (front == null) {
            return null;
        }

        Node newCurrent = front;
        while (newCurrent.next != null) {
            if (newCurrent.val == newCurrent.next.val) {
                newCurrent.next = newCurrent.next.next;
            } else {
                newCurrent = newCurrent.next;
            }
        }
        return front;
    }

    public Node weave() {
        Node faster = front;
        Node slower = front;

        while (faster.next != null && faster.next.next != null) {
            faster = faster.next.next;
            slower = slower.next;
        }

        Node firstHalf = front;
        Node secondHalf = slower.next;
        slower.next = null;

        while (secondHalf != null) {
            Node firstIndex = firstHalf.next;
            Node secondIndex = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = firstIndex;

            firstHalf = firstIndex;
            secondHalf = secondIndex;

        }
        return front;
    }

    public Node deleteAllOccurrence(int val) {
        if (front == null) {
            return null;
        }

        while (front != null && front.val == val) {
            front = front.next;
        }

        Node current = front;
        while (current != null && current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return front;
    }

    public Node deleteFirstOccurrence(int val) {
        if (front == null) {
            return null;
        }

        Node current = front;
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
                return front;
            }
            current = current.next;
        }
        return front;
    }

    public void appendToTail(int val) {
        if (front == null) {
            front = new Node(val);
            return;
        }
        Node current = front;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(val);
    }

    public void printLinkedList() {

        if (front == null) {
            System.out.println("Linked list is null.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        Node current = front;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        System.out.println(sb);
    }

}
