package com.example.cricketapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MatchDetails.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    public static AppDatabase INSTANCE;
    private static String appDatabaseName = "CricketAppDatabase";

    public abstract MatchDetailsDAO getMatchDetailsDAO();

    static AppDatabase getInstance(final Context context)
    {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
