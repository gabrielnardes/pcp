package forecasting;

import forecasting.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    @Test
    @DisplayName("Testing print() method")
    public void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        data.print();

        String expectedOutput  = "\nDATA\n" +
                "d[1]: 24,000\n" +
                "d[2]: 26,000\n" +
                "d[3]: 22,000\n" +
                "d[4]: 25,000\n" +
                "d[5]: 19,000\n" +
                "d[6]: 31,000\n" +
                "d[7]: 26,000\n" +
                "d[8]: 18,000\n" +
                "d[9]: 29,000\n" +
                "d[10]: 24,000\n" +
                "d[11]: 30,000\n" +
                "d[12]: 23,000\n";

        assertEquals(expectedOutput, outContent
                        .toString()
                        .replaceAll("\r", ""),
                "Console output must be equal");
    }

    @Test
    @DisplayName("Testing toString() method")
    public void testToString() {
        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);

        String expectedOutput  = "Data{data=[24.0, 26.0, 22.0, 25.0, 19.0, 31.0, 26.0, 18.0, 29.0, 24.0, 30.0, 23.0]}";

        assertEquals(expectedOutput, data.toString()
                        .replaceAll("\r", ""),
                "Strings must be equal");
    }
}