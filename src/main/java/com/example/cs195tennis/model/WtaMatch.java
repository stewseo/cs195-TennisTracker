package com.example.cs195tennis.model;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WtaMatch extends Match {


    static WtaPlayer winner, loser;

    public Tournament tournament;

    public String match_id, match_num, winner_id, loser_id, round;

    public PlayerRanking wtaRanking;

    public Match matchData;

    public WtaMatch(Object object) {}

    public WtaMatch(String key, String tourney_name, String date) {}

    public WtaMatch(String tourney_id, String tourney_name, String surface, String draw_size, String tourney_level, String tourney_date, String match_num, String winner_id,
                    String winner_seed, String winner_entry, String winner_name, String winner_hand, String winner_ht, String winner_ioc, String winner_age, String loser_id, String loser_seed, String loser_entry, String loser_name, String loser_hand, String winner_hand1,
                    String loser_ht, String loser_ioc, String loser_age, String score, String best_of,
                    String round, String minutes, String w_ace, String w_df, String w_svpt, String w_1stIn, String w_1stWon, String w_2ndWon, String w_bpSaved, String w_bpFaced, String l_ace, String l_df, String l_svpt, String l_1stIn, String l_1stWon, String l_2ndWon,
                    String l_svGms, String l_bpSaved, String l_bpFaced, String winner_rank, String winner_rank_points, String loser_rank, String loser_rank_points) {


        super(new Tournament(tourney_id, tourney_name, surface, draw_size, tourney_level, tourney_date, match_num),

                new WtaPlayer(winner_id, winner_seed, winner_entry, winner_name, winner_hand, winner_ht, winner_ioc, winner_age),

                new WtaPlayer(loser_id, loser_seed,  loser_entry,  loser_name,  loser_hand, loser_ht,  loser_ioc, loser_age),

                new PlayerRanking(tourney_id, winner_id, winner_rank_points, winner_rank, loser_id, loser_rank_points, loser_rank),

                new String[]{ score,  best_of,  round,  minutes,  w_ace, w_df,  w_svpt,  w_1stIn,  w_1stWon,  w_2ndWon,
                        w_bpSaved, w_bpFaced,  l_ace,  l_df,  l_svpt,  l_1stIn,  l_1stWon,  l_2ndWon, l_svGms,  l_bpSaved,
                        l_bpFaced,  winner_rank,  winner_rank_points,  loser_rank,  loser_rank_points});
    }

    public WtaMatch(String tourney_id, String tourney_name, String tourney_date, String winner_name, String loser_name, String score, String round, String winner_id, String loser_id) {
        this.tourney_id = tourney_id;
        this.tourney_name = tourney_name;
        this.tourney_date = tourney_name;
        this.score = score;
        this.round = round;
        this.winner_id = winner_id;
        this.loser_id = loser_id;
    }

    public WtaMatch(String tourney_id, String tourney_name, String surface, String draw_size, String tourney_level, String tourney_date) {
        this.tourney_id = tourney_id;
        this.tourney_name = tourney_name;
        this.surface = surface;
        this.draw_size = draw_size;
        this.tourney_level = tourney_level;
        this.tourney_date = tourney_date;
    }

}




