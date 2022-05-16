package com.example.cs195tennis.model;


import java.util.List;

public class Tournament {

    private int tourneyId;

    public String tourney_id,tourney_name,tourney_date;
    public String surface;
    public String draw_size;
    public String tourney_level;
    public String score;
    public Player winner, loser;

    public Match match;
    List<Match> matches;

    public Tournament(String tourney_id, String tourney_name, String surface, String draw_size, String tourney_level, String tourney_date) {
        this.tourney_id = tourney_id;
        this.tourney_name = tourney_name;
        this.surface = surface;
        this.draw_size = draw_size;
        this.tourney_level = tourney_level;
        this.tourney_date = tourney_date;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder(tourney_id);

        return sb.append(", ").append(tourney_name).append(", ").append(surface).append(", ")
                .append(draw_size)
                .append(", ").append(tourney_level)
                .append(", ").append(tourney_date).toString();
    }


    public int getTourneyId() {
        return tourneyId;
    }

    public void setTourneyId(int tourneyId) {
        this.tourneyId = tourneyId;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWinnerFullName() {
        return winner.getFirstName() + " " + winner.getLastName();
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public String getLoserFullName() {
        return loser.getFirstName() + " " + loser.getLastName();
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Tournament() {}

    public String getScore() {return score;}

    public String getTourney_id() {
        return tourney_id;
    }

    public void setTourney_id(String tourney_id) {
        this.tourney_id = tourney_id;
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

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getDraw_size() {
        return draw_size;
    }

    public void setDraw_size(String draw_size) {
        this.draw_size = draw_size;
    }

    public String getTourney_level() {
        return tourney_level;
    }

    public void setTourney_level(String tourney_level) {
        this.tourney_level = tourney_level;
    }

    public Tournament(String tourney_id, String tourney_name, String surface, String draw_size, String tourney_level, String tourney_date, String match_num) {
        this.tourney_id = tourney_id;
        this.tourney_name = tourney_name;
        this.tourney_level = tourney_date;
        this.surface = surface;
        this.draw_size = draw_size;
    }


}