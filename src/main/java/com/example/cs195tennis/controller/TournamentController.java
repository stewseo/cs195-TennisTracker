package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.Dao.TournamentDao;
import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.ColorUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class TournamentController<Record> implements Initializable {

    @FXML private MFXListView<org.jooq.Record> list;
    @FXML private MFXListView<org.jooq.Record> listView;
    @FXML private MFXCheckListView<String> checkList;
    @FXML private MFXListView<org.jooq.Record> tournamentList;


        @Override
        public void initialize(URL location, ResourceBundle resources) {

            TournamentDao tournamentDao = new TournamentDao();
            PlayerDao playerDao = new PlayerDao();

            //most recent tournaments
            ObservableList<org.jooq.Record> recentTournaments = FXCollections.observableArrayList();
            recentTournaments = tournamentDao.getRecentTournaments();
            createMostGrandSlamWins(recentTournaments);

            //current rankings WTA
            ObservableList<org.jooq.Record> currentWtaRanks = FXCollections.observableArrayList();
            currentWtaRanks = playerDao.getCurrentWtaRanks();
            createRecentWtaResultsView(currentWtaRanks);

            //current rankings ATP
            ObservableList<Record> currentAtpRanks = FXCollections.observableArrayList();
            currentAtpRanks = playerDao.getCurrentAtpRanks();
            createRecentAtpResultsViews(currentAtpRanks);

            //most tournament wins single player
            ObservableList<Record> players = FXCollections.observableArrayList();
            players = playerDao.getPlayersWithMostAllTimeWins();
            createCheckListView(players);

//            createRecentTournamentResultsListView(recentTournaments);

        }

    private void createMostGrandSlamWins(ObservableList<org.jooq.Record> recentTournaments) {
    }

    private StringConverter<org.jooq.Record> createStringConverters() {
            return FunctionalStringConverter.to(match -> (match == null) ? "" : match + " " + match);
    }

    private void createRecentTournamentResultsListView(ObservableList<org.jooq.Record> recentTournaments) {

        StringConverter<org.jooq.Record> converter = FunctionalStringConverter.to(match -> (match == null) ? "" : match.get("slams").toString());
        tournamentList.setItems(recentTournaments);
        tournamentList.setConverter(converter);
    }

    private void createCheckListView(ObservableList<Record> players) {
//        checkList.setItems(players);
    }

    private void createRecentAtpResultsViews(ObservableList<Record> currentAtpRanks) {

    }


    private void createRecentWtaResultsView(ObservableList<org.jooq.Record> currentWtaRanks) {
        StringConverter<org.jooq.Record> converter = FunctionalStringConverter.to(match -> (match == null) ? "" : match.get("rank") +"" + match.get("name_first") + "" + match.get("name_last"));
        System.out.println(currentWtaRanks.get(1));
        listView.setItems(currentWtaRanks);
        listView.setConverter(converter);
        listView.setCellFactory(match -> new MatchCellFactory(listView, match));
        listView.features().enableBounceEffect();
        listView.features().enableSmoothScrolling(0.5);
        }


        @FXML
    void changeColors(ActionEvent event) {
            listView.setTrackColor(ColorUtils.getRandomColor());
            listView.setThumbColor(ColorUtils.getRandomColor());
            listView.setThumbHoverColor(ColorUtils.getRandomColor());
        }

        @FXML
        void changeDepth(ActionEvent event) {
            DepthLevel newLevel = (listView.getDepthLevel() == DepthLevel.LEVEL0) ? DepthLevel.LEVEL2 : DepthLevel.LEVEL0;
            listView.setDepthLevel(newLevel);
            System.out.println("depth level: " + listView.getDepthLevel());
            System.out.println("depth test: " + listView.getDepthTest());

        }

        @FXML
        public void handleMouseClick(MouseEvent mouseEvent) {
            String s = mouseEvent.getTarget().toString();
        }

    private class MatchCellFactory extends MFXListCell<org.jooq.Record> {
            private final MFXFontIcon matchIcon;

            public MatchCellFactory(MFXListView<org.jooq.Record> listView, org.jooq.Record data) {
                super(listView, data);

                matchIcon = new MFXFontIcon("mfx-user", 18);
                matchIcon.getStyleClass().add("user-icon");
                render(data);
            }

            @Override
            protected void render(org.jooq.Record data) {
                super.render(data);
                if (matchIcon != null) getChildren().add(0, matchIcon);
            }
        }
    }


