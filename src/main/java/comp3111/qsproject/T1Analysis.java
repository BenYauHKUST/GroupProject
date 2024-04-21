package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.*;

public class T1Analysis {
    public ObservableList<QSItem> tableList = FXCollections.observableArrayList();
    T1Analysis (String year) {
        /*
            Your Code Here.
            Collect the QSItem with corresponding years into tableList.
            Use static properties in QSList here.
            Hint: QSList.list is a static property.
         */

        for(QSItem item : QSList.list){
            if(item.year.equals(year)){
                tableList.add(item);
            }
        }
    }

    ObservableList<QSItem> getTableList() {
        return tableList;
    }

    ObservableList<PieChart.Data> getPieChartData(String searchName) {
        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList();
        /*
            Your Code Here.
            Return the Pie Chart Data.
            Pie Chart shows the SUM of the score.
            For example, when the user chooses "size", which means the searchName will be "size"
            And Return an ObservableList with PieChart.Data
            [
                key: "L", value: the Sum score of the Large size universities,
                key: "M", value: the Sum score of the Middle size universities,
                key: "S", value: the Sum score of the Small size universities,
            ]
         */

        HashMap<String, Integer> sumScores = new HashMap<>();

        // Loop through the items in QSList.list
        for (QSItem item : tableList) {
            String key = "";

            // Get the category value based on the searchName
            switch (searchName) {
                case "size":
                    key = item.getSize();
                    break;
                case "type":
                    key = item.getType();
                    break;
                case "country":
                    key = item.getCountry();
                    break;
                case "researchOutput":
                    key = item.getResearchOutput();
                    break;
                case "region":
                    key = item.getRegion();
                    break;
            }

            // Update the sum of scores for the category
            if (sumScores.containsKey(key)) {
                sumScores.put(key, sumScores.get(key) + 1);
            } else {
                sumScores.put(key, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : sumScores.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey() + " " + entry.getValue(), entry.getValue()));
        }

        return pieChartData;
    }

    XYChart.Series<String, Double> getBarChartData(String searchName) {
        XYChart.Series<String, Double> barData= new XYChart.Series<>();


        HashMap<String, Double> TableScores = new HashMap<>();

        HashMap<String, Integer> CountMap = new HashMap<>();

        for (QSItem item : tableList) {

            String key = "";

            // Get the category value based on the searchName
            switch (searchName) {
                case "size":
                    key = item.getSize();
                    break;
                case "type":
                    key = item.getType();
                    break;
                case "country":
                    key = item.getCountry();
                    break;
                case "researchOutput":
                    key = item.getResearchOutput();
                    break;
                case "region":
                    key = item.getRegion();
                    break;
            }

            if(key.isEmpty()){
                continue;
            }

            // Update the sum of scores for the category
            if (TableScores.containsKey(key) && !item.getScore().isEmpty()) {
                Double newValue = TableScores.get(key) + Double.parseDouble(item.getScore());
                TableScores.put(key, newValue);
                CountMap.put(key, CountMap.get(key) + 1);
            } else if (TableScores.containsKey(key) && item.getScore().isEmpty()) {
                Double newValue = TableScores.get(key);
                TableScores.put(key, newValue);
                CountMap.put(key, CountMap.get(key) + 1);
            } else if (!TableScores.containsKey(key) && !item.getScore().isEmpty()){
                TableScores.put(key, Double.parseDouble(item.getScore()));
                CountMap.put(key, 1);
            }else{
                TableScores.put(key, 0.0);
                CountMap.put(key, 1);
            }
        }

        for (Map.Entry<String, Double> entry : TableScores.entrySet()) {
            String label = entry.getKey();
            Integer count = 0;

            for (Map.Entry<String, Integer> CountEntry : CountMap.entrySet()){
                if(CountEntry.getKey().equals(label)){
                    count = CountEntry.getValue();
                }
            }

            barData.getData().add(new XYChart.Data<String, Double>(label, (entry.getValue()/count)));
        }

        return barData;
    }


}
