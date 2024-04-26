package comp3111.qsproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecommendItemTest {
    private RecommendItem recommendationItem;
    public void setup() {
        String[] input = {"Massachusetts Institute of Technology (MIT)", "2017", "1", "100", "https://www.topuniversities.com/universiti es/massachusetts- institute-technology-mit", "UnitedStates", "Cambridge", "NorthAmerica", "https://www.topuniversities.com/sites/ default/files/massachusetts-institute-of-technology- mit_410_small.jpg", "Private", "Very high", "4", "3,730", "M", "3,065"};
        QSItem qsItem = new QSItem(input);
        recommendationItem = new RecommendItem(qsItem);
}
        @Test
        void update() {
            setup();
            String[] input2 = {"Massachusetts Institute of Technology (MIT)", "2020", "3", "100", "https://www.topuniversities.com/universiti es/massachusetts- institute-technology-mit", "UnitedStates", "Cambridge", "NorthAmerica", "https://www.topuniversities.com/sites/ default/files/massachusetts-institute-of-technology- mit_410_small.jpg", "Private", "Very high", "4", "3,730", "M", "3,065"};
                QSItem qsItem2 = new QSItem(input2);
                recommendationItem.update(qsItem2);
                String expected = "2020";
                String actual =
                        recommendationItem.getRecentYear();
                assertEquals(expected, actual);
}
                @Test
                void getName() {
                setup();
                String expected = "Massachusetts Institute of Technology (MIT)";
                String actual = recommendationItem.getName();
                assertEquals(expected, actual);
            }
            @Test
            void getBestYear() {
                setup();
                String expected = "2017";
                String actual = recommendationItem.getBestYear();
                assertEquals(expected, actual);
            }
            @Test
            void getBestRank() {
                setup();
                String expected = "1";
                String actual = recommendationItem.getBestRank();
                assertEquals(expected, actual);
            }
            @Test
            void getRecentYear() {
                setup();
                String expected = "2017";
                String actual =
                        recommendationItem.getRecentYear();
                assertEquals(expected, actual);
            }
            @Test
            void getRecentRank() {
                setup();
                String expected = "1";
                String actual =
                        recommendationItem.getRecentRank();
                assertEquals(expected, actual);
            }
        }

