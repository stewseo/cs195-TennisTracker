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

    ObservableList<WtaPlayer> wtaPlayerObservable;


    public MFXTextField testWtaPlayer;

    @FXML Label handleWtaPlayers;
    @FXML MFXDatePicker handleWtaDate;

    @FXML MFXFilterComboBox<WtaPlayer> wtaFilerComboBoxCustom;
    @FXML MFXFilterComboBox<WtaPlayer> wtaFilerComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


        MFXTableColumn<WtaPlayer> playerNameColumn = new MFXTableColumn<>("name_first", true, Comparator.comparing(WtaPlayer::getFullName));
        MFXTableColumn<WtaPlayer> playerHeightCol = new MFXTableColumn<>("name_last", true, Comparator.comparing(WtaPlayer::getHeight));
        MFXTableColumn<WtaPlayer> playerHandCol = new MFXTableColumn<>("Hand", true, Comparator.comparing(WtaPlayer::getHand));
        MFXTableColumn<WtaPlayer> playerDobCol = new MFXTableColumn<>("dob", true, Comparator.comparing(WtaPlayer::getDob));
//        MFXTableColumn<WtaPlayer> playerRankColumn = new MFXTableColumn<>("rank", true, Comparator.comparing(WtaPlayer::getRanking));
        MFXTableColumn<WtaPlayer> playerLocCol = new MFXTableColumn<>("player_ioc", true, Comparator.comparing(WtaPlayer::getIoc));

        playerNameColumn.setRowCellFactory(player -> new MFXTableRowCell<>(WtaPlayer::getFullName));
        playerHeightCol.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getHeight)
//        playerDobCol.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getHeight));
//        playerLocCol.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getDob));
//        playerRankColumn.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getRank));
//        playerHeightCol.setRowCellFactory(match -> new MFXTableRowCell<>(WtaPlayer::getRank)

        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        playerHeightCol.setAlignment(Pos.CENTER_RIGHT);

        wtaPlayerTable.getTableColumns().addAll(playerNameColumn,playerHeightCol);
        wtaPlayerTable.getFilters().addAll(
                new StringFilter<>("Name", WtaPlayer::getFullName),
                new StringFilter<>("Name", WtaPlayer::getHeight)
//                new StringFilter<>("Dominant Hand", WtaPlayer::getHand),
//                new StringFilter<>("Date of Birth", WtaPlayer::getDob),
//                new StringFilter<>("Country", WtaPlayer::getRanking),
//                new StringFilter<>("Current Rank", WtaPlayer::getIoc)
        );
        wtaPlayerTable.setItems(wtaPlayerObservable);
    }


    public void handleWtaPlayers(ActionEvent event) {
    }
}



