package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {

    @NotNull(message = "id should be not empty")
    private int id;
    @NotEmpty(message = "username should be not empty")
    @Size(min = 6 , message = "username length should be more than 5 characters")
    private String username;
    @NotEmpty(message = "password should be not empty")
    @Size(min = 7 , message = "password length should be more than 6 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "password should have letters and digits")
    private String password;
    @Email
    @NotEmpty(message = "email should be not empty")
    private String email;
    @NotEmpty(message = "role should be not empty")
    @Pattern(regexp = "^(Admin|Customer)" , message = "role should be either admin or customer")
    private String role;
    @NotNull(message = "balance should be not empty")
    @Positive(message = "balance should be positive")
    private double balance;

    private ArrayList<Product> purchasedProducts;

    private ArrayList<Product> shoppingCart;
}
