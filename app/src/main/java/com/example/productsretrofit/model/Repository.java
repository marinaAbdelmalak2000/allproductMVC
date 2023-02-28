package com.example.productsretrofit.model;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.productsretrofit.db.AppDataBase;
import com.example.productsretrofit.db.ProductDAO;
import com.example.productsretrofit.model.Product;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {

    private Context context;
    private ProductDAO productDAO;
//    private LiveData<List<Product>> storeProduct;

    private Observable<List<Product>> storeProduct;
    private LiveData<List<Integer> >storeProductById;
    private LiveData<List<Product>> storeProductByTitle;

    boolean check=true;

    public Repository(Context context) {
        this.context = context;
        AppDataBase db= AppDataBase.getInstance(context.getApplicationContext());
        productDAO=db.productDAO();
        storeProduct=productDAO.getAllProdects();
        storeProductById=productDAO.getAllProdectsById();
    }

    //get product from db
//    public LiveData<List<Product>> getStoreProduct(){
//        return storeProduct;
//    }

    //with RX java
    public Observable<List<Product>> getStoreProduct(){
        return storeProduct;
    }



    public LiveData<List<Integer>> getStoreProductById(){
        return storeProductById;
    }
//    public List<Integer> getStoreProductById (){
//        new Thread(new Runnable(){
//            public void run(){
//                Log.i(TAG, "runnnnnnnn: ");
//            }
//        }).start();
//        return storeProductById;
//    }

    public LiveData<List<Product>> getStoreProductByTitle(String title){
        storeProductByTitle=productDAO.getProductByTitle(title);
        return storeProductByTitle;
    }

//    public void delete (Product product){
//        new Thread(new Runnable(){
//            public void run(){
//
//                productDAO.deleteProduct(product);
//
//            }
//        }).start();
//    }

//    public void insert(Product product){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
////                for(int i=0;i<storeProductById;i++){
////                    if(product.getId()==storeProductById.get(i)){
////                        Log.i(TAG, "run aredy in fav: ");
////                    }
////                }
//                productDAO.insertProduct(product);
//
//            }
//        }).start();
//    }


    //with RX java
    public void insert(Product product){
        productDAO.insertProduct(product).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "onError: ");
            }
        });
    }

    public void delete(Product product){
        productDAO.deleteProduct(product).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "onSubscribe delete: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete delete: ");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "onError: ");
            }
        });
    }



}
