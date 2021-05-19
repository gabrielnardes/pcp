package data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {
    @Test
    @DisplayName("Testing get() method")
    public void testGet() {
        double[] d = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);

        for (int i = 0; i < data.length(); i++) {
            assertEquals(d[i], data.get(i),"Data array should be equal");
        }
    }

    @Test
    @DisplayName("Testing length() method")
    public void testLength() {
        Data data = new Data(24, 26, 22);

        assertEquals(data.length(), 3, "Length must be equal");
    }
}