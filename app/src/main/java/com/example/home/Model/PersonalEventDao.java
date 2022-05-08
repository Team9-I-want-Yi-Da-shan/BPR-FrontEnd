package com.example.home.Model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonalEventDao {

    @Insert
    void insert(PersonalEvent note);

    @Update
    void update(PersonalEvent note);

    @Delete
    void delete(PersonalEvent note);

    @Query("DELETE FROM personal_event_table")
    void deleteAllPersonalEvents();

    @Query("SELECT * FROM personal_event_table ORDER BY priority DESC")
    LiveData<List<PersonalEvent>> getAllPersonalEvents();
}
