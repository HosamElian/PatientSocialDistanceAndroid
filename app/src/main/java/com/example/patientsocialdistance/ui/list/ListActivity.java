package com.example.patientsocialdistance.ui.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityListBinding;
import com.example.patientsocialdistance.ui.accept_appointments.AcceptAppointmentsActivity;
import com.example.patientsocialdistance.ui.block_list.BlocklistActivity;
import com.example.patientsocialdistance.ui.interactionlist.InteractionListActivity;
import com.example.patientsocialdistance.ui.patient_schedule.PatientScheduleActivity;
import com.example.patientsocialdistance.ui.visit_schedule.VisitSheduleActivity;
import com.example.patientsocialdistance.ui.whoguide.WhoGuideActivity;

public class ListActivity extends AppCompatActivity {
    ActivityListBinding binding;
    Intent navigationIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        binding.interactionTV.setOnClickListener(view -> {
            navigationIntent = new Intent(this, InteractionListActivity.class);
            startActivity(navigationIntent);
        });

        binding.patientScheduleTV.setOnClickListener(view -> {
            navigationIntent = new Intent(this, PatientScheduleActivity.class);
            startActivity(navigationIntent);
        });

        binding.visitTV.setOnClickListener(view -> {
            navigationIntent = new Intent(this, VisitSheduleActivity.class);
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
    }

}