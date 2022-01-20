package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PetsClinic {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Pet> petList = new ArrayList<>();
        List<Clinic> clinicList = new ArrayList<>();

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {

            String[] commandData = sc.nextLine().split("\\s");

            String command = commandData[0];

            switch(command) {

                case "Create":
                    String type = commandData[1];

                    if ("Pet".equals(type)) {

                        String name = commandData[2];
                        Integer age = Integer.parseInt(commandData[3]);
                        String kind = commandData[4];
                        Pet pet = new Pet(name,age,kind);

                        boolean result = validatePetUponCreation(petList,pet);
                        if (result) {
                            petList.add(pet);
                        }
                    } else if ("Clinic".equals(type)) {
                        String clinicName = commandData[2];
                        Integer rooms = Integer.parseInt(commandData[3]);

                        try {
                            validateRooms(rooms);
                            Clinic newClinic = new Clinic(clinicName, rooms);
                            if (!clinicList.contains(newClinic)) {
                                clinicList.add(newClinic);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getLocalizedMessage());
                        }


                    }

                    break;
                case "Add":
                    String petName = commandData[1];
                    String clinicName = commandData[2];

                    try {
                        validatePetUponAddition(petList,petName);
                    } catch (Exception e) {
                        System.out.println(e.getLocalizedMessage());
                        break;
                    }

                    Pet pet = petList.stream()
                            .filter(p -> petName.equals(p.getName())).findFirst().orElse(null);
                    Clinic clinic = clinicList.stream()
                            .filter(c -> clinicName.equals(c.getName())).findFirst().orElse(null);

                    boolean result = false;

                    if (clinic != null) {
                        List<Room> roomList = clinic.getRoomsList();
                        result = addPet(roomList, pet);
                    }

                    System.out.println(result);
                    break;
                case "Release":
                    clinicName = commandData[1];
                    clinic = clinicList.stream()
                            .filter(c -> clinicName.equals(c.getName())).findFirst().orElse(null);
                    result = false;
                    if (clinic != null) {
                        List<Room> roomList = clinic.getRoomsList();
                        result = releasePet(roomList);
                    }

                    System.out.println(result);
                    break;
                case "HasEmptyRooms":
                    clinicName = commandData[1];
                    clinic = clinicList.stream()
                            .filter(c -> clinicName.equals(c.getName())).findFirst().orElse(null);
                    result = false;
                    if (clinic != null) {
                        result = hasEmptyRooms(clinic);
                    }
                    System.out.println(result);
                    break;
                case "Print":
                    clinicName = commandData[1];
                    if (commandData.length == 2) {
                        printClinicRooms(clinicList,clinicName);
                    } else if (commandData.length == 3) {
                        Integer roomNum = Integer.parseInt(commandData[2]);
                        printClinicRoomPet(clinicList,clinicName, roomNum);
                    }
                    break;
            }
        }


    }

    public static void validatePetUponAddition(List<Pet> petList, String petName) throws Exception {

        if ((petName != null && !containsPet(petList,petName))) {
            throw new Exception("Invalid Operation!");
        }

    }

    public static boolean validatePetUponCreation(List<Pet> petList, Pet pet) {

        if ((pet != null && containsPet(petList,pet))) {
            return false;
        }

        return true;
    }

    public static void validateRooms(int roomNum) throws Exception {

        if (roomNum % 2 == 0) {
            throw new Exception("Invalid Operation!");
        }
    }

    public static void printClinicRooms(List<Clinic> clinicList, String clinicName) {
        Clinic clinic = clinicList.stream().filter(c -> clinicName.equals(c.getName()))
                .findFirst().orElse(null);

        if (clinic != null) {

            clinic.getRoomsList().stream().sorted().forEach(System.out::println);
        }

    }

    public static void printClinicRoomPet(List<Clinic> clinicList, String clinicName, Integer roomNum) {

        Clinic clinic = clinicList.stream().filter(c -> clinicName.equals(c.getName()))
                .findFirst().orElse(null);

        if (clinic != null) {
            Room room = clinic.getRoomsList().get(roomNum-1);

            if (room.roomEmpty()) {
                System.out.println("Room empty");
            } else {
                System.out.println(room.getPet());
            }
        }

    }

    public static boolean hasEmptyRooms(Clinic clinic) {
        List<Room> roomList = clinic.getRoomsList();

        Room room = roomList.stream().filter(r -> r.roomEmpty()).findFirst().orElse(null);

        if (room != null) {
            return true;
        }

        return false;
    }

    public static boolean clinicExists(List<Clinic> clinicList, Clinic clinic) {
        return clinicList.contains(clinic);
    }

    public static boolean containsPet(List<Pet> petList, Pet pet) {
        return petList.contains(pet);
    }

    public static boolean containsPet(List<Pet> petList, String petName) {
        Pet pet = petList.stream()
                .filter(p -> petName.equals(p.getName())).findFirst().orElse(null);

        return pet == null ? false : true;

    }

    public static boolean releasePet(List<Room> roomList) {


        // release pet: 1 2 3 4 5 - 3,4,5 1,2
        int midIndex = roomList.size() / 2;

        if (!roomList.get(midIndex).roomEmpty()) {
            roomList.get(midIndex).setPet(null);
            return true;
        }

        int step = 1;
        int leftIndex = 0;
        int rightIndex = midIndex+step;

        while(rightIndex <= roomList.size()-1) {

            if (!roomList.get(rightIndex).roomEmpty()) {
                roomList.get(rightIndex).setPet(null);
                return true;
            }

            rightIndex += step;
        }

        leftIndex = midIndex - step;
        while(leftIndex>=0) {

            if (!roomList.get(leftIndex).roomEmpty()) {
                roomList.get(leftIndex).setPet(null);
                return true;
            }

            leftIndex -= step;
        }

        return false;
    }

    public static boolean addPet(List<Room> roomList, Pet newPet) {
        // adding pet 1 2 3 4 5 - 3,2,4,1,5

        int midIndex = roomList.size() / 2;

        if (roomList.get(midIndex).roomEmpty()) {
            roomList.get(midIndex).setPet(newPet);
            return true;
        }

        int step = 1;
        int leftIndex = midIndex;
        int rightIndex = midIndex;

        while(leftIndex>=0 || rightIndex <= roomList.size()-1) {

            leftIndex -= step;
            if (leftIndex >=0 && roomList.get(leftIndex).roomEmpty()) {
                roomList.get(leftIndex).setPet(newPet);
                return true;
            }

            rightIndex += step;
            if (rightIndex <= roomList.size()-1 && roomList.get(rightIndex).roomEmpty()) {
                roomList.get(rightIndex).setPet(newPet);
                return true;
            }
        }

        return false;
    }

    static class Clinic {

        private String name;
        private List<Room> roomsList;

        public Clinic(String name,int rooms) throws Exception {
            if (rooms % 2 == 0) {
                throw new Exception("Rooms are even number!");
            }
            roomsList = new ArrayList<>();
            for (int i = 0; i < rooms; i++) {
                Room room = new Room();
                room.setNumber(i+1);
                roomsList.add(room);
            }
            this.name = name;
        }

        public List<Room> getRoomsList() {
            return roomsList;
        }

        public void setRoomsList(List<Room> roomsList) {
            this.roomsList = roomsList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Clinic clinic = (Clinic) o;
            return Objects.equals(name, clinic.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name) + 110;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Room implements Comparable<Room> {

        private Pet pet;
        private Integer number;

        public Room() {
            pet = null;
        }

        public Room(Pet pet) {
            this.pet = pet;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public boolean roomEmpty() {

            return pet == null;
        }

        public Pet getPet() {
            return pet;
        }

        public void setPet(Pet pet) {
            this.pet = pet;
        }

        @Override
        public String toString() {
            if (this.roomEmpty()) {
                return "Room empty";
            }
            return pet.toString();
        }

        @Override
        public int compareTo(Room o) {
            return this.getNumber().compareTo(o.getNumber());
        }
    }

    static class Pet {
        private String name;
        private Integer age;
        private String kind;

        public Pet(String name, Integer age, String kind) {
            this.name = name;
            this.age = age;
            this.kind = kind;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        @Override
        public String toString() {
            return name + " "+ age + " "+ kind;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pet pet = (Pet) o;
            return Objects.equals(name, pet.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name) + 205;
        }
    }
}
