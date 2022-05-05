package com.example.cs195tennis.model;

public class ShotType {
    String match_id, crosscourt,down_middle,down_the_line,inside_out,inside_in;
    String player,shots_in_pts_won;
    String row;
    String shots;
    String pt_ending;
    String winners;
    String induced_forced;
    String unforced;
    String serve_return;
    String shots_in_pts_lost;

    public ShotType(String match_id, String player, String row, String crosscourt, String down_middle, String down_the_line, String inside_out, String inside_in){
        this.match_id = match_id;
        this.player = player;
        this.row = row;
        this.crosscourt = crosscourt;
        this.down_middle = down_middle;
        this.down_the_line = down_the_line;
        this.inside_out = inside_out;
        this.inside_in = inside_in;
    }

    public ShotType(String match_id, String player, String row, String shots, String pt_ending, String winners, String induced_forced,
                    String unforced, String shots_in_pts_won, String shots_in_pts_lost){
        this.match_id = match_id;
        this.player = player;
        this.row = row;
        this.shots = shots;
        this.pt_ending = pt_ending;
        this.winners = winners;
        this.induced_forced = induced_forced;
        this.unforced = unforced;
        this.shots_in_pts_lost = shots_in_pts_lost;
        this.shots_in_pts_won = shots_in_pts_won;
    }

    public ShotType(String match_id, String player, String row, String shots, String pt_ending, String winners,
                    String induced_forced, String unforced, String serve_return, String shots_in_pts_won, String shots_in_pts_lost) {

        this.match_id = match_id;
        this.player = player;
        this.row = row;
        this.shots = shots;
        this.pt_ending = pt_ending;
        this.winners = winners;
        this.induced_forced = induced_forced;
        this.unforced = unforced;
        this.serve_return = serve_return;
        this.shots_in_pts_won = shots_in_pts_won;
        this.shots_in_pts_lost = shots_in_pts_lost;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getShots() {
        return shots;
    }

    public void setShots(String shots) {
        this.shots = shots;
    }

    public String getPt_ending() {
        return pt_ending;
    }

    public void setPt_ending(String pt_ending) {
        this.pt_ending = pt_ending;
    }

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public String getInduced_forced() {
        return induced_forced;
    }

    public void setInduced_forced(String induced_forced) {
        this.induced_forced = induced_forced;
    }

    public String getUnforced() {
        return unforced;
    }

    public void setUnforced(String unforced) {
        this.unforced = unforced;
    }

    public String getServe_return() {
        return serve_return;
    }

    public void setServe_return(String serve_return) {
        this.serve_return = serve_return;
    }

    public String getShots_in_pts_won() {
        return shots_in_pts_won;
    }

    public void setShots_in_pts_won(String shots_in_pts_won) {
        this.shots_in_pts_won = shots_in_pts_won;
    }

    public String getShots_in_pts_lost() {
        return shots_in_pts_lost;
    }

    public void setShots_in_pts_lost(String shots_in_pts_lost) {
        this.shots_in_pts_lost = shots_in_pts_lost;
    }
}
