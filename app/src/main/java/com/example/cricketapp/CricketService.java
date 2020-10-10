package com.example.cricketapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CricketService {
    @GET("api/MatchData/CurrentMatches")
    Call<MatchList> getAllMatches ();

    @GET("api/MatchData/GetMatchDetail")
    Call<LiveMatch> getMatchDetail(@Query("currentMatchid") String matchId );

    @GET("api/MatchData/GetCurrentRecordId")
    Call<RecordId> getCurrentRecordId(@Query("currentMatchid") String matchId );
}