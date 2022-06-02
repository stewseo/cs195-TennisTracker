package com.example.cs195tennis.model;

public class PlayerRanking extends Player {

    public String playerRank, rankDate, playerId,rankingPoints;

    public PlayerRanking(String ranking_date, String rank, String player, String points) {
        super();
        this.playerRank = rank;
        this.rankDate = ranking_date;
        this.playerId = player;
        this.rankingPoints = points;
    }
    public PlayerRanking(Object playerId, Object fullName, Object rankDate, Object playerRank, Object rankingPoints, Object dominantHand, Object location, Object dateOfBirth) {
        super(playerId, fullName, location, dominantHand, dateOfBirth);
        this.rankDate= rankDate.toString();
        this.playerRank = playerRank.toString();
        this.playerId = playerId.toString();
        this.rankingPoints = rankingPoints.toString();

    }

    public String toString() {
        return playerId + " " +  rankDate;
    }

    public String getPlayerRank() {
        return playerRank;
    }

    public String getRankDate() {
        return rankDate;
    }

    public String getRankingPoints() {
        return rankingPoints;
    }
}

