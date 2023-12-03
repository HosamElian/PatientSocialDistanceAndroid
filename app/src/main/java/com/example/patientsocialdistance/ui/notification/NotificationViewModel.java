package com.example.patientsocialdistance.ui.notification;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.patientsocialdistance.data.Clients.NotificationClient;
import com.example.patientsocialdistance.pojo.DTOs.NotificationDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationViewModel extends ViewModel {
    public MutableLiveData<ArrayList<NotificationDTO>> notificationMutableLiveData = new MutableLiveData<>();
    public void getNotifications(String username){ getFromDatabase(username);}

    private void getFromDatabase(String username) {
        NotificationClient.getInstance().getAllNotifications(username).enqueue(new Callback<List<NotificationDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<NotificationDTO>> call, @NonNull Response<List<NotificationDTO>> response) {
                if (200 == response.code() && null != response.body()) {
                    ArrayList<NotificationDTO> list = new ArrayList<>(response.body());
                    notificationMutableLiveData.setValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<NotificationDTO>> call, @NonNull Throwable t) {

            }
        });
    }

}
