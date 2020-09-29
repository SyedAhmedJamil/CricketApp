package com.example.cricketapp.main_fragmanets.live;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.cricketapp.CricketService;
import com.example.cricketapp.MatchDetails;
import com.example.cricketapp.MatchDetailsList;
import com.example.cricketapp.UnsafeOkHttpClient;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveViewModel extends ViewModel {

    MutableLiveData<List<MatchDetails>> liveMatchList;

    public LiveData<List<MatchDetails>> getLiveMatchList() {

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

        Call<MatchDetailsList> call = cricketService.getAllMatches();
        call.enqueue(new Callback<MatchDetailsList>() {
            @Override
            public void onResponse(Call<MatchDetailsList> call, Response<MatchDetailsList> response) {
                liveMatchList.setValue(response.body().matchDetailsList);
            }

            @Override
            public void onFailure(Call<MatchDetailsList> call, Throwable t) {
                int x = 3;
            }
        });
        return liveMatchList;
    }
}
