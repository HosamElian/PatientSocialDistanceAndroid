package com.example.patientsocialdistance.data.Interfaces;


import com.example.patientsocialdistance.pojo.APIResponse.AuthModelResponse;
import com.example.patientsocialdistance.pojo.DTOs.BlockedUserDTO;
import com.example.patientsocialdistance.pojo.DTOs.RegisterModelDTO;
import com.example.patientsocialdistance.pojo.DTOs.TokenRequestModel;
import com.example.patientsocialdistance.pojo.DTOs.VisitorDto;
import com.example.patientsocialdistance.ui.list.ListActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BlockInterface {
//
//    @POST("LoginAndroid")
//    @Headers("Content-Type: application/json")
//    public Call<String> login(@Body User user);


    @GET("GetAllByUser")
    @Headers("Content-Type: application/json")
    public Call<List<BlockedUserDTO>> getBlockedUsers (@Query("username") String username);



//    @POST("login")
//    @Headers("Content-Type: application/json")
//    public Call<AuthModelResponse> login (@Body TokenRequestModel model);
}
