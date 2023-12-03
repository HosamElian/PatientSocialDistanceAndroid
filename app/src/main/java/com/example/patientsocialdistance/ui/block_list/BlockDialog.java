package com.example.patientsocialdistance.ui.block_list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.BlockClient;
import com.example.patientsocialdistance.data.Clients.VisitClient;
import com.example.patientsocialdistance.pojo.DTOs.BlockUserDTO;
import com.example.patientsocialdistance.ui.visit_schedule.interfaces.OnReceivedData;
import com.example.patientsocialdistance.ui.visit_schedule.interfaces.OnReceivedStatus;
import com.example.patientsocialdistance.utilities.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlockDialog extends AlertDialog {
    private OnReceivedStatus mListener = null;
    private String mUserName;
    TextView confirm, cancel, username;
    RadioButton notify, noNotification;
    Context mContext;
    public BlockDialog(Context context, String username) {
        super(context);
        mContext = context;
        this.mUserName = username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState != null
                ? savedInstanceState
                : new Bundle());

        View view = View.inflate(getContext(),R.layout.block_dialog, null);
        setContentView(view);

        username = view.findViewById(R.id.blockDialogNameTV);
        cancel = view.findViewById(R.id.blockDialogCancelTV);
        confirm = view.findViewById(R.id.blockDialogConfirmTV);
        notify = view.findViewById(R.id.blockDialogNotify);
        noNotification = view.findViewById(R.id.blockDialogNoNotification);

        noNotification.setChecked(true);
        noNotification.setActivated(true);
        notify.setChecked(false);
        username.setText(mUserName);

        cancel.setOnClickListener( view1 -> {
            mListener.Received(0);
        });

        confirm.setOnClickListener(value -> {
            if(noNotification.isChecked()){

                mListener.Received(1);
            }else{
                mListener.Received(2);
            }
        });

        setCanceledOnTouchOutside(true);

        setCancelable(true);
    }

    public void setReceivedListener(OnReceivedStatus listener) {
        this.mListener = listener;
    }
}
