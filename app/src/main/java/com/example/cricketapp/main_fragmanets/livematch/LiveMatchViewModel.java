package com.example.cricketapp.main_fragmanets.livematch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cricketapp.AppDatabase;
import com.example.cricketapp.CricketService;
import com.example.cricketapp.LiveMatch;
import com.example.cricketapp.LiveMatchRepository;
import com.example.cricketapp.Match;
import com.example.cricketapp.UnsafeOkHttpClient;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveMatchViewModel  extends ViewModel {

    public LiveData<LiveMatch> liveMatch;
    private LiveMatchRepository liveMatchRepository;
    public String matchId;
    public boolean isUpdated;

    public void setCallback(Runnable callback) {
        if(isUpdated)
            callback.run();
        else
            this.callback = callback;
    }

    private Runnable callback;

    public LiveMatchViewModel()
    {
        liveMatchRepository = new LiveMatchRepository();
    }

    public LiveData<LiveMatch> getUpdate()
    {
        isUpdated = false;
        if (liveMatch == null)
            liveMatch = liveMatchRepository.getUpdate(matchId, new Runnable() {
                @Override
                public void run() {
                    isUpdated = true;
                    if (callback != null)
                        callback.run();
                }
            });
        return liveMatch;
    }



}
