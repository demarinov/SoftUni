package com.dido.more;

import java.util.Scanner;

public class Hospital {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int days = Integer.parseInt(sc.nextLine());
        int patientsTaken = 0;
        int patientsDiscarded = 0;
        int doctors = 7;


        for (int i = 1; i <= days; i++) {

            if (i % 3 == 0) {
                if (patientsDiscarded > patientsTaken) {
                    doctors++;
                }
            }

            int patients = Integer.parseInt(sc.nextLine());

            if (patients > doctors) {
                patientsDiscarded += (patients - doctors);
                patientsTaken += doctors;
            } else {
                patientsTaken += patients;
            }

        }

        System.out.println("Treated patients: "+patientsTaken+".");
        System.out.println("Untreated patients: "+patientsDiscarded+".");
    }
}
