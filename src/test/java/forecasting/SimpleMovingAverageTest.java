package forecasting;

import data.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMovingAverageTest {
    @Test
    @DisplayName("Testing calc() method")
    public void testCalc() {
        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        SimpleMovingAverage sma = new SimpleMovingAverage(data, 2);
        double[] smaTest = {25.00, 24.00, 23.50, 22.00, 25.00, 28.50, 22.00, 23.50, 26.50, 27.00, 26.50};

        sma.calc();

        for (int i = 0; i < smaTest.length; i++) {
            assertEquals(sma.get(i), smaTest[i], "SMA arrays must be equal");
        }
    }

    @Test
    @DisplayName("Testing get() method")
    public void testGet() {
        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        SimpleMovingAverage sma = new SimpleMovingAverage(data, 2);
        double[] smaTest = {25.00, 24.00, 23.50, 22.00, 25.00, 28.50, 22.00, 23.50, 26.50, 27.00, 26.50};

        sma.calc();

        assertEquals(sma.get(5), 28.50, "get() must retrieve the correct SMA element");
    }

    @Test
    @DisplayName("Testing getSma() method")
    public void testGetSma() {
        Data data = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        SimpleMovingAverage sma = new SimpleMovingAverage(data, 2);
        double[] smaTest = sma.getSma();

        sma.calc();

        for (int i = 0; i < smaTest.length; i++) {
            assertEquals(sma.get(i), smaTest[i], "getSma() must retrieve the correct SMA array");
        }
    }

    @Test
    @DisplayName("Testing print() method")
    public void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Data d = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        SimpleMovingAverage sma = new SimpleMovingAverage(d, 2);
        sma.calc();
        sma.print();

        String expectedOutput  = "SMA with period 2\n" +
                "P[2]: 25,00\n" +
                "P[3]: 24,00\n" +
                "P[4]: 23,50\n" +
                "P[5]: 22,00\n" +
                "P[6]: 25,00\n" +
                "P[7]: 28,50\n" +
                "P[8]: 22,00\n" +
                "P[9]: 23,50\n" +
                "P[10]: 26,50\n" +
                "P[11]: 27,00\n" +
                "P[12]: 26,50\n";

        assertEquals(expectedOutput, outContent
                                    .toString()
                                    .replaceAll("\r", ""),
                            "Console output must be equal");
    }
}