package com.example.home.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PersonalEvent.class}, version = 1)
public abstract class PersonalEventDatabase extends RoomDatabase {

    private static PersonalEventDatabase instance;

    public abstract PersonalEventDao personalEventDao();

    public static synchronized PersonalEventDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    PersonalEventDatabase.class, "personal_event_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
