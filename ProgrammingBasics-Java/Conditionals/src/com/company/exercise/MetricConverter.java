package com.company.exercise;

import java.util.Scanner;

public class MetricConverter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double n = Double.parseDouble(sc.nextLine());
        String inMetric = sc.nextLine();
        String outMetric = sc.nextLine();
        double newNumMM = 0.0d;

        if (inMetric.equals("m")) {
            newNumMM = n * 1000;
        } else if (inMetric.equals("cm")) {
            newNumMM = n * 10;
        } else {
            newNumMM = n;
        }

        double numInOutMetric = 0.0d;

        if (outMetric.equals("m")) {
            numInOutMetric = newNumMM / 1000d;
        } else if (outMetric.equals("cm")) {
            numInOutMetric = newNumMM / 10d;
        } else {
            numInOutMetric = newNumMM;
        }

        System.out.printf("%.3f",numInOutMetric);
    }
}
