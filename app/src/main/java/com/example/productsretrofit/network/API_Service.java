package com.example.productsretrofit.network;


import android.database.Observable;

import com.example.productsretrofit.model.Root;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Service {

@GET("products")
Single<Root> getProduct();
}
