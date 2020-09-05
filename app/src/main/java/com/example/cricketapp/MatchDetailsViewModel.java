package com.example.cricketapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MatchDetailsViewModel extends AndroidViewModel {

    private AppRepository appRepository;

    private LiveData<List<MatchDetails>> matchDetailsList;

    public MatchDetailsViewModel (Application application) {
        super(application);
        appRepository = new AppRepository(application);
        matchDetailsList = appRepository.getMatchDetailsList();
    }

    LiveData<List<MatchDetails>> getMatchDetailsList() { return matchDetailsList; }

    public void insert(MatchDetails matchDetails) { appRepository.insert(matchDetails); }
}
