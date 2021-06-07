package com.gabrielnardes.pcpapi.forecasting.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorTest {

    @Test
    void meanForecastError() {
        double[] data = {12, 15, 13, 16, 14, 12};
        double[] forecast = {14, 13, 12, 13, 15, 14};

        Error e1 = new Error(data, forecast);
        double mfe = e1.meanForecastError();

        assertEquals(mfe, 0.1701, 0.01, "Mean Forecast Error must be the same");
    }

    @Test
    void meanAbsoluteDeviation() {
        double[] data = {12, 15, 13, 16, 14, 12};
        double[] forecast = {14, 13, 12, 13, 15, 14};

        Error e1 = new Error(data, forecast);
        double mad = e1.meanAbsoluteDeviation();
        System.out.println(mad);

        assertEquals(mad, 1.831, 0.01, "Mean Absolute Deviation must be the same");
    }

    @Test
    void trackingSignal() {
        double[] data = {8, 11, 12, 14};
        double[] forecast = {10, 10, 10, 10};

        Error e2 = new Error(data, forecast);
        double ts = e2.trackingSignal();

        assertEquals(ts, 2.222, 0.01, "Tracking Signal must be the same");
    }
}