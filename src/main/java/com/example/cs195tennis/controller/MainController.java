package com.example.cs195tennis.controller;
import com.example.cs195tennis.TournamentListLoader;
import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Tournament;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXHamburger;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MainController implements Initializable {
    public Tab userntries;
    public Text moveButtons;
    public Text display;
    public Text matchDataHolder1;
    public Text matchDataHolder2;
    public Text matchDataHolder3;
    @FXML Tab userDashboard;
    public StackPane tournamentContainer;
    public Text tournamentNameHolder;
    public Text tournamentDataHolder2;
    public Text tournamentDataHolder3;
    @FXML TextField tournamentInput;
    @FXML TextField tournamentID;
    public JFXButton renewButton;
    public JFXButton submissionButton;
    @FXML StackPane rootPane;
    @FXML AnchorPane rootAnchorPane;
    @FXML TabPane mainTabPane;
    @FXML HBox tournamentInfo;
    @FXML HBox matchInfo;
    @FXML TextField matchNumberInput;
    @FXML StackPane matchContainer;
    @FXML Text matchName;
    @FXML Text matchInfoText;
    @FXML HBox dataHolderContainer;
    @FXML JFXButton Search;
    @FXML Text tournamentType;
    @FXML Text tournamentDate;
    @FXML Text tournamentName;
    @FXML
    public Text issueDateHolder;
    @FXML
    public Text numberDaysHolder;
    @FXML
    private ListView<Tournament> listView;
    private ObservableList<Tournament> tournamentStatsObservableList;

    public MainController() {
    }

    @FXML
    JFXDrawer drawer;
    @FXML
    JFXHamburger hamburger;

    public TableView<Tournament> tableViewT;

    public TableColumn<Tournament, String> id_Col;

    public TableColumn<Tournament, String> tourney_nameCol;

    public TableColumn<Tournament, String> tourney_dateCol;

    public TableColumn<Tournament, String> winner_nameCol;

    public TableColumn<Tournament, String> loser_nameCol;

    public TableColumn<Tournament, String> scoreCol;

    public Button editButton;

    public Button deleteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        JFXButton jb1 = new JFXButton("Add1");
        JFXButton jb2 = new JFXButton("Add2");
        JFXButton jb3 = new JFXButton("Add3");
        TextField tempText = new TextField("Search Tournaments");

        VBox toolbar = new VBox(20);

        toolbar.getChildren().addAll(jb1, jb2, jb3, tempText);

        toolbar.getStyleClass().add("jfx-drawer-side-pane");

        drawer.setSidePane(toolbar);

        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);

        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            drawer.toggle();
        });

        drawer.setOnDrawerOpening((event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            drawer.toFront();
        });

        drawer.setOnDrawerClosed((event) -> {
            drawer.toBack();
            task.setRate(task.getRate() * -1);
            task.play();
        });

        mainTabPane.tabMinWidthProperty().bind(rootAnchorPane.widthProperty().divide(mainTabPane.getTabs().size()).subtract(15));
    }



    @FXML Tab userEntries;

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void handleMenuClose(ActionEvent event) {
        getStage().close();
    }

    @FXML JFXButton addTournament;

    public String result;

    public void setResult(String res){
        this.result = res;
    }

    private static List<String> resList;

    public List<String> getResult(){
        return resList;
    }

    @FXML
    public void loadTournamentData(ActionEvent actionEvent) throws SQLException, IOException {

        List<Tournament> resultList = new ArrayList<>();
        List<String> resList = Collections.singletonList(tournamentInput.getText());
        DatabaseConnection dbConnection = new DatabaseConnection();
        String s = tournamentInput.getText();
        ResultSet rs = dbConnection.execQuery(s);

        try {
            while (rs.next()) {
                    resultList.add(new Tournament(
                            rs.getString("tourney_id"),
                            rs.getString("tourney_name"),
                            rs.getString("tourney_date"),
                            rs.getString("surface"),
                            rs.getString("draw_size"),
                            rs.getString("tourney_level")
                    ));
                }
        } catch (SQLException ex) {

        }
        System.out.println("List: ");
        Node query = (Node) actionEvent.getSource();
        System.out.println(query);
        resultList.stream().filter(e->e.getTourney_name().equals(query)).forEach(System.out::println);

       TournamentListController tournamentController = new TournamentListController();

        TournamentListLoader tournamentListLoader = new TournamentListLoader();
    }

}








