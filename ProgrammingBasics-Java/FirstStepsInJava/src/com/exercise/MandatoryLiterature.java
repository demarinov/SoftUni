package com.exercise;

import java.util.Scanner;

public class MandatoryLiterature {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int pageCount = Integer.parseInt(scan.nextLine());
        int pagesPerHour = Integer.parseInt(scan.nextLine());
        int dayCount = Integer.parseInt(scan.nextLine());


        int totalHours = pageCount / pagesPerHour;
        int hoursPerDay = totalHours / dayCount;

        System.out.println(hoursPerDay);

    }
}
