package com.week1.cashregisterpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManagerActivity extends AppCompatActivity {

Button HistroyBtn,BackBtn,ReStockBtn;
    public static List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        HistroyBtn = findViewById(R.id.historyBtn);
        BackBtn = findViewById(R.id.BackBtn);
        ReStockBtn = findViewById(R.id.reStockBtn);
        List<Product> updated =  (List<Product>) getIntent().getSerializableExtra("UpdatedStock");
        ArrayList<PurchasedProduct> purchasedProducts = (ArrayList<PurchasedProduct>) getIntent().getSerializableExtra("purchasedProducts");
        ArrayList<PurchasedProduct> purchasedProducts1 =  (ArrayList<PurchasedProduct>) getIntent().getSerializableExtra("purchasedProductsHis");
        productList = (List<Product>) getIntent().getSerializableExtra("products");
        HistroyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this,HistoryActivity.class);
                if (purchasedProducts1 == null){
                    intent.putExtra("purchasedProducts", purchasedProducts);
                } else {
                    intent.putExtra("purchasedProducts", purchasedProducts1);
                }


                startActivity(intent);
                finish();
            }
        });
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this,MainActivity.class);
                if (updated != null){
                    intent.putExtra("UpdatedStock",(ArrayList) updated);

                }
                startActivity(intent);
                finish();
            }
        });
        ReStockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this,RestockActivity.class);
                if (updated == null){
                    intent.putExtra("products", (ArrayList) productList);
                } else {
                    intent.putExtra("products",(ArrayList) updated);
                }
                startActivity(intent);
                finish();
            }
        });

    }
}