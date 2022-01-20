package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class PointInRectangle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double[] rectangleCoordinates = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToDouble(d -> Double.parseDouble(d))
                .toArray();

        Point bottomLeft = new Point(rectangleCoordinates[0],rectangleCoordinates[1]);
        Point topRight = new Point(rectangleCoordinates[2],rectangleCoordinates[3]);

        Rectangle rectangle = new Rectangle(bottomLeft, topRight);

        int n = Integer.parseInt(sc.nextLine());

        printOutput(rectangle, n, sc);
    }

    public static void printOutput(Rectangle rectangle, int n, Scanner sc) {
        for (int i = 0; i < n; i++) {

            double[] pointCoordinates = Arrays.stream(sc.nextLine().split("\\s"))
                    .mapToDouble(s -> Double.parseDouble(s))
                    .toArray();

            Point point = new Point(pointCoordinates[0],pointCoordinates[1]);
            System.out.println(rectangle.contains(point));
        }

    }

    private static class Rectangle{

        private Point bottomLeft;
        private Point topRight;

        public Rectangle(Point bottomLeft, Point topRight) {
            this.bottomLeft = bottomLeft;
            this.topRight = topRight;
        }

        public boolean contains(Point point) {

            if (point.getX().doubleValue() >= this.bottomLeft.getX().doubleValue()
            && point.getX().doubleValue() <= this.topRight.getX().doubleValue()) {

                if (point.getY().doubleValue() >= this.getBottomLeft().getY().doubleValue()
                && point.getY().doubleValue() <= this.topRight.getY().doubleValue()) {

                    return true;
                }
            }

            return false;
        }

        public Point getBottomLeft() {
            return bottomLeft;
        }

        public void setBottomLeft(Point bottomLeft) {
            this.bottomLeft = bottomLeft;
        }

        public Point getTopRight() {
            return topRight;
        }

        public void setTopRight(Point topRight) {
            this.topRight = topRight;
        }
    }

    private static class Point {

        private Double x;
        private Double y;

        public Point(Double x, Double y) {
            this.x = x;
            this.y = y;
        }

        public Double getX() {
            return x;
        }

        public void setX(Double x) {
            this.x = x;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            this.y = y;
        }
    }
}
