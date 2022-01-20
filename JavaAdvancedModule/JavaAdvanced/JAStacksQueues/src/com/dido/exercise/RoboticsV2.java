package com.dido.exercise;

import java.util.ArrayDeque;
import java.util.Scanner;

public class RoboticsV2 {

       public static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {

            String[] input = scanner.nextLine().split(";");
            String[] robots = new String[input.length];
            int[] processTime = new int[input.length];
            int[] worktime = new int[input.length];

            for (int i = 0; i < input.length; i++) {
                String[] data = input[i].split("-");
                String name = data[0];
                int time = Integer.parseInt(data[1]);
                robots[i] = name;
                processTime[i] = time;
            }

            String startTime = scanner.nextLine();

            ArrayDeque<String> products = new ArrayDeque<>();

            String inputProduct = scanner.nextLine();

            while (!inputProduct.equals("End")) {
                products.offer(inputProduct);

                inputProduct = scanner.nextLine();
            }

            String[] timeData = startTime.split(":");
            int hours = Integer.parseInt(timeData[0]);
            int minutes = Integer.parseInt(timeData[1]);
            int seconds = Integer.parseInt(timeData[2]);

            int beginSeconds = hours * 3600 + minutes * 60 + seconds;

            while (!products.isEmpty()) {
                beginSeconds++;

                String product = products.poll();

                boolean isWorking = false;
                for (int i = 0; i < robots.length; i++) {
                    if (worktime[i] == 0 && !isWorking) {
                        worktime[i] = processTime[i];
                        isWorking = true;
                        printRobotData(robots[i], product, beginSeconds);
                    }
                    if (worktime[i] > 0) {
                        worktime[i]--;
                    }
                }

                if (!isWorking) { //ako ima svoboden
                    products.offer(product);
                }
            }

        }

        private static void printRobotData(String robot, String product, int beginSeconds) {
            long s = beginSeconds % 60;
            long m = (beginSeconds / 60) % 60;
            long h = (beginSeconds / (60 * 60)) % 24;
            System.out.printf("%s - %s%s",robot, product,
                    String.format(" [%02d:%02d:%02d] %n", h, m, s));
        }
}
