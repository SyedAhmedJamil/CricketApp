package com.example.cricketapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MatchRepository {

    private MatchDAO matchDAO;
    private LiveData<List<Match>> matches;

    public MatchRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        matchDAO = appDatabase.getMatchDAO();
        matches = matchDAO.getMatches();
    }

    public LiveData<List<Match>> getMatches() {
        return matches;
    }

    public void refresh(Runnable callback) {
        OkHttpClient unsafeOkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.crickssix.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(unsafeOkHttpClient)
                .build();
        CricketService cricketService = retrofit.create(CricketService.class);
        Call<MatchList> call = cricketService.getAllMatches();
        call.enqueue(new Callback<MatchList>() {
            @Override
            public void onResponse(Call<MatchList> call, Response<MatchList> response) {
                callback.run();
                insertAll(response.body().matchList);
            }

            @Override
            public void onFailure(Call<MatchList> call, Throwable t) {

            }
        });
    }

    public void insertAll(List<Match> matchList) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    matchDAO.deleteAll();
                    for (Match match : matchList) {
                        matchDAO.insert(match);
                    }
                } catch (Exception e) {
                    //Do nothing
                }
            }
        });
    }
}
