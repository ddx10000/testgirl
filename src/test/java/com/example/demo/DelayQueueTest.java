package com.example.demo;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    public static void main(String[] args) {
        DelayQueue<DelayTask> queue = new DelayQueue<>();
        queue.add(new DelayTask("1", 1000L, TimeUnit.MILLISECONDS));
        queue.add(new DelayTask("4", 1000L, TimeUnit.MILLISECONDS));
        queue.add(new DelayTask("2", 2000L, TimeUnit.MILLISECONDS));
        queue.add(new DelayTask("3", 3000L, TimeUnit.MILLISECONDS));
        queue.add(new DelayTask("5", 4000L, TimeUnit.MILLISECONDS));
        queue.add(new DelayTask("6", 50000L, TimeUnit.MILLISECONDS));

        System.out.println("queue put done");

        long start = System.currentTimeMillis();
        while (!queue.isEmpty()) {
            new Thread(() -> {
                try {
                    DelayTask task = queue.take();
                    long usetime = System.currentTimeMillis() - start;
                    System.out.println(task.name + " usetime: " + usetime);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
        long usetime = System.currentTimeMillis() - start;
        System.out.println("main finished.usetime: " + usetime);
    }
}

class DelayTask implements Delayed {
    public String name;
    public TimeUnit delayTimeUnit;
    public Long executeTime;//ms

    DelayTask(String name, long delayTime, TimeUnit delayTimeUnit) {
        this.name = name;
        this.delayTimeUnit = delayTimeUnit;
        this.executeTime = System.currentTimeMillis() + delayTimeUnit.toMillis(delayTime);
    }


    @Override
    public int compareTo(Delayed o) {
        if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
            return 1;
        } else if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
            return -1;
        }
        return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

}