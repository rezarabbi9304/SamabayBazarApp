package com.example.samabaybazar;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class ProductListModel{


	@PrimaryKey(autoGenerate = false)
	@SerializedName("id")
	private int id;

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

	@SerializedName("status")
	private String status;


	int productCount;

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


	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setHasOffer(String hasOffer) {
		this.hasOffer = hasOffer;
	}

	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(String status) {
		this.status = status;
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