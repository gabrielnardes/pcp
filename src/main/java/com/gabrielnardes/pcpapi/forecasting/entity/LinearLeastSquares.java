package com.gabrielnardes.pcpapi.forecasting.entity;

public class LinearLeastSquares {
    private final double m;
    private final double b;

    public LinearLeastSquares(double[] x, double[] y) {

        if (x.length != y.length) {
            System.out.println("X and Y data set must be equal length");
        }
        double n = x.length;

        double xSum = 0;
        for (double v : x) {
            xSum += v;
        }

        double ySum = 0;
        for (int i = 0; i < n; i++) {
            ySum += y[i];
        }

        double xySum = 0;
        for (int i = 0; i < n; i++) {
            xySum += x[i] * y[i];
        }

        double xxSum = 0;
        for (double v : x) {
            xxSum += v * v;
        }

        m = (n *xySum - xSum*ySum) / (n *xxSum - xSum*xSum);
        b = (ySum - m*xSum) / n;
    }

    public double calc(double x) {
        return m * x + b;
    }

    public double[] calc(double[] x) {
        double[] y = new double[x.length];

        for (int i = 0; i < x.length; i++) {
            y[i] = m * x[i] + b;
        }

        return y;
    }

    public double[] seasoned(double[] demand, double[] regression, int period) {
        double[] ratio = new double[demand.length];
        for (int i = 0; i < ratio.length; i++) {
            ratio[i] = demand[i] / regression[i];
        }

        double[] m = new double[period];
        for (int i = 0; i < m.length; i++) {
            m[i] = (ratio[i] + ratio[i+period]) / 2;
        }

        double[] multiplier = new double[regression.length];
        for (int i = 0; i < multiplier.length; i++) {
            multiplier[i] = m[i % period];
        }

        double[] y = new double[multiplier.length];
        for (int i = 0; i < y.length; i++) {
            y[i] = regression[i] * multiplier[i];
        }

        return y;
    }

    @Override
    public String toString() {
        return "LLS{" +
                "m=" + m +
                ", b=" + b +
                '}';
    }
}
