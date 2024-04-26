package comp3111.qsproject;

import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class T21AnalysisTest {
    void test_initializer() {
        // Initialize QSList with some test data
        QSList.list.clear();
        QSList.list.add(new QSItem(new String[]{
                "University ABC", // name
                "2022",           // year
                "1",              // rank
                "99.9",           // score
                "",
                "Country X",      // country
                "City Y",         // city
                "Region Z",       // region
                "",
                "Public",         // type
                "Very High",      // researchOutput
                "15:1",           // studentFacultyRatio
                "5000",           // internationalStudents
                "Large",          // size
                "1000"            // facultyCount
        }));

        QSList.list.add(new QSItem(new String[]{
                "University DEF", // name
                "2022",           // year
                "2",              // rank
                "98.5",           // score
                "",
                "Country Y",      // country
                "City Z",         // city
                "Region A",       // region
                "",
                "Private",        // type
                "High",           // researchOutput
                "20:1",           // studentFacultyRatio
                "3000",           // internationalStudents
                "Medium",         // size
                "800"             // facultyCount
        }));

        QSList.list.add(new QSItem(new String[]{
                "University GHI", // name
                "2024",           // year
                "3",              // rank
                "97.0",           // score
                "",
                "Country Z",      // country
                "City A",         // city
                "Region B",       // region
                "",
                "Private",        // type
                "Moderate",       // researchOutput
                "10:1",           // studentFacultyRatio
                "1000",           // internationalStudents
                "Small",          // size
                "500"             // facultyCount
        }));
    }
    @Test
    void T21Analysis() {
        test_initializer();
        String university1 = "University GHI";
        String university2 = "University ABC";
        List<String> selected_years = new ArrayList<>();
        selected_years.add("2022");
        selected_years.add("2024");
        T21Analysis object = new T21Analysis(university1, university2, selected_years);
        String expected = "97.0";
        String actual = object.University1List.getFirst().score;
        assertEquals(expected, actual);
    }
    @Test
    void getBarChartData() {
        test_initializer();
        String university1 = "University GHI";
        String university2 = "University ABC";
        List<String> selected_years = new ArrayList<>();
        selected_years.add("2022");
        selected_years.add("2024");
        T21Analysis object = new T21Analysis(university1, university2, selected_years);
        XYChart.Series<Double, String> barData = object.getBarChartData("internationalStudents");
        XYChart.Data<Double, String> data = barData.getData().getFirst();
        double expected = 5000;
        double actual = data.getXValue();
        assertEquals(expected, actual);
    }
    @Test
    void getLineChartData() {
        test_initializer();
        String university1 = "University GHI";
        String university2 = "University ABC";
        List<String> selected_years = new ArrayList<>();
        selected_years.add("2022");
        selected_years.add("2024");
        T21Analysis object = new T21Analysis(university1, university2, selected_years);
        List<XYChart.Series<String, Double>> lineData = object.getLineChartData("score");
        XYChart.Data<String, Double> data = lineData.getFirst().getData().getFirst();
        double expected = 99.9;
        double actual = data.getYValue();
        assertEquals(expected, actual);
    }
}
