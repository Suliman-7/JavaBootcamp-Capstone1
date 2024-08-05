package com.example.capstone1.Service;


import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    ArrayList<User> users = new ArrayList<>();
    private final MerchantStockService merchantStockService;
    private final ProductService productService;


    public ArrayList<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(int id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }


    public String buyProduct(int uid, int pid, int mid) {
        boolean foundUser = false;
        boolean foundProduct = false;
        boolean foundMerchant = false;
        int indexUser = -1;
        int indexProduct = -1;
        int indexMerchant = -1;

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getId() == uid) {
                foundUser = true;
                indexUser = i;
            }
        }

        if (!foundUser) {
            return "User Not Found";
        }

        for (int i = 0; i < productService.getAllProducts().size(); i++) {
            if (productService.getAllProducts().get(i).getId() == pid) {
                foundProduct = true;
                indexProduct = i;
            }
        }

        if (!foundProduct) {
            return "Product Not Found";
        }


        for (int i = 0; i < merchantStockService.getAllMerchantStocks().size(); i++) {
            if (merchantStockService.getAllMerchantStocks().get(i).getMerchantId() == mid) {
                foundMerchant = true;
                indexMerchant = i;
            }
        }

        if (!foundMerchant) {
            return "Merchant Not Found";
        }

        if(users.get(indexUser).getRole().equalsIgnoreCase("Admin")){
            return "You can't buy because you are Admin";
        }



            if (users.get(indexUser).getBalance() < productService.getAllProducts().get(indexProduct).getPrice()) {
                return "insufficient Balance";
            } else {

                if (merchantStockService.getAllMerchantStocks().get(indexMerchant).getStock() <= 0) {
                    return "No Stock Available";
                } else {

                    merchantStockService.getAllMerchantStocks().get(indexMerchant).setStock(merchantStockService.getAllMerchantStocks().get(indexMerchant).getStock() - 1);
                    users.get(indexUser).setBalance(users.get(indexUser).getBalance() - productService.getAllProducts().get(indexProduct).getPrice());


                    users.get(indexUser).getPurchasedProducts().add(productService.getAllProducts().get(indexProduct));
                    return "Buy Success";
                }
            }


    }




    public String reStockProduct(int uid, int pid, int mid) {

        boolean foundUser = false;
        int indexUser = -1;



        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getId() == uid) {
                foundUser = true;
                indexUser = i;
            }
        }

        if (!foundUser) {
            return "User Not Found";
        }

        boolean foundProduct = false;
        int indexProduct = -1;

        for (int i = 0; i < productService.getAllProducts().size(); i++) {
            if (productService.getAllProducts().get(i).getId() == pid) {
                foundProduct = true;
                indexProduct = i;
            }
        }

        if (!foundProduct) {
            return "Product Not Found";
        }

        boolean foundMerchant = false;
        int indexMerchant = -1;


        for (int i = 0; i < merchantStockService.getAllMerchantStocks().size(); i++) {
            if (merchantStockService.getAllMerchantStocks().get(i).getMerchantId() == mid) {
                foundMerchant = true;
                indexMerchant = i;
            }
        }

        if (!foundMerchant) {
            return "Merchant Not Found";
        }


        if(users.get(indexUser).getRole().equalsIgnoreCase("Admin")){
            return "You can't restock because you are Admin";
        }


        for(Product product : users.get(indexUser).getPurchasedProducts()){
            if(product.getId() == pid){
                users.get(indexUser).getPurchasedProducts().remove(product);
                merchantStockService.getAllMerchantStocks().get(indexMerchant).setStock(merchantStockService.getAllMerchantStocks().get(indexMerchant).getStock() + 1);
                users.get(indexUser).setBalance(users.get(indexUser).getBalance() + productService.getAllProducts().get(indexProduct).getPrice());
                return "Restock Success";
            }
        }
        return "Restock Failed, you didn't buy the product";

    }





    public boolean addBalance(int uid , int amount) {
        boolean foundUser = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == uid) {
                users.get(i).setBalance(users.get(i).getBalance() + amount);
                return true;
            }
        }
        return foundUser;
    }


    public String addToShoppingCart(int uid, int pid ) {
        boolean foundUser = false;
        int indexUser = -1;

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getId() == uid) {
                foundUser = true;
                indexUser = i;
            }
        }

        if (!foundUser) {
            return "User Not Found";
        }

        boolean foundProduct = false;
        int indexProduct = -1;


        for (int i = 0; i < productService.getAllProducts().size(); i++) {
            if (productService.getAllProducts().get(i).getId() == pid) {
                foundProduct = true;
                indexProduct = i;
            }
        }

        if (!foundProduct) {
            return "Product Not Found";
        }


        if(users.get(indexUser).getRole().equalsIgnoreCase("Admin")){
            return "You can't add to shopping cart because you are Admin";
        }

        users.get(indexUser).getShoppingCart().add(productService.getAllProducts().get(indexProduct));
        return "Product Added to cart successfully";
    }







    public String checkOut(int uid) {


        boolean foundUser = false;
        int indexUser = -1;

        // check the user id

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == uid) {
                foundUser = true;
                indexUser = i;
            }
        }
        if (!foundUser) {
            return "User Not Found";
        }

        // To ensure the role is customer

        if(users.get(indexUser).getRole().equalsIgnoreCase("Admin")){
            return "You can't buy because you are Admin";
        }

        // check the shopping cart

        if(users.get(indexUser).getShoppingCart().size()==0){
            return "Shopping cart is Empty";
        }

        // check the balance

        int sum = 0 ;
        for(Product product : users.get(indexUser).getShoppingCart()){
            sum += product.getPrice();
            if(sum>users.get(indexUser).getBalance()){
                return "Insufficient Balance";
            }
        }


        for(Product product : users.get(indexUser).getShoppingCart()) {
            users.get(indexUser).setBalance(users.get(indexUser).getBalance()-product.getPrice());
            for (int i = 0; i < merchantStockService.getAllMerchantStocks().size(); i++) {
                if (product.getId() == merchantStockService.getAllMerchantStocks().get(i).getProductId()) {
                    merchantStockService.getAllMerchantStocks().get(i).setStock(merchantStockService.getAllMerchantStocks().get(i).getStock() - 1);
                }
            }
            users.get(indexUser).getPurchasedProducts().add(product);

        }

        for (Product product : users.get(indexUser).getPurchasedProducts()) {
            users.get(indexUser).getShoppingCart().remove(product);
        }



        return "Buy Success";

    }




}
