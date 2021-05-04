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
    TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_activiaty);

        recyclerView = findViewById(R.id.recyclerViewCart);
        price = findViewById(R.id.textView);

        AppDataBase db = AppDataBase.getINSTANCE(this);
        List<ProductListModel> cartProducts = db.productDao().getAllAnim();
        PutDataIntoAdapter(cartProducts);

        int totalPrice = 0;
        for (ProductListModel product : cartProducts){
            totalPrice = totalPrice + (Integer.parseInt(product.getPrice()) * product.getProductCount());
        }
        price.setText(totalPrice +"");

    }

    private void PutDataIntoAdapter(List<ProductListModel> productListModelList) {

        adapter = new CartAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setProductListModelList(productListModelList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(String value, Boolean add) {
        if (add){
            String p = price.getText().toString();
            price.setText((Integer.parseInt(p) + Integer.parseInt(value)) + "");
        }else{
            String p = price.getText().toString();
            price.setText((Integer.parseInt(p) - Integer.parseInt(value)) + "");
        }

    }
}