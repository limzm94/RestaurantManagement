package com.example.restaurantmanagement;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restaurantmanagement.utility.DBHandler;


public class SignUpActivity extends AppCompatActivity {

    EditText edtPassword, edtUsername;
    Button btnSignUp;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        DB = new DBHandler(this);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.buttonSignUp);


        btnSignUp.setOnClickListener(view -> {
            String user = edtUsername.getText().toString();
            String pass = edtPassword.getText().toString();
            String status = "Active";
            String personName = "PersonName";
            String role = "Admin";

            if (user.equals("") || pass.equals(""))
                Toast.makeText(SignUpActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkUser = DB.checkUsername(user);
                if (!checkUser) {
                    Boolean insert = DB.insertData(user, pass, status, personName, role);
                    if (insert) {
                        Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
