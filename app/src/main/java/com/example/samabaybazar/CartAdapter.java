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

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context mContext;
    private List<ProductListModel> productListModelList;
    OnItemClick listener;
    AppDataBase db;


    private List<ProductListModel> cartList;

    public CartAdapter(Context mContext, OnItemClick listener) {
        this.mContext = mContext;
        this.listener = listener;
        db = AppDataBase.getINSTANCE(mContext);


    }

    public void setProductListModelList(List<ProductListModel> productListModelList) {
        this.productListModelList = productListModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.sample_cart_product_layout,null);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.setName.setText(productListModelList.get(position).getName());
        holder.setPrice.setText(productListModelList.get(position).getPrice());

        Glide.with(mContext)
                .load(productListModelList.get(position).getImage())
                .into(holder.setImage);

        holder.setTxtCounter.setText(""+productListModelList.get(position).getProductCount());
        holder.toatlAmount.setText(""+productListModelList.get(position).getProductCount() * Integer.parseInt(productListModelList.get(position).getPrice()));

        holder.btnAddTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(productListModelList.get(position).getPrice(), true);
                int c = Integer.parseInt(holder.setTxtCounter.getText().toString());
                c+=1;
                holder.setTxtCounter.setText(""+ c);
                holder.toatlAmount.setText("" + c * Integer.parseInt(productListModelList.get(position).getPrice()));


                //add to database
                ProductListModel product;
                product = db.productDao().getAnim(productListModelList.get(position).getId());


                int productCount = product.getProductCount();
                productCount+=1;
                product.setProductCount(productCount);


                SaveData(product);

            }
        });


        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(productListModelList.get(position).getPrice(), false);

                int c = Integer.parseInt(holder.setTxtCounter.getText().toString());

                ProductListModel product;
                product = db.productDao().getAnim(productListModelList.get(position).getId());

                if (c == 1){
                    db.productDao().deleteProduct(productListModelList.get(position));
                    productListModelList.remove(productListModelList.get(position));
                    notifyDataSetChanged();

                }else{
                    c-=1;
                    holder.setTxtCounter.setText(""+ c);
                    holder.toatlAmount.setText("" + c * Integer.parseInt(productListModelList.get(position).getPrice()));

                }

                int productCount = product.getProductCount() ;
                productCount-=1;
                product.setProductCount(productCount);


                SaveData(product);


            }
        });


        /*holder.productName.setText(productListModelList.get(position).getName());
        holder.price.setText(productListModelList.get(position).getPrice());
        holder.productDetails.setText(productListModelList.get(position).getDescription());

        Glide.with(mContext)
                .load(productListModelList.get(position).getImage())
                .into(holder.productImage);*/





    /*  holder.count.setText(""+productListModelList.get(position).getProductCount());*/


      //passing value from adapter to activity through interface

     /* holder.plus.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              listener.onClick(productListModelList.get(position).getPrice(), true);
              String c = holder.count.getText().toString();
              holder.count.setText(""+(Integer.parseInt(c) + 1));
          }
      });*/

       /* holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(productListModelList.get(position).getPrice(), false);

                String c = holder.count.getText().toString();
                holder.count.setText(""+(Integer.parseInt(c) - 1));
            }
        });*/
    }


    public void SaveData(ProductListModel model){

            db.productDao().updateProduct(model);


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


        private TextView setName,setPrice,btnAddTxt,setTxtCounter, buttonMinus,toatlAmount;
        private ImageView setImage;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageViewProduct);
            productName = itemView.findViewById(R.id.textProductName);
            price = itemView.findViewById(R.id.textPrice);
            productDetails = itemView.findViewById(R.id.textViewDetails);

            count = itemView.findViewById(R.id.count);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);



            setName = itemView.findViewById(R.id.setProductName);
            setPrice = itemView.findViewById(R.id.setPrice);
            setImage = itemView.findViewById(R.id.imageView);
            setTxtCounter = itemView.findViewById(R.id.setCounter);
            buttonMinus = itemView.findViewById(R.id.btnMinus);
            btnAddTxt = itemView.findViewById(R.id.btnAdd);
            toatlAmount = itemView.findViewById(R.id.setTotalAmount);

        }
    }
}
