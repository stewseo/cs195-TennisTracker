package com.example.cs195tennis.model;

import java.util.List;

public class Rankings extends Tournament {

    String ranking_date,rank,player,points;
    List<Rankings> rankList;


    public Rankings(String ranking_date, String rank, String player, String points) {
        this.ranking_date = ranking_date;
        this.rank = rank;
        this.player = player;
        this.points  = points;
    }
    public String toString(){
        return ranking_date + ", " + rank + ", " + player + ", " + points;
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
