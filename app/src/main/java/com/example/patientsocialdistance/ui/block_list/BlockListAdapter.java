package com.example.patientsocialdistance.ui.block_list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.pojo.DTOs.BlockedUserDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitorDto;
import com.example.patientsocialdistance.ui.accept_appointments.VisitAcceptAppointmentListAdapter;

import java.util.ArrayList;

public class BlockListAdapter extends RecyclerView.Adapter<BlockListAdapter.BlockViewHolder>{
    private ArrayList<BlockedUserDTO> blockedVisitorDtoArrayList = new ArrayList<>();

    @NonNull
    @Override
    public BlockListAdapter.BlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BlockViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.block_list_card_view,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull BlockListAdapter.BlockViewHolder holder, int position) {
        holder.blockedName.setText(blockedVisitorDtoArrayList.get(position).name);
        holder.blockHospital.setText(blockedVisitorDtoArrayList.get(position).hospital);
        holder.blockPhoneNumber.setText(blockedVisitorDtoArrayList.get(position).phoneNumber);
    }

    @Override
    public int getItemCount() {
        return blockedVisitorDtoArrayList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<BlockedUserDTO> list){
        this.blockedVisitorDtoArrayList = list;
        notifyDataSetChanged();
    }
    public static class BlockViewHolder extends RecyclerView.ViewHolder {
        TextView blockedName, blockHospital, blockPhoneNumber;
        public BlockViewHolder(@NonNull View itemView) {
            super(itemView);
            blockedName = itemView.findViewById(R.id.patientVisitorNameBlockCardTV);
            blockHospital = itemView.findViewById(R.id.patientVisitorHospitalBlockCardTV);
            blockPhoneNumber = itemView.findViewById(R.id.patientVisitorPhoneNumberBlockCardTV);

        }
    }
}
