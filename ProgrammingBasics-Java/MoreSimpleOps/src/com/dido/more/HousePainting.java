package com.dido.more;

import java.util.Scanner;

public class HousePainting {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double x = Double.parseDouble(sc.nextLine());
        double y = Double.parseDouble(sc.nextLine());
        double h = Double.parseDouble(sc.nextLine());

//        Стените имат следните размери:
//•	Предната и задната стена са квадрати със страна „x“
//        o	на предната стена има правоъгълна врата с широчина 1.2м и височина 2м
//•	Страничните стени са правоъгълници със страни „x“ и „y“
//        o	и на двете странични стени има по един квадратен прозорец със страна 1.5м
//        Покривът има следните размери:
//•	Два правоъгълника със страни „x“ и „y“
//•	Два равностранни триъгълника със страна „x“ и височина „h“
//        Трябва да пресметнете площта на всички страни и площта на покрива, за да
//        намерите колко литра от всяка боя ще са нужни.

        double frontSide = x * x - (1.2 * 2);
        double backSide = x * x;
        double side = x * y - (1.5 * 1.5);

        // total side area
        double totalSide = frontSide + backSide + (2 * side);

        double roofFront = x * h/2;
        double roofBack = roofFront;
        double roofSide = x * y;

        double totalRoof = roofBack + roofFront + (2 * roofSide);

//        Разхода на зелената боя е 1 литър за 3.4 м2, а на червената – 1 литър за 4.3 м2.
        double greenLiters =  totalSide / 3.4;
        double redLiters = totalRoof / 4.3;
        System.out.printf("%.2f%n",greenLiters);
        System.out.printf("%.2f",redLiters);
    }
}
