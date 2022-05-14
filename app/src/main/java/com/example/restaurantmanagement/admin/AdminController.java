package com.example.restaurantmanagement.admin;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;

import java.util.ArrayList;

public class AdminController extends RecyclerView.Adapter<AdminController.ViewHolder> {

    private Context context;
    private ArrayList<AdminEntity> adminModelArrayList;

    // Constructor
    public AdminController(Context context, ArrayList<AdminEntity> adminModelArrayList) {
        this.context = context;
        this.adminModelArrayList = adminModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        AdminEntity model = adminModelArrayList.get(position);
        holder.personName.setText(model.getPerson_name());
        holder.status.setText(model.getStatus());
        holder.position.setText(model.getPosition());
        holder.username.setText(model.getUsername());
        holder.password.setText(model.getPassword());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return adminModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView personName, status, position, username, password;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.personName);
            status = itemView.findViewById(R.id.status);
            position = itemView.findViewById(R.id.position);
            username = itemView.findViewById(R.id.username);
            password = itemView.findViewById(R.id.password);
        }
    }
}