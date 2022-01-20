package com.dido.lab.reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ReflectionMain {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);

        // •	This class type
        //•	Super class type
        //•	All interfaces that are implemented by this class
        //•	Instantiate object using reflection and print it too

        Class clazz = Reflection.class;

        System.out.println(clazz);
        System.out.println(clazz.getSuperclass());

        for (Class inf : clazz.getInterfaces()) {
            System.out.println(inf.getSimpleName());
        }

        Object reflection =  clazz.getDeclaredConstructor().newInstance();
        System.out.println(reflection);
    }
}
