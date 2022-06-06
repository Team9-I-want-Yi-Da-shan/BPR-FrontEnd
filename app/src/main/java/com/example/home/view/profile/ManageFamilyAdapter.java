package com.example.home.view.profile;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.model.User;

import java.util.ArrayList;

public class ManageFamilyAdapter  extends RecyclerView.Adapter<ManageFamilyAdapter.ViewHolder> {

    private ArrayList<User> familyMembers;

    public ManageFamilyAdapter(ArrayList<User> familyMembers) {
        this.familyMembers = familyMembers;
    }

    @NonNull
    @Override
    public ManageFamilyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ManageFamilyAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {



        ViewHolder(View itemView) {
            super(itemView);

        }
    }

}
