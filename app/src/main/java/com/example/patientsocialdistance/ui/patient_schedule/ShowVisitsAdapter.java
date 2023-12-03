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
import com.example.patientsocialdistance.pojo.DTOs.VisitsAcceptedDTO;

import java.util.ArrayList;
import java.util.List;

public class ShowVisitsAdapter extends RecyclerView.Adapter<ShowVisitsAdapter.ShowVisitViewHolder>{
    List<VisitsAcceptedDTO> mList;
    Context mContext;
    public ShowVisitsAdapter(Context context, ArrayList<VisitsAcceptedDTO> list){
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
        holder.dateTV.setText(mList.get(position).getStartDate());
        holder.timeTV.setText(String.valueOf(mList.get(position).getStartTime()));
        holder.durationTV.setText(String.valueOf(mList.get(position).getDurationInMinutes()));
        holder.MessageTV.setText(mList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<VisitsAcceptedDTO> list) {
        this.mList = list;
        notifyDataSetChanged();
    }
    public static class ShowVisitViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, dateTV, timeTV, durationTV, MessageTV;

        public ShowVisitViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.patientVisitorNamePatientScheduleCardTV);
            dateTV = itemView.findViewById(R.id.patientVisitorDatePatientScheduleCardTV);
            timeTV = itemView.findViewById(R.id.patientVisitorTimePatientScheduleCardTV);
            durationTV = itemView.findViewById(R.id.patientVisitorDurationPatientScheduleCardTV);
            MessageTV = itemView.findViewById(R.id.patientVisitorMessagePatientScheduleCardTV);
        }
    }
}
