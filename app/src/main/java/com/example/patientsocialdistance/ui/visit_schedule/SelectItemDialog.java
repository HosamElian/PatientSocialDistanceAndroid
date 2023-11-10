package com.example.patientsocialdistance.ui.visit_schedule;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.pojo.DTOs.UserForSearchDTO;
import com.example.patientsocialdistance.ui.visit_schedule.interfaces.OnAdapterItemClicked;
import com.example.patientsocialdistance.ui.visit_schedule.interfaces.OnReceivedData;
import com.example.patientsocialdistance.ui.visit_schedule.VisitScheduleAdapter;

import java.util.ArrayList;

public abstract class SelectItemDialog extends AlertDialog {

    private final ArrayList<UserForSearchDTO> mList;
    private VisitScheduleAdapter adapter;
    private OnReceivedData mListener = null;

    public SelectItemDialog(Context context, ArrayList<UserForSearchDTO> list) {
        super(context);
        this.mList = list;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState != null
                ? savedInstanceState
                : new Bundle());

        View view = View.inflate(getContext(),R.layout.user_dialog, null);

        setContentView(view);

        setCanceledOnTouchOutside(true);

        setCancelable(true);

        setUpRecyclerView(view);
    }

    private void setUpRecyclerView(View view) {
        RecyclerView userSearchDialogRV = view.findViewById(R.id.userSearchDialogRV);
        userSearchDialogRV.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VisitScheduleAdapter(getContext(), mList);
        userSearchDialogRV.setAdapter(adapter);

        adapter.setOnItemClickListener(number -> mListener.Received(number));
    }
    public void setReceivedListener(OnReceivedData listener) {
        this.mListener = listener;
    }
}
