package com.example.productsretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.productsretrofit.allProduct.controller.MainActivity;
import com.example.productsretrofit.favourite.controller.FavoriteProduct;

public class MainScreen extends AppCompatActivity {

    ImageView imageViewMainScrren;
    Button btnAllPro,btnFavPro,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        imageViewMainScrren=findViewById(R.id.imageViewMainScreen);
        imageViewMainScrren.setImageResource(R.drawable.mainscreen);
        btnAllPro=findViewById(R.id.buttonGetAllProduct);
        btnFavPro=findViewById(R.id.buttonFavourite);
        btnExit=findViewById(R.id.buttonExit);

        btnAllPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intentMainScreen=new Intent(MainScreen.this, MainActivity.class);
                 startActivity(intentMainScreen);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    btnFavPro.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentMainScreen=new Intent(MainScreen.this, FavoriteProduct.class);
            startActivity(intentMainScreen);
        }
    });

    }
}