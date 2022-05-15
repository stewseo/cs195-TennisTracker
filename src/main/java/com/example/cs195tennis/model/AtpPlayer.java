package com.example.cs195tennis.model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class AtpPlayer extends Player {

    public String firstName, lastName, height, player_id,ioc, dob, hand, wiki, ranking, fullName;

    static PlayerRanking atp;

    static List<Player> playerStats;

    public AtpPlayer(String playerId, String firstName, String lastName, String hand, String dob, String ioc, String height, String wiki) {
        this.player_id = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hand = hand;
        this.dob = dob;
        this.ioc = ioc;
        this.height = height;
        this.wiki = wiki;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static PlayerRanking getAtp() {
        return atp;
    }

    public static void setAtp(PlayerRanking atp) {
        AtpPlayer.atp = atp;
    }

    public static List<Player> getPlayerStats() {
        return playerStats;
    }

    public static void setPlayerStats(List<Player> playerStats) {
        AtpPlayer.playerStats = playerStats;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getIoc() {
        return ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }


    public String getRanking(){
        return ranking;
    }

}
