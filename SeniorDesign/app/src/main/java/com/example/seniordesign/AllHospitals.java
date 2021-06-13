package com.example.seniordesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AllHospitals extends AppCompatActivity {

    private RecyclerView FStoreListHospital;
    private FirebaseFirestore firebaseFirestoreH;
    private FirestoreRecyclerAdapter adapterH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hospitals);

        FStoreListHospital = findViewById(R.id.firestore_listHospital);
        firebaseFirestoreH = FirebaseFirestore.getInstance();

        Query query = firebaseFirestoreH.collection("hospitals");

        FirestoreRecyclerOptions<HospitalsModel> optionsH = new FirestoreRecyclerOptions.Builder<HospitalsModel>()
                .setQuery(query, HospitalsModel.class).build();

        adapterH = new FirestoreRecyclerAdapter<HospitalsModel, AllHospitals.HospitalsViewHolder>(optionsH) {
            @NonNull
            @Override
            public AllHospitals.HospitalsViewHolder onCreateViewHolder(@NonNull ViewGroup parentH, int viewType) {

                View view = LayoutInflater.from(parentH.getContext()).inflate(R.layout.list_sing_hospitals, parentH, false);
                return new HospitalsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull AllHospitals.HospitalsViewHolder holderH, int position, @NonNull HospitalsModel model) {
                holderH.listnameH.setText(model.getHospitalName());
                holderH.listemailH.setText(model.getHospitalEmail());
            }
        };

        FStoreListHospital.setHasFixedSize(true);
        FStoreListHospital.setLayoutManager(new LinearLayoutManager(this));
        FStoreListHospital.setAdapter(adapterH);
    }

    private class HospitalsViewHolder extends RecyclerView.ViewHolder {

        private TextView listnameH, listemailH;

        public HospitalsViewHolder(@NonNull View itemView) {
            super(itemView);

            listnameH = itemView.findViewById(R.id.listnameH);
            listemailH = itemView.findViewById(R.id.listemailH);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterH.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterH.startListening();
    }
}

