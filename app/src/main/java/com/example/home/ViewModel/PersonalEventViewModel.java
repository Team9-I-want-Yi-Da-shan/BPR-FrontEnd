package com.example.home.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.home.Model.PersonalEvent;
import com.example.home.Model.PersonalEventRepository;

import java.util.List;

public class PersonalEventViewModel extends AndroidViewModel {
    private final PersonalEventRepository repository;

    public PersonalEventViewModel(Application app) {
        super(app);
        repository = PersonalEventRepository.getInstance(app);
    }

    public LiveData<List<PersonalEvent>> getAllPersonalEvents() {
        return repository.getAllPersonalEvents();
    }

    public void insert(final PersonalEvent note) {
        repository.insert(note);
    }

    public void deleteAllPersonalEvents() {
        repository.deleteAllPersonalEvents();
    }
}
