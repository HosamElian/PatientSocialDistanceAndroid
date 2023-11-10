package com.example.patientsocialdistance.ui.interactionlist;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.pojo.DTOs.InteractionForUserDTO;

import java.util.ArrayList;

public class InteractionListAdapter extends RecyclerView.Adapter<InteractionListAdapter.InteractionListViewHolder>{

    private ArrayList<InteractionForUserDTO> interactionForUserDtoArrayList = new ArrayList<>();
    @NonNull
    @Override
    public InteractionListAdapter.InteractionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InteractionListViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.interaction_list_card_view,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull InteractionListAdapter.InteractionListViewHolder holder, int position) {
        holder.name.setText(interactionForUserDtoArrayList.get(position).name);
        holder.time.setText(String.valueOf(interactionForUserDtoArrayList.get(position).time));
        if(interactionForUserDtoArrayList.get(position).time >= 30){
            holder.linearLayout.setBackground(ContextCompat.getDrawable(holder.linearLayout.getContext(), R.drawable.rounded_border_red));
        }else {
            holder.linearLayout.setBackground(ContextCompat.getDrawable(holder.linearLayout.getContext(), R.drawable.rounded_border_green));

        }
    }

    @Override
    public int getItemCount() {
        return interactionForUserDtoArrayList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<InteractionForUserDTO> list){
        this.interactionForUserDtoArrayList = list;
        notifyDataSetChanged();
    }

    public static class InteractionListViewHolder extends RecyclerView.ViewHolder {
        TextView name, time;
        LinearLayout linearLayout;
        public InteractionListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameOfInteractedUserTV);
            time = itemView.findViewById(R.id.timeOfInteractedUserTV);
            linearLayout = itemView.findViewById(R.id.interactedUserLL);
        }
    }
}
