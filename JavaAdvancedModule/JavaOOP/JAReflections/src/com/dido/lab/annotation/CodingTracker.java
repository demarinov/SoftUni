package com.dido.lab.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CodingTracker {

    @Author(name ="George")
    public static void main(String[] args) {

        Tracker.printMethodsByAuthor(Tracker.class);
    }

    static class Tracker {

        @Author(name="Peter")
        public static void printMethodsByAuthor(Class<?> c1) {

            Map<String, List<String>> methodsByAuthor = new TreeMap<>();
            Method[] methods = c1.getDeclaredMethods();

            for (Method method : methods) {

                Author author = method.getAnnotation(Author.class);

                if (author != null) {

                    methodsByAuthor.putIfAbsent(author.name(), new ArrayList<>());

                    methodsByAuthor.get(author.name()).add(method.getName()+"()");
                }
            }

            methodsByAuthor.entrySet().stream()
                    .forEach(e -> System.out.printf("author: %s with methods: %s",e.getKey(),e.getValue()));
        }
    }
}
