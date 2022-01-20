package com.dido.exercise;

import java.util.Scanner;

public class TrafficLight {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] trafficLightData = sc.nextLine().split("\\s");

        int n = Integer.parseInt(sc.nextLine());
        TrafficLights[] trafficLights = new TrafficLights[trafficLightData.length];

        for (int i = 0; i < trafficLightData.length; i++) {
            trafficLights[i] = TrafficLights.getByName(trafficLightData[i]);
        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < trafficLights.length; j++) {

                // red -> green -> yellow -> red
                if (trafficLights[j] == TrafficLights.RED) {
                    trafficLights[j] = TrafficLights.GREEN;
                } else if (trafficLights[j] == TrafficLights.GREEN) {
                    trafficLights[j] = TrafficLights.YELLOW;
                } else if (trafficLights[j] == TrafficLights.YELLOW) {
                    trafficLights[j] = TrafficLights.RED;
                }

                System.out.printf("%s ",trafficLights[j].getName());
            }

            System.out.println();
        }

    }

    private enum TrafficLights {

        RED("RED"),GREEN("GREEN"),YELLOW("YELLOW");

        private String name;
        TrafficLights(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static TrafficLights getByName(String name) {

            for (TrafficLights lights : TrafficLights.values()) {

                if (lights.getName().equals(name)) {
                    return lights;
                }
            }

            return null;
        }

    }
}
