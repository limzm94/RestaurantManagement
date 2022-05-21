package com.example.restaurantmanagement.login.Boundary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.admin.Boundary.AdminPageView;
import com.example.restaurantmanagement.admin.Controller.CreateUser;
import com.example.restaurantmanagement.customer.Boundary.CustomerUI;
import com.example.restaurantmanagement.login.Controller.CheckIsActive;
import com.example.restaurantmanagement.login.Controller.CheckLogin;
import com.example.restaurantmanagement.login.Controller.CheckRole;
import com.example.restaurantmanagement.manager.Controller.FoodMenu.CreateFoodMenu;
import com.example.restaurantmanagement.owner.OwnerUI;
import com.example.restaurantmanagement.utility.DBHandler;

public class MainActivity extends AppCompatActivity {
    //ZM test push
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckLogin checkLogin = new CheckLogin(MainActivity.this);
        CheckIsActive checkIsActive = new CheckIsActive(MainActivity.this);
        CheckRole checkRole = new CheckRole(MainActivity.this);
        EditText edtUsername = findViewById(R.id.login_username_et);
        EditText edtPassword = findViewById(R.id.login_password_et);
        Button btnSignIn = findViewById(R.id.login_btn);

        CreateUser createUser = new CreateUser(MainActivity.this);
        createUser.createAcc("masterAdmin", "password", "Active", "UwU", "Owner");

        //Preloaded menu NEED TO BE REMOVED
        CreateFoodMenu createFoodMenu = new CreateFoodMenu(MainActivity.this);
        createFoodMenu.insertFoodMenu("Curry", "Curry is a dish with a sauce seasoned with spices.", 7.50);
        createFoodMenu.insertFoodMenu("Chicken Rice", "Hainan's chicken rice is a dish of poached chicken and seasoned rice.", 4.50);
        createFoodMenu.insertFoodMenu("Ramen", "Ramen is a Japanese noodle dish.", 10.90);
        createFoodMenu.insertFoodMenu("Bingsu", "Bingsu is a Korean shaved ice dessert with sweet toppings.", 8.00);
        createFoodMenu.insertFoodMenu("Carrot Cake", "Carrot cake is cake that contains carrots mixed into the batter.", 6.50);

        btnSignIn.setOnClickListener(v -> {
            String user = edtUsername.getText().toString();
            String pass = edtPassword.getText().toString();
            if (user.equals("") || pass.equals(""))
                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {

                if (checkLogin.checkUserAndPass(user, pass) && checkIsActive.checkStatus(user, pass)) {
                    Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    switch (checkRole.checkRoles(user, pass)) {
                        case "Owner": {
                            Intent adminUI = new Intent(MainActivity.this, OwnerUI.class);
                            startActivity(adminUI);
                            break;
                        }
                        case "Admin": {
                            Intent adminUI = new Intent(MainActivity.this, AdminPageView.class);
                            startActivity(adminUI);
                            break;
                        }
                        case "Manager": {
                            Intent adminUI = new Intent(MainActivity.this, AdminPageView.class);
                            startActivity(adminUI);
                            break;
                        }
                        case "Staff": {
                            Intent adminUI = new Intent(MainActivity.this, AdminPageView.class);
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
    }
}