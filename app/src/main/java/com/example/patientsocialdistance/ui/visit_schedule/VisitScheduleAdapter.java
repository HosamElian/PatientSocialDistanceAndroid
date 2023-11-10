package com.example.patientsocialdistance.ui.visit_schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.pojo.DTOs.UserForSearchDTO;
import com.example.patientsocialdistance.ui.visit_schedule.interfaces.OnAdapterItemClicked;

import java.util.ArrayList;
import java.util.List;

public class VisitScheduleAdapter extends RecyclerView.Adapter<VisitScheduleAdapter.VisitScheduleViewHolder>{
    List<UserForSearchDTO> mList;
    Context mContext;
    private OnAdapterItemClicked mListener = null;
    public VisitScheduleAdapter(Context context, ArrayList<UserForSearchDTO> list){
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public VisitScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VisitScheduleAdapter.VisitScheduleViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.user_dialog_view_card,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull VisitScheduleViewHolder holder, int position) {
        holder.nameTV.setText(mList.get(position).getName());
        holder.nameTV.setOnClickListener(view -> {
            mListener.itemClicked(holder.getAbsoluteAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<UserForSearchDTO> list) {
        this.mList = list;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnAdapterItemClicked listener) {
        this.mListener = listener;
    }
    public static class VisitScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;

        public VisitScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.userNameFromSearchTV);
        }
    }
}
