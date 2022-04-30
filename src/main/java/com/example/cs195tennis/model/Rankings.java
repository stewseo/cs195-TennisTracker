package com.example.cs195tennis.model;

import java.util.List;

public class Rankings extends Tournament {

    String ranking_date,rank,player,points;
    List<Rankings> rankList;



    public Rankings(String resultDate, String resultRank, String resultPlayer, String resultPoints) {
        this.ranking_date = resultDate;
        this.rank = resultRank;
        this.player = resultPlayer;
        this.points  = resultPoints;
    }


    public Rankings(){}


    public String getRanking_date() {
        return ranking_date;
    }

    public void setRanking_date(String ranking_date) {
        this.ranking_date = ranking_date;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
