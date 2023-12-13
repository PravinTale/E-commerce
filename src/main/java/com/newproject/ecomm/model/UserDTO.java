package com.newproject.ecomm.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class UserDTO {
    @NotNull(message = "Please enter mobile")
    @Pattern(regexp = "[7896]{1}[0-9]{9}", message = "Input a valid mobile number")
    private String mobile;

    @NotNull(message = "Please enter password")
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$\n", message = "Input a valid password")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$", message = "Input a valid password")

    private String password;
}

