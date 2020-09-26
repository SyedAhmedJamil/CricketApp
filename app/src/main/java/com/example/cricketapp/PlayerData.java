package com.example.cricketapp;

import com.google.gson.annotations.SerializedName;

public class PlayerData {

    @SerializedName("PlayerName")
    private String PlayerName;
    @SerializedName("BallsPlayed")
    private String BallsPlayed;
    @SerializedName("TotalScore")
    private String TotalScore;
    @SerializedName("StrikeRate")
    private String StrikeRate;
    @SerializedName("CurrentMatchId")
    private String CurrentMatchId;
    @SerializedName("TotalFours")
    private String TotalFours;
    @SerializedName("TotalSix")
    private String TotalSix;
    @SerializedName("TotalOut")
    private String TotalOut;
    @SerializedName("ball")
    private String ball;
    @SerializedName("PlayerType")
    private String PlayerType;
    @SerializedName("IsPlaying")
    private String IsPlaying;
    @SerializedName("ISBowler")
    private String ISBowler;
    @SerializedName("IsShow")
    private String IsShow;

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public String getBallsPlayed() {
        return BallsPlayed;
    }

    public void setBallsPlayed(String ballsPlayed) {
        BallsPlayed = ballsPlayed;
    }

    public String getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(String totalScore) {
        TotalScore = totalScore;
    }

    public String getStrikeRate() {
        return StrikeRate;
    }

    public void setStrikeRate(String strikeRate) {
        StrikeRate = strikeRate;
    }

    public String getCurrentMatchId() {
        return CurrentMatchId;
    }

    public void setCurrentMatchId(String currentMatchId) {
        CurrentMatchId = currentMatchId;
    }

    public String getTotalFours() {
        return TotalFours;
    }

    public void setTotalFours(String totalFours) {
        TotalFours = totalFours;
    }

    public String getTotalSix() {
        return TotalSix;
    }

    public void setTotalSix(String totalSix) {
        TotalSix = totalSix;
    }

    public String getTotalOut() {
        return TotalOut;
    }

    public void setTotalOut(String totalOut) {
        TotalOut = totalOut;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String ball) {
        this.ball = ball;
    }

    public String getPlayerType() {
        return PlayerType;
    }

    public void setPlayerType(String playerType) {
        PlayerType = playerType;
    }

    public String getIsPlaying() {
        return IsPlaying;
    }

    public void setIsPlaying(String isPlaying) {
        IsPlaying = isPlaying;
    }

    public String getISBowler() {
        return ISBowler;
    }

    public void setISBowler(String ISBowler) {
        this.ISBowler = ISBowler;
    }

    public String getIsShow() {
        return IsShow;
    }

    public void setIsShow(String isShow) {
        IsShow = isShow;
    }
}
