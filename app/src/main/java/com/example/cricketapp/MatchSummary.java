package com.example.cricketapp;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchSummary {

    @SerializedName("MatchDetail")
    public MatchData matchData;
    @SerializedName("PlayerDetail")
    public List<PlayerData> playerDataList;
}
