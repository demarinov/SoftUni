package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayManipulation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s-> Integer.parseInt(s)).toArray();

        String command = sc.nextLine();

        while(!command.equalsIgnoreCase("end")) {

            String[] subCommands = command.split(" ");

            if (subCommands[0].equalsIgnoreCase("exchange")) {

                exchangeArrayElements(nums, Integer.parseInt(subCommands[1]));
            } else if (subCommands[0].equalsIgnoreCase("max")) {

                if (subCommands[1].equalsIgnoreCase("even")) {
                    int evenIdx = getMaxEvenIdx(nums);
                    if (evenIdx == -1) {
                        System.out.println("No matches");
                    } else {
                        System.out.println(evenIdx);
                    }
                } else if (subCommands[1].equalsIgnoreCase("odd")) {
                    int oddIdx = getMaxOddIdx(nums);
                    if (oddIdx == -1) {
                        System.out.println("No matches.");
                    } else {
                        System.out.println(oddIdx);
                    }
                }

            } else if (subCommands[0].equalsIgnoreCase("min")) {
                if (subCommands[1].equalsIgnoreCase("even")) {
                    int evenIdx = getMinEvenIdx(nums);
                    if (evenIdx == -1) {
                        System.out.println("No matches");
                    } else {
                        System.out.println(evenIdx);
                    }
                } else if (subCommands[1].equalsIgnoreCase("odd")) {
                    int oddIdx = getMinOddIdx(nums);
                    if (oddIdx == -1) {
                        System.out.println("No matches.");
                    } else {
                        System.out.println(oddIdx);
                    }
                }
            } else if (subCommands[0].equalsIgnoreCase("first")) {
                if (subCommands[2].equalsIgnoreCase("even")) {
                    int[] firstEven = getFirstNEvenElements(nums,Integer.parseInt(subCommands[1]));

                    printArray(firstEven);
                } else if (subCommands[2].equalsIgnoreCase("odd")) {
                    int[] firstOdd = getFirstNOddElements(nums,Integer.parseInt(subCommands[1]));

                    printArray(firstOdd);
                }

            } else if (subCommands[0].equalsIgnoreCase("last")) {
                if (subCommands[2].equalsIgnoreCase("even")) {
                    int[] lastEven = getLastNEvenElements(nums, Integer.parseInt(subCommands[1]));
                    printArray(lastEven);
                } else if (subCommands[2].equalsIgnoreCase("odd")) {
                    int[] lastOdd = getLastNOddElements(nums, Integer.parseInt(subCommands[1]));
                    printArray(lastOdd);
                }
            }


            command = sc.nextLine();
        }

        System.out.printf("[");
        for (int i = 0; i < nums.length; i++) {
            if (i != nums.length - 1) {
                System.out.printf("%d, ", nums[i]);
            } else {
                System.out.printf("%d",nums[i]);
            }
        }
        System.out.println("]");
    }

    public static void printArray(int[] arr) {

        if (arr == null) {
            return;
        }

        if (arr.length == 0) {
            System.out.println("[]");
        } else {
            System.out.printf("[");
            for (int i = 0; i < arr.length; i++) {

                if (i != arr.length - 1) {
                    System.out.printf("%d, ", arr[i]);
                } else {
                    System.out.printf("%d",arr[i]);
                }
            }
            System.out.println("]");
        }
    }

    public static int[] getLastNOddElements(int[] nums, int count) {

        int[] result = new int[count];
        int countOdd = 0;
        int[] countAllOdd = new int[nums.length];

        if (count > nums.length) {
            System.out.println("Invalid count");
            return null;
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 != 0) {
                countAllOdd[countOdd] = nums[i];
                countOdd++;
            }
        }

        if (countOdd == 0) {
            return new int[]{};
        }

        if (countOdd >= count) {
            for (int i = 0; i < count; i++) {
                result[i] = countAllOdd[countOdd - count + i];
            }
        } else {
            result = new int[countOdd];
            for (int i = 0; i < countOdd; i++) {
                result[i] = countAllOdd[i];
            }
        }

        return result;

    }

    public static int[] getLastNEvenElements(int[] nums, int count) {

        int[] result = new int[count];
        int countEven = 0;
        int[] countAllEven = new int[nums.length];

        if (count > nums.length) {
            System.out.println("Invalid count");
            return null;
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 == 0) {
                countAllEven[countEven] = nums[i];
                countEven++;
            }
        }

        if (countEven == 0) {
            return new int[]{};
        }

        if (countEven >= count) {
            for (int i = 0; i < count; i++) {
                result[i] = countAllEven[countEven - count + i];
            }
        } else {
            result = new int[countEven];
            for (int i = 0; i < countEven; i++) {
                result[i] = countAllEven[i];
            }

        }

        return result;

    }

    public static int[] getFirstNOddElements(int[] nums, int count) {

        int[] result = new int[count];
        int countOdd = 0;

        if (count > nums.length) {
            System.out.println("Invalid count");
            return null;
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 != 0) {
                result[countOdd] = nums[i];
                countOdd++;
                if (countOdd == count) {
                    break;
                }


            }
        }

        if (countOdd == 0) {
            return new int[]{};
        }

        if (countOdd < count) {
            int[] newArr = new int[countOdd];

            for (int i = 0; i < countOdd; i++) {
                newArr[i] = result[i];
            }

            result = newArr;
        }

        return result;

    }

    public static int[] getFirstNEvenElements(int[] nums, int count) {

        int[] result = new int[count];
        int countEven = 0;

        if (count > nums.length) {
            System.out.println("Invalid count");
            return null;
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 == 0) {
                result[countEven] = nums[i];
                countEven++;
                if (countEven == count) {
                    break;
                }


            }
        }

        if (countEven == 0) {
            return new int[]{};
        }

        if (countEven < count) {
            int[] newArr = new int[countEven];

            for (int i = 0; i < countEven; i++) {
                newArr[i] = result[i];
            }

            result = newArr;
        }

        return result;

    }

    public static int getMinOddIdx(int[] nums) {
        int minIdx = -1;
        int minNum = Integer.MAX_VALUE;


        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 != 0) {
                if (minNum >= nums[i]) {
                    minNum = nums[i];
                    minIdx = i;
                }
            }
        }

        return minIdx;

    }

    public static int getMaxOddIdx(int[] nums) {
        int maxIdx = -1;
        int maxNum = Integer.MIN_VALUE;


        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 != 0) {
                if (maxNum <= nums[i]) {
                    maxNum = nums[i];
                    maxIdx = i;
                }
            }
        }

        return maxIdx;

    }

    public static int getMinEvenIdx(int[] nums) {
        int minIdx = -1;
        int minNum = Integer.MAX_VALUE;


        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 == 0) {
                if (minNum >= nums[i]) {
                    minNum = nums[i];
                    minIdx = i;
                }
            }
        }

        return minIdx;

    }

    public static int getMaxEvenIdx(int[] nums) {
        int maxIdx = -1;
        int maxNum = Integer.MIN_VALUE;


        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 == 0) {
                if (maxNum <= nums[i]) {
                    maxNum = nums[i];
                    maxIdx = i;
                }
            }
        }

        return maxIdx;

    }

    public static void exchangeArrayElements(int[] nums, int index) {


        if (index < 0 || index >= nums.length) {
            System.out.println("Invalid index");
            return;
        }
//        int[] leftArray = new int[index+1];
//        int[] rightArray = new int[nums.length - index + 1];
        int[] exchangeArray = new int[nums.length];

        // 1 3 5 7 9 exc 1 -> 5 7 9 1 3
        // get array elements from the left
        for (int i = 0; i <= index; i++) {
            //leftArray[i] = nums[i];
            exchangeArray[nums.length - index - 1 + i] = nums[i];
        }

        // get array elements from the right
        for (int i = index+1; i < nums.length; i++) {
            //rightArray[i] = nums[i + index + 1];
            exchangeArray[i - index - 1] = nums[i];
        }

        // overwrite original array with exchanged elements
        for (int i = 0; i < exchangeArray.length; i++) {
            nums[i] = exchangeArray[i];
        }
    }
}
