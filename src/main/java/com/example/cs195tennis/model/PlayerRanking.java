package com.example.cs195tennis.model;

import java.sql.SQLException;

public class PlayerRanking extends Player{

    public String rank,ranking_date,player,points;
    private static Player playerObj;

    public PlayerRanking(String ranking_date, String rank, String player, String points) {

        this.rank = rank;
        this.ranking_date = ranking_date;
        this.player = player;
        this.points = points;
        playerObj = new Player(player);
    }

    public static String getFullName() {
        return playerObj.firstName + " " + playerObj.getLastName();
    }


    public String getRanking_date() {
        return ranking_date;
    }

    public void setRanking_date(String ranking_date) {
        this.ranking_date = ranking_date;
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

    public void setRank(String rank) {this.rank = rank;}




}
