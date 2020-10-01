package com.example.cricketapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchList {
    @SerializedName("match_list")
    public List<Match> matchList;
}
