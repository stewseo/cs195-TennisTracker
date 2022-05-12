package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.MatchDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.Dao.WtaDao;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.model.*;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.DateTimeUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class WTATournamentController implements Initializable {

    @FXML
    public MFXTableView<Match> wtaTouranmentTable;

    @FXML
    public MFXButton searchButton;

    @FXML
    private MFXTableView<Match> table;

    @FXML
    private MFXComboBox<String> tournamentFilterBox;

    @FXML
    private MFXComboBox<Match> matchStatFilterBox;

    @FXML
    public MFXDatePicker custDatePicker;

    @FXML
    private MFXFilterComboBox<Match> filterTournaments;

    @FXML private MFXFilterComboBox<Player> filteredPlayers;


    @FXML
    private MFXTextField textField;

    @FXML
    private Label validationLabel;

    static ObservableList<Match> tournamentObservable = FXCollections.observableArrayList();
    ObservableList<Player.Ranking> playerRankObservable = FXCollections.observableArrayList();
    ObservableList<Player> playerObservable = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
        custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));

        try {

            playerRankObservable.addAll(PlayerDao.getPlayerRanks());

            playerObservable = PlayerDao.readWtaPlayerToObservable();

            tournamentObservable = WtaDao.readWtaMatchesToObservable();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        validationLabel.setText("Validation label");

        StringConverter<Match> converter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getTourney_name());

        StringConverter<Player> playerConverter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getFullName());

        Function<String, Predicate<Match>> filterFunction = s -> e -> {return StringUtils.containsIgnoreCase(converter.toString(e), s);};

        Function<String, Predicate<Player>> filterPlayer = s -> e -> StringUtils.containsIgnoreCase(playerConverter.toString(e), s);;

        filteredPlayers.setItems(playerObservable);
        filteredPlayers.setConverter(playerConverter);
        filteredPlayers.setFilterFunction(filterPlayer);

        try {
            filterTournaments.setItems(MatchDao.getTournamentNames());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        filterTournaments.setConverter(converter);
        filterTournaments.setFilterFunction(filterFunction);
        filterTournaments.setResetOnPopupHidden(false);

        setupTable();
    }

    private void setupTable() {
        MFXTableColumn<Match> tourneyNameColumn = new MFXTableColumn<>("tourney_name", true, Comparator.comparing(Match::getTourney_name));
        MFXTableColumn<Match> tourneyDateColumn = new MFXTableColumn<>("tourney_date", true, Comparator.comparing(Match::getTourney_date));
        MFXTableColumn<Match> winnerNameColumn = new MFXTableColumn<>("loser_name", true, Comparator.comparing(Match::getWinner_name));
        MFXTableColumn<Match> loserNameColumn = new MFXTableColumn<>("score", true, Comparator.comparing(Match::getLoser_name));
        MFXTableColumn<Match> roundColumn = new MFXTableColumn<>("round", true, Comparator.comparing(Match::getRound));


        tourneyNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getTourney_name));
        tourneyDateColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getTourney_date));
        winnerNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getWinner_name));
        loserNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getLoser_name));
        loserNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getScore));
        roundColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getRound)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        tourneyDateColumn.setAlignment(Pos.CENTER_RIGHT);

        table.getTableColumns().addAll(tourneyNameColumn, tourneyDateColumn, winnerNameColumn, loserNameColumn, roundColumn);
        table.getFilters().addAll(
                new StringFilter<>("tourney_name", Match::getTourney_name),
                new StringFilter<>("tourney_date", Match::getTourney_date),
                new StringFilter<>("winner_name", Match::getWinner_name),
                new StringFilter<>("loser_name", Match::getLoser_name),
                new StringFilter<>("score", Match::getScore),
                new StringFilter<>("round", Match::getRound)
        );

        table.setItems(tournamentObservable);
    }

    public void handleSearchPlayer1(ActionEvent event) throws SQLException {
        String date = custDatePicker.getText();
        String player = filteredPlayers.getText();
        String tournament = filterTournaments.getText();
        String filterInput = tournamentFilterBox.getText();

        String[] temp = new String[]{};

        PlayerDao.query(player, temp);
    }
}


