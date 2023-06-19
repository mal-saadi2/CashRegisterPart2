package com.week1.cashregisterpart1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PurchasedProductAdapter extends ArrayAdapter<PurchasedProduct> {

    public PurchasedProductAdapter(Context context, List<PurchasedProduct> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }
}
