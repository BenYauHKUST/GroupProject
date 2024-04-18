package comp3111.qsproject;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class QSListTest {
    @Test
    void initialize() {
        QSList temp = new QSList();
        temp.initialize();
        String [] expected = {"Private","Public",""};
        String [] actual = new String[temp.type.size()];
        for (int i = 0; i < temp.type.size(); i++) {
            actual[i] = temp.type.get(i);
        }
        Arrays.sort(expected);
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }
}