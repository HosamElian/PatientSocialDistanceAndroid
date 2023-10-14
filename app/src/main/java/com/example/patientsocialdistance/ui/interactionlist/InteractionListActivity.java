package com.example.patientsocialdistance.ui.interactionlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityInteractionListBinding;

public class InteractionListActivity extends AppCompatActivity {

    ActivityInteractionListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_interaction_list);
//        setContentView(R.layout.activity_interaction_list);

    }
}