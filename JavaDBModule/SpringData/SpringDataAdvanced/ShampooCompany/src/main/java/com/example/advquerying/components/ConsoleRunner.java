package com.example.advquerying.components;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.LabelService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private ShampooService shampooService;
    private IngredientService ingredientService;
    private LabelService labelService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService, LabelService labelService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
        this.labelService = labelService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Shampoo> shampoos = shampooService.getAllShampoosById(1L);

        // 1.	Select Shampoos by Size
//        printSelectShampoosBySize();

        // 2.	Select Shampoos by Size or Label
//        printSelectShampoosBySizeOrLabel();

        // 3.	Select Shampoos by Price
//        printSelectShampoosByPrice();

        // 4.	Select Ingredients by Name
//        printSelectIngredientsByName();

        // 5.	Select Ingredients by Names 2
//        printSelectIngredientsByNameContained();

        // 6.	Count Shampoos by Price
//        printCountShampoosByPrice();

        // JPQL Querying
        //7.	Select Shampoos by Ingredients
//        printShampoosAndIngredientsInList();

        // 8.	Select Shampoos by Ingredients Count
//        printSelectShampoosByIngredientCount();

        // 9.	Delete Ingredients by Name
//        deleteIngredientsByName();

        // 10.	Update Ingredients by Price
//        updateIngredientsByPrice();

        // 11.	Update Ingredients by Names
        updateIngredientsByPriceWithNames();
    }

    private void updateIngredientsByPriceWithNames() {

        String[] names = new String[]{"Lavender","Nettle","Herbs"};
        ingredientService.increaseByTenForNamesNamed(Arrays.asList(names));
    }

    private void updateIngredientsByPrice() {

//        ingredientService.increaseByTen();
        ingredientService.increaseByTenNamed();
    }

    private void deleteIngredientsByName() {

        Scanner sc = new Scanner(System.in);

        String name = sc.nextLine();

        ingredientService.deleteByName(name);
    }

    private void printSelectShampoosByIngredientCount() {

        Scanner sc = new Scanner(System.in);

        Long number = Long.parseLong(sc.nextLine());

        List<Shampoo> shampoos = shampooService.findByIngredientCountLessThan(number);

        shampoos.forEach(s -> System.out.println(s.getBrand()));
    }

    private void printShampoosAndIngredientsInList() {

        String[] subStr = new String[] {"Berry","Mineral-Collagen"};
        List<Shampoo> shampoos = null;

        List<Ingredient> ingredients = ingredientService.getAll();

        List<Ingredient> ingredientsInList = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            if (Arrays.stream(subStr).
                    filter(e -> ingredient.getName().equals(e)).findFirst().orElse(null) != null) {
               ingredientsInList.add(ingredient);
            }
        }

        shampoos = shampooService.findShampoosAndIngredientsInList(ingredientsInList);

        shampoos.forEach(s -> System.out.println(s.getBrand()));
    }

    private void printCountShampoosByPrice() {
        Scanner sc = new Scanner(System.in);

        BigDecimal price = new BigDecimal(sc.nextLine());
        List<Shampoo> shampoos = null;

        if (price != null) {
            shampoos = shampooService.findAllByPriceLessThan(price);

        }

        if (shampoos != null) {
            System.out.printf("%d%n",shampoos.size());
        }

    }

    private void printSelectIngredientsByNameContained() {


        String[] subStr = new String[] {"Lavender","Herbs","Apple"};
        List<Ingredient> ingredients = null;

        ingredients = ingredientService.findByNameInOrderByPriceAsc(Arrays.asList(subStr));

        ingredients.forEach(s -> System.out.println(s.getName()));

    }

    private void printSelectIngredientsByName() {
        Scanner sc = new Scanner(System.in);

        String subStr = sc.nextLine();
        List<Ingredient> ingredients = null;

        if (subStr != null) {
            ingredients = ingredientService.findByNameStartingWith(subStr);
        }

        ingredients.forEach(s -> System.out.println(s.getName()));
    }

    private void printSelectShampoosByPrice() {
        Scanner sc = new Scanner(System.in);

        BigDecimal price = new BigDecimal(sc.nextLine());
        List<Shampoo> shampoos = null;

        if (price != null) {
            shampoos = shampooService.findAllByPriceGreaterThanOrderByPriceDesc(price);

        }

        if (shampoos != null) {
            shampoos.forEach(s -> System.out.printf("%s %s %slv.%n",s.getBrand(), s.getSize(),s.getPrice()));
        }
    }

    private void printSelectShampoosBySizeOrLabel() {
        Scanner sc = new Scanner(System.in);

        String sizeStr = sc.nextLine();
        Long labelId = Long.parseLong(sc.nextLine());
        Size size = null;
        List<Shampoo> shampoos = null;

        switch(sizeStr.toUpperCase()) {

            case "MEDIUM":
                size = Size.MEDIUM;
                break;
            case "SMALL":
                size = Size.SMALL;
                break;
            case "LARGE":
                size = Size.LARGE;
                break;
        }

        if (size != null) {
            Label label = labelService.getAllLabelsById(labelId).get(0);
            shampoos = shampooService.findAllBySizeOrLabelOrderByPriceAsc(size, label);

        }

        if (shampoos != null) {
            shampoos.forEach(s -> System.out.printf("%s %s %slv.%n",s.getBrand(), s.getSize(),s.getPrice()));
        }
    }

    private void printSelectShampoosBySize() {
        Scanner sc = new Scanner(System.in);

        String sizeStr = sc.nextLine();
        Size size = null;
        List<Shampoo> shampoos = null;

        switch(sizeStr.toUpperCase()) {

            case "MEDIUM":
                size = Size.MEDIUM;
                break;
            case "SMALL":
                size = Size.SMALL;
                break;
            case "LARGE":
                size = Size.LARGE;
                break;
        }

        if (size != null) {
           shampoos = shampooService.findAllBySizeOrderById(size);

        }

        if (shampoos != null) {
            shampoos.forEach(s -> System.out.printf("%s %s %slv.%n",s.getBrand(), s.getSize(),s.getPrice()));
        }

    }
}
