package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.*;

/**
 * A class to search, compare and visualize data.
 * @author <a href=mailto:fsam@connect.ust.hk>SAM FETULLAKH</a>
 * @version 1.0
 */

public class T21Analysis {
    public ObservableList<QSItem> University1List = FXCollections.observableArrayList();
    public ObservableList<QSItem> University2List = FXCollections.observableArrayList();
    private String University1Name;
    private String University2Name;

    /**
     * @param uni_1 The University 1 to search.
     * @param uni_2 The University 2 to search.
     * @param years The years for which collect data.
     */
    T21Analysis(String uni_1, String uni_2, List<String> years) {
        /*
            Your Code Here.
            Collect the QSItem with corresponding years and university into two university lists.
            Sort university lists by the years.
            Hint: QSList.list is a static property.
         */
        University1Name = uni_1;
        University2Name = uni_2;

        //Collect QSItem
        for (QSItem item : QSList.list) {
            if (years.contains(item.year)) {
                if (item.name.equals(uni_1)) {
                    University1List.add(item);
                }
                if (item.name.equals(uni_2)) {
                    University2List.add(item);
                }

            }
        }
        // Sort University1List and University2List based on year
        University1List.sort(Comparator.comparing(item -> item.year));
        University2List.sort(Comparator.comparing(item -> item.year));
    }

    /**
     * @param searchName type to search
     * @return BarChartData for given universities and years
     */

    XYChart.Series<Double, String> getBarChartData(String searchName) {
        XYChart.Series<Double, String> barData= new XYChart.Series<>();
        /*
            Your Code Here.
            Return the Bar Chart Data.
            Bar Chart shows the Avg. of the selected property.
            For example, when the user chooses "score", which means the searchName will be "score"
            And Return an XYChart.Series with XYChart.Data
            [
              Average score of university2, "University 2"
              Average score of university1, "University 1"
            ]

            There are some "dirty data" in csv.
            For example, the string "3,143" or "3.143" can not transfer to Integer or Double directly.
            Careful process these data.
         */
        //Calculating Avg of University 2
        double total = 0.0;
        int count = 0;
        for (QSItem item : University2List) {
            double val = parseProp(item.getProperty(searchName));
            if (val!=-1) {
                total+=val;
                count++;
            }
        }
        barData.getData().add(new XYChart.Data<>(total/count, "University 2"));

        //Calculating Avg of University 1
        total = 0.0;
        count = 0;
        for (QSItem item : University1List) {
            double val = parseProp(item.getProperty(searchName));
            if (val!=-1) {
                total+=val;
                count++;
            }
        }
        barData.getData().add(new XYChart.Data<>(total/count, "University 1"));

        return barData;
    }

    /**
     * @param searchName type to search
     * @return LineChartData for given universities and years
     */

    List<XYChart.Series<String, Double>> getLineChartData(String searchName) {
        List<XYChart.Series<String, Double>> lineData = new ArrayList<>();
        /*
            Your Code Here.
            Fill the lineData1 and lineData2.
            Line Chart shows two lines. Each line shows the number of searchName each year.
            In our cases, the searchName will be "score"
            And Return an XYChart.Series with XYChart.Data
            [
              Series[Data<year,score>],
            ]

            There are some "dirty data" in csv.
            For example, the string "3,143" or "3.143" can not transfer to Integer or Double directly.
            Careful process these data.
         */
        // Create series for University 2
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        series1.setName(University2Name);
        fillData(series1, University2List, searchName);
        lineData.add(series1);

        // Create series for University 1
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();
        series2.setName(University1Name);
        fillData(series2, University1List, searchName);
        lineData.add(series2);

        return lineData;
    }

    /**
     * @param series Chart series to fill with data
     * @param itemList QSItems to collect data about
     * @param searchName type to search
     */

    private void fillData(XYChart.Series<String, Double> series, ObservableList<QSItem> itemList, String searchName) {
        String currentYear = null;
        double total = 0;
        int count = 0;

        //Collect data
        for (QSItem item : itemList) {
            if (currentYear == null || !currentYear.equals(item.year)) {
                if (currentYear != null) {
                    series.getData().add(new XYChart.Data<>(currentYear, total / count));
                }
                currentYear = item.year;
                total = 0;
                count = 0;
            }
            double val = parseProp(item.getProperty(searchName));
            if (val != -1) {
                total += val;
                count++;
            }
        }

        // Add the last data point
        if (currentYear != null) {
            series.getData().add(new XYChart.Data<>(currentYear, total / count));
        }
    }

    /**
     * @param prop string value which should be parsed
     * @return value of parsed string
     */
    private double parseProp(String prop) {
        //Assumption: strings with more than 3 digits are integer values greater than 1000
        try{
            //If there are no "," or "."
            return Integer.parseInt(prop);
        } catch (NumberFormatException e) {
            //If there are "," or "." but it represents numbers greater than 1000
            String rep_prop = prop.replace(",", "");
            rep_prop = rep_prop.replace(".", "");
            int num = Integer.parseInt(rep_prop);
            if (num < 1000) {
                //If there is "." and it represents double
                return Double.parseDouble(prop);
            }
            return num;
        }
    }
}
