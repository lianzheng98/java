/**
 * @(#)TestFunc.java, 8月 13, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lianzhengbj
 */
public abstract class TestFunc {


    public static class A implements Runnable {

        @Override
        public void run() {

        }
    }

    ;

    public static class B implements Callable {


        @Override
        public Object call() throws Exception {
            return null;
        }
    }

    ;
    //思路：由于t2和t3一开始是park(阻塞)的，所以一定会先输出1，然后逐一唤醒对应线程
    static Thread t11 = null, t22 = null, t33 = null;

    public static void main(String[] args) {
        String s = "1.1.1";
        String[] split = s.split(".");
        int i = split.length;
        ArrayList<String> lists = new ArrayList<String>();


        int[] data = new int[5];
        int n = 3;
        int m = 3;

        int[][] ghjhg = new int[n][m];
        for (String ss : split) {

        }
        t11 = new Thread(() -> {
            System.out.println(1);
            LockSupport.unpark(t22);
        });

        t22 = new Thread(() -> {
            LockSupport.park();
            System.out.println(2);
            LockSupport.unpark(t33);
        });

        t33 = new Thread(() -> {
            LockSupport.park();
            System.out.println(3);
        });

        t11.start();
        t22.start();
        t33.start();
    }

//    public static void main(String[] args) {
//
//
//        A a = new A();
//        B b = new B();
//
//        Thread thread = new Thread(a);
//        FutureTask futureTask = new FutureTask<>(b);
//        Thread thread1 = new Thread(futureTask);
////        Lock lock = new ReentrantLock();
////        lock.lock();
////        try{
////
////        }catch(Exception e ){
////
////        }finally {
////
////            lock.unlock();
////        }
////        System.out.println("123");
////        String s = "123";
////        String substring = s.substring(0, 2);
////
////        System.out.println(substring);
//    }

    public void dd() {
        Map<Integer, Integer> m = new HashMap<>();


        for (Map.Entry<Integer, Integer> integerIntegerEntry : m.entrySet()) {

        }

        for (Integer it : m.keySet()) {

        }

        Iterator<Map.Entry<Integer, Integer>> iterator = m.entrySet().iterator();
        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> next = (Map.Entry<Integer, Integer>) it.next();
        }
        m.entrySet().stream().forEach(entry -> {
        });


    }

    private static class asd implements Runnable {
        @Override
        public void run() {

        }
    }

    private static class bbb implements Callable<String> {
        @Override
        public String call() {
            return "";
        }

    }

}