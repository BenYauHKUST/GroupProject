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

public class T22Analysis {
    public ObservableList<QSItem> CountryRegion1List = FXCollections.observableArrayList();
    public ObservableList<QSItem> CountryRegion2List = FXCollections.observableArrayList();
    private String CountryRegion1Name;
    private String CountryRegion2Name;

    /**
     * @param country_region_1 The University 1 to search.
     * @param country_region_2 The University 2 to search.
     * @param years The years for which collect data.
     */

    T22Analysis(String country_region_1, String country_region_2, List<String> years) {
        /*
            Your Code Here.
            Collect the QSItem with corresponding years and country/region into two country/region lists.
            Sort country/region lists by the years.
            Hint: QSList.list is a static property.
         */
        CountryRegion1Name = country_region_1;
        CountryRegion2Name = country_region_2;

        //Collect QSItem
        for (QSItem item : QSList.list) {
            if (years.contains(item.year)) {
                if (item.country.equals(country_region_1) || item.region.equals(country_region_1)) {
                    CountryRegion1List.add(item);
                }
                if (item.country.equals(country_region_2) || item.region.equals(country_region_2)) {
                    CountryRegion2List.add(item);
                }

            }
        }
        // Sort CountryRegion1List and CountryRegion2List based on year
        CountryRegion1List.sort(Comparator.comparing(item -> item.year));
        CountryRegion2List.sort(Comparator.comparing(item -> item.year));
    }

    /**
     * @param searchName type to search
     * @return BarChartData for given country/regions and years
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
              Average score of country/region2, "Country/Region 2"
              Average score of country/region1, "Country/Region 1"
            ]

            There are some "dirty data" in csv.
            For example, the string "3,143" or "3.143" can not transfer to Integer or Double directly.
            Careful process these data.
         */
        //Calculating Avg of Country/Region 2
        double total = 0.0;
        int count = 0;
        for (QSItem item : CountryRegion2List) {
            double val = parseProp(item.getProperty(searchName));
            if (val!=-1) {
                total+=val;
                count++;
            }
        }
        barData.getData().add(new XYChart.Data<>(total/count, "Country/Region 2"));

        //Calculating Avg of Country/Region 1
        total = 0.0;
        count = 0;
        for (QSItem item : CountryRegion1List) {
            double val = parseProp(item.getProperty(searchName));
            if (val!=-1) {
                total+=val;
                count++;
            }
        }
        barData.getData().add(new XYChart.Data<>(total/count, "Country/Region 1"));

        return barData;
    }

    /**
     * @param searchName type to search
     * @return LineChartData for given country/regions and years
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
        // Create series for Country/Region 2
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        series1.setName(CountryRegion2Name);
        fillData(series1, CountryRegion2List, searchName);
        lineData.add(series1);

        // Create series for Country/Region 1
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();
        series2.setName(CountryRegion1Name);
        fillData(series2, CountryRegion1List, searchName);
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
