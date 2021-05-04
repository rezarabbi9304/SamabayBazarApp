package com.example.samabaybazar;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.samabaybazar.db.AppDataBase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context mContext;
    private List<ProductListModel> productListModelList;
    AppDataBase db;
    Boolean isUpdate = false;

    private List<ProductListModel> cartList;

    public ProductAdapter(Context mContext) {
        this.mContext = mContext;
        cartList = new ArrayList<>();
         db = AppDataBase.getINSTANCE(mContext);
    }

    public void setProductListModelList(List<ProductListModel> productListModelList) {
        this.productListModelList = productListModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_list_layout,null);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.productName.setText(productListModelList.get(position).getName());
        holder.price.setText(productListModelList.get(position).getPrice());
        holder.productDetails.setText(productListModelList.get(position).getDescription());

        Glide.with(mContext)
                .load(productListModelList.get(position).getImage())
                .into(holder.productImage);

      /*  holder.ratingBar.setRating(productListModelList.get(position));*/
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductListModel product;
                product = db.productDao().getAnim(productListModelList.get(position).getId());
                isUpdate = true;

                if (product == null){
                    product = productListModelList.get(position);
                    isUpdate = false;
                }
                int productCount = product.getProductCount();
                productCount+=1;
                product.setProductCount(productCount);

                SaveData(product);
            }
        });
    }


    public void SaveData(ProductListModel model){
        Toast.makeText(mContext, "Product added to cart",Toast.LENGTH_LONG).show();
        if (isUpdate){
            db.productDao().updateProduct(model);
        }else {
            db.productDao().insetAnim(model);
        }

    }

    @Override
    public int getItemCount() {
        return productListModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productName;
        private TextView price;
        private TextView productDetails;
        private RatingBar ratingBar;
        private Button btnAddToCart;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageViewProduct);
            productName = itemView.findViewById(R.id.textProductName);
            price = itemView.findViewById(R.id.textPrice);
            productDetails = itemView.findViewById(R.id.textViewDetails);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            btnAddToCart = itemView.findViewById(R.id.buttonCart);
        }
    }
}
