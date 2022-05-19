package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.AtpTourDao;
import com.example.cs195tennis.model.Tournament;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.MFXTableView;
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

import static javax.swing.text.StyleConstants.setAlignment;

public class ATPTourController implements Initializable {

    @FXML public MFXButton searchButton;

    @FXML public MFXFilterComboBox<Tournament> atpFilterTournament;

    @FXML public MFXFilterComboBox<Tournament> atpFilterTournamentStats;

    @FXML public Label atpValidate;

    @FXML public MFXTextField atpTextFields;

    @FXML MFXTableView<Tournament> atpTournamentViewTable;

    @FXML public MFXDatePicker custDatePicker;

    static ObservableList<Tournament> loadGrandSlams;
    static ObservableList<Tournament> loadRecentGrandSlamChampions;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Objects.requireNonNull(AtpTourDao.observableTournaments);

        System.out.println(" check database and models: " + AtpTourDao.observableTournaments);

        custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
        custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));


        StringConverter<Tournament> converter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getTourney_name());

        Function<String, Predicate<Tournament>> filterFunction = s -> e -> {
            return StringUtils.containsIgnoreCase(converter.toString(e), s);
        };

        loadGrandSlams = FXCollections.observableArrayList(AtpTourDao.observableTournaments);

        atpFilterTournamentStats.setItems(loadGrandSlams);
        atpFilterTournamentStats.setConverter(converter);
        atpFilterTournamentStats.setFilterFunction(filterFunction);

        loadGrandSlams = null;

        loadRecentGrandSlamChampions = FXCollections.observableArrayList(AtpTourDao.observableTournaments);

        atpFilterTournament.setItems(loadGrandSlams);
        atpFilterTournament.setConverter(converter);
        atpFilterTournament.setFilterFunction(filterFunction);
        atpFilterTournament.setResetOnPopupHidden(false);

        loadRecentGrandSlamChampions = null;

        try {
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void setupTable() throws SQLException {
        MFXTableColumn<Tournament> tourneyNameColumn = new MFXTableColumn<>("tourney_name", true, Comparator.comparing(Tournament::getTourney_name));
        MFXTableColumn<Tournament> tourneyDateColumn = new MFXTableColumn<>("tourney_date", true, Comparator.comparing(Tournament::getTourney_date));

        tourneyNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getTourney_name));
        tourneyDateColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getTourney_date)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        MFXTableColumn<Tournament> loserNameColumn;
        tourneyDateColumn.setAlignment(Pos.CENTER_RIGHT);

        atpTournamentViewTable.getTableColumns().addAll(tourneyNameColumn, tourneyDateColumn);
        Object AtpMatch;
        atpTournamentViewTable.getFilters().addAll(
                new StringFilter<>("tourney_name", Tournament::getTourney_name),
                new StringFilter<>("tourney_date", Tournament::getTourney_date)
        );

        System.out.println(Objects.nonNull(loadGrandSlams.addAll(AtpTourDao.observableTournaments)));

		atpTournamentViewTable.setItems(loadGrandSlams);
    }

    public void Tournament(ActionEvent event) {
    }
}