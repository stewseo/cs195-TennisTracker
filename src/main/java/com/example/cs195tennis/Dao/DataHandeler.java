package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.jooq.*;
import org.jooq.impl.DSL;

import javax.naming.InvalidNameException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Record;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.jooq.impl.DSL.*;

//
public class DataHandeler {

    static DSLContext ctx(){
        return using(Database.connect(), SQLDialect.SQLITE);
    }

    private List<Object> getTable(String tableName) {
        return ctx().select(table(tableName).fields()).stream().collect(Collectors.toList());
    }

    // Parse Csv rows and insert to table.
    public void csvToListString(List<String> getFilesFromFolder, String tableName, String[] fields) throws IOException {

        getFilesFromFolder.forEach(e->{
            List<List<String>> recordList = new ArrayList<>();
            try {
                recordList =  parseCsvToListString(e);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
//            insertRank(recordList);

        });
        System.out.println("done");
    }
    // add / remove columns from table
    public void updateColumn(String tableName, String[] fields, boolean addColumn) {

        if(!addColumn) {
            Arrays.stream(fields).forEach(field -> {
                ctx().alterTable(table(tableName)).addColumn(field(field)).execute();
            });
        }else {
            Arrays.stream(fields).forEach(field -> {
                ctx().alterTable(table(tableName)).drop(field(field)).execute();
            });
        }
    }

    // insert Atp / Wta Player and Rank Csv -> List -> AtpPlayerRanking/AtpPlayer WtaPlayer/WtaPlyerRank2010_2022
    public void insertRank(String getFilesFromFolder, boolean insertRank) throws IOException {
        List<List<String>> temp = parseCsvToListString(getFilesFromFolder);

        Loader<org.jooq.Record> rs = ctx().loadInto(table("Playerdd"))
                .loadCSV(getFilesFromFolder)
                .fields(field("WtaPlayerRank.ranking_date"), field("WtaPlayerRank.rank"), field("WtaPlayerRank.player"),field("WtaPlayerRank.ranking_points")).execute();


        if(insertRank) {
            temp.forEach(col -> {
                var tours = "";
                var ranking_date = col.get(0);
                var rank = col.get(1);
                var player = col.get(2);
                var points = col.get(3);
                ctx().insertInto(table("AtpRankings"), field("ranking_date"), field("rank"), field("player"), field("points"))
                        .values(ranking_date, rank, player, points).execute();
            });
        }
        else {
            temp.forEach(col-> {

                String playerId = col.get(0);
                String firstName = col.get(1);
                String lastName = col.get(2);
                String hand = col.get(3);
                String dateOfBirth = col.get(4);
                String location = col.get(5) == null ? "" : col.get(5);
                String height = col.get(6) == null ? "" : col.get(6);
                String wikiId = col.get(7) == null ? "" : col.get(7);

                ctx().insertInto(table("WtaPlayers"), field("player_id"), field("name_first"), field("name_last"), field("hand"), field("dob"),
                                field("ioc"),field("height"),field("wikidata_id"))
                        .values(playerId, firstName, lastName, hand, dateOfBirth, location,height,wikiId).execute();
            });

        }
        System.out.println("records in table: " + ctx().fetchCount(table("AtpPlayerRanking")));
    }

    // delete temp / unused tables
    public void clearTable() {
//        ctx().delete(table("AtpPlayer")).execute();
//        ctx().delete(table("PLAYER_RANKS")).execute();
//        ctx().delete(table("GrandSlams2011_2022")).execute();
//        ctx().delete(table("PLAYER")).execute();
//        ctx().delete(table("PLAYERS")).execute();
//        ctx().delete(table("PLAYER_RANKS")).execute();
//        ctx().delete(table("TOURNAMENT")).execute();
    }

    // print all tables in the database
    // print all fields in each table
    // print number of items per column
    void printTableFields() throws IOException {

        ctx().meta().getTables().forEach(table->{
            System.out.println(ctx().meta().getTables(table.getName()));
            System.out.println(ctx().meta().getTables(table.getName()).get(0).fields().length);
        });

        List<Table<?>> r = ctx().meta().getTables();

        IntStream.range(0, r.size()).forEach(e->{

            System.out.println("table: " + e);

            if(ctx().fetchCount(DSL.selectFrom(r.get(e))) >= 0){

                System.out.println(ctx().fetchCount(DSL.selectFrom(r.get(e))));

                System.out.println("table " + r.get(e).fieldsRow());
            }
        });

    }


    // return file path of all files in param directory
    List<String> getFilesFromFolder(String directory) throws IOException {
        return Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .map(Path::toString).toList();
    }
    // convert each csv row to list of Strings
    public List<List<String>> parseCsvToListString(String path) throws IOException {
        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
            String[] values = null;
            List<String> columns = new ArrayList<>();
            while ((values = csvReader.readNext()) != null) {
                allMatchesCsv.add(Arrays.asList(values));
            }
    } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return allMatchesCsv;
    }
    // create new table in master table
    private void createTable(String tableName, List<String> columns) throws SQLException, InvalidNameException {
        if(tableName == null || tableName.length() == 0){
            throw new InvalidNameException("Invalid");
        }

        ctx().createTableIfNotExists(table(tableName,columns)).execute();
    }

}










