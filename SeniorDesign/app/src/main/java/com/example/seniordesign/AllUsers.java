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

public class AllUsers extends AppCompatActivity {

    DrawerLayout drawerLayout1;

    FirebaseFirestore firebaseFirestore;
    RecyclerView mFirestoreList;
    FirestoreAdapter adapter;
    ArrayList<UsersModel> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        drawerLayout1 = findViewById(R.id.drawerLayout1);

        mFirestoreList = findViewById(R.id.firestore_list);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        firebaseFirestore = FirebaseFirestore.getInstance();
        usersList = new ArrayList<>();
        adapter = new FirestoreAdapter(usersList);
        mFirestoreList.setAdapter(adapter);

        firebaseFirestore.collection("users").orderBy("Age").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            UsersModel obj = d.toObject(UsersModel.class);
                            usersList.add(obj);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public void ClickMenu1(View view) {
        navigationHospital.openDrawer(drawerLayout1);
    }

    public void ClickLogo1(View view) {
        navigationHospital.closeDrawer(drawerLayout1);
    }

    public void ClickHome1(View view) {
        navigationHospital.redirectActivity(this, navigationHospital.class);
    }

    public void ClickProfile1(View view) {
        navigationHospital.redirectActivity(this, hospitalProfile.class);
    }

    public void ClickListUsers(View view) {

        recreate();

    }

    public void ClickLogout1(View view) {
        navigation.redirectActivity(this, firstPage.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigation.closeDrawer(drawerLayout1);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

     */

}



