package com.dido.exercise;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class BlackBoxIntMain {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner sc = new Scanner(System.in);

        Class clazz = BlackBoxInt.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = (BlackBoxInt) constructor.newInstance();

        Method[] methods = clazz.getDeclaredMethods();

        String input = sc.nextLine();

        while(!"END".equals(input)) {

            //"<command name>_<value>"
            String[] commandData = input.split("_");
            String commandName = commandData[0];
            int value = Integer.parseInt(commandData[1]);

            Method method = Arrays.stream(methods).filter(m -> m.getName().equals(commandName))
                    .findFirst().orElse(null);
            if (method != null) {
                method.setAccessible(true);
                method.invoke(blackBoxInt,value);
            }

            Field field = clazz.getDeclaredField("innerValue");
            field.setAccessible(true);
            System.out.println(field.getInt(blackBoxInt));

            input = sc.nextLine();
        }
    }
}
