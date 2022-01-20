package com.dido.lab;

import java.util.Scanner;

public class DayOfWeek {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] days = new String[] {"Invalid day!","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        int day = Integer.parseInt(sc.nextLine());

        if ((day < 0) || (day > 7)) {
            day = 0;
        }

        System.out.printf("%s", days[day]);

    }
}
