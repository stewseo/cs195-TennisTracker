package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.DataModel.DataHandeler;
import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.database.Database;
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


    private void grandSlamFilters() {
        StringConverter<Tournament> converter =
                FunctionalStringConverter.to(e->(e == null) ? "" : e.getTourneyName());

        System.out.println(converter);

        Function<String, Predicate<Tournament>> filterFunction = s -> e -> {
            return StringUtils.containsIgnoreCase(converter.toString(e), s);
        };
        System.out.println(filterFunction);

        ObservableList<Tournament> observableGrandSlams = FXCollections.observableArrayList();

        observableGrandSlams = TournamentDao.populateGrandSlam();

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
        MFXTableColumn<Tournament> c3=new MFXTableColumn<>("Player 1",true,Comparator.comparing(Tournament::getPlayer1));
        MFXTableColumn<Tournament> c4=new MFXTableColumn<>("Player 2",true,Comparator.comparing(Tournament::getPlayer2));
        MFXTableColumn<Tournament> c5=new MFXTableColumn<>("Tournament Level",true,Comparator.comparing(Tournament::getTourney_level));
        MFXTableColumn<Tournament> c6=new MFXTableColumn<>("Surface",true,Comparator.comparing(Tournament::getSurface));

        System.out.println();

        c1.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getTourneyName));
        c2.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getTourneyDate));
        c3.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getPlayer1));
        c4.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getPlayer2));
        c5.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getTourney_level));
        c6.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getSurface)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        c5.setAlignment(Pos.CENTER_RIGHT);

        grandSlamTable.getTableColumns().addAll(c1, c2, c3, c4, c5, c6);
        grandSlamTable.getFilters().addAll(
                new StringFilter<>("Tournament Name",Tournament::getTourneyName),
            new StringFilter<>("Year",Tournament::getTourneyDate),
            new StringFilter<>("Player 1",Tournament::getPlayer1),
            new StringFilter<>("Player 2",Tournament::getPlayer2),
            new StringFilter<>("Tournament Level",Tournament::getTourney_level),
        new StringFilter<>("Surface",Tournament::getSurface));

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