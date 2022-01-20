package com.dido.more;

import java.util.Scanner;

public class SchoolCamp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String season =sc.nextLine();
        String groupType = sc.nextLine();
        int scholars = Integer.parseInt(sc.nextLine());
        int nights = Integer.parseInt(sc.nextLine());
        double price = 0.0d;
        String sport = "";

//        Зимна ваканция	Пролетна ваканция	Лятна ваканция
//        момчета/момичета	9.60	7.20	15
//        смесена група	10	9.50	20

//        Зимна ваканция	Пролетна ваканция	Лятна ваканция
//        момичета	Gymnastics	Athletics	Volleyball
//        момчета	Judo	Tennis	Football
//        смесена група	Ski	Cycling	Swimming

          if (season.equals("Winter")) {
              if (groupType.equals("boys") || groupType.equals("girls")) {
                price = 9.6 * nights * scholars;
                if (groupType.equals("girls")) {
                    sport = "Gymnastics";
                } else {
                    sport = "Judo";
                }
              } else if (groupType.equals("mixed")) {
                  price = 10.0 * nights * scholars;
                  sport = "Ski";
              }
          } else if (season.equals("Spring")) {
              if (groupType.equals("boys") || groupType.equals("girls")) {
                  price = 7.2 * nights * scholars;
                  if (groupType.equals("girls")) {
                      sport = "Athletics";
                  } else {
                      sport = "Tennis";
                  }
              } else if (groupType.equals("mixed")) {
                  price = 9.5 * nights * scholars;
                  sport = "Cycling";
              }
          } else if (season.equals("Summer")) {
              if (groupType.equals("boys") || groupType.equals("girls")) {
                  price = 15 * nights * scholars;
                  if (groupType.equals("girls")) {
                      sport = "Volleyball";
                  } else {
                      sport = "Football";
                  }
              } else if (groupType.equals("mixed")) {
                  price = 20 * nights * scholars;
                  sport = "Swimming";
              }
          }
//          •	Ако броят на учениците е 50 или повече, училището получава 50% отстъпка
//•	Ако броят на учениците е 20 или повече и в същото време по-малък от 50, училището получава 15% отстъпка
//•	Ако броят на учениците е 10 или повече и в същото време по-малък от 20, училището получава 5% отстъпка

        if (scholars >= 50) {
            price -= (0.5 * price);
        } else if (scholars >=20 && scholars < 50) {
            price -= (0.15 * price);
        } else if (scholars >= 10 && scholars < 20) {
            price -= (0.05 * price);
        }

        System.out.printf("%s %.2f lv.",sport,price);

    }
}
