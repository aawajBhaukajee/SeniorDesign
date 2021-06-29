 package com.example.seniordesign;

 import android.os.Bundle;
 import android.view.View;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.drawerlayout.widget.DrawerLayout;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.google.android.gms.tasks.OnSuccessListener;
 import com.google.firebase.firestore.DocumentSnapshot;
 import com.google.firebase.firestore.FirebaseFirestore;
 import com.google.firebase.firestore.QuerySnapshot;

 import java.util.ArrayList;
 import java.util.List;

 public class AllHospitals extends AppCompatActivity{

    DrawerLayout drawerLayout;

    RecyclerView FStoreListHospital;
    ArrayList<HospitalsModel> hospitalList;
    FirebaseFirestore firebaseFirestoreH;
    FirestoreAdapterHospital adapterH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hospitals);

        drawerLayout=findViewById(R.id.drawerLayout);

        FStoreListHospital = findViewById(R.id.firestore_listHospital);
        FStoreListHospital.setLayoutManager(new LinearLayoutManager(this));
        hospitalList = new ArrayList<>();
        firebaseFirestoreH = FirebaseFirestore.getInstance();
        adapterH  = new FirestoreAdapterHospital(hospitalList);
        FStoreListHospital.setAdapter(adapterH);

        firebaseFirestoreH.collection("hospitals").orderBy("HospitalName").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> listH = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:listH)
                {

                    HospitalsModel Hobj = d.toObject(HospitalsModel.class);
                    hospitalList.add(Hobj);
                }
                adapterH.notifyDataSetChanged();
            }
        });

    }


    public void ClickMenu(View view){
        navigation.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        navigation.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        navigation.redirectActivity(this,navigation.class);
    }

    public void ClickProfile(View view){
        navigation.redirectActivity(this,MainActivity.class);
    }

    public void ClickListHospital(View view){

        recreate();

    }

    public void ClickLogout(View view){
        navigation.redirectActivity(this, firstPage.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigation.closeDrawer(drawerLayout);
    }


}

