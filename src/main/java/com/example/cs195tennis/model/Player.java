package com.example.cs195tennis.model;

import java.util.List;

    public class Player {

        public String firstName, height, id,rank,ioc,dob,hand,lastName,wiki;
        public List<Player> playerList;
        public Player(){}

        @Override
        public String toString(){

            return id + " " + firstName + " " + lastName + " " + hand + " " +  dob + " " + wiki;
        }


        public Player(String id, String firstName, String lastName, String hand, String dob, String ioc, String height, String wiki) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.hand = hand;
            this.dob = dob;
            this.ioc = ioc;
            this.height = height;
            this.wiki = wiki;
        }



        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getHand() {
            return hand;
        }

        public void setHand(String hand) {
            this.hand = hand;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getIoc() {
            return ioc;
        }

        public void setIoc(String ioc) {
            this.ioc = ioc;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWiki() {
            return wiki;
        }

        public void setWiki(String wiki) {
            this.wiki = wiki;
        }


    }
