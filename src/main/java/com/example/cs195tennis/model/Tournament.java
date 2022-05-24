
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
    public String tourneyName,tourneyDate;
    public String surface,draw_size,tourney_level,score;
    String[] matchStats;
    private Player winner, loser;

    public Tournament(int tourney_id, String tourney_name, String tourney_date, Player winner, Player loser, String[] matchStats) {
        this.tourneyId = tourney_id;
        this.tourneyName = tourney_name;
        this.tourneyDate = tourney_date;
        this.winner = winner;
        this.loser = loser;
        this.matchStats = matchStats;
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

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setLoser(Player loser) {
        this.loser = loser;
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

    public void setScore(String score) {
        this.score = score;
    }
    public String getScore() {return score;}

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
        return winner.getFirstName() +" " +winner.getLastName();
    }

    public String getLoser() {
        return loser.getFirstName() +" " +loser.getLastName();
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
