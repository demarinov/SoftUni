package com.dido.lab.reflection;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class GettersSettersMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // •	"{name} will return class {Return Type}"
        //Then print all setters in format:
        //•	"{name} and will set field of class {Parameter Type}"
        Class clazz = Reflection.class;
        Method[] methods = clazz.getDeclaredMethods();

        Map<String, String> getters = new TreeMap<>();
        Map<String, String> setters = new TreeMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                // getter
                getters.putIfAbsent(method.getName(),method.getReturnType().getName());
            } else if (method.getName().startsWith("set")) {
                // setter
                setters.putIfAbsent(method.getName(), method.getParameterTypes()[0].getName());
            }
        }

        getters.entrySet().stream().
                forEach(e -> System.out.printf("%s will return class %s%n",e.getKey(),e.getValue()));
        setters.entrySet().stream().
                forEach(e -> System.out.printf("%s and will set field of class %s%n",
                        e.getKey(),e.getValue()));
    }
}
