
package com.example.cs195tennis.controller;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableRow;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.font.FontResources;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class FontResourcesController implements Initializable {
	private final ObservableList<FontResources> fontResources;

	@FXML
	private Label header;

	@FXML
	private MFXTableView<FontResources> tableView;

	public FontResourcesController() {
		fontResources = FXCollections.observableArrayList(FontResources.values());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("test test");
		MFXTableColumn<FontResources> iconColumn = new MFXTableColumn<>("Icon", false, Comparator.comparing(FontResources::getDescription));
		MFXTableColumn<FontResources> descriptionColumn = new MFXTableColumn<>("Description", false, Comparator.comparing(FontResources::getDescription));
		MFXTableColumn<FontResources> codeColumn = new MFXTableColumn<>("Code", false, Comparator.comparing(FontResources::getCode));

		iconColumn.setRowCellFactory(resource -> new MFXTableRowCell<>(FontResources::getDescription) {
			final MFXFontIcon icon = new MFXFontIcon("mfx-logo", 32);

			@Override
			public void update(FontResources item) {
				icon.setDescription(item.getDescription());
				setGraphic(icon);
			}
		});
		descriptionColumn.setRowCellFactory(resource -> new MFXTableRowCell<>(FontResources::getDescription) {
			@Override
			public double computePrefWidth(double height) {
				return 300;
			}
		});
		codeColumn.setRowCellFactory(resource -> new MFXTableRowCell<>(FontResources::getCode, character -> Integer.toHexString(character | 0x10000).substring(1).toUpperCase()));

		tableView.setTableRowFactory(resource -> new MFXTableRow<>(tableView, resource) {{
			setPrefHeight(48);
		}});
		tableView.getTableColumns().addAll(iconColumn, descriptionColumn, codeColumn);
		tableView.getFilters().add(new StringFilter<>("Description", FontResources::getDescription));
		tableView.setItems(fontResources);
		tableView.features().enableBounceEffect();
		tableView.features().enableSmoothScrolling(0.7);
		tableView.autosizeColumnsOnInitialization();

		header.setText("MaterialFX Font Resources (" + fontResources.size() + ")");
	}
}
