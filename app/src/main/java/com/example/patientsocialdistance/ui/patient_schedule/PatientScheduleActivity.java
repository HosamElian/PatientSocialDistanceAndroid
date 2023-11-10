package com.example.patientsocialdistance.ui.patient_schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.VisitClient;
import com.example.patientsocialdistance.databinding.ActivityPatientScheduleBinding;
import com.example.patientsocialdistance.pojo.DTOs.VisitDto;
import com.example.patientsocialdistance.utilities.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientScheduleActivity extends AppCompatActivity {

    Context context;
    String selectedDate;
    ArrayList<VisitDto> list;

    ShowVisitsDialog dialog;

    ActivityPatientScheduleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_schedule);
        binding.setLifecycleOwner(this);
        context = this;
        binding.patientScheduleCV.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

            binding.patientVisitDateDayTV.setText(String.valueOf(dayOfMonth));
            binding.patientVisitDateMonthTV.setText(getMonthNameFromNumber(month));

            ++month;
            selectedDate = month + "/" + dayOfMonth + "/" + year;
            getVisitsByDate(selectedDate);
        });

    }

    private void getVisitsByDate(String selectedDate) {
        VisitClient.getInstance().GetAllApprovedByDate(Constants.getCurrentUsername(), selectedDate).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<VisitDto>> call, @NonNull Response<List<VisitDto>> response) {
                if (200 == response.code() && null != response.body()) {
                    if (response.body().size() > 0) {
                        list = new ArrayList<>(response.body());
                        ShowVisits(list);
                    } else {
                        Toast.makeText(context, "No visit Available", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<VisitDto>> call, @NonNull Throwable t) {
                Toast.makeText(context, "No visit Available", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ShowVisits(ArrayList<VisitDto> list) {
        dialog = new ShowVisitsDialog(this, list) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            }
        };

        dialog.show();

    }

    private String getMonthNameFromNumber(int monthNumber) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        cal.set(Calendar.MONTH, monthNumber);
        return month_date.format(cal.getTime());
    }
}