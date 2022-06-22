package com.example.cs195tennis.model;

import org.jooq.Field;
import org.jooq.impl.QOM;

import java.util.*;

public class Match<T extends Comparable<? super T>> implements Comparator<Object> {
    private Tournament<T> tournament;
    private Player player1, player2;
    int matchId;
    T matchNumber, tournamentWinner;

//    public Match(int matchId, T tourneyName, T tourneyDate, List<T> tournamentCourtStats, T matchNumber, List<T> playerFields) {
//        tournament = new Tournament(matchId, tourneyName, tourneyDate, tournamentCourtStats);
//        this.matchId = matchId;
//        this.matchNumber = matchNumber;
//        player1 = new Player(playerFields.get(0),playerFields.get(1),playerFields.get(2));
//        player2 = new Player(playerFields.get(3),playerFields.get(4),playerFields.get(5));
//    }

    public Match() {}

    public Match(int id, Object tourneyName, Object tourneyDate, List<Object> tournamentFields, Object matchNum, Object winner, List<Object> playerFields) {
        tournament = new Tournament(tourneyName, tourneyDate,tournamentFields,winner);
        this.matchId = id;
        this.matchNumber = (T) matchNum;
        player1 = new Player(playerFields.get(0),playerFields.get(1),playerFields.get(2));
        player2 = new Player(playerFields.get(3),playerFields.get(4),playerFields.get(5));
    }

    public Match(int id, Object tourneyName, Object tourneyDate, List<Object> tournamentFields, Object matchNum, List<Object> playerFields) {
        tournament = new Tournament(matchId, tourneyName, tourneyDate, playerFields);
        this.matchId = matchId;
        this.matchNumber = matchNumber;
        player1 = new Player(playerFields.get(0),playerFields.get(1),playerFields.get(2));
        player2 = new Player(playerFields.get(3),playerFields.get(4),playerFields.get(5));
    }


    public String getTournamentWinner(){
        return player1.equals(tournamentWinner) ? player1.getFullName() : player2.getFullName();
    }

    public String getPlayer1() {

        return player1.getFullName();
    }

    public String getPlayer2() {
        return player2.getFullName();
    }

    public int getMatchId() {
        return matchId;
    }

    public String getMatchNumber() {
        return matchNumber.toString();
    }


    public String getTourneyName() {
        return tournament.getTourneyName();
    }
    public String getTourneyDate() {
        return tournament.getTourneyDate();
    }

    public String getRound() {return tournament.getRound();}

    public String getCourtId() {
        return tournament.getCourtId();
    }

    public String getCourtName() {
        return tournament.getCourtName();
    }

    public String getEventName() {
        return tournament.getEventName();
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}





