package com.example.cs195tennis.ui;

import com.example.cs195tennis.database.DatabaseConnection;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.stage.Window;

public class Search<E> extends Window {
    DatabaseConnection dbCont;
    @FXML JFXComboBox<E> searchBox;

}
