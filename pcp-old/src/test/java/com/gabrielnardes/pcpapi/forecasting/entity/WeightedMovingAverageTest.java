package com.gabrielnardes.pcpapi.forecasting.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightedMovingAverageTest {
    @Test
    @DisplayName("Testing calc() method")
    void testCalc() {
        double[] data = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        WeightedMovingAverage wma = new WeightedMovingAverage(data, 3,0.5, 0.3, 0.2);
        double[] wmaTest = {24.20, 24.60, 22.30, 24.40, 24.00, 26.90, 24.20, 22.50, 27.70, 25.60};

        wma.calc();

        assertArrayEquals(wma.getWma(), wmaTest, 0.01, "WMA arrays must be equal");
    }

    @Test
    @DisplayName("Testing getSma() method")
    public void testGetSma() {
        double[] data = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        WeightedMovingAverage wma = new WeightedMovingAverage(data, 3,0.5, 0.3, 0.2);
        wma.calc();

        double[] wmaTest = wma.getWma();

        assertArrayEquals(wma.getWma(), wmaTest, 0.01, "getWma() must retrieve the correct SMA array");
    }

    @Test
    @DisplayName("Testing weight sum check")
    void testWeightSum() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        double[] data = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        WeightedMovingAverage wma = new WeightedMovingAverage(data, 3,0.9, 0.9, 0.9);
        wma.calc();

        String expectedOutput  = "Weight sum not equal to 1\n";

        assertEquals(expectedOutput, outContent
                        .toString()
                        .replaceAll("\r", ""),
                "Console output must be equal");
    }
}
