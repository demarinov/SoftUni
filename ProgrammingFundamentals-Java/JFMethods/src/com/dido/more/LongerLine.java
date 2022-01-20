package com.dido.more;

import java.util.Scanner;

public class LongerLine {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double x11 = Integer.parseInt(sc.nextLine());
        double y11 = Integer.parseInt(sc.nextLine());
        double x12 = Integer.parseInt(sc.nextLine());
        double y12 = Integer.parseInt(sc.nextLine());

        double x21 = Integer.parseInt(sc.nextLine());
        double y21 = Integer.parseInt(sc.nextLine());
        double x22 = Integer.parseInt(sc.nextLine());
        double y22 = Integer.parseInt(sc.nextLine());

        double[] firstLineCoordinates = new double[4];
        firstLineCoordinates[0] = x11;
        firstLineCoordinates[1] = y11;
        firstLineCoordinates[2] = x12;
        firstLineCoordinates[3] = y12;

        double[] secondLineCoordinates = new double[4];
        secondLineCoordinates[0] = x21;
        secondLineCoordinates[1] = y21;
        secondLineCoordinates[2] = x22;
        secondLineCoordinates[3] = y22;

        printLongerLine(firstLineCoordinates, secondLineCoordinates);
    }

    public static void printLongerLine(double[] firstLine, double[] secondLine) {
        double firstLineDist = calculateLineDistance(firstLine);
        double secondLineDist = calculateLineDistance(secondLine);

        int startIdx = -1;
        double[] longerLine;

        if (firstLineDist >= secondLineDist) {
            startIdx = findClosestPoint(firstLine);

            longerLine = firstLine;
        } else {
            startIdx = findClosestPoint(secondLine);
            longerLine = secondLine;
        }

        if (startIdx == 0) {
            System.out.printf("(%.0f, %.0f)(%.0f, %.0f)",longerLine[0], longerLine[1], longerLine[2], longerLine[3]);
        } else {
            System.out.printf("(%.0f, %.0f)(%.0f, %.0f)",longerLine[2], longerLine[3], longerLine[0], longerLine[1]);
        }

    }

    public static double calculateLineDistance(double[] line) {

        double xDiff = line[0] - line[2];
        double yDiff = line[1] - line[3];
        double distance = Math.sqrt((xDiff * xDiff + yDiff * yDiff));

        return distance;

    }

    public static int findClosestPoint(double[] line) {
        double d1 = Math.sqrt((line[0] * line[0] + line[1] * line[1]));
        double d2 = Math.sqrt((line[2] * line[2] + line[3] * line[3]));

        if (d1 <= d2) {
            return 0;
        } else {
            return 2;
        }
    }
}
