package com.example.patientsocialdistance.data.Interfaces;


import com.example.patientsocialdistance.pojo.APIResponse.AuthModelResponse;
import com.example.patientsocialdistance.pojo.DTOs.BlockUserDTO;
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


    @GET("GetAllByUser")
    @Headers("Content-Type: application/json")
    public Call<List<BlockedUserDTO>> getBlockedUsers (@Query("username") String username);



    @POST("CreateOrDeleteBlock")
    @Headers("Content-Type: application/json")
    public Call<BlockUserDTO> CreateOrDeleteBlock (@Body BlockUserDTO model);
}
