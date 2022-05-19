package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.AtpPlayerDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.model.AtpPlayer;
import com.example.cs195tennis.model.PlayerRanking;
import com.example.cs195tennis.model.Tournament;
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

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class PlayerRankingController implements Initializable {

    @FXML public MFXDatePicker custDatePicker;
    @FXML public MFXFilterComboBox<PlayerRanking> filterCombo;
    @FXML public MFXFilterComboBox<PlayerRanking> custFilterCombo;

    @FXML public Label validateAtpPlayer;

    @FXML public MFXTableView<AtpPlayer> atpPlayerTable;
    public MFXTextField textAtpPlayer;


    private void grandSlamFilters() {
        StringConverter<PlayerRanking> converter =
                FunctionalStringConverter.to(e->(e==null) ? "" : e.getFirstName() + " " + e.getLastName());

        Function<String, Predicate<PlayerRanking>> filterFunction =
                s ->
                        e -> {
                            return StringUtils.containsIgnoreCase(converter.toString(e), s);
                        };

        ObservableList<AtpPlayer> observableAtpPlayer = FXCollections.observableArrayList();
        ObservableList<PlayerRanking> observablePlayerRank = FXCollections.observableArrayList();

        observablePlayerRank = PlayerDao.oberservablePlayerRanking();

        observableAtpPlayer = PlayerDao.observableAtpPlayer();

        filterCombo.setItems(observablePlayerRank);
        filterCombo.setConverter(converter);
        filterCombo.setFilterFunction(filterFunction);

        custFilterCombo.setItems(observablePlayerRank);
        custFilterCombo.setConverter(converter);
        custFilterCombo.setFilterFunction(filterFunction);
        custFilterCombo.setResetOnPopupHidden(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
        custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));

        grandSlamFilters();
        try {
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void setupTable() throws SQLException {

        MFXTableColumn<AtpPlayer> atpPlayerNameColumn = new MFXTableColumn<>("fullName", true, Comparator.comparing(AtpPlayer::getFullName));
        MFXTableColumn<AtpPlayer> atpPlayerDominantHandCol = new MFXTableColumn<>("hand", true, Comparator.comparing(AtpPlayer::getHand));
        MFXTableColumn<AtpPlayer> atpPlayerHeightCol = new MFXTableColumn<>("height", true, Comparator.comparing(AtpPlayer::getHeight));
        MFXTableColumn<AtpPlayer> atpPlayerDobCol = new MFXTableColumn<>("Country", true, Comparator.comparing(AtpPlayer::getCountry));

        atpPlayerNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(AtpPlayer::getFullName));
        atpPlayerDominantHandCol.setRowCellFactory(match -> new MFXTableRowCell<>(AtpPlayer::getHand));
        atpPlayerHeightCol.setRowCellFactory(match -> new MFXTableRowCell<>(AtpPlayer::getHeight));
        atpPlayerDobCol.setRowCellFactory(match -> new MFXTableRowCell<>(AtpPlayer::getCountry)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        atpPlayerNameColumn.setAlignment(Pos.CENTER_RIGHT);

        atpPlayerTable.getTableColumns().addAll(atpPlayerNameColumn,atpPlayerDominantHandCol,atpPlayerHeightCol,atpPlayerDobCol);
        atpPlayerTable.getFilters().addAll(
                new StringFilter<>("fullName", AtpPlayer::getFullName),
                new StringFilter<>("hand", AtpPlayer::getHand),
                new StringFilter<>("height", AtpPlayer::getHeight),
                new StringFilter<>("Country", AtpPlayer::getCountry)
        );

        ObservableList<AtpPlayer> observableAtpPlayer = FXCollections.observableArrayList();

        observableAtpPlayer = PlayerDao.observableAtpPlayer();

        atpPlayerTable.setItems(observableAtpPlayer);
    }

    public void handleAtpPlayer(ActionEvent event) {
    }
}