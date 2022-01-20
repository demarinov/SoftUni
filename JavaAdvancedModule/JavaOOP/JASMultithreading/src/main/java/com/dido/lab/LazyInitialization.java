package com.dido.lab;

/*
    Problem with initializing objects in multithreaded environment
    In this case created objects my differ which spoils the correctness of
    getInstance method, to create object only once if it is null.
    Due to race condition multiple objects may be created.

 */

@NotThreadSafe
public class LazyInitialization {

    public static void main(String[] args) {

        LazyInitialization lazyInitialization = new LazyInitialization();

        Thread t1 = new Thread(() ->
                System.out.println(Thread.currentThread().getName()+
                        " "+lazyInitialization.getInstance()));

        Thread t2 = new Thread(() ->
                System.out.println(Thread.currentThread().getName()+
                        " "+lazyInitialization.getInstance()));
        Thread t3 = new Thread(() ->
                System.out.println(Thread.currentThread().getName()+
                        " "+lazyInitialization.getInstance()));

        t1.start();
        t2.start();
        t3.start();
    }

    private SomeObject object;

    public SomeObject getInstance() {
        if (object == null) {
            object = new SomeObject();
        }

        return object;
    }

    static class SomeObject {

    }
}
