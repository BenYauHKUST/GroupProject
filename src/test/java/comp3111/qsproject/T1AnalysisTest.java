package comp3111.qsproject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class T1AnalysisTest {

    @Test
    public void testConstructor() {
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

        T1Analysis analysis = new T1Analysis("2024");
        for(QSItem item : analysis.tableList){
            assertEquals("2024", item.year);
        }
        assertEquals(1, analysis.tableList.size());
    }


    @Test
    void getTableListTest2() {

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

        T1Analysis analysis = new T1Analysis("");
        assertEquals(0, analysis.tableList.size());
    }


}