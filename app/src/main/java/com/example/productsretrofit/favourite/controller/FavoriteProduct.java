package com.example.productsretrofit.favourite.controller;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.productsretrofit.model.Product;
import com.example.productsretrofit.R;
import com.example.productsretrofit.model.Repository;
import com.example.productsretrofit.favourite.view.FavouritAdapter;
import com.example.productsretrofit.favourite.view.OnFavouriteClickLisener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteProduct extends AppCompatActivity implements OnFavouriteClickLisener {

    RecyclerView recyclerViewFavourit;
    FavouritAdapter myAdapter;
    List<Product> inputFav =new ArrayList<>();

   // LiveData<List<Product>> productsFav =MainActivity.products;

    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_product);

        recyclerViewFavourit=findViewById(R.id.recycleFavourite);
        recyclerViewFavourit.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewFavourit.setLayoutManager(layoutManager);
        myAdapter=new FavouritAdapter(this, inputFav,this);
        repo=new Repository(this);
//        repo.getStoreProduct().observe(this, new Observer<List<Product>>() {
//            @Override
//            public void onChanged(List<Product> products) {
//                myAdapter.setData(products);
//                myAdapter.notifyDataSetChanged();
//            }
//        });


        repo.getStoreProduct().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        Log.i(TAG, "onNext: getAllProdects");
                        myAdapter.setData(products);
                        myAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

       // myAdapter.notifyDataSetChanged();
        recyclerViewFavourit.setAdapter(myAdapter);
    }

    @Override
    public void onClick(Product product) {
        repo.delete(product);
        myAdapter.notifyDataSetChanged();
    }
}