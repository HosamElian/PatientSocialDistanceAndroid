package com.example.patientsocialdistance.ui.notification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.NotificationClient;
import com.example.patientsocialdistance.pojo.DTOs.BlockUserDTO;
import com.example.patientsocialdistance.pojo.DTOs.ChangeRequestDTO;
import com.example.patientsocialdistance.pojo.DTOs.NotificationDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitorRequestVisitDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{

    Context mContext;
    private ArrayList<NotificationDTO> list = new ArrayList<>();
    public NotificationAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.notification_view_card,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationViewHolder holder, int position) {
        holder.acceptOrRejectLL.setVisibility(View.GONE);
        holder.usernameTV.setText(list.get(position).makeActionUsername);
        holder.messageTV.setText(list.get(position).message);
        if(list.get(position).isChangeDate){
            holder.acceptOrRejectLL.setVisibility(View.VISIBLE);
            holder.rejectChangeIV.setOnClickListener(view -> {
                NotificationClient.getInstance().ChangeDateResponse(
                        new ChangeRequestDTO(list.get(position).visitId,
                                list.get(position).id,
                                false)).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        Intent navIntent = new Intent(mContext, NotificationActivity.class);
                        mContext.startActivity(navIntent);
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                    }
                });
            });
            holder.acceptChangeIV.setOnClickListener(view -> {
                NotificationClient.getInstance().ChangeDateResponse(
                        new ChangeRequestDTO(list.get(position).visitId,
                                list.get(position).id,
                                true)).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        Intent navIntent = new Intent(mContext, NotificationActivity.class);
                        mContext.startActivity(navIntent);
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                    }
                });
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<NotificationDTO> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTV, messageTV;
        ImageView acceptChangeIV, rejectChangeIV;
        LinearLayout acceptOrRejectLL;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTV = itemView.findViewById(R.id.usernameOfUserMakeActionTV);
            messageTV = itemView.findViewById(R.id.notificationMessageTV);
            acceptChangeIV = itemView.findViewById(R.id.acceptChangeIV);
            rejectChangeIV = itemView.findViewById(R.id.rejectChangeIV);
            acceptOrRejectLL = itemView.findViewById(R.id.acceptOrRejectLL);

        }
    }
}
