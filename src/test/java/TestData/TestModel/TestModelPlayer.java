package TestData.TestModel;

import com.example.cs195tennis.model.PlayerRanking;

import java.util.Objects;

public class TestModelPlayer {

    public String fullName, id;

    static PlayerRanking playerRank;

    static String[] playerStats;

    public TestModelPlayer(String id, String fullName, String[] stats, PlayerRanking playerRanking) {
        this.id = id;
        this.fullName=fullName;
        playerStats= stats;
        playerRanking= playerRank;c
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TestModelPlayer atpPlayer = (TestModelPlayer) o;

        return Objects.equals(getFullName(), atpPlayer.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFullName());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public static String[] getPlayerStats() {
        return playerStats;
    }

    public static void setPlayerStats(String[] playerStats) {
        TestModelPlayer.playerStats = playerStats;
    }
}
