package com.example.patientsocialdistance.ui.accept_appointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityAcceptAppointmentsBinding;

public class AcceptAppointmentsActivity extends AppCompatActivity {

    ActivityAcceptAppointmentsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_accept_appointments);
    }
}