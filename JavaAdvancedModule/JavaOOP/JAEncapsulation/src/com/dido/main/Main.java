package com.dido.main;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        Scanner sc = new Scanner(System.in);

        String playerName = sc.nextLine();
        int[] attributeData = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        if (playerName == null || playerName.trim().isEmpty()) {
            throw new Exception("Player name cannot be empty!");
        }
        Player player = new Player(playerName);
        for (int i = 0; i < attributeData.length; i++) {
            try {
                player.addAttribute(attributeData[i]);
            } catch(IllegalArgumentException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        System.out.printf("Player %s attributes:%n",player.getName());
        player.getAttributes().stream().forEach(System.out::println);

    }

    static class Player {

        private String name;
        private List<Integer> attributes;
        public static final int ATTRIBUTE_MAX = 20;
        public static final int ATTRIBUTE_MIN = 1;
        public static final int ATTRIBUTE_MID = 12;

        public Player(String name) {
            setName(name);
            attributes = new ArrayList<>();
        }

        public void addAttribute(Integer attribute) {

            if (attribute <= 0) {
                throw new IllegalArgumentException("Attribute cannot be zero or negative");
            }

            if (attribute < ATTRIBUTE_MID) {
                attribute = ATTRIBUTE_MID;
            }

            if (attribute > ATTRIBUTE_MAX) {
                attribute = ATTRIBUTE_MAX;
            }

            attributes.add(attribute);
        }

        public String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        public List<Integer> getAttributes() {
            return Collections.unmodifiableList(attributes);
        }

        private void setAttributes(List<Integer> attributes) {
            this.attributes = attributes;
        }
    }
}