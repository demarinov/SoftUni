package com.dido.lab.annotation;

import java.util.Scanner;

public class CreateAnnotation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Class clazz = TestClass.class;
        Subject annotation = (Subject) clazz.getAnnotation(Subject.class);
        System.out.println(annotation.categories()[0] + ","+annotation.categories()[1]);
    }
}


