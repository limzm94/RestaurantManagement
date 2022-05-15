package com.example.restaurantmanagement.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.utility.DBHandler;

public class CreateAccount extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        DBHandler DB = new DBHandler(this);
        Spinner statusSpinner = findViewById(R.id.status_create);
        Spinner rolesSpinner = findViewById(R.id.roles_create);
        EditText usernameText = findViewById(R.id.username_create);
        EditText passwordText = findViewById(R.id.password_create);
        EditText personNameText = findViewById(R.id.person_name_create);
        Button createBtn = findViewById(R.id.create_btn);
        Button cancelBtn = findViewById(R.id.cancel_btn);

        // set the items in the spinner
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(statusAdapter);

        ArrayAdapter<CharSequence> rolesAdapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        rolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        rolesSpinner.setAdapter(rolesAdapter);

        createBtn.setOnClickListener(v -> {
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();
            String personName = personNameText.getText().toString();
            String status = statusSpinner.getSelectedItem().toString();
            String role = rolesSpinner.getSelectedItem().toString();

            System.out.println(String.format("Username: %s%nPassword: %s%nPerson Name: %b%nStatus: %s%nRoles: %s%n",
                    username, password, personName, status, role));

            if (username.equals("") || password.equals("") || personName.equals(""))
                Toast.makeText(CreateAccount.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkUser = DB.checkUsername(username);
                if (!checkUser) {
                    Boolean insert = DB.insertUserData(username, password, status, personName, role);
                    if (insert) {
                        Toast.makeText(CreateAccount.this, "Registered successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(CreateAccount.this, "Registration failed", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CreateAccount.this, "User already exists! please sign in", Toast.LENGTH_LONG).show();
                }

                Intent createAcc = new Intent(CreateAccount.this, AdminUI.class);
                startActivity(createAcc);
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }
}
