package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.DataModel.TournamentRecord;
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
import org.jooq.TableField;

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


    @Override public void initialize(URL location, ResourceBundle resources) {


        custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);

        custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));

        try {
            tournamentObservable = TournamentDao.getTournamentObservable();
            setupTable();

        } catch (SQLException e) {System.out.println();
            e.printStackTrace();
        }
//
        StringConverter<Tournament> converter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getTourney_name());

        Function<String, Predicate<Tournament>> filterTournaments = s -> e -> StringUtils.containsIgnoreCase(converter.toString(e), s);

        filterCombo.setItems(tournamentObservable);

        filterCombo.setConverter(converter);
        filterCombo.setFilterFunction(filterTournaments);

        custFilterCombo.setItems(tournamentObservable);

        custFilterCombo.setConverter(converter);
        custFilterCombo.setFilterFunction(filterTournaments);
        custFilterCombo.setResetOnPopupHidden(false);
    }

    @SuppressWarnings("unchecked")
    private void setupTable() throws SQLException {

        MFXTableColumn<Tournament> c1 = new MFXTableColumn<>("Tourney_name", true, Comparator.comparing(Tournament::getTourney_name));
        MFXTableColumn<Tournament> c2 = new MFXTableColumn<>("Date", true, Comparator.comparing(Tournament::getTourney_date));
        MFXTableColumn<Tournament> c3 = new MFXTableColumn<>("Winner", true, Comparator.comparing(Tournament::getWinnerFullName));
        MFXTableColumn<Tournament> c4 = new MFXTableColumn<>("Loser", true, Comparator.comparing(Tournament::getLoserFullName));
        MFXTableColumn<Tournament> c5 = new MFXTableColumn<>("Level", true, Comparator.comparing(Tournament::getTourney_level));
        MFXTableColumn<Tournament> c6 = new MFXTableColumn<>("Surface", true, Comparator.comparing(Tournament::getSurface));
        MFXTableColumn<Tournament> c7 = new MFXTableColumn<>("Draw Size", true, Comparator.comparing(Tournament::getDraw_size));
            System.out.println();

        c1.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getTourney_name));
        c2.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getTourney_date));
        c3.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getWinnerFullName)
        c4.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getLoserFullName));
        c5.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getTourney_level));
        c6.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getSurface));
        c7.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getDraw_size)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
            c2.setAlignment(Pos.CENTER_RIGHT);

        grandSlamTable.getTableColumns().addAll(c1, c2);
        grandSlamTable.getFilters().addAll(
                new StringFilter<>("GrandSlam.tourney_name", Tournament::getTourney_name));
                new StringFilter<>("GrandSlam.surface", Tournament::getTourney_date));
                new StringFilter<>("GrandSlam.Draw Size", Tournament::getWinnerFullName))
                new StringFilter<>("GrandSlam.level",Tournament::getLoserFullName),
                new StringFilter<>("GrandSlam.Winner",Tournament::getTourney_level),
                new StringFilter<>("GrandSlam.Loser",Tournament::getSurface));

        grandSlamTable.setItems(tournamentObservable);
    public void handleTextEntry(ActionEvent event))};



}

