package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.MatchDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.Dao.WtaDao;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.model.*;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.DateTimeUtils;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class WTATournamentController implements Initializable {

        @FXML public MFXTableView<Match> wtaTouranmentTable;

        public ObservableList<Player> playerObservableList = FXCollections.observableArrayList();

        public ObservableList<Match> wtaTournamentObservable = FXCollections.observableArrayList();

        private static Map<String, List<Match>> matchMap = new HashMap<>();

        Map<Integer, Map<Integer, List<Object>>> mapQueries = new HashMap<>();

        @FXML private MFXDatePicker custDatePicker;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
            custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));

            try {
                matchMap = WtaDao.readWtaCsvToMap();
            } catch (FileNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            System.out.println(matchMap.size());

            matchMap.forEach((k, v) -> v.forEach(e ->
                    wtaTournamentObservable.add(new Match(e.getTourney_name(), e.getTourney_date(), e.getMatch_num(), e.getWinner_name(), e.getLoser_name(), e.getScore(),
                            e.getRound(), e.getBest_of()
                    ))));

            setupTable();

        }

        private void setupTable() {
            MFXTableColumn<Match> tourneyNameColumn = new MFXTableColumn<>("tourney_name", true, Comparator.comparing(Match::getTourney_name));
            MFXTableColumn<Match> tourneyDateColumn = new MFXTableColumn<>("tourney_date", true, Comparator.comparing(Match::getTourney_date));
            MFXTableColumn<Match> matchNumColumn = new MFXTableColumn<>("match_num", true, Comparator.comparing(Match::getMatch_num));
            MFXTableColumn<Match> winnerNameColumn = new MFXTableColumn<>("winner_name", true, Comparator.comparing(Match::getWinner_name));
            MFXTableColumn<Match> loserNameColumn = new MFXTableColumn<>("loser_name", true, Comparator.comparing(Match::getLoser_name));
            MFXTableColumn<Match> scoreColumn = new MFXTableColumn<>("score", true, Comparator.comparing(Match::getScore));
            MFXTableColumn<Match> roundColumn = new MFXTableColumn<>("round", true, Comparator.comparing(Match::getRound));
            MFXTableColumn<Match> bestOfColumn = new MFXTableColumn<>("best_of", true, Comparator.comparing(Match::getBest_of));

            tourneyNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getTourney_name));
            tourneyDateColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getTourney_date));
            matchNumColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getMatch_num));
            winnerNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getWinner_name));
            loserNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getLoser_name));
            scoreColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getScore));
            roundColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getRound));
            bestOfColumn.setRowCellFactory(match -> new MFXTableRowCell<>(Match::getBest_of)
            {{
                setAlignment(Pos.CENTER_RIGHT);
            }});
            bestOfColumn.setAlignment(Pos.CENTER_RIGHT);

            wtaTouranmentTable.getTableColumns().addAll(tourneyNameColumn, tourneyDateColumn,matchNumColumn, winnerNameColumn,loserNameColumn,scoreColumn, roundColumn, bestOfColumn);
            wtaTouranmentTable.getFilters().addAll(
                    new StringFilter<>("tourney_name", Match::getTourney_name),
                    new StringFilter<>("tourney_date", Match::getTourney_date),
                    new StringFilter<>("match_num", Match::getTourney_date),
                    new StringFilter<>("winner_name", Match::getWinner_name),
                    new StringFilter<>("loser_name", Match::getLoser_name),
                    new StringFilter<>("score", Match::getScore),
                    new StringFilter<>("round", Match::getRound),
                    new StringFilter<>("best_of", Match::getBest_of)
            );
            wtaTouranmentTable.setItems(wtaTournamentObservable);
        }

    }



