package com.example.restaurantmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantmanagement.admin.AdminUI;
import com.example.restaurantmanagement.utility.DBHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edtUsername = findViewById(R.id.login_username_et);
        EditText edtPassword = findViewById(R.id.login_password_et);
        Button btnSignIn = findViewById(R.id.login_btn);
//        Button signUpBtn = findViewById(R.id.signup_btn);
        DBHandler DB = new DBHandler(this);

        // create admin account on first login
        Boolean checkAdminExist = DB.checkUsernamePassword("admin", "password");
        if (!checkAdminExist) {
            Boolean insert = DB.insertData("admin", "password", "Active", "UwU", "Admin");
            if (insert) {
                System.out.println("Admin created");
            }
        }


        btnSignIn.setOnClickListener(v -> {

            String user = edtUsername.getText().toString();
            String pass = edtPassword.getText().toString();

            if (user.equals("") || pass.equals(""))
                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkUsernamePassword = DB.checkUsernamePassword(user, pass);
                if (checkUsernamePassword) {
                    Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    Intent adminUI = new Intent(MainActivity.this, AdminUI.class);
                    startActivity(adminUI);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });


     /*   signUpBtn.setOnClickListener(v -> {
            Intent signUp = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(signUp);
        });*/
    }

}