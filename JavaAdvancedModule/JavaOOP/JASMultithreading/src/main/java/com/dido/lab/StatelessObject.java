package com.dido.lab;


/*
    Stateless objects are thread-safe, no state,fields present.
 */

@ThreadSafe
public class StatelessObject {

    public static void main(String[] args) {

        StatelessObject statelessObject = new StatelessObject();

        Thread t1 = new Thread(() -> statelessObject.service(Thread.currentThread().getName()));
        Thread t2 = new Thread(() -> statelessObject.service(Thread.currentThread().getName()));

        t1.start();
        t2.start();
    }

    public void service(String threadName) {
        System.out.println(threadName+" " +"stateless object service called");
    }
}
