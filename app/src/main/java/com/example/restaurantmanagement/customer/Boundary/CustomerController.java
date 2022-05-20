package com.example.restaurantmanagement.customer.Boundary;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.Entity.OrderObject;

import java.util.ArrayList;

public class CustomerController extends RecyclerView.Adapter<CustomerController.ViewHolder> {

    private final ArrayList<OrderObject> courseModelArrayList;

    // Constructor
    public CustomerController(ArrayList<OrderObject> courseModelArrayList) {
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_cardview, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        String priceText;
        OrderObject model = courseModelArrayList.get(position);
        holder.foodName.setText(model.getFoodName());
        holder.foodDesc.setText(model.getFoodDesc());
        priceText = "$" + String.format("%.2f", model.getPrice());
        holder.price.setText(priceText);
        holder.quantity.setText(String.valueOf(model.getQuantity()));

        holder.increaseBtn.setOnClickListener(v -> {
            /// button click event
            model.setQuantity(model.getQuantity()+1);
            System.out.println(model.getQuantity());
            holder.quantity.setText(String.valueOf(model.getQuantity()));
        });

        holder.decreaseBtn.setOnClickListener(v -> {
            /// button click event
            if (model.getQuantity() > 0)
                model.setQuantity(model.getQuantity()-1);
            System.out.println(model.getQuantity());
            holder.quantity.setText(String.valueOf(model.getQuantity()));
        });

    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return courseModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView foodName;
        private final TextView foodDesc;
        private final TextView price;
        private final TextView quantity;
        private final Button increaseBtn, decreaseBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodTitle);
            foodDesc = itemView.findViewById(R.id.foodDesc);
            price = itemView.findViewById(R.id.foodPrice);
            quantity = itemView.findViewById(R.id.foodQuantity);
            increaseBtn = itemView.findViewById(R.id.increaseBtn);
            decreaseBtn = itemView.findViewById(R.id.decreaseBtn);
        }
    }
}
