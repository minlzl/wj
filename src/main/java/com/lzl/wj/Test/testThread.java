package com.lzl.wj.Test;

class MyThread implements Runnable {

    private static int ticket = 30;
    @Override
    public void run() {
        while(ticket>0) {
            System.out.println("票: "+Thread.currentThread().getName()+": "+ticket--);
        }
    }
}
public class testThread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        new Thread(myThread, "hello1").start();
        new Thread(myThread, "hello2").start();
        new Thread(myThread, "hello3").start();

    }
}
