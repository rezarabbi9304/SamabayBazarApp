package com.example.samabaybazar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {

    @GET("products")
    Call<List<ProductListModel>> getProduct();



}
