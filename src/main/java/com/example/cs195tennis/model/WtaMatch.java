package com.example.cs195tennis.model;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WtaMatch extends Match {

    public String match_id, match_num, winner_id, loser_id, round;

    public WtaMatch(String key, String tourney_name, String date) {}


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




