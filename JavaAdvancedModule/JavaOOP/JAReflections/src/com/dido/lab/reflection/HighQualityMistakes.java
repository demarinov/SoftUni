package com.dido.lab.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class HighQualityMistakes {

    public static void main(String[] args) throws ClassNotFoundException {

        Class clazz = Class.forName("com.dido.lab.reflection.Reflection");

        Method[] methods = clazz.getDeclaredMethods();

        Set<String> mistakenGetters = new TreeSet<>();
        Set<String> mistakenSetters = new TreeSet<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                // getter

                if (!Modifier.isPublic(method.getModifiers())) {
                    mistakenGetters.add(method.getName());
                }
            } else if (method.getName().startsWith("set")) {
                // setter
                if (!Modifier.isPrivate(method.getModifiers())) {
                    mistakenSetters.add(method.getName());
                }
            }
        }

        Field[] fields = clazz.getDeclaredFields();

        Set<String> mistakenFields = new TreeSet<>();
        for (Field field : fields) {

            if (!Modifier.isPrivate(field.getModifiers())) {
                mistakenFields.add(field.getName());
            }
        }

        mistakenFields.forEach(s -> System.out.printf("%s must be private!%n",s));
        mistakenGetters.stream().forEach(s -> System.out.printf("%s have to be public!%n",s));
        mistakenSetters.stream().forEach(s -> System.out.printf("%s have to be private!%n",s));

    }
}
