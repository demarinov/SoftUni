package com.company.lab;

import java.util.Scanner;

public class CinemaTickets {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        int totalTickets = 0;
        int studentTickets = 0;
        int standardTickets = 0;
        int kidTickets = 0;

        while (!input.equals("Finish")) {

            String movie = input;
            int freePlaces = Integer.parseInt(sc.nextLine());
            int initFreePlaces = freePlaces;


            input = sc.nextLine();
            while (!input.equals("End")) {

                String ticket = input;

                if (ticket.equals("student")) {
                    studentTickets++;
                    freePlaces--;
                } else if (ticket.equals("standard")) {
                    standardTickets++;
                    freePlaces--;
                } else if (ticket.equals("kid")) {
                    kidTickets++;
                    freePlaces--;
                }


                if (freePlaces > 0) {
                    input = sc.nextLine();
                } else {
                    break;
                }
            }

            System.out.printf(movie +
                    " - " +
                    "%.2f%s",(1.0 * (initFreePlaces - freePlaces)/ initFreePlaces * 100),
                    "% full.\n");

            input = sc.nextLine();
        }

        totalTickets = studentTickets + standardTickets + kidTickets;
        System.out.println("Total tickets: " +
                totalTickets);
        System.out.printf("%.2f%s",(1.0 * studentTickets / totalTickets * 100),
                "% student tickets.\n");
        System.out.printf("%.2f%s",(1.0 * standardTickets / totalTickets * 100),
                "% standard tickets.\n");
        System.out.printf("%.2f%s",(1.0 * kidTickets / totalTickets * 100),
                "% kids tickets.\n");
    }
}
