package com.example.patientsocialdistance.ui.block_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityBlocklistBinding;
import com.example.patientsocialdistance.pojo.DTOs.BlockedUserDTO;
import com.example.patientsocialdistance.utilities.Constants;

import java.util.ArrayList;

public class BlocklistActivity extends AppCompatActivity {
    BlockListViewModel blockListViewModel;
    ActivityBlocklistBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_blocklist);
        blockListViewModel = ViewModelProviders.of(this).get(BlockListViewModel.class);
        binding.setBlockListViewModel(blockListViewModel);
        BlockListAdapter adapter = new BlockListAdapter();
        binding.blocksRV.setAdapter(adapter);
        binding.blockNumberTV.setText("0");
        blockListViewModel.blockDtoMutableLiveData.observe(this, blockedUserDTOS -> {
            adapter.setList(blockedUserDTOS);
            if( blockedUserDTOS.size() > 0){
                binding.blockNumberTV.setText(String.valueOf(blockedUserDTOS.size()));
                Log.d("login", " blockedUserDTOS "  + blockedUserDTOS.size());
            }
        });

        binding.blocksRV.setLayoutManager(new LinearLayoutManager(this));
        blockListViewModel.getBlockedUser(Constants.getCurrentUsername(this));
        binding.setLifecycleOwner(this);

    }
}