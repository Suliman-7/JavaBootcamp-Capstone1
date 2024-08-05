package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Category {

    @NotNull(message = "category ID should be not empty")
    private int id;
    @NotEmpty(message = "category name should be not empty")
    @Size(min = 3 , message = "category name length should be more than 3 characters")
    private String name;
}
