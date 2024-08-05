package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Product {

    @NotNull(message = "id should be not empty")
    private int id;
    @NotEmpty(message = "name should be not empty")
    @Size(min = 3 , message = "name length should be more than 3 characters")
    private String name;
    @NotNull(message = "price should be not empty")
    @Positive(message = "price must be positive number")
    private double price;
    @NotNull(message = "category ID should be not empty")
    private int categoryID;


}
