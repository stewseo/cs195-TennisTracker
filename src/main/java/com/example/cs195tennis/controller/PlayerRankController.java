//package com.example.cs195tennis.controller;
//
//import com.example.cs195tennis.Dao.AtpPlayerDao;
//import com.example.cs195tennis.model.AtpPlayer;
//import com.example.cs195tennis.model.PlayerRanking;
//import io.github.palexdev.materialfx.controls.*;
//import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
//import io.github.palexdev.materialfx.filter.StringFilter;
//import io.github.palexdev.materialfx.utils.DateTimeUtils;
//import io.github.palexdev.materialfx.utils.StringUtils;
//import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
//import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.util.StringConverter;
//
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.*;
//import java.util.function.Function;
//import java.util.function.Predicate;
//
//public class PlayerRankController implements Initializable {
//
//    public MFXFilterComboBox<AtpPlayer> filterCombo;
//    public MFXFilterComboBox<AtpPlayer> custFilterCombo;
//
//    public MFXTextField textAtpPlayer;
//    public MFXDatePicker atpPlayerDate;
//
//    @FXML MFXTableView<AtpPlayer> table;
//
//    @FXML Label atpPlayerValidation;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        atpPlayerDate.setGridAlgorithm(DateTimeUtils::partialIntMonthMatrix);
//        atpPlayerDate.setConverterSupplier(() -> new DateStringConverter("dd/MM/yyyy", atpPlayerDate.getLocale()));
//        try {
//            setupTable();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        ObservableList<AtpPlayer> playerObservable = FXCollections.observableArrayList();
//        ObservableList<PlayerRanking> playerRankObservable = FXCollections.observableArrayList();
//
//
//        try {
////            playerRankObservable = AtpPlayerDao.getAtpRanking();
////            playerObservable = AtpPlayerDao.getAtpObservablePlayer();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Observable lists null");
//        }
//
//
//        StringConverter<AtpPlayer> playerConverter = FunctionalStringConverter.to(e -> (e == null) ? "" : e.getFirstName() + " " + e.getLastName());
//
//        Function<String, Predicate<AtpPlayer>> filterPlayer = s -> e -> {
//            return StringUtils.containsIgnoreCase(playerConverter.toString(e), s);
//        };
//
////        playerObservable.forEach(System.out::println);
//        filterCombo.setItems(playerObservable);
//        filterCombo.setConverter(playerConverter);
//        filterCombo.setFilterFunction(filterPlayer);
//
//        custFilterCombo.setItems(playerObservable);
//        custFilterCombo.setConverter(playerConverter);
//        custFilterCombo.setFilterFunction(filterPlayer);
//        custFilterCombo.setResetOnPopupHidden(false);
//    }
//    String column1, column2, column3;
//    String c1Value, c2Value, c3Value;
//    private void setupTable() throws SQLException {
//
//        MFXTableColumn<AtpPlayer> column1 = new MFXTableColumn<>(c1Value, true, Comparator.comparing(AtpPlayer::getFullName));
//        MFXTableColumn<AtpPlayer> column2 = new MFXTableColumn<>(c2Value, true, Comparator.comparing(AtpPlayer::getHand));
//        MFXTableColumn<AtpPlayer> column3 = new MFXTableColumn<>(c3Value, true, Comparator.comparing(AtpPlayer::getHeight));
////        MFXTableColumn<AtpPlayer> playerDominantHand = new MFXTableColumn<>("player_ioc", true, Comparator.comparing(AtpPlayer::getHand));
//
//        column1.setRowCellFactory(player -> new MFXTableRowCell<>(AtpPlayer::getFullName));
//        column2.setRowCellFactory(player -> new MFXTableRowCell<>(AtpPlayer::getHand));
//        column3.setRowCellFactory(player -> new MFXTableRowCell<>(AtpPlayer::getHeight) {{
////        playerDominantHand.setRowCellFactory(player -> new MFXTableRowCell<>(AtpPlayer::getDob) {{
//            setAlignment(Pos.CENTER_RIGHT);
//        }});
//        column3.setAlignment(Pos.CENTER_RIGHT);
//
//        table.getTableColumns().addAll(column1, column2, column3);
//        table.getFilters().addAll(
//                new StringFilter<>(c1Value, AtpPlayer::getFullName),
//                new StringFilter<>(c2Value, AtpPlayer::getHand),
//                new StringFilter<>(c3Value, AtpPlayer::getHeight)
////                new StringFilter<>("height", AtpPlayer::getIoc)
//        );
//
////        System.out.println(playerObservable.size());
//
//
//        ObservableList<AtpPlayer> playerObservable = FXCollections.observableArrayList();
////        playerObservable = AtpPlayerDao.getAtpObservablePlayer();
//
////        table.setItems(playerObservable);
//    }
//
//    public void handleAtpPlayer(ActionEvent event) {
//    }
//
//
//
//}
//
//
//
