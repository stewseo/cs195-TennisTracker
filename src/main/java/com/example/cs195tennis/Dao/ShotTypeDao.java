package com.example.cs195tennis.Dao;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.ShotType;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ShotTypeDao {

    static String shotTypeCsv = "C:\\Users\\seost\\cs195TennisAnalytics\\cs195-TennisTracker\\src\\main\\resources\\com\\example\\cs195tennis\\charting-w-stats-ShotTypes.csv";
    static List<ShotType> shotDirectionList = new ArrayList<>();
    static List<ShotType> shotDirOutcomeList = new ArrayList<>();


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

      shotTypesCsvList.forEach(System.out::println);
      return shotTypeList;
    }
    static String matchCsv = "C:\\Users\\seost\\cs195TennisAnalytics\\cs195-TennisTracker\\src\\main\\resources\\com\\example\\cs195tennis\\atp_matches_2022.csv";

    static String shotDirOutcomeCsv = "C:\\Users\\seost\\cs195TennisAnalytics\\cs195-TennisTracker\\src\\main\\resources\\com\\example\\cs195tennis\\charting-m-stats-ShotDirOutcomes.csv";

    public static Map<String,List<ShotType>> getShotDirOutcomeList() throws IOException, CsvValidationException {

        List<List<String>> shotDirOutcomeCsvList = new ArrayList<List<String>>();

        Map<String,List<ShotType>> map = new HashMap<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(shotDirOutcomeCsv));) {
            String[] values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                shotDirOutcomeCsvList.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        List<List<String>> otherKeys = new ArrayList<List<String>>();

        CSVReader csvReader1 = new CSVReader(new FileReader(matchCsv));

        String[] matchVals = csvReader1.readNext();

        while((matchVals = csvReader1.readNext()) != null) {
            otherKeys.add(Arrays.asList(matchVals));
        }

        Map<String, List<List<String>>> allMatchIds = new HashMap<String, List<List<String>>>();

        System.out.println(allMatchIds.size());

        otherKeys.forEach(r-> {

            allMatchIds.computeIfAbsent(r.get(0), k->new ArrayList<>());

            allMatchIds.get(r.get(0)).add(r);
        });

        shotDirOutcomeCsvList.forEach(row -> {

            map.computeIfAbsent(row.get(0), k->new ArrayList<>());

            map.get(row.get(0)).add(new ShotType(row));

            map.get(row.get(0)).add(new ShotType(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),
                            row.get(5), row.get(6),row.get(7),row.get(8),row.get(9)
            ));}
        );;;
//        map.forEach((k,v)-> System.out.println("k: " + k + "\nv: " + v.size()));
        return map;
    }

    static String shotDirectionCsv = "C:\\Users\\seost\\cs195TennisAnalytics\\cs195-TennisTracker\\src\\main\\resources\\com\\example\\cs195tennis\\charting-w-stats-ShotDirection.csv";

    public static List<ShotType> getShotDirectionList() throws IOException, CsvValidationException {

        List<List<String>> shotDirectionCsvList = new ArrayList<List<String>>();

        List<List<String>> otherKeys = new ArrayList<List<String>>();

        CSVReader csvReader1 = new CSVReader(new FileReader(matchCsv));

        String[] matchVals = csvReader1.readNext();


        try (CSVReader csvReader = new CSVReader(new FileReader(shotDirectionCsv));) {

            String[] values = null;

            while ((values = csvReader.readNext()) != null) {
                shotDirectionCsvList.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        String[] shotDirectionkeys;

        shotDirectionCsvList.forEach(row -> {


//            System.out.println(shotDirectionkey);

            shotDirectionList.add(
                    new ShotType(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),
                            row.get(5), row.get(6),row.get(7)
                    ));}
        );;;
        System.out.println(shotDirectionList.size());
        return shotDirOutcomeList;
    }
}
