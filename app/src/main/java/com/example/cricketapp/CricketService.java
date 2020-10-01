package com.example.cricketapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CricketService {
    @GET("api/MatchData/CurrentMatches")
    Call<MatchList> getAllMatches ();

    @GET("api/MatchData/GetMatchDetail")
    Call<MatchSummary> getMatchDetail( @Query("currentMatchid") String matchId );
}