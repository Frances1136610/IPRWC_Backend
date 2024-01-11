package com.example.iprwc_backend.daos;

import com.example.iprwc_backend.models.Product;
import com.example.iprwc_backend.services.NewIdService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductDao {
    private final ProductRepository productRepository;
    private final NewIdService newIdService;

    public ProductDao(ProductRepository productRepository, NewIdService newIdService){
        this.productRepository = productRepository;
        this.newIdService = newIdService;
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

    public int giveNewProductId() {
        ArrayList<Product> products = (ArrayList<Product>) this.productRepository.findAll();
        return this.newIdService.newProductId(products);
    }
}