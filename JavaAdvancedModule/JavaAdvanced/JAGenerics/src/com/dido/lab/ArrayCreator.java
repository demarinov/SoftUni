package com.dido.lab;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayCreator {

    public static void main(String[] args) {

        String[] strings = ArrayCreator.create(10,"none");
        Integer[] ints = ArrayCreator.create(Integer.class,10,0);

        Arrays.stream(strings).forEach(System.out::println);
        Arrays.stream(ints).forEach(System.out::println);
    }

    public static <T> T[] create(int len, T item) {

        T[] array = (T[]) new Object[len];

        for (int i = 0; i < array.length; i++) {
            array[i] = item;
        }

        return array;
    }

    public static <T> T[] create(Class<T> classs,int len, T item) {

        T[] array = (T[]) Array.newInstance(classs,len);

        for (int i = 0; i < array.length; i++) {

            array[i]=item;
        }

        return array;
    }
}
