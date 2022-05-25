package com.example.cs195tennis.model;

import org.jooq.Result;

import java.util.*;

public class Match extends Tournament {

    private Player winner, loser;

    Tournament tournament;

    public String match_id, match_num, winnerName, loserName, round, status, score;

    static String[] matchStats;

    int matchId;


    public Match(String tourney_id, String winner_name, String loser_name, String tourney_date, String score, String round) {
        super();
        this.score = score;
        this.round = round;
    }

    public Match(Tournament tournament, WtaPlayer winner, WtaPlayer loser, PlayerRanking playerRanking, String[] matchStats) {
        super();
    }

    public Match(String match_num, String status, String winner, String[] strings) {
        this.match_id = match_num;
        this.status = status;
        this.winnerName = winner;
        matchStats = strings;
    }


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

    public String getMatch_num() {
        return match_num;
    }

    public void setMatch_num(String match_num) {
        this.match_num = match_num;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getLoserName() {
        return loserName;
    }

    public void setLoserName(String loserName) {
        this.loserName = loserName;
    }

    public String getRound() {
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





