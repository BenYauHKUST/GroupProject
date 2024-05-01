package comp3111.qsproject;

import com.csvreader.CsvReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

/**
 * This file is a container of collected QS data
 */

public class QSList {
    public static ObservableList<QSItem> list = FXCollections.observableArrayList();
    public static ObservableList<String> university = FXCollections.observableArrayList();
    public static ObservableList<String> type = FXCollections.observableArrayList();
    public static ObservableList<String> region = FXCollections.observableArrayList();
    public static ObservableList<String> country = FXCollections.observableArrayList();
    public static HashMap<String, ObservableList<String>> countryRegion = new HashMap<>();

    /**
     * To initialize the QSList container based on the qs dataset
     */
    public static void initialize() {
        try {
            String csvFilePath = "qs.csv";
            CsvReader reader = new CsvReader(csvFilePath,  ',', Charset.forName("UTF-8"));
            reader.readHeaders();
            Set<String> universitySet = new HashSet<>();
            Set<String> typeSet = new HashSet<>();
            Set<String> regionSet = new HashSet<>();
            Set<String> countrySet = new HashSet<>();
            HashMap<String, HashSet<String>> countryRegionSet = new HashMap<>();
            while (reader.readRecord()) {
                String[] row = reader.getValues();
                QSItem qsItem = new QSItem(row);
                list.add(qsItem);
                universitySet.add(qsItem.getName());
                typeSet.add(qsItem.getType());
                regionSet.add(row[7]);
                if (!countryRegionSet.containsKey(row[7])) {
                    countryRegionSet.put(row[7], new HashSet<>());
                }
                countrySet.add(qsItem.getCountry());
                countryRegionSet.get(row[7]).add(qsItem.getCountry());
            }
            university.addAll(universitySet);
            type.addAll(typeSet);
            region.addAll(regionSet);
            country.addAll(countrySet);
            for (String key : countryRegionSet.keySet()) {
                countryRegion.put(key, FXCollections.observableArrayList());
                countryRegion.get(key).addAll(countryRegionSet.get(key));
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To clear all the static data stored in QSList.
     */
    public static void clear() {
        list.clear();
        university.clear();
        type.clear();
        region.clear();
        country.clear();
        countryRegion.clear();
    }
}
