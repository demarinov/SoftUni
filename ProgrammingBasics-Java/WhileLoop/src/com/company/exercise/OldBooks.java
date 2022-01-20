package com.company.exercise;

import java.util.Scanner;

public class OldBooks {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        String searchedBook = input;
        // number of books in the lib
        int count = 0;
        boolean bookFound = false;

        input = sc.nextLine();
        while (!input.equals("No More Books")) {


            if (searchedBook.equals(input)) {
                bookFound = true;
                break;
            }

            count++;
            input = sc.nextLine();
        }

        if (bookFound) {
            System.out.println("You checked " +
                    count +
                    " books and found it.");
        } else {
            System.out.println("The book you search is not here!");
            System.out.println("You checked " +
                    count +
                    " books.");
        }
    }
}
