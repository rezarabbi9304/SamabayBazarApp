package com.example.samabaybazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductApi productApi;

    private List<ProductListModel> productListModelList;

    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

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
       }

    private void getProduct() {

        Call<List<ProductListModel>> call = productApi.getProduct();

        call.enqueue(new Callback<List<ProductListModel>>() {
            @Override
            public void onResponse(Call<List<ProductListModel>> call, Response<List<ProductListModel>> response) {
                if(!response.isSuccessful())
                {
                    Log.d("TAG", "onResponse: ................... "+response.body().size());
                }

                productListModelList = response.body();
                PutDataIntoAdapter(productListModelList);

            }

            @Override
            public void onFailure(Call<List<ProductListModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void PutDataIntoAdapter(List<ProductListModel> productListModelList) {

        adapter = new ProductAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setProductListModelList(productListModelList);
        recyclerView.setAdapter(adapter);

    }
}