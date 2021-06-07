package com.gabrielnardes.pcpapi.forecasting.entity;

import java.util.Arrays;

public class Error {
    private double[] x;
    private double[] y;
    private int n;
    private double meanForecastError;
    private double meanAbsoluteDeviation;
    private double trackingSignal;

    public Error(double[] x, double[] y) {
        if (x.length != y.length) {
            System.out.println("X and Y data set must be equal length");
        }

        this.x = x;
        this.y = y;

        this.n = x.length;

        meanForecastError = meanForecastError();
        meanAbsoluteDeviation = meanAbsoluteDeviation();
        trackingSignal = trackingSignal();
    }

    public double meanForecastError() {
        double errorSum = 0;

        for (int i = 0; i < n; i++) {
            errorSum += x[i] - y[i];
        }

        return errorSum / n;
    }

    public double meanAbsoluteDeviation() {
        double errorSum = 0;

        for (int i = 0; i < n; i++) {
            errorSum += Math.abs(x[i] - y[i]);
        }

        return errorSum / n;
    }

    public double runningSumOfForecastErrors() {
        double errorSum = 0;

        for (int i = 0; i < n; i++) {
            errorSum += x[i] - y[i];
        }

        return errorSum;
    }

    public double trackingSignal() {
        return runningSumOfForecastErrors() / meanAbsoluteDeviation();
    }

    @Override
    public String toString() {
        return "Error{" +
                "\nn=" + n +
                "\nmeanForecastError=" + meanForecastError +
                "\nmeanAbsoluteDeviation=" + meanAbsoluteDeviation +
                "\ntrackingSignal=" + trackingSignal +
                "\nx=" + Arrays.toString(x) +
                "\ny=" + Arrays.toString(y) +
                '}';
    }
}
