package comp3111.qsproject;

import com.csvreader.CsvReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
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

    public static void initialize() {
        try {
            String csvFilePath = "C:/Users/85298/OneDrive - HKUST Connect/Desktop/Courses/COMP 3111/Project/qs.csv";
            CsvReader reader = new CsvReader(csvFilePath,  ',', Charset.forName("UTF-8"));
            reader.readHeaders();
            Set<String> universitySet = new HashSet<>();
            Set<String> typeSet = new HashSet<>();
            Set<String> regionSet = new HashSet<>();
            Set<String> countrySet = new HashSet<>();
            while (reader.readRecord()) {
                String[] row = reader.getValues();
                QSItem qsItem = new QSItem(row);
                list.add(qsItem);
                universitySet.add(qsItem.getName());
                typeSet.add(qsItem.getType());
                regionSet.add(row[7]);
                countrySet.add(qsItem.getCountry());
            }
            university.addAll(universitySet);
            type.addAll(typeSet);
            region.addAll(regionSet);
            country.addAll(countrySet);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
