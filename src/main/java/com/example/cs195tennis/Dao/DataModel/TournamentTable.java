package com.example.cs195tennis.Dao.DataModel;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.CustomField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;

import java.beans.ConstructorProperties;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.jooq.impl.DSL.*;

import static org.jooq.impl.SQLDataType.VARCHAR;


public class TournamentTable extends CustomTable<TournamentRecord> {

    public static final TournamentTable TOURNAMENT = new TournamentTable();

    private static DSLContext ctx() {
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
    }


    public TournamentTable() {

        super(name("GrandSlam"));
    }

    @Override
    @SuppressWarnings({"all", "unchecked", "rawtypes"})
    public Class<? extends TournamentRecord> getRecordType() {
        return TournamentRecord.class;
    }

//    public static Field<String> toChar(Field<?> field, String format) {
//        return CustomField.of("to_char", VARCHAR, ctx -> {
//        });
//    }

//    void tempTableFields() {
//        Table<Record> TMP = DSL.table(DSL.name("TempGrandSlam"));
//        Field<String> TYPE = DSL.field(DSL.name("SelectedFields", "type"), String.class);
//        Field<String> TOKEN = DSL.field(DSL.name("TempToken", "token"), String.class);
//    }

    public ObservableList<Tournament> createGrandSlamHistory() {

        ObservableList<Tournament> tournamentModelList = FXCollections.observableArrayList();
        return tournamentModelList;
    }

}