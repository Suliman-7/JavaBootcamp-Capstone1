package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Merchant {

    @NotNull(message = "Merchant ID should be not empty")
    private int id;
    @NotEmpty(message = "Merchant name should be not empty")
    @Size(min = 3 , message = "Merchant name length should be more than 3 characters")
    private String name;

}
