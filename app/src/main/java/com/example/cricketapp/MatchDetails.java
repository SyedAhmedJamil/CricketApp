package com.example.cricketapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class MatchDetails {

    @SerializedName("MainScreenMatchId")
    @PrimaryKey
    @NonNull
    private Integer MainScreenMatchId;

    @SerializedName("TeamAname")
    private String TeamAname;
    @SerializedName("TeamBname")
    private String TeamBname;
    @SerializedName("TeamAstats")
    private String TeamAstats;
    @SerializedName("TeamBstats")
    private String TeamBstats;
    @SerializedName("Message")
    private String Message;
    @SerializedName("StartDate")
    private String StartDate;
    @SerializedName("StartTime")
    private String StartTime;
    @SerializedName("TeamApicURL")
    private String TeamApicURL;
    @SerializedName("TeamBpicURL")
    private String TeamBpicURL;
    @SerializedName("venue")
    private String venue;
    @SerializedName("totalover")
    private String totalover;
    @SerializedName("IsToss")
    private boolean IsToss;
    @SerializedName("IsStarted")
    private boolean IsStarted;
    @SerializedName("IsActive")
    private boolean IsActive;

    public Integer getMainScreenMatchId() {
        return MainScreenMatchId;
    }

    public void setMainScreenMatchId(Integer mainScreenMatchId) {
        this.MainScreenMatchId = mainScreenMatchId;
    }

    public String getTeamAname() {
        return TeamAname;
    }

    public void setTeamAname(String teamAname) {
        TeamAname = teamAname;
    }

    public String getTeamBname() {
        return TeamBname;
    }

    public void setTeamBname(String teamBname) {
        TeamBname = teamBname;
    }

    public String getTeamAstats() {
        return TeamAstats;
    }

    public void setTeamAstats(String teamAstats) {
        TeamAstats = teamAstats;
    }

    public String getTeamBstats() {
        return TeamBstats;
    }

    public void setTeamBstats(String teamBstats) {
        TeamBstats = teamBstats;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getTeamApicURL() {
        return TeamApicURL;
    }

    public void setTeamApicURL(String teamApicURL) {
        TeamApicURL = teamApicURL;
    }

    public String getTeamBpicURL() {
        return TeamBpicURL;
    }

    public void setTeamBpicURL(String teamBpicURL) {
        TeamBpicURL = teamBpicURL;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTotalover() {
        return totalover;
    }

    public void setTotalover(String totalover) {
        this.totalover = totalover;
    }

    public boolean getIsToss() {
        return IsToss;
    }

    public void setIsToss(boolean toss) {
        IsToss = toss;
    }

    public boolean getIsStarted() {
        return IsStarted;
    }

    public void setIsStarted(boolean started) {
        IsStarted = started;
    }

    public boolean getIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean active) {
        IsActive = active;
    }
}
