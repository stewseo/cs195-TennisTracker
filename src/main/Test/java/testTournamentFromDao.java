import java.util.List;

class testTournamentFromDao {

    private int tourneyId;
    public String tourneyName,tourneyDate;
    List<String> tournamentStats;
    String[] matchStats;
    private Object winner, loser;


    public testTournamentFromDao(int id, String year, String tourneyName, Object player, Object player1, Object match) {
        tourneyId=id;
        tourneyDate=year;
        this.tourneyName=tourneyName;

        if(player.equals(player1)){
            winner = player;
        }
        else{
            winner = player1;
        }

    }

    public int getTourneyId() {
        return tourneyId;
    }

    public void setTourneyId(int tourneyId) {
        this.tourneyId = tourneyId;
    }

    public String getTourneyName() {
        return tourneyName;
    }

    public void setTourneyName(String tourneyName) {
        this.tourneyName = tourneyName;
    }

    public String getTourneyDate() {
        return tourneyDate;
    }

    public void setTourneyDate(String tourneyDate) {
        this.tourneyDate = tourneyDate;
    }

    public List<String> getTournamentStats() {
        return tournamentStats;
    }

    public void setTournamentStats(List<String> tournamentStats) {
        this.tournamentStats = tournamentStats;
    }

    public String[] getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(String[] matchStats) {
        this.matchStats = matchStats;
    }

    public Object getWinner() {
        return winner;
    }

    public void setWinner(Object winner) {
        this.winner = winner;
    }

    public Object getLoser() {
        return loser;
    }

    public void setLoser(Object loser) {
        this.loser = loser;
    }
}