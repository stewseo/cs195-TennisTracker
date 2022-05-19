
package com.example.cs195tennis.model;

import com.example.cs195tennis.database.Database;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.SimpleSelector;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.sqlite.SQLiteLimits;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.using;
import static org.kordamp.ikonli.materialdesign2.MaterialDesignT.TOURNAMENT;

public class Tournament {

    public Tournament() {}

    private int tourneyId;
    public String tourney_id,tourney_name,tourney_date;
    public String surface,draw_size,tourney_level;
    public String score;
    public String winner, loser;
    public Object o1, o2;
    String[] matchStats;
    private Player player;


    public Tournament(String tourney_id, String tourney_name, String tourney_date, String winner, String loser, String level, String draw_size,  String drawSize, String[] matchStats) {
        this.tourney_id = tourney_id;
        this.tourney_name = tourney_name;
        this.tourney_date = tourney_date;
        this.winner = winner;
        this.loser = loser;
        this.tourney_level = level;
        this.draw_size = draw_size;
        this.matchStats = matchStats;
    }


    public Tournament(Object o, Object o1) {
        this.o1 = o; o2 = o1;
    }



    public Tournament(Field<?>[] fields) {
        System.out.println(Arrays.stream(fields).count());
    }



    DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);

    Query query = ctx.select();

    public Tournament(String tourney_id, String tourney_name, String tourney_date, String winner_name, String loser_name, String tourney_level, String surface, String draw_size) {
        this.tourney_id = tourney_id;
        this.tourney_name = tourney_name;
        this.tourney_date = tourney_date;
        this.winner = winner_name;
        this.loser = loser_name;
        this.tourney_level = tourney_level;
        this.surface = surface;
        this.draw_size = draw_size;
    }

    List<String> list;
    public Tournament(Record row) {

        for(int i=0; i< row.size(); i++){
            System.out.println(list.set(i, row.getValue(i).toString()));

        }
        this.tourney_id = list.get(0);
        this.tourney_name = list.get(1);
        this.tourney_date = list.get(5);
        this.winner = Objects.requireNonNull(row.get("winner")).toString();
        this.loser = Objects.requireNonNull(row.get("loser")).toString();
        this.tourney_level = Objects.requireNonNull(row.get("level")).toString();
        this.surface = Objects.requireNonNull(row.get("surface")).toString();
    }

    public Tournament(String id, String name, String date, String winnerName, String loserName) {
        this.tourney_name = id;
        this.tourney_id = name;
        this.tourney_name = date;
        this.tourney_date = winnerName;
        this.winner = loserName;
    }


    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public DSLContext getCtx() {
        return ctx;
    }

    public void setCtx(DSLContext ctx) {
        this.ctx = ctx;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

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

    public String getWinner() {
        return winner;
    }

    public String getLoser() {
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

    public String[] getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(String[] matchStats) {
        this.matchStats = matchStats;
    }

    public Object getMatches() {
        return matchStats;
    }

    public String getWinnerFullName() {
        return getWinner() + " " + getLoser();
    }
    public String getLoserFullName() {
        return getWinner() + " " + getLoser();
    }

}
