package com.newproject.ecomm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userId;

    @NotNull(message = "Please enter name")
    private String name;

    @NotNull(message = "Please enter mobile number")
    @Pattern(regexp = "[7896]{1}[0-9]{9}", message = "Input a valid mobile number")
    private String mobile;

    @NotNull(message = "Please enter password")
    private String password;


}
