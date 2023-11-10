package com.example.patientsocialdistance.ui.interactionlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityInteractionListBinding;
import com.example.patientsocialdistance.pojo.DTOs.InteractionForUserDTO;

import java.util.ArrayList;

public class InteractionListActivity extends AppCompatActivity {
    InteractionListViewModel interactionListViewModel;
    ActivityInteractionListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_interaction_list);
        interactionListViewModel = ViewModelProviders.of(this).get(InteractionListViewModel.class);
        binding.setInteractionListViewModel(interactionListViewModel);

        InteractionListAdapter adapter = new InteractionListAdapter();
        binding.interactionsRV.setAdapter(adapter);

        interactionListViewModel.InteractionDtoMutableLiveData.observe(this, new Observer<ArrayList<InteractionForUserDTO>>() {
            @Override
            public void onChanged(ArrayList<InteractionForUserDTO> interactionForUserDTO) {
                adapter.setList(interactionForUserDTO);
            }
        });

        binding.interactionsRV.setLayoutManager(new LinearLayoutManager(this));
        binding.setLifecycleOwner(this);

        interactionListViewModel.getInteractions();

    }
}