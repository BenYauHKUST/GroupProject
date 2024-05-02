package comp3111.qsproject;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * To test the initialize function
 */
class QSListTest {
    @Test
    void initialize() {
        String [] expected = {"Private","Public",""};
        String [] actual = new String[QSList.type.size()];
        for (int i = 0; i < QSList.type.size(); i++) {
            actual[i] = QSList.type.get(i);
        }
        Arrays.sort(expected);
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }
}