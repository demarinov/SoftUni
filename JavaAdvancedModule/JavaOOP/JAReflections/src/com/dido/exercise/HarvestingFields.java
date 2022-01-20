package com.dido.exercise;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class HarvestingFields {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Class clazz = harvestingFields.RichSoilLand.class;
        Field[] fields = clazz.getDeclaredFields();

        String input = sc.nextLine();


        while(!"HARVEST".equals(input)) {

            // •	private - print all private fields
            //•	protected - print all protected fields
            //•	public - print all public fields
            //•	all - print ALL declared fields
            //•	HARVEST - end the input

            switch(input) {

                case "private":
                    Arrays.stream(fields).filter(f -> Modifier.isPrivate(f.getModifiers()))
                            .map(f -> String.format("%s %s %s","private",
                                    f.getType().getSimpleName(),f.getName()))
                            .forEach(System.out::println);
                    break;
                case "protected":
                    Arrays.stream(fields).filter(f -> Modifier.isProtected(f.getModifiers()))
                            .map(f -> String.format("%s %s %s","protected",
                                    f.getType().getSimpleName(),f.getName()))
                            .forEach(System.out::println);
                    break;
                case "public":
                    Arrays.stream(fields).filter(f -> Modifier.isPublic(f.getModifiers()))
                            .map(f -> String.format("%s %s %s","public",
                                    f.getType().getSimpleName(),f.getName()))
                            .forEach(System.out::println);
                    break;
                case "all":
                    Arrays.stream(fields)
                            .map(f -> {
                                String access = Modifier.isPrivate(f.getModifiers()) ? "private"
                                        : Modifier.isProtected(f.getModifiers()) ? "protected"
                                        : Modifier.isPublic(f.getModifiers()) ? "public"
                                        : "default";
                                return String.format("%s %s %s",access,
                                    f.getType().getSimpleName(),f.getName());
                            })
                            .forEach(System.out::println);
                    break;
            }

            input = sc.nextLine();
        }


    }
}
