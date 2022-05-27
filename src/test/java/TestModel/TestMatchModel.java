package TestModel;

public class TestMatchModel extends TestTournamentModel {
    public int matchNum;
    public int matchId;
    private TestTournamentModel tournamentModel;
    private TestPlayerModel winner, loser;

    public TestMatchModel(int matchId, int matchNum, TestTournamentModel tournament, TestPlayerModel winner, TestPlayerModel loser) {
        this.matchNum = matchNum;
        this.winner = winner;
        this.loser = loser;
        super(tournament);
        matchId = tournament.getTestTourneyId() +  matchNum;
    }

    public int getMatchNum() {return matchNum;}

    public int getMatchId() {
        return matchId;
    }

    public String getTourneyName() {return tournamentModel.getTestTourneyName();}

    public String getMatchWinner() {return winner.getFirstName().concat(" ").concat(winner.getLastName());}

    public String getMatchLoser() {return loser.getFirstName().concat(" ").concat(loser.getLastName());}
}
