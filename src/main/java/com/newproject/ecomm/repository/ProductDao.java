package com.newproject.ecomm.repository;

import com.newproject.ecomm.model.CategoryEnum;
import com.newproject.ecomm.model.Product;
import com.newproject.ecomm.model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    // Find products by name
    public List<Product>  findByProductName(String productName);

    // Find products by price less than a given value
    @Query("SELECT p FROM Product p WHERE p.price < :maxPrice")
    List<Product> findByPriceLessThan(Double maxPrice);

    // Find products by category
    @Query("SELECT p FROM Product p WHERE p.category = :category\n")
    public List<ProductDTO> getCategoryWiseProducts(CategoryEnum category);
}
