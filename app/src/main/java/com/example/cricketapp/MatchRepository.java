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
    private MutableLiveData<List<Match>> matches;

    public MatchRepository(Application application)
    {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        matchDAO = appDatabase.getMatchDAO();
    }

    LiveData<List<Match>> getMatches() {
        matches = matchDAO.getMatches();
        if (matches == null) {
            OkHttpClient unsafeOkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.crickssix.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(unsafeOkHttpClient)
                    .build();
            CricketService cricketService = retrofit.create(CricketService.class);

            Call<List<Match>> call = cricketService.getAllMatches();
            call.enqueue(new Callback<List<Match>>() {
                @Override
                public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                    matches.setValue(response.body());
                    for (Match match : matches.getValue()) {
                        matchDAO.insert(match);
                    }
                }

                @Override
                public void onFailure(Call<List<Match>> call, Throwable t) {

                }
            });
        }
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


    public LiveData<List<Match>> getLiveMatchList() {

        if (matches != null)
            return matches;
        matches = new MutableLiveData<>();
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
                matches.setValue(response.body().matchList);
            }

            @Override
            public void onFailure(Call<MatchList> call, Throwable t) {
                int x = 3;
            }
        });
        return matches;
    }
}
