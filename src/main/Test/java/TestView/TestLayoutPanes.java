package TestView;

import com.example.cs195tennis.model.AtpPlayer;
import com.example.cs195tennis.model.PlayerRanking;
import com.example.cs195tennis.model.WtaPlayer;

import java.util.Objects;

public class TestLayoutPanes {
    String name;
    int playerId;

    PlayerRanking ranking;

    WtaPlayer wtaPlayer;

    AtpPlayer atpPlayer;

    public String firstName, lastName, player_id, nation1, nation2;

    static String fullName;


    public TestLayoutPanes(String winnerName, PlayerRanking playerRanking) {
        this.name = winnerName;
    }

    public TestLayoutPanes(Object o, String name, String[] strings, PlayerRanking playerRanking) {

    }

    static enum GENDER {
        M, F
    }

    public TestLayoutPanes(){}

    public TestLayoutPanes(String firstName) {
            this.firstName = firstName;
        }


    public TestLayoutPanes(String id, String firstName, String lastName) {
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
