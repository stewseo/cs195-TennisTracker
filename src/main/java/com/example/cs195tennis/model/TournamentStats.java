package com.example.cs195tennis.model;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TournamentStats {

    public String tourney_name;
    public String tourney_date;
    public String loser_name;
    public String tourney_id;
    public String surface;
    public int draw_size;
    public String tourney_level;
    public String match_num;
    public String winner_id;
    public String winner_seed;
    public String winner_entry;
    public String winner_name;
    public String winner_hand;
    public String winner_ht;
    public String winner_ioc;
    public String winner_age;
    public String loser_id;
    public String loser_seed;
    public String loser_hand;
    public String loser_ht;
    public String loser_ioc;
    public String loser_age;
    public String score;
    public int best_of;
    public String minutes;
    public String w_ace;
    public String w_double_fault;
    public String w_serve_points;
    public String w_1st_serves;
    public String w_1st_serves_won;
    public String w_2nd_serves_won;
    public String w_serve_games;
    public String w_break_points_saved;
    public String w_break_points_faced;
    public String l_ace;
    public String l_double_fault;
    public String l_serve_points;
    public String l_1st_serves;
    public String l_1st_serves_won;
    public String l_2nd_serves_won;
    public String l_serve_games;
    public String l_break_points_saved;
    public String l_break_points_faced;
    public String winner_rank;
    public String winner_rank_points;
    public String loser_rank;
    public String loser_rank_points;

    private List<TournamentStats> tournamentList;

    public List<TournamentStats> getTournamentList() {
        return tournamentList;
    }

    public void setTournamentList(List<TournamentStats> tournamentList) {
        this.tournamentList = tournamentList;
    }

    public String toString(){
        return tourney_name + ", " +tourney_date + ", " + winner_name + ", " + loser_name + ", " + tourney_id;
    }
    public TournamentStats(){}

    public TournamentStats(String tourney_name, String tourney_date, String winner_name, String loser_name, String tournament_id) {
        this.tourney_name = tourney_name;
        this.tourney_date = tourney_date;
        this.winner_name = winner_name;
        this.loser_name = loser_name;
        this.tourney_id = tournament_id;
    }

    public void setTournamentStats(List<TournamentStats> list) {
        tournamentList = list;
    }

    public String getTourney_name() {
        return tourney_name;
    }

    public void setTourney_name(String tourney_name) {
        this.tourney_name = tourney_name;
    }

    public String getTourney_date() {
        return tourney_date;
    }

    public void setTourney_date(String tourney_date) {
        this.tourney_date = tourney_date;
    }

    public String getTourney_id() {
        return tourney_id;
    }

    public void setTourney_id(String tourney_id) {
        this.tourney_id = tourney_id;
    }

    public String getLoser_name() {
        return loser_name;
    }

    public void setLoser_name(String loser_name) {
        this.loser_name = loser_name;
    }

    public String getWinner_name() {
        return winner_name;
    }

    public void setWinner_name(String winner_name) {
        this.winner_name = winner_name;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public int getDraw_size() {
        return draw_size;
    }

    public void setDraw_size(int draw_size) {
        this.draw_size = draw_size;
    }

    public String getTourney_level() {
        return tourney_level;
    }

    public void setTourney_level(String tourney_level) {
        this.tourney_level = tourney_level;
    }

    public String getMatch_num() {
        return match_num;
    }

    public void setMatch_num(String match_num) {
        this.match_num = match_num;
    }

    public String getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(String winner_id) {
        this.winner_id = winner_id;
    }

    public String getWinner_seed() {
        return winner_seed;
    }

    public void setWinner_seed(String winner_seed) {
        this.winner_seed = winner_seed;
    }

    public String getWinner_entry() {
        return winner_entry;
    }

    public void setWinner_entry(String winner_entry) {
        this.winner_entry = winner_entry;
    }

    public String getWinner_hand() {
        return winner_hand;
    }

    public void setWinner_hand(String winner_hand) {
        this.winner_hand = winner_hand;
    }

    public String getWinner_ht() {
        return winner_ht;
    }

    public void setWinner_ht(String winner_ht) {
        this.winner_ht = winner_ht;
    }

    public String getWinner_ioc() {
        return winner_ioc;
    }

    public void setWinner_ioc(String winner_ioc) {
        this.winner_ioc = winner_ioc;
    }

    public String getWinner_age() {
        return winner_age;
    }

    public void setWinner_age(String winner_age) {
        this.winner_age = winner_age;
    }

    public String getLoser_id() {
        return loser_id;
    }

    public void setLoser_id(String loser_id) {
        this.loser_id = loser_id;
    }

    public String getLoser_seed() {
        return loser_seed;
    }

    public void setLoser_seed(String loser_seed) {
        this.loser_seed = loser_seed;
    }

    public String getLoser_hand() {
        return loser_hand;
    }

    public void setLoser_hand(String loser_hand) {
        this.loser_hand = loser_hand;
    }

    public String getLoser_ht() {
        return loser_ht;
    }

    public void setLoser_ht(String loser_ht) {
        this.loser_ht = loser_ht;
    }

    public String getLoser_ioc() {
        return loser_ioc;
    }

    public void setLoser_ioc(String loser_ioc) {
        this.loser_ioc = loser_ioc;
    }

    public String getLoser_age() {
        return loser_age;
    }

    public void setLoser_age(String loser_age) {
        this.loser_age = loser_age;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getBest_of() {
        return best_of;
    }

    public void setBest_of(int best_of) {
        this.best_of = best_of;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getW_ace() {
        return w_ace;
    }

    public void setW_ace(String w_ace) {
        this.w_ace = w_ace;
    }

    public String getW_double_fault() {
        return w_double_fault;
    }

    public void setW_double_fault(String w_double_fault) {
        this.w_double_fault = w_double_fault;
    }

    public String getW_serve_points() {
        return w_serve_points;
    }

    public void setW_serve_points(String w_serve_points) {
        this.w_serve_points = w_serve_points;
    }

    public String getW_1st_serves() {
        return w_1st_serves;
    }

    public void setW_1st_serves(String w_1st_serves) {
        this.w_1st_serves = w_1st_serves;
    }

    public String getW_1st_serves_won() {
        return w_1st_serves_won;
    }

    public void setW_1st_serves_won(String w_1st_serves_won) {
        this.w_1st_serves_won = w_1st_serves_won;
    }

    public String getW_2nd_serves_won() {
        return w_2nd_serves_won;
    }

    public void setW_2nd_serves_won(String w_2nd_serves_won) {
        this.w_2nd_serves_won = w_2nd_serves_won;
    }

    public String getW_serve_games() {
        return w_serve_games;
    }

    public void setW_serve_games(String w_serve_games) {
        this.w_serve_games = w_serve_games;
    }

    public String getW_break_points_saved() {
        return w_break_points_saved;
    }

    public void setW_break_points_saved(String w_break_points_saved) {
        this.w_break_points_saved = w_break_points_saved;
    }

    public String getW_break_points_faced() {
        return w_break_points_faced;
    }

    public void setW_break_points_faced(String w_break_points_faced) {
        this.w_break_points_faced = w_break_points_faced;
    }

    public String getL_ace() {
        return l_ace;
    }

    public void setL_ace(String l_ace) {
        this.l_ace = l_ace;
    }

    public String getL_double_fault() {
        return l_double_fault;
    }

    public void setL_double_fault(String l_double_fault) {
        this.l_double_fault = l_double_fault;
    }

    public String getL_serve_points() {
        return l_serve_points;
    }

    public void setL_serve_points(String l_serve_points) {
        this.l_serve_points = l_serve_points;
    }

    public String getL_1st_serves() {
        return l_1st_serves;
    }

    public void setL_1st_serves(String l_1st_serves) {
        this.l_1st_serves = l_1st_serves;
    }

    public String getL_1st_serves_won() {
        return l_1st_serves_won;
    }

    public void setL_1st_serves_won(String l_1st_serves_won) {
        this.l_1st_serves_won = l_1st_serves_won;
    }

    public String getL_2nd_serves_won() {
        return l_2nd_serves_won;
    }

    public void setL_2nd_serves_won(String l_2nd_serves_won) {
        this.l_2nd_serves_won = l_2nd_serves_won;
    }

    public String getL_serve_games() {
        return l_serve_games;
    }

    public void setL_serve_games(String l_serve_games) {
        this.l_serve_games = l_serve_games;
    }

    public String getL_break_points_saved() {
        return l_break_points_saved;
    }

    public void setL_break_points_saved(String l_break_points_saved) {
        this.l_break_points_saved = l_break_points_saved;
    }

    public String getL_break_points_faced() {
        return l_break_points_faced;
    }

    public void setL_break_points_faced(String l_break_points_faced) {
        this.l_break_points_faced = l_break_points_faced;
    }

    public String getWinner_rank() {
        return winner_rank;
    }

    public void setWinner_rank(String winner_rank) {
        this.winner_rank = winner_rank;
    }

    public String getWinner_rank_points() {
        return winner_rank_points;
    }

    public void setWinner_rank_points(String winner_rank_points) {
        this.winner_rank_points = winner_rank_points;
    }

    public String getLoser_rank() {
        return loser_rank;
    }

    public void setLoser_rank(String loser_rank) {
        this.loser_rank = loser_rank;
    }

    public String getLoser_rank_points() {
        return loser_rank_points;
    }

    public void setLoser_rank_points(String loser_rank_points) {
        this.loser_rank_points = loser_rank_points;
    }
}