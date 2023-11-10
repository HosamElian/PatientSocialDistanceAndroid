package com.example.patientsocialdistance.data.Clients;

import com.example.patientsocialdistance.data.Interfaces.InteractionInterface;
import com.example.patientsocialdistance.data.netwrokaccess.UnsafeOkHttpClient;
import com.example.patientsocialdistance.pojo.DTOs.InteractionForUserDTO;
import com.example.patientsocialdistance.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InteractionClient {
    private static final String BASE_URL = Constants.getUrl("Interaction");
    private final InteractionInterface interactionInterface;
    private static InteractionClient Instance;
    OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
    Gson gson = new GsonBuilder().setLenient().create();

    public InteractionClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        interactionInterface = retrofit.create(InteractionInterface.class);
    }

    public static InteractionClient getInstance() {
        if (null == Instance) {
            return new InteractionClient();
        }
        return Instance;
    }
    public Call<List<InteractionForUserDTO>> GetUserInteractions(String username) {
        return interactionInterface.getUserInteractions(username);
    }
}
