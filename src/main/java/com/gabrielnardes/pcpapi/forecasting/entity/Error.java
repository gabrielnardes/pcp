package com.gabrielnardes.pcpapi.forecasting.entity;

public class Error {
    double[] x;
    double[] y;
    int n;

    public Error(double[] x, double[] y) {
        if (x.length != y.length) {
            System.out.println("X and Y data set must be equal length");
        }

        this.x = x;
        this.y = y;

        this.n = x.length;
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
        return "forecasting.Error{" +
                "x=" + x +
                ", y=" + y +
                ", n=" + n +
                '}';
    }
}
