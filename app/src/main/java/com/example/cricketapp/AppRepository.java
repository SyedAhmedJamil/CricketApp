package com.example.cricketapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppRepository {

    private MatchDetailsDAO matchDetailsDAO;
    private LiveData<List<MatchDetails>> matchDetailsList;

    AppRepository(Application application)
    {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        matchDetailsDAO = appDatabase.getMatchDetailsDAO();
        matchDetailsList = matchDetailsDAO.getMatchDetailsList();
    }

    LiveData<List<MatchDetails>> getMatchDetailsList() {
        return matchDetailsList;
    }

    public void insert (MatchDetails matchDetails)
    {
        new insertAsyncTask(matchDetailsDAO).execute(matchDetails);
    }

    private static class insertAsyncTask extends AsyncTask<MatchDetails, Void, Void> {

        private MatchDetailsDAO mAsyncTaskDao;

        insertAsyncTask(MatchDetailsDAO dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final MatchDetails... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


}
