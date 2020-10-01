package com.example.cricketapp.main_fragmanets.live;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cricketapp.CricketService;
import com.example.cricketapp.Match;
import com.example.cricketapp.MatchList;
import com.example.cricketapp.UnsafeOkHttpClient;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveViewModel extends ViewModel {

    MutableLiveData<List<Match>> liveMatchList;

    public LiveData<List<Match>> getLiveMatchList() {

        if (liveMatchList != null)
            return liveMatchList;
        liveMatchList = new MutableLiveData<>();
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
                liveMatchList.setValue(response.body().matchList);
            }

            @Override
            public void onFailure(Call<MatchList> call, Throwable t) {
                int x = 3;
            }
        });
        return liveMatchList;
    }
}
