package com.prep.interview.linkedlists;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {

    private Node front;

    public LinkedList() {}

    public Node rotateList(int k) {
        Node temp = new Node();
        temp.next = front;

        int size = 0;
        Node runner = front;
        while (runner != null) {
            runner = runner.next;
            size++;
        }

        if (k > size) k = k % size;
        if (k % size == 0) return front;

        Node fast = front;
        Node slow = front;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        fast.next = front;
        front = slow.next;
        slow.next = null;


        return front;
    }

    public LinkedList (Node front) {
        this.front = front;
    }

    public Node loopDetection() {

        if (front == null) {
            return null;
        }

        Node slow = front;
        Node fast = front;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                Node runner = front;
                while (runner != slow) {
                    runner = runner.next;
                    slow = slow.next;
                }
                return runner;
            }
        }
        return null;
    }

    public Node intersection(Node node1, Node node2) {
        Set<Node> set = new HashSet<>();
        Node node1Runner = node1;
        while (node1Runner != null) {
            set.add(node1Runner);
            node1Runner = node1Runner.next;
        }

        Node node2Runner = node2;
        while (node2Runner != null) {
            if (set.contains(node2Runner)) {
                return node2Runner;
            }
            node2Runner = node2Runner.next;
        }

        return null;
    }

    public Node partition(int n) {
        if (front == null) {
            return null;
        } else if (front.next == null) {
            return front;
        }

        Node smallerHead = new Node(0);
        Node biggerHead = new Node(0);
        Node smaller = smallerHead;
        Node bigger = biggerHead;
        Node current = front;

        while (current != null) {
            if (current.val >= n) {
                bigger.next = new Node(current.val);
                bigger = bigger.next;
            } else {
                smaller.next = new Node(current.val);
                smaller = smaller.next;
            }
            current = current.next;
        }

        smaller.next = biggerHead.next;
        return smallerHead.next;

    }

    public boolean isPalindrome() {
        if (front == null) {
            return false;
        }

        Node fast = front;
        Node slow = front;

        //get mid point
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = slow.next;

        //cut the list
        slow.next = null;

        Node current = secondHalf;
        Node next;
        Node previous = null;

        //reverse the secondHalf
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        //compare
        secondHalf = previous;
        Node runner = front;
        while (secondHalf != null) {
            if (secondHalf.val != runner.val) {
                return false;
            }
            secondHalf = secondHalf.next;
            runner = runner.next;
        }

        return true;
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

    public void printNode(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

}
