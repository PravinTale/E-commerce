package com.newproject.ecomm.service;

import com.newproject.ecomm.model.CategoryEnum;
import com.newproject.ecomm.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService{
    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public String deleteProductById(Integer id) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Optional<Product> getProductById(Integer productId) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(CategoryEnum category) {
        return null;
    }
}
