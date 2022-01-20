package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {

    List<Shampoo> getAllShampoosById(Long id);

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, Label labelId);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findAllByPriceLessThan(BigDecimal price);

    List<Shampoo> findShampoosAndIngredientsInList(List<Ingredient> subStr);

    List<Shampoo> findByIngredientCountLessThan(Long number);
}
