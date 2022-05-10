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

import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Model;
import com.example.cs195tennis.model.Person;
import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.ColorUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class ListViewsController implements Initializable {

	@FXML
	private MFXListView<String> list2;

	@FXML
	private MFXListView<Person> list;

	@FXML
	private MFXCheckListView<String> checkList;

	@FXML
	private MFXListView<Person> legacyList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> strings = Model.strings;
		ObservableList<Person> people = Model.people;
		StringConverter<Person> converter = FunctionalStringConverter.to(person -> (person == null) ? "" : person.getName() + " " + person.getSurname());

		list2.setItems(strings);
		list.setItems(people);
		checkList.setItems(strings);
		list.setConverter(converter);
		list.setCellFactory(person -> new PersonCellFactory(list, person));
		list.features().enableBounceEffect();
		list.features().enableSmoothScrolling(0.5);

		legacyList.setItems(people);
		legacyList.setConverter(converter);
	}

	@FXML
	void changeColors(ActionEvent event) {
		list.setTrackColor(ColorUtils.getRandomColor());
		list.setThumbColor(ColorUtils.getRandomColor());
		list.setThumbHoverColor(ColorUtils.getRandomColor());
	}

	@FXML
	void changeDepth(ActionEvent event) {
		DepthLevel newLevel = (list.getDepthLevel() == DepthLevel.LEVEL0) ? DepthLevel.LEVEL2 : DepthLevel.LEVEL0;
		list.setDepthLevel(newLevel);
	}

	private static class PersonCellFactory extends MFXListCell<Person> {
		private final MFXFontIcon userIcon;

		public PersonCellFactory(MFXListView<Person> listView, Person data) {
			super(listView, data);

			userIcon = new MFXFontIcon("mfx-user", 18);
			userIcon.getStyleClass().add("user-icon");
			render(data);
		}

		@Override
		protected void render(Person data) {
			super.render(data);
			if (userIcon != null) getChildren().add(0, userIcon);
		}
	}
}
