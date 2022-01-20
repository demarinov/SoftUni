package app.services.impl;

import app.entites.Product;
import app.repositories.ProductRepository;
import app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllById(Long id) {
        return productRepository.findAllById(id);
    }

    @Override
    public void saveAll(Product[] products) {
        productRepository.saveAll(Arrays.asList(products));
    }
}
