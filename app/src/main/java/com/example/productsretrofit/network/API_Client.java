package com.example.productsretrofit.network;

import com.example.productsretrofit.model.Product;
import com.example.productsretrofit.model.Root;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.Provider;
import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client {

    API_Service api_service;
    private static final String BASE_URL = "https://dummyjson.com/";
    private static final String TAG = "API_Client";
    ArrayList<Product> arrayListProduct;
    private static API_Client client=null;


    private API_Client(){

    }
    public static API_Client getInstance(){
        if(client==null)
            return new API_Client();
        return client;
    }

    public void enqueueCall(NetwoerkConnection netwoerkConnection) {
        Gson gson = new GsonBuilder().create();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();
        api_service = retrofit.create(API_Service.class);


        //with Rx java
        Single<Root> observableProducts = api_service.getProduct();
        observableProducts.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        item -> netwoerkConnection.onSuccessResulr(item.getProducts()), (e -> e.printStackTrace())
                );
    }
//        Callback<Root> callback=new Callback<Root>() {
//            @Override
//            public void onResponse(Call<Root> call, Response<Root> response) {
//                if (response.isSuccessful()){
//                       netwoerkConnection.onSuccessResulr(response.body().products);
//               }
//            }
//
//            @Override
//            public void onFailure(Call<Root> call, Throwable t) {
//                netwoerkConnection.onFailureResult(t.getMessage());
//            }
//        };
//        Call<Root> root=api_service.getProduct();
//        root.enqueue(callback);
//
//    }


//    Retrofit startCall() {
//
//        Gson gson = new GsonBuilder().create();
//
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
//
//        API_Service api_service =api_client.startCall().create(API_Service.class);
//        Call<Root> root=api_service.getProduct();
//        root.enqueue(new Callback<Root>() {
//            @Override
//            public void onResponse(Call<Root> call, Response<Root> response) {
//                if(response.isSuccessful()&&response.body()!=null){
//                    input =response.body().products;
//                    myAdapter.setData(input);
//                    Log.i(TAG, "onResponse*******************: "+input.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Root> call, Throwable t) {
//                Log.i(TAG, "onFailure: ");
//            }
//        });
//
//        return retrofit;
//    }
}
