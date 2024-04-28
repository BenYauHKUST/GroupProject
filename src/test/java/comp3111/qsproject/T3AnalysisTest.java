package comp3111.qsproject;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class T3AnalysisTest {
    @Test
    void T3Analysis() {
        QSList.initialize();
        String topValue = "1";
        String bottomValue = "11";
        String type = "Public";
        String region = "Asia";
        String country = "All";
        T3Analysis object = new T3Analysis(topValue, bottomValue, type, region, country);
        ObservableList<RecommendItem> data = object.getRecommendData();
        String expected_name = "National University of Singapore (NUS)";
        String actual_name = data.getFirst().name;
        assertEquals(expected_name, actual_name);
    }
}