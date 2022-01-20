package app.services;

import app.entites.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllById(Long id);

    void saveAll(Category[] categories);
}
