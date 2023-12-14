package com.newproject.ecomm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addId;

    @NotNull(message = "please fill locality")
    private String locality;

    @NotNull(message = "please fill city")
    private String city;

    @NotNull(message = "please fill state")
    private String state;

    @NotNull(message = "please fill country")
    private String country;

    @NotNull
    @Pattern(regexp = "([1-9]{1}[0-9]{5}|[1-9]{1}[0-9]{3}\\\\s[0-9]{3})", message = "please input a valid pincode")
    private String pinCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
