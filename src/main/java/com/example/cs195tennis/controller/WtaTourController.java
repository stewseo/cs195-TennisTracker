package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.model.PlayerRanking;
import io.github.palexdev.materialfx.controls.*;
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
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

public class WtaTourController implements Initializable {
    public @FXML MFXTextField testWtaPlayer;
    public  @FXML Label handleWtaPlayers;
    public  @FXML MFXDatePicker WtaTourDatePicker;
    public  @FXML MFXFilterComboBox<PlayerRanking> wtaFilerComboBoxCustom;
    public  @FXML MFXFilterComboBox<PlayerRanking> wtaFilerComboBox;
    public  @FXML MFXTableView<PlayerRanking> wtaPlayerTable;

    private void grandSlamFilters() {
        StringConverter<PlayerRanking> converter = FunctionalStringConverter.to(e->(e== null) ? "" : e.getFullName() +" " + e.getPlayerRank() );

        StringConverter<PlayerRanking> converterFields = FunctionalStringConverter.to(e->(e== null) ? "" : e.getFullName() +" " + e.getRankingPoints());

        Function<String, Predicate<PlayerRanking>> filterFunction = s -> e -> {return StringUtils.containsIgnoreCase(converter.toString(e), s);};

        ObservableList<PlayerRanking> observablePlayerRank = FXCollections.observableArrayList();

        observablePlayerRank = PlayerDao.observableWtaPlayer();

        wtaFilerComboBox.setItems(observablePlayerRank);
        wtaFilerComboBox.setConverter(converter);
        wtaFilerComboBox.setFilterFunction(filterFunction);

        wtaFilerComboBoxCustom.setItems(observablePlayerRank);
        wtaFilerComboBoxCustom.setConverter(converterFields);
        wtaFilerComboBoxCustom.setFilterFunction(filterFunction);
        wtaFilerComboBoxCustom.setResetOnPopupHidden(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        WtaTourDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
        WtaTourDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", WtaTourDatePicker.getLocale()));

        grandSlamFilters();
        try {
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void setupTable() throws SQLException {

        MFXTableColumn<PlayerRanking> wtaColumn1 = new MFXTableColumn<>("Name", true, Comparator.comparing(PlayerRanking::getFullName));
        MFXTableColumn<PlayerRanking> wtaColumn2 = new MFXTableColumn<>("Rank", true, Comparator.comparing(PlayerRanking::getPlayerRank));
        MFXTableColumn<PlayerRanking> wtaColumn3 = new MFXTableColumn<>("Rank Date", true, Comparator.comparing(PlayerRanking::getRankDate));
        MFXTableColumn<PlayerRanking> wtaColumn4 = new MFXTableColumn<>("Rank Points", true, Comparator.comparing(PlayerRanking::getRankingPoints));
        MFXTableColumn<PlayerRanking> wtaColumn5 = new MFXTableColumn<>("Country", true, Comparator.comparing(PlayerRanking::getNation));
        MFXTableColumn<PlayerRanking> wtaColumn6 = new MFXTableColumn<>("Dominant Hand", true, Comparator.comparing(PlayerRanking::getDominantHand));

        System.out.println();

        wtaColumn1.setRowCellFactory(match -> new MFXTableRowCell<>(PlayerRanking::getFullName));
        wtaColumn2.setRowCellFactory(match->new MFXTableRowCell<>(PlayerRanking::getPlayerRank));
        wtaColumn3.setRowCellFactory(match->new MFXTableRowCell<>(PlayerRanking::getRankDate));
        wtaColumn4.setRowCellFactory(match->new MFXTableRowCell<>(PlayerRanking::getRankingPoints));
        wtaColumn5.setRowCellFactory(match->new MFXTableRowCell<>(PlayerRanking::getNation));
        wtaColumn6.setRowCellFactory(match->new MFXTableRowCell<>(PlayerRanking::getDominantHand)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        wtaColumn6.setAlignment(Pos.CENTER_RIGHT);

        wtaPlayerTable.getTableColumns().addAll(wtaColumn1,wtaColumn2,wtaColumn3,wtaColumn4,wtaColumn5,wtaColumn6 );

        wtaPlayerTable.getFilters().addAll(
                new StringFilter<>("Name", PlayerRanking::getFullName),
                new StringFilter<>("Rank",PlayerRanking::getPlayerRank),
                new StringFilter<>("Rank Date",PlayerRanking::getRankDate),
                new StringFilter<>("Rank Points",PlayerRanking::getRankingPoints),
                new StringFilter<>("Country",PlayerRanking::getNation),
                new StringFilter<>("Dominant Hand",PlayerRanking::getDominantHand));

        ObservableList<PlayerRanking> observablePlayer = FXCollections.observableArrayList();
        observablePlayer =  PlayerDao.observableWtaPlayer();
        wtaPlayerTable.setItems(observablePlayer);
    }

    public void handleWtaPlayers(ActionEvent event) {
    }
}
