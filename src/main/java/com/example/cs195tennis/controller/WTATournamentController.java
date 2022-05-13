package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.WtaMatchDao;
import com.example.cs195tennis.model.*;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.DateTimeUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import javafx.beans.property.SimpleObjectProperty;
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
import java.util.stream.Stream;

public class WtaTournamentController implements Initializable {

    @FXML public MFXTableView<WtaMatch> wtaTouranmentTable;

    @FXML public MFXButton searchButton;

    @FXML public MFXFilterComboBox<WtaMatch>  filterCombo;

    @FXML public MFXFilterComboBox<WtaMatch>  custFilterCombo;
    public Label validateWtaTournamentLavel;
    public MFXTextField wtaMatchText;

    @FXML MFXTableView<WtaMatch> matchTable;

    @FXML public MFXDatePicker custDatePicker;

    @FXML private MFXTextField textField;


    ObservableList<WtaMatch> tournamentObservable = FXCollections.observableArrayList();


    @Override public void initialize(URL location, ResourceBundle resources) {

        custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);

        custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));


        StringConverter<WtaMatch> converter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getTourney_name() + "" + e.getTourney_id());

        Function<String, Predicate<WtaMatch>> filterPlayer = s -> e -> StringUtils.containsIgnoreCase(converter.toString(e), s);

        try {
            System.out.println(WtaMatchDao.getTournamentNames().size());

            tournamentObservable = WtaMatchDao.getTournamentNames();


            filterCombo.setItems(tournamentObservable);
            filterCombo.setConverter(converter);
            filterCombo.setFilterFunction(filterPlayer);

            custFilterCombo.setItems(WtaMatchDao.getTournamentNames());
            custFilterCombo.setConverter(converter);
            custFilterCombo.setFilterFunction(filterPlayer);
            custFilterCombo.setResetOnPopupHidden(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() throws SQLException {

        MFXTableColumn<WtaMatch> tourneyNameColumn = new MFXTableColumn<>("tourney_name", true, Comparator.comparing(WtaMatch::getTourney_name));
        MFXTableColumn<WtaMatch> tourneyDateColumn = new MFXTableColumn<>("tourney_date", true, Comparator.comparing(WtaMatch::getTourney_date));
        MFXTableColumn<WtaMatch> loserNameColumn = new MFXTableColumn<>("winner_name", true, Comparator.comparing(WtaMatch::getWinnerName));


        tourneyNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(WtaMatch::getTourney_name));
        tourneyDateColumn.setRowCellFactory(match -> new MFXTableRowCell<>(WtaMatch::getTourney_date));
        loserNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(WtaMatch::getLoserName)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        tourneyDateColumn.setAlignment(Pos.CENTER_RIGHT);

        matchTable.getTableColumns().addAll(tourneyNameColumn, tourneyDateColumn, loserNameColumn);
        matchTable.getFilters().addAll(
                new StringFilter<>("tourney_name", WtaMatch::getTourney_name),
                new StringFilter<>("tourney_date", WtaMatch::getTourney_date),
                new StringFilter<>("winner_name", WtaMatch::getWinnerName)
        );

        tournamentObservable = WtaMatchDao.getTournamentNames();

        matchTable.setItems(tournamentObservable);
    }

    public void handleTextEntry(ActionEvent event) {
    }
}


