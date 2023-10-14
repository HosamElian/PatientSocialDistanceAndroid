package com.example.patientsocialdistance.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityMainBinding;
import com.example.patientsocialdistance.ui.list.ListActivity;
import com.example.patientsocialdistance.ui.register.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    String userName, password;
    Intent navigationIntent;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.loginBT.setOnClickListener(view -> {
            userName = binding.usernameLoginET.toString();
            password = binding.passwordLoginET.toString();
            if(validLogin(userName, password)){ login(); }
        });

        binding.registerBT.setOnClickListener(view -> {
            navigationIntent = new Intent(this, RegisterActivity.class);
            startActivity(navigationIntent);
        });
    }

    private void login() {
        navigationIntent = new Intent(this, ListActivity.class);
        startActivity(navigationIntent);
    }


    private boolean validLogin(String userName, String password) {
        return true;
    }
}