package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.WtaPlayerDao;
import com.example.cs195tennis.model.PlayerRanking;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
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

public class WtaTourController implements Initializable {

    @FXML public MFXTableView<PlayerRanking> playerRankingTable;

    ObservableList<PlayerRanking> playerRankObservable;

    @FXML public MFXTextField testWtaPlayer;

    @FXML Label handleWtaPlayers;
    @FXML MFXDatePicker handleWtaDate;

    @FXML MFXFilterComboBox<PlayerRanking> filterPlayerRecentRanks;
    @FXML MFXFilterComboBox<PlayerRanking> filterAllTimeRanks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() throws SQLException {
        StringConverter<PlayerRanking> converter = FunctionalStringConverter.to(playerRank -> (playerRank == null) ? "" : playerRank.getFirstName() + " " + playerRank.getLastName());
        Function<String, Predicate<PlayerRanking>> filterFunction = s -> playerRank -> StringUtils.containsIgnoreCase(converter.toString(playerRank), s);

        String input = handleWtaPlayers.getText();
        playerRankObservable = WtaPlayerDao.allTimeWtaRankings("Select * from WTATournament ");

        System.out.println(WtaPlayerDao.allTimeWtaRankings("Select * from WTATournament "));
        filterPlayerRecentRanks.setItems(playerRankObservable);
        filterPlayerRecentRanks.setConverter(converter);
        filterPlayerRecentRanks.setFilterFunction(filterFunction);

        filterAllTimeRanks.setItems(playerRankObservable);
        filterAllTimeRanks.setConverter(converter);
        filterAllTimeRanks.setFilterFunction(filterFunction);


        MFXTableColumn<PlayerRanking> playerNameColumn = new MFXTableColumn<>("first_name", true, Comparator.comparing(PlayerRanking::getFirstName));
        MFXTableColumn<PlayerRanking> playerRankColumn = new MFXTableColumn<>("rank", true, Comparator.comparing(PlayerRanking::getPlayerWTARanking));
        MFXTableColumn<PlayerRanking> playerRankPtsCol = new MFXTableColumn<>("points", true, Comparator.comparing(PlayerRanking::getPoints));
        MFXTableColumn<PlayerRanking> playerDobCol = new MFXTableColumn<>("dob", true, Comparator.comparing(PlayerRanking::getRank));
        MFXTableColumn<PlayerRanking> playerRankColumn = new MFXTableColumn<>("rank", true, Comparator.comparing(WtaPlayer::getRanking));
        MFXTableColumn<PlayerRanking> playerLocCol = new MFXTableColumn<>("player_ioc", true, Comparator.comparing(WtaPlayer::getIoc));

        playerNameColumn.setRowCellFactory(player -> new MFXTableRowCell<>(PlayerRanking::getFirstName));
        playerRankColumn.setRowCellFactory(match -> new MFXTableRowCell<>(PlayerRanking::getPlayerWTARanking));
        playerRankPtsCol.setRowCellFactory(match -> new MFXTableRowCell<>(PlayerRanking::getPoints)
        playerLocCol.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getDob));
        playerRankColumn.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getRank));
        playerHeightCol.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getRank)

        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        playerRankColumn.setAlignment(Pos.CENTER_RIGHT);

        playerRankingTable.getTableColumns().addAll(playerNameColumn,playerRankColumn,playerRankPtsCol);
        playerRankingTable.getFilters().addAll(
                new StringFilter<>("first_name", PlayerRanking::getFirstName),
                new StringFilter<>("rank", PlayerRanking::getPlayerWTARanking),
              new StringFilter<>("points", PlayerRanking::getPlayerWTARanking)
                new StringFilter<>("Date of Birth", PlayerRanking::getDob),
                new StringFilter<>("Country", PlayerRanking::getRanking),
                new StringFilter<>("Current Rank", PlayerRanking::getIoc)
        );
        playerRankingTable.setItems(playerRankObservable);
    }

    public void handleWtaPlayers(ActionEvent event) {
    }
}



