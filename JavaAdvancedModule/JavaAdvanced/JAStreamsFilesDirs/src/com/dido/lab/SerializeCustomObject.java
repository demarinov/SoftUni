package com.dido.lab;

import java.io.*;

public class SerializeCustomObject {

    public static void main(String[] args) {
        String path="C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\serializedOutput.txt";


        Cube cube = new Cube();

        cube.setColor("green");
        cube.setWidth(15.3d);
        cube.setHeight(12.4d);
        cube.setDepth(3d);

        try(ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(path))) {

            objectOutput.writeObject(cube);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(path))) {

            Cube newCube = (Cube) objectIn.readObject();
            System.out.println(newCube.getColor());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    static class Cube implements Serializable {

        private String color;
        private Double width;
        private Double height;
        private Double depth;

        public Cube() {

        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Double getWidth() {
            return width;
        }

        public void setWidth(Double width) {
            this.width = width;
        }

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }

        public Double getDepth() {
            return depth;
        }

        public void setDepth(Double depth) {
            this.depth = depth;
        }
    }
}
