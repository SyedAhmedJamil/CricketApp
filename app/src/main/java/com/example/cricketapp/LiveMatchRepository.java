package com.example.cricketapp;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveMatchRepository {

    private MutableLiveData<LiveMatch> liveMatch;
    private String oldRecordId;

    static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    public  LiveMatchRepository()
    {
        liveMatch = new MutableLiveData<>();
    }

    public LiveData<LiveMatch> getUpdate(String matchId, Runnable callback) {

        OkHttpClient unsafeOkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.crickssix.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(unsafeOkHttpClient)
                .build();
        CricketService cricketService = retrofit.create(CricketService.class);
        Call<LiveMatch> call = cricketService.getMatchDetail(matchId);
        call.enqueue(new Callback<LiveMatch>() {
            @Override
            public void onResponse(Call<LiveMatch> call, Response<LiveMatch> response) {
                liveMatch.setValue(response.body());
                callback.run();
                oldRecordId = response.body().matchData.getRecordId();
                checkForUpdates(matchId);
            }

            @Override
            public void onFailure(Call<LiveMatch> call, Throwable t) {
                int x = 3;
            }
        });

        return liveMatch;
    }

    private void getNewUpdate(String matchId) {

        OkHttpClient unsafeOkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.crickssix.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(unsafeOkHttpClient)
                .build();
        CricketService cricketService = retrofit.create(CricketService.class);
        Call<LiveMatch> call = cricketService.getMatchDetail(matchId);
        call.enqueue(new Callback<LiveMatch>() {
            @Override
            public void onResponse(Call<LiveMatch> call, Response<LiveMatch> response) {
                liveMatch.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LiveMatch> call, Throwable t) {
                int x = 3;
            }
        });

    }

    private void checkForUpdates(String matchId)
    {
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                OkHttpClient unsafeOkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.crickssix.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(unsafeOkHttpClient)
                        .build();
                CricketService cricketService = retrofit.create(CricketService.class);
                Call<RecordId> call = cricketService.getCurrentRecordId(matchId);
                call.enqueue(new Callback<RecordId>() {
                    @Override
                    public void onResponse(Call<RecordId> call, Response<RecordId> response) {
                        String recordId = response.body().recordId;
                        if (!recordId.equals(oldRecordId))
                        {
                            getNewUpdate(matchId);
                            oldRecordId = recordId;
                        }
                    }

                    @Override
                    public void onFailure(Call<RecordId> call, Throwable t) {

                    }
                });

            }
        },10,10,TimeUnit.SECONDS);
    }


}
