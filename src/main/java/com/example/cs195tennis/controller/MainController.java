package com.example.cs195tennis.controller;
import com.example.cs195tennis.TournamentListLoader;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.TournamentStats;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXHamburger;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {

    @FXML public Text issueDateHolder;
    @FXML public Text numberDaysHolder;
    @FXML private ListView<TournamentStats> listView;
    private ObservableList<TournamentStats> tournamentStatsObservableList;

    public MainController() {}

    @FXML JFXDrawer drawer;
    @FXML JFXHamburger hamburger;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXButton jb1 = new JFXButton("Add1");
        JFXButton jb2 = new JFXButton("Add2");
        JFXButton jb3 = new JFXButton("Add3");
        JFXButton jb4 = new JFXButton("Remove");

        VBox toolbar = new VBox(20);

        toolbar.getChildren().addAll(jb1, jb2, jb3, jb4);

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

    @FXML
    public void loadTournamentData(ActionEvent actionEvent) throws SQLException, IOException {
        String s = tournamentInput.getText();
        DatabaseConnection database = new DatabaseConnection();
        ResultSet rs = database.execQuery(s);
        try {
            while (rs.next()) {
                String resultId = rs.getString("Source.name");
                String tourneyName = rs.getString("tourney_name");
                String resultRound = rs.getString("ROUND");
                String resultLoc = rs.getString("winner_ioc");
                String resultLoserName = rs.getString("loser_name");
                String resultWinnerName = rs.getString("winner_name");
                tournamentID.setText(tourneyName);
                tournamentDate.setText(resultRound);
                tournamentType.setText(resultLoc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TournamentListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadMatchInfo(ActionEvent actionEvent) {
    }




    public void handleMenuAddMatch(ActionEvent actionEvent) throws IOException {
    }

    public void handleMenuViewTournement(ActionEvent actionEvent) throws IOException {
    }

    public void handleMenuViewMatches(ActionEvent actionEvent) throws IOException {
    }

    public void handleMenuViewPlayers(ActionEvent actionEvent) throws IOException {
    }

    public void handleMenuFullScreen(ActionEvent actionEvent) {
    }

    public void handleMenuUi(ActionEvent actionEvent) throws IOException {
    }

    public void handleMenuAddSettings(ActionEvent actionEvent) throws IOException {
    }

    public void handleButtons(KeyEvent keyEvent) {
    }

    public void searchBuilder(ActionEvent actionEvent) {
    }

    public void handleAboutMenu(ActionEvent actionEvent) {
    }

    public void handleMenuAddTourneyment(ActionEvent actionEvent) {
    }

    public Tab userntries;
    public Text changeFont;
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

    public void handleMenuTournament(ActionEvent actionEvent) {
    }

    public void handleMenuMatchData(ActionEvent actionEvent) {
    }

    public void handleMenuViewTournament(ActionEvent actionEvent) {
    }

    public void handleMenuViewMemberList(ActionEvent actionEvent) {
    }

    public void handleMenuOverdueNotification(ActionEvent actionEvent) {
    }

    public void handleIssuedList(ActionEvent actionEvent) {
    }

    public void handleMenuViewBook(ActionEvent actionEvent) {
    }

    public void handleMenuAddMember(ActionEvent actionEvent) {
    }

    public void handleMenuSettings(ActionEvent actionEvent) {
    }

    public void handleMenuAddBook(ActionEvent actionEvent) {
    }
}








