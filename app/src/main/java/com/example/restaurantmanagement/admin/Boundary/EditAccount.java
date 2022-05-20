package com.example.restaurantmanagement.admin.Boundary;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.admin.Controller.EditUser;

public class EditAccount extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        EditUser editUser = new EditUser(EditAccount.this);

        Spinner statusSpinner = findViewById(R.id.status_create);
        Spinner rolesSpinner = findViewById(R.id.roles_create);
        EditText usernameText = findViewById(R.id.username_create);
        EditText passwordText = findViewById(R.id.password_create);
        EditText personNameText = findViewById(R.id.person_name_create);
        Button editBtn = findViewById(R.id.create_btn);
        Button cancelBtn = findViewById(R.id.cancel_btn);
        editBtn.setText("Edit");

        // get the data from the cardView
        int userKey = getIntent().getIntExtra("userKey", 0);
        String personName = getIntent().getStringExtra("personName");
        String status = getIntent().getStringExtra("status");
        String role = getIntent().getStringExtra("position");
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        personNameText.setText(personName);
        usernameText.setText(username);
        passwordText.setText(password);
        // set the items in the spinner
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(statusAdapter);
        statusSpinner.setSelection(statusAdapter.getPosition(status));

        ArrayAdapter<CharSequence> rolesAdapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        rolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        rolesSpinner.setAdapter(rolesAdapter);
        rolesSpinner.setSelection(rolesAdapter.getPosition(role));

        editBtn.setOnClickListener(v -> {
            String editedUsername = usernameText.getText().toString();
            String editedPassword = passwordText.getText().toString();
            String editedPersonName = personNameText.getText().toString();
            String editedStatus = statusSpinner.getSelectedItem().toString();
            String editedRole = rolesSpinner.getSelectedItem().toString();

            if (editedUsername.equals("") || editedPassword.equals("") || editedPersonName.equals(""))
                Toast.makeText(EditAccount.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                editUser.updateAcc(editedUsername, editedPassword, editedPersonName, editedStatus, editedRole, userKey);
                Intent editAcc = new Intent(EditAccount.this, AdminPage.class);
                startActivity(editAcc);
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }
}