package com.dido.exercise;

import java.util.Scanner;

public class Login {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String user = sc.nextLine();
        StringBuilder expectedPass = new StringBuilder();
        String message = "";
        // reverse user to pass
        expectedPass.append(user);
        expectedPass = expectedPass.reverse();

        int count = 0;
        // check pass
        while(true) {
            String pass = sc.nextLine();

            count++;

            if (pass.equals(expectedPass.toString())) {
                message = String.format("User %s logged in.",user);
                break;
            } else {
                if (count == 4) {
                    message = String.format("User %s blocked!",user);
                    break;
                }
                message = String.format("Incorrect password. Try again.");
                System.out.println(message);
            }

        }

        System.out.print(message);
    }
}
