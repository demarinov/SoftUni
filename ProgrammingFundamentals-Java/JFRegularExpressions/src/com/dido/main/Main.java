package com.dido.main;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        printLetters();
//        printEvenAsciiLetters();

//        printLastThreeSortedNumsSum();
//        printLastTwoWords();

//        printReducedElementsV1();
//        printReducedElementsV2();

//        printLettersOnly();
//        printDigitsOnly();
//        printNameAndAge();
//        printSimpleEmail();
//        printNumLessThan();

//        printMaxMountainPeakHeight();

//        printSwappedNum();
//        printReversedText();

//        printTextOnly();
//        printDigitsAndLettersOnly();

//        printTagElements();

//        printMatchNumDivTwo();

//            printMaxMatchNum();

//        printSumOfAscii();
//        printOddAscii();

//        printAsciiShiftLeftByTwo();
//        printTestEvaluations();


//        printSymbols();
//        printNumbersAndDollars();

        printShiftedNum();
    }

    public static void printShiftedNum() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "(\\d+)(<<|>>)(\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {

            Integer num = Integer.parseInt(matcher.group(1));
            String direction = matcher.group(2);
            Integer shifting = Integer.parseInt(matcher.group(3));

            System.out.println(num+direction+shifting);

            int shiftedNum = 0;
            if (direction.equalsIgnoreCase("<<")) {
                // shift left
                shiftedNum = num << shifting;
                System.out.println("Left shifted number: "+shiftedNum);
            } else if (direction.equalsIgnoreCase(">>")) {
                // shift right
                shiftedNum = num >> shifting;
                System.out.println("Right shifted number: "+shiftedNum);
            }
        }
    }

    public static void printNumbersAndDollars() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "[\\d]+\\$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {
            String match = matcher.group();
            System.out.println(match);

        }
    }

    public static void printSymbols() {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "[^\\d\\w]+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {

            String match = matcher.group();

            System.out.println(match);
        }
    }

    public static void printTestEvaluations() {

        int t = 0;


//        t = t++*3 + 2;
        int[] arr = new int[2];

        arr[t++] = 2;
        System.out.println(arr[0]);
    }

    public static void printAsciiShiftLeftByTwo() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "(.+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {

            String match = matcher.group(1);

            for (int i = 0; i < match.length(); i++) {

                System.out.println((match.charAt(i)<<2));
            }
        }
    }

    public static void printOddAscii() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "(.+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {

            String match = matcher.group(1);

            for (int i = 0; i < match.length(); i++) {

                if (i % 2 != 0) {
                    System.out.println((int)match.charAt(i));
                }
            }
        }
    }

    public static void printSumOfAscii() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "(.+)";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {

            String match = matcher.group(0);

            int sumAscii = 0;
            for (int i = 0; i < match.length(); i++) {

                sumAscii += match.charAt(i);
            }

            if (sumAscii > 0) {
                System.out.println(sumAscii);
            }
        }
    }

    public static void printMaxMatchNum() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "\\d+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);


        Integer max = Integer.MIN_VALUE;
        while(matcher.find()) {

            Integer num = Integer.parseInt(matcher.group());

            if (max < num) {
                max = num;
            }
        }

        if (max != Integer.MIN_VALUE) {
            System.out.println(max);
        }
    }

    public static void printMatchNumDivTwo() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "\\d+";


        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {

            Integer num = Integer.parseInt(matcher.group(0));

            if (num % 2 == 0) {
                System.out.println(num);
            }
        }

    }

    public static void printTagElements() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "\\<(\\w+)\\>(.+)\\<\\/\\1\\>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {

            String tag = matcher.group(1);
            String value = matcher.group(2);

            System.out.println(tag+"->"+value);
        }
    }

    public static void printDigitsAndLettersOnly() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "[A-Za-z]*[0-9]*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {

            String match = matcher.group();

            if (!match.isEmpty()) {
                System.out.println(match);
            }
        }

    }

    public static void printTextOnly() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "[A-Za-z_]+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);


        while(matcher.find()) {

            String word = matcher.group();

            System.out.println(word);
        }
    }

    public static void printReversedText() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "(\\w+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {

            StringBuilder text = new StringBuilder();
            text.append(matcher.group(1));

            System.out.println(text.reverse().toString());
        }
    }

    public static void printSwappedNum() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "\\$(\\d+)\\$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {

            char[] nums = matcher.group(1).toCharArray();

            // swap digits;
            int numLen = nums.length;
            for (int i = 0; i < numLen / 2; i++) {

                char temp = nums[i];
                nums[i] = nums[numLen - 1 - i];
                nums[numLen - 1 - i] = temp;
            }

            System.out.println(nums);
        }

    }

    public static void printMaxMountainPeakHeight() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        // @Everest-8849 @Uhuru-5895
        String regex = "@(\\w+)-(\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        Map<String, Integer> mountains = new LinkedHashMap<>();
        while (matcher.find()) {

            String mountainName = matcher.group(1);
            int mountainHeight = Integer.parseInt(matcher.group(2));

            mountains.putIfAbsent(mountainName, mountainHeight);
        }

        // sort descending by height
        mountains.entrySet().stream()
                .sorted((m1, m2) -> m2.getValue().compareTo(m2.getValue()))
                .map(m -> String.format("%s -> %d", m.getKey(), m.getValue()))
                .forEach(System.out::println);
    }


    public static void printNumLessThan() {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        String input = sc.nextLine();

        String regex = "\\s*\\|([\\-+]?\\d+)\\|\\s*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {

            int num = Integer.parseInt(matcher.group(1));

            if (num < n) {
                System.out.println(num);
            }
        }

    }

    public static void printSimpleEmail() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "(\\w+)@(\\w+[.]\\w+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {

            String name = matcher.group(1);
            String domain = matcher.group(2);

            System.out.printf("%s -> %s%n", name, domain);

        }
    }

    public static void printNameAndAge() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "(\\w+)[\\- ](\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {

            String name = matcher.group(1);
            String age = matcher.group(2);

            System.out.printf("%s -> %s%n", name, age);
        }
    }

    public static void printDigitsOnly() {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "([0-9]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {

            String digits = matcher.group(1);

            if (digits == null || digits.isEmpty()) {
                System.out.println("Null or empty!");
            } else {

                System.out.println(matcher.group(1));
            }
        }
    }

    public static void printLettersOnly() {

        Scanner sc = new Scanner(System.in);

        String sentence = sc.nextLine();

        String regex = "([A-Za-z]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sentence);

        while (matcher.find()) {

            String text = matcher.group(1);
            System.out.println(text);
        }
    }


    public static void printReducedElementsV2() {
        Scanner sc = new Scanner(System.in);

        List<String> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        String result = nums.stream().reduce("", (partialRes, s) -> partialRes + s);

        System.out.println(result);
    }

    public static void printReducedElementsV1() {
        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        int result = nums.stream().reduce(0, (partialRes, d) -> partialRes + d);

        System.out.println(result);

    }

    public static void printLastTwoWords() {

        Scanner sc = new Scanner(System.in);

        List<String> words = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        Collections.reverse(words);

        words.stream().limit(2).collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void printLastThreeSortedNumsSum() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        System.out.println(nums.stream().sorted((d1, d2) -> d2.compareTo(d1)).limit(3).mapToInt(s -> s).sum());

    }

    public static void printEvenAsciiLetters() {
        Scanner sc = new Scanner(System.in);

        String letters = sc.nextLine();

        for (int i = 0; i < letters.length(); i++) {
            char c = letters.charAt(i);
            if (c % 2 == 0) {
                System.out.println(c);
            }
        }
    }

    public static void printLetters() {

        Scanner sc = new Scanner(System.in);

        String letters = sc.nextLine();

        for (int i = 0; i < letters.length(); i++) {

            char c = letters.charAt(i);
            System.out.println(c);
        }
    }
}
