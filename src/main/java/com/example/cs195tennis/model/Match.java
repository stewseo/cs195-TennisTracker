package com.example.cs195tennis.model;

import org.jooq.Result;

import java.util.*;

public class Match extends Tournament {

    Player winner;
    static  Player Loser;

    static WtaPlayer wtaWinner, wtaLoser;

    public AtpPlayer atpWinner, atpLoser;

    Tournament tournament;

    public String match_id, match_num, winnerName, loserName, round;

    PlayerRanking wtaRank;
    static String[] matchStats;


    public Match(Tournament tournament, WtaPlayer winner, WtaPlayer loser, PlayerRanking playerRanking, String[] matchStats) {
        this.tournament = tournament;
        wtaWinner = winner;
        wtaLoser = loser;
        this.wtaRank = playerRanking;
        System.arraycopy(matchStats, 0, matchStats, 0, matchStats.length);
    }

    public Match(String tourney_id, String winner_name, String loser_name, String tourney_date, String score, String round) {
        this.tourney_id = tourney_id;
        this.tourney_date = tourney_date;
        this.score = score;
        this.round = round;
    }



}





