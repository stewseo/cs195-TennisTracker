package com.example.cs195tennis.model;

import org.jooq.Result;

import java.util.*;

public class Match extends Tournament {

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

    public Match(String key, String tourney_name, String date) {
    }

    public Match(List<String> row) {
    }

    public Match(String string, String tourney_name, String surface, String draw_size, String tourney_level, String tourney_date, String match_num, String winner_id, String winner_seed, String winner_entry, String winner_name, String winner_hand, String winner_ht, String winner_ioc, String winner_age, String loser_id, String loser_seed, String loser_entry, String loser_name, String loser_hand, String winner_hand1, String loser_ht, String loser_ioc, String loser_age, String score, String best_of, String round, String minutes, String w_ace, String w_df, String w_svpt, String w_1stIn, String w_1stWon, String w_2ndWon, String w_bpSaved, String w_bpFaced, String l_ace, String l_df, String l_svpt, String l_1stIn, String l_1stWon, String l_2ndWon, String l_svGms, String l_bpSaved, String l_bpFaced, String winner_rank, String winner_rank_points, String loser_rank, String loser_rank_points) {
    }

    public Match(int size, Result result) {
    }

    public String getTourney_id() {
        return tournament.getTourney_id();
    }

    public String getTourney_name() {
        return tourney_name = tourney_name;
    }

    public void setTourney_name(String tourney_name) {
        tourney_name = tourney_name;
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

    public Match(String match_id) {
        this.match_id = match_id;
    }

    public Match() {}

    @Override
    public int hashCode() {
        return Objects.hash(match_id);
    }

    public WtaPlayer getWtaWinner() {
        return wtaWinner;
    }

    public void setWtaWinner(WtaPlayer wtaWinner) {
        wtaWinner = wtaWinner;
    }

    public WtaPlayer getWtaLoser() {
        return wtaLoser;
    }

    public void setWtaLoser(WtaPlayer wtaLoser) {
        wtaLoser = wtaLoser;
    }

    public AtpPlayer getAtpWinner() {
        return atpWinner;
    }

    public void setAtpWinner(AtpPlayer atpWinner) {
        this.atpWinner = atpWinner;
    }

    public AtpPlayer getAtpLoser() {
        return atpLoser;
    }

    public void setAtpLoser(AtpPlayer atpLoser) {
        this.atpLoser = atpLoser;
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

    public PlayerRanking getWtaRank() {
        return wtaRank;
    }

    public void setWtaRank(PlayerRanking wtaRank) {
        this.wtaRank = wtaRank;
    }

    public static void setMatchStats(String[] matchStats) {
        Match.matchStats = matchStats;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}





