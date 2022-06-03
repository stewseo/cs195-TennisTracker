package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.model.*;
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
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class AtpTourController implements Initializable {

    @FXML public MFXDatePicker custDatePicker;
    @FXML public MFXFilterComboBox<PlayerRanking> filterCombo;
    @FXML public MFXFilterComboBox<PlayerRanking> custFilterCombo;

    @FXML public Label validateAtpPlayer;

    @FXML public MFXTableView<PlayerRanking> playerTable;
    @FXML public MFXTextField textAtpPlayer;

    private void grandSlamFilters() {
        StringConverter<PlayerRanking> converter = FunctionalStringConverter.to(e->(e== null) ? "" : e.getFullName() +" " + e.getPlayerRank() );
        StringConverter<PlayerRanking> converterFields = FunctionalStringConverter.to(e->(e== null) ? "" : e.getFullName() +" " + e.getRankingPoints());

        Function<String, Predicate<PlayerRanking>> filterFunction = s -> e -> {return StringUtils.containsIgnoreCase(converter.toString(e), s);};

        ObservableList<PlayerRanking> observableAtp = FXCollections.observableArrayList();

        observableAtp = PlayerDao.observableAtpPlayer();

        filterCombo.setItems(observableAtp);
        filterCombo.setConverter(converter);
        filterCombo.setFilterFunction(filterFunction);
        //atp 250, 500, 1000, championships in the filter boxes
        custFilterCombo.setItems(observableAtp);
        custFilterCombo.setConverter(converterFields);
        custFilterCombo.setFilterFunction(filterFunction);
        custFilterCombo.setResetOnPopupHidden(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        custDatePicker.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
        custDatePicker.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", custDatePicker.getLocale()));

        grandSlamFilters();
        try {
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void setupTable() throws SQLException {

        MFXTableColumn<PlayerRanking> wtacoler = new MFXTableColumn<>("Name", true, Comparator.comparing(PlayerRanking::getFullName));
        MFXTableColumn<PlayerRanking> wtaCol1 = new MFXTableColumn<>("Rank", true, Comparator.comparing(PlayerRanking::getPlayerRank));
        MFXTableColumn<PlayerRanking> wtaCol2 = new MFXTableColumn<>("Rank Date", true, Comparator.comparing(PlayerRanking::getRankDate));
        MFXTableColumn<PlayerRanking> wtaCpl3 = new MFXTableColumn<>("Rank Points", true, Comparator.comparing(PlayerRanking::getRankingPoints));
        MFXTableColumn<PlayerRanking> wtaCol4 = new MFXTableColumn<>("Country", true, Comparator.comparing(PlayerRanking::getNation));
        MFXTableColumn<PlayerRanking> wtaCol5 = new MFXTableColumn<>("Dominant Hand", true, Comparator.comparing(PlayerRanking::getDominantHand));

        System.out.println();

        wtacoler.setRowCellFactory(match -> new MFXTableRowCell<>(PlayerRanking::getFullName));
        wtaCol1.setRowCellFactory(match->new MFXTableRowCell<>(PlayerRanking::getPlayerRank));
        wtaCol2.setRowCellFactory(match->new MFXTableRowCell<>(PlayerRanking::getRankDate));
        wtaCpl3.setRowCellFactory(match->new MFXTableRowCell<>(PlayerRanking::getRankingPoints));
        wtaCol4.setRowCellFactory(match->new MFXTableRowCell<>(PlayerRanking::getNation));
        wtaCol5.setRowCellFactory(match->new MFXTableRowCell<>(PlayerRanking::getDominantHand)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        wtaCol5.setAlignment(Pos.CENTER_RIGHT);

        playerTable.getTableColumns().addAll(wtacoler,wtaCol1,wtaCol2,wtaCpl3,wtaCol4,wtaCol5 );

        playerTable.getFilters().addAll(
                new StringFilter<>("Name", PlayerRanking::getFullName),
                new StringFilter<>("Rank",PlayerRanking::getPlayerRank),
                new StringFilter<>("Rank Date",PlayerRanking::getRankDate),
                new StringFilter<>("Rank Points",PlayerRanking::getRankingPoints),
                new StringFilter<>("Country",PlayerRanking::getNation),
                new StringFilter<>("Dominant Hand",PlayerRanking::getDominantHand));


        ObservableList<PlayerRanking> observableAtp = FXCollections.observableArrayList();
        observableAtp =  PlayerDao.observableAtpPlayer();
        playerTable.setItems(observableAtp);
    }

    public void handleAtpPlayer(ActionEvent event) {
    }

    public void AtpPlayersText(ActionEvent event) {
    }

    public void handleTournamentText(ActionEvent event) {
    }
}