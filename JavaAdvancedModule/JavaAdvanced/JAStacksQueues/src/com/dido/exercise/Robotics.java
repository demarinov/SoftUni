package com.dido.exercise;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Robotics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] robotData = sc.nextLine().split(";");

        List<Robot> roboList = new ArrayList<>();
        getRobots(roboList, robotData, "");

        String startTime = sc.nextLine();

        String input = sc.nextLine();

        Queue<String> pendingProducts = new ArrayDeque<>();


        while(!input.equals("End")) {

            pendingProducts.offer(input);
            input = sc.nextLine();
        }

        String[] timeData = startTime.split(":");
        int hours = Integer.parseInt(timeData[0]);
        int min = Integer.parseInt(timeData[1]);
        int sec = Integer.parseInt(timeData[2]);

        int startTimeInSeconds = (hours * 3600) + (min * 60) + sec;
        while(!pendingProducts.isEmpty()) {

            startTimeInSeconds++;
            checkRobots(roboList, pendingProducts, startTimeInSeconds);

        }

    }

    public static void checkRobots(List<Robot> roboList, Queue<String> pendingProducts, int startTimeInSec) {

        String product = pendingProducts.poll();
        boolean productWorkedOn = false;
        for (int i = 0; i < roboList.size(); i++) {

            Robot robot = roboList.get(i);
            Integer remainingProcessTime = robot.getRemainingProcessTime();

            if (remainingProcessTime.equals(0) && !productWorkedOn) {
                // handle product by this robot
                robot.setProduct(product);
                robot.setRemainingProcessTime(robot.getProcessTime());

                robot.setCurrentProcessTime(String.format("%d",startTimeInSec));
                System.out.println(robot.toString());
                productWorkedOn = true;
            }

            updateRobotTime(robot);
        }

        if (!productWorkedOn) {
            pendingProducts.offer(product);
        }

    }

    public static void updateRobotTime(Robot robot) {
        int roboRemTime = robot.getRemainingProcessTime();
        if (roboRemTime > 0) {
            robot.setRemainingProcessTime(roboRemTime - 1);
        }
    }

    public static void getRobots(List<Robot> roboList, String[] roboData, String startTime) {

        for (int i = 0; i < roboData.length; i++) {

            String[] robo = roboData[i].split("-");
            Robot robot = new Robot();
            robot.setName(robo[0]);
            robot.setProcessTime(Integer.parseInt(robo[1]));
//            robot.convertTime(startTime);
//            robot.setStartTime(startTime);
            roboList.add(robot);
        }
    }

    static class Robot {

        private String name;
        private Integer processTime;
        private Integer remainingProcessTime = BigDecimal.ZERO.intValue();
        private String currentProcessTime;
        private Integer hour;
        private Integer min;
        private Integer sec;
        private String product;
        private String startTime;

        public Robot() {
        }


        public void setHour(Integer hour) {
            this.hour = hour;
        }

        public void setSec(Integer sec) {
            this.sec = sec;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getProcessTime() {
            return processTime;
        }

        public void setProcessTime(Integer processTime) {
            this.processTime = processTime;
        }

        public Integer getRemainingProcessTime() {
            return remainingProcessTime;
        }

        public void setRemainingProcessTime(Integer remainingProcessTime) {
            this.remainingProcessTime = remainingProcessTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String formatTime() {
            hour = (Integer.parseInt(currentProcessTime) / 3600) % 24;
            min = (Integer.parseInt(currentProcessTime) / 60) % 60;
            sec = (Integer.parseInt(currentProcessTime) % 60);

            return String.format("%02d:%02d:%02d",hour,min,sec);
        }

        public void setCurrentProcessTime(String currentProcessTime) {
            this.currentProcessTime = currentProcessTime;
        }

        public void parseTime(String time) {
            String[] timeData = time.split(":");
            hour = Integer.parseInt(timeData[0]);
            min = Integer.parseInt(timeData[1]);
            sec = Integer.parseInt(timeData[2]);
        }

        public void convertTime(String time) {
            parseTime(time);

            int totalSeconds = (hour * 3600) + (min * 60) + sec;
            setCurrentProcessTime(String.format("%d",totalSeconds));
        }

        @Override
        public String toString() {
            // populate hour,min,sec from currentProcessTime.
            formatTime();
            return String.format("%s - %s [%02d:%02d:%02d]",name, product,hour, min, sec);
        }
    }
}
