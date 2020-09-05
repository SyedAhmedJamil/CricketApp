package com.example.cricketapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CricketService {
    @GET("api/MatchData/CurrentMatches")
    Call<MatchDetailsList> getMatchDetailsList ();
}