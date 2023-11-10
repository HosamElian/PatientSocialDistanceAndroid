package com.example.patientsocialdistance.data.Clients;

import com.example.patientsocialdistance.data.Interfaces.BlockInterface;
import com.example.patientsocialdistance.data.netwrokaccess.UnsafeOkHttpClient;
import com.example.patientsocialdistance.pojo.DTOs.BlockedUserDTO;
import com.example.patientsocialdistance.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BlockClient {
    private static final String BASE_URL = Constants.getUrl("Block");
    private final BlockInterface blockInterface;
    private static BlockClient Instance;
    OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
    Gson gson = new GsonBuilder().setLenient().create();

    public BlockClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        blockInterface = retrofit.create(BlockInterface.class);
    }

    public static BlockClient getInstance() {
        if (null == Instance) {
            return new BlockClient();
        }
        return Instance;
    }

    public Call<List<BlockedUserDTO>> GetBlockedUsers(String username) {
        return blockInterface.getBlockedUsers(username);
    }
//    public Call<AuthModelResponse> Login(TokenRequestModel user) {
//        return blockInterface.login(user);
//    }



//    public Call<String> ChangeUserPassword(ChangePasswordDto changePasswordDto) {
//        return userInterface.ChangeUserPassword(changePasswordDto);
//    }
}
