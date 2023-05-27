package com.lzl.wj.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class connection {
    public static void main(String[] args) {
        Object []objects=new Object[4];
        objects[0]=Integer.valueOf(4);
        Object i = objects[0];
        Integer ii = (Integer)i;
        System.out.println(ii);
        Integer integer1 = new Integer(1);
        Integer integer2 = new Integer(1);
        System.out.println(integer1 == integer2);
        Integer integer3 = 255;
        Integer integer4 = 255;
        System.out.println(integer3 == integer4);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(4, 0.75f, true);
        linkedHashMap.put("hello", "lzl");
        linkedHashMap.put("world", "lzl");
        linkedHashMap.get("world");
        linkedHashMap.put("lzl", "lzl");
        linkedHashMap.put("lz1l", "lzl");
        linkedHashMap.put("lzl2", "lzl");
        linkedHashMap.put("lzl3", "lzl");
        linkedHashMap.put("lzl4", "lzl");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(linkedHashMap.size());
        Set<String> strings = linkedHashMap.keySet();
        Set<Map.Entry<String, String>> entries = linkedHashMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        
    }
}
