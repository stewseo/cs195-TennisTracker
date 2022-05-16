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
    public WtaPlayer(){}

    public WtaPlayer(String string, String string1, String string2, String string3) {
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
