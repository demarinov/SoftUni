package com.dido.exercise;

import java.util.Scanner;

public class Ages {

    public static void main(String[] args) {
        // write your code here

        Scanner sc = new Scanner(System.in);

        int ages = Integer.parseInt(sc.nextLine());

//        •	0-2 – baby;
//•	3-13 – child;
//•	14-19 – teenager;
//•	20-65 – adult;
//•	>=66 – elder;
        String person = "";

        if (ages >= 0 && ages <= 2) {
            person = "baby";
        } else if (ages >= 3 && ages <= 13) {
            person = "child";
        } else if (ages >= 14 && ages <= 19) {
            person = "teenager";
        } else if (ages >= 20 && ages <= 65) {
            person = "adult";
        } else if (ages >= 66) {
            person = "elder";
        }

        System.out.print(person);
    }
}
