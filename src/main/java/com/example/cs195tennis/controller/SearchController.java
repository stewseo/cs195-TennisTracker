/*
 * Copyright (C) 2022 Parisi Alessandro
 * This file is part of MaterialFX (https://github.com/palexdev/MaterialFX).
 *
 * MaterialFX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialFX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialFX.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.MatchDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.model.*;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

public class SearchController implements Initializable {

	@FXML private MFXComboBox<String> yrStartFilterBox;

	@FXML private MFXComboBox<String> tournamentFilterBox;

	@FXML private MFXComboBox<String> playerStatsFilterBox;

	@FXML private MFXComboBox<Match> matchStatFilterBox;

	@FXML private MFXComboBox<String> yrEndFilterBox;

	@FXML private MFXFilterComboBox<Match> filters;

	@FXML private MFXFilterComboBox<Match> filterMatchStats;

	@FXML private MFXTextField textField, textFieldPlayer2;

	@FXML private Label validationLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//player ranks
		//player individual stats
		//winner / loser stats
		ObservableList<String> tournamentFilterObservable = FXCollections.observableArrayList();
		ObservableList<String> winnerLoserObservable = FXCollections.observableArrayList();
		ObservableList<String> playerRankObservable = FXCollections.observableArrayList();
		ObservableList<Player> playerObservable = FXCollections.observableArrayList();
		ObservableList<Match> matchObservable = FXCollections.observableArrayList();

		try {
			winnerLoserObservable = DataHandeler.getQueryFields(new String[]{"winner_id","winner_name","loser_id","loser_name","loser_rank","winner_rank"});

			MatchDao.readAtpMatchToMap().forEach((k,v)-> v.forEach(matchObservable::addAll));
			playerStatsFilterBox.setItems(winnerLoserObservable);
			tournamentFilterBox.setItems(MatchDao.getTournamentNames());

		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		List<String> fields = new ArrayList<>();
		fields.add("serves_won");
		fields.add("returns");
		fields.add("errors");
		playerRankObservable.addAll(fields);
		validationLabel.setText("Head to Head");
		matchStatFilterBox.setItems(matchObservable);
		yrStartFilterBox.setItems(playerRankObservable);
		yrEndFilterBox.setItems(playerRankObservable);

		matchStatFilterBox.setOnCancel(s -> matchStatFilterBox.setText(matchStatFilterBox.getSelectedItem().getTourney_name()));

		matchStatFilterBox.setOnCommit(s -> {
			Match match = new Match(s);
			if (!matchObservable.contains(match)) {
				matchObservable.add(match);
			}
			matchStatFilterBox.selectItem(match);
		});

		StringConverter<Match> converter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getTourney_id());
		Function<String, Predicate<Match>> filterFunction = s -> e -> StringUtils.containsIgnoreCase(converter.toString(e), s);

		filters.setItems(matchObservable);
		filters.setConverter(converter);
		filters.setFilterFunction(filterFunction);
		filterMatchStats.setItems(matchObservable);
		filterMatchStats.setConverter(converter);
		filterMatchStats.setFilterFunction(filterFunction);
		filterMatchStats.setResetOnPopupHidden(false);
	}

	public void handleSearchPlayer1(ActionEvent event) throws SQLException {
		String input = textField.getText();

		String[] temp = new String[]{input,filters.getText(),filterMatchStats.getText(),
				tournamentFilterBox.getText(), playerStatsFilterBox.getText()};

		PlayerDao.query(filterMatchStats.getText(), temp);
	}

	public void handleSearchPlayer2(ActionEvent event) {
		String input = textField.getText();

	}
}
