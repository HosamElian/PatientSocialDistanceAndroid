package com.example.patientsocialdistance.ui.accept_appointments;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.patientsocialdistance.data.Clients.VisitClient;
import com.example.patientsocialdistance.pojo.DTOs.VisitorDto;
import com.example.patientsocialdistance.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitAcceptAppointmentViewModel extends ViewModel {
    public MutableLiveData<ArrayList<VisitorDto>> appointmentDtoMutableLiveData = new MutableLiveData<>();

    public void getAppointments(String username){ getFromDatabase(username);}
//    public void getAppointments(String username, String date){ getFromDatabase(username, date);}

    private void getFromDatabase(String username){
        VisitClient.getInstance().GetAllNotApprovedVisits(username).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<VisitorDto>> call, @NonNull Response<List<VisitorDto>> response) {
                if (200 == response.code() && null != response.body()) {
                    ArrayList<VisitorDto> list = new ArrayList<>(response.body());
                    appointmentDtoMutableLiveData.setValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<VisitorDto>> call, @NonNull Throwable t) {

            }
        });
    }
//    private void getFromDatabase(String username, String date){
//        VisitClient.getInstance().GetAllNotApprovedVisits(username).enqueue(new Callback<>() {
//            @Override
//            public void onResponse(@NonNull Call<List<VisitorDto>> call, @NonNull Response<List<VisitorDto>> response) {
//                if (200 == response.code() && null != response.body()) {
//                    ArrayList<VisitorDto> list = new ArrayList<>(response.body());
//                    appointmentDtoMutableLiveData.setValue(list);
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<VisitorDto>> call, @NonNull Throwable t) {
//
//            }
//        });
//    }
}
