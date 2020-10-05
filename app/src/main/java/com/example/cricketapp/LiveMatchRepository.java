package com.example.cricketapp;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveMatchRepository {

    private MutableLiveData<LiveMatch> liveMatch;
    public  LiveMatchRepository()
    {
        liveMatch = new MutableLiveData<>();
    }

    public LiveData<LiveMatch> getUpdate(String matchId) {

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

        return liveMatch;
    }

}
