package com.example.patientsocialdistance.data.Interfaces;


import com.example.patientsocialdistance.pojo.APIResponse.AuthModelResponse;
import com.example.patientsocialdistance.pojo.DTOs.RegisterModelDTO;
import com.example.patientsocialdistance.pojo.DTOs.TokenRequestModel;
import com.example.patientsocialdistance.pojo.DTOs.VisitApprovalDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitDto;
import com.example.patientsocialdistance.pojo.DTOs.VisitorDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VisitInterface {
    @GET("GetAllApprovedByDate")
    @Headers("Content-Type: application/json")
    public Call<List<VisitDto>> GetAllApprovedByDate (@Query("username") String username,
                                           @Query("date") String date);

//    @GET("GetAllNotApprovedByDate")
//    @Headers("Content-Type: application/json")
//    public Call<List<VisitDto>> GetAllNotApprovedByDate (@Query("username") String username,
//                                           @Query("date") String date);

    @GET("GetAllNotApprovedVisits")
    @Headers("Content-Type: application/json")
    public Call<List<VisitorDto>> GetAllNotApprovedVisits (@Query("username") String username);

    @POST("ReserveVisit")
    @Headers("Content-Type: application/json")
    public Call<VisitDto> ReserveVisit (@Body VisitDto model);

    @POST("VisitApproval")
    @Headers("Content-Type: application/json")
    public Call<VisitApprovalDTO> SetVisitApproval (@Body VisitApprovalDTO model);

}
