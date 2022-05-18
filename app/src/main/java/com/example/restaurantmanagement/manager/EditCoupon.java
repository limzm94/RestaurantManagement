package com.example.restaurantmanagement.manager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.number.IntegerWidth;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.utility.DBHandler;

public class EditCoupon extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_coupon);
        DBHandler DB = new DBHandler(this);
        Spinner statusSpinner = findViewById(R.id.couponStatus_create);
        EditText couponCodeText = findViewById(R.id.couponCode_create);
        EditText couponDescText = findViewById(R.id.couponDesc_create);
        EditText discountText = findViewById(R.id.discount_create);
        Button editBtn = findViewById(R.id.create_btn);
        Button cancelBtn = findViewById(R.id.cancel_btn);

        editBtn.setText("Edit");

        // get the data from the cardview
        int couponKey = getIntent().getIntExtra("couponKey", 0);
        String couponCode = getIntent().getStringExtra("couponCode");
        String couponDesc = getIntent().getStringExtra("couponDesc");
        String couponStatus = getIntent().getStringExtra("couponStatus");
        int couponDisc = getIntent().getIntExtra("couponDisc", 0);

        couponCodeText.setText(couponCode);
        couponDescText.setText(couponDesc);
        discountText.setText(Integer.toString(couponDisc));
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(statusAdapter);
        statusSpinner.setSelection(statusAdapter.getPosition(couponStatus));

        editBtn.setOnClickListener(v -> {
            String editedCouponCode = couponCodeText.getText().toString();
            String editedCouponDesc = couponDescText.getText().toString();
            int editedDiscount = Integer.parseInt(discountText.getText().toString());
            String editedStatus = statusSpinner.getSelectedItem().toString();

            if (editedCouponCode.equals("") || editedCouponDesc.equals("") || editedDiscount == 0)
                Toast.makeText(EditCoupon.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                DB.updateCouponInfo(editedCouponCode, editedCouponDesc, editedDiscount, editedStatus, couponKey);

                Intent editCoupon = new Intent(EditCoupon.this, Coupon.class);
                startActivity(editCoupon);
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }
}