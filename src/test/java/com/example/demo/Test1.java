package com.example.demo;

import org.junit.Test;

import javax.management.monitor.Monitor;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test1 {
    @Test
    public synchronized void test() throws InterruptedException {
//        Test1.class.wait();
//        BlockingDeque blockingDeque;
//        BoundedQueue o;
//        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(10));
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//        Lock readLock = lock.readLock();
//        Lock writeLock = lock.writeLock();
//        writeLock.lock();
//        readLock.lock();
//        writeLock.unlock();
//        writeLock.lock();
//        Lock lock2 = new ReentrantLock();
//        Condition condition = lock2.newCondition();
//        condition.await();
//        condition.signalAll();

        Calendar now = Calendar.getInstance();

        System.out.println(now);
        long value = now.getTimeInMillis();
        System.out.println(value);
//        now.setTimeInMillis(value + 2 * 60 * 60 * 1000 + 1 * 60 * 1000);
        now.setTimeInMillis(value + TimeUnit.HOURS.toMillis(2) + TimeUnit.MINUTES.toMillis(1));
        System.out.println(2 * 60 * 60 * 1000 + 1 * 60 * 1000);
        System.out.println(TimeUnit.HOURS.toMillis(2) + TimeUnit.MINUTES.toMillis(1));
        System.out.println(now);
        System.out.println(now.get(Calendar.AM_PM));
        System.out.println(now.get(Calendar.HOUR));
        System.out.println(now.get(Calendar.MINUTE));
        B b = new B();
        b.hh();
        Monitor monitor = new Monitor() {
            @Override
            public void start() {

            }

            @Override
            public void stop() {

            }
        };
    }

    private static AtomicInteger a = new AtomicInteger(0);
    @Test
    public void test2() throws InterruptedException {
        final Map<String, String> map = new Hashtable<>(10000);
        final Map<String, String> map1 = new LinkedHashMap<>(10000);
        final Map<String, String> map2 = new ConcurrentHashMap<>(10000);
        long start = System.currentTimeMillis();
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                new Thread(() -> {map.put(UUID.randomUUID().toString(), "");
                    System.out.println(Thread.currentThread().getName() + "=====" + a.addAndGet(1) + "======" + map.size());
                    }, "ftf" + i).start();

            }
        }, "ftf");
        t.start();
        t.join();
        long end = System.currentTimeMillis();
        long usetime = end - start;
        System.out.println("finished! usetime:" + usetime);
    }

    @Test
    public void test3() {
        ConcurrentLinkedDeque concurrentLinkedDeque  = new ConcurrentLinkedDeque();
    }
}


class A {

    public void hh() {
        go();
    }

    void go() {
        System.out.println("this is A go!");
    }
}

class B extends A {

    void go() {
        System.out.println("this is B go!");
    }


}
