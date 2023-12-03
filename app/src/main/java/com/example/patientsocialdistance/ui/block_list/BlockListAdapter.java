package com.example.patientsocialdistance.ui.block_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientsocialdistance.R;
import com.example.patientsocialdistance.data.Clients.BlockClient;
import com.example.patientsocialdistance.pojo.DTOs.BlockUserDTO;
import com.example.patientsocialdistance.pojo.DTOs.BlockedUserDTO;
import com.example.patientsocialdistance.utilities.Constants;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlockListAdapter extends RecyclerView.Adapter<BlockListAdapter.BlockViewHolder>{
    Context mContext;
    private ArrayList<BlockedUserDTO> list = new ArrayList<>();
    public BlockListAdapter(Context context){
        mContext = context;
    }
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
        holder.blockedName.setText(list.get(position).name);
        holder.blockHospital.setText(list.get(position).hospital);
        holder.blockPhoneNumber.setText(list.get(position).phoneNumber);

        holder.CancelBlock.setOnClickListener(view -> {
            blockUser(new BlockUserDTO(Constants.getCurrentUsername(mContext),
                   list.get(position).name,
                    false,
                    false));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<BlockedUserDTO> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public static class BlockViewHolder extends RecyclerView.ViewHolder {
        TextView blockedName, blockHospital, blockPhoneNumber, CancelBlock;
        public BlockViewHolder(@NonNull View itemView) {
            super(itemView);
            blockedName = itemView.findViewById(R.id.patientVisitorNameBlockCardTV);
            blockHospital = itemView.findViewById(R.id.patientVisitorHospitalBlockCardTV);
            blockPhoneNumber = itemView.findViewById(R.id.patientVisitorPhoneNumberBlockCardTV);
            CancelBlock = itemView.findViewById(R.id.patientVisitorCancelBlockCardTV);
        }
    }
    private void blockUser(BlockUserDTO blockUserDTO){

        BlockClient.getInstance().CreateOrDeleteBlock(blockUserDTO).enqueue(new Callback<BlockUserDTO>() {
            @Override
            public void onResponse(@NonNull Call<BlockUserDTO> call, @NonNull Response<BlockUserDTO> response) {
                Intent callBack = new Intent(mContext, BlocklistActivity.class);
                mContext.startActivity(callBack);

            }

            @Override
            public void onFailure(@NonNull Call<BlockUserDTO> call, @NonNull Throwable t) {

            }
        });
    }
}
