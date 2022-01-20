package com.dido.exams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AdAstraRegex {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String productInfo = sc.nextLine();

        List<String> products = new ArrayList<>();

        Pattern pattern = Pattern.compile("([#|]\\s*?[a-zA-Z ]+\\s*?)[#|]([0-9]{2}/[0-9]{2}/[0-9]{2})[#|]([0-9]*?[#|])");
        Matcher matcher = pattern.matcher(productInfo);

        List<ProductItem> items = new ArrayList<>();
        int totalCalories = 0;
        while(matcher.find()) {
            String name = matcher.group(1);
            String date = matcher.group(2);
            String calories = matcher.group(3);
            char start = name.charAt(0);
            char end = calories.charAt(calories.length()-1);

            if (start != end) {
                continue;
            } else {
                name = name.substring(1);
                calories = calories.substring(0,calories.length()-1);
            }

            ProductItem item = new ProductItem(name, date, calories);
            items.add(item);
            totalCalories += Integer.parseInt(calories);
            //System.out.println(name+" "+date + " "+calories);
        }

        System.out.printf("You have food to last you for: %d days!%n",(totalCalories/2000));
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%s%n",items.get(i).toString());
        }

    }
}

class ProductItem {

    private String name;
    private String date;
    private String calories;

    public ProductItem(String name, String date, String calories) {
        this.name = name;
        this.date = date;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Item: "+name+", Best before: "+date+", Nutrition: "+calories;
    }
}
