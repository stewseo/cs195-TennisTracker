package com.example.cs195tennis.model;

public class WtaPlayer {
    public String firstName,lastName,height,id,ioc,dob,hand,wiki;

    public WtaPlayer(String id, String firstName, String lastName, String hand, String dob, String ioc, String height, String wiki) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hand = hand;
        this.dob = dob;
        this.ioc = ioc;
        this.height = height;
        this.wiki = wiki;
    }

    public WtaPlayer(String firstName, String lastName, String height, String id, String ioc, String dob, String hand, String wiki, PlayerRanking rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.id = id;
        this.ioc = ioc;
        this.dob = dob;
        this.hand = hand;
        this.wiki = wiki;
    }

    public WtaPlayer(String ranking_date, String rank, String player, String points) {
    }

    public WtaPlayer(String string, String string1, String string2, String string3, String string4, String string5) {

    }

    public WtaPlayer() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
