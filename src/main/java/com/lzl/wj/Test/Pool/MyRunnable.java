package com.lzl.wj.Test.Pool;

import java.util.Date;

public class MyRunnable implements Runnable{

    private String command;

    public MyRunnable(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + "End. Time= " + new Date());

    }

    private void processCommand() {
        try{
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MyRunnable{" +
                "command='" + command + '\'' +
                '}';
    }
}
