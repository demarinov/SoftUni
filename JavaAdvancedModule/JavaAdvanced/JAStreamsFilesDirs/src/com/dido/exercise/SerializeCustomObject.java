package com.dido.exercise;

import java.io.*;

public class SerializeCustomObject {

    public static void main(String[] args) {


        // Write a program that saves and loads the information about a custom object
        // using ObjectInputStream and ObjectOutputStream.
        //Create a simple class called "Course" that has a String field containing
        // its name and an integer field containing
        // the number of students attending the course. Set the name of the save file as course.ser.

        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\course.ser";

        Course course = new Course();
        course.setName("Maths");
        course.setStudents(10);

        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(path))) {

            writer.writeObject(course);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(path))) {

            Course fetchedCourse = (Course)reader.readObject();

            System.out.printf("%s -> %d",fetchedCourse.getName(), fetchedCourse.getStudents());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Course implements Serializable {

        private String name;
        private Integer students;

        public Course() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getStudents() {
            return students;
        }

        public void setStudents(Integer students) {
            this.students = students;
        }
    }
}
