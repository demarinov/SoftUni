package com.example.advquerying.services.impl;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getAllIngredientsById(Long id) {
        return ingredientRepository.getAllById(id);
    }

    @Override
    public List<Ingredient> findByNameStartingWith(String subStr) {
        return ingredientRepository.findByNameStartingWith(subStr);
    }

    @Override
    public List<Ingredient> findByNameInOrderByPriceAsc(List<String> asList) {
        return ingredientRepository.findByNameInOrderByPriceAsc(asList);
    }

    @Override
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public void deleteByName(String name) {

        ingredientRepository.deleteByName(name);
    }

    @Override
    public void increaseByTen() {
        ingredientRepository.increaseByTen();
    }

    @Override
    public void increaseByTenNamed() {
        ingredientRepository.increaseByTenNamed();
    }

    @Override
    public void increaseByTenForNamesNamed(List<String> names) {
        ingredientRepository.increaseByTenForNamesNamed(names);
    }

}
