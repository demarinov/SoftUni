package com.dido.lab;

/*
    To avoid race condition on getNextNumber, the method is synchronized
    so that only one thread operates on the number at a time thus guarantee unique
    values
 */

@ThreadSafe
public class NextNumberSync {

    public static void main(String[] args) {
        NextNumberSync nextNumber = new NextNumberSync();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" "+nextNumber.getNextNumber());
        });
        Thread t2 = new Thread(() ->
                System.out.println(Thread.currentThread().getName()+" "+nextNumber.getNextNumber()));

        Thread t3 = new Thread(() ->
                System.out.println(Thread.currentThread().getName()+" "+nextNumber.getNextNumber()));

        t1.start();
        t2.start();
        t3.start();

    }

    private int number;

    public NextNumberSync() {
        this.number = 0;
    }

    public synchronized int getNextNumber() {
        return number++;
    }
}
