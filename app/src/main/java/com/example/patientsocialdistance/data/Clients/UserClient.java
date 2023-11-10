package com.example.patientsocialdistance.data.Clients;

import com.example.patientsocialdistance.data.Interfaces.UserInterface;
import com.example.patientsocialdistance.data.netwrokaccess.UnsafeOkHttpClient;
import com.example.patientsocialdistance.pojo.APIResponse.AuthModelResponse;
import com.example.patientsocialdistance.pojo.DTOs.RegisterModelDTO;
import com.example.patientsocialdistance.pojo.DTOs.TokenRequestModel;
import com.example.patientsocialdistance.pojo.DTOs.UserForSearchDTO;
import com.example.patientsocialdistance.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserClient {
    private static final String BASE_URL = Constants.getUrl("Auth");
    private final UserInterface userInterface;
    private static UserClient Instance;
    OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
    Gson gson = new GsonBuilder().setLenient().create();

    public UserClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        userInterface = retrofit.create(UserInterface.class);
    }

    public static UserClient getInstance() {
        if (null == Instance) {
            return new UserClient();
        }
        return Instance;
    }

    public Call<AuthModelResponse> Registration(RegisterModelDTO model) {
        return userInterface.registration(model);
    }
    public Call<AuthModelResponse> Login(TokenRequestModel user) {
        return userInterface.login(user);
    }

    public Call<List<UserForSearchDTO>> GetUserByUsername(String username) {
        return userInterface.getUserByUsername(username);
    }


//    public Call<String> ChangeUserPassword(ChangePasswordDto changePasswordDto) {
//        return userInterface.ChangeUserPassword(changePasswordDto);
//    }
}
