package com.dido.lab;

/*
    synchronized keyword produces mutual exclusive reentrant lock (intrinsic lock)
    if the lock was not reentrant then the below case would produce deadlock
    since the Widget object will wait for super.doSomething() to be released
    but it is held by the same widget object, thus deadlock case.
    Reentrancy guarantees that thread that has acquired lock can do it again
    due to acquisition count incremented on lock and decrement on exiting the sync block
    when the count is zero the lock is released.

 */

@ThreadSafe
public class ReentrantObjectProblem {

    public static void main(String[] args) {
        Widget widget = new LoggingWidget();

        Thread t1 = new Thread(() -> widget.doSomething());
        Thread t2 = new Thread(() -> widget.doSomething());
        Thread t3 = new Thread(() -> widget.doSomething());

        t1.start();
        t2.start();
        t3.start();
    }

    static class Widget {
        public synchronized void doSomething(){}
    }

    static class LoggingWidget extends Widget {

        @Override
        public synchronized void doSomething() {
            System.out.println("LoggingWidget doSomething()");
            super.doSomething();
        }
    }
}
