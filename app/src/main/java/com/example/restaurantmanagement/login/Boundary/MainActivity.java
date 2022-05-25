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
import com.example.restaurantmanagement.customer.Boundary.CustomerView;
import com.example.restaurantmanagement.login.Controller.CheckIsActive;
import com.example.restaurantmanagement.login.Controller.CheckLogin;
import com.example.restaurantmanagement.login.Controller.CheckRole;
import com.example.restaurantmanagement.manager.Boundary.ManagerView;
import com.example.restaurantmanagement.manager.Controller.FoodMenu.CreateFoodMenu;
import com.example.restaurantmanagement.owner.OwnerUI;
import com.example.restaurantmanagement.staff.Boundary.StaffView;

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
                            Intent intent = new Intent(MainActivity.this, OwnerUI.class);
                            intent.putExtra("accountRole","Owner");
                            startActivity(intent);
                            break;
                        }
                        case "Admin": {
                            Intent intent = new Intent(MainActivity.this, AdminPageView.class);
                            intent.putExtra("accountRole","Admin");
                            startActivity(intent);
                            break;
                        }
                        case "Manager": {
                            Intent intent = new Intent(MainActivity.this, ManagerView.class);
                            intent.putExtra("accountRole","Manager");
                            startActivity(intent);
                            break;
                        }
                        case "Staff": {
                            Intent intent = new Intent(MainActivity.this, StaffView.class);
                            intent.putExtra("accountRole","Staff");
                            startActivity(intent);
                            break;
                        }
                        default: {
                            Intent intent = new Intent(MainActivity.this, CustomerView.class);
                            intent.putExtra("accountRole","Customer");
                            intent.putExtra("customerName",user);
                            startActivity(intent);
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