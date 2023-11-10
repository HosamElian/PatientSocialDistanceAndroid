package com.example.patientsocialdistance.ui.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.UserClient;
import com.example.patientsocialdistance.databinding.ActivityRegisterBinding;
import com.example.patientsocialdistance.pojo.APIResponse.AuthModelResponse;
import com.example.patientsocialdistance.pojo.DTOs.RegisterModelDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setLifecycleOwner(this);

        binding.submitRegisterBT.setOnClickListener(view -> {
            // TODO: valid login
            UserClient.getInstance().Registration(getUserDate()
            ).enqueue(new Callback<AuthModelResponse>() {
                @Override
                public void onResponse(@NonNull Call<AuthModelResponse> call, @NonNull Response<AuthModelResponse> response) {
                    if ((null != response.body()) && (200 == response.code())) {
                        Log.d("login", "onResponse_Log_main " + response.body().username);

                    }
                    else {
//                        makeToast(getString(R.string.not_authorized));
                    }
                    Log.d("login", "onResponse_Log_main " + response);
                }

                @Override
                public void onFailure(@NonNull Call<AuthModelResponse> call, @NonNull Throwable t) {
                    Log.d("login", "onFailure_Log_main " + t.getMessage());

                }
            });
        });

    }

    private RegisterModelDTO getUserDate() {
        return new RegisterModelDTO(2,
                binding.passwordRegisterET.getText().toString(),
                Integer.parseInt(binding.ageRegisterET.getText().toString()),
                binding.nameRegisterET.getText().toString(),
                binding.userNameRegisterET.getText().toString(),
                binding.emailRegisterET.getText().toString(),
                binding.nationalIdRegisterET.getText().toString(),
                 binding.nationalityRegisterET.getText().toString(),
                binding.hospitalRegisterET.getText().toString());
    }
}