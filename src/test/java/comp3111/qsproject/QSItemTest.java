package comp3111.qsproject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QSItemTest {
    @Test
    void getRank() {
        String [] input = {
                "Massachusetts Institute of Technology (MIT)" ,
                "2017",
                "1",
                "100",
                "https://www.topuniversities.com/universities/massachusetts-institute-technology-mit",
                "United States",
                "Cambridge",
                "North America",
                "https://www.topuniversities.com/sites/default/files/massachusetts-institute-of-technology-mit_410_small.jpg",
                "Private",
                "Very high",
                "4",
                "3,730",
                "M",
                "3,065"
        };
        QSItem item = new QSItem(input);
        String expected = "1";
        String actual = item.getRank();
        assertEquals(expected, actual);
    }
}