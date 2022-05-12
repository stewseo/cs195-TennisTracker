package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.AtpPlayerDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.Dao.WtaDao;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Player;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.DateTimeUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

public class AtpPlayerController implements Initializable {

    @FXML public MFXTableView<Player> table;

    @FXML public MFXButton searchButton;

    @FXML private MFXComboBox<String> tournamentFilterBox;

    @FXML private MFXComboBox<Match> matchStatFilterBox;

    @FXML public MFXDatePicker custDatePicker;

    @FXML private MFXFilterComboBox<Player> filterPlayerCategories;

    @FXML private MFXFilterComboBox<Player> filteredPlayers;

    @FXML private MFXTextField textField;

    @FXML private Label validationLabel;

    static ObservableList<Match> matchObservable;
    static ObservableList<Player> playerObservable;
    static ObservableList<Player.Ranking> playerRankObservable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initDate();

//        populateDb();

        try {
            initFilters();
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void initDate() {
        custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
        custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));
    }

    private void populateDb() {
    }

    private void initFilters() throws SQLException {

        playerObservable = AtpPlayerDao.getAtpPlayers();

        validationLabel.setText("Validation label");

        StringConverter<Player> playerConverter =
                FunctionalStringConverter.to(e ->
                (e == null) ? "" : e.getFirstName() +" "+e.getLastName());

        StringConverter<Player> rankConverter =
                FunctionalStringConverter.to(e ->
                        (e == null) ? "" : e.getRanking() +" "+ e.getRanking());

        Function<String, Predicate<Player>> filterPlayer = s -> e -> {
            System.out.println(e);
            return StringUtils.containsIgnoreCase(playerConverter.toString(e), s);
        };


        filteredPlayers.setItems(playerObservable);

        playerObservable.forEach(System.out::println);
        filteredPlayers.setConverter(playerConverter);
        filteredPlayers.setFilterFunction(filterPlayer);

        filterPlayerCategories.setItems(playerObservable);
        filterPlayerCategories.setConverter(playerConverter);
        filterPlayerCategories.setFilterFunction(filterPlayer);
        filterPlayerCategories.setResetOnPopupHidden(false);
    }

    private void setupTable() throws SQLException {
        MFXTableColumn<Player> playerNameColumn = new MFXTableColumn<>("fulName", true, Comparator.comparing(Player::getFullName));
        MFXTableColumn<Player> playerRankColumn = new MFXTableColumn<>("rank", true, Comparator.comparing(Player::getRank));
        MFXTableColumn<Player> playerLocationColumn = new MFXTableColumn<>("player_ioc", true, Comparator.comparing(Player::getIoc));
        MFXTableColumn<Player> playerHeightColumn = new MFXTableColumn<>("height", true, Comparator.comparing(Player::getHeight));
        MFXTableColumn<Player> playerDominantHand = new MFXTableColumn<>("hand", true, Comparator.comparing(Player::getHand));

        playerNameColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getFullName));
        playerRankColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getRank));
        playerLocationColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getIoc));
        playerLocationColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getHeight));
        playerDominantHand.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getHand)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        playerDominantHand.setAlignment(Pos.CENTER_RIGHT);

        table.getTableColumns().addAll(playerNameColumn, playerRankColumn, playerLocationColumn, playerHeightColumn, playerDominantHand);
        table.getFilters().addAll(
                new StringFilter<>("player_name", Player::getFullName),
                new StringFilter<>("rank", Player::getRank),
                new StringFilter<>("player_ioc", Player::getIoc),
                new StringFilter<>("height", Player::getIoc),
                new StringFilter<>("hand", Player::getHand)
        );
        table.setItems(AtpPlayerDao.getAtpPlayers());
    }

    public void handleFileInput(ActionEvent event) {
        String input = textField.getText();

    }

    public void handleSearchPlayer1(ActionEvent event) throws SQLException {
        String date = custDatePicker.getText();
        String player = filteredPlayers.getText();
        String tournament = filterPlayerCategories.getText();
        String filterInput = tournamentFilterBox.getText();

        System.out.println(filterInput + " " + tournament + " " + player + " " + date);

        String[] temp = new String[]{};

        PlayerDao.query(player, temp);
    }

    public void handleSearchPlayer(ActionEvent event) {
    }

    public void handleCsvPath(ActionEvent event) throws SQLException, FileNotFoundException {
        String[] test = textField.getText().split(", ");

        System.out.println(Arrays.toString(test));

        DataHandeler.buildPath(test[0],test[1]);

    }
}


