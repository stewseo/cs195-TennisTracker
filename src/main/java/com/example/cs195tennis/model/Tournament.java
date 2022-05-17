package com.example.cs195tennis.model;

import com.example.cs195tennis.database.Database;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Query;
import org.jooq.SQLDialect;
import org.sqlite.SQLiteLimits;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.jooq.impl.DSL.using;
import static org.kordamp.ikonli.materialdesign2.MaterialDesignT.TOURNAMENT;

public class Tournament {

    private int tourneyId;
    public String tourney_id,tourney_name,tourney_date;
    public String surface;
    public String draw_size;
    public String tourney_level;
    public String score;
    public Player winner, loser;
    public Object o1, o2;
    public List<String> matchStats;


    public Tournament(String tourney_id, String tourney_name, String tourney_date, Player winner, Player loser,  List<String> matchStats) {
        this.tourney_id = tourney_id;
        this.tourney_name = tourney_name;
        this.tourney_date = tourney_date;
        this.winner = winner;
        this.loser = loser;
        this.matchStats = matchStats;
    }


    public Tournament(Object o, Object o1) {
        this.o1 = o; o2 = o1;
    }

    public Tournament(Field<?>[] fields) {

        for(int i=0; i<6; i++){

            this.tourney_id = fields[i].toString();
        }

        IntStream.range(6,fields.length).forEach(e-> {

            matchStats.add(String.valueOf(e));
        });
    }

    DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);

    Query query = ctx.select();

    public String toString() {
        StringBuilder sb = new StringBuilder(tourney_id);

        return sb.append(", ").append(tourney_name).append(", ").append(surface).append(", ")
                .append(draw_size)
                .append(", ").append(tourney_level)
                .append(", ").append(tourney_date).toString();
    }

    public String dateFormat(String date) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String strDate = formatter.format(date);

        return strDate;

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

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }

    public Object getO1() {
        return o1;
    }

    public void setO1(Object o1) {
        this.o1 = o1;
    }

    public Object getO2() {
        return o2;
    }

    public void setO2(Object o2) {
        this.o2 = o2;
    }

    public List<String> getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(List<String> matchStats) {
        this.matchStats = matchStats;
    }

    public Object getMatches() {
        return matchStats;
    }
}