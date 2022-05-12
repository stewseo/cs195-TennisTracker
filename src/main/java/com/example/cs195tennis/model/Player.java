package com.example.cs195tennis.model;

import java.util.List;
import java.util.Objects;

public class Player {
        public static Ranking ranking;
        public String firstName, lastName, fullName, height, id,rank,ioc,dob,hand,wiki;
        public List<Player> playerList;

        public Player(){}

        public Player(String firstName) {
            this.firstName = firstName;
        }

    public Player(String ranking_date, String points, String player, String dob) {
            ranking = new Ranking(ranking_date, points, player, dob);
    }


    @Override
        public String toString(){

            return id + " " + fullName + " " + hand + " " +  dob + " " + wiki;
        }

    public Player(String id, String firstName, String lastName, String hand, String dob, String ioc, String height, String wiki) {
        this.id = id;
        this.fullName = firstName.concat(" " + lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hand = hand;
        this.dob = dob;
        this.ioc = ioc;
        this.height = height;
        this.wiki = wiki;
    }


    public Player(String id, String firstName, String lastName, String hand, String dob, String ioc, String height, String wiki, String rank) {
            this.id = id;
            this.fullName = firstName.concat("_" + lastName);
            this.firstName = firstName;
            this.lastName = lastName;
            this.hand = hand;
            this.dob = dob;
            this.ioc = ioc;
            this.height = height;
            this.wiki = wiki;
            this.rank = rank;
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
            return Objects.hash(id);
        }

        static enum GENDER {
            M, F
        }

    public static class Ranking {
            String ranking_date, rank,player, points;

        public Ranking(){}

        public Ranking(Player p) {
            this(p.getRanking().getRanking_date(),
                    p.getRanking().getRank(),
                    p.getRanking().getPlayer(),
                    p.getRanking().getPoints());
        }

        public Ranking(String ranking_date, String rank, String player, String points) {
            this.ranking_date = ranking_date;
            this.rank = rank;
            this.player = player;
            this.points = points;
        }

        public String getPlayer() {
            return player;
        }

        public void setPlayer(String player) {
            this.player = player;
        }

        public String getRanking_date() {
            return ranking_date;
        }

        public void setRanking_date(String ranking_date) {
            this.ranking_date = ranking_date;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }

        public List<Player> getPlayerList() {
            return playerList;
        }

        public void setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
        }

        public Ranking getRanking() {
            return ranking;
        }

        public void setRanking(Ranking ranking) {
            this.ranking = ranking;
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

        public String getFullName() {
            return fullName;
        }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
