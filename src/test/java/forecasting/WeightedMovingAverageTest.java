package forecasting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class WeightedMovingAverageTest {
    @Test
    @DisplayName("Testing calc() method")
    void testCalc() {
        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        WeightedMovingAverage wma = new WeightedMovingAverage(data, 3,0.5, 0.3, 0.2);
        double[] wmaTest = {24.20, 24.60, 22.30, 24.40, 24.00, 26.90, 24.20, 22.50, 27.70, 25.60};

        wma.calc();

        for (int i = 0; i < wmaTest.length; i++) {
            assertEquals(wma.get(i), wmaTest[i], 0.01, "WMA arrays must be equal");
        }
    }

    @Test
    @DisplayName("Testing get() method")
    public void testGet() {
        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        WeightedMovingAverage wma = new WeightedMovingAverage(data, 3,0.5, 0.3, 0.2);

        wma.calc();

        assertEquals(wma.get(5), 26.90, 0.01, "get() must retrieve the correct SMA element");
    }

    @Test
    @DisplayName("Testing getSma() method")
    public void testGetSma() {
        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        WeightedMovingAverage wma = new WeightedMovingAverage(data, 3,0.5, 0.3, 0.2);
        double[] wmaTest = wma.getWma();

        wma.calc();

        for (int i = 0; i < wmaTest.length; i++) {
            assertEquals(wma.get(i), wmaTest[i], "getWma() must retrieve the correct SMA array");
        }
    }

    @Test
    @DisplayName("Testing print() method")
    public void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        WeightedMovingAverage wma = new WeightedMovingAverage(data, 3,0.5, 0.3, 0.2);
        wma.calc();
        wma.print();

        String expectedOutput  = "WMA with period 3\n" +
                "P[3]: 24,20\n" +
                "P[4]: 24,60\n" +
                "P[5]: 22,30\n" +
                "P[6]: 24,40\n" +
                "P[7]: 24,00\n" +
                "P[8]: 26,90\n" +
                "P[9]: 24,20\n" +
                "P[10]: 22,50\n" +
                "P[11]: 27,70\n" +
                "P[12]: 25,60\n";

        assertEquals(expectedOutput, outContent
                        .toString()
                        .replaceAll("\r", ""),
                "Console output must be equal");
    }

    @Test
    @DisplayName("Testing weight sum check")
    void testWeightSum() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        WeightedMovingAverage wma = new WeightedMovingAverage(data, 3,0.9, 0.9, 0.9);
        wma.calc();

        String expectedOutput  = "Weight sum not equal to 1\n";

        assertEquals(expectedOutput, outContent
                        .toString()
                        .replaceAll("\r", ""),
                "Console output must be equal");
    }
}
