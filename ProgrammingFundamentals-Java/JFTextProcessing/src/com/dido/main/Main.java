package com.dido.main;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	    // write your code here

      //  printEncodedString();
//        printJoinedHalves();
//        printEncodedUpDown();
//        printDecodedUpDown(getEncodedUpDown("I go to school"));
//        printEncodingShifting();
//        printShortNumbers();
        //printDivFourNumbers();
//        printAvgNum();

//        printEachNumNTimes();
//        printMaxFiveDivTwo();
//        printFlatList();

//        printReversedMiddle();

//        printMaxNumber();

//        printMidSwappedWords();
//        printMidSwappedReversed();
       // printCountOfDigits();
//        printLargestValueDivTwo();
//        printNamesMatched();

        
    }

    public static void printNamesMatched() {

        Scanner sc = new Scanner(System.in);

        String names = sc.nextLine();
        String strPattern = "([A-Z][a-z]+) ([A-Z][a-z]+)";

        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(names);

        while(matcher.find()) {

            String firstName = matcher.group(1);
            String lastName = matcher.group(2);

            System.out.printf("FirstName: %s %nLastName: %s%n",firstName, lastName);
        }
    }

    public static void printLargestValueDivTwo() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        System.out.println(nums.stream().filter(d -> d % 2 == 0).mapToInt(d -> d)
                .max().getAsInt());
    }

    public static void printCountOfDigits() {


        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());
        int digit = Integer.parseInt(sc.nextLine()); //0 or 1

        int count = 0;

        String binaryNum = convertToBinary(n);

        System.out.println("Bin rep: "+binaryNum);
        int num = n;
        int length = 0;
        while(num > 0) {
            num /= 10;
            length++;
        }

        length = binaryNum.length();
        int mask = 1;
        for (int i = 0; i < length; i++) {

            int shiftedNum = n >> i;
            int checkDigit = mask & shiftedNum;

            if (checkDigit == digit) {
                count++;
            }
        }

        System.out.println("Count: "+count);

    }

    public static String convertToBinary(int n) {

        int num = n;
        int rem = num;

        StringBuilder res = new StringBuilder();
        while(rem>0) {

            int i = rem % 2;
            res.append(i);
            rem /= 2;

        }

        return res.reverse().toString();
    }

    public static void printMidSwappedReversed() {
        Scanner sc = new Scanner(System.in);

        List<String> words = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());
        int middleIdx = words.size() / 2;

        for (int i = words.size()-1; i >= middleIdx; i--) {
            String word  =words.get(i);
            System.out.println(word);
        }

        System.out.println("###");
        for (int i = middleIdx-1; i >=0; i--) {

            String word  =words.get(i);
            System.out.println(word);
        }
    }

    public static void printMidSwappedWords() {

        Scanner sc = new Scanner(System.in);

        List<String> words = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());
        int middleIdx = words.size() / 2;

        for (int i = middleIdx; i < words.size(); i++) {
            String word  =words.get(i);
            System.out.println(word);
        }

        for (int i = 0; i < middleIdx; i++) {

            String word  =words.get(i);
            System.out.println(word);
        }



    }

    public static void printMaxNumber() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        System.out.println(nums.stream().mapToInt(i -> i).max().getAsInt());
    }

    public static void printReversedMiddle() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        int middle = nums.size()/2;

        for (int i =nums.size()-1; i >= middle; i--) {

            Integer num = nums.get(i);
            System.out.println(num);
        }
    }

    public static void printFlatList() {

        List<List<String>> listOfLists = Arrays.asList(Arrays.asList("a"),Arrays.asList("b"));

        listOfLists.stream().flatMap(s -> s.stream()).forEach(System.out::println);

        Map<String,Map<String, String>> mapOfMaps = new LinkedHashMap();
        mapOfMaps.putIfAbsent("a",new LinkedHashMap<>());
        mapOfMaps.get("a").putIfAbsent("ac","here we go");

        mapOfMaps.putIfAbsent("b",new LinkedHashMap<>());
        mapOfMaps.get("b").putIfAbsent("bc","Again we go");

        mapOfMaps.entrySet().stream().flatMap(m -> m.getValue().entrySet().stream())
                .forEach(System.out::println);


    }

    public static void printMaxFiveDivTwo() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        for (int i = 0; i < nums.size(); i++) {

            Integer num = nums.get(i);

            if (num % 2 == 0) {

                nums.remove(i);
                i--;
            }
        }

        nums = nums.stream().sorted((n1,n2) -> n2.compareTo(n1)).limit(5)
                .collect(Collectors.toList());

        System.out.println(nums.toString().replaceAll("[\\[ ,\\]]",""));
    }

    public static void printEachNumNTimes() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        int n = Integer.parseInt(sc.nextLine());

        int count = 1;
        for (int i = 0; i < nums.size(); i++) {

            Integer num = nums.get(i);

            if (num % n == 0) {

                if (count % 2 == 0) {
                    System.out.println(num);
                }

                count++;
            }
        }
    }

    public static void printAvgNum() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        System.out.printf("%.2f%n",nums.stream().mapToInt(s -> s)
                .average().getAsDouble());
    }

    public static void printDivFourNumbers() {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {

            if (i % 4 == 0) {
                result.append(String.format("%d",i));
                result.append(",");
            }
        }

        System.out.println(result.toString().substring(0,result.length()-1));
    }

    public static void printShortNumbers() {

        Scanner sc = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        for (int i = 0; i < nums.size(); i++) {

            Integer num = nums.get(i);

            if (num < 10) {
                System.out.println(num);
            }
        }
    }

    public static void printEncodingShifting() {

        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        int shifts = Integer.parseInt(sc.nextLine());

        int mask = 1<< shifts;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            Character c = text.charAt(i);

            Character newChar = (char)(c & mask + i);
            builder.append(String.format("%c",newChar));
        }

        System.out.println(builder.toString());

        for (int i = 0; i < builder.length(); i++) {

            System.out.printf("%d %n",(int)builder.charAt(i));
        }
    }

    public static void mumboJumbo() {
        StringBuilder text = new StringBuilder("pesho>2>2>3>4afdsf");

        String t = "bundyunls ulweu";
        t = t.replace("u","");
        System.out.println(t);
        text.replace(0,2,"asr");
        System.out.println(text);

        int d3 = 1;
        int[] ar = new int[]{1,2,3,4};
        if (d3 == 0+d3++) {
            System.out.println("yes");
        }
        System.out.println(ar[++d3]);

        int x = 5;
        int y = 10;
        int z = ++x * y--;

        System.out.println(z);
    }

    public static void printDecodedUpDown(String encodedText) {

        StringBuilder decodingBuilder = new StringBuilder();

        for (int i = 0; i < encodedText.length(); i++) {
            Character c = encodedText.charAt(i);

            if (i % 2 == 0) {
                if (c-1 >= 0) {
                    decodingBuilder.append(String.format("%c", (c - 1)));
                }
            } else {
                if (c-1 < 0) {
                    decodingBuilder.append(String.format("%c",c));
                } else {
                    decodingBuilder.append(String.format("%c", (c + 1)));
                }
            }
        }

        System.out.println(decodingBuilder.toString());
    }

    public static void printEncodedUpDown() {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        System.out.println(getEncodedUpDown(text));

    }

    public static String getEncodedUpDown(String text) {
        StringBuilder encodedBuilder  = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            Character c = text.charAt(i);

            if (i % 2 == 0) {
                encodedBuilder.append(String.format("%c",(c+1)));
            } else {
                if (c-1 >= 0) {
                    encodedBuilder.append(String.format("%c", (c - 1)));
                } else {
                    encodedBuilder.append(String.format("%c",c));
                }
            }

        }

        return encodedBuilder.toString();
    }

    public static void printJoinedHalves() {

        Scanner sc = new Scanner(System.in);

        String[] data = sc.nextLine().split("\\s+");

        List<String> halves = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {

            String text = data[i];
            int len = text.length()/2;
            if (text.length() % 2 != 0) {
                len++;
            }
            halves.add(text.substring(0,len));
        }

        System.out.println(String.join("<->",halves));
    }

    public static void printEncodedString() {

        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();

        StringBuilder encodingBuilder = new StringBuilder();
        for (int i = 0; i < text.length() ; i++) {

            Character c = text.charAt(i);
            encodingBuilder.append(String.format("%c",(c+1)));
        }

        System.out.println(encodingBuilder.reverse().toString());
    }
}
