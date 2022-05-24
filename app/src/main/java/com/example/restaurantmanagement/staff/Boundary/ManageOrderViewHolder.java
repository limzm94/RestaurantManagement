package com.example.restaurantmanagement.staff.Boundary;

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
import com.example.restaurantmanagement.customer.Entity.OrderObject;
import com.example.restaurantmanagement.manager.Boundary.Coupon.EditCouponView;
import com.example.restaurantmanagement.staff.Controller.MarkOrderFulfilled;
import com.example.restaurantmanagement.staff.Controller.ViewOrderSummary;

import java.util.ArrayList;

public class ManageOrderViewHolder extends RecyclerView.Adapter<ManageOrderViewHolder.ViewHolder>{
    private final Context context;
    private final ArrayList<Integer> orderObjectArrayList;

    // Constructor
    public ManageOrderViewHolder(Context context, ArrayList<Integer> orderObjectArrayList) {
        this.context = context;
        this.orderObjectArrayList = orderObjectArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_order_cardview, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ManageOrderViewHolder.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        MarkOrderFulfilled markOrderFulfilled = new MarkOrderFulfilled(context);
        Integer model = orderObjectArrayList.get(position);
        holder.orderId.setText(model.toString());
        holder.viewBtn.setOnClickListener(v -> {
            // send the account info to edit account activity
            Intent intent = new Intent(context, OrderSummaryView.class);
            intent.putExtra("orderId", orderObjectArrayList.get(model));
            context.startActivity(intent);
        });

        holder.markBtn.setOnClickListener(v -> {
            // need to change the isFulfilled in the database from Unfulfilled to Fulfilled
            markOrderFulfilled.markFulFilled(model);
            orderObjectArrayList.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            notifyItemRangeChanged(holder.getAdapterPosition(), orderObjectArrayList.size());
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return orderObjectArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderId;
        private final Button viewBtn;
        private final Button markBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            viewBtn = itemView.findViewById(R.id.viewOrderBtn);
            markBtn = itemView.findViewById(R.id.markFulfilledBtn);
        }
    }
}

