package com.dido.exercise;

import java.util.Scanner;

public class BoxMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double length = Double.parseDouble(sc.nextLine());
        double width = Double.parseDouble(sc.nextLine());
        double height = Double.parseDouble(sc.nextLine());

        try {
            Box box = new Box(length, width, height);
            System.out.println(box);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }


    }

    static class Box {
        private double length;
        private double width;
        private double height;

        public Box(double length, double width, double height) {
            setLength(length);
            setWidth(width);
            setHeight(height);
        }

        public double calculateVolume() {

            return length * width * height;
        }

        public double calculateLateralSurfaceArea() {

            return (height * width * 2) + (height * length * 2);
        }

        public double calculateSurfaceArea() {

            return (length * width * 2) + (height * width * 2) + (height * length * 2);
        }

        private void setLength(double length) {

            if (length <= 0) {
                throw new IllegalArgumentException("Length cannot be zero or negative");
            }
            this.length = length;
        }

        private void setWidth(double width) {
            if (width <= 0) {
                throw new IllegalArgumentException("Width cannot be zero or negative");
            }
            this.width = width;
        }

        private void setHeight(double height) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height cannot be zero or negative");
            }
            this.height = height;
        }

        @Override
        public String toString() {
            return String.format("Surface Area  - %.2f%n" +
                            "Lateral Surface Area - %.2f%nVolume - %.2f"
                    , calculateSurfaceArea(), calculateLateralSurfaceArea(), calculateVolume());
        }
    }
}
