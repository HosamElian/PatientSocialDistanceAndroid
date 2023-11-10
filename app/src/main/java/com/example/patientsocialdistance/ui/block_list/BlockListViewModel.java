package com.example.patientsocialdistance.ui.block_list;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.patientsocialdistance.data.Clients.BlockClient;
import com.example.patientsocialdistance.pojo.DTOs.BlockedUserDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlockListViewModel extends ViewModel {
    public MutableLiveData<ArrayList<BlockedUserDTO>> blockDtoMutableLiveData = new MutableLiveData<>();

    public void getBlockedUser(String username){
        getFromDatabase(username);
    }

    private void getFromDatabase(String username){
        ArrayList<BlockedUserDTO> list = new ArrayList<>();
        BlockClient.getInstance().GetBlockedUsers(username).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<BlockedUserDTO>> call, @NonNull Response<List<BlockedUserDTO>> response) {
                if (200 == response.code() && null != response.body()) {

                    list.clear();
                    list.addAll(response.body());
                    blockDtoMutableLiveData.setValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<BlockedUserDTO>> call, @NonNull Throwable t) {
                Log.d("login", "onFailure: " + t.getMessage());

            }
        });

//        list.add(new VisitorDto(1, "Hosam", "Kuwit", "10"));
//        list.add(new VisitorDto(2, "Ahmed", "Kuwit", "11"));
//        list.add(new VisitorDto(3, "Mohamed", "Kuwit", "12"));
    }
}
