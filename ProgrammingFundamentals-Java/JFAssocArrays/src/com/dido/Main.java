package com.dido;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Map<String, String> map = new LinkedHashMap<>();
        map.put("banana","4.50");
        map.put("mango","3.50");
        map.put("kiwi","5.50");
        

        for (Map.Entry<String, String> entry: map.entrySet()) {

//            System.out.println(entry.getKey()+" "+entry.getValue());


        }

//        printAscendingOrder();
//        printSynonyms();
       // printEvenLength();
//        printLargestThree();
//        printSum();
//        printMin();
//        printMax();
//        printAverage();
        //oddOccurrences();
//        printSumsInTable();
//        printNumStairs();

//        printMaxOccurrence();
//        printSumEvenNumber();
        //printMultiplication();
        //printBitReset();
//        printBitToggle();
//        findMaxTimeFourDigits();
//        findMinTicketCost();
//        printDivNumbers();
//        findMinTicketCost();
//        printSetBits();
        printSetEvenBits();
    }


    public static void printSetEvenBits() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        int mask = 1;
        for (int i = 0; i < nums.size(); i++) {

            if (i % 2 == 0) {
                mask = mask << i;
                int num = nums.get(i) | mask;
                System.out.println(num);
            }
        }
    }
    public static void printSetBits() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        int bitToShift = Integer.parseInt(sc.nextLine());
        int mask = 1<<bitToShift;

        for (int i = 0; i < nums.size(); i++) {
            Integer num = nums.get(i);
            System.out.println((num | mask));
        }
    }

    public static void printDivNumbers() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        for (int i = 0; i < nums.size(); i++) {

            if (nums.get(i) % (i+1) == 0) {
                System.out.println(nums.get(i));
            }
        }
    }

    //Find minimum cost of tickets required to buy for traveling on known days
    // of the month (1...30).
//Three types of tickets are available : 1-day ticket valid for 1 days and costs 2 units,
// 7-days ticket valid for 7 days and costs 7 units,
//30-days ticket valid for 30 days and costs 25 units.

    //For eg: I want to travel on [1,4,6,7,28,30] days of the month i.e. 1st, 4th, 6th ...
//day of the month. How to buy tickets so that the cost is minimum.
    public static void findMinTicketCost() {

        Scanner sc = new Scanner(System.in);

        int[] dates = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        int[] tDays = {1,7,30};
        int[] tCost = {2,7,25};

        System.out.println(minCost(dates, tDays, tCost));

//        System.out.println("Min cost: "+minCost);

    }

    public static int minCost(int[] arr, int[] tDays, int[] tCost) {
        int[][] dp = new int[arr.length][tDays.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < tDays.length; j++) {

                int prevDayIndex = findPrevDayIndex(arr,i,tDays,j);
                int prevCost = prevDayIndex>=0 ? dp[prevDayIndex][tDays.length-1] : 0;
                int currCost = prevCost + tCost[j];
                if(j-1>=0){
                    currCost = Math.min(currCost, dp[i][j-1]);
                }
                dp[i][j] = currCost;
            }
        }

        return dp[arr.length-1][tDays.length-1];
    }

    private static int findPrevDayIndex(int[] arr, int i, int[] days, int j){
        int validAfterDate = arr[i] - days[j];
        if (validAfterDate<1){
            return -1;
        }
        for (int k = i-1; k >= 0; k--) {
            if (arr[k]<=validAfterDate){
                return k;
            }
        }
        return -1;
    }

    public static int findMaxMinuteForHour(int[] nums, int maxLeftHourIdx, int maxRightHourIdx) {

        int hourLeftIdx = maxLeftHourIdx;
        int hourRightIdx = maxRightHourIdx;
        int maxNum = Integer.MIN_VALUE;
        int maxNumLeftIdx = -1;
        int maxNumRightIdx = -1;

        for (int i = 0; i < nums.length; i++) {

            if (i == hourLeftIdx || i == hourRightIdx) {
                continue;
            }
            for (int j = i+1; j < nums.length; j++) {

                if (j == hourLeftIdx || j == hourRightIdx) {
                    continue;
                }

                int n1 = nums[i];
                int n2 = nums[j];

                int num = n1 * 10 + n2;

                if ((num >= 0 && num <= 59) && num > maxNum) {
                    maxNum = num;
                    maxNumLeftIdx = i;
                    maxNumRightIdx = j;
                }

                num = n2 * 10 + n1;
                if ((num >= 0 && num <= 59) && num > maxNum) {
                    maxNum = num;
                    maxNumLeftIdx = j;
                    maxNumRightIdx = i;
                }
            }
        }


        return maxNum;
    }

    public static void findMaxTimeFourDigits() {

        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        int maxNum = Integer.MIN_VALUE;
        int maxNumLeftIdx = -1;
        int maxNumRightIdx = -1;
        int[] maxTimeArr = new int[2];
        maxTimeArr[0] = -1;
        maxTimeArr[1] = -1;
        int[] maxMinArr = new int[4];

        // find max hour
        for (int i = 0; i < nums.length; i++) {

            for (int j = i+1; j < nums.length; j++) {

                int maxMin = Integer.MIN_VALUE;

                int n1 = nums[i];
                int n2 = nums[j];

                int num = n1 * 10 + n2;

                if ((num >= 0 && num <= 23) && num > maxNum) {
                    maxNum = num;
                    maxNumLeftIdx = i;
                    maxNumRightIdx = j;
                    maxMin = findMaxMinuteForHour(nums, maxNumLeftIdx, maxNumRightIdx);
                }

                num = n2 * 10 + n1;
                if ((num >= 0 && num <= 23) && num > maxNum) {
                    maxNum = num;
                    maxNumLeftIdx = j;
                    maxNumRightIdx = i;
                    maxMin = findMaxMinuteForHour(nums, maxNumLeftIdx, maxNumRightIdx);
                }

                if (maxNum != Integer.MIN_VALUE && maxMin != Integer.MIN_VALUE) {
                    maxTimeArr[0] = maxNum;
                    maxTimeArr[1] = maxMin;
                }
            }
        }

        System.out.println("Print the time: ");
        if (maxTimeArr[0] != -1) {
            System.out.printf("%02d:%02d", maxTimeArr[0], maxTimeArr[1]);
        } else {
            System.out.println("-1");
        }
    }

    public static void printBitToggle() {
        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        for (int i = 0; i < nums.size(); i++) {

            int mask = 1;
            Integer num = nums.get(i);
            Integer newNum = (mask<<i) ^ num;
            System.out.println(newNum);
        }

    }

    public static void printBitReset() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());


        for (int i = 0; i < nums.size(); i++) {

            int mask = 1;
            Integer num = nums.get(i);
            Integer newNum = ~(mask << i) & num;
            System.out.println("Reset num: "+newNum);
        }
    }

    public static void printMultiplication() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        List<Integer> multiplications =  new ArrayList<>();

        for (int i = 0; i < nums.size()-1; i+=2) {

            Integer n1 = nums.get(i);
            Integer n2 = nums.get(i+1);

            multiplications.add(n1 * n2);
        }

        multiplications.stream().sorted((n1,n2) -> n2.compareTo(n1))
                .forEach(System.out::println);
    }

    public static void printSumEvenNumber() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        int n = Integer.parseInt(sc.nextLine());

        if (!nums.isEmpty()) {
            System.out.println("Even sum numbers are: ");
        }
        for (int i = 0; i < nums.size(); i++) {

            int deltaSum = nums.get(i) + n;

            if (deltaSum % 2 == 0) {

                System.out.printf("%d%n",nums.get(i));
            }
        }
    }

    public static void printMaxOccurrence() {
        Scanner sc = new Scanner(System.in);

        List<String> words = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        Map<Character, Integer> occurrences = new LinkedHashMap<>();

        for(String word : words) {

            for (int i = 0; i < word.length(); i++) {

                char c = word.charAt(i);
                occurrences.putIfAbsent(c, 0);
                occurrences.put(c, occurrences.get(c)+1);
            }

        }

        int maxOccurrence = 0;
        char maxChar = '\0';

        // take first max occurrence if the same
        for (Map.Entry<Character, Integer> charEntry : occurrences.entrySet()) {

            Integer count = charEntry.getValue();

            if (maxOccurrence < count) {
                maxOccurrence = count;
                maxChar = charEntry.getKey();
            }
        }

        System.out.printf("%c -> %d%n",maxChar, maxOccurrence);
    }

    public static void printNumStairs() {
        Scanner sc = new Scanner(System.in);

        List<Integer> numList = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).sorted().collect(Collectors.toList());

        List<Integer> divByThreeNumList = new ArrayList<>();

        for (int i = 0; i < numList.size(); i++) {

            Integer num = numList.get(i);
            if (num % 3 == 0) {
                divByThreeNumList.add(num);
            }
        }

        for (int i = 0; i < divByThreeNumList.size(); i++) {

            // print spaces
            for (int j = 0; j < i; j++) {

                System.out.printf(" ");
            }

            System.out.println(divByThreeNumList.get(i));
        }
    }

    public static void printSumsInTable() {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        int sumEven = 0;
        int sumOdd = 0;

        for (int i = 0; i < n; i++) {

            if (i % 2 == 0) {
                sumEven += i;
            } else {
                sumOdd += i;
            }
        }

        int digitsEven = String.format("%d",sumEven).length();
        int digitsOdd = String.format("%d",sumOdd).length();
        System.out.print(" -"+"-".repeat(digitsEven)+"-");
        System.out.println(" -"+"-".repeat(digitsOdd)+"-");
        System.out.printf("| %d | %d |%n",sumEven, sumOdd);
        System.out.printf(" -"+"-".repeat(digitsEven)+"-");
        System.out.println(" -"+"-".repeat(digitsOdd)+"-");


    }

    public static void oddOccurrences() {

        Scanner sc = new Scanner(System.in);

        List<String> elements = Arrays.stream(sc.nextLine().split("\\s+")).map(s -> s.toLowerCase())
                .collect(Collectors.toList());

        Map<String, Integer> elementMap = new LinkedHashMap<>();

        for (int i = 0; i < elements.size(); i++) {

            String element = elements.get(i);
            elementMap.putIfAbsent(element, 0);
            elementMap.put(element, elementMap.get(element)+1);
        }

        List<String> finalElements = elementMap.entrySet().stream()
                .filter(e -> e.getValue() % 2 != 0)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        for (int i = 0; i < finalElements.size(); i++) {

            System.out.printf("%s", finalElements.get(i));
            if (i != finalElements.size()-1) {
                System.out.printf(", ");
            }
        }
    }

    public static void printAverage() {

        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        double average = nums.stream().mapToInt(Integer::intValue).average().getAsDouble();

        System.out.println(average);
    }

    public static void printMax() {
        Scanner sc = new Scanner(System.in);

        int max = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).max().getAsInt();

        System.out.println(max);
    }

    public static void printMin() {
        Scanner sc = new Scanner(System.in);

        int min = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).min().getAsInt();

        System.out.println(min);
    }

    public static void printSum() {
        Scanner sc = new Scanner(System.in);

        int sum = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).sum();
        System.out.println(sum);
    }

    public static void printLargestThree() {
        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).sorted((i1,i2) ->i2.compareTo(i1) ).limit(3)
                .collect(Collectors.toList());

        nums.forEach(System.out::println);
    }

    public static void printEvenLength() {

        Scanner sc = new Scanner(System.in);

        List<String> words = Arrays.stream(sc.nextLine().split("\\s+"))
                .filter(s -> s.length() % 2 == 0).collect(Collectors.toList());

        words.forEach(System.out::println);
    }

    public static void printSynonyms() {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Map<String, ArrayList<String>> words = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String word = sc.nextLine();
            String synonym = sc.nextLine();

            words.putIfAbsent(word, new ArrayList<>());
            words.get(word).add(synonym);
        }

        for (Map.Entry<String, ArrayList<String>> entry: words.entrySet()) {

            System.out.printf("%s - ", entry.getKey());

            for (int i = 0; i < entry.getValue().size(); i++) {

                System.out.printf("%s", entry.getValue().get(i));
                if (i < entry.getValue().size()-1) {
                    System.out.printf(", ");
                }
            }

            System.out.println();
        }
    }

    public static void printAscendingOrder() {

        Scanner sc = new Scanner(System.in);

        List<Double> numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Double.parseDouble(s)).collect(Collectors.toList());

        Map<Double, Integer> numMap = new TreeMap<>();

        for (int i = 0; i < numbers.size(); i++) {

            if (!numMap.containsKey(numbers.get(i))) {
                numMap.put(numbers.get(i),0);
            }

            numMap.put(numbers.get(i),numMap.get(numbers.get(i))+1);
        }

        for (Map.Entry<Double, Integer> entry:numMap.entrySet()) {

            DecimalFormat df = new DecimalFormat("#.######");
            System.out.printf("%s -> %d%n", df.format(entry.getKey()), entry.getValue());
        }
    }
}
