package com.example.seniordesign;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FirestoreAdapter extends RecyclerView.Adapter<FirestoreAdapter.UsersViewHolder> {

    ArrayList<UsersModel> usersList;


    public FirestoreAdapter(ArrayList<UsersModel> usersList) {
        this.usersList = usersList;
    }


    @NonNull
    @Override
    public FirestoreAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sing_users, parent, false);
        return new FirestoreAdapter.UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirestoreAdapter.UsersViewHolder holder, int position) {
        holder.listname.setText(usersList.get(position).getFullName());
        holder.listemail.setText(usersList.get(position).getEmailAddress());
        holder.listhighbp.setText(usersList.get(position).getMaximumBP());
        holder.listlowbp.setText(usersList.get(position).getMinimumBP());
        holder.listbloodtype.setText(usersList.get(position).getBloodType());
        holder.listage.setText(usersList.get(position).getAge());
        holder.listweight.setText(usersList.get(position).getWeight());

        holder.listname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.listname.getContext(),userSchedule.class);
                intent.putExtra("uname",usersList.get(position).getuserId());
                intent.putExtra("username",usersList.get(position).getFullName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.listname.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView listname, listemail, listlowbp, listhighbp, listbloodtype, listage, listweight;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            listname = itemView.findViewById(R.id.listname);
            listemail = itemView.findViewById(R.id.listemail);
            listhighbp = itemView.findViewById(R.id.listhighbp);
            listlowbp = itemView.findViewById(R.id.listlowbp);
            listbloodtype = itemView.findViewById(R.id.listbloodtype);
            listage = itemView.findViewById(R.id.listage);
            listweight = itemView.findViewById(R.id.listweight);

        }
    }

    public void filteredList(ArrayList<UsersModel> filteredList){
        usersList = filteredList;
        notifyDataSetChanged();
    }
}