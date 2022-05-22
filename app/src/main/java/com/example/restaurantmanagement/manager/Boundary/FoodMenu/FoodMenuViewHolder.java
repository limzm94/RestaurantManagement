package com.example.restaurantmanagement.manager.Boundary.FoodMenu;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.Entity.FoodObject;
import com.example.restaurantmanagement.manager.Controller.FoodMenu.DeleteFoodMenu;

import java.util.ArrayList;

public class FoodMenuViewHolder extends RecyclerView.Adapter<FoodMenuViewHolder.ViewHolder> {
    private final Context context;
    private final ArrayList<FoodObject> foodModelArrayList;

    // Constructor
    public FoodMenuViewHolder(Context context, ArrayList<FoodObject> foodModelArrayList) {
        this.context = context;
        this.foodModelArrayList = foodModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_cardview, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        String priceText;
        FoodObject model = foodModelArrayList.get(position);
        DeleteFoodMenu deleteFoodMenu = new DeleteFoodMenu(context);
        holder.foodName.setText(model.getFoodName());
        holder.foodDesc.setText(model.getFoodDesc());
        priceText = "$" + String.format("%.2f", model.getPrice());
        holder.price.setText(priceText);

        holder.editBtn.setOnClickListener(v -> {
            // send the account info to edit account activity
            Intent intent = new Intent(context, EditFoodItem.class);
            intent.putExtra("foodKey", model.getFoodKey());
            intent.putExtra("foodName", model.getFoodName());
            intent.putExtra("foodDesc", model.getFoodDesc());
            intent.putExtra("price", model.getPrice());
            context.startActivity(intent);
        });

        holder.deleteBtn.setOnClickListener(v -> {
            deleteFoodMenu.insertFoodMenu(model.getFoodKey());
            foodModelArrayList.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            notifyItemRangeChanged(holder.getAdapterPosition(), foodModelArrayList.size());
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return foodModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView foodName;
        private final TextView foodDesc;
        private final TextView price;
        private final Button editBtn;
        private final Button deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodTitle);
            foodDesc = itemView.findViewById(R.id.foodDesc);
            price = itemView.findViewById(R.id.foodPrice);
            editBtn = itemView.findViewById(R.id.editFoodBtn);
            deleteBtn = itemView.findViewById(R.id.deleteFoodBtn);
        }
    }
}

