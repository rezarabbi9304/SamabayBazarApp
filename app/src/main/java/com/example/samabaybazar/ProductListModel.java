package com.example.samabaybazar;

import com.google.gson.annotations.SerializedName;

public class ProductListModel{

	@SerializedName("image")
	private String image;

	@SerializedName("brandName")
	private String brandName;

	@SerializedName("has_offer")
	private String hasOffer;

	@SerializedName("actual_price")
	private String actualPrice;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("product_code")
	private String productCode;

	@SerializedName("brand_id")
	private String brandId;

	@SerializedName("subCategoryName")
	private String subCategoryName;

	@SerializedName("unit")
	private String unit;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("sub_category_id")
	private String subCategoryId;

	@SerializedName("price")
	private String price;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

	public String getImage(){
		return image;
	}

	public String getBrandName(){
		return brandName;
	}

	public String getHasOffer(){
		return hasOffer;
	}

	public String getActualPrice(){
		return actualPrice;
	}

	public String getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getProductCode(){
		return productCode;
	}

	public String getBrandId(){
		return brandId;
	}

	public String getSubCategoryName(){
		return subCategoryName;
	}

	public String getUnit(){
		return unit;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getSubCategoryId(){
		return subCategoryId;
	}

	public String getPrice(){
		return price;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ProductListModel{" + 
			"image = '" + image + '\'' + 
			",brandName = '" + brandName + '\'' + 
			",has_offer = '" + hasOffer + '\'' + 
			",actual_price = '" + actualPrice + '\'' + 
			",description = '" + description + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",product_code = '" + productCode + '\'' + 
			",brand_id = '" + brandId + '\'' + 
			",subCategoryName = '" + subCategoryName + '\'' + 
			",unit = '" + unit + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",sub_category_id = '" + subCategoryId + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}