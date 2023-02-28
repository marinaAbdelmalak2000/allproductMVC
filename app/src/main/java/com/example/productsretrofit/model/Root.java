package com.example.productsretrofit.model;

import com.example.productsretrofit.model.Product;

import java.util.ArrayList;

public class Root {
    public ArrayList<Product> products;
    public int total;
    public int skip;
    public int limit;

    public Root(){}

    @Override
    public String toString() {
        return "Root{" +
                "products=" + products +
                ", total=" + total +
                ", skip=" + skip +
                ", limit=" + limit +
                '}';
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Root(ArrayList<Product> products, int total, int skip, int limit) {
        this.products = products;
        this.total = total;
        this.skip = skip;
        this.limit = limit;


    }
}
