package com.gabrielnardes.pcpapi.forecasting.entity;

public class SimpleExponentialSmoothing {

    private long id;
    private final double[] d;
    private final double[] ses;
    private final int period;
    private final double alpha;

    public SimpleExponentialSmoothing(double[] d, int period, double alpha) {
        if (alpha > 1.0 | alpha < 0.0) {
            System.out.println("Alpha must be between 0.0 and 1.0");
        }

        if (period < 1) {
            System.out.println("Period must be equal or greater than 2");
        }

        this.d = d;
        this.period = period;
        this.alpha = alpha;

        if (period == 1) {
            ses = new double[(int) d.length];
        } else {
            ses = new double[(int) d.length - period];
        }
    }

    public void print() {
        System.out.println("forecasting.SES with period " + period);

        int adjust = period > 1 ? 2: 1;

        for (int i = 0; i < ses.length; i++) {
            System.out.printf("F[%d]: %.3f\n", i + period + adjust, ses[i]);
        }
    }

    public void calc() {
        if (period == 1) {
            ses[0] = d[0];

            for (int i = 1; i < ses.length; i++) {
                ses[i] = ses[i-1] + alpha * (d[i] - ses[i-1]);
//                System.out.printf("%d: %.1f = %.1f + %.1f * (%.1f - %.1f)\n", i, ses[i], ses[i-1], a, d.get(i), ses[i-1]);
            }
        } else {
            double[] d1 = new double[period];

            for (int i = 0; i < period; i++) {
                d1[i] = d[i];
            }

//            Data d = new Data(d1);
            double[] d = d1;
            SimpleMovingAverage sma = new SimpleMovingAverage(d, period);

            sma.calc();

            ses[0] = sma.get(0) + alpha * (this.d[period] - sma.get(0));

            for (int i = 1; i < ses.length; i++) {
                ses[i] = ses[i-1] + alpha * (this.d[i+period] - ses[i-1]);
//                System.out.printf("%d: %.1f = %.1f + %.1f * (%.1f - %.1f)\n", i+period+2, ses[i], ses[i-1], a, d.get(i+period), ses[i-1]);
            }
        }
    }

    public double[] getSes() {
        return ses;
    }
}