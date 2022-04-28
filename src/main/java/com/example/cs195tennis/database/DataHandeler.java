package com.example.cs195tennis.database;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class DataHandeler {

    DataHandeler(){}

    private static void readCsvToDb() throws FileNotFoundException {

    List<List<String>> records = new ArrayList<List<String>>();

    try (CSVReader csvReader = new CSVReader(new FileReader("current-rankings.csv"));) {

        String[] values = null;

        while ((values = csvReader.readNext()) != null) {

            records.add(Arrays.asList(values));
        }
    } catch (CsvValidationException | IOException e) {
        e.printStackTrace();
    }
    }


}
