package com.newproject.ecomm.service;

import com.newproject.ecomm.model.CategoryEnum;
import com.newproject.ecomm.model.Product;
import com.newproject.ecomm.model.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    // Create a new product
    public Product addProduct(Product product);

    // Delete a product by ID
    public String deleteProductById(Integer id);

    // Update an existing product
    public Product updateProduct(Product product);

    // Get a product by ID
    Optional<Product> getProductById(Integer productId);

    // Get all products
    List<Product> getAllProducts();

    // Find products by category
    List<Product> getProductsByCategory(CategoryEnum category);


}
