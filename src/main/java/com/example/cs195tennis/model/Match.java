package com.example.cs195tennis.model;

import java.util.*;

public class Match extends Tournament {

    private Player winner, loser;

    Tournament tournament;

    public String match_id, eventName;
    public String matchNumber,round;

    public String status;
    public String score;

    static String[] matchStats;

    int matchId;


    public Match(int id, String match_num,String round, String status, String winner, String eventName) {
        this.matchId = id;
        this.matchNumber = match_num;
        this.round = round;
        this.status = status;
        this.eventName = eventName;

        if(winner != null) {
            this.winner = new Player(winner);
        }
    }

    public Match() {

    }

    public String getEventName() {return eventName;}

    public void setEventName(String eventName) {this.eventName = eventName;}

    @Override
    public String getMatchNumber() {return matchNumber;}

    public void setMatchNumber(String matchNumber) {this.matchNumber = matchNumber;}

    @Override
    public String getRound() {return round;}

    public String getWinner() {
        return winner.getFirstName() + " " + winner.getLastName();
    }


    public void setWinner(Player winner) {
        this.winner = winner;
    }


    public String getLoser() {
        return loser.getFirstName() + " " + loser.getLastName();
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public  String getMatch_num() {
        return matchNumber;
    }

    public void setMatch_num(String match_num) {
        this.matchNumber = match_num;
    }

    public  String getRoundN() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public static void setMatchStats(String[] matchStats) {
        Match.matchStats = matchStats;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}





