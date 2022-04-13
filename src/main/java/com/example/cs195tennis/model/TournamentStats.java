package com.example.cs195tennis.model;


public class TournamentStats {

    public static String id;
    public static String TOURNEY_DATE;
    public static String loser_name;
    public static String winner_ioc;
    public static String winner_name;
    public static String ROUND;

    public TournamentStats(){}

    public TournamentStats(String id, String winner_name, String loser_name, String winner_ioc, String ROUND) {
        id = id;
        winner_name = winner_name;
        loser_name = loser_name;
        winner_ioc = winner_ioc;
        ROUND = ROUND;
    }


    public String getWinner_name() {
        return winner_name;
    }

    public void setWinner_name(String winner_name) {
        TournamentStats.winner_name = winner_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        TournamentStats.id = id;
    }

    public String getTOURNEY_DATE() {
        return TOURNEY_DATE;
    }

    public void setTOURNEY_DATE(String TOURNEY_DATE) {
        TournamentStats.TOURNEY_DATE = TOURNEY_DATE;
    }

    public String getTourneyDate() {
        return TOURNEY_DATE;
    }

    public void setTourneyDate(String tourneyDate) {
        TOURNEY_DATE = tourneyDate;
    }

    public String getLoser_name() {
        return loser_name;
    }

    public void setLoser_name(String loser_name) {
        TournamentStats.loser_name = loser_name;
    }

    public String getWinner_ioc() {
        return winner_ioc;
    }

    public void setWinner_ioc(String winner_ioc) {
        TournamentStats.winner_ioc = winner_ioc;
    }

    public String getROUND() {
        return ROUND;
    }

    public void setROUND(String ROUND) {
        TournamentStats.ROUND = ROUND;
    }

}