package com.example.home.view.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.model.FamilyPlan;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class FamilyPlanAdapter extends RecyclerView.Adapter<FamilyPlanAdapter.ViewHolder> {

    private ArrayList<FamilyPlan> personalPlans;

    public FamilyPlanAdapter(ArrayList<FamilyPlan> personalPlans) {
        this.personalPlans = personalPlans;
    }

    @NonNull
    @Override
    public FamilyPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_family_plan_item, parent, false);
        return new FamilyPlanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyPlanAdapter.ViewHolder holder, int position) {
        holder.title.setText(personalPlans.get(position).getName());
        holder.description.setText(personalPlans.get(position).getDescription());
        holder.comment.setText(personalPlans.get(position).getComment());

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
            planCard = itemView.findViewById(R.id.FamilyPlanItem_Card);
            title = itemView.findViewById(R.id.FamilyPlanItem_Title);
            description = itemView.findViewById(R.id.FamilyPlanItem_Description);
            comment = itemView.findViewById(R.id.FamilyPlanItem_Comment);

        }
    }
}
