package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.model.Tournament;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.DateTimeUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;


public class GrandSlamController implements Initializable {

    @FXML public MFXTableView<Tournament> wtaTouranmentTable;

    @FXML public MFXButton searchButton;

    @FXML public MFXFilterComboBox<Tournament> filterCombo;
    @FXML public MFXFilterComboBox<Tournament>  custFilterCombo;

    @FXML public Label validateWtaTournamentLavel;

    @FXML public MFXDatePicker custDatePicker;

    @FXML private MFXTextField textField;

    ObservableList<Tournament> tournamentObservable = null;

    @Override public void initialize(URL location, ResourceBundle resources) {

        custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
        custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));

        try {
          tournamentObservable = TournamentDao.getTournamentObservable();
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringConverter<Tournament> converter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getTourney_name());
        Function<String, Predicate<Tournament>> filterTournaments = s -> e -> StringUtils.containsIgnoreCase(converter.toString(e), s);

        filterCombo.setItems(tournamentObservable);
        filterCombo.setConverter(converter);
        filterCombo.setFilterFunction(filterTournaments);
        tournamentObservable.stream().close();

//        ObservableList<TournamentRecord> checkedCatgeGoriesFilter = null;
//        StringConverter<TournamentRecord> convertInputs = null;
//        Function<String, Predicate<TournamentRecord>> filterTournamentStats = null;

        try {
            tournamentObservable = TournamentDao.getTournamentObservable();
            custFilterCombo.setItems(tournamentObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        custFilterCombo.setConverter(converter);
        custFilterCombo.setFilterFunction(filterTournaments);
        custFilterCombo.setResetOnPopupHidden(false);

    }

    String col1 = "tourney_name";
    String col2 = "surface";
    String col3 = "draw_size";
    String col4 = "level";
    String col5 = "winner_name";
    String suffix = "Column";
    private void setupTable() throws SQLException {

        MFXTableColumn<Tournament> c1 = new MFXTableColumn<>(col1, true, Comparator.comparing(Tournament::getTourney_name));
        MFXTableColumn<Tournament> c2 = new MFXTableColumn<>(col2, true, Comparator.comparing(Tournament::getSurface));
        MFXTableColumn<Tournament> c3 = new MFXTableColumn<>(col3, true, Comparator.comparing(Tournament::getDraw_size));
        MFXTableColumn<Tournament> c4 = new MFXTableColumn<>(col3, true, Comparator.comparing(Tournament::getTourney_level));

        c1.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getTourney_name));
        c2.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getSurface));
        c3.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getDraw_size));
        c4.setRowCellFactory(match -> new MFXTableRowCell<>(Tournament::getTourney_level)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        c4.setAlignment(Pos.CENTER_RIGHT);

        wtaTouranmentTable.getTableColumns().addAll(c1, c2, c3, c4);
        wtaTouranmentTable.getFilters().addAll(
                new StringFilter<>("tourney_name", Tournament::getTourney_name),
                new StringFilter<>("surface", Tournament::getSurface),
                new StringFilter<>("draw_size", Tournament::getDraw_size),
                new StringFilter<>("level",Tournament::getTourney_level)
        );


        tournamentObservable = TournamentDao.getTournamentObservable();

        wtaTouranmentTable.setItems(tournamentObservable);
        tournamentObservable.stream().close();
    }

    public void handleTextEntry(ActionEvent event) {
    }
}


