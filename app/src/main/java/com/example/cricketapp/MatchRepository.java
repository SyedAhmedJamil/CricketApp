package com.example.cricketapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MatchRepository {

    private MatchDAO matchDAO;
    private LiveData<List<Match>> matches;

    MatchRepository(Application application)
    {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        matchDAO = appDatabase.getMatchDAO();
        matches = matchDAO.getMatches();
    }

    LiveData<List<Match>> getMatches() {
        return matches;
    }

    public void insert (Match match)
    {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                matchDAO.insert(match);
            }
        });
    }
}
