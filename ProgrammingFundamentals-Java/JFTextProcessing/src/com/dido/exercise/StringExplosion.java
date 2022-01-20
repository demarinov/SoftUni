package com.dido.exercise;

import java.util.Scanner;

public class StringExplosion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder input = new StringBuilder(sc.nextLine());
        boolean explosionMode = false;
        Integer remStrength = 0;

        for (int i = 0; i < input.length(); i++) {

            Character c = input.charAt(i);

            if (c == '>') {
                explosionMode = true;
                c = input.charAt(++i);
            } else {
                explosionMode = false;
            }

            if (explosionMode) {
                Integer strength = Integer.parseInt(String.format("%c",c)) + remStrength;

                int j = 0;
                remStrength = 0;
                int removalSteps = strength;
                while(removalSteps >= 0) {
                    int startIdx = i;
                    int nextIdx = startIdx+j;
                    Character nextChar = input.charAt(nextIdx);

                    if (nextIdx == input.length()-1) {
                        // terminate

                        nextChar = '>';
                        j++;


                    }

                    if (nextChar == '>' || j == strength) {
                        remStrength = removalSteps;
                        input = input.replace(startIdx,startIdx+j,"");
                        i--;
                        break;
                    }

                    j++;
                    removalSteps--;
                }

            }


        }

        System.out.println(input);
    }
}
