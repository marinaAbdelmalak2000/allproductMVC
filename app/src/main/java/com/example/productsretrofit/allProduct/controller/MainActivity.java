package com.example.productsretrofit.allProduct.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.productsretrofit.db.ProductDAO;
import com.example.productsretrofit.network.API_Client;
import com.example.productsretrofit.network.NetwoerkConnection;
import com.example.productsretrofit.model.Product;
import com.example.productsretrofit.R;
import com.example.productsretrofit.model.Repository;
import com.example.productsretrofit.allProduct.view.MyAdapter;
import com.example.productsretrofit.allProduct.view.OnProductClickLesener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NetwoerkConnection, OnProductClickLesener {

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    List<Product> input =new ArrayList<>();
    MyAdapter myAdapter;

    Product product;

    Repository repo;

     List<Product> products;

     Product p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter=new MyAdapter(this, input,this);

        repo=new Repository(this);

        API_Client api_client =API_Client.getInstance();
        api_client.enqueueCall(this);


        

      //  startConnect();
    }
    
    
//    void startConnect(){
//       API_Service api_service =api_client.startCall().create(API_Service.class);
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
//    }

    @Override
    public void onSuccessResulr(List<Product> products) {
        myAdapter.setData(products);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailureResult(String errMsg) {

        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(Product product) {

        repo.insert(product);


        Toast.makeText(this, "add to favorite", Toast.LENGTH_SHORT).show();
    }

}