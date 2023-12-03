package com.example.patientsocialdistance.ui.patient_schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.VisitClient;
import com.example.patientsocialdistance.databinding.ActivityPatientScheduleBinding;
import com.example.patientsocialdistance.pojo.DTOs.GetVisitByDateRequest;
import com.example.patientsocialdistance.pojo.DTOs.VisitDto;
import com.example.patientsocialdistance.pojo.DTOs.VisitsAcceptedDTO;
import com.example.patientsocialdistance.utilities.Constants;
import com.example.patientsocialdistance.utilities.ImageHandler;

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
    ArrayList<VisitsAcceptedDTO> list;

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
        ImageHandler handler = new ImageHandler();
        String image = Constants.getCurrentUserImage(context);
//        Log.d("imageTag", "onCreate: " + "Image:" + image);
        Bitmap bitmap =  handler.StringToBitMap(image);
        binding.patientVisitorImageIV.setImageBitmap(bitmap);
        binding.patientVisitorHospitalTV.setText(Constants.getCurrentUsername(context));
    }

    private void getVisitsByDate(String selectedDate) {
        VisitClient.getInstance().GetAllVisitsByDate(new GetVisitByDateRequest(Constants.getCurrentUsername(context),
                true, selectedDate)).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<VisitsAcceptedDTO>> call, @NonNull Response<List<VisitsAcceptedDTO>> response) {
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
            public void onFailure(@NonNull Call<List<VisitsAcceptedDTO>> call, @NonNull Throwable t) {
                Toast.makeText(context, "No visit Available", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ShowVisits(ArrayList<VisitsAcceptedDTO> list) {
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