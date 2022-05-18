package com.example.restaurantmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantmanagement.admin.AdminUI;
import com.example.restaurantmanagement.customer.CustomerUI;
import com.example.restaurantmanagement.manager.ManagerController;
import com.example.restaurantmanagement.owner.OwnerUI;
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
        Boolean checkAdminExist = DB.checkUsernamePassword("masterAdmin", "password");
        if (!checkAdminExist) {
            Boolean insert = DB.insertUserData("masterAdmin", "password", "Active", "UwU", "Owner");
            if (insert) {
                System.out.println("Admin created");
            }
        }

        //Preloaded menu NEED TO BE REMOVED
        ManagerController managerController = new ManagerController();
        managerController.insertFood(MainActivity.this,"Curry","Curry is a dish with a sauce seasoned with spices.", 7.50);
        managerController.insertFood(MainActivity.this,"Chicken Rice","Hainan's chicken rice is a dish of poached chicken and seasoned rice.", 4.50);
        managerController.insertFood(MainActivity.this,"Ramen","Ramen is a Japanese noodle dish.", 10.90);
        managerController.insertFood(MainActivity.this,"Bingsu","Bingsu is a Korean shaved ice dessert with sweet toppings.", 8.00);
        managerController.insertFood(MainActivity.this,"Carrot Cake","Carrot cake is cake that contains carrots mixed into the batter.", 6.50);


        btnSignIn.setOnClickListener(v -> {

            String user = edtUsername.getText().toString();
            String pass = edtPassword.getText().toString();

            if (user.equals("") || pass.equals(""))
                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkUsernamePassword = DB.checkUsernamePassword(user, pass);
                // get userrole
                String userRole = DB.getUserRole(user,pass);
                Boolean isActive = DB.getUserStatus(user,pass);

                if (checkUsernamePassword && isActive) {
                    Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();

                    switch (userRole) {
                        case "Owner": {
                            Intent adminUI = new Intent(MainActivity.this, OwnerUI.class);
                            startActivity(adminUI);
                            break;
                        }
                        case "Admin": {
                            Intent adminUI = new Intent(MainActivity.this, AdminUI.class);
                            startActivity(adminUI);
                            break;
                        }
                        case "Manager": {
                            Intent adminUI = new Intent(MainActivity.this, AdminUI.class);
                            startActivity(adminUI);
                            break;
                        }
                        case "Staff": {
                            Intent adminUI = new Intent(MainActivity.this, AdminUI.class);
                            startActivity(adminUI);
                            break;
                        }
                        default: {
                            Intent adminUI = new Intent(MainActivity.this, CustomerUI.class);
                            startActivity(adminUI);
                            break;
                        }
                    }

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