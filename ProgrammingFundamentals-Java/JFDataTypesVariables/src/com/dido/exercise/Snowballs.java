package com.dido.exercise;

import java.util.Scanner;

public class Snowballs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n  = Integer.parseInt(sc.nextLine());
        double maxVal = 0.0d;
        int maxs = 0;
        int maxt = 0;
        int maxq = 0;

        for (int i = 1; i <= n; i++) {

            int snow = Integer.parseInt(sc.nextLine());
            int time = Integer.parseInt(sc.nextLine());
            int quality = Integer.parseInt(sc.nextLine());

            double value = Math.pow(snow / time,quality);

            if (maxVal < value) {
                maxVal = value;
                maxs = snow;
                maxt = time;
                maxq = quality;
            }

        }

        System.out.printf("%d : %d = %.0f (%d)",maxs,maxt,maxVal,maxq);
    }
}
