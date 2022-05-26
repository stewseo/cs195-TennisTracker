package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.WtaPlayerDao;
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

public class PlayerRankingController implements Initializable {

    @FXML public MFXDatePicker custDatePicker;
    @FXML public MFXFilterComboBox<WtaPlayer> filterCombo;
    @FXML public MFXFilterComboBox<WtaPlayer> custFilterCombo;

    @FXML public Label validateAtpPlayer;

    @FXML public MFXTableView<WtaPlayer> wtaPlayer;
    public MFXTextField textAtpPlayer;


    private void grandSlamFilters() {
        StringConverter<WtaPlayer> converter = FunctionalStringConverter.to(e->(e== null) ? "" : e.getPlayerRank() +" " + e.getFullName() );
        StringConverter<WtaPlayer> converterFields = FunctionalStringConverter.to(e->(e== null) ? "" : e.getPlayerRank() +" " + e.getFullName() );

        Function<String, Predicate<WtaPlayer>> filterFunction = s -> e -> {return StringUtils.containsIgnoreCase(converter.toString(e), s);};

        ObservableList<WtaPlayer> observablePlayerRank = FXCollections.observableArrayList();

        observablePlayerRank = WtaPlayerDao.populateWtaPlayerRanks();

        filterCombo.setItems(observablePlayerRank);
        filterCombo.setConverter(converter);
        filterCombo.setFilterFunction(filterFunction);

        custFilterCombo.setItems(observablePlayerRank);
        custFilterCombo.setConverter(converter);
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

        MFXTableColumn<WtaPlayer> wtacoler = new MFXTableColumn<>("Name", true, Comparator.comparing(WtaPlayer::getFirstName));
        MFXTableColumn<WtaPlayer> wtaCol1 = new MFXTableColumn<>("Date", true, Comparator.comparing(WtaPlayer::getRankDate));
        MFXTableColumn<WtaPlayer> wtaCol2 = new MFXTableColumn<>("Rank", true, Comparator.comparing(WtaPlayer::getPlayerRank));
        MFXTableColumn<WtaPlayer> wtaCpl3 = new MFXTableColumn<>("Rank Points", true, Comparator.comparing(WtaPlayer::getRankingPoints));
        MFXTableColumn<WtaPlayer> wtaCol4 = new MFXTableColumn<>("Country", true, Comparator.comparing(WtaPlayer::getIoc));
        MFXTableColumn<WtaPlayer> wtaCol5 = new MFXTableColumn<>("Dominant Hand", true, Comparator.comparing(WtaPlayer::getDominantHand));

        System.out.println();

        wtacoler.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getFirstName));
        wtaCol1.setRowCellFactory(match->new MFXTableRowCell<>(WtaPlayer::getRankDate));
        wtaCol2.setRowCellFactory(match->new MFXTableRowCell<>(WtaPlayer::getPlayerRank));
        wtaCpl3.setRowCellFactory(match->new MFXTableRowCell<>(WtaPlayer::getRankingPoints));
        wtaCol4.setRowCellFactory(match->new MFXTableRowCell<>(WtaPlayer::getIoc));
        wtaCol5.setRowCellFactory(match->new MFXTableRowCell<>(WtaPlayer::getDominantHand)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        wtaCol5.setAlignment(Pos.CENTER_RIGHT);

        wtaPlayer.getTableColumns().addAll(wtacoler,wtaCol1,wtaCol2,wtaCpl3,wtaCol4,wtaCol5 );

        wtaPlayer.getFilters().addAll(
                new StringFilter<>("Name", WtaPlayer::getFirstName),
                new StringFilter<>("DATE",WtaPlayer::getRankDate),
                        new StringFilter<>("Rank",WtaPlayer::getPlayerRank),
                        new StringFilter<>("Rank Point",WtaPlayer::getRankingPoints),
                        new StringFilter<>("Country",WtaPlayer::getIoc),
                        new StringFilter<>("Dominant Hand",WtaPlayer::getDominantHand));


        ObservableList<WtaPlayer> observableWtaPlayer = FXCollections.observableArrayList();
        observableWtaPlayer = WtaPlayerDao.populateWtaPlayerRanks();
        wtaPlayer.setItems(observableWtaPlayer);
    }

    public void handleAtpPlayer(ActionEvent event) {
    }
}