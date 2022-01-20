package com.company.exercise;

import java.util.Scanner;

public class PassGenerator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int l = Integer.parseInt(sc.nextLine());

        int n1, n2, n3 = 0;
        char a1 = 0;
        char a2 = 0;

        for (int i = 1; i < n; i++) {
            n1 = i;
            for (int j = 1; j < n; j++) {
                n2 = j;
                for (int k = 1; k <= l; k++) {

                    switch(k) {
                        case 1:
                            // a
                            a1 = 'a';
                            break;
                        case 2:
                            // b
                            a1 = 'b';
                            break;
                        case 3:
                            // c
                            a1 = 'c';
                            break;
                        case 4:
                            // d
                            a1 = 'd';
                            break;
                        case 5:
                            // e
                            a1 = 'e';
                            break;
                        case 6:
                            // f
                            a1 = 'f';
                            break;
                        case 7:
                            // g
                            a1 = 'g';
                            break;
                        case 8:
                            //h
                            a1 = 'h';
                            break;
                        case 9:
                            // i
                            a1 = 'i';
                            break;
                        default:
                            break;
                    }

                    for (int m = 1; m <= l; m++) {

                        switch(m) {
                            case 1:
                                // a
                                a2 = 'a';
                                break;
                            case 2:
                                // b
                                a2 = 'b';
                                break;
                            case 3:
                                // c
                                a2 = 'c';
                                break;
                            case 4:
                                // d
                                a2 = 'd';
                                break;
                            case 5:
                                // e
                                a2 = 'e';
                                break;
                            case 6:
                                // f
                                a2 = 'f';
                                break;
                            case 7:
                                // g
                                a2 = 'g';
                                break;
                            case 8:
                                //h
                                a2 = 'h';
                                break;
                            case 9:
                                // i
                                a2 = 'i';
                                break;
                            default:
                                break;
                        }

                        int f = 0;
                        if (i > j) {
                            // i+1
                            f = i+1;

                        } else {
                            // j+1
                            f = j + 1;

                        }
                        for (int o = f; o <= n; o++) {
                            n3 = o;
                            System.out.printf("%d%d%c%c%d ",n1,n2,a1,a2,n3);
                        }


                    }
                }
            }
        }
    }
}
