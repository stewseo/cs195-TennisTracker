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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class GrandSlamController implements Initializable {

    @FXML
    public MFXButton searchButton;

    @FXML public MFXTextField textField;

    @FXML
    public MFXFilterComboBox<Tournament> filterCombo;

    @FXML
    public MFXFilterComboBox<Tournament> custFilterCombo;

    @FXML
    public Label validateWtaTournamentLavel;

    @FXML
    public MFXDatePicker custDatePicker;

    @FXML
    public MFXTableView<Tournament> grandSlamTable;



    private void grandSlamFilters() {
        StringConverter<Tournament> converter =
                FunctionalStringConverter.to(e->(e==null) ? "" : e.getTourney_name());

        Function<String, Predicate<Tournament>> filterFunction =
                s ->
                        e -> {
            return StringUtils.containsIgnoreCase(converter.toString(e), s);
        };

        ObservableList<Tournament> observableGrandSlams = FXCollections.observableArrayList();

        observableGrandSlams = TournamentDao.populateGrandSlam();

        filterCombo.setItems(observableGrandSlams);
        filterCombo.setConverter(converter);
        filterCombo.setFilterFunction(filterFunction);

        custFilterCombo.setItems(observableGrandSlams);
        custFilterCombo.setConverter(converter);
        custFilterCombo.setFilterFunction(filterFunction);
        custFilterCombo.setResetOnPopupHidden(false);
    }



    private void setupTable() throws SQLException{

        MFXTableColumn<Tournament> c1=new MFXTableColumn<>("Tourney_name",true,Comparator.comparing(Tournament::getTourney_name));
        MFXTableColumn<Tournament> c2=new MFXTableColumn<>("tourney_date",true,Comparator.comparing(Tournament::getTourney_date));
        MFXTableColumn<Tournament> c3=new MFXTableColumn<>("Winner",true,Comparator.comparing(Tournament::getWinner));
        MFXTableColumn<Tournament> c4=new MFXTableColumn<>("Loser",true,Comparator.comparing(Tournament::getLoser));
        MFXTableColumn<Tournament> c5=new MFXTableColumn<>("Tourney_level",true,Comparator.comparing(Tournament::getTourney_level));
        MFXTableColumn<Tournament> c6=new MFXTableColumn<>("Surface",true,Comparator.comparing(Tournament::getSurface));

        System.out.println();

        c1.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getTourney_name));
        c2.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getTourney_date));
        c3.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getWinner));
        c4.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getLoser));
        c5.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getTourney_level));
        c6.setRowCellFactory(match->new MFXTableRowCell<>(Tournament::getSurface)

        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        c5.setAlignment(Pos.CENTER_RIGHT);

        grandSlamTable.getTableColumns().addAll(c1, c2, c3, c4, c5, c6);
        grandSlamTable.getFilters().addAll(
                new StringFilter<>("tourney_name",Tournament::getTourney_name));
            new StringFilter<>("tourney_date",Tournament::getTourney_date);
            new StringFilter<>("winner",Tournament::getWinner);
            new StringFilter<>("loser",Tournament::getLoser);
            new StringFilter<>("Tourney_level",Tournament::getTourney_level);
        new StringFilter<>("surface",Tournament::getSurface);

        ObservableList<Tournament> observableGrandSlams = FXCollections.observableArrayList();
        observableGrandSlams = TournamentDao.populateGrandSlam();
        grandSlamTable.setItems(observableGrandSlams);
        }

    public void handleTextEntry(ActionEvent event) {
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