package com.example.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.model.FamilyActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class FamilyActivityAdapter extends RecyclerView.Adapter<FamilyActivityAdapter.ViewHolder>{


    private ArrayList<FamilyActivity> personalActivities;

    public FamilyActivityAdapter(ArrayList<FamilyActivity> personalActivities) {
        this.personalActivities = personalActivities;
    }

    @NonNull
    @Override
    public FamilyActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_family_activity_item, parent, false);
        return new FamilyActivityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyActivityAdapter.ViewHolder holder, int position) {
        holder.timeLabel.setText(personalActivities.get(position).getStartTime());
        holder.title.setText(personalActivities.get(position).getTitle());
        holder.description.setText(personalActivities.get(position).getDescription());
        String time = personalActivities.get(position).getStartTime() + " - " + personalActivities.get(position).getEndTime().toString();
        holder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return personalActivities.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView timeLabel;
        MaterialCardView activityCard;
        TextView title;
        TextView description;
        TextView time;

        ViewHolder(View itemView) {
            super(itemView);
            timeLabel  = itemView.findViewById(R.id.FamilyActivityItem_TimeLabel);
            activityCard = itemView.findViewById(R.id.FamilyActivityItem_Card);
            title = itemView.findViewById(R.id.FamilyActivityItem_Title);
            description = itemView.findViewById(R.id.FamilyActivityItem_Description);
            time = itemView.findViewById(R.id.FamilyActivityItem_Time);
        }
    }

}
