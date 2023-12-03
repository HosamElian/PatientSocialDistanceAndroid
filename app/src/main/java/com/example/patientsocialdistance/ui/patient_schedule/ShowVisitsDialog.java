package com.example.patientsocialdistance.ui.patient_schedule;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.pojo.DTOs.UserForSearchDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitDto;
import com.example.patientsocialdistance.pojo.DTOs.VisitsAcceptedDTO;
import com.example.patientsocialdistance.ui.visit_schedule.VisitScheduleAdapter;
import com.example.patientsocialdistance.ui.visit_schedule.interfaces.OnAdapterItemClicked;
import com.example.patientsocialdistance.ui.visit_schedule.interfaces.OnReceivedData;

import java.util.ArrayList;

public abstract class ShowVisitsDialog extends AlertDialog {

    private final ArrayList<VisitsAcceptedDTO> mList;
    private ShowVisitsAdapter adapter;

    public ShowVisitsDialog(Context context, ArrayList<VisitsAcceptedDTO> list) {
        super(context);
        this.mList = list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState != null
                ? savedInstanceState
                : new Bundle());

        View view = View.inflate(getContext(),R.layout.show_visits_dialog, null);

        setContentView(view);

        setCanceledOnTouchOutside(true);

        setCancelable(true);

        setUpRecyclerView(view);
    }

    private void setUpRecyclerView(View view) {
        RecyclerView showVisitRV = view.findViewById(R.id.showVisitRV);
        showVisitRV.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShowVisitsAdapter(getContext(), mList);
        showVisitRV.setAdapter(adapter);

    }
}
