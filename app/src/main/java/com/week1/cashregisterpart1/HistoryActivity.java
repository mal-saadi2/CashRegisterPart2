package com.week1.cashregisterpart1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
HistoryAdapter adapter;
RecyclerView recyclerView;
Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ArrayList<PurchasedProduct> purchasedProducts = (ArrayList<PurchasedProduct>) getIntent().getSerializableExtra("purchasedProducts");
        recyclerView = findViewById(R.id.HistoryRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(HistoryActivity.this,purchasedProducts);
        recyclerView.setAdapter(adapter);

        back = findViewById(R.id.backBtnn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this,ManagerActivity.class);
                intent.putExtra("purchasedProductsHis", purchasedProducts);
                startActivity(intent );
                finish();
            }
        });

    }


}