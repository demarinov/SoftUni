package com.dido.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Songs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Song> songList = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            String[] songData = sc.nextLine().split("_");
            songList.add(new Song(songData[0], songData[1], songData[2]));

        }

        String command = sc.nextLine();

        if (command.equalsIgnoreCase("all")) {
            printAllSongs(songList);
        } else {
            printSong(songList,command);
        }

    }
    public static void printAllSongs(List<Song> songList) {
        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i);
            System.out.println(song.toString());
        }
    }


    public static void printSong(List<Song> songList, String type) {
        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i);
            if (song.typeList.equalsIgnoreCase(type)) {
                System.out.println(song.toString());
            }
        }
    }
}

class Song {

    String typeList;
    String name;
    String time;

    public Song(String typeList, String name, String time) {
        this.typeList = typeList;
        this.name = name;
        this.time = time;
    }

    public String getTypeList() {
        return typeList;
    }

    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return name;
    }
}
