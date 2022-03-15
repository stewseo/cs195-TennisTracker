//package com.test.viewercontrollertest;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListCell;
//import javafx.scene.layout.GridPane;
//import org.kordamp.ikonli.javafx.FontIcon;
//
//import java.io.IOException;
//
//public class TournamentListViewCell extends ListCell<TournamentStats> {
//    @FXML
//    private Label label1, label2;
//    @FXML
//    private GridPane gridPane;
//    private FXMLLoader mLLoader;
//    @FXML private FontIcon tournamentIcon;
//
//
//    protected void updateItem(TournamentStats stats, boolean empty) {
//        super.updateItem(stats, empty);
//
//        if (empty || stats == null) {
//            setText(null);
//            setGraphic(null);
//        } else {
//            if (mLLoader == null) {
//                mLLoader = new FXMLLoader(getClass().getResource("TournamentListCellViewer.fxml"));
//                mLLoader.setController(this);
//
//                try {
//                    mLLoader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
////            label1.setText(String.valueOf(stats.getTournamentId()));
////            label2.setText(stats.getTournamentName());
//
//            if (stats.getCourt().equals(TournamentStats.COURT.CLAY)) {
//                tournamentIcon.setIconLiteral("fas-football-ball");
//            }
//            else if(stats.getCourt().equals(TournamentStats.COURT.GRASS)) {
//                tournamentIcon.setIconLiteral("fab-hooli");
//            }else {
//                tournamentIcon.setIconLiteral("fab-aviato");
//            }
//            setText(null);
//            setGraphic(gridPane);
//        }
//    }
//}
