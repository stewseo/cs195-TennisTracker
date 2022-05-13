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
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXTooltip;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import com.example.cs195tennis.model.Model;
import com.example.cs195tennis.model.Person;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

public class ComboBoxesController implements Initializable {

	@FXML public MFXTextField textFieldPlayer2;

	@FXML private MFXComboBox<Match> nBFCombo;

	@FXML private MFXComboBox<Match> nCombo;

	@FXML private MFXComboBox<Match> nCustCombo;

	@FXML
	private MFXComboBox<Match> nEditCombo;

	@FXML
	private MFXComboBox<Match> nNFCombo;

	@FXML private MFXFilterComboBox<Match> filterCombo;

	@FXML private MFXFilterComboBox<Match> custFilterCombo;


	@FXML private MFXTextField textField;

	@FXML private Label validationLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<Player> playerObservable = FXCollections.observableArrayList();
		ObservableList<Match> matchObservable = FXCollections.observableArrayList();
		try {
			MatchDao.readAtpMatchToMap().forEach((k,v)-> v.forEach(matchObservable::addAll));
		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String input = textField.getText();

		validationLabel.setText("Head to Head");

		System.out.println(" size " + matchObservable.size());
		System.out.println(matchObservable);
		nCombo.setItems(matchObservable);
		nCustCombo.setItems(matchObservable);
		nEditCombo.setItems(matchObservable);
		nBFCombo.setItems(matchObservable);
		nNFCombo.setItems(matchObservable);

		nEditCombo.setOnCancel(s -> nEditCombo.setText(nEditCombo.getSelectedItem().getTourney_name()));

		nEditCombo.setOnCommit(s -> {
			Match match = new Match(s);
			if (!matchObservable.contains(match)) {
				matchObservable.add(match);
			}
			nEditCombo.selectItem(match);
		});

		MFXTooltip.of(
				nEditCombo,
				"""
						This combo box allows you to add new items to the list (no duplicates allowed) when pressing Enter.
						It also allows to restore the previous selected item by pressing Ctrl+Shift+Z.
						Both key strokes are default for all MFXComboBoxes but the action to perform must be configured by the user.
						This combo box is also set to scroll to the selected item when opening the popup.
						"""
		).install();

		StringConverter<Match> converter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getWinner_ioc());
		Function<String, Predicate<Match>> filterFunction = s -> e -> StringUtils.containsIgnoreCase(converter.toString(e), s);

		filterCombo.setItems(matchObservable);
		filterCombo.setConverter(converter);
		filterCombo.setFilterFunction(filterFunction);
		custFilterCombo.setItems(matchObservable);
		custFilterCombo.setConverter(converter);
		custFilterCombo.setFilterFunction(filterFunction);
		custFilterCombo.setResetOnPopupHidden(false);
	}
}
