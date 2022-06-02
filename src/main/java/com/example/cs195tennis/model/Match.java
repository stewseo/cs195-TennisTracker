package com.example.cs195tennis.model;

import org.jooq.Field;
import org.jooq.impl.QOM;

import java.util.*;

public class Match<T> extends Tournament<T> {

    private Player player1, player2;
    T matchNumber,matchId, tournamentWinner;


    public Match(T matchId, T tourneyName, T tourneyDate, List<T> tournamentCourtStats, T matchNumber, List<T> playerFields) {
        super(matchId, tourneyName, tourneyDate, tournamentCourtStats);
        this.matchId = matchId;
        this.matchNumber = matchNumber;
        player1 = new Player(playerFields.get(0),playerFields.get(1),playerFields.get(2));
        player2 = new Player(playerFields.get(3),playerFields.get(4),playerFields.get(5));
    }
    public Match(T matchId, T tourneyName, T tourneyDate, List<T> tournamentCourtStats, T matchNumber, T winner, List<T> playerFields) {
        super(tourneyName, tourneyDate,tournamentCourtStats,winner);
        this.matchId = matchId;
        this.matchNumber = matchNumber;
        player1 = new Player(playerFields.get(0),playerFields.get(1),playerFields.get(2));
        player2 = new Player(playerFields.get(3),playerFields.get(4),playerFields.get(5));
    }

    public Match() {}

    public String getTournamentWinner(){
        return player1.equals(tournamentWinner) ? player1.getFullName() : player2.getFullName();
    }

    public String getPlayer1() {
        return player1.getFullName();
    }

    public String getPlayer2() {
        return player2.getFullName();
    }

    public T getMatchId() {
        return matchId;
    }

    public String getMatchNumber() {
        return matchNumber.toString();
    }



}





