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

public class AllUsers extends AppCompatActivity {

    Menu menu;
    DrawerLayout drawerLayout1;

    FirebaseFirestore firebaseFirestore;
    RecyclerView mFirestoreList;
    FirestoreAdapter adapter;
    ArrayList<UsersModel> usersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        EditText editText = findViewById(R.id.search_bar);
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

    private void filter(String text){
        ArrayList<UsersModel> filteredList = new ArrayList<>();

        for (UsersModel item: usersList)
        {
            if(item.getBloodType().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        adapter.filteredList(filteredList);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_ascAge:
                Collections.sort(usersList,UsersModel.ageSort);
                Toast.makeText(AllUsers.this, "Sort by Age", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.menu_ascName:
                Collections.sort(usersList,UsersModel.sortName);
                Toast.makeText(AllUsers.this, "Sort by Name", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.menu_ascLBP:
                Collections.sort(usersList,UsersModel.sortLBP);
                Toast.makeText(AllUsers.this, "Sort by low blood pressure", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.menu_ascHBP:
                Collections.sort(usersList,UsersModel.sortHBP);
                Toast.makeText(AllUsers.this, "Sort by high blood pressure", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.menu_ascbloodtype:
                Collections.sort(usersList,UsersModel.sortBloodType);
                Toast.makeText(AllUsers.this, "Sort by Blood Type", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }


}



