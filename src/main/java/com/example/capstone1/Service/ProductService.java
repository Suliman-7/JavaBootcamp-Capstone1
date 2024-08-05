package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class ProductService {

    ArrayList<Product> products = new ArrayList<>();
    private final CategoryService categoryService;

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public boolean addProduct(Product product) {
        for (Category category : categoryService.getAllCategories()) {
            if(product.getCategoryID()==category.getId()){
                products.add(product);
                return true;
            }
        }
        return false;
    }

    public boolean updateProduct(int id , Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return true;
                 }
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean discountProduct(int pid , double discount) {
        boolean founndProduct = false;
        double percentage = discount / 100;
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == pid){
                products.get(i).setPrice(products.get(i).getPrice()-(percentage*products.get(i).getPrice()));
                founndProduct = true;
            }
        }
        return founndProduct;
    }



    public ArrayList<Product> getProductsByCategory(int cid) {
        ArrayList<Product> productsByCategory = new ArrayList<>();
        for (Product product : products) {
            if(product.getCategoryID()==cid){
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    }

}
