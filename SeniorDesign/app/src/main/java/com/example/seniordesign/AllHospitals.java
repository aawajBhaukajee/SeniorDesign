 package com.example.seniordesign;

 import android.os.Bundle;
 import android.text.Editable;
 import android.text.TextWatcher;
 import android.view.Menu;
 import android.view.MenuInflater;
 import android.view.MenuItem;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.drawerlayout.widget.DrawerLayout;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.google.android.gms.tasks.OnSuccessListener;
 import com.google.firebase.firestore.DocumentSnapshot;
 import com.google.firebase.firestore.FirebaseFirestore;
 import com.google.firebase.firestore.QuerySnapshot;

 import java.util.ArrayList;
 import java.util.Collections;
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

        EditText editText = findViewById(R.id.search_barH);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        drawerLayout=findViewById(R.id.drawerLayout);

        FStoreListHospital = findViewById(R.id.firestore_listHospital);
        FStoreListHospital.setLayoutManager(new LinearLayoutManager(this));
        hospitalList = new ArrayList<>();
        firebaseFirestoreH = FirebaseFirestore.getInstance();
        adapterH  = new FirestoreAdapterHospital(hospitalList);
        FStoreListHospital.setAdapter(adapterH);

        firebaseFirestoreH.collection("hospitals").get()
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

     private void filter(String text){
         ArrayList<HospitalsModel> filteredList = new ArrayList<>();

         for (HospitalsModel item: hospitalList)
         {
             if(item.getHospitalLocation().toLowerCase().contains(text.toLowerCase())){
                 filteredList.add(item);
             }
         }

         adapterH.filteredList(filteredList);

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

     public void ClickMap(View view){
         navigation.redirectActivity(this,MapsActivity.class);
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

    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.main_menuhospital,menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         switch(item.getItemId()) {
             case R.id.menu_ascHospitalName:
                 Collections.sort(hospitalList, HospitalsModel.hospitalSort);
                 Toast.makeText(AllHospitals.this, "Sort by Hospital Name", Toast.LENGTH_SHORT).show();
                 adapterH.notifyDataSetChanged();
                 return true;
         }
             return super.onOptionsItemSelected(item);
         }


}

