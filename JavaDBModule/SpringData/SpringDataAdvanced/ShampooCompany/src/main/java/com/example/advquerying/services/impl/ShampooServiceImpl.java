package com.example.advquerying.services.impl;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> getAllShampoosById(Long id) {
        return shampooRepository.getAllById(id);
    }

    @Override
    public List<Shampoo> findAllBySizeOrderById(Size size) {
        return shampooRepository.findAllBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, Label label) {
        return shampooRepository.findAllBySizeOrLabelOrderByPriceAsc(size, label);
    }

    @Override
    public List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public List<Shampoo> findAllByPriceLessThan(BigDecimal price) {
        return shampooRepository.findAllByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> findShampoosAndIngredientsInList(List<Ingredient> subStr) {
        return shampooRepository.findShampoosAndIngredientsInList(subStr);
    }

    @Override
    public List<Shampoo> findByIngredientCountLessThan(Long number) {
        return shampooRepository.findByIngredientCountLessThan(number);
    }


}
