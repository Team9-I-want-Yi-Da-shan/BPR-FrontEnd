package com.example.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.model.dataTransferObject.PersonalActivityDTO;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class PersonalActivityAdapter extends RecyclerView.Adapter<PersonalActivityAdapter.ViewHolder> {
    private ArrayList<PersonalActivityDTO> personalActivities;

    public PersonalActivityAdapter() {
        personalActivities = new ArrayList<PersonalActivityDTO>();
    }

    public void setPersonalActivities(ArrayList<PersonalActivityDTO> personalActivities){
        this.personalActivities = personalActivities;
    }

    public ArrayList<PersonalActivityDTO> getPersonalActivities(){
        return personalActivities;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_personal_activity_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.timeLabel.setText(personalActivities.get(position).getStartTimeString());
        viewHolder.title.setText(personalActivities.get(position).getTitle());
        viewHolder.description.setText(personalActivities.get(position).getDescription());
        String time = personalActivities.get(position).getStartTimeString() + " - " + personalActivities.get(position).getEndTimeString();
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
