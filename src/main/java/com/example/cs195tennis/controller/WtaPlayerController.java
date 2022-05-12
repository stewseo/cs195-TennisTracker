package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.MatchDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.Dao.WtaDao;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.model.*;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class WtaPlayerController implements Initializable {

    @FXML public MFXTableView<Player> wtaPlayerTable;

    static public ObservableList<Player> wtaPlayerObservable = FXCollections.observableArrayList();

    private static Map<String, List<Player>> playerMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            playerMap = PlayerDao.getPlayerMap();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(playerMap.size());

        playerMap.forEach((k, v) -> v.forEach(e ->
                wtaPlayerObservable.add(new Player(e.getId(), e.getFirstName(), e.getLastName(), e.getHand(), e.getDob(), e.getIoc(),
                        e.getHeight(), e.getRank()
                ))));
        setupTable();
    }

    private void setupTable() {
        MFXTableColumn<Player> playerNameCol = new MFXTableColumn<>("Name", true, Comparator.comparing(Player::getFullName));
        MFXTableColumn<Player> playerHandCol = new MFXTableColumn<>("Dominant Hand", true, Comparator.comparing(Player::getHeight));
        MFXTableColumn<Player> playerDobCol = new MFXTableColumn<>("Date of Birth", true, Comparator.comparing(Player::getDob));
        MFXTableColumn<Player> playerLocCol = new MFXTableColumn<>("Country", true, Comparator.comparing(Player::getIoc));
        MFXTableColumn<Player> playerHeightCol = new MFXTableColumn<>("Height", true, Comparator.comparing(Player::getHeight));
        MFXTableColumn<Player> playerRank = new MFXTableColumn<>("Current Rank", true, Comparator.comparing(Player::getRank));

        playerNameCol.setRowCellFactory(player -> new MFXTableRowCell<>(Player::getFullName));
        playerHandCol.setRowCellFactory(match -> new MFXTableRowCell<>(Player::getHeight));
        playerDobCol.setRowCellFactory(match -> new MFXTableRowCell<>(Player::getDob));
        playerLocCol.setRowCellFactory(match -> new MFXTableRowCell<>(Player::getIoc));
        playerHeightCol.setRowCellFactory(match -> new MFXTableRowCell<>(Player::getHeight));
        playerRank.setRowCellFactory(match -> new MFXTableRowCell<>(Player::getRank)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        playerRank.setAlignment(Pos.CENTER_RIGHT);

        wtaPlayerTable.getTableColumns().addAll(playerNameCol, playerHandCol, playerDobCol, playerLocCol, playerHeightCol, playerRank);
        wtaPlayerTable.getFilters().addAll(
                new StringFilter<>("Name", Player::getFullName),
                new StringFilter<>("Dominant Hand", Player::getHeight),
                new StringFilter<>("Date of Birth", Player::getDob),
                new StringFilter<>("Country", Player::getIoc),
                new StringFilter<>("Height", Player::getHeight),
                new StringFilter<>("Current Rank", Player::getRank)
        );
        wtaPlayerTable.setItems(wtaPlayerObservable);
    }

    public void changeColors(ActionEvent event) {
    }

    public void handleSearchPlayer(ActionEvent event) {
    }
}



