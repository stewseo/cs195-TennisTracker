package TestModel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTournamentModel {
    private String testTourneyName, testDate;
    private int testTourneyId;
    TestPlayerModel testWinner;

    public TestTournamentModel(String testTourneyName, String testDate, int testTourneyId, TestPlayerModel testWinner) {
        this.testTourneyName = testTourneyName;
        this.testDate = testDate;
        this.testTourneyId = testTourneyId;
        this.testWinner = testWinner;
    }

    public TestTournamentModel(String testTourneyName, String testDate, int testTourneyId) {
        this.testTourneyName = testTourneyName;
        this.testDate = testDate;
        this.testTourneyId = testTourneyId;
    }

    public TestTournamentModel() {
        assertNotNull(getTestTourneyName());
    }


    public String getTestTourneyName() {return testTourneyName;}

    public void setTestTourneyName(String testTourneyName) {this.testTourneyName = testTourneyName;}

    public String getTestDate() {return testDate;}

    public int getTestTourneyId() {return testTourneyId;}

    public TestPlayerModel getTestWinner() {return testWinner;}

}
