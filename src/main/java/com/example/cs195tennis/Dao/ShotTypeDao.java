package com.example.cs195tennis.Dao;

import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.MatchStats;
import com.example.cs195tennis.model.ShotType;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShotTypeDao {

    static String shotTypeCsv = "C:\\Users\\seost\\cs195TennisAnalytics\\cs195-TennisTracker\\src\\main\\resources\\com\\example\\cs195tennis\\charting-w-stats-ShotTypes.csv";


    public static List<ShotType> readShotTypesCsv() throws FileNotFoundException {

        List<ShotType> shotTypeList = new ArrayList<>();

        List<List<String>> shotTypesCsvList = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(shotTypeCsv));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                shotTypesCsvList.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        shotTypesCsvList.forEach(row -> {

            shotTypeList.add(
                    new ShotType(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),
                            row.get(5), row.get(6),row.get(7),row.get(8),row.get(9),row.get(10)
                    ));}
        );
        ;;
        return shotTypeList;
    }
}
