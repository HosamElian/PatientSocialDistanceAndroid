package com.example.patientsocialdistance.ui.accept_appointments;

import static com.example.patientsocialdistance.ui.accept_appointments.CollectionTransformer.mapVisitsAcceptedDTOToVisitorRequestVisitDTOCollection;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.patientsocialdistance.data.Clients.VisitClient;
import com.example.patientsocialdistance.pojo.DTOs.GetVisitByDateRequest;
import com.example.patientsocialdistance.pojo.DTOs.GetVisitsRequest;
import com.example.patientsocialdistance.pojo.DTOs.VisitorDto;
import com.example.patientsocialdistance.pojo.DTOs.VisitorRequestVisitDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitsAcceptedDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitAcceptAppointmentViewModel extends ViewModel {
    public MutableLiveData<ArrayList<VisitorRequestVisitDTO>> appointmentDtoMutableLiveData = new MutableLiveData<>();

    public void getAppointments(GetVisitsRequest request){ getFromDatabase(request);}
    public void getAppointmentsByDate(GetVisitByDateRequest request){ getFromDatabaseByDate(request);}

    private void getFromDatabase(GetVisitsRequest request){
        VisitClient.getInstance().GetAllVisits(request).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<VisitorRequestVisitDTO>> call, @NonNull Response<List<VisitorRequestVisitDTO>> response) {
                if (200 == response.code() && null != response.body()) {
                    ArrayList<VisitorRequestVisitDTO> list = new ArrayList<>(response.body());
                    appointmentDtoMutableLiveData.setValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<VisitorRequestVisitDTO>> call, @NonNull Throwable t) {

            }
        });
    }
    private void getFromDatabaseByDate(GetVisitByDateRequest request){
        VisitClient.getInstance().GetAllVisitsByDate(request).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<VisitsAcceptedDTO>> call, @NonNull Response<List<VisitsAcceptedDTO>> response) {
                if (200 == response.code() && null != response.body()) {
                    ArrayList<VisitsAcceptedDTO> listReceived = new ArrayList<>(response.body());
                    ArrayList<VisitorRequestVisitDTO> list = new ArrayList<>(mapVisitsAcceptedDTOToVisitorRequestVisitDTOCollection(listReceived));
                    appointmentDtoMutableLiveData.setValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<VisitsAcceptedDTO>> call, @NonNull Throwable t) {

            }
        });
    }
}
