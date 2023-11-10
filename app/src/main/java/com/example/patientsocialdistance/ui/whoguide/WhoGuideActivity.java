package com.example.patientsocialdistance.ui.whoguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityWhoGuideBinding;

public class WhoGuideActivity extends AppCompatActivity {
    ActivityWhoGuideBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_who_guide);
        binding.setLifecycleOwner(this);
    }
}