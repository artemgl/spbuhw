package com.artemchernikov.g144;

/**A class describing priority queue*/
public class PriorityQueue<T> {

    private Element first;

    /**A class describing the element of priority queue*/
    private class Element {
        private T value;
        private int priority;
        private Element next;

        private Element(T value, int priority, Element next) {
            this.value = value;
            this.priority = priority;
            this.next = next;
        }
    }

    /**
     * A method receiving value and priority and adding this value to queue with this priority
     * Values are sorted in descending way by priority all the time
     * */
    public void enqueue(T value, int priority) {
        first = new Element(value, priority, first);

        Element current = first;
        Element next = current.next;
        while (next != null && priority < next.priority) {
            //swap
            next.priority += current.priority - (current.priority = next.priority);
            //swap
            T buffer = next.value;
            next.value = current.value;
            current.value = buffer;

            current = next;
            next = next.next;
        }
    }

    /**A method, returns the value with the largest priority or throws exception if the priority queue is empty*/
    public T dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception("Priority queue is underflow");
        }

        T result = first.value;
        first = first.next;
        return result;
    }

    public boolean isEmpty() {
        return first == null;
    }

}
