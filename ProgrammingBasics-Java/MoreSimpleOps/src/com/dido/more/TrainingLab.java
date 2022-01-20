package com.dido.more;

import java.util.Scanner;

public class TrainingLab {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double length = Double.parseDouble(sc.nextLine());
        double width = Double.parseDouble(sc.nextLine());

        // 3 ≤ width ≤ length ≤ 100.

        // 1 desk size: 70 width na 120 length

        // multiple by 100 to convert to cm
        length *= 100;
        width *= 100;
        // minus corridor 100 wide
        int desks = (int)((width - 100) / 70);
        int rowsWithDesks = (int)(length / 120);

        // 1 place for door and 2 places for cattedra
        int places = desks  * rowsWithDesks - 3;

        System.out.println(places);


    }
}
