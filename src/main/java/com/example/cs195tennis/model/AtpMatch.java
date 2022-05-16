package com.example.cs195tennis.model;

import java.util.Comparator;
import java.util.Objects;

public class AtpMatch extends Match {

        public AtpPlayer winner,loser;

        private String match_id, winnerName, loserName;

        private int matchId;

        public AtpMatch(String tourney_id, String tourney_name, String tourney_date, String winner_name,
                        String loser_name, String score, String round, String winner_id, String loser_id) {


            this.winnerName = winner_name;
            this.loserName = loser_name;
        }


    public AtpPlayer getWinner() {
        return winner;
    }

    public void setWinner(AtpPlayer winner) {
        this.winner = winner;
    }

    public AtpPlayer getLoser() {
        return loser;
    }

    public void setLoser(AtpPlayer loser) {
        this.loser = loser;
    }

    @Override
    public String getMatch_id() {
        return match_id;
    }

    @Override
    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    @Override
    public String getWinnerName() {
        return winnerName;
    }

    @Override
    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    @Override
    public String getLoserName() {
        return loserName;
    }

    @Override
    public void setLoserName(String loserName) {
        this.loserName = loserName;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}


