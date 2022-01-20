package com.dido.lab;

import java.util.*;
import java.util.stream.Collectors;

public class Voina {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        Write a program that:
//•	Reads 20 numbers for both players, separated with " " (single space)
//•	Every player can hold only unique numbers
//        Each Round both players get the top number from their own deck. Player with the bigger number get both numbers and add it on the bottom of his own sequence
//        Game ends after 50 rounds or if any player lose all of his numbers

//        Input
//•	Numbers – Integer
//
        List<Integer> aNums = Arrays.stream(sc.nextLine().split("\\s"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        List<Integer> bNums = Arrays.stream(sc.nextLine().split("\\s"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        Set<Integer> aSet = new LinkedHashSet<>(aNums);
        Set<Integer> bSet = new LinkedHashSet<>(bNums);

        int count = 50;
        while(count > 0) {

            if (aSet.isEmpty() || bSet.isEmpty()) {

                break;
            }

            Integer firstNumber = aSet.iterator().next();
            aSet.remove(firstNumber);

            Integer secondNumber = bSet.iterator().next();
            bSet.remove(secondNumber);

            if (firstNumber > secondNumber) {
                aSet.add(firstNumber);
                aSet.add(secondNumber);
            } else if (secondNumber > firstNumber) {
                bSet.add(firstNumber);
                bSet.add(secondNumber);
            }



            count--;
        }

//•	Output must be "First Player Win!", "Second Player Win!" or "Draw!"

        if (aSet.size() > bSet.size()) {
            System.out.println("First player win!");
        } else if (bSet.size() > aSet.size()) {
            System.out.println("Second player win!");
        } else {
            System.out.println("Draw!");
        }
    }
}
