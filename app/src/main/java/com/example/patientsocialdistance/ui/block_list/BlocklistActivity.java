package com.example.patientsocialdistance.ui.block_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.BlockClient;
import com.example.patientsocialdistance.data.Clients.UserClient;
import com.example.patientsocialdistance.databinding.ActivityBlocklistBinding;
import com.example.patientsocialdistance.pojo.DTOs.BlockUserDTO;
import com.example.patientsocialdistance.pojo.DTOs.BlockedUserDTO;
import com.example.patientsocialdistance.pojo.DTOs.UserForSearchDTO;
import com.example.patientsocialdistance.ui.visit_schedule.SelectItemDialog;
import com.example.patientsocialdistance.ui.visit_schedule.interfaces.OnReceivedStatus;
import com.example.patientsocialdistance.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlocklistActivity extends AppCompatActivity {
    BlockListViewModel blockListViewModel;
    ArrayList<UserForSearchDTO> list;
    String searchValue;
    SelectItemDialog dialog;
    BlockDialog blockDialog;
    ActivityBlocklistBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_blocklist);

        blockListViewModel = ViewModelProviders.of(this).get(BlockListViewModel.class);
        binding.setBlockListViewModel(blockListViewModel);

        BlockListAdapter adapter = new BlockListAdapter(context);
        binding.blocksRV.setAdapter(adapter);
        context = this;

        binding.blockNumberTV.setText("0");

        blockListViewModel.blockDtoMutableLiveData.observe(this, blockedUserDTOS -> {
            adapter.setList(blockedUserDTOS);
            if (blockedUserDTOS.size() > 0) {
                binding.blockNumberTV.setText(String.valueOf(blockedUserDTOS.size()));
                Log.d("login", " blockedUserDTOS " + blockedUserDTOS.size());
            }
        });

        binding.blockMakingSearchTV.setOnClickListener(view -> {
            searchValue = binding.nameOfBlockedUserMakingET.getText().toString();
            if (searchValue.isEmpty()) {
                Toast.makeText(this, "Can't search with empty text", Toast.LENGTH_SHORT).show();
            } else {
                UserClient.getInstance().GetUserByUsername(searchValue).enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserForSearchDTO>> call, @NonNull Response<List<UserForSearchDTO>> response) {
                        if (200 == response.code() && null != response.body() && response.body().size() > 0) {
                            list = new ArrayList<>(response.body());
                            selectUserFromDialog(list);
                        } else if (400 == response.code() && null != response.body()) {
                            Toast.makeText(context, "Can't Searching without writing any name", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "No user Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserForSearchDTO>> call, @NonNull Throwable t) {
                    }
                });
            }

        });

        binding.blockMakingTV.setOnClickListener(view -> {
            if (!binding.nameOfBlockedUserMakingET.getText().toString().isEmpty()) {
                makeBlockDialog(binding.nameOfBlockedUserMakingET.getText().toString());
            }
        });
        binding.blocksRV.setLayoutManager(new LinearLayoutManager(this));
        blockListViewModel.getBlockedUser(Constants.getCurrentUsername(this));
        binding.setLifecycleOwner(this);

    }

    private void selectUserFromDialog(ArrayList<UserForSearchDTO> list) {
        dialog = new SelectItemDialog(this, list) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            }
        };

        dialog.show();
        dialog.setReceivedListener(number -> {
            binding.nameOfBlockedUserMakingET.setText(list.get(number).getName());
            dialog.dismiss();
        });
    }

    private void makeBlockDialog(String username) {
        blockDialog = new BlockDialog(context, username) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            }
        };
        blockDialog.show();
        blockDialog.setReceivedListener(new OnReceivedStatus() {
            @Override
            public void Received(int number) {
                    if (number == 1) {
                        // without
                blockUser(new BlockUserDTO(Constants.getCurrentUsername(context),
                        binding.nameOfBlockedUserMakingET.getText().toString(),
                        true,
                        false));

                    }
                    if (number == 2) {
                        // with
                        blockUser(new BlockUserDTO(Constants.getCurrentUsername(context),
                                binding.nameOfBlockedUserMakingET.getText().toString(),
                                true,
                                true));
                    }
                }
        });
    }
    private void blockUser(BlockUserDTO blockUserDTO){
        BlockClient.getInstance().CreateOrDeleteBlock(blockUserDTO).enqueue(new Callback<BlockUserDTO>() {
            @Override
            public void onResponse(@NonNull Call<BlockUserDTO> call, @NonNull Response<BlockUserDTO> response) {
                Intent callBack = new Intent(context, BlocklistActivity.class);
                context.startActivity(callBack);

            }

            @Override
            public void onFailure(@NonNull Call<BlockUserDTO> call, @NonNull Throwable t) {

            }
        });
    }
}