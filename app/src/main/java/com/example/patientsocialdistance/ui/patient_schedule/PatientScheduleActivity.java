package com.example.patientsocialdistance.ui.patient_schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityPatientScheduleBinding;

public class PatientScheduleActivity extends AppCompatActivity {

    ActivityPatientScheduleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_schedule);

    }
}