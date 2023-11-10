package com.example.patientsocialdistance.ui.patient_schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.pojo.DTOs.VisitDto;

import java.util.ArrayList;
import java.util.List;

public class ShowVisitsAdapter extends RecyclerView.Adapter<ShowVisitsAdapter.ShowVisitViewHolder>{
    List<VisitDto> mList;
    Context mContext;
    public ShowVisitsAdapter(Context context, ArrayList<VisitDto> list){
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ShowVisitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowVisitViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.patient_schedule_view_card,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowVisitViewHolder holder, int position) {
        holder.nameTV.setText(mList.get(position).getVisitorUsername());
        holder.dateTV.setText(mList.get(position).getVisitDate());
        holder.MessageTV.setText(mList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<VisitDto> list) {
        this.mList = list;
        notifyDataSetChanged();
    }
    public static class ShowVisitViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, dateTV, MessageTV;

        public ShowVisitViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.patientVisitorNamePatientScheduleCardTV);
            dateTV = itemView.findViewById(R.id.patientVisitorDatePatientScheduleCardTV);
            MessageTV = itemView.findViewById(R.id.patientVisitorMessagePatientScheduleCardTV);
        }
    }
}
