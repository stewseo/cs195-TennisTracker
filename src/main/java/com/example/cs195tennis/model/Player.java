package com.example.cs195tennis.model;

import java.util.Objects;

public class Player extends Match{
    String name;
    int playerId;

    PlayerRanking ranking;

    WtaPlayer wtaPlayer;

    AtpPlayer atpPlayer;

    public String firstName, lastName, player_id, nation1, nation2;

    static String fullName;


    public Player(String grandSlamChampion, PlayerRanking playerRanking) {
        super();
        this.name = grandSlamChampion;
    }

    public Player(Object o, String name, String[] strings, PlayerRanking playerRanking) {
        super();

    }

    static enum GENDER {
        M, F
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public PlayerRanking getRanking() {
        return ranking;
    }

    public void setRanking(PlayerRanking ranking) {
        this.ranking = ranking;
    }

    public WtaPlayer getWtaPlayer() {
        return wtaPlayer;
    }

    public void setWtaPlayer(WtaPlayer wtaPlayer) {
        this.wtaPlayer = wtaPlayer;
    }

    public AtpPlayer getAtpPlayer() {
        return atpPlayer;
    }

    public void setAtpPlayer(AtpPlayer atpPlayer) {
        this.atpPlayer = atpPlayer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getNation1() {
        return nation1;
    }

    public void setNation1(String nation1) {
        this.nation1 = nation1;
    }

    public String getNation2() {
        return nation2;
    }

    public void setNation2(String nation2) {
        this.nation2 = nation2;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        Player.fullName = fullName;
    }

    @Override
    public int hashCode(){
        return Objects.hash(player_id);
    }

}
