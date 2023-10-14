package com.example.patientsocialdistance.ui.visit_schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.databinding.ActivityVisitSheduleBinding;

public class VisitSheduleActivity extends AppCompatActivity {

    ActivityVisitSheduleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_visit_shedule);

        setContentView(R.layout.activity_visit_shedule);
//        //get the spinner from the xml.
//        Spinner dropdown = findViewById(R.id.spinner1);
//        //create a list of items for the spinner.
//        String[] items = new String[]{"User 1", "User 2", "User 3"};
//        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
//        //There are multiple variations of this, but this is the basic variant.
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//        //set the spinners adapter to the previously created one.
//        dropdown.setAdapter(adapter);
    }
}