package com.example.samabaybazar;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
	@SerializedName("products")
	private List<ProductListModel> products;

	public List<ProductListModel> getProducts(){
		return products;
	}
}