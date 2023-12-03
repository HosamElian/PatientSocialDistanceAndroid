package com.example.patientsocialdistance.data.Clients;

import com.example.patientsocialdistance.data.Interfaces.VisitInterface;
import com.example.patientsocialdistance.data.netwrokaccess.UnsafeOkHttpClient;
import com.example.patientsocialdistance.pojo.DTOs.GetVisitByDateRequest;
import com.example.patientsocialdistance.pojo.DTOs.GetVisitsRequest;
import com.example.patientsocialdistance.pojo.DTOs.VisitApprovalDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitDto;
import com.example.patientsocialdistance.pojo.DTOs.VisitorRequestVisitDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitsAcceptedDTO;
import com.example.patientsocialdistance.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class VisitClient {
    private static final String BASE_URL = Constants.getUrl("Visit");
    private final VisitInterface visitInterface;
    private static VisitClient Instance;
    OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
    Gson gson = new GsonBuilder().setLenient().create();

    public VisitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        visitInterface = retrofit.create(VisitInterface.class);
    }

    public static VisitClient getInstance() {
        if (null == Instance) {
            return new VisitClient();
        }
        return Instance;
    }
    public Call<List<VisitsAcceptedDTO>> GetAllVisitsByDate(GetVisitByDateRequest request) {
        return visitInterface.GetAllVisitsByDate(request);
    }

    public Call<List<VisitorRequestVisitDTO>> GetAllVisits(GetVisitsRequest request) {
        return visitInterface.GetAllVisits(request);
    }
    public Call<VisitDto> ReserveVisit(VisitDto model) {
        return visitInterface.ReserveVisit(model);
    }


    public Call<VisitApprovalDTO> SetVisitApproval(VisitApprovalDTO visitApprovalDTO) {
        return visitInterface.SetVisitApproval(visitApprovalDTO);
    }

}
