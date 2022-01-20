package com.dido.exercise;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SerializeArray {

    public static void main(String[] args) {

        List<Double> arrayList = new ArrayList<>();
        arrayList.add(2.0);
        arrayList.add(3.0);
        arrayList.add(4.0);
        arrayList.add(5.0);

        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\list.ser";

        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(path))) {

            writer.writeObject(arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(path))) {

            List<Double> array = (List)reader.readObject();

            for(Double num : array) {
                System.out.printf("%.2f%n",num);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
