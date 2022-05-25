package com.example.restaurantmanagement.admin.Boundary;


import android.app.Activity;
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
import com.example.restaurantmanagement.admin.Entity.UserObject;

import java.util.ArrayList;

public class AdminViewHolder extends RecyclerView.Adapter<AdminViewHolder.ViewHolder> {

    private Context context;
    private ArrayList<UserObject> adminModelArrayList;
    private String role;

    // Constructor
    public AdminViewHolder(Context context, ArrayList<UserObject> adminModelArrayList, String role) {
        this.context = context;
        this.adminModelArrayList = adminModelArrayList;
        this.role = role;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int cardPosition) {
        // to set data to textview and imageview of each card layout
        UserObject model = adminModelArrayList.get(cardPosition);
        holder.personName.setText(model.getPerson_name());
        holder.status.setText(model.getStatus());
        holder.position.setText(model.getRole());
        holder.username.setText(model.getUsername());
        holder.password.setText(model.getPassword());

        holder.editBtn.setOnClickListener(v -> {
            // send the account info to edit account activity
            Intent intent = new Intent(context, EditAccountView.class);
            intent.putExtra("userKey", model.getId());
            intent.putExtra("personName", model.getPerson_name());
            intent.putExtra("status", model.getStatus());
            intent.putExtra("position", model.getRole());
            intent.putExtra("username", model.getUsername());
            intent.putExtra("password", model.getPassword());
            intent.putExtra("accountRole", role);
            context.startActivity(intent);
            ((Activity) context).finish();
        });
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
        private Button editBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.personName);
            status = itemView.findViewById(R.id.status);
            position = itemView.findViewById(R.id.position);
            username = itemView.findViewById(R.id.username);
            password = itemView.findViewById(R.id.password);
            editBtn = itemView.findViewById(R.id.editBtn);
        }
    }
}