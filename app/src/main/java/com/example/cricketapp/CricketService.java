package com.example.cricketapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CricketService {
    @GET("api/MatchData/CurrentMatches")
    Call<List<Match>> getAllMatches ();

    @GET("api/MatchData/GetMatchDetail")
    Call<MatchSummary> getMatchDetail( @Query("currentMatchid") String matchId );
}