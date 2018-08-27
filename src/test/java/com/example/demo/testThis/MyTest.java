package com.example.demo.testThis;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author DongDexuan
 * @date 2018/7/20 14:38
 * @desc
 */
public class MyTest {

    @Test
    @Transactional
    public void test() throws InterruptedException {
        Children dexuna = new Children("dexuna");
        System.out.println("---------");
        Children dong = new Children("dong");
//        AtomicStampedReference atomicStampedReference = new AtomicStampedReference();

        AtomicReference reference = new AtomicReference(dong);
        dong.setS("hahha");
        reference.compareAndSet(dong,dexuna);
        Children o = (Children) reference.get();
        System.out.println(o.getS());
        CopyOnWriteArrayList<Children> children = new CopyOnWriteArrayList<>();

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        condition.await();
        children.wait();

        LockSupport.park();

        PipedInputStream pipedInputStream = new PipedInputStream();
    }

    @Test
    public void test2() throws IOException {
        int i;
//        String str;
        while ((i = System.in.read()) != -1){
            System.out.println(i);
        }
    }
}
