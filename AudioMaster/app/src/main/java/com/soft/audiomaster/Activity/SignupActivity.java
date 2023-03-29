package com.soft.audiomaster.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.soft.audiomaster.DBHelper;
import com.soft.audiomaster.Users;
import com.soft.audiomaster.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding activitySignupBinding;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(activitySignupBinding.getRoot());
        dbHelper = new DBHelper(this);
        activitySignupBinding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                execLogin();
            }
        });

        activitySignupBinding.loginRedirectTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void execLogin() {
        Users users = getData();

        String confirmPass = activitySignupBinding.signupConfirmPassword.getText().toString();
        if (users.getEmail().isEmpty() || users.getUsername().isEmpty() || users.getAddress().isEmpty() || users.getPhone().isEmpty() ||
                users.getPassword().isEmpty() || confirmPass.isEmpty())
            Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_LONG);
        else {
            if (users.getPassword().equals(confirmPass)) {
                Boolean checkUserEmail = dbHelper.checkEmail(users.getEmail());

                if (!checkUserEmail) {
                    Boolean insert = dbHelper.insertData(users.getEmail(), users.getUsername(), users.getAddress(), users.getPhone(), users.getPassword());
                    if (insert) {
                        Toast.makeText(SignupActivity.this, "Signup Successfully!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignupActivity.this, "Signup Failed!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "User already exists. please Login!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(SignupActivity.this, "Invalid password!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private Users getData() {
        String email = activitySignupBinding.signupEmail.getText().toString();
        String username = activitySignupBinding.signupUsername.getText().toString();
        String address = activitySignupBinding.signupAddress.getText().toString();
        String phone = activitySignupBinding.signupPhone.getText().toString();
        String password = activitySignupBinding.signupPassword.getText().toString();
        return new Users(email, username, address, phone, password);
    }
}