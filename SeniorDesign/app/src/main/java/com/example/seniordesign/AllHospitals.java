package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AllHospitals extends AppCompatActivity implements FirestoreAdapterHospital.OnListItemClick {

    private RecyclerView FStoreListHospital;
    private FirebaseFirestore firebaseFirestoreH;
    private FirestoreAdapterHospital adapterH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hospitals);

        FStoreListHospital = findViewById(R.id.firestore_listHospital);
        firebaseFirestoreH = FirebaseFirestore.getInstance();

        Query query = firebaseFirestoreH.collection("hospitals");

        FirestoreRecyclerOptions<HospitalsModel> optionsH = new FirestoreRecyclerOptions.Builder<HospitalsModel>()
                .setQuery(query, HospitalsModel.class).build();

        adapterH  = new FirestoreAdapterHospital(optionsH,this);

        FStoreListHospital.setHasFixedSize(true);
        FStoreListHospital.setLayoutManager(new LinearLayoutManager(this));
        FStoreListHospital.setAdapter(adapterH);
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

    @Override
    public void onItemClick() {
       // Log.d("ITEM CLICK", "Clicked an item");
        startActivity(new Intent(getApplicationContext(),schedule.class));
    }
}

