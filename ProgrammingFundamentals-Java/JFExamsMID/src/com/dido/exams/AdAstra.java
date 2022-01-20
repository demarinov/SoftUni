package com.dido.exams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// should be done with regular expression ...
public class AdAstra {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String productInfo = sc.nextLine();

        List<String> products = Arrays.stream(productInfo.split("[#\\|]")).collect(Collectors.toList());

        int itemStep = 0;
        String itemName = "";
        String itemDate = "";
        String itemCalories = "";
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {

            // validation for item
            if (itemStep == 0) {
                if (!isValidItemName(products.get(i))) {

                    itemName = "";
                    itemStep = 0;
                    continue;
                } else {
                    itemName = products.get(i);

                }
            }

            // validation for date
            if (itemStep == 1) {
                if (!isValidDate(products.get(i))) {

                    itemDate = "";
                    itemStep = 0;
                    continue;
                } else {
                    itemDate = products.get(i);
                }
            }

            // validation for calories
            if (itemStep == 2) {
                if (!validCalories(products.get(i))) {

                    itemCalories = "";
                } else {
                    itemCalories = products.get(i);
                    Item item = new Item(itemName, itemDate, itemCalories);
                    items.add(item);
                    itemCalories = "";
                    itemDate = "";
                    itemName = "";
                }

                itemStep = 0;
                continue;
            }

            itemStep++;
        }

//        System.out.println(products.toString().replaceAll("[\\[\\],]",""));
//        System.out.println(items.toString().replaceAll("[\\[\\],]",""));

        int totalCalories = 0;
        for (int i = 0; i < items.size(); i++) {

            if (productInfo.contains(items.get(i).getName())) {
                int startIndex = productInfo.indexOf(items.get(i).getName());
                int endIndex = productInfo.indexOf(items.get(i).getCalories()) + items.get(i).getCalories().length();

                if (startIndex >0 && endIndex < productInfo.length()-1) {
                    char start = productInfo.charAt(startIndex-1);
                    char end = productInfo.charAt(endIndex);

                    if (!((start == '#' && end == '#') || (start == '|' && end == '|'))) {
                        items.remove(i);
                        i--;
                        continue;
                    }
                }
            }

            totalCalories += Integer.parseInt(items.get(i).getCalories());
        }

        System.out.printf("You have food to last you for: %d days!%n",(totalCalories/2000));
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%s%n",items.get(i).toString());
        }

    }

    public static boolean validCalories(String calories) {


        for (int i = 0; i < calories.length(); i++) {
            char c = calories.charAt(i);

            if (!(c >= 48 && c <= 57)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidDate(String date) {

        int countSlashes = 0;
        int countDigits = 0;
        for (int i = 0; i < date.length(); i++) {
            char c = date.charAt(i);

            if (!(c >= 47 && c<=57)) {

                return false;
            } else if (c == 47) {
                countSlashes++;
            } else {
                countDigits++;
            }

        }

        if (countSlashes != 2 || countDigits != 6) {
            return false;
        }

        return true;

    }

    public static boolean isValidItemName(String itemName) {

        if (itemName.equalsIgnoreCase(" ") || itemName.isEmpty()) {
            return false;
        }

        // lowercase/uppercase chars or whitespace;
        String nameLower = itemName.toLowerCase();

        for (int i = 0; i < nameLower.length(); i++) {
            char c = nameLower.charAt(i);

            // space or other whitespace ...???
            if (!(c >= 97 && c <= 122) && (c != ' ')) {
                return false;
            }
        }

        return true;

    }
}

class Item {

    private String name;
    private String date;
    private String calories;

    public Item(String name, String date, String calories) {
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
