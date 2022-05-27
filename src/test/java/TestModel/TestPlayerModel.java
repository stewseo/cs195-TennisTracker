package TestModel;

public class TestPlayerModel {
    private String firstName, lastName;
    private int playerId;

    public TestPlayerModel(GENDER GENDER, String firstName, String lastName, int playerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerId = playerId;
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

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    static enum GENDER {

    }

    static class Rank {
        private String rankingDate, player, rank, rankPoint;

    }
}
