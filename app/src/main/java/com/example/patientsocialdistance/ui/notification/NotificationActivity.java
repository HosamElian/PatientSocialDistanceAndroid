package com.example.patientsocialdistance.ui.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityNotificationBinding;
import com.example.patientsocialdistance.pojo.DTOs.NotificationDTO;
import com.example.patientsocialdistance.utilities.Constants;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    Context mContext;
    ActivityNotificationBinding binding;
    NotificationViewModel notificationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
        mContext = this;
        notificationViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);
        binding.setNotificationViewModel(notificationViewModel);
        NotificationAdapter adapter = new NotificationAdapter(mContext);
        binding.notificationRV.setAdapter(adapter);

        notificationViewModel.notificationMutableLiveData.observe(this, new Observer<ArrayList<NotificationDTO>>() {
            @Override
            public void onChanged(ArrayList<NotificationDTO> notificationDTOS) {
                adapter.setList(notificationDTOS);
            }
        });
        binding.notificationRV.setLayoutManager(new LinearLayoutManager(this));
        notificationViewModel.getNotifications(Constants.getCurrentUsername(mContext));

        binding.setLifecycleOwner(this);
    }
}