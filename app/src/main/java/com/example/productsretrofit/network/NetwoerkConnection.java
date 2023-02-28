package com.example.productsretrofit.network;

import com.example.productsretrofit.model.Product;

import java.util.List;

public interface NetwoerkConnection {
    public void onSuccessResulr(List<Product> products);
    public void onFailureResult(String errMsg);
}
