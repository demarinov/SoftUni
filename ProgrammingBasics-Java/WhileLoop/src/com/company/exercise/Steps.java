package com.company.exercise;

import java.util.Scanner;

public class Steps {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // target -> 10 000 steps daily
        String input = "";
        int steps = 0;

        while(steps < 10000){
            input = sc.nextLine();

            if (input.equals("Going home")) {
                int moreSteps = Integer.parseInt(sc.nextLine());
                steps += moreSteps;
                break;
            }

            steps += Integer.parseInt(input);
        }

        int stepDiff = Math.abs(10000 - steps);

        if (steps >= 10000) {
            System.out.println("Goal reached! Good job!");
            System.out.println(stepDiff +
                    " steps over the goal!");
        } else {
            System.out.println(stepDiff +
                    " more steps to reach goal.");
        }
    }
}
