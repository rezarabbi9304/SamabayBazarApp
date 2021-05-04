package com.example.samabaybazar.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.samabaybazar.ProductListModel;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM ProductListModel")
    List<ProductListModel> getAllAnim();


    @Insert
    Long insetAnim(ProductListModel animModels);

    @Update
    int updateProduct(ProductListModel animModels);

    @Query("SELECT * FROM ProductListModel WHERE id LIKE:pid")
    ProductListModel getAnim(int pid);
}
