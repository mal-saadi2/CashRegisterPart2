package com.week1.cashregisterpart1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RestockActivity extends AppCompatActivity {

ListView ReStockRv;
    private TextView txtProductName,txtProductQuantity, txtTotalPrice;
    private EditText editTextProductNumber;
    private ProductAdapter adapter;
    public static List<Product> productList,updatedlist;
    private AlertDialog.Builder builder;
    String userInput;
    Intent Back;
    int productNumeber;
    Product selectedProduct;
    double totalPrice;
    String productName;   int productQuantity;
    double productPrice;

    private Button Restock, Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restock);
        ReStockRv = findViewById(R.id.Restockproduct_listview);
        txtProductName = findViewById(R.id.textviewProductName);
        txtProductQuantity = findViewById(R.id.textviewQuantity);
        txtTotalPrice = findViewById(R.id.textviewTotalAmount);
        productList = (List<Product>) getIntent().getSerializableExtra("products");


        Restock = findViewById(R.id.buttonOk);
        Cancel = findViewById(R.id.buttonCancel);
        // Create and set the custom adapter
        adapter = new ProductAdapter(this, productList);
        ReStockRv.setAdapter(adapter);
        ReStockRv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected product from the adapter
                selectedProduct = adapter.getItem(position);

                // Display the product details
                productName = selectedProduct.getName();
                productQuantity = selectedProduct.getQuantity();
                productPrice = selectedProduct.getPrice();
                txtProductName.setText(productName);


            }
        });
        editTextProductNumber = findViewById(R.id.number_picker);
        editTextProductNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Handle the action here
                    userInput = editTextProductNumber.getText().toString();
                    // Do something with the entered text


                        txtProductQuantity.setText(userInput);
                        productNumeber = Integer.parseInt(userInput);
                        totalPrice = productNumeber*productPrice;
                        txtTotalPrice.setText(String.valueOf(totalPrice));


                    return true;
                }
                return false;
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back = new Intent(RestockActivity.this,ManagerActivity.class);
                Back.putExtra("UpdatedStock",(ArrayList) productList);
                startActivity(Back);
            }
        });
        builder = new AlertDialog.Builder(this);

        Restock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput = editTextProductNumber.getText().toString();
                productNumeber = Integer.parseInt(userInput);
                totalPrice = productNumeber*productPrice;
                if (editTextProductNumber.getText().toString().equals("") || selectedProduct == null)
                    Toast.makeText(RestockActivity.this, "Please Select Product and Enter Product Number", Toast.LENGTH_SHORT).show();



                else {
                    //Uncomment the below code to Set the message and title from the strings.xml file
                    builder.setMessage("Are you confirm?").setTitle("Confirm Addtion");

                    //Setting message manually and performing action on button click
                    builder.setMessage("Do you want to confirm this Addtion?\nYour New Stock entered is " + userInput + " " + productName + " for " + String.valueOf(totalPrice))
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    selectedProduct.setQuantity(productQuantity + productNumeber);
                                    //  Toast.makeText(MainActivity.this, purchasedProducts.get(0).getName(), Toast.LENGTH_SHORT).show();
                                    adapter.notifyDataSetChanged();

                                    editTextProductNumber.setText("");

                                    txtProductQuantity.setText("Total Qunatity");
                                    txtTotalPrice.setText("Total Price");
                                    txtProductName.setText("Selected Product");



                                    Toast.makeText(RestockActivity.this, "Thanks for Purchasing!", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    editTextProductNumber.setText("");
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Confirm Purchase");
                    alert.show();
                }
            }
        });
    }

}