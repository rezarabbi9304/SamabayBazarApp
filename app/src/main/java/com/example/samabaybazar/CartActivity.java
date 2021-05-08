package com.example.samabaybazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.samabaybazar.db.AppDataBase;

import java.util.List;

public class CartActivity extends AppCompatActivity implements OnItemClick {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    TextView price,subtotal,shippingCost,GrandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_activiaty);

        recyclerView = findViewById(R.id.recyclerCartView);
        subtotal = findViewById(R.id.setSubTotal);
        shippingCost= findViewById(R.id.setShippingCost);
        GrandTotal= findViewById(R.id.setGrandtotal);




        AppDataBase db = AppDataBase.getINSTANCE(this);
        List<ProductListModel> cartProducts = db.productDao().getAllAnim();
        PutDataIntoAdapter(cartProducts);

        int totalPrice = 0;
        for (ProductListModel product : cartProducts){
            totalPrice = totalPrice + (Integer.parseInt(product.getPrice()) * product.getProductCount());
        }
        setPrice(totalPrice);

    }

    private void setPrice(int totalPrice) {
        subtotal.setText(totalPrice+"");
        shippingCost.setText("20");
        GrandTotal.setText(""+(totalPrice + 20));
    }


    private void PutDataIntoAdapter(List<ProductListModel> productListModelList) {

        adapter = new CartAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setProductListModelList(productListModelList);
        recyclerView.setAdapter(adapter);

    }

    //2nd logic
    @Override
    public void onClick(String value, Boolean add) {
        String p = subtotal.getText().toString();
        if (add){
            int total = Integer.parseInt(p) + Integer.parseInt(value);
            setPrice(total);
        }else{
            int total = Integer.parseInt(p) - Integer.parseInt(value);
            setPrice(total);
        }

    }
}