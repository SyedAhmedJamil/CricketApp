package com.example.cricketapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchDetailsList {
    @SerializedName("match_list")
    public List<MatchDetails> matchDetailsList;
}
