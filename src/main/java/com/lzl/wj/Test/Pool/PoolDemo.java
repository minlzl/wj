package com.lzl.wj.Test.Pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolDemo {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 4;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 1l, TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_CAPACITY), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            Runnable worker = new MyRunnable("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()){
//            System.out.println("wait");
        }
        System.out.println("finished all threads");
    }
}
