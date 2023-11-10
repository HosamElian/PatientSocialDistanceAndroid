package com.example.patientsocialdistance.ui.interactionlist;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.patientsocialdistance.data.Clients.InteractionClient;
import com.example.patientsocialdistance.pojo.DTOs.InteractionForUserDTO;
import com.example.patientsocialdistance.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InteractionListViewModel extends ViewModel {
    public MutableLiveData<ArrayList<InteractionForUserDTO>> InteractionDtoMutableLiveData = new MutableLiveData<>();

    public void getInteractions(){
        getFromDatabase();
    }

    private void getFromDatabase(){
        InteractionClient.getInstance().GetUserInteractions(Constants.getCurrentUsername()).enqueue(new Callback<List<InteractionForUserDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<InteractionForUserDTO>> call, @NonNull Response<List<InteractionForUserDTO>> response) {
                if(200 == response.code() && null != response.body()  ){
                    ArrayList<InteractionForUserDTO> list = new ArrayList<>(response.body());
                    InteractionDtoMutableLiveData.setValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<InteractionForUserDTO>> call, @NonNull Throwable t) {

            }
        });
    }

}
