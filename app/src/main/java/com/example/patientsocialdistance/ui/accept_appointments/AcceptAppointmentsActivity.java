package com.example.patientsocialdistance.ui.accept_appointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityAcceptAppointmentsBinding;
import com.example.patientsocialdistance.pojo.DTOs.VisitorDto;
import com.example.patientsocialdistance.utilities.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AcceptAppointmentsActivity extends AppCompatActivity {
    Calendar calendar;
    Context mContext;
    VisitAcceptAppointmentViewModel appointViewModel;
    ActivityAcceptAppointmentsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_accept_appointments);
        mContext = this;
        appointViewModel = ViewModelProviders.of(this).get(VisitAcceptAppointmentViewModel.class);
        binding.setAppointViewModel(appointViewModel);

        VisitAcceptAppointmentListAdapter adapter = new VisitAcceptAppointmentListAdapter();
        binding.appointmentsRequestedRV.setAdapter(adapter);

        appointViewModel.appointmentDtoMutableLiveData.observe(this, new Observer<ArrayList<VisitorDto>>() {
            @Override
            public void onChanged(ArrayList<VisitorDto> visitorDtos) {
                adapter.setList(visitorDtos);
            }
        });

        binding.appointmentsRequestedRV.setLayoutManager(new LinearLayoutManager(this));
        appointViewModel.getAppointments(Constants.getCurrentUsername(mContext));

        binding.setLifecycleOwner(this);

        binding.dateOfVisitFilterIV.setOnClickListener(view -> openCalenderDialog());
        binding.clearFilterIV.setOnClickListener(view -> appointViewModel.getAppointments(Constants.getCurrentUsername(mContext)));


    }

    private void openCalenderDialog() {
        Date date = new Date();
        calendar = new GregorianCalendar();
        calendar.setTime(date);
        @SuppressLint("SetTextI18n") DatePickerDialog dialog = new DatePickerDialog(this,
                (datePicker, year, month, day) ->
                        Toast.makeText(mContext, day + "/" + month + "/" + year, Toast.LENGTH_SHORT).show(),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}