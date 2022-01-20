package com.company.exercise;

import java.util.Scanner;

public class WorldSwimRecord {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double worldRec = Double.parseDouble(sc.nextLine());
        double distance = Double.parseDouble(sc.nextLine());
        double timeOneM = Double.parseDouble(sc.nextLine());

        double timeDist = timeOneM * distance;

        int slowDownCount = (int) (distance / 15);

        if (slowDownCount > 0) {
            timeDist = timeDist + 12.5d * slowDownCount;
        }

        double recDiff = timeDist - worldRec;

        if (recDiff >= 0) {
            System.out.printf("%s%.2f%s","No, he failed! He was ",
                    recDiff,
                    " seconds slower.");

        } else {
            System.out.printf("%s%.2f%s"," Yes, he succeeded! The new world record is ",
                    timeDist,
                    " seconds.");
        }
    }
}
