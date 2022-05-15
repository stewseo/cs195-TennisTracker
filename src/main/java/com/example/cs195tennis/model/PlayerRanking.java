package com.example.cs195tennis.model;

import java.sql.SQLException;

public class PlayerRanking extends Player{
    static League league;
    public String rank,ranking_date,player,points;
    

    public PlayerRanking(String ranking_date, String rank, String player, String points) {
        league = League.getATPEnum();

        this.rank = rank;
        this.ranking_date = ranking_date;
        this.player = player;
        this.points = points;
    }

    public PlayerRanking(League WTA, String rank) {
        league = League.WTA;
        this.rank = rank;
    }

    public PlayerRanking(Player e) {}


    public PlayerRanking(String tourney_id, String score, String tourney_name) throws SQLException {
//        WtaMatchDao.getTournamentResults(tourney_id, score ,tourney_name);
    }

    public PlayerRanking(String tourney_id, String winner_id, String winner_rank_points, String winner_rank, String loser_id, String loser_rank_points, String loser_rank) {
    }

    private void queryUsingNameAndId() {

    }

    public String getPlayerWTARanking(){
        return rank;
    }

    enum League {
        WTA, ATP;


        public League getWtaEnum() {
            return League.WTA;
        };
        public static League getATPEnum(){
            return ATP;
        }
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
