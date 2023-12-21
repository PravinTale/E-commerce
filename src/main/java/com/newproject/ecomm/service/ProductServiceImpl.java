package com.newproject.ecomm.service;

import com.newproject.ecomm.exceptions.ProductNotFoundException;
import com.newproject.ecomm.exceptions.ProductServiceException;
import com.newproject.ecomm.model.CategoryEnum;
import com.newproject.ecomm.model.Product;
import com.newproject.ecomm.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productRepo;
    @Override
    public Product addProduct(Product product) {
        try {
            return productRepo.save(product);
        } catch (Exception e) {
            // Log the exception
            throw new ProductServiceException("Error creating product", e);
        }
    }
    @Override
    public void deleteProductById(Integer id) throws ProductNotFoundException {
        try {
            if (productRepo.existsById(id)) {
                productRepo.deleteById(id);
            } else {
                throw new ProductNotFoundException("Product not found for ID: " + id);
            }
        } catch (Exception e) {
            // Log the exception
            throw new ProductServiceException("Error deleting product", e);
        }
    }

    @Override
    public Product updateProduct(Product product) {
        try {
            if (productRepo.existsById(product.getProdID())) {
                return productRepo.save(product);
            } else {
                throw new ProductNotFoundException("Product not found for ID: " + product.getProdID());
            }
        } catch (Exception e) {
            // Log the exception
            throw new ProductServiceException("Error updating product", e);
        }
    }

    @Override
    public Optional<Product> getProductById(Integer productId) {
        try {
            return productRepo.findById(productId);
        } catch (Exception e) {
            // Log the exception
            throw new ProductServiceException("Error retrieving product by ID", e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return productRepo.findAll();
        } catch (Exception e) {
            // Log the exception
            throw new ProductServiceException("Error retrieving all products", e);
        }
    }

    @Override
    public List<Product> getProductsByCategory(CategoryEnum category) {
        try {
            return productRepo.getCategoryWiseProducts(category);
        } catch (Exception e) {
            // Log the exception
            throw new ProductServiceException("Error retrieving products by category", e);
        }
    }
}
