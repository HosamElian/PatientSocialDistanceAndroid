package com.example.patientsocialdistance.data.Clients;

import com.example.patientsocialdistance.data.Interfaces.NotificationInterface;
import com.example.patientsocialdistance.data.netwrokaccess.UnsafeOkHttpClient;
import com.example.patientsocialdistance.pojo.DTOs.BlockedUserDTO;
import com.example.patientsocialdistance.pojo.DTOs.ChangeRequestDTO;
import com.example.patientsocialdistance.pojo.DTOs.NotificationDTO;
import com.example.patientsocialdistance.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NotificationClient {
    private static final String BASE_URL = Constants.getUrl("Notification");
    private final NotificationInterface notificationInterface;
    private static NotificationClient Instance;
    OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
    Gson gson = new GsonBuilder().setLenient().create();

    public NotificationClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        notificationInterface = retrofit.create(NotificationInterface.class);
    }

    public static NotificationClient getInstance() {
        if (null == Instance) {
            return new NotificationClient();
        }
        return Instance;
    }

    public Call<List<NotificationDTO>> getAllNotifications(String username) {
        return notificationInterface.getAllNotifications(username);
    }
    public Call<String> ChangeDateResponse(ChangeRequestDTO requestDTO) {
        return notificationInterface.ChangeDateResponse(requestDTO);
    }
}
