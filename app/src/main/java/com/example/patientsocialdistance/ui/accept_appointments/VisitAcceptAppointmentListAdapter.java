package com.example.patientsocialdistance.ui.accept_appointments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.VisitClient;
import com.example.patientsocialdistance.pojo.DTOs.VisitApprovalDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitorDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitAcceptAppointmentListAdapter extends RecyclerView.Adapter<VisitAcceptAppointmentListAdapter.VisitsViewHolder> {

    private ArrayList<VisitorDto> list = new ArrayList<>();

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
        holder.acceptIV.setOnClickListener(view -> sendApproveResult(holder.itemView.getContext(), 2, holder.getAbsoluteAdapterPosition()));
        holder.rejectIV.setOnClickListener(view -> sendApproveResult(holder.itemView.getContext(),3, holder.getAbsoluteAdapterPosition()));
        holder.editIV.setOnClickListener(view -> Toast.makeText(holder.itemView.getContext(), "need Task", Toast.LENGTH_SHORT).show());
    }

    private void sendApproveResult(Context context, int result, int position) {
        VisitApprovalDTO visitApprovalDTO = new VisitApprovalDTO(list.get(position).visitId, result);

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
    public void setList(ArrayList<VisitorDto> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class VisitsViewHolder extends RecyclerView.ViewHolder {
        TextView visitorNameTV, visitDateTV, visitMessageTV;
        ImageView acceptIV, rejectIV, editIV;

        public VisitsViewHolder(@NonNull View itemView) {
            super(itemView);
            visitorNameTV = itemView.findViewById(R.id.patientVisitorNameAppointmentCardTV);
            visitMessageTV = itemView.findViewById(R.id.patientVisitorMessageAppointmentCardTV);
            visitDateTV = itemView.findViewById(R.id.patientVisitorDateAppointmentCardTV);
            acceptIV = itemView.findViewById(R.id.acceptIV);
            rejectIV = itemView.findViewById(R.id.rejectIV);
            editIV = itemView.findViewById(R.id.editIV);
        }
    }
}
