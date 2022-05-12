package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.MatchDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.Dao.WtaDao;
import com.example.cs195tennis.Dao.WtaMatchDao;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Player;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.DateTimeUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import org.apache.logging.log4j.util.PropertySource;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SearchController implements Initializable {


    @FXML public MFXButton searchButton;

    @FXML private MFXTableView<Match> table;

	@FXML private MFXComboBox<String> tournamentFilterBox;

	@FXML private MFXComboBox<Match> matchStatFilterBox;

	@FXML public MFXDatePicker custDatePicker;

	@FXML private MFXFilterComboBox<Match> filterTournaments;

	@FXML private MFXFilterComboBox<Player> filteredPlayers;

	@FXML private MFXTextField textField;

	@FXML private Label validationLabel;

	static ObservableList<Match> matchObservable = FXCollections.observableArrayList();


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
		custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));

		ObservableList<String> tournamentFilterObservable = FXCollections.observableArrayList();
		ObservableList<String> winnerLoserObservable = FXCollections.observableArrayList();
		ObservableList<Player.Ranking> playerRankObservable = FXCollections.observableArrayList();
		ObservableList<Player> playerObservable = FXCollections.observableArrayList();
		ObservableList<Match> tournamentObservable = FXCollections.observableArrayList();

		addTableListener();
		try {

			playerRankObservable.addAll(PlayerDao.getPlayerRanks());

			filteredPlayers.setItems(
					PlayerDao.readWtaPlayerToObservable());

			filterTournaments.setItems(
					WtaDao.readWtaMatchesToObservable());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		validationLabel.setText("Validation label");

		StringConverter<Match> converter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getTourney_name());
		StringConverter<Player> playerConverter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getFullName());

		Function<String, Predicate<Match>> filterFunction = s -> e -> {
			System.out.println(s);
			System.out.println(e);
			return StringUtils.containsIgnoreCase(converter.toString(e), s);
		};

		Function<String, Predicate<Player>> filterPlayer = s -> e -> {
			System.out.println(s);
			System.out.println(e);
			return StringUtils.containsIgnoreCase(playerConverter.toString(e), s);
		};

		filteredPlayers.setConverter(playerConverter);
		filteredPlayers.setFilterFunction(filterPlayer);

		filterTournaments.setConverter(converter);
		filterTournaments.setFilterFunction(filterFunction);
		filterTournaments.setResetOnPopupHidden(false);

		setupTable();
	}

	String colOneValue = "tourney_name";
	Comparator<Match> bySelection = Comparator.comparing(userQueryParameters-> {
		try {
			return colOneValue = userQueryParameters.equals(
					new Match(new HashSet<String>(
							Integer.parseInt("Adjust model hash and equals")))) ? getCategoryByIndex(userQueryParameters.toString()) : "tourney_name";
		} catch (SQLException e) {
			e.printStackTrace();
		}return colOneValue;
	});

	private String getCategoryByIndex(String statCategory) throws SQLException {
		return MatchDao
				.getMatchData()
				.get(statCategory.hashCode()).toString();

	}

	private void addTableListener() {

	}

	private void setupTable() {
		MFXTableColumn<Match> tourneyNameColumn = new MFXTableColumn<>(colOneValue, true, Comparator.comparing(Match::getTourney_name));
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
		tourneyDateColumn.setAlignment(Pos.CENTER_RIGHT);

		table.getTableColumns().addAll(tourneyNameColumn, tourneyDateColumn);
		table.getFilters().addAll(
				new StringFilter<>("tourney_name", Match::getTourney_name),
				new StringFilter<>("tourney_date", Match::getTourney_date),
				new StringFilter<>("winner_name", Match::getWinner_name),
				new StringFilter<>("loser_name", Match::getLoser_name),
				new StringFilter<>("score", Match::getScore),
				new StringFilter<>("round", Match::getRound),
				new StringFilter<>("best_of", Match::getBest_of)
		);
		table.setItems(matchObservable);
	}

	public void handleSearchPlayer1(ActionEvent event) throws SQLException {
		String date = custDatePicker.getText();
		String player = filteredPlayers.getText();
		String tournament = filterTournaments.getText();
		String filterInput = tournamentFilterBox.getText();

		String[] temp = new String[]{};

		PlayerDao.query(player, temp);
	}

}
