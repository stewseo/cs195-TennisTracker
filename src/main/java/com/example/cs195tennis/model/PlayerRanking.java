package com.example.cs195tennis.model;

import java.sql.SQLException;

public class PlayerRanking extends Player{

    public String rank,ranking_date,player,points;
    

    public PlayerRanking(String ranking_date, String rank, String player, String points) {

        this.rank = rank;
        this.ranking_date = ranking_date;
        this.player = player;
        this.points = points;
    }

    public PlayerRanking(Player e) {}


    private void queryUsingNameAndId() {

    }

    public String getPlayerWTARanking(){
        return rank;
    }


    public PlayerRanking(){
    }

    public static String getRank() {
        return PlayerRanking.getRank();
    }

    public void setRank(String rank) {
        this.rank = rank;
    }



}
