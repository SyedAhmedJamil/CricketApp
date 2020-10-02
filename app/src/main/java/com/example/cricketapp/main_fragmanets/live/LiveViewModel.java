package com.example.cricketapp.main_fragmanets.live;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cricketapp.CricketService;
import com.example.cricketapp.Match;
import com.example.cricketapp.MatchList;
import com.example.cricketapp.MatchRepository;
import com.example.cricketapp.UnsafeOkHttpClient;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveViewModel extends AndroidViewModel {

    MutableLiveData<List<Match>> liveMatchList;
    MatchRepository matchRepository ;

    public LiveViewModel(@NonNull Application application) {
        super(application);
        matchRepository = new MatchRepository(application);

    }



}
