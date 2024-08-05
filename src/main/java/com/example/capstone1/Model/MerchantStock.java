package com.example.capstone1.Model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotNull(message = "Merchant Stock Id should be not empty")
    private int id;
    @NotNull(message = "product Id should be not empty")
    private int productId;
    @NotNull(message = "Merchant Id should be not empty")
    private int merchantId;
    @NotNull(message = "stock should be not empty")
    @Min(value = 11 , message = "stock should be initialize at least 10")
    private int stock;

}
