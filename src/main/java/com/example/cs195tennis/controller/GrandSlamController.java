package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.DataModel.DataHandeler;
import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.Dao.WtaPlayerDao;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Tournament;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
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
import org.jooq.meta.Database;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class GrandSlamController implements Initializable {

    @FXML public MFXButton searchButton;

    @FXML public MFXTextField textField;

    @FXML public MFXFilterComboBox<Match> filterCombo;

    @FXML public MFXFilterComboBox<Match> custFilterCombo;

    @FXML public Label validateWtaTournamentLavel;

    @FXML public MFXDatePicker custDatePicker;

    @FXML public MFXTableView<Match> grandSlamTable;

    Map<Integer, List<String>> pointByPoint;

    private void grandSlamFilters() {
        StringConverter<Match> converter =
                FunctionalStringConverter.to(e->(e == null) ? "" : e.getTourneyName());

        Function<String, Predicate<Match>> filterFunction = s -> e -> {
            return StringUtils.containsIgnoreCase(converter.toString(e), s);
        };

        ObservableList<Match> observableGrandSlams = FXCollections.observableArrayList();
        TournamentDao tournamentDao = new TournamentDao();

        observableGrandSlams = tournamentDao.populateGrandSlam();

        assert equals(observableGrandSlams.size() > 0);

        filterCombo.setItems(observableGrandSlams);
        filterCombo.setConverter(converter);
        filterCombo.setFilterFunction(filterFunction);

        custFilterCombo.setItems(observableGrandSlams);
        custFilterCombo.setConverter(converter);
        custFilterCombo.setFilterFunction(filterFunction);
        custFilterCombo.setResetOnPopupHidden(false);

        observableGrandSlams = null;
    }
    private void setupTable() throws SQLException{
        MFXTableColumn<Match> c1=new MFXTableColumn<>("Tournament Name",true,Comparator.comparing(Match::getTourneyName));
        MFXTableColumn<Match> c2=new MFXTableColumn<>("Year",true,Comparator.comparing(Match::getTourneyDate));
        MFXTableColumn<Match> c3=new MFXTableColumn<>("Match Num",true,Comparator.comparing(Match::getMatchNumber));
        MFXTableColumn<Match> c4=new MFXTableColumn<>("Round",true,Comparator.comparing(Match::getRound));
        MFXTableColumn<Match> c5=new MFXTableColumn<>("Player 1",true,Comparator.comparing(Match::getPlayer1));
        MFXTableColumn<Match> c6=new MFXTableColumn<>("Player 2",true,Comparator.comparing(Match::getPlayer2));
        MFXTableColumn<Match> c7=new MFXTableColumn<>("Court Name",true,Comparator.comparing(Match::getCourtName));
        MFXTableColumn<Match> c8=new MFXTableColumn<>("Event Name",true,Comparator.comparing(e-> e.getEventName()));
        MFXTableColumn<Match> c9=new MFXTableColumn<>("Winner",true,Comparator.comparing(e-> e.getTournamentWinner()));

        System.out.println();

        c1.setRowCellFactory(match->new MFXTableRowCell<>(Match::getTourneyName));
        c2.setRowCellFactory(match->new MFXTableRowCell<>(Match::getTourneyDate));
        c3.setRowCellFactory(match->new MFXTableRowCell<>(Match::getMatchNumber));
        c4.setRowCellFactory(match->new MFXTableRowCell<>(Match::getRound));
        c5.setRowCellFactory(match->new MFXTableRowCell<>(Match::getPlayer1));
        c6.setRowCellFactory(match->new MFXTableRowCell<>(Match::getPlayer2));
        c7.setRowCellFactory(match->new MFXTableRowCell<>(Match::getCourtName));
        c8.setRowCellFactory(match->new MFXTableRowCell<>(e-> e.getEventName()));
        c9.setRowCellFactory(match->new MFXTableRowCell<>(e-> e.getTournamentWinner())
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        c9.setAlignment(Pos.CENTER_RIGHT);

        grandSlamTable.getTableColumns().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9);
        grandSlamTable.getFilters().addAll(
                new StringFilter<>("Tournament Name",Match::getTourneyName),
                new StringFilter<>("Year",Match::getTourneyDate),
                new StringFilter<>("Match Num",Match::getMatchNumber),
                new StringFilter<>("Round", e-> e.getRound()),
                new StringFilter<>("Player 1",Match::getPlayer1),
                new StringFilter<>("Player 2",Match::getPlayer2),
                new StringFilter<>("Court Name",Match::getCourtName),
                new StringFilter<>("Event Name",e-> e.getEventName()),
                new StringFilter<>("Winner", e-> e.getTournamentWinner()));

        ObservableList<Match> observableGrandSlams = FXCollections.observableArrayList();

        assert equals(observableGrandSlams.size() > 0);

        observableGrandSlams = TournamentDao.populateGrandSlam();

        grandSlamTable.setItems(observableGrandSlams);

        observableGrandSlams = null;
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