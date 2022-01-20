package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> getAllIngredientsById(Long id);

    List<Ingredient> findByNameStartingWith(String subStr);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> asList);

    List<Ingredient> getAll();

    void deleteByName(String name);

    void increaseByTen();

    void increaseByTenNamed();

    void increaseByTenForNamesNamed(List<String> names);
}
