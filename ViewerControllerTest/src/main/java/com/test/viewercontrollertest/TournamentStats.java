package com.test.viewercontrollertest;

public class TournamentStats {
    private static int tournamentIdAct = 0;
    private int tournamentId;
    private String tournamentName;
    private COURT court;

    public TournamentStats(String tournamentName, COURT court) {
        tournamentId = tournamentIdAct++;
        this.tournamentName = tournamentName;
        this.court = court;
    }

    enum COURT {
        GRASS, CLAY, HARD
    }

    public static int getTournamentIdAct() {
        return tournamentIdAct;
    }

    public static void setTournamentIdAct(int tournamentIdAct) {
        TournamentStats.tournamentIdAct = tournamentIdAct;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public COURT getCourt() {
        return court;
    }

    public void setCourt(COURT court) {
        this.court = court;
    }
}
