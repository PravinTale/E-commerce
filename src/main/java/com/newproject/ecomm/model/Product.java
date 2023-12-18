package com.newproject.ecomm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer prodID;

    @NotNull(message = "ProductName can not be blank")
    private String productName;

    @NotNull(message = "price can not be blank")
    private Double price;

    @NotNull(message = "manufacturer can not be blank")
    private String manufacturer;

    @Min(value = 1, message = "quantity should be minimum one")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

}
