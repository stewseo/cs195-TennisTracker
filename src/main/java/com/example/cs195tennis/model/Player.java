package com.example.cs195tennis.model;

import org.jooq.Field;

import java.util.Objects;

public class Player extends Match{

    PlayerRanking ranking;

    String ATP, WTA;

    public String playerId, firstName, lastName, nation, fullName, champion,dominantHand,dateOfBirth;


    public Player(){}

    public Player(Object playerId,Object playerName, Object nation, Object dominantHand, Object dateOfBirth) {
        this.fullName = playerName.toString();
        this.playerId = playerId.toString();
        this.nation = nation.toString();
        this.dominantHand = dominantHand.toString();
        this.dateOfBirth = dateOfBirth.toString();
    }

    public Player(Object playerId,Object playerName, Object nation, Object dominantHand) {
        this.fullName = playerName.toString();
        this.playerId = playerId.toString();
        this.nation = nation.toString();
        this.dominantHand = dominantHand.toString();
        this.dateOfBirth = dateOfBirth.toString();
    }

    public Player(Object playerName, Object playerId, Object nation){
        this.fullName = playerName.toString();
        this.playerId = playerId.toString();
        this.nation = nation.toString();
        this.dominantHand = null;
        this.dateOfBirth = null;
    }

    public Player(Object playerId,Object playerName) {
        this.fullName = playerName.toString();
        this.playerId = playerId.toString();
        this.nation = nation.toString();
        this.dominantHand = dominantHand.toString();
        this.dateOfBirth = dateOfBirth.toString();
    }


    public String getPlayerId() {
        return playerId;
    }

    public PlayerRanking getRanking() {
        return ranking;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDominantHand() {
        return dominantHand;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getChampion() {
        return champion;
    }

    public String getATP() {
        return ATP;
    }

    public String getWTA() {
        return WTA;
    }

    public String getNation() {
        return nation;
    }

    public String getFullName() {
        return fullName;
    }
}
