package com.prep.interview.linkedlists;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {

    private LinkedListNode front;

    public LinkedList() {}

    public LinkedListNode rotateList(int k) {
        LinkedListNode temp = new LinkedListNode();
        temp.next = front;

        int size = 0;
        LinkedListNode runner = front;
        while (runner != null) {
            runner = runner.next;
            size++;
        }

        if (k > size) k = k % size;
        if (k % size == 0) return front;

        LinkedListNode fast = front;
        LinkedListNode slow = front;
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

    public LinkedList (LinkedListNode front) {
        this.front = front;
    }

    public LinkedListNode loopDetection() {

        if (front == null) {
            return null;
        }

        LinkedListNode slow = front;
        LinkedListNode fast = front;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                LinkedListNode runner = front;
                while (runner != slow) {
                    runner = runner.next;
                    slow = slow.next;
                }
                return runner;
            }
        }
        return null;
    }

    public LinkedListNode intersection(LinkedListNode linkedListNode1, LinkedListNode linkedListNode2) {
        Set<LinkedListNode> set = new HashSet<>();
        LinkedListNode linkedListNode1Runner = linkedListNode1;
        while (linkedListNode1Runner != null) {
            set.add(linkedListNode1Runner);
            linkedListNode1Runner = linkedListNode1Runner.next;
        }

        LinkedListNode linkedListNode2Runner = linkedListNode2;
        while (linkedListNode2Runner != null) {
            if (set.contains(linkedListNode2Runner)) {
                return linkedListNode2Runner;
            }
            linkedListNode2Runner = linkedListNode2Runner.next;
        }

        return null;
    }

    public LinkedListNode partition(int n) {
        if (front == null) {
            return null;
        } else if (front.next == null) {
            return front;
        }

        LinkedListNode smallerHead = new LinkedListNode(0);
        LinkedListNode biggerHead = new LinkedListNode(0);
        LinkedListNode smaller = smallerHead;
        LinkedListNode bigger = biggerHead;
        LinkedListNode current = front;

        while (current != null) {
            if (current.val >= n) {
                bigger.next = new LinkedListNode(current.val);
                bigger = bigger.next;
            } else {
                smaller.next = new LinkedListNode(current.val);
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

        LinkedListNode fast = front;
        LinkedListNode slow = front;

        //get mid point
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        LinkedListNode secondHalf = slow.next;

        //cut the list
        slow.next = null;

        LinkedListNode current = secondHalf;
        LinkedListNode next;
        LinkedListNode previous = null;

        //reverse the secondHalf
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        //compare
        secondHalf = previous;
        LinkedListNode runner = front;
        while (secondHalf != null) {
            if (secondHalf.val != runner.val) {
                return false;
            }
            secondHalf = secondHalf.next;
            runner = runner.next;
        }

        return true;
    }

    public void deleteMiddleNode(LinkedListNode linkedListNode) {

        if (linkedListNode == null || linkedListNode.next == null) {
            return;
        }

        linkedListNode.val = linkedListNode.next.val;
        linkedListNode.next = linkedListNode.next.next;

    }

    public LinkedListNode findNode(int val) {
        LinkedListNode current = front;

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

        LinkedListNode runner1 = front;
        for (int i = 0; i < n; i++) {
            if (runner1 == null) {
                return -1;
            }
            runner1 = runner1.next;
        }

        LinkedListNode runner2 = front;
        while (runner1 != null) {
            runner1 = runner1.next;
            runner2 = runner2.next;
        }

        return runner2.val;

    }

    public LinkedListNode removeDuplicatesInUnsortedLinedList_3() {

        if (front == null) {
            return null;
        }

        Set<Integer> set = new HashSet<>();
        LinkedListNode current = front;
        LinkedListNode runner = null;

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

    public LinkedListNode removeDuplicatesInUnsortedLinedList_2() {
        if (front == null) {
            return null;
        }

        LinkedListNode pointer_1 = front;
        LinkedListNode pointer_2 = front;

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

    public LinkedListNode removeDuplicatesInUnsortedLinedList() {
        if (front == null) {
            return null;
        }

        LinkedListNode current = front;
        Set<Integer> set = new HashSet<>();
        while (current != null) {
            set.add(current.val);
            current = current.next;
        }

        LinkedListNode output = null;
        LinkedListNode outputTail = null;

        for (Integer val : set) {
            if (output == null) {
                output = new LinkedListNode(val);
                outputTail = output;
            } else {
                outputTail.next = new LinkedListNode(val);
                outputTail = outputTail.next;
            }
        }
        return output;
    }

    public LinkedListNode removeDuplicatesInSortedLinkedList() {
        if (front == null) {
            return null;
        }

        LinkedListNode newCurrent = front;
        while (newCurrent.next != null) {
            if (newCurrent.val == newCurrent.next.val) {
                newCurrent.next = newCurrent.next.next;
            } else {
                newCurrent = newCurrent.next;
            }
        }
        return front;
    }

    public LinkedListNode weave() {
        LinkedListNode faster = front;
        LinkedListNode slower = front;

        while (faster.next != null && faster.next.next != null) {
            faster = faster.next.next;
            slower = slower.next;
        }

        LinkedListNode firstHalf = front;
        LinkedListNode secondHalf = slower.next;
        slower.next = null;

        while (secondHalf != null) {
            LinkedListNode firstIndex = firstHalf.next;
            LinkedListNode secondIndex = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = firstIndex;

            firstHalf = firstIndex;
            secondHalf = secondIndex;

        }
        return front;
    }

    public LinkedListNode deleteAllOccurrence(int val) {
        if (front == null) {
            return null;
        }

        while (front != null && front.val == val) {
            front = front.next;
        }

        LinkedListNode current = front;
        while (current != null && current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return front;
    }

    public LinkedListNode deleteFirstOccurrence(int val) {
        if (front == null) {
            return null;
        }

        LinkedListNode current = front;
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
            front = new LinkedListNode(val);
            return;
        }
        LinkedListNode current = front;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new LinkedListNode(val);
    }

    public void printLinkedList() {

        if (front == null) {
            System.out.println("Linked list is null.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        LinkedListNode current = front;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        System.out.println(sb);
    }

    public void printNode(LinkedListNode head) {
        LinkedListNode current = head;

        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

}
