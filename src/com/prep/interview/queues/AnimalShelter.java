package com.prep.interview.queues;

import com.prep.interview.queues.models.Animal;
import com.prep.interview.queues.models.Cat;
import com.prep.interview.queues.models.Dog;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class AnimalShelter {

    LinkedList<Animal> catsQueue = new LinkedList<>();
    LinkedList<Animal> dogsQueue = new LinkedList<>();
    int order = 0;

    public void enqueue(Animal animal) {
        if (animal instanceof Cat) {
            animal.setOrder(order);
            order++;
            catsQueue.add(animal);
        } else if (animal instanceof Dog) {
            animal.setOrder(order);
            order++;
            dogsQueue.add(animal);
        } else {
            throw new NoSuchElementException("Animal is not valid");
        }
    }

    public Animal dequeueAny() {
        if (catsQueue.isEmpty() && dogsQueue.isEmpty()) {
            throw new NoSuchElementException("No animals available");
        }

        if (catsQueue.isEmpty()) {
            return dogsQueue.removeFirst();
        } else if (dogsQueue.isEmpty()) {
            return catsQueue.removeFirst();
        } else {
            int catOrder = catsQueue.getFirst().getOrder();
            int dogOrder = dogsQueue.getFirst().getOrder();
            if (catOrder < dogOrder) {
                return catsQueue.removeFirst();
            } else {
                return dogsQueue.removeFirst();
            }
        }
    }

    public Cat dequeueCat() {
        if (catsQueue.isEmpty()) {
            throw new NoSuchElementException("No cats available");
        }
        return (Cat) catsQueue.removeFirst();
    }

    public Dog dequeueDog() {
        if (dogsQueue.isEmpty()) {
            throw new NoSuchElementException("No dogs available");
        }
        return (Dog) dogsQueue.removeFirst();
    }

}
