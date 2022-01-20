package com.dido.exercise;

import java.util.Scanner;

public class Vacation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        Friday	Saturday	Sunday
//        Students	8.45	9.80	10.46
//        Business	10.90	15.60	16
//        Regular	15	20	22.50

        int groupNum = Integer.parseInt(sc.nextLine());
        String group = sc.nextLine();
        String day = sc.nextLine();

        double price = 0.0d;
        double totalPrice = 0.0d;

        if (group.equals("Students")) {

            if (day.equals("Friday")) {
                price = 8.45;
            } else if (day.equals("Saturday")) {
                price = 9.80;
            } else if (day.equals("Sunday")) {
                price = 10.46;
            }

            totalPrice = price * groupNum;
            if (groupNum >= 30) {
                // reduce by 15%
                totalPrice -= (0.15 * totalPrice);
            }
        } else if (group.equals("Business")) {
            if (day.equals("Friday")) {
                price = 10.90;
            } else if (day.equals("Saturday")) {
                price = 15.60;
            } else if (day.equals("Sunday")) {
                price = 16.0;
            }

            totalPrice = price * groupNum;
            if (groupNum >= 100) {
                // 10 for free
                totalPrice -= (price * 10);
            }
        } else if (group.equals("Regular")) {
            if (day.equals("Friday")) {
                price = 15.0;
            } else if (day.equals("Saturday")) {
                price = 20.0;
            } else if (day.equals("Sunday")) {
                price = 22.50;
            }

            totalPrice = price * groupNum;
            if (groupNum >= 10 && groupNum <= 20) {
                totalPrice -= (0.05 * totalPrice);
            }
        }

//        •	Students – if the group is bigger than or equal to 30 people you should reduce
//        the total price by 15%
//•	Business – if the group is bigger than or equal to  100 people
// 10 of them can stay for free.
//•	Regular – if the group is bigger than or equal 10 and less than or equal to 20 reduce
// the total price by 5%

        System.out.printf("Total price: %.2f%n",totalPrice);


    }
}
