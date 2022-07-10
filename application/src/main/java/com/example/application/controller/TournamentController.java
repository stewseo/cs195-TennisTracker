//package com.example.application.controller;
//
//import com.example.application.model.Tournament;
//import io.github.palexdev.materialfx.controls.*;
//import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
//import io.github.palexdev.materialfx.filter.StringFilter;
//import io.github.palexdev.materialfx.utils.StringUtils;
//import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.util.StringConverter;
//import java.net.URL;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.function.Predicate;
//
//public class TournamentController implements Initializable {
//
//    @FXML public MFXButton searchButton;
//
//    @FXML public MFXTextField textField;
//
//    @FXML public MFXFilterComboBox<Tournament> filterCombo;
//
//    @FXML public MFXFilterComboBox<Record> custFilterCombo;
//
//    @FXML public Label validateWtaTournamentLavel;
//
//    @FXML public MFXDatePicker custDatePicker;
//
//    @FXML public MFXTableView<Tournament> tournamentTableView;
//
//    Map<Integer, List<String>> pointByPoint;
//
//    private void tournamentFilter() {
//        StringConverter<Tournament> converter =
//                FunctionalStringConverter.to(tournament->(tournament == null) ? "" : tournament.getTourneyName());
//
//        Function<String, Predicate<Tournament>> filterFunction = s -> e -> {
//            return StringUtils.containsIgnoreCase(converter.toString(e), s);
//        };
//        ObservableList<Tournament> observableTournament = FXCollections.observableArrayList();
//        Tournament tournament = new Tournament();
//
//        filterCombo.setItems(observableTournament);
//        filterCombo.setConverter(converter);
//        filterCombo.setFilterFunction(filterFunction);
//        //use case combo box with atp and wta champions from each year
//
//        ObservableList<Record> pointByPointObservable = FXCollections.observableArrayList();
//
//        StringConverter<Record> pointByPointConverter =
//                FunctionalStringConverter.to(e->(e == null) ? "" : e.get("match_id").toString());
//
//        Function<String, Predicate<Record>> pointByPointFilterFunction = s -> e -> {
//            return StringUtils.containsIgnoreCase(pointByPointConverter.toString(e), s);
//        };
//
//        custFilterCombo.setItems(pointByPointObservable);
//        custFilterCombo.setConverter(pointByPointConverter);
//        custFilterCombo.setFilterFunction(pointByPointFilterFunction);
//        custFilterCombo.setResetOnPopupHidden(false);
//
//    }
//    private void setupTable() throws SQLException{
//        MFXTableColumn<Tournament> c1=new MFXTableColumn<>("Tournament Name",true,Comparator.comparing(Tournament::getTourneyName));
//        MFXTableColumn<Tournament> c2=new MFXTableColumn<>("Year",true,Comparator.comparing(Tournament::getTourneyDate));
//        MFXTableColumn<Tournament> c3=new MFXTableColumn<>("Match Num",true,Comparator.comparing(Tournament::getMatchNumber));
//        MFXTableColumn<Tournament> c4=new MFXTableColumn<>("Round",true,Comparator.comparing(Tournament::getRound));
//        MFXTableColumn<Tournament> c5=new MFXTableColumn<>("Player 1",true,Comparator.comparing(Tournament::getPlayer1));
//        MFXTableColumn<Tournament> c6=new MFXTableColumn<>("Player 2",true,Comparator.comparing(Tournament::getPlayer2));
//        MFXTableColumn<Tournament> c7=new MFXTableColumn<>("Court Name",true,Comparator.comparing(Tournament::getCourtName));
//        MFXTableColumn<Tournament> c8=new MFXTableColumn<>("Event Name",true,Comparator.comparing(tournament-> tournament.getEventName()));
//        MFXTableColumn<Tournament> c9=new MFXTableColumn<>("Winner",true,Comparator.comparing(tournament-> tournament.getTournamentWinner()));
//
//        System.out.println();
//
//        c1.setRowCellFactory(match->new MFXTableRowCell<>(Match::getTourneyName));
//        c2.setRowCellFactory(match->new MFXTableRowCell<>(Match::getTourneyDate));
//        c3.setRowCellFactory(match->new MFXTableRowCell<>(Match::getMatchNumber));
//        c4.setRowCellFactory(match->new MFXTableRowCell<>(Match::getRound));
//        c5.setRowCellFactory(match->new MFXTableRowCell<>(Match::getPlayer1));
//        c6.setRowCellFactory(match->new MFXTableRowCell<>(Match::getPlayer2));
//        c7.setRowCellFactory(match->new MFXTableRowCell<>(Match::getCourtName));
//        c8.setRowCellFactory(match->new MFXTableRowCell<>(e-> e.getEventName()));
//        c9.setRowCellFactory(match->new MFXTableRowCell<>(e-> e.getTournamentWinner())
//        {{
//            setAlignment(Pos.CENTER_RIGHT);
//        }});
//        c9.setAlignment(Pos.CENTER_RIGHT);
//
//        tournamentTableView.getTableColumns().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9);
//        tournamentTableView.getFilters().addAll(
//                new StringFilter<>("Tournament Name",Match::getTourneyName),
//                new StringFilter<>("Year",Match::getTourneyDate),
//                new StringFilter<>("Match Num",Match::getMatchNumber),
//                new StringFilter<>("Round", e-> e.getRound()),
//                new StringFilter<>("Player 1",Match::getPlayer1),
//                new StringFilter<>("Player 2",Match::getPlayer2),
//                new StringFilter<>("Court Name",Match::getCourtName),
//                new StringFilter<>("Event Name",e-> e.getEventName()),
//                new StringFilter<>("Winner", e-> e.getTournamentWinner()));
//
//        ObservableList<Match> observableGrandSlams = FXCollections.observableArrayList();
//
//        assert equals(observableGrandSlams.size() > 0);
//
//        observableGrandSlams = TournamentDao.populateGrandSlam();
//
//        tournamentTableView.setItems(observableGrandSlams);
//
//        observableGrandSlams = null;
//    }
//    public void handleTextEntry(ActionEvent event) {
//        String dateInput = custDatePicker.getText();
//        System.out.println(dateInput);
//        String input = textField.getText();
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        grandSlamFilters();
//
//        try {
//            setupTable();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}