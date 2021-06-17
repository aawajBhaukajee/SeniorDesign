package com.example.seniordesign;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FirestoreAdapter extends FirestoreRecyclerAdapter<UsersModel,FirestoreAdapter.UsersViewHolder> {

private OnListItemClick onListItemClick;

    public FirestoreAdapter(@NonNull  FirestoreRecyclerOptions<UsersModel> options, OnListItemClick onListItemClick) {

        super(options);
        this.onListItemClick = onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull UsersModel model) {
        holder.listname.setText(model.getFullName());
        holder.listemail.setText(model.getEmailAddress());
        holder.listhighbp.setText(model.getMaximumBP());
        holder.listlowbp.setText(model.getMinimumBP());
        holder.listbloodtype.setText(model.getBloodType());
        holder.listage.setText(model.getAge());
        holder.listweight.setText(model.getWeight());
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sing_users, parent, false);
        return new UsersViewHolder(view);
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView listname, listemail, listlowbp, listhighbp, listbloodtype, listage, listweight;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            listname = itemView.findViewById(R.id.listname);
            listemail = itemView.findViewById(R.id.listemail);
            listhighbp = itemView.findViewById(R.id.listhighbp);
            listlowbp = itemView.findViewById(R.id.listlowbp);
            listbloodtype = itemView.findViewById(R.id.listbloodtype);
            listage = itemView.findViewById(R.id.listage);
            listweight = itemView.findViewById(R.id.listweight);

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
