package com.example.cs195tennis.controller;
import com.example.cs195tennis.model.AtpMatch;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.PlayerRanking;
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

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class ATPTourController implements Initializable {


    @FXML public MFXButton searchButton;

	@FXML public MFXFilterComboBox<AtpMatch> atpFilterTournament;
	@FXML public MFXFilterComboBox<AtpMatch> atpFilterTournamentStats;
    public Label atpValidate;
	public MFXTextField atpTextFields;
	@FXML MFXTableView<AtpMatch> atpTournamentViewTable;

	@FXML public MFXDatePicker custDatePicker;


	@FXML private MFXTextField textField;

	ObservableList<AtpMatch> matchObservable;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
		custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));



		StringConverter<AtpMatch> converter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getTourney_name());

		Function<String, Predicate<AtpMatch>> filterFunction = s -> e -> {
			return StringUtils.containsIgnoreCase(converter.toString(e), s);
		};


		atpFilterTournamentStats.setConverter(converter);
		atpFilterTournamentStats.setFilterFunction(filterFunction);

		atpFilterTournament.setConverter(converter);
		atpFilterTournament.setFilterFunction(filterFunction);
		atpFilterTournament.setResetOnPopupHidden(false);

		try {
			setupTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings("unchecked")
	private void setupTable() throws SQLException {
		MFXTableColumn<AtpMatch> tourneyNameColumn = new MFXTableColumn<>("tourney_name", true, Comparator.comparing(AtpMatch::getTourney_name));
		MFXTableColumn<AtpMatch> tourneyDateColumn = new MFXTableColumn<>("tourney_date", true, Comparator.comparing(AtpMatch::getTourney_date));
		MFXTableColumn<AtpMatch> winnerNameColumn = new MFXTableColumn<>("winner_name", true,  Comparator.comparing(AtpMatch::getWinnerName));
		MFXTableColumn<AtpMatch> loserNameColumn = new MFXTableColumn<>("loser_name", true,  Comparator.comparing(AtpMatch::getLoserName));


		tourneyNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(AtpMatch::getTourney_name));
		tourneyDateColumn.setRowCellFactory(match -> new MFXTableRowCell<>(AtpMatch::getTourney_date));
		winnerNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(AtpMatch::getWinnerName));
		loserNameColumn.setRowCellFactory(match -> new MFXTableRowCell<>(AtpMatch::getLoserName)
		{{
			setAlignment(Pos.CENTER_RIGHT);
		}});
		loserNameColumn.setAlignment(Pos.CENTER_RIGHT);

		atpTournamentViewTable.getTableColumns().addAll(tourneyNameColumn, tourneyDateColumn,winnerNameColumn,loserNameColumn);
		atpTournamentViewTable.getFilters().addAll(
				new StringFilter<>("tourney_name", AtpMatch::getTourney_name),
				new StringFilter<>("tourney_date", AtpMatch::getTourney_date),
				new StringFilter<>("winner_name", AtpMatch::getWinnerName),
				new StringFilter<>("loser_name", AtpMatch::getLoserName)
		);

//		atpTournamentViewTable.setItems();
	}

	public void AtpPlayersText(ActionEvent event) {
	}
}
