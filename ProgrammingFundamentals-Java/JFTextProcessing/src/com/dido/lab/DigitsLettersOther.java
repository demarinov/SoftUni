package com.dido.lab;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DigitsLettersOther {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        List<Character> digits = new ArrayList<>();
        List<Character> letters = new ArrayList<>();
        List<Character> other = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {

            Character c = input.charAt(i);

            if (Character.isDigit(c)) {
                digits.add(c);
            } else if (Character.isLetter(c)) {
                letters.add(c);
            } else {
                other.add(c);
            }
        }

        digits.stream().forEach(System.out::print);
        System.out.println();
        letters.stream().forEach(System.out::print);
        System.out.println();
        other.stream().forEach(System.out::print);
    }
}
