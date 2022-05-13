package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.AtpPlayerDao;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.model.AtpPlayer;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.PlayerRanking;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AtpPlayerController implements Initializable {


    static ObservableList<AtpPlayer> playerObservable = FXCollections.observableArrayList();
    static ObservableList<PlayerRanking> playerRankObservable = FXCollections.observableArrayList();
    public MFXFilterComboBox<AtpPlayer> filterCombo;
    public MFXFilterComboBox<AtpPlayer> custFilterCombo;
    public MFXTextField textAtpPlayer;
    public MFXDatePicker atpPlayerDate;

    @FXML MFXTableView<AtpPlayer> table;

    @FXML Label atpPlayerValidation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        atpPlayerDate.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", atpPlayerDate.getLocale()));

        playerObservable = AtpPlayerDao.getAtpObservablePlayer();

        try {
            playerRankObservable = AtpPlayerDao.getAtpRanking();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        StringConverter<AtpPlayer> playerConverter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getFirstName() + " " + e.getLastName());

        Function<String, Predicate<AtpPlayer>> filterPlayer = s -> e -> {
            return StringUtils.containsIgnoreCase(playerConverter.toString(e), s);
        };

        filterCombo.setItems(playerObservable);
        filterCombo.setConverter(playerConverter);
        filterCombo.setFilterFunction(filterPlayer);

        custFilterCombo.setItems(playerObservable);
        custFilterCombo.setConverter(playerConverter);
        custFilterCombo.setFilterFunction(filterPlayer);
        custFilterCombo.setResetOnPopupHidden(false);
    }


    private void setupTable() throws SQLException {

        MFXTableColumn<AtpPlayer> playerFirstNameColumn = new MFXTableColumn<>("name_first", true, Comparator.comparing(AtpPlayer::getFirstName));
        MFXTableColumn<AtpPlayer> playerDominantHand = new MFXTableColumn<>("player_ioc", true, Comparator.comparing(AtpPlayer::getHand));
        MFXTableColumn<AtpPlayer> playerHeightColumn = new MFXTableColumn<>("height", true, Comparator.comparing(AtpPlayer::getHeight));

        playerFirstNameColumn.setRowCellFactory(player -> new MFXTableRowCell<>(AtpPlayer::getFirstName));
        playerHeightColumn.setRowCellFactory(player -> new MFXTableRowCell<>(AtpPlayer::getHeight));
        playerDominantHand.setRowCellFactory(player -> new MFXTableRowCell<>(AtpPlayer::getDob) {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        playerDominantHand.setAlignment(Pos.CENTER_RIGHT);

        table.getTableColumns().addAll(playerFirstNameColumn, playerDominantHand, playerHeightColumn);
        table.getFilters().addAll(
                new StringFilter<>("name_first", AtpPlayer::getFirstName),
                new StringFilter<>("hand", AtpPlayer::getHand),
                new StringFilter<>("height", AtpPlayer::getIoc)
        );

        System.out.println(playerObservable.size());

        AtpPlayerDao.getAtpObservablePlayer();

        table.setItems(playerObservable);
    }

    public void handleAtpPlayer(ActionEvent event) {
    }

    static String atpMatches2022 = "C:\\Users\\seost\\tennis_atp\\atp_matches_";
    static String atpPlayers = "C:\\Users\\seost\\tennis_atp\\atp_players.csv";
    static String grandPrix = "C:\\Users\\seost\\Downloads\\tennis_slam_pointbypoint-master\\tennis_slam_pointbypoint-master\\";

    public static void insert(String csv) throws SQLException, IOException, CsvValidationException {

        List<File> filesInFolder = Files.walk(Paths.get(grandPrix))
                .filter(Files::isRegularFile)
                .map(Path::toFile).collect(Collectors.toList());

        filesInFolder.forEach(System.out::println);

        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();


        List<String[]> csvList = new ArrayList<>();

        String[] values = null;

        int i = 0;



//        DataHandeler.createTable("GrandSlamTournament", allMatchesCsv.get(0));
//
//        DataHandeler.create("GrandSlamTournament", (List<String[]>) csvList);

    }
}



