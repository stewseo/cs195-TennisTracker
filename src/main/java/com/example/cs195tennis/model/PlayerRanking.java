package com.example.cs195tennis.model;

import java.sql.SQLException;

public class PlayerRanking extends Player{

    public String playerRank, rankDate, playerId,rankingPoints;
    private static Player playerObj;

    public PlayerRanking(String ranking_date, String rank, String player, String points) {

        this.playerRank = rank;
        this.rankDate = ranking_date;
        this.playerId = player;
        this.rankingPoints = points;
    }

    public String getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(String playerRank) {
        this.playerRank = playerRank;
    }

    public String getRankDate() {
        return rankDate;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getRankingPoints() {
        return rankingPoints;
    }

    public void setRankingPoints(String rankingPoints) {
        this.rankingPoints = rankingPoints;
    }

    public static Player getPlayerObj() {
        return playerObj;
    }

    public static void setPlayerObj(Player playerObj) {
        PlayerRanking.playerObj = playerObj;
    }

    public PlayerRanking(Player e) {}

    private void queryUsingNameAndId() {}


    public PlayerRanking(){
    }

    public static String getRank() {
        return PlayerRanking.getRank();
    }



}
