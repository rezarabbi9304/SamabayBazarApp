package com.example.samabaybazar;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.samabaybazar.db.AppDataBase;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context mContext;
    private List<ProductListModel> productListModelList;
    AppDataBase db;
    Boolean isUpdate = false;
    OnItemClick listener;

    private List<ProductListModel> cartList;

    public ProductAdapter(Context mContext) {
        this.mContext = mContext;
        this.listener = listener;
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.sample_product_layout,null);
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

        if (productListModelList.get(position).productCount > 0){
            holder.counterView.setVisibility(View.VISIBLE);
            holder.btnAddToCart.setVisibility(View.GONE);
        }else{
            holder.counterView.setVisibility(View.GONE);
            holder.btnAddToCart.setVisibility(View.VISIBLE);
        }

        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.counterView.setVisibility(View.VISIBLE);
                holder.btnAddToCart.setVisibility(View.GONE);

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
                holder.setTxtCounter.setText(productCount+"");
                holder.totalAmount.setText(""+(productCount * Integer.parseInt(productListModelList.get(position).getPrice()) ));

                SaveData(product);

            }
        });

        //1st logic add to cart , if exist increase count.
        holder.btnAddTxt.setOnClickListener(new View.OnClickListener() {
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
                holder.setTxtCounter.setText(productCount+"");
                holder.totalAmount.setText(""+(productCount * Integer.parseInt(productListModelList.get(position).getPrice()) ));

                SaveData(product);
            }
        });

        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
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

                if (productCount == 0){
                    holder.counterView.setVisibility(View.GONE);
                    holder.btnAddToCart.setVisibility(View.VISIBLE);
                }else{
                    productCount-=1;
                    product.setProductCount(productCount);
                    holder.setTxtCounter.setText(productCount+"");

                    SaveData(product);
                }
                holder.totalAmount.setText(""+(productCount * Integer.parseInt(productListModelList.get(position).getPrice()) ));

            }
        });



    }

    private void update(ProductListModel product) {
        db.productDao().updateProduct(product);
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
        private TextView btnAddToCart;

        private TextView setName,setPrice,btnAddTxt,setTxtCounter,buttonMinus,totalAmount;
        private ImageView setImage;
        LinearLayout counterView;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageViewProduct);
            productName = itemView.findViewById(R.id.textProductName);
            price = itemView.findViewById(R.id.textPrice);
            productDetails = itemView.findViewById(R.id.textViewDetails);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            btnAddToCart = itemView.findViewById(R.id.addToCart);
            counterView = itemView.findViewById(R.id.layoutCountButton);



            setName = itemView.findViewById(R.id.setProductName);
            setPrice = itemView.findViewById(R.id.setPrice);
            setImage = itemView.findViewById(R.id.imageView);
            setTxtCounter = itemView.findViewById(R.id.setCounter);

            btnAddTxt = itemView.findViewById(R.id.btnAdd);
            buttonMinus = itemView.findViewById(R.id.btnMinus);
            totalAmount = itemView.findViewById(R.id.totalAmount);
        }
    }
}
