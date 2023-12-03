package com.example.patientsocialdistance.ui.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.UserClient;
import com.example.patientsocialdistance.databinding.ActivityRegisterBinding;
import com.example.patientsocialdistance.pojo.APIResponse.AuthModelResponse;
import com.example.patientsocialdistance.pojo.DTOs.RegisterModelDTO;
import com.example.patientsocialdistance.ui.list.ListActivity;
import com.example.patientsocialdistance.utilities.Constants;
import com.example.patientsocialdistance.utilities.ImageHandler;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 275;
    String image = "";
    Context context;
    Intent navIntent;
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setLifecycleOwner(this);
        context = this;

        binding.submitRegisterBT.setOnClickListener(view ->
        {
            if (!image.isBlank() && ! image.isBlank() ) {

                UserClient.getInstance().Registration(getUserDate()
                ).enqueue(new Callback<AuthModelResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<AuthModelResponse> call, @NonNull Response<AuthModelResponse> response) {
                        if ((null != response.body()) && (200 == response.code())) {
                            Log.d("login", "onResponse_Log_main " + response.body().username);
                            navIntent = new Intent(context, ListActivity.class);
                            Constants.saveUserName(context, binding.userNameRegisterET.getText().toString());
                            context.startActivity(navIntent);
                        } else {
//                        makeToast(getString(R.string.not_authorized));
                        }
                        Log.d("login", "onResponse_Log_main " + response);
                    }

                    @Override
                    public void onFailure(@NonNull Call<AuthModelResponse> call, @NonNull Throwable t) {
                        Log.d("login", "onFailure_Log_main " + t.getMessage());

                    }
                });
            }
            else{
                Toast.makeText(context, "Please select Image", Toast.LENGTH_SHORT).show();
            }
        });
        binding.imageRegisterIB.setOnClickListener(view -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        });
    }

    private RegisterModelDTO getUserDate() {
        return new RegisterModelDTO(2,
                binding.passwordRegisterET.getText().toString(),
                Integer.parseInt(binding.ageRegisterET.getText().toString()),
                binding.nameRegisterET.getText().toString(),
                binding.emailRegisterET.getText().toString(),
                binding.userNameRegisterET.getText().toString(),
                binding.nationalIdRegisterET.getText().toString(),
                binding.nationalityRegisterET.getText().toString(),
                binding.hospitalRegisterET.getText().toString(),
                image);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == GALLERY_REQUEST) {
                Uri selectedImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    binding.imageRegisterIV.setImageBitmap(bitmap);
                    ImageHandler handler = new ImageHandler();
                    image = handler.BitMapToString(bitmap);
                } catch (IOException e) {
                    Log.i("TAG", "Some exception " + e);
                }
            }
    }
}