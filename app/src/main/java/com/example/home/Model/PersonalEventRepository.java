package com.example.home.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonalEventRepository {

    private static PersonalEventRepository instance;
    private final PersonalEventDao personalEventDao;
    private final LiveData<List<PersonalEvent>> allPersonalEvents;
    private final ExecutorService executorService;

    private PersonalEventRepository(Application application) {
        PersonalEventDatabase database = PersonalEventDatabase.getInstance(application);
        personalEventDao = database.personalEventDao();
        allPersonalEvents = personalEventDao.getAllPersonalEvents();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized PersonalEventRepository getInstance(Application application) {
        if (instance == null)
            instance = new PersonalEventRepository(application);

        return instance;
    }

    public LiveData<List<PersonalEvent>> getAllPersonalEvents() {
        return allPersonalEvents;
    }

    public void insert(PersonalEvent personalEvent) {
        executorService.execute(() -> personalEventDao.insert(personalEvent));
    }

    public void deleteAllPersonalEvents() {
        executorService.execute(personalEventDao::deleteAllPersonalEvents);
    }
}
