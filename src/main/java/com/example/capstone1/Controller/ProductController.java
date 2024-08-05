package com.example.capstone1.Controller;


import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/post")
    public ResponseEntity addProduct(@Valid @RequestBody Product product , Errors errors) {
        if (errors.hasErrors()) {
            String messaqe = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(messaqe);
        }
        boolean isAdded = productService.addProduct(product);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Product added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category Id not found"));
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateProduct(@PathVariable int id, @Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String messaqe = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(messaqe);
        }
        boolean isUpdated = productService.updateProduct(id, product);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Product updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }

    @PutMapping("/discount/{pid}/{discount}")
    public ResponseEntity discountProduct(@PathVariable int pid, @PathVariable double discount) {
        boolean isDiscount = productService.discountProduct(pid, discount);
        if (isDiscount) {
            return ResponseEntity.status(200).body(new ApiResponse("Product price discount successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }

    @GetMapping("/getbycategory/{cid}")
    public ResponseEntity getProductByCategory(@PathVariable int cid) {
        ArrayList<Product> products = productService.getProductsByCategory(cid);
        if(products.size()>0) {
            return ResponseEntity.status(200).body(products);
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }



}
