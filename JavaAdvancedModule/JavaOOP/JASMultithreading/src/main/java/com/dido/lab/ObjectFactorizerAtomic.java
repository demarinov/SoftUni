package com.dido.lab;

import java.util.concurrent.atomic.AtomicInteger;

/*
    Present Atomic class to make the incrementing op atomic and prevent
    race condition.
 */
@ThreadSafe
public class ObjectFactorizerAtomic {

    public static void main(String[] args) {
        ObjectFactorizerAtomic objectFactorizer = new ObjectFactorizerAtomic();

        Thread t1 = new Thread(() -> objectFactorizer.service(Thread.currentThread().getName()));
        Thread t2 = new Thread(() -> objectFactorizer.service(Thread.currentThread().getName()));
        Thread t3 = new Thread(() -> objectFactorizer.service(Thread.currentThread().getName()));
        Thread t4 = new Thread(() -> objectFactorizer.service(Thread.currentThread().getName()));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    private AtomicInteger count = new AtomicInteger(0);

    public int getCount(){return count.get();}

    public void service(String threadName) {
//        System.out.println(threadName +" object factorizer service counting:");
        System.out.println(threadName+" "+count.incrementAndGet());
    }
}
