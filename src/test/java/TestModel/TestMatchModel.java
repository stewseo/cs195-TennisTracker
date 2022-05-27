package TestModel;

public class TestMatchModel {
    public String name;
    public int matchNum;
    public int matchId;



    public TestMatchModel(String name, int matchNum) {
        this.name = name;
        this.matchNum = matchNum;
        matchId = (matchNum + name.hashCode());
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getName() {
        return name;
    }
    public int matchId() {
        return matchId;
    }
}
