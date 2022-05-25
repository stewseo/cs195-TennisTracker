
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
import java.util.*;
import java.util.stream.IntStream;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.using;
import static org.kordamp.ikonli.materialdesign2.MaterialDesignT.TOURNAMENT;

public class Tournament {

    public Tournament() {}

    private int tourneyId;
    public String tourneyName,tourneyDate, surface, draw_size, tourney_level;
    public List<Match> matches;
    private Player player1, player2;

    public Tournament(int id, String year, String tourneyName, Player player1, Player player2, Match match) {

        matches = new ArrayList<>();
        matches.add(match);

        tourneyId=id;
        tourneyDate=year;
        this.tourneyName=tourneyName;

        this.player1 = player1;
        this.player2 = player2;

        if(tourneyName == null) {
            matches = new ArrayList<>();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return getTourneyId() == that.getTourneyId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTourneyId());
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public String getPlayer1() {
        return player1.getFirstName();
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2.getFirstName();
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(", ").append(tourneyName).append(", ").append(tourneyDate).toString();
    }

    public int getTourneyId() {
        return tourneyId;
    }
    public void setTourneyId(int tourneyId) {
        this.tourneyId = tourneyId;
    }


    public String getTourneyName() {
        return tourneyName;
    }

    public void setTourneyName(String tourneyName) {
        this.tourneyName = tourneyName;
    }

    public String getTourneyDate() {
        return tourneyDate;
    }

    public void setTourneyDate(String tourneyDate) {
        this.tourneyDate = tourneyDate;
    }

    public String getSurface() {
        if(surface == null) {
            System.out.println("test");
        }
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

    public List<Match> getMatchStats() {
        return matches;
    }
    public void setMatchStats(List<Match> matchStats) {
        this.matches = matchStats;
    }

}
