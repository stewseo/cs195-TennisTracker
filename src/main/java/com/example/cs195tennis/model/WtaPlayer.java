package com.example.cs195tennis.model;

import org.jooq.Record;

import java.util.List;

public class WtaPlayer {
    private PlayerRanking rank;
    private String dominantHand;
    private String dateOfBirth;
    private String location;
    private String rankDate;
    private int id;
    private String playerId;
    private String playerRank;
    private String rankingPoints;
    public String firstName,lastName,height,ioc,dob,hand,wiki;

    public WtaPlayer(int id, String firstName, String lastName, String hand, String dob, String ioc, String height, String wiki) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hand = hand;
        this.dob = dob;
        this.ioc = ioc;
        this.height = height;
        this.wiki = wiki;
    }
    public WtaPlayer(){}

    public WtaPlayer(String string, String string1, String string2, String string3) {
    }

    public WtaPlayer(int id, String lastName, String dominantHand, String dateOfBirth, String location,
                     String height, String rankDate, String playerId, String playerRank, String rankingPoints, String fullName) {
        this.id = id;
        this.lastName = lastName;
        this.dominantHand = dominantHand;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
        this.height = height;
        this.rankDate = rankDate;
        this.playerId = playerId;
        this.playerRank = playerRank;
        rank = new PlayerRanking(rankDate, playerId, playerRank, rankingPoints);
    }

    public WtaPlayer(int id, List<Record> recordList) {
    }

    public String getDominantHand() {
        return dominantHand;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getLocation() {
        return location;
    }

    public String getRankDate() {
        return rankDate;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerRank() {
        return playerRank;
    }

    public String getRankingPoints() {
        return rankingPoints;
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

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setDominantHand(String dominantHand) {
        this.dominantHand = dominantHand;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayerRank(String playerRank) {
        this.playerRank = playerRank;
    }

    public void setRankingPoints(String rankingPoints) {
        this.rankingPoints = rankingPoints;
    }

}
