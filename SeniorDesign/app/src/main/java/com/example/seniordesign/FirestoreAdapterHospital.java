package com.example.seniordesign;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FirestoreAdapterHospital extends RecyclerView.Adapter<FirestoreAdapterHospital.HospitalsViewHolder> {

    ArrayList<HospitalsModel> hospitalList;

    public FirestoreAdapterHospital(ArrayList<HospitalsModel> hospitalList) {
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override
    public HospitalsViewHolder onCreateViewHolder(@NonNull ViewGroup parentH, int viewType) {
        View view = LayoutInflater.from(parentH.getContext()).inflate(R.layout.list_sing_hospitals, parentH, false);
        return new HospitalsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirestoreAdapterHospital.HospitalsViewHolder holderH, int position) {
        holderH.listnameH.setText(hospitalList.get(position).getHospitalName());
        holderH.listemailH.setText(hospitalList.get(position).getHospitalEmail());
        holderH.listlocationH.setText(hospitalList.get(position).getHospitalLocation());

        holderH.listnameH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holderH.listnameH.getContext(), timeSchedule.class);
                intent.putExtra("uname", hospitalList.get(position).getHospitalName());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holderH.listnameH.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }

    class HospitalsViewHolder extends RecyclerView.ViewHolder {

        TextView listnameH, listemailH, listlocationH;

        public HospitalsViewHolder(@NonNull View itemView) {
            super(itemView);
            listnameH = itemView.findViewById(R.id.listnameH);
            listemailH = itemView.findViewById(R.id.listemailH);
            listlocationH = itemView.findViewById(R.id.listlocationH);

        }
    }

    public void filteredList(ArrayList<HospitalsModel> filteredList) {
        hospitalList = filteredList;
        notifyDataSetChanged();
    }

}


