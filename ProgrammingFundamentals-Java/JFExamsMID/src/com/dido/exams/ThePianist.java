package com.dido.exams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ThePianist {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Pianist> pianists = new ArrayList<>();

        // load initial pianists
        for (int i = 0; i < n; i++) {

            String[] pianistData = sc.nextLine().split("\\|");

            String piece = pianistData[0];
            String composer = pianistData[1];
            String key = pianistData[2];

            pianists.add(new Pianist(piece, composer, key));
        }

        String command = sc.nextLine();

        while (!command.equalsIgnoreCase("stop")) {

            String[] commandData = command.split("\\|");

            String instruction = commandData[0];

            if (instruction.equalsIgnoreCase("add")) {

                String piece = commandData[1];
                String composer = commandData[2];
                String key = commandData[3];

                Pianist pianist = new Pianist(piece, composer, key);
//                •	Add|{piece}|{composer}|{key}
                addPiece(pianists, pianist);

            } else if (instruction.equalsIgnoreCase("remove")) {

                String piece = commandData[1];
//                •	Remove|{piece}
                removePiece(pianists, piece);

            } else if (instruction.equalsIgnoreCase("changekey")) {

                String piece = commandData[1];
                String newKey = commandData[2];
//                •	ChangeKey|{piece}|{new key}
                changeKey(pianists, piece, newKey);

            }

            command = sc.nextLine();
        }


        printPieces(pianists);
    }

    public static void printPieces(List<Pianist> pianists) {

        Collections.sort(pianists,
                (p1, p2) -> (p1.getPiece() + p1.getComposer()).compareTo((p2.getPiece() + p2.getComposer())));

        for (int i = 0; i < pianists.size(); i++) {

            System.out.println(pianists.get(i));
        }

    }

    public static void changeKey(List<Pianist> pianists, String piece, String newKey) {

        //                o	If the piece is in the collection, change its key with the given one and print:
//                "Changed the key of {piece} to {new key}!"
//                o	If the piece is not in the collection, print:
//                "Invalid operation! {piece} does not exist in the collection."

        for (int i = 0; i < pianists.size(); i++) {

            Pianist pianist = pianists.get(i);

            if (pianist.getPiece().equals(piece)) {
                pianist.setKey(newKey);
                System.out.println("Changed the key of "+piece+" to "+newKey+"!");
                return;
            }

        }

        System.out.println("Invalid operation! "+piece+" does not exist in the collection.");

    }

    public static void removePiece(List<Pianist> pianists, String piece) {

        //                o	If the piece is in the collection, remove it and print:
//                "Successfully removed {piece}!"
//                o	If the piece is not in the collection, print:
//                "Invalid operation! {piece} does not exist in the collection."

        for (int i = 0; i < pianists.size(); i++) {

            Pianist pianist = pianists.get(i);

            if (pianist.getPiece().equals(piece)) {

                pianists.remove(i);
                System.out.println("Successfully removed "+piece+"!");
                return;
            }
        }

        System.out.println("Invalid operation! "+piece+" does not exist in the collection.");
    }

    public static void addPiece(List<Pianist> pianists, Pianist pianist) {

        //                o	You need to add the given piece with the information about it to the other pieces
//                o	If the piece is already in the collection, print:
//                "{piece} is already in the collection!"
//                o	If the piece is not in the collection, print:
//                "{piece} by {composer} in {key} added to the collection!"

        for (int i = 0; i < pianists.size(); i++) {

            Pianist currentPianist = pianists.get(i);
            if (currentPianist.getPiece().equals(pianist.getPiece())) {

                System.out.println(pianist.getPiece()+" is already in the collection!");
                return;
            }
        }

        pianists.add(pianist);
        System.out.println(pianist.getPiece()+" by "+pianist.getComposer()+" in "+pianist.getKey()+" added to the collection!");
    }
}

class Pianist {

    private String piece;
    private String composer;
    private String key;

    public Pianist(String piece, String composer, String key) {
        this.piece = piece;
        this.composer = composer;
        this.key = key;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return piece+" -> Composer: "+composer+", Key: "+key;
    }
}
