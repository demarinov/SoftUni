package com.dido.lab;

import java.util.Scanner;

public class HotelReservation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] priceData = sc.nextLine().split("\\s");

        Double pricePerDay = Double.parseDouble(priceData[0]);
//        <pricePerDay> <numberOfDays> <season> <discountType>
        Integer numOfDays = Integer.parseInt(priceData[1]);
        Season season = "Autumn".equals(priceData[2])
                ? Season.Autumn : "Spring".equals(priceData[2])
                ? Season.Spring : "Winter".equals(priceData[2])
                ? Season.Winter : Season.Summer;
        DiscountType discountType = "VIP".equals(priceData[3]) ? DiscountType.VIP
                : "SecondVisit".equals(priceData[3]) ? DiscountType.SecondVisit : DiscountType.None;

        Double totalPrice = pricePerDay * season.getValue() * numOfDays;
        totalPrice -= totalPrice * discountType.getValue()/100;

        System.out.printf("%.2f",totalPrice);

    }

    enum DiscountType {

        VIP(20), SecondVisit(10), None(0);

        private int value;

        DiscountType(int val) {
            this.value = val;
        }

        public int getValue() {
            return value;
        }
    }

    enum Season {

        Autumn(1), Spring(2), Winter(3), Summer(4);

        private int value;

        Season(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }
    }

    private static class PriceCalculator {



    }
}
