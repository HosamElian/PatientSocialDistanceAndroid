package com.example.patientsocialdistance.ui.visit_schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.UserClient;
import com.example.patientsocialdistance.data.Clients.VisitClient;
import com.example.patientsocialdistance.databinding.ActivityVisitSheduleBinding;
import com.example.patientsocialdistance.pojo.DTOs.UserForSearchDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitDto;
import com.example.patientsocialdistance.utilities.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitScheduleActivity extends AppCompatActivity {
    Calendar calendar;
    String searchValue;
    SelectItemDialog dialog;
    ArrayList<UserForSearchDTO> list;
    Context context;
    ActivityVisitSheduleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_visit_shedule);
        binding.setLifecycleOwner(this);
        context = this;

        binding.searchSpannerLL.setVisibility(View.GONE);
        binding.searchIB.setOnClickListener(view ->
        {
            searchValue =  binding.userET.getText().toString();
//            Toast.makeText(context, "Text: " + searchValue, Toast.LENGTH_SHORT).show();

            if(!searchValue.isBlank()  &&!searchValue.isEmpty()){
                UserClient.getInstance().GetUserByUsername(searchValue).enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserForSearchDTO>> call, @NonNull Response<List<UserForSearchDTO>> response) {
                        if (200 == response.code() && null != response.body() && response.body().size() > 0) {
                            list = new ArrayList<>(response.body());
                            selectUserFromDialog(list);
                        }
                        else if (400 == response.code() && null != response.body()) {
                            Toast.makeText(context, "Can't Searching without writing any name", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "No user Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserForSearchDTO>> call, @NonNull Throwable t) {
                    }
                });
            }
            else {
                Toast.makeText(this, "not text", Toast.LENGTH_SHORT).show();

            }

        });

        binding.dateOfVisitIV.setOnClickListener(view ->  openCalenderDialog() );

        binding.requestVisitBT.setOnClickListener(view -> {
            VisitClient.getInstance().ReserveVisit(new VisitDto(
                    Constants.getCurrentUsername(context),
                    binding.userET.getText().toString(),
                    binding.dateOfVisitTV.getText().toString(),
                    binding.messageOfVisitTV.getText().toString()
            )).enqueue(new Callback<>() {
                @Override
                public void onResponse(@NonNull Call<VisitDto> call, @NonNull Response<VisitDto> response) {
                    Toast.makeText(context, "Request Send Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, List.class);
                    context.startActivity(intent);
                }

                @Override
                public void onFailure(@NonNull Call<VisitDto> call, @NonNull Throwable t) {
                    Toast.makeText(context, "Request Send Successfully", Toast.LENGTH_SHORT).show();

                }
            });
        });
    }

    private void selectUserFromDialog(ArrayList<UserForSearchDTO> list) {
        dialog = new SelectItemDialog(this, list) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            }
        };

        dialog.show();
        dialog.setReceivedListener(number -> {
            binding.userET.setText(list.get(number).getName());
            dialog.dismiss();
        });
    }

    private void openCalenderDialog() {
        Date date = new Date();
        calendar = new GregorianCalendar();
        calendar.setTime(date);
        @SuppressLint("SetTextI18n") DatePickerDialog dialog = new DatePickerDialog(this,
                (datePicker, year, month, day) -> binding.dateOfVisitTV.setText(day + "/" + month + "/" + year),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

}