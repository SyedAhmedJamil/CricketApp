package com.example.cricketapp.main_fragmanets.LiveMatch;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cricketapp.CricketService;
import com.example.cricketapp.MatchSummary;
import com.example.cricketapp.UnsafeOkHttpClient;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveMatchViewModel  extends ViewModel {
    private MutableLiveData<MatchSummary> matchSummary;
    public String matchTitle = "none";

    public MutableLiveData<MatchSummary> getMatchSummary() {
        if (matchSummary != null)
            return matchSummary;
        else  matchSummary = new MutableLiveData<>();

        OkHttpClient unsafeOkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.crickssix.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(unsafeOkHttpClient)
                .build();
        CricketService cricketService = retrofit.create(CricketService.class);
        Call<MatchSummary> call = cricketService.getMatchDetail("2345");
        call.enqueue(new Callback<MatchSummary>() {
            @Override
            public void onResponse(Call<MatchSummary> call, Response<MatchSummary> response) {

            }

            @Override
            public void onFailure(Call<MatchSummary> call, Throwable t) {

            }
        });


        return matchSummary;
    }
}
