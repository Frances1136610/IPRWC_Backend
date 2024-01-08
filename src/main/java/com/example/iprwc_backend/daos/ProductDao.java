package com.example.iprwc_backend.daos;

import com.example.iprwc_backend.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductDao {
    private final ProductRepository productRepository;

    public ProductDao(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void saveToDatabase(Product product) {
        this.productRepository.save(product);
    }

    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) this.productRepository.findAll();
    }

    public void deleteProductFromDatabase(long id) {
        this.productRepository.deleteById(id);
    }
}
