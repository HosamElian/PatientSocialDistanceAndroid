package com.example.patientsocialdistance.data.Interfaces;

import com.example.patientsocialdistance.pojo.DTOs.BlockUserDTO;
import com.example.patientsocialdistance.pojo.DTOs.BlockedUserDTO;
import com.example.patientsocialdistance.pojo.DTOs.ChangeRequestDTO;
import com.example.patientsocialdistance.pojo.DTOs.NotificationDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NotificationInterface {
    @GET("GetAllByUser")
    @Headers("Content-Type: application/json")
    public Call<List<NotificationDTO>> getAllNotifications(@Query("username") String username);


    @POST("ChangeDateResponse")
    @Headers("Content-Type: application/json")
    public Call<String> ChangeDateResponse (@Body ChangeRequestDTO model);

}
