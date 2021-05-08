package com.example.samabaybazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.samabaybazar.db.AppDataBase;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnItemClick  {

    private RecyclerView recyclerView;
    private ProductApi productApi;
    private List<ProductListModel> productListModelList;
    private ProductAdapter adapter;
    private TextView totalAmount;
    AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDataBase.getINSTANCE(this);

        recyclerView = findViewById(R.id.recView);

        totalAmount = findViewById(R.id.setTotalAmount);

        productListModelList = new ArrayList<>();

     /*   http://shamabaybazar.cccul.com/api/products*/

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://shamabaybazar.cccul.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

             productApi = retrofit.create(ProductApi.class);

        getProduct();




   /*     AppDataBase db = AppDataBase.getINSTANCE(this);
        List<ProductListModel> cartProducts = db.productDao().getAllAnim();


        int totalPrice = 0;
        for (ProductListModel product : cartProducts){
            totalPrice = totalPrice + (Integer.parseInt(product.getPrice()) * product.getProductCount());
        }
        setPrice(totalPrice);*/

       }

    @Override
    public void onClick(String value, Boolean add) {
        String p = totalAmount.getText().toString();
        if (add){
            int total = Integer.parseInt(p) + Integer.parseInt(value);
            setPrice(total);
        }else{
            int total = Integer.parseInt(p) - Integer.parseInt(value);
            setPrice(total);
        }

    }

    private void setPrice(int totalPrice) {
        totalAmount.setText(totalPrice);
    }

    private void getProduct() {


        Call<ProductResponse> call = productApi.getProduct();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful())
                {
                    productListModelList = response.body().getProducts();
                    PutDataIntoAdapter(productListModelList);

                  /*  productListModelList = response.body().getProducts();
                    AppDataBase db = AppDataBase.getINSTANCE(MainActivity.this);
                    for (ProductListModel model : productListModelList){
                        db.productDao().insetAnim(model);
                    }
                    List<ProductListModel> cartProducts = db.productDao().getAllAnim();
                    PutDataIntoAdapter(cartProducts);*/
                }

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<ProductListModel> cartProducts = db.productDao().getAllAnim();

        int totalPrice = 0;
        for (ProductListModel product : cartProducts){
            totalPrice = totalPrice + (Integer.parseInt(product.getPrice()) * product.getProductCount());
        }
        totalAmount.setText(""+totalPrice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.cart:
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    private void PutDataIntoAdapter(List<ProductListModel> productListModelList) {

        adapter = new ProductAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setProductListModelList(productListModelList);
        recyclerView.setAdapter(adapter);

    }
}