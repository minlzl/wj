package com.lzl.wj.Test.BlockingQueue;

public class MyBlockingQueue {
    private final Object[] items;
    private int takeIndex;
    private int putIndex;
    private int count;
    public MyBlockingQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        items = new Object[capacity];
    }

    private void enqueue(Object e) {
        items[putIndex++] = e;
        if (putIndex == items.length)
            putIndex = 0;
        count++;
    }
    private Object dequeue() {
        Object e = items[takeIndex];
        items[takeIndex] = null;
        if(++takeIndex == items.length) {
            takeIndex = 0;
        }
        count--;
        return e;
    }
    public void put(Object e) throws InterruptedException {
        synchronized (this) {
            while(count == items.length) {
                wait();
            }
            enqueue(e);
            notifyAll();
        }
    }
    public Object take() throws InterruptedException {
        synchronized (this) {
            while(count == 0) {
                wait();
            }
            Object e = dequeue();
            notifyAll();
            return e;
        }
    }
}
