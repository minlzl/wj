package com.lzl.wj.Test.BlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        final MyBlockingQueue q = new MyBlockingQueue(200);
        final int threads = 10;
        final int times = 100;
        List<Thread> threadList = new ArrayList<>(threads * 2);
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < threads; ++i) {
            final int offset = i * times;
            Thread producer = new Thread(()->{
                try {
                    for (int j=0; j < times; ++j) {
                        q.put(offset +j);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threadList.add(producer);
            producer.start();
        }
        for(int i=0;i<threads;++i) {
            Thread consumer = new Thread(() -> {
                try {
                    for (int j = 0; j < times; ++j) {
                        Integer element = (Integer) q.take();
                        System.out.println(element);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            threadList.add(consumer);
            consumer.start();
        }
        for(Thread thread : threadList) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("总耗时：%.2fs", (endTime - startTime) / 1e3));
    }
}
