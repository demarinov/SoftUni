package com.dido.main;

import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Queue<String> queue = new ArrayDeque<>();

        queue.add("Bob");
        queue.add("Alice");
        queue.add("Jenny");

        queue.stream().forEach(System.out::println);

        System.out.println("Retrieving from queue");


        ArrayDeque<String> stack = new ArrayDeque<>();

        stack.push("Cindy");
        stack.push("Ronnie");
        stack.stream().forEach(System.out::println);

        String stackElement = stack.pop();
        System.out.println("Popping from stack");
        System.out.println(stackElement);
        System.out.println("###################");

        Queue<Integer> priority = new PriorityQueue<>();

        priority.offer(1);
        priority.offer(10);
        priority.offer(3);
        priority.offer(4);
        priority.stream().forEach(System.out::println);
        System.out.println(priority.remove());
//        printBrowserHistory();

//        printStackTheBooks();

//        printCalculation();

//        printDecimalToBinary();
//        printMatchingBrackets();
//        printHotPotato();

//        printHotPotatoV2();
//        printPrimeHotPotato();

//        printDigitsFromStack();

//        printMatrix();

//        printSumOfMatrix();
//        printMatrixProduct();

//        printMultipliedQueue();

//        printTripleMatrix();

//        printTripletMatrixSum();

//        printMatrixFromQueue();

//        printMatrixFromStack();

//        printStashedBooks();
//        printGivenSalaries();

        StringBuilder builder = new StringBuilder();
        builder.append("abc");
//        builder.delete(builder.length() - 1,2);
        System.out.println(builder.substring(0,builder.length()-3));
    }

    public static void printGivenSalaries() {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        Queue<Double> givenSalaries = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            Double salary = Double.parseDouble(sc.nextLine());

            givenSalaries.offer(salary);
        }

        for (;!givenSalaries.isEmpty();) {

            System.out.println(givenSalaries.poll());
        }
    }

    public static void printStashedBooks() {

        Scanner sc = new Scanner(System.in);

        int count = Integer.parseInt(sc.nextLine());

        Deque<String> stashedBooks = new ArrayDeque<>();
        for (int i = 0; i < count; i++) {

            String book = sc.nextLine();

            stashedBooks.push(book);
        }

        for (;!stashedBooks.isEmpty();) {

            System.out.println(stashedBooks.pop());
        }
    }

    public static void printMatrixFromStack() {

        System.out.println("Matrix from stack ...");

        int[][] matrix = new int[2][2];

        populateMatrix(matrix);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                stack.push(matrix[i][j]);
            }
        }

        while(!stack.isEmpty()) {

            System.out.println(stack.pop());
        }
    }

    public static void printMatrixFromQueue() {

        System.out.println("Matrix from queue ...");

        int[][] matrix = new int[2][2];

        populateMatrix(matrix);

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                queue.offer(matrix[i][j]);
            }
        }

        while(!queue.isEmpty()) {

            System.out.println(queue.poll());
        }
    }

    public static void printTripletMatrixSum() {

        System.out.println("Sum of triplet is coming ...");

        int[][][] triplet = new int[2][2][2];

        populateTriplet(triplet);

        int sum = 0;
        for (int i = 0; i < triplet.length; i++) {
            for (int j = 0; j < triplet[i].length; j++) {
                for (int k = 0; k < triplet[i][j].length; k++) {

                    sum += triplet[i][j][k];
                }
            }
        }

        System.out.println(sum);
    }

    public static void printTripleMatrix() {

        System.out.println("Welcome to the triplets ...");
        Scanner sc = new Scanner(System.in);

        int[][][] triplet = new int[2][2][2];

        populateTriplet(triplet);

        for (int i = 0; i < triplet.length; i++) {

            for (int j = 0; j < triplet[i].length; j++) {
                for (int k = 0; k < triplet[i][j].length; k++) {
                    System.out.println(triplet[i][j][k]);
                }
            }
        }
    }

    public static void populateTriplet(int[][][] triplet) {
        triplet[0][0][0] = 1;
        triplet[0][0][1] = 2;
        triplet[0][1][0] = 3;
        triplet[0][1][1] = 4;
        triplet[1][0][0] = 5;
        triplet[1][0][1] = 6;
        triplet[1][1][0] = 7;
        triplet[1][1][1] = 8;
    }

    public static void printMultipliedQueue() {
        Scanner sc = new Scanner(System.in);

        int[] elements = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        int x = Integer.parseInt(sc.nextLine());

        Queue<Integer> numsQueue = new ArrayDeque<>();

        for (int i = 0; i < elements.length; i++) {

            numsQueue.offer(elements[i] * x);
        }

        numsQueue.stream().forEach(System.out::println);
    }

    public static void populateMatrix(int[][] matrix) {
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[1][0] = 3;
        matrix[1][1] = 4;
    }

    public static void printMatrixProduct() {

        System.out.println("Matrix enhanced ...");
        int[][] matrix = new int[2][2];

        int product = 1;
        populateMatrix(matrix);
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                product *= matrix[i][j];
            }
        }

        System.out.println(product);
    }

    public static void printSumOfMatrix() {

        System.out.println("Matrix return ...");

        int[][] matrix = new int[2][2];

        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[1][0] = 3;
        matrix[1][1] = 4;

        int sum = 0;

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {
                sum +=matrix[i][j];
            }
        }

        System.out.println(sum);
    }

    public static void printMatrix() {

        System.out.println("Matrix reloaded ...");
        int[][] matrix = new int[2][2];

        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[1][0] = 3;
        matrix[1][1] = 4;

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println(matrix[i][j]);
            }
        }
    }

    public static void printDigitsFromStack() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "\\d";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        Deque<Integer> stack = new ArrayDeque<>();
        while(matcher.find()) {

            Integer num = Integer.parseInt(matcher.group());

            stack.push(num);
        }

        stack.stream().forEach(System.out::println);
    }

    public static boolean isPrime(Integer n) {

        if (n <= 1) {
            return false;
        }

        for (int i = 2; i <= n/2; i++) {

            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void printPrimeHotPotato() {

        Scanner sc = new Scanner(System.in);


        Queue<String> names = new ArrayDeque<>();
        Collections.addAll(names,sc.nextLine().split("\\s+"));
        Integer n = Integer.parseInt(sc.nextLine());

        int cycles = 1;
        while(names.size() > 1) {

            for (int i = 1; i < n; i++) {
                names.offer(names.poll());
            }

            if (isPrime(cycles)) {
                System.out.println("Prime "+names.peek());
            } else {
                System.out.println("Removed: "+names.poll());
            }

            cycles++;
        }

        System.out.println("Last is "+names.poll());
    }

    public static void printHotPotatoV2() {

        Scanner sc = new Scanner(System.in);

        Queue<String> names = new ArrayDeque<>();
        Collections.addAll(names,sc.nextLine().split("\\s+"));
        Integer n = Integer.parseInt(sc.nextLine());

        while(names.size() > 1) {

            for (int i = 1; i < n; i++) {
                names.offer(names.poll());
            }

            System.out.println("Removed: "+names.poll());
        }

        System.out.println(names.poll());
    }

    public static void printHotPotato() {

        Scanner sc = new Scanner(System.in);

        Queue<String> names = new ArrayDeque<>();
        Collections.addAll(names,sc.nextLine().split("\\s+"));
        Integer n = Integer.parseInt(sc.nextLine());

        int catchPotato = 1;
        while(names.size() > 1) {

            if (catchPotato == n) {
                System.out.println("Removed: "+ names.poll());
                catchPotato = 1;
                continue;
            }

            String name  = names.poll();
            names.offer(name);

            catchPotato++;
        }

        System.out.println(names.peek());
    }

    public static void printMatchingBrackets() {

        Scanner sc = new Scanner(System.in);

        String expression = sc.nextLine();

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {

            char c = expression.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int startIndex = stack.pop();

                String contents = expression.substring(startIndex, i+1);
                System.out.println(contents);
            }
        }
    }

    public static void printDecimalToBinary() {

        Scanner sc = new Scanner(System.in);

        Integer num = Integer.parseInt(sc.nextLine());

        ArrayDeque<Integer> stackConverter = new ArrayDeque<>();
        while(num > 0) {

            int rem = num % 2;
            num /=2;
            stackConverter.push(rem);
        }

        stackConverter.stream().forEach(System.out::print);
        System.out.println();
    }

    public static void printCalculation() {

        // addition and subtraction
        Scanner sc = new Scanner(System.in);

        String[] tokens = sc.nextLine().split("\\s+");

        ArrayDeque<String> stack = new ArrayDeque<>();
        Collections.addAll(stack, tokens);

        while(stack.size() > 1) {

            Integer numOne = Integer.parseInt(stack.pop());
            String op = stack.pop();
            Integer numTwo = Integer.parseInt(stack.pop());


            switch(op) {

                case "+":
                    stack.push(String.format("%s",numOne+numTwo));
                    break;
                case "-":
                    stack.push(String.format("%s",numOne-numTwo));
                    break;
                default:
                    break;
            }
        }

        System.out.println(stack.pop());
    }

    public static void printStackTheBooks() {

        Scanner sc = new Scanner(System.in);

        // 123KylieIsBack##$$NewWay!@13$%%BestOfTheBest
        String input = sc.nextLine();

        String regex = "[A-Za-z]+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        ArrayDeque<String> bookBox = new ArrayDeque<>();

        while(matcher.find()) {

            String word = matcher.group();

            bookBox.push(word);
        }

        bookBox.stream().forEach(System.out::println);
    }

    public static void printBrowserHistory() {

        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> browserStack = new ArrayDeque<>();
        String line = sc.nextLine();

        String current = "";

        while(!line.equalsIgnoreCase("home")) {

            if (line.equalsIgnoreCase("back")) {
                if (!browserStack.isEmpty()) {
                    current = browserStack.pop();
                } else {
                    System.out.println("No previous URLs");
                    line = sc.nextLine();
                    continue;
                }
            } else {
                if (!current.isEmpty()) {
                    browserStack.push(current);

                }
                current = line;
            }

            System.out.println(current);
            line = sc.nextLine();
        }
    }
}
