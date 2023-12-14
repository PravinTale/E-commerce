package com.newproject.ecomm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer custId;

    @NotNull(message = "please input a valid firstName")
    @Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$", message = "please input a valid name")
    private String firstName;

    @NotNull(message = "please input a valid lastName")
    @Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$", message = "please input a valid name")
    private String lastName;

    @NotNull(message = "please input a valid mobileNo")
    @Pattern(regexp = "[7896]{1}[0-9]{9}", message = "please input a valid mobileNo")
    private String mobileNo;

    @NotNull(message = "please input a valid email")
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "please input a valid email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<>();
}
