package com.example.cs195tennis.model;

import java.util.Objects;

public class Player{
    String name;
    int playerId;

    PlayerRanking ranking;

    WtaPlayer wtaPlayer;

    AtpPlayer atpPlayer;

    public String firstName, lastName, player_id;

    static String fullName;


    public Player(String winnerName, PlayerRanking playerRanking) {
        this.name = winnerName;
    }

    static enum GENDER {
        M, F
    }

    public Player(){}

    public Player(String firstName) {
            this.firstName = firstName;
        }


    public Player(String id, String firstName, String lastName) {
        this.player_id = id;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    static String fullName(){
        return fullName;
    }

    @Override
    public String toString(){
        return firstName  + " " +  lastName;
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

    @Override
    public int hashCode(){
        return Objects.hash(player_id);
    }

}
