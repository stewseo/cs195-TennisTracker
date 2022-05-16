package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.model.Tournament;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.DateTimeUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;


public class GrandSlamController implements Initializable {

    @FXML public MFXButton searchButton;

    @FXML public MFXFilterComboBox<Tournament> filterCombo;

    @FXML public MFXFilterComboBox<Tournament>  custFilterCombo;

    @FXML public Label validateWtaTournamentLavel;

    @FXML public MFXDatePicker custDatePicker;

    @FXML public MFXTableView<Tournament> grandSlamTable;

    @FXML private MFXTextField textField;

    ObservableList<Tournament> tournamentObservable = null;

    @Override public void initialize(URL location, ResourceBundle resources) {

        custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
        custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));

        try {
            tournamentObservable = TournamentDao.grandSlamTournamentsObservable();

            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        StringConverter<Tournament> dateC = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getTourney_date())   ;
        StringConverter<Tournament> converter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getTourney_name());
        Function<String, Predicate<Tournament>> filterTournaments = s -> e -> StringUtils.containsIgnoreCase(converter.toString(e), s);

        ObservableList<Tournament> grandSlamTournaments;

        filterCombo.setItems(tournamentObservable);
        filterCombo.setConverter(converter);
        filterCombo.setFilterFunction(filterTournaments);

        ObservableList<Tournament> grandSlamChampions;

        custFilterCombo.setItems(tournamentObservable);
        custFilterCombo.setConverter(converter);
        custFilterCombo.setFilterFunction(filterTournaments);
        custFilterCombo.setResetOnPopupHidden(false);

    }

    @SuppressWarnings("unchecked")
    private void setupTable() throws SQLException {

        MFXTableColumn<Tournament> c1 = new MFXTableColumn<>("Tourney_name", true, Comparator.comparing(Tournament::getTourney_name));
        System.out.println(c1);
        MFXTableColumn<Tournament> c2 = new MFXTableColumn<>("Date", true, Comparator.comparing(Tournament::getTourney_date));

        MFXTableColumn<Tournament> c3 = new MFXTableColumn<>("Winner", true, Comparator.comparing(Tournament::getWinnerFullName));

        MFXTableColumn<Tournament> c4 = new MFXTableColumn<>("Loser", true, Comparator.comparing(Tournament::getLoserFullName));

        MFXTableColumn<Tournament> c5 = new MFXTableColumn<>("Level", true, Comparator.comparing(Tournament::getTourney_level));

        MFXTableColumn<Tournament> c6 = new MFXTableColumn<>("Surface", true, Comparator.comparing(Tournament::getSurface));

        MFXTableColumn<Tournament> c7 = new MFXTableColumn<>("Draw Size", true, Comparator.comparing(Tournament::getDraw_size));


        c1.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getTourney_name));
        c2.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getTourney_date));
        c3.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getWinnerFullName));
        c4.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getLoserFullName));
        c5.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getTourney_level));
        c6.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getSurface));
        c7.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getDraw_size)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        c4.setAlignment(Pos.CENTER_RIGHT);

        grandSlamTable.getTableColumns().addAll(c1, c2, c3, c4, c5, c6);
        grandSlamTable.getFilters().addAll(
                new StringFilter<>("tourney_name", Tournament::getTourney_name),
                new StringFilter<>("surface", Tournament::getTourney_date),
                new StringFilter<>("Draw Size", Tournament::getWinnerFullName),
                new StringFilter<>("level",Tournament::getLoserFullName),
                new StringFilter<>("Winner",Tournament::getTourney_level),
                new StringFilter<>("Loser",Tournament::getSurface),
                new StringFilter<>("Loser",Tournament::getDraw_size)
        );

        grandSlamTable.setItems(tournamentObservable);
        tournamentObservable.stream().close();
    }

    public void handleTextEntry(ActionEvent event) {}
}


