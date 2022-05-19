package com.example.cs195tennis.model;

import org.jooq.Result;

import java.util.*;

public class Match extends Tournament {


    static  Player winner, Loser;

    static WtaPlayer wtaWinner, wtaLoser;

    public AtpPlayer atpWinner, atpLoser;

    Tournament tournament;

    public String match_id, match_num, winnerName, loserName, round;

    PlayerRanking wtaRank;
    static String[] matchStats;



    public Match(String tourney_id, String winner_name, String loser_name, String tourney_date, String score, String round) {
        super();
        this.tourney_id = tourney_id;
        this.tourney_date = tourney_date;
        this.score = score;
        this.round = round;
    }


    public Match(Tournament tournament, WtaPlayer winner, WtaPlayer loser, PlayerRanking playerRanking, String[] matchStats) {
        super();
    }
}





