package com.dido.lab;


/*
    Candidate for race condition since count hold 3 ops - read/write/read
    leading to not thread safe behaviour and potential race condition on count value.
 */
@NotThreadSafe
public class ObjectFactorizer {

    public static void main(String[] args) {
        ObjectFactorizer objectFactorizer = new ObjectFactorizer();

        Thread t1 = new Thread(() -> objectFactorizer.service(Thread.currentThread().getName()));
        Thread t2 = new Thread(() -> objectFactorizer.service(Thread.currentThread().getName()));
        Thread t3 = new Thread(() -> objectFactorizer.service(Thread.currentThread().getName()));
        Thread t4 = new Thread(() -> objectFactorizer.service(Thread.currentThread().getName()));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    private int count;

    public int getCount(){return count;}

    public void service(String threadName) {
//        System.out.println(threadName +" object factorizer service counting:");
        ++count;
        System.out.println(threadName+" "+count);
    }
}
