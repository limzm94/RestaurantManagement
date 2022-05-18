package com.example.restaurantmanagement.manager;


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

import java.util.ArrayList;

public class CouponController extends RecyclerView.Adapter<CouponController.ViewHolder> {
    private final Context context;
    private final ArrayList<CouponEntity> couponModelArrayList;

    // Constructor
    public CouponController(Context context, ArrayList<CouponEntity> couponModelArrayList) {
        this.context = context;
        this.couponModelArrayList = couponModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_cardview, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        String discText;
        CouponEntity model = couponModelArrayList.get(position);
        holder.couponCode.setText(model.getCouponCode());
        holder.couponDesc.setText(model.getCouponDesc());
        holder.couponStatus.setText(model.getCouponStatus());
        discText = String.format("%d", model.getCouponDisc()) + "%";
        holder.couponDiscount.setText(discText);

        holder.editBtn.setOnClickListener(v -> {
            // send the account info to edit account activity
            Intent intent = new Intent(context, EditCoupon.class);
            intent.putExtra("couponKey", model.getCouponKey());
            intent.putExtra("couponCode", model.getCouponCode());
            intent.putExtra("couponDesc", model.getCouponDesc());
            intent.putExtra("couponStatus", model.getCouponStatus());
            intent.putExtra("couponDisc", model.getCouponDisc());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return couponModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView couponCode;
        private final TextView couponDesc;
        private final TextView couponStatus;
        private final TextView couponDiscount;
        private final Button editBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            couponCode = itemView.findViewById(R.id.couponCode);
            couponDesc = itemView.findViewById(R.id.couponDesc);
            couponStatus = itemView.findViewById(R.id.couponStatus);
            couponDiscount = itemView.findViewById(R.id.couponDisc);
            editBtn = itemView.findViewById(R.id.editCouponBtn);
        }
    }
}
