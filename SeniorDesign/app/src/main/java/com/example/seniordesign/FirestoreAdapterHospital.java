package com.example.seniordesign;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FirestoreAdapterHospital extends FirestoreRecyclerAdapter<HospitalsModel, FirestoreAdapterHospital.HospitalsViewHolder>{

    private OnListItemClick onListItemClick;

    public FirestoreAdapterHospital(@NonNull FirestoreRecyclerOptions<HospitalsModel> optionsH,OnListItemClick onListItemClick) {
        super(optionsH);
        this.onListItemClick = onListItemClick;

    }

    @Override
    protected void onBindViewHolder(@NonNull FirestoreAdapterHospital.HospitalsViewHolder holderH, int position, @NonNull HospitalsModel model) {
        holderH.listnameH.setText(model.getHospitalName());
        holderH.listemailH.setText(model.getHospitalEmail());
        holderH.listlocationH.setText(model.getHospitalLocation());
    }

    @NonNull

    @Override
    public HospitalsViewHolder onCreateViewHolder(@NonNull ViewGroup parentH, int viewType) {
        View view = LayoutInflater.from(parentH.getContext()).inflate(R.layout.list_sing_hospitals, parentH, false);
        return new HospitalsViewHolder(view);
    }

    public class HospitalsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView listnameH, listemailH, listlocationH;

        public HospitalsViewHolder(@NonNull View itemView) {
            super(itemView);

            listnameH = itemView.findViewById(R.id.listnameH);
            listemailH = itemView.findViewById(R.id.listemailH);
            listlocationH = itemView.findViewById(R.id.listlocationH);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClick.onItemClick();

        }
    }

    public interface OnListItemClick{
        void onItemClick();
    }
}
