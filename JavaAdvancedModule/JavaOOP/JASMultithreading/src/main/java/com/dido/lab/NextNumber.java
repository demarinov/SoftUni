package com.dido.lab;


/*
    The consistency/unsafe sequence problem of data - race condition
    t1 and t2 may get the same next number even though the number should be incremented
    and the threads receive different number. Example: t1 -> 0, t2 -> 0
    Synchronization issue -> getNext should return unique value...
 */
@NotThreadSafe
public class NextNumber {

    public static void main(String[] args) {
        NextNumber nextNumber = new NextNumber();

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

    public NextNumber() {
        this.number = 0;
    }

    public int getNextNumber() {
        return number++;
    }
}
