package com.example.cs195tennis.model;

import java.util.Comparator;
import java.util.Objects;

public class AtpMatch extends Match {

        public AtpPlayer winner,loser;

        private PlayerRanking.League atpLeague;

        private String match_id, winnerName, loserName;

        private int matchId;

        public AtpMatch(String tourney_id, String tourney_name, String tourney_date, String winner_name,
                        String loser_name, String score, String round, String winner_id, String loser_id) {

//            tourney_id, winner_name, loser_name, tourney_date, score, round

            matchId = Objects.hash(tourney_id);
            this.winnerName = winner_name;
            this.loserName = loser_name;
        }

    public AtpMatch(String key, String tourney_name, String surface, String draw_size, String tourney_level, String tourney_date, String match_num, String winner_id, String winner_seed, String winner_entry, String winner_name, String winner_hand, String winner_ht, String winner_ioc, String winner_age, String loser_id, String loser_seed, String loser_entry, String loser_name, String loser_hand, String winner_hand1, String loser_ht, String loser_ioc, String loser_age, String score, String best_of, String round, String minutes, String w_ace, String w_df, String w_svpt, String w_1stIn, String w_1stWon, String w_2ndWon, String w_bpSaved, String w_bpFaced, String l_ace, String l_df, String l_svpt, String l_1stIn, String l_1stWon, String l_2ndWon, String l_svGms, String l_bpSaved, String l_bpFaced, String winner_rank, String winner_rank_points, String loser_rank, String loser_rank_points) {
    }

    public AtpMatch(String key, String tourney_name, String surface, String draw_size, String tourney_level, String tourney_date, String match_num, String winner_id, String winner_seed, String winner_entry, String winner_name, String winner_hand, String winner_ht, String winner_ioc, String winner_age, String loser_id, String loser_seed, String loser_entry, String loser_name, String loser_hand) {
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

    public PlayerRanking.League getAtpLeague() {
        return atpLeague;
    }

    public void setAtpLeague(PlayerRanking.League atpLeague) {
        this.atpLeague = atpLeague;
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


