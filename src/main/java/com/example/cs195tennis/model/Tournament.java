package com.example.cs195tennis.model;

import org.jooq.Field;
import org.jooq.Record;

import java.util.*;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.using;

public class Tournament<T> {

    T round;
    private T tourneyId, tourneyName, tourneyDate, surface,
            tourneyLevel, courtId, courtName, eventName, tournamentWinner;

    public Tournament() {}

    public Tournament(T tourneyName, T tourneyDate) {
        this.tourneyName = tourneyName;
        this.tourneyDate = tourneyDate;
        surface = null;
        tourneyLevel = null;
        courtId = null;
        courtName = null;
    }

    public Tournament(T tourneyName, T tourneyDate, T tourneyId, List<T> tournamentCourtStats) {
        this.tourneyName = tourneyName;
        this.tourneyId = tourneyId;
        this.tourneyDate = tourneyDate;
        courtId = tournamentCourtStats.get(0);
        courtName = tournamentCourtStats.get(1);
        round = tournamentCourtStats.get(2);
        eventName = tournamentCourtStats.get(3);
        tournamentWinner = null;
    }
    public Tournament(T tourneyName, T tourneyDate, List<T> tournamentCourtStats, T tournamentWinner) {
        this.tourneyName = tourneyName;
        this.tourneyDate = tourneyDate;
        courtId = tournamentCourtStats.get(0);
        courtName = tournamentCourtStats.get(1);
        round = tournamentCourtStats.get(2);
        eventName = tournamentCourtStats.get(3);
        this.tournamentWinner = tournamentWinner;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament<?> that = (Tournament<?>) o;
        return Objects.equals(getTourneyId(), that.getTourneyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTourneyId());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(", ").append(tourneyName).append(", ").append(tourneyDate).toString();
    }

    public String getRound() {return round.toString();}

    public String getTourneyLevel() {return tourneyLevel.toString();}

    public String getTourneyId() {
        return tourneyId.toString();
    }

    public String getTourneyName() {
        return tourneyName.toString();
    }

    public String getTourneyDate() {
        return tourneyDate.toString();
    }

    public String getSurface() {
        return surface.toString();
    }

    public String getCourtId() {
        return courtId.toString();
    }

    public String getCourtName() {
        return courtName.toString();
    }

    public String getEventName() {
        return eventName.toString();
    }

}
