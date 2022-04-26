package com.example.cs195tennis.controller;
import com.example.cs195tennis.TournamentListLoader;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.model.TournamentStats;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
    private ListView<TournamentStats> listView;
    private ObservableList<TournamentStats> tournamentStatsObservableList;

    public MainController() {
    }

    @FXML
    JFXDrawer drawer;
    @FXML
    JFXHamburger hamburger;

    public TableView<TournamentStats> tableViewT;

    public TableColumn<TournamentStats, String> id_Col;

    public TableColumn<TournamentStats, String> tourney_nameCol;

    public TableColumn<TournamentStats, String> tourney_dateCol;

    public TableColumn<TournamentStats, String> winner_nameCol;

    public TableColumn<TournamentStats, String> loser_nameCol;

    public TableColumn<TournamentStats, String> scoreCol;

    public Button editButton;

    public Button deleteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        tableViewT.setItems(TournamentDao.getTournament());


//        tourney_nameCol.setCellValueFactory(new PropertyValueFactory<>("tourney_name"));
//        tourney_dateCol.setCellValueFactory(new PropertyValueFactory<>("tourney_date"));
//        winner_nameCol.setCellValueFactory(new PropertyValueFactory<>("winner_name"));
//        loser_nameCol.setCellValueFactory(new PropertyValueFactory<>("loser_name"));
//        id_Col.setCellValueFactory(new PropertyValueFactory<>("id"));

//        editButton.disableProperty().bind(Bindings.isEmpty(tableViewT.getSelectionModel().getSelectedItems()));
//        deleteButton.disableProperty().bind(Bindings.isEmpty(tableViewT.getSelectionModel().getSelectedItems()));

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

//    public void addTournament(ActionEvent event) {
//        Dialog<TournamentStats> addTournamentDialogue = createTournamentDialgue(null);
//        Optional<TournamentStats> result = addTournamentDialogue.showAndWait();
//        result.ifPresent(tournamentStats ->
//                TournamentDao.insertTournament(
//                        tournamentStats.getTourney_name(),
//                        tournamentStats.getTourney_date(),
//                        tournamentStats.getWinner_name()
//                ));
//        event.consume();
//    }

//    private Dialog<TournamentStats> createTournamentDialgue(TournamentStats tourney) {
//
//        Dialog<TournamentStats> dialog = new Dialog<>();
//        dialog.setTitle("Add Dialog");
//        if (tourney == null) {
//            dialog.setHeaderText("Add a new person to the database");
//        } else {
//            dialog.setHeaderText("Edit a database record");
//        }
//        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//        Stage dialogWindow = (Stage) dialog.getDialogPane().getScene().getWindow();
//        dialogWindow.getIcons().add(new Image(.class.getResource("img/EdenCodingIcon.png").toExternalForm()));
//
//        //create the form for the user to fill in
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20, 150, 10, 10));
//        TextField tourneyName = new TextField();
//        tourneyName.setPromptText("Enter Tournament Name");
//        TextField tourneyDate = new TextField();
//        tourneyDate.setPromptText("Enter Tournament Date");
//        TextField winningPlayer = new TextField();
//        winningPlayer.setPromptText("Winning Player");
//        grid.add(new Label("Enter Tournament Name:"), 0, 0);
//        grid.add(tourneyName, 1, 0);
//        grid.add(new Label("Enter Tournament Date:"), 0, 1);
//        grid.add(tourneyDate, 1, 1);
//        grid.add(new Label("Winning Player:"), 0, 2);
//        grid.add(winningPlayer, 1, 2);
//        dialog.getDialogPane().setContent(grid);
//
//
//        //disable the OK button if the fields haven't been filled in
//        dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
//                Bindings.createBooleanBinding(() -> tourneyName.getText().trim().isEmpty(), tourneyName.textProperty())
//                        .or(Bindings.createBooleanBinding(() -> tourneyDate.getText().trim().isEmpty(), tourneyDate.textProperty())
//                                .or(Bindings.createBooleanBinding(() -> winningPlayer.getText().trim().isEmpty(), winningPlayer.textProperty())
//                                )));
//
//        //ensure only numeric input (integers) in age text field
//        UnaryOperator<TextFormatter.Change> numberValidationFormatter = change -> {
//            if (change.getText().matches("\\d+") || change.getText().equals("")) {
//                return change; //if change is a number or if a deletion is being made
//            } else {
//                change.setText(""); //else make no change
//                change.setRange(    //don't remove any selected text either.
//                        change.getRangeStart(),
//                        change.getRangeStart()
//                );
//                return change;
//            }
//        };
//        winningPlayer.setTextFormatter(new TextFormatter<Object>(numberValidationFormatter));
//
//    //make sure the dialog returns a Person if it's available
//        dialog.setResultConverter(dialogButton -> {
//        if (dialogButton == ButtonType.OK) {
//            int id = -1;
//            if (tourney != null) id = tourney.getId();
//            return new TournamentStats(tourneyName.getText(), tourneyDate.getText(), winningPlayer.getText(), losingPlayer.getText());
//        }
//        return null;
//    });
//
//    //if a record is supplied, use it to fill in the fields automatically
//        if (tourney != null) {
//        tourneyName.setText(tourney.getTourney_name());
//        tourneyDate.setText(tourney.getTourney_date());
//        winningPlayer.setText(tourney.getWinner_name());
//    }
//
//        return dialog;
//}



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
//        List<TournamentStats> resultList = new ArrayList<>();
//        List<String> resList = Collections.singletonList(tournamentInput.getText());
//        DatabaseConnection dbConnection = new DatabaseConnection();
//        String s = tournamentInput.getText();
//        ResultSet rs = dbConnection.execQuery(s);
//
//        try {
//            while (rs.next()) {
//                    resultList.add(new TournamentStats(rs.getString("tourney_name"),
//                    rs.getString("tourney_date"),
//                    rs.getString("winner_name"),
//                    rs.getString("loser_name"),
//                    rs.getString("tourney_id")));
//                }
//        } catch (SQLException ex) {
//
//        }
//        System.out.println("List: ");
//        Node query = (Node) actionEvent.getSource();
//        System.out.println(query);
//        resultList.stream().filter(e->e.getTourney_name().equals(query)).forEach(System.out::println);
        //TODO: Create tables for player ranking, recent tournaments, head to head
        //TODO: Web Search Object
       TournamentListController tournamentController = new TournamentListController();

        TournamentListLoader tournamentListLoader = new TournamentListLoader();
    }


    public void loadMatchInfo(ActionEvent actionEvent) throws SQLException {
        Node matchNode = (Node) actionEvent.getSource();
        //TODO: use saved event nodes for scene switches or stage changes
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

    public void handleExitButtonClicked(ActionEvent event) {
    }

    public void deleteTournament(ActionEvent event) {
    }

    public void editTournament(ActionEvent event) {
    }
}








