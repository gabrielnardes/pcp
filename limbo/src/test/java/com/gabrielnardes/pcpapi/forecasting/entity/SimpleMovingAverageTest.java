package com.gabrielnardes.pcpapi.forecasting.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleMovingAverageTest {
    @Test
    @DisplayName("Testing calc() method")
    public void testCalc() {
        double[] data = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        SimpleMovingAverage sma = new SimpleMovingAverage(data, 2);
        double[] smaTest = {25.00, 24.00, 23.50, 22.00, 25.00, 28.50, 22.00, 23.50, 26.50, 27.00, 26.50};

        sma.calc();

        assertArrayEquals(smaTest, sma.getSma(), 0.001, "SMA arrays must be equal");
    }

    @Test
    @DisplayName("Testing get() method")
    public void testGet() {
        double[] data = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        SimpleMovingAverage sma = new SimpleMovingAverage(data, 2);

        sma.calc();

        assertEquals(sma.get(5), 28.50, "get() must retrieve the correct SMA element");
    }

    @Test
    @DisplayName("Testing getSma() method")
    public void testGetSma() {
        double[] data = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        SimpleMovingAverage sma = new SimpleMovingAverage(data, 2);
        sma.calc();
        double[] smaTest = sma.getSma();

        assertArrayEquals(smaTest, sma.getSma(), 0.001, "getSma() must retrieve the correct SMA array");
    }
}