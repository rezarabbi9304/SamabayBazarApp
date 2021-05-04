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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.samabaybazar.db.AppDataBase;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context mContext;
    private List<ProductListModel> productListModelList;
    OnItemClick listener;

    private List<ProductListModel> cartList;

    public CartAdapter(Context mContext, OnItemClick listener) {
        this.mContext = mContext;
        this.listener = listener;

    }

    public void setProductListModelList(List<ProductListModel> productListModelList) {
        this.productListModelList = productListModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cart_layout,null);
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

      holder.count.setText(""+productListModelList.get(position).getProductCount());

      holder.plus.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              listener.onClick(productListModelList.get(position).getPrice(), true);
              String c = holder.count.getText().toString();
              holder.count.setText(""+(Integer.parseInt(c) + 1));
          }
      });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(productListModelList.get(position).getPrice(), false);

                String c = holder.count.getText().toString();
                holder.count.setText(""+(Integer.parseInt(c) - 1));
            }
        });
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
        private Button count,plus, minus;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageViewProduct);
            productName = itemView.findViewById(R.id.textProductName);
            price = itemView.findViewById(R.id.textPrice);
            productDetails = itemView.findViewById(R.id.textViewDetails);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            count = itemView.findViewById(R.id.count);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
        }
    }
}
