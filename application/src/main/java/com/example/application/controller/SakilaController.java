package com.example.application.controller;
import com.example.application.util.Tools;
import com.example.dao.ActorDao;
import com.example.database.sakila_database.model.Table.Actor;
import com.example.database.sakila_database.model.Tables;
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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

public class SakilaController<T> implements Initializable {

    @FXML public MFXButton searchButton;

    @FXML public MFXFilterComboBox<T> ActorComboBox;

    @FXML public MFXFilterComboBox<T> FilteredActorComboBox;

    @FXML public MFXDatePicker datePicker;

    @FXML public MFXTableView<T> sakilaTableView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tournamentFilter();
        setupTable();

    }

    private void tournamentFilter() {
        StringConverter<T> converter =
                FunctionalStringConverter.to(actor->(actor == null) ? "" : actor.toString());

        Function<String, Predicate<T>> filterFunction = s -> e -> {
            return StringUtils.containsIgnoreCase(converter.toString(e), s);
        };
        ObservableList<T> observableTournament = FXCollections.observableArrayList();


        ActorDao actorDao = new ActorDao();

        actorDao.fetchByActorId(1L);

        FilteredActorComboBox.setItems(observableTournament);
        FilteredActorComboBox.setConverter(converter);
        FilteredActorComboBox.setFilterFunction(filterFunction);
        //use case combo box with atp and wta champions from each year

        ObservableList<T> pointByPointObservable = FXCollections.observableArrayList();

        StringConverter<T> pointByPointConverter =
                FunctionalStringConverter.to(a->(a == null) ?  "" : a.toString());

        Function<String, Predicate<T>> pointByPointFilterFunction = s -> e -> {
            return StringUtils.containsIgnoreCase(pointByPointConverter.toString(e), s);
        };

        ActorComboBox.setItems(pointByPointObservable);
        ActorComboBox.setConverter(pointByPointConverter);
        ActorComboBox.setFilterFunction(pointByPointFilterFunction);
        ActorComboBox.setResetOnPopupHidden(false);

        Tools.document(ActorComboBox.getLayoutBounds());

    }
    private void setupTable() {

        MFXTableColumn<T> c1=new MFXTableColumn<>("Tournament Name",true, Comparator.comparing(T::toString));
        MFXTableColumn<T> c2=new MFXTableColumn<>("Year",true,Comparator.comparing(T::toString));
        MFXTableColumn<T> c3=new MFXTableColumn<>("Match Num",true,Comparator.comparing(T::toString));
        MFXTableColumn<T> c4=new MFXTableColumn<>("Round",true,Comparator.comparing(T::toString));
        MFXTableColumn<T> c5=new MFXTableColumn<>("Player 1",true,Comparator.comparing(T::toString));
        MFXTableColumn<T> c6=new MFXTableColumn<>("Player 2",true,Comparator.comparing(T::toString));
        MFXTableColumn<T> c7=new MFXTableColumn<>("Court Name",true,Comparator.comparing(T::toString));
        MFXTableColumn<T> c8=new MFXTableColumn<>("Event Name",true,Comparator.comparing(T::toString));
        MFXTableColumn<T> c9=new MFXTableColumn<T>("Winner",true,Comparator.comparing(T::toString));

        System.out.println();

        c1.setRowCellFactory(match->new MFXTableRowCell<>(Object::toString));
        c2.setRowCellFactory(match->new MFXTableRowCell<>(Object::toString));
        c3.setRowCellFactory(match->new MFXTableRowCell<>(Object::toString));
        c4.setRowCellFactory(match->new MFXTableRowCell<>(Object::toString));
        c5.setRowCellFactory(match->new MFXTableRowCell<>(Object::toString));
        c6.setRowCellFactory(match->new MFXTableRowCell<>(Object::toString));
        c7.setRowCellFactory(match->new MFXTableRowCell<>(Object::toString));
        c8.setRowCellFactory(match->new MFXTableRowCell<>(Object::toString));
        c9.setRowCellFactory(match->new MFXTableRowCell<>(Object::toString)
        {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        c9.setAlignment(Pos.CENTER_RIGHT);

        sakilaTableView.getTableColumns().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9);
        sakilaTableView.getFilters().addAll(
                new StringFilter<>("Tournament Name",Object::toString),
                new StringFilter<>("Year",Object::toString),
                new StringFilter<>("Match Num",Object::toString),
                new StringFilter<>("Round", Object::toString),
                new StringFilter<>("Player 1",Object::toString),
                new StringFilter<>("Player 2",Object::toString),
                new StringFilter<>("Court Name",Object::toString),
                new StringFilter<>("Event Name", Object::toString),
                new StringFilter<>("Winner", Object::toString));

        ObservableList<Object> observableGrandSlams = FXCollections.observableArrayList();

        assert equals(observableGrandSlams.size() > 0);


        observableGrandSlams = null;
    }
    public void handleTextEntry(ActionEvent event) {
        String dateInput = datePicker.getText();
        System.out.println(dateInput);

    }

}
