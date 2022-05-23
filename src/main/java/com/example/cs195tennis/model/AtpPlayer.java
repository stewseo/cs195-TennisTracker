package com.example.cs195tennis.model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class AtpPlayer extends Player {

    public String fullName, id;

    private PlayerRanking playerRanking;

    static String[] playerStats;

    public AtpPlayer(String id, String fullName, PlayerRanking playerRanking, String[] strings) {
        this.id = id;
        this.fullName = fullName;
        this.playerRanking = playerRanking;
        playerStats = strings;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AtpPlayer atpPlayer = (AtpPlayer) o;

        return Objects.equals(getFullName(), atpPlayer.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFullName());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlayerRanking getPlayerRanking() {
        return playerRanking;
    }

    public void setPlayerRanking(PlayerRanking playerRanking) {
        this.playerRanking = playerRanking;
    }

    public static String[] getPlayerStats() {
        return playerStats;
    }

    public static void setPlayerStats(String[] playerStats) {
        AtpPlayer.playerStats = playerStats;
    }
}
