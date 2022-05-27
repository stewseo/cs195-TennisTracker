package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.DataModel.DataHandeler;
import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.Dao.WtaPlayerDao;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Tournament;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.DateTimeUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import org.jooq.Converter;
import org.jooq.TableField;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class GrandSlamController implements Initializable {

    @FXML public MFXButton searchButton;

    @FXML public MFXTextField textField;

    @FXML public MFXFilterComboBox<Tournament> filterCombo;

    @FXML public MFXFilterComboBox<Tournament> custFilterCombo;

    @FXML public Label validateWtaTournamentLavel;

    @FXML public MFXDatePicker custDatePicker;

    @FXML public MFXTableView<Tournament> grandSlamTable;

    Map<Integer, List<String>> pointByPoint;

    private void grandSlamFilters() {
        StringConverter<Tournament> converter =
                FunctionalStringConverter.to(e->(e == null) ? "" : e.getTourneyName());
        //filters everything inside of the searchbpx.
        Function<String, Predicate<Tournament>> filterFunction = s -> e -> {
            return StringUtils.containsIgnoreCase(converter.toString(e), s);
        };
        System.out.println(filterFunction);

        //Tournament Dao loads
        ObservableList<Tournament> observableGrandSlams = FXCollections.observableArrayList();
        TournamentDao tournamentDao = new TournamentDao();

        observableGrandSlams = tournamentDao.populateGrandSlam();

        if(observableGrandSlams == null) {
            throw new NullPointerException(" null line 65 while trying to load Grand Slams ");
        }

        filterCombo.setItems(observableGrandSlams);
        filterCombo.setConverter(converter);
        filterCombo.setFilterFunction(filterFunction);

        custFilterCombo.setItems(observableGrandSlams);
        custFilterCombo.setConverter(converter);
        custFilterCombo.setFilterFunction(filterFunction);
        custFilterCombo.setResetOnPopupHidden(false);
    }
    private void setupTable() throws SQLException{
        MFXTableColumn<Tournament> c1=new MFXTableColumn<>("Tournament Name",true,Comparator.comparing(Tournament::getTourneyName));
        MFXTableColumn<Tournament> c2=new MFXTableColumn<>("Year",true,Comparator.comparing(Tournament::getTourneyDate));
        MFXTableColumn<Tournament> c3=new MFXTableColumn<>("Match Num",true,Comparator.comparing(Tournament::getMatchNumber));
        MFXTableColumn<Tournament> c4=new MFXTableColumn<>("Round",true,Comparator.comparing(Tournament::getRound));
        MFXTableColumn<Tournament> c5=new MFXTableColumn<>("Player 1",true,Comparator.comparing(Tournament::getPlayer1));
        MFXTableColumn<Tournament> c6=new MFXTableColumn<>("Player 2",true,Comparator.comparing(Tournament::getPlayer2));
        MFXTableColumn<Tournament> c7=new MFXTableColumn<>("Court Name",true,Comparator.comparing(Tournament::getCourtName));
        MFXTableColumn<Tournament> c8=new MFXTableColumn<>("Event Name",true,Comparator.comparing(e-> e.getMatch().getEventName()));
        MFXTableColumn<Tournament> c9=new MFXTableColumn<>("Winner",true,Comparator.comparing(e-> e.getMatch().getWinner()));

        System.out.println();

        c1.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getTourneyName));
        c2.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getTourneyDate));
        c3.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getMatchNumber));
        c4.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getRound));
        c5.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getPlayer1));
        c6.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getPlayer2));
        c7.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getCourtName));
        c8.setRowCellFactory(match->new MFXTableRowCell<>(e-> e.getMatch().getEventName()));
        c9.setRowCellFactory(match->new MFXTableRowCell<>(e-> e.getMatch().getWinner())
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        c9.setAlignment(Pos.CENTER_RIGHT);

        grandSlamTable.getTableColumns().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9);
        grandSlamTable.getFilters().addAll(
//                new StringFilter<>("Tournament Name",Tournament::getTourneyName),
            new StringFilter<>("Year",Tournament::getTourneyDate),
            new StringFilter<>("Match Num",Tournament::getMatchNumber),
                new StringFilter<>("Round",Tournament::getRound),
                new StringFilter<>("Player 1",Tournament::getPlayer1),
                new StringFilter<>("Player 2",Tournament::getPlayer2),
                new StringFilter<>("Court Name",Tournament::getCourtName),
                new StringFilter<>("Event Name",e-> e.getMatch().getEventName()),
                new StringFilter<>("Winner", e-> {
                    return e.getMatch().getWinner().hashCode() == e.getPlayer1().hashCode() ?
                            e.getMatch().getPlayer2() : e.getPlayer2();
                }));

        ObservableList<Tournament> observableGrandSlams = FXCollections.observableArrayList();

        observableGrandSlams = TournamentDao.populateGrandSlam();

        grandSlamTable.setItems(observableGrandSlams);
    }
    public void handleTextEntry(ActionEvent event) {
        String dateInput = custDatePicker.getText();
        System.out.println(dateInput);
        String input = textField.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        grandSlamFilters();

        try {
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}