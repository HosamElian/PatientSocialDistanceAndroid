package com.example.patientsocialdistance.data.Interfaces;


import com.example.patientsocialdistance.pojo.APIResponse.AuthModelResponse;
import com.example.patientsocialdistance.pojo.DTOs.RegisterModelDTO;
import com.example.patientsocialdistance.pojo.DTOs.TokenRequestModel;
import com.example.patientsocialdistance.pojo.DTOs.UserForSearchDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserInterface {


    @GET("GetUsers")
    public Call<List<UserForSearchDTO>> getUserByUsername(@Query("username") String username);

    @POST("register")
    @Headers("Content-Type: application/json")
    public Call<AuthModelResponse> registration (@Body RegisterModelDTO model);

    @POST("login")
    @Headers("Content-Type: application/json")
    public Call<AuthModelResponse> login (@Body TokenRequestModel model);

}
