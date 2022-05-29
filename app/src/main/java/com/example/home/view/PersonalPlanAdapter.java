package com.example.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.model.PersonalActivity;
import com.example.home.model.PersonalPlan;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class PersonalPlanAdapter extends RecyclerView.Adapter<PersonalPlanAdapter.ViewHolder> {

    private ArrayList<PersonalPlan> personalPlans;

    public PersonalPlanAdapter(ArrayList<PersonalPlan> personalPlans) {
        this.personalPlans = personalPlans;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_personal_plan_item, parent, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.title.setText(personalPlans.get(position).getName());
        viewHolder.description.setText(personalPlans.get(position).getDescription());
        viewHolder.comment.setText(personalPlans.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return personalPlans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView planCard;
        TextView title;
        TextView description;
        TextView comment;

        ViewHolder(View itemView) {
            super(itemView);
            planCard = itemView.findViewById(R.id.PersonalPlan_Card);
            title = itemView.findViewById(R.id.PersonalPlan_Title);
            description = itemView.findViewById(R.id.PersonalPlan_Description);
            comment = itemView.findViewById(R.id.PersonalPlan_Comment);

        }
    }
}
