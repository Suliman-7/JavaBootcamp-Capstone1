package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final ProductService productService;






    public ArrayList<MerchantStock> getAllMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock) {

        merchantStocks.add(merchantStock);

    }

    public boolean updateMerchantStock(int id , MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(int id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }


    public String discountProduct(int pid , int mid ,  double discount) {
        double percentage = discount / 100;

        for(MerchantStock merstock : merchantStocks){
            if(merstock.getProductId()==pid && merstock.getMerchantId()==mid){
                for (int i = 0; i < productService.getAllProducts().size(); i++) {
                    if(productService.getAllProducts().get(i).getId() == pid){
                        productService.getAllProducts().get(i).setPrice(productService.getAllProducts().get(i).getPrice()-(percentage*productService.getAllProducts().get(i).getPrice()));
                        return "Product discount successfully";
                    }
                }
            }
        }
        return "invalid prodect id or merchant id";
    }


    public String addStocks(int pid , int mid , int amount) {

        boolean foundMerchant = false;
        int indexMerchant = 0;


        for(int i = 0; i < merchantStocks.size(); i++) {

            if (mid == merchantStocks.get(i).getMerchantId()) {
                foundMerchant = true;
                indexMerchant = i;
            }
        }

        if(! foundMerchant) {
            return "Merchant not found";
        }

        boolean foundProduct = false;

        for(int i = 0; i < productService.getAllProducts().size(); i++) {

            if (pid == productService.getAllProducts().get(i).getId()) {
                foundProduct = true;
            }
        }

        if(! foundProduct) {
            return "Product not found";
        }

        merchantStocks.get(indexMerchant).setStock(merchantStocks.get(indexMerchant).getStock() + amount);
        return "Stock added successfully";
    }



    }



