package com.example.patientsocialdistance.ui.accept_appointments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.VisitClient;
import com.example.patientsocialdistance.pojo.DTOs.VisitApprovalDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitorDto;
import com.example.patientsocialdistance.pojo.DTOs.VisitorRequestVisitDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitAcceptAppointmentListAdapter extends RecyclerView.Adapter<VisitAcceptAppointmentListAdapter.VisitsViewHolder> {
    Calendar calendar;

    private ArrayList<VisitorRequestVisitDTO> list = new ArrayList<>();

    @NonNull
    @Override
    public VisitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VisitsViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.accept_appointments_list_card_view,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull VisitsViewHolder holder, int position) {
        holder.visitorNameTV.setText(list.get(position).visitorName);
        holder.visitDateTV.setText(list.get(position).visitDate);
        holder.visitMessageTV.setText(list.get(position).visitMessage);
        holder.timeET.setText(String.valueOf(list.get(position).durationInMinutes));

        holder.acceptIV.setOnClickListener(view -> sendApproveResult(holder.itemView.getContext(),
                2, holder.getAbsoluteAdapterPosition(), list.get(position).durationInMinutes,
                list.get(position).isStartDateChange,list.get(position).newDate));
        holder.rejectIV.setOnClickListener(view -> sendApproveResult(holder.itemView.getContext(),3,
                holder.getAbsoluteAdapterPosition(), list.get(position).durationInMinutes,
                list.get(position).isStartDateChange,list.get(position).newDate));
        holder.editIV.setOnClickListener(view -> {
                    openCalenderDialog(holder.itemView.getContext(), holder, position );
                }
        );
    }

    private void sendApproveResult(Context context, int result, int position, int durationInMinutes,
        Boolean isChanged, String newDate) {
        VisitApprovalDTO visitApprovalDTO = new VisitApprovalDTO(list.get(position).visitId,
                result,
                durationInMinutes,
                isChanged,
                newDate);

        VisitClient.getInstance().SetVisitApproval(visitApprovalDTO).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<VisitApprovalDTO> call, @NonNull Response<VisitApprovalDTO> response) {
                if (200 == response.code()){
                    Toast.makeText(context, "Complete Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, AcceptAppointmentsActivity.class);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(@NonNull Call<VisitApprovalDTO> call, @NonNull Throwable t) {
                Toast.makeText(context, "Can't complete!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<VisitorRequestVisitDTO> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class VisitsViewHolder extends RecyclerView.ViewHolder {
        TextView visitorNameTV, visitDateTV, visitMessageTV;
        ImageView acceptIV, rejectIV, editIV;
        EditText timeET;

        public VisitsViewHolder(@NonNull View itemView) {
            super(itemView);
            visitorNameTV = itemView.findViewById(R.id.patientVisitorNameAppointmentCardTV);
            visitMessageTV = itemView.findViewById(R.id.patientVisitorMessageAppointmentCardTV);
            visitDateTV = itemView.findViewById(R.id.patientVisitorDateAppointmentCardTV);
            acceptIV = itemView.findViewById(R.id.acceptIV);
            rejectIV = itemView.findViewById(R.id.rejectIV);
            editIV = itemView.findViewById(R.id.editIV);
            timeET = itemView.findViewById(R.id.timeET);
        }
    }
    private void openCalenderDialog(Context context, @NonNull VisitsViewHolder holder, int position) {
        Date date = new Date();
        calendar = new GregorianCalendar();
        calendar.setTime(date);
        @SuppressLint("SetTextI18n") DatePickerDialog dialog = new DatePickerDialog(context,
                (datePicker, year, month, day) ->{

                    list.get(position).setNewDate(year + "/" + month + "/" +day);
                    list.get(position).setStartDateChange(true);
                    holder.visitDateTV.setText(day + "/" + month + "/" + year);
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
