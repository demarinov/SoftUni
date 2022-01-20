package com.dido.lab;

import java.text.DecimalFormat;
import java.util.*;

public class AcademyGraduation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = Integer.parseInt(sc.nextLine());

        Map<String, List<Double>> gradsMap = new TreeMap<>();
        for (int i = 0; i < count; i++) {

            String name = sc.nextLine();
//            double[] grades = Arrays.stream(sc.nextLine().split("\\s"))
//                    .mapToDouble(s -> Double.parseDouble(s))
//                    .toArray();

            String[] scores = sc.nextLine().split("\\s+");
            Double[] grades = new Double[scores.length];

            for (int j = 0; j < grades.length; j++) {
                grades[j] = Double.parseDouble(scores[j]);
            }

//            Double avg = Arrays.stream(grades).mapToDouble(s -> s)
//                    .average().getAsDouble();


            double sum = 0.0d;

            for (int j = 0; j < grades.length; j++) {

                sum += grades[j];
            }

            double avg = sum / grades.length;

            gradsMap.putIfAbsent(name, new LinkedList<>());
            gradsMap.get(name).add(avg);

        }

        gradsMap.entrySet().stream()
                .map(e ->
                {
                    String name = e.getKey();
//                    DecimalFormat decimalFormat = new DecimalFormat("0.###############");
                    Collections.reverse(e.getValue());
                    String avgValues = e.getValue().stream()
                            .map(s ->
                            {
//                                String avgStr = decimalFormat.format(s);
                                return String.format("%s is graduated with %s%n",name, s);
                            })
                            .reduce("",String::concat);

                    return String.format("%s",avgValues);
                })
                .forEach(System.out::print);
    }
}
