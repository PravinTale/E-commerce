package com.newproject.ecomm.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    @NotNull(message = "ProductName can not be blank")
    private String productName;

    @NotNull(message = "price can not be blank")
    private Double price;
}
