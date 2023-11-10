package com.example.patientsocialdistance.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.UserClient;
import com.example.patientsocialdistance.databinding.ActivityMainBinding;
import com.example.patientsocialdistance.pojo.APIResponse.AuthModelResponse;
import com.example.patientsocialdistance.pojo.DTOs.TokenRequestModel;
import com.example.patientsocialdistance.ui.list.ListActivity;
import com.example.patientsocialdistance.ui.register.RegisterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String userName, password;
    Intent navigationIntent;
    Context context;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        context = this;
        binding.loginBT.setOnClickListener(view -> {
            userName = binding.usernameLoginET.toString();
            password = binding.passwordLoginET.toString();
            if (validLogin(userName, password)) {
                login();
            }
        });

        binding.registerBT.setOnClickListener(view -> {
            navigationIntent = new Intent(this, RegisterActivity.class);
            startActivity(navigationIntent);
        });
    }

    private void login() {
        UserClient.getInstance().Login(new TokenRequestModel("test1@gmail.com", "Password@1"))
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<AuthModelResponse> call, @NonNull Response<AuthModelResponse> response) {
                        navigationIntent = new Intent(context, ListActivity.class);
                        startActivity(navigationIntent);
                    }

                    @Override
                    public void onFailure(@NonNull Call<AuthModelResponse> call, @NonNull Throwable t) {
                        Toast.makeText(context, "Invalid Operation", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // TODO: validation for login
    private boolean validLogin(String userName, String password) {
        return true;
    }
}