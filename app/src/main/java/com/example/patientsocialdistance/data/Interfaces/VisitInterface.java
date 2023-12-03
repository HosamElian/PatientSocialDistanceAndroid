package com.example.patientsocialdistance.data.Interfaces;


import com.example.patientsocialdistance.pojo.DTOs.GetVisitByDateRequest;
import com.example.patientsocialdistance.pojo.DTOs.GetVisitsRequest;
import com.example.patientsocialdistance.pojo.DTOs.VisitApprovalDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitDto;
import com.example.patientsocialdistance.pojo.DTOs.VisitorRequestVisitDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitsAcceptedDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface VisitInterface {
    @POST("GetAllVisitsByDate")
    @Headers("Content-Type: application/json")
    public Call<List<VisitsAcceptedDTO>> GetAllVisitsByDate(@Body GetVisitByDateRequest request);


    @POST("GetAllVisits")
    @Headers("Content-Type: application/json")
    public Call<List<VisitorRequestVisitDTO>> GetAllVisits (@Body GetVisitsRequest request);

    @POST("ReserveVisit")
    @Headers("Content-Type: application/json")
    public Call<VisitDto> ReserveVisit (@Body VisitDto model);

    @POST("VisitApproval")
    @Headers("Content-Type: application/json")
    public Call<VisitApprovalDTO> SetVisitApproval (@Body VisitApprovalDTO model);

}
