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

public class AllUsers extends AppCompatActivity
{

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList ;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.firestore_list);

        Query query = firebaseFirestore.collection("users");

        FirestoreRecyclerOptions<UsersModel> options = new FirestoreRecyclerOptions.Builder<UsersModel>()
                .setQuery(query, UsersModel.class).build();

         adapter = new FirestoreRecyclerAdapter<UsersModel, UsersViewHolder>(options) {
            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sing_users, parent, false);
                return new UsersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull UsersModel model) {
                holder.listname.setText(model.getFullName());
                holder.listemail.setText(model.getEmailAddress());
                holder.listhighbp.setText(model.getMaximumBP());
                holder.listlowbp.setText(model.getMinimumBP());

            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }

    private class UsersViewHolder extends RecyclerView.ViewHolder {

        private TextView listname, listemail, listlowbp, listhighbp;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

        listname = itemView.findViewById(R.id.listname);
        listemail = itemView.findViewById(R.id.listemail);
        listhighbp = itemView.findViewById(R.id.listhighbp);
        listlowbp = itemView.findViewById(R.id.listlowbp);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}



