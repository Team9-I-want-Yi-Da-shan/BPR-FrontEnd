package com.example.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.model.PersonalActivity;
import com.example.home.R;
import com.example.home.databinding.ListCreatePersonalActivityItemBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.time.LocalTime;
import java.util.ArrayList;

public class CreatePersonalActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<PersonalActivity> personalActivities;
    private final int viewTypeCreate = 1;
    private final int viewTypeAdd = 2;

    public CreatePersonalActivityAdapter() {
        personalActivities = new ArrayList<PersonalActivity>();
        personalActivities.add(new PersonalActivity("","",LocalTime.now(),null,0,0));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case viewTypeCreate:
                ListCreatePersonalActivityItemBinding itemBinding = ListCreatePersonalActivityItemBinding.inflate(inflater, parent, false);
                return new CreateActivityViewHolder(itemBinding);
//            case viewTypeAdd:
//                View itemView = LayoutInflater.from(parent.getContext()).inflate(viewTypeCreate, parent, false);
//                return new AddButtonViewHolder(itemView);
            default:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_create_personal_activity_button_item, parent, false);
                return new AddButtonViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case viewTypeCreate:
                CreateActivityViewHolder viewHolder = (CreateActivityViewHolder)holder;
                PersonalActivity pa = personalActivities.get(position);
                viewHolder.personalActivityItemBinding.setPersonalActivity(pa);
                viewHolder.personalActivityItemBinding.executePendingBindings();
                break;

            case viewTypeAdd:
//                AddButtonViewHolder buttonViewHolder = (AddButtonViewHolder)holder;
                break;
        }
    }


    @Override
    public int getItemCount() {
        return personalActivities.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position != personalActivities.size()) ? viewTypeCreate : viewTypeAdd;
    }












    class AddButtonViewHolder extends RecyclerView.ViewHolder{

        MaterialButton addButton;

        public AddButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            addButton = itemView.findViewById(R.id.CreatePersonalActivity_AddCard);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    personalActivities.add(new PersonalActivity());
                }
            });
        }


    }

    class CreateActivityViewHolder extends RecyclerView.ViewHolder {

        ListCreatePersonalActivityItemBinding personalActivityItemBinding;

        ImageButton closeButton;

        TextInputEditText titleEditText;
        TextInputEditText descriptionEditText;

        LinearLayout startTimeLinearLayout;
        LinearLayout endTimeLinearLayout;

        LinearLayout repeatLinearLayout;
        TextView repeatTimeTextView;

        LinearLayout remindLinearLayout;
        TextView remindTimeTextView;
        SwitchMaterial alarmSwitch;



        CreateActivityViewHolder(ListCreatePersonalActivityItemBinding itemBinding) {
            super(itemBinding.getRoot());
            personalActivityItemBinding = itemBinding;

            closeButton = itemView.findViewById(R.id.CreatePersonalActivity_CloseCreateActivityCard);

            titleEditText = itemView.findViewById(R.id.CreatePersonalActivity_TitleTextField);
            descriptionEditText = itemView.findViewById(R.id.CreatePersonalActivity_DescriptionTextField);

            startTimeLinearLayout = itemView.findViewById(R.id.CreatePersonalActivity_StartTimeLayout);
            endTimeLinearLayout = itemView.findViewById(R.id.CreatePersonalActivity_EndTimeLayout);

            repeatLinearLayout = itemView.findViewById(R.id.CreatePersonalActivity_RepeatLayout);
            repeatTimeTextView = itemView.findViewById(R.id.CreatePersonalActivity_RepeatTextView);

            remindLinearLayout = itemView.findViewById(R.id.CreatePersonalActivity_RemindLayout);
            remindTimeTextView = itemView.findViewById(R.id.CreatePersonalActivity_RemindTextView);
            alarmSwitch = itemView.findViewById(R.id.CreatePersonalActivity_RemindAlarmSwitch);

            setCloseButtonOnClickListener(closeButton);
            setTimeLayoutOnClickListener(startTimeLinearLayout);
            setTimeLayoutOnClickListener(endTimeLinearLayout);
            setRemindLayoutOnClickListener();

            alarmSwitch.setVisibility(View.GONE);
        }

        private void setCloseButtonOnClickListener(ImageButton closeButton) {
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialCardView cardView = (MaterialCardView)v.getParent();
                    RecyclerView recyclerView = (RecyclerView)cardView.getParent();
                    recyclerView.removeView(cardView);
                }
            });
        }

        private void setRemindLayoutOnClickListener() {
            remindLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if(){
//                        alarmSwitch.setVisibility(View.VISIBLE);
//                    }else {
//                        alarmSwitch.setVisibility(View.GONE);
//                    }
                }
            });

        }

        private void setTimeLayoutOnClickListener(LinearLayout linearLayout){
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
//                        @Override
//                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                            if(linearLayout.getId()==R.id.CreatePersonalActivity_StartTimeLayout){
//                                personalActivities.get(getLayoutPosition()).setStartTime(LocalTime.of(selectedHour,selectedMinute));
//                            }
//                            if(linearLayout.getId()==R.id.CreatePersonalActivity_EndTimeLayout){
//                                personalActivities.get(getLayoutPosition()).setStartTime(LocalTime.of(selectedHour,selectedMinute));
//                            }
//                        }
//                    };
//                    TimePickerDialog timePickerDialog = new TimePickerDialog(this,v.getContext(), onTimeSetListener,true);
                    // instance of MDC time picker
                    MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                            // set the title for the alert dialog
                            .setTitleText("SELECT YOUR TIMING")
                            // set the default hour for the
                            // dialog when the dialog opens
                            .setHour(12)
                            .setMinute(10)
                            // set the time format
                            // according to the region
                            .setTimeFormat(TimeFormat.CLOCK_24H)
                            .build();

                    materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int selectedHour = materialTimePicker.getHour();
                            int selectedMinute = materialTimePicker.getMinute();
                            if(linearLayout.getId()==R.id.CreatePersonalActivity_StartTimeLayout){
                                personalActivities.get(getLayoutPosition()).setStartTime(LocalTime.of(selectedHour,selectedMinute));
                            }
                            if(linearLayout.getId()==R.id.CreatePersonalActivity_EndTimeLayout){
                                personalActivities.get(getLayoutPosition()).setStartTime(LocalTime.of(selectedHour,selectedMinute));
                            }
                        }
                    });

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    materialTimePicker.show(fragmentManager,"CreateActivityActivity");
                }
            });
        }




    }


}
