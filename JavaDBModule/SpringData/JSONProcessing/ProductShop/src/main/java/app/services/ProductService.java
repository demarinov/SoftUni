package app.services;

import app.entites.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllById(Long id);

    void saveAll(Product[] products);
}
