package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.WtaPlayerDao;
import com.example.cs195tennis.model.WtaPlayer;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
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

public class WtaPlayerController implements Initializable {

    @FXML public MFXTableView<WtaPlayer> wtaPlayerTable;

    static public ObservableList<WtaPlayer> wtaPlayerObservable = FXCollections.observableArrayList();

    private static Map<String, List<WtaPlayer>> playerMap = new HashMap<>();
    public MFXTextField testWtaPlayer;

    @FXML Label handleWtaPlayers;
    @FXML MFXDatePicker handleWtaDate;

    @FXML MFXFilterComboBox<WtaPlayer> wtaFilerComboBoxCustom;
    @FXML MFXFilterComboBox<WtaPlayer> wtaFilerComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            playerMap = WtaPlayerDao.getPlayerMap();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(playerMap.size());

        playerMap.forEach((k, v) -> v.forEach(e ->
                wtaPlayerObservable.add(new WtaPlayer(e.getId(), e.getFirstName(), e.getLastName(), e.getHand(), e.getDob(), e.getIoc(),
                        e.getHeight(),e.getWiki()
                ))));

        try {
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() throws SQLException {
        StringConverter<WtaPlayer> converter = FunctionalStringConverter.to(wtaPlayer -> (wtaPlayer == null) ? "" : wtaPlayer.getFirstName() + " " + wtaPlayer.getLastName());
        Function<String, Predicate<WtaPlayer>> filterFunction = s -> wtaPlayer -> StringUtils.containsIgnoreCase(converter.toString(wtaPlayer), s);


        wtaPlayerObservable = WtaPlayerDao.readWtaPlayerToObservable();

        wtaFilerComboBox.setItems(wtaPlayerObservable);
        wtaFilerComboBox.setConverter(converter);
        wtaFilerComboBox.setFilterFunction(filterFunction);

        wtaFilerComboBoxCustom.setItems(wtaPlayerObservable);
        wtaFilerComboBoxCustom.setConverter(converter);
        wtaFilerComboBoxCustom.setFilterFunction(filterFunction);


        MFXTableColumn<WtaPlayer> playerNameCol = new MFXTableColumn<>("name_first", true, Comparator.comparing(WtaPlayer::getFirstName));
        MFXTableColumn<WtaPlayer> playerHeightCol = new MFXTableColumn<>("name_last", true, Comparator.comparing(WtaPlayer::getLastName));
        MFXTableColumn<WtaPlayer> playerHandCol = new MFXTableColumn<>("Hand", true, Comparator.comparing(WtaPlayer::getHand));
        MFXTableColumn<WtaPlayer> playerDobCol = new MFXTableColumn<>("dob", true, Comparator.comparing(WtaPlayer::getDob));
        MFXTableColumn<WtaPlayer> playerRankColumn = new MFXTableColumn<>("rank", true, Comparator.comparing(WtaPlayer::getRanking));
        MFXTableColumn<WtaPlayer> playerLocCol = new MFXTableColumn<>("player_ioc", true, Comparator.comparing(WtaPlayer::getIoc));

        playerNameCol.setRowCellFactory(player -> new MFXTableRowCell<>(WtaPlayer::getFirstName));
        playerHandCol.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getLastName));
        playerDobCol.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getHeight));
        playerLocCol.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getDob));
        playerRankColumn.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getRank));
        playerHeightCol.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getRank)

        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        playerRankColumn.setAlignment(Pos.CENTER_RIGHT);

        wtaPlayerTable.getTableColumns().addAll(playerNameCol, playerHandCol, playerDobCol, playerLocCol, playerRankColumn, playerHeightCol);
        wtaPlayerTable.getFilters().addAll(
                new StringFilter<>("Name", WtaPlayer::getFirstName),
                new StringFilter<>("Name", WtaPlayer::getLastName),
                new StringFilter<>("Dominant Hand", WtaPlayer::getHand),
                new StringFilter<>("Date of Birth", WtaPlayer::getDob),
                new StringFilter<>("Country", WtaPlayer::getRanking),
                new StringFilter<>("Current Rank", WtaPlayer::getIoc)
        );
        wtaPlayerTable.setItems(wtaPlayerObservable);
    }


    public void handleWtaPlayers(ActionEvent event) {
    }
}



