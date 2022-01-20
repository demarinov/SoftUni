package com.dido.more;

import java.util.Scanner;

public class Company {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int projectFinishHours = Integer.parseInt(sc.nextLine());
        int daysForProject = Integer.parseInt(sc.nextLine());
        int emps = Integer.parseInt(sc.nextLine());

//        През 10% от дните служителите са на обучение и не могат да работят по проекта.
        double projectHours = 8.0 * (daysForProject - (0.1 * daysForProject));

        // emps working overtime
        double overtimeHours = 2.0 * daysForProject * emps;

        double totalHours = projectHours + overtimeHours;
        int totalHoursInt = (int)Math.floor(totalHours);

        if (totalHoursInt >= projectFinishHours) {
            System.out.printf("Yes!%d hours left.",(totalHoursInt - projectFinishHours));
        } else {
            System.out.printf("Not enough time!%d hours needed.",(projectFinishHours - totalHoursInt));
        }
    }
}
