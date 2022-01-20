package com.dido.main;

import java.util.*;

public class MainCode {
    static Double value;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
	// write your code here


//        if (value != null) {
//            System.out.println(value);
//        }
//
////        testFibonacci();
////        testStack();
//        int[][] nums = new int[2][2];
//
//        System.out.println(nums[0][0]);
//
//        for (int i = 1; i <= 10; i++) {
//            System.out.printf("%d ",i);
//        }

//        HashTest h1 = new HashTest("1");
//        HashTest h2 = new HashTest("1");
//        String s1 = new String("2");
//        String s2 = new String("2");
//
//        HashSet<Object> hs = new HashSet<Object>();
//        hs.add(h1);
//        hs.add(h2);
//        hs.add(s1);
//        hs.add(s2);
//
//        System.out.print(hs.size());

        Thread a = new Thread(new Cruiser());
        a.start();

        System.out.println("A");
        a.join();
        System.out.println("B");

    }

    public static void testStack() {
        // multi input, space separated

        Integer[] nums = Arrays.stream(sc.nextLine().split("\\s"))
                .map(s -> Integer.parseInt(s)).toArray(Integer[]::new);

        Deque<Integer> stackNums = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            stackNums.push(nums[i]);
        }

        while(stackNums.size() > 0) {
            System.out.printf("%d ",stackNums.pop());
        }


    }

    public static void testFibonacci() {

        // single input

        List<Integer> fibonacciList = new ArrayList<>();

        int n = Integer.parseInt(sc.nextLine());

        int currentNumber = 1;
        fibonacciList.add(currentNumber);
        fibonacciList.add(currentNumber);
        for (int i = 0; i < n/2; i++) {

            int tempNumOne = fibonacciList.get(fibonacciList.size()-1);
            int tempNumTwo = fibonacciList.get(fibonacciList.size()-2);

            fibonacciList.add((tempNumOne+tempNumTwo));
        }

        fibonacciList.stream().forEach(System.out::println);

        for (int i = 0; i < n; i++) {

            if (fibonacciList.contains(i)) {
                System.out.println("Fibo hit: "+i);
            }
        }
    }

    static class HashTest {

        private String value;

        public HashTest(String value) {
            this.value = value;
        }


    }
}

class Cruiser implements Runnable {


    public void run() {
        System.out.println("C");
    }
}

