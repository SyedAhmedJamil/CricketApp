package com.example.cricketapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MatchDetailsDAO {

    @Insert
    void insert(MatchDetails matchDetails);

    @Query("DELETE FROM MatchDetails")
    void deleteAll();

    @Query("SELECT * from MatchDetails ORDER BY MainScreenMatchId ASC")
    LiveData<List<MatchDetails>> getMatchDetailsList();
}
