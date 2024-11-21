package com.prep.interview.queues;

import java.util.NoSuchElementException;

public class Queue {

    QueueNode front;
    QueueNode back;

    //add item to back of the queue
    public void add(int value) {
        QueueNode newQueueNode = new QueueNode(value);

        if (isEmpty()) {
            front = newQueueNode;
            back = newQueueNode;
            return;
        }

        back.next = newQueueNode;
        back = newQueueNode;
    }

    //remove item in front of the queue
    public int remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int frontValue = front.value;
        front = front.next;

        if (front == null) {
            back = null;
        }
        return frontValue;
    }

    //return front of the queue
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return front.value;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void printQueue() {
        QueueNode current = front;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }

}
