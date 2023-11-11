package com.example.patientsocialdistance.ui.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.BlockClient;
import com.example.patientsocialdistance.databinding.ActivityListBinding;
import com.example.patientsocialdistance.pojo.DTOs.BlockedUserDTO;
import com.example.patientsocialdistance.ui.accept_appointments.AcceptAppointmentsActivity;
import com.example.patientsocialdistance.ui.block_list.BlocklistActivity;
import com.example.patientsocialdistance.ui.interactionlist.InteractionListActivity;
import com.example.patientsocialdistance.ui.patient_schedule.PatientScheduleActivity;
import com.example.patientsocialdistance.ui.visit_schedule.VisitScheduleActivity;
import com.example.patientsocialdistance.ui.whoguide.WhoGuideActivity;
import com.example.patientsocialdistance.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    ActivityListBinding binding;
    Intent navigationIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        binding.setLifecycleOwner(this);


        binding.interactionTV.setOnClickListener(view -> {
            navigationIntent = new Intent(this, InteractionListActivity.class);
            startActivity(navigationIntent);
        });

        binding.patientScheduleTV.setOnClickListener(view -> {
            navigationIntent = new Intent(this, PatientScheduleActivity.class);
            startActivity(navigationIntent);
        });

        binding.visitTV.setOnClickListener(view -> {
            navigationIntent = new Intent(this, VisitScheduleActivity.class);
            startActivity(navigationIntent);
        });


        binding.acceptAppointmentsTV.setOnClickListener(view -> {
            navigationIntent = new Intent(this, AcceptAppointmentsActivity.class);
            startActivity(navigationIntent);
        });

        binding.whoGuideTV.setOnClickListener(view -> {
            navigationIntent = new Intent(this, WhoGuideActivity.class);
            startActivity(navigationIntent);
        });

        binding.blockListTV.setOnClickListener(view -> {
            navigationIntent = new Intent(this, BlocklistActivity.class);
            startActivity(navigationIntent);
        });

        getNumberOfUser(Constants.getCurrentUsername(this));
    }

    private void getNumberOfUser(String username){
        BlockClient.getInstance().GetBlockedUsers(username).enqueue(new Callback<List<BlockedUserDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<BlockedUserDTO>> call, @NonNull Response<List<BlockedUserDTO>> response) {
                if(200 == response.code() && null != response.body() )
                {
                    binding.numberBlockedPeopleInListActivity.setText(String.valueOf(response.body().size()));
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<BlockedUserDTO>> call, @NonNull Throwable t) {
                Log.d("login", "onFailure: " + t.getMessage());
            }
        });
    }



}