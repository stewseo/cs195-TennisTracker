package com.example.cs195tennis.controller;

import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.ColorUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class TournamentController<Match> implements Initializable {

    @FXML private MFXListView<Match> list;
    @FXML private MFXListView<Match> listView;
    @FXML private MFXCheckListView<Match> checkList;
    @FXML private MFXListView<Match> tournamentList;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            ObservableList<Match> matches = FXCollections.observableArrayList();
            ObservableList<Match> players = FXCollections.observableArrayList();
            StringConverter<Match> converter = FunctionalStringConverter.to(match -> (match == null) ? "" : match + " " + match);

            list.setItems(matches);
            listView.setItems(matches);
            checkList.setItems(players);
            listView.setConverter(converter);
            listView.setCellFactory(match -> new MatchCellFactory(listView, match));
            listView.features().enableBounceEffect();
            listView.features().enableSmoothScrolling(0.5);

            tournamentList.setItems(matches);
            tournamentList.setConverter(converter);
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
        }

        private class MatchCellFactory extends MFXListCell<Match> {
            private final MFXFontIcon matchIcon;

            public MatchCellFactory(MFXListView<Match> listView, Match data) {
                super(listView, data);

                matchIcon = new MFXFontIcon("mfx-user", 18);
                matchIcon.getStyleClass().add("user-icon");
                render(data);
            }

            @Override
            protected void render(Match data) {
                super.render(data);
                if (matchIcon != null) getChildren().add(0, matchIcon);
            }
        }
    }


