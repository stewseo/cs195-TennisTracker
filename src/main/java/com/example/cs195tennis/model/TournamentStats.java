package com.example.cs195tennis.model;


public class TournamentStats {

    public String id;
    public String TOURNEY_DATE;
    public String loser_name;
    public String winner_ioc;
    public String winner_name;
    public String ROUND;


    public TournamentStats(){}

    public TournamentStats(String id, String winner_name, String loser_name, String winner_ioc, String ROUND) {
        this.id = id;
        this.winner_name = winner_name;
        this.loser_name = loser_name;
        this.winner_ioc = winner_ioc;
        this.ROUND = ROUND;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTOURNEY_DATE() {
        return TOURNEY_DATE;
    }

    public void setTOURNEY_DATE(String TOURNEY_DATE) {
        this.TOURNEY_DATE = TOURNEY_DATE;
    }

    public String getLoser_name() {
        return loser_name;
    }

    public void setLoser_name(String loser_name) {
        this.loser_name = loser_name;
    }

    public String getWinner_ioc() {
        return winner_ioc;
    }

    public void setWinner_ioc(String winner_ioc) {
        this.winner_ioc = winner_ioc;
    }

    public String getWinner_name() {
        return winner_name;
    }

    public void setWinner_name(String winner_name) {
        this.winner_name = winner_name;
    }

    public String getROUND() {
        return ROUND;
    }

    public void setROUND(String ROUND) {
        this.ROUND = ROUND;
    }
}