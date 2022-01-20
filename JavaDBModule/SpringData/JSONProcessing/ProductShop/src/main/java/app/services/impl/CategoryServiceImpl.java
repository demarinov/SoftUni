package app.services.impl;

import app.entites.Category;
import app.repositories.CategoryRepository;
import app.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllById(Long id) {
        return categoryRepository.findAllById(id);
    }

    @Override
    public void saveAll(Category[] categories) {
        categoryRepository.saveAll(Arrays.asList(categories));
    }
}
