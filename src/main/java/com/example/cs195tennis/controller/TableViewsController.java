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
import com.example.cs195tennis.model.*;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.EnumFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
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

public class TableViewsController implements Initializable {

	@FXML private MFXTableView<Match> table;
	@FXML private MFXPaginatedTableView<Player> paginated;

	public ObservableList<Player> playerObservableList = FXCollections.observableArrayList();
	public ObservableList<Match> matchObservableList = FXCollections.observableArrayList();

	private static Map<String, List<Match>> matchMap = new HashMap<>();

	Map<Integer, Map<Integer, List<Object>>> mapQueries = new HashMap<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			matchMap = MatchDao.readAtpMatchToMap();
		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		matchMap.forEach((k, v) -> v.forEach(e ->
				matchObservableList.add(new Match(e.getTourney_name(), e.getTourney_date(), e.getMatch_num(), e.getWinner_name(), e.getLoser_name(), e.getScore(),
						e.getRound(), e.getBest_of()
				))));

		Map<String, List<Player>> playerMap = new HashMap<>(PlayerDao.getPlayerMap());

		playerMap.forEach((k, v) -> v.forEach(pid-> playerObservableList.add(
                    new Player(pid.getId(), pid.getFirstName(), pid.getLastName(), pid.getHand(), pid.getDob(), pid.getIoc(), pid.getHeight(), pid.getWiki(), pid.getRank()
                    ))));

		setupTable();
		setupPaginated();

		table.autosizeColumnsOnInitialization();
		paginated.autosizeColumnsOnInitialization();

		When.onChanged(paginated.currentPageProperty())
				.then((oldValue, newValue) -> paginated.autosizeColumns())
				.listen();
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

		table.getTableColumns().addAll(tourneyNameColumn, tourneyDateColumn,matchNumColumn, winnerNameColumn,loserNameColumn,scoreColumn, roundColumn, bestOfColumn);
		table.getFilters().addAll(
				new StringFilter<>("tourney_name", Match::getTourney_name),
				new StringFilter<>("tourney_date", Match::getTourney_date),
				new StringFilter<>("match_num", Match::getTourney_date),
				new StringFilter<>("winner_name", Match::getWinner_name),
				new StringFilter<>("loser_name", Match::getLoser_name),
				new StringFilter<>("score", Match::getScore),
				new StringFilter<>("round", Match::getRound),
				new StringFilter<>("best_of", Match::getBest_of)
		);
		table.setItems(matchObservableList);
	}

	private void setupPaginated() {
		MFXTableColumn<Player> playerIdColumn = new MFXTableColumn<>("player_id", false, Comparator.comparing(Player::getId));
		MFXTableColumn<Player> playerNameColumn = new MFXTableColumn<>("fullName", false, Comparator.comparing(Player::getFirstName));
		MFXTableColumn<Player> playerHandColumn = new MFXTableColumn<>("hand", false, Comparator.comparing(Player::getHand));
		MFXTableColumn<Player> playerDobColumn = new MFXTableColumn<>("dob", false, Comparator.comparing(Player::getDob));
		MFXTableColumn<Player> playerLocColumn = new MFXTableColumn<>("player_ioc", false, Comparator.comparing(Player::getIoc));
		MFXTableColumn<Player> playerHeightColumn = new MFXTableColumn<>("height", false, Comparator.comparing(Player::getHeight));
		MFXTableColumn<Player> playerWikiColumn = new MFXTableColumn<>("wiki", false, Comparator.comparing(Player::getWiki));

		playerIdColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getId));
		playerNameColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getFullName));
		playerHandColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getHand));
		playerDobColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getDob));
		playerLocColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getIoc));
		playerHeightColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getHeight));
		playerWikiColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getWiki)
				{{
			setAlignment(Pos.CENTER_RIGHT);
		}});
		playerWikiColumn.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getWiki));
		playerHandColumn.setAlignment(Pos.CENTER_RIGHT);

		paginated.getTableColumns().addAll(playerIdColumn, playerNameColumn,playerHandColumn, playerDobColumn, playerLocColumn, playerHeightColumn, playerWikiColumn);
		paginated.getFilters().addAll(
				new StringFilter<>("player_id", Player::getId),
				new StringFilter<>("fullName", Player::getFullName),
				new StringFilter<>("hand", Player::getHand),
				new StringFilter<>("dob", Player::getDob),
				new StringFilter<>("player_ioc", Player::getDob),
				new StringFilter<>("height", Player::getIoc),
				new StringFilter<>("wiki", Player::getHand)
		);

		System.out.println(playerObservableList.size());

		paginated.setItems(playerObservableList);
	}
}
