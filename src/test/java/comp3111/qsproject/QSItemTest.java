package comp3111.qsproject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * To test the whole QSItem class.
 */
class QSItemTest {
    public QSItem item;
    QSItemTest() {
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
        item = new QSItem(input);
    }
    @Test
    void getRank() {
        String expected = "1";
        String actual = item.getRank();
        assertEquals(expected, actual);
    }
    @Test
    void getName() {
        String expected = "Massachusetts Institute of Technology (MIT)";
        String actual = item.getName();
        assertEquals(expected, actual);
    }
    @Test
    void getScore() {
        String expected = "100";
        String actual = item.getScore();
        assertEquals(expected, actual);
    }
    @Test
    void getCountry() {
        String expected = "United States";
        String actual = item.getCountry();
        assertEquals(expected, actual);
    }
    @Test
    void getCity() {
        String expected = "Cambridge";
        String actual = item.getCity();
        assertEquals(expected, actual);
    }
    @Test
    void getType() {
        String expected = "Private";
        String actual = item.getType();
        assertEquals(expected, actual);
    }
    @Test
    void getSize() {
        String expected = "M";
        String actual = item.getSize();
        assertEquals(expected, actual);
    }
    @Test
    void getRegion() {
        String expected = "North America";
        String actual = item.getRegion();
        assertEquals(expected, actual);
    }
    @Test
    void getResearchOutput() {
        String expected = "Very high";
        String actual = item.getResearchOutput();
        assertEquals(expected, actual);
    }
    @Test
    void getStudentFacultyRatio() {
        String expected = "4";
        String actual = item.getStudentFacultyRatio();
        assertEquals(expected, actual);
    }
    @Test
    void getInternationalStudents() {
        String expected = "3,730";
        String actual = item.getInternationalStudents();
        assertEquals(expected, actual);
    }
    @Test
    void getFacultyCount() {
        String expected = "3,065";
        String actual = item.getFacultyCount();
        assertEquals(expected, actual);
    }

}