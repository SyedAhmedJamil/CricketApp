package com.example.cricketapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface MatchDAO {

    @Insert
    void insert(Match match);

    @Query("DELETE FROM `Matches`")
    void deleteAll();

    @Query("SELECT * from `Matches` ORDER BY MainScreenMatchId ASC")
    LiveData<List<Match>> getMatches();
}