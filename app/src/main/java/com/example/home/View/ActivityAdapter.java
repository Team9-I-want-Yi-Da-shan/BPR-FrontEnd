package com.example.home.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.home.Model.PersonalActivity;
import com.example.home.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Date;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {
    private ArrayList<PersonalActivity> personalActivities;

    public ActivityAdapter(ArrayList<PersonalActivity> personalActivities) {
        this.personalActivities = personalActivities;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.timeLabel.setText(personalActivities.get(position).getStartTime().toString());
        viewHolder.title.setText(personalActivities.get(position).getTitle());
        viewHolder.description.setText(personalActivities.get(position).getDescription());
        String time = personalActivities.get(position).getStartTime().toString() + " - " + personalActivities.get(position).getEndTime().toString();
        viewHolder.time.setText(time);
    }

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
            timeLabel  = itemView.findViewById(R.id.PersonalActivity_TimeLabel);
            activityCard = itemView.findViewById(R.id.PersonalActivity_Card);
            title = itemView.findViewById(R.id.PersonalActivity_Title);
            description = itemView.findViewById(R.id.PersonalActivity_Description);
            time = itemView.findViewById(R.id.PersonalActivity_Time);
        }

    }

}