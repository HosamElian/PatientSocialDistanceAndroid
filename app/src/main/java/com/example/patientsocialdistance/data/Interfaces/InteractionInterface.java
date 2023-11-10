package com.example.patientsocialdistance.data.Interfaces;

import com.example.patientsocialdistance.pojo.DTOs.InteractionForUserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface InteractionInterface {

    @GET("GetAllInteraction")
    public Call<List<InteractionForUserDTO>> getUserInteractions (@Query("username") String username);
}
